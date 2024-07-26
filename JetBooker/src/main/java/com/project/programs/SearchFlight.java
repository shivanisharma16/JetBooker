package com.project.programs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchFlight
 */
@WebServlet("/SearchFlight")
public class SearchFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Connection con = null;
	public static PreparedStatement pstat = null ;
	public static ResultSet result = null;
	String url = "jdbc:mysql://localhost:3306/ARS";
	String user = "root";
	String pwd = "root";
       
	static void close() throws SQLException, IOException {
        if(result != null){
            result.close();
        }
        if(pstat != null){
            pstat.close();
        }
        if(con != null){
            con.close();
        }
        
    }
	
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		
		String From = req.getParameter("FromCity");
		String To = req.getParameter("ToCity");
		String Departure = req.getParameter("DDate");
		String Class = req.getParameter("Class");
		String pass = req.getParameter("pass");
		String Day = null;
		String Query = null;
		int Price = 0;
		
		
		//Check login
		Cookie[] cookies = req.getCookies();
		String CheckEmail = null;
        	if (cookies != null) {
                // loop through the cookies and find the one with the name "myCookie"
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("Email")) {
                        // do something with the cookie's value
                        CheckEmail = cookie.getValue();
                    }
                    
                }
            }
        	if(CheckEmail == null) {
        		resp.sendRedirect("login.jsp");
        	}
        	
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		Day = getDay(Departure);
		
		try {
			if(Day.equals("MON")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  Mon = 'TRUE' ";
			}
			else if(Day.equals("TUE")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  TUE = 'TRUE' ";
			} else if(Day.equals("WED")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  WED = 'TRUE' ";
			} else if(Day.equals("THU")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  THU = 'TRUE' ";
			} else if(Day.equals("FRI")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  FRI = 'TRUE' ";
			} else if(Day.equals("SAT")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  SAT = 'TRUE' ";
			} else if(Day.equals("SUN")) {
				Query = "select * from flights where Departure = ? AND Destination = ? AND  SUN = 'TRUE' ";
			}
			
			pstat = con.prepareStatement(Query);
			pstat.setString(1, From);
			pstat.setString(2, To);
			
			
			result = pstat.executeQuery();
			
			req.getRequestDispatcher("NavBar.jsp").include(req, resp);
			System.out.println(Day);
			out.println("<div class=\"cbody\">");
			out.println("<h2>"+From +" To "+To+"</h2>");
			out.println("<h4>"+Day +" : "+ Departure+"</h4>");
			int count = 0;
			while(result.next()) {
				count++;
				if(Class.equals("Economy")) {
					Price = result.getInt(6);
				}
				else if(Class.equals("Bussiness")) {
					Price = result.getInt(7);
				}
				else if(Class.equals("Premium")) {
					Price = result.getInt(8);
				}
				else if(Class.equals("First-Class")) {
					Price = result.getInt(9);
				}
				if(count == 1) {
					out.println("<div class=\"tagline\"><id  class=\"element\">FlightId</id> <from class=\"element\">Departure</from> <to class=\"element\">Destination</to> <departure class=\"element\">FlightTime</departure> <arival class=\"element\">LandingTime</arival> <price class=\"element\">Price</price> <Action class=\"element\">Action</Action></div>");
				}
				out.println(" <box> <form action=\"BookFlight\" class=\"element\"> <input type=\"hidden\" name=\"Date\" value=\""+Departure+"\"> <input type=\"hidden\" name=\"Class\" value=\""+Class+"\"> <input type=\"hidden\" name=\"pass\" value=\""+pass+"\"> <id  class=\"element\">"+result.getString(5)+"</id> <from class=\"element\">"+result.getString(1)+"</from> <to class=\"element\">"+result.getString(2)+"</to> <departure class=\"element\">"+result.getString(3)+"</departure> <arival class=\"element\">"+result.getString(4)+"</arival> <price class=\"element\">"+Price+"</price> <button name=\"Flight\" value=\""+result.getString(5)+"\"  type=\"submit\">Book</button> </form> </box>");
//				System.out.println(" <box> <form action=\"BookFlight\" class=\"element\"> <id class=\"element\">"+result.getString(5)+"</id> <from class=\"element\">"+result.getString(1)+"</from> <to class=\"element\">"+result.getString(2)+"</to> <departure class=\"element\">"+result.getString(3)+"</departure> <arival class=\"element\">"+result.getString(4)+"</arival> <price class=\"element\">"+Price+"</price> <button name=\"Flight\" value=\""+result.getString(5)+"\"  type=\"submit\">Book</button> </form> </box>");
				
				out.println("<br>");
				}
			if(count == 0) {
				out.println("<h3>Sorry! No Flights Available On This Rout.</h3>");
			}
			out.println("</div>");

			
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("erroer in before line 157");
		}
		}
		catch(Exception e) {
			resp.sendRedirect("ErrorPage.html");
		}
		
	}
	private static String getDay(String Date) {
		try {
		      java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Date);
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(date);
		      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		      
//Day will return the day name of the week... 
		      return getDayOfWeek(dayOfWeek);
		    } catch (ParseException e) {
		      System.out.println("Error parsing the input date: " + Date);
		      return "error";
		    }
	}
	 private static String getDayOfWeek(int value) {
		    switch (value) {
		      case Calendar.MONDAY:
		        return "MON";
		      case Calendar.TUESDAY:
		        return "TUE";
		      case Calendar.WEDNESDAY:
		        return "WED";
		      case Calendar.THURSDAY:
		        return "THU";
		      case Calendar.FRIDAY:
		        return "FRI";
		      case Calendar.SATURDAY:
		        return "SAT";
		      case Calendar.SUNDAY:
		        return "SUN";
		      default:
		        return "Unknown";
		    }
		  }


}
