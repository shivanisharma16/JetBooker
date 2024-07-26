package com.project.programs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;


import java.security.SecureRandom;


@WebServlet("/Tickets")
public class Tickets extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static Connection con, con2 = null;
	   public static Statement stat = null ;
	   public static ResultSet result, result2 = null;
	
	   String url = "jdbc:mysql://localhost:3306/ARS";
	   String user = "root";
	   String pwd = "root";
	   private static final String  QUERY = "select * from seats where Flight_Number = ? and Date = ?"; 
	   
	   private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    private static final SecureRandom RANDOM = new SecureRandom();
	   
	   
	   public void init() throws ServletException{
		   try {
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   DriverManager.getConnection(url, user, pwd);
		   }
		   catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   static void close() throws SQLException, IOException {
	        if(result != null){
	            result.close();
	        }
	        if(result2 != null){
	            result2.close();
	        }
	        if(stat != null){
	            stat.close();
	        }
	        if(con != null){
	            con.close();
	        }
	        if(con2 != null){
	        	con2.close();
	        }
	        
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String Flight = request.getParameter("Flight");
		String pass = request.getParameter("pass");
		
		String Date = request.getParameter("Date");
		String Class = request.getParameter("Class");
		String Email = null;
		String Time = null;
		String From = null;
		String To = null;
		
		String gs = "<div class=\"Message-box\"> <h3>";
		String ge = "<h3><a href=\"Home\">Go To HomePage</a><a href=\"MyTickets\">View Tickets</a></div>";
		String rs = "<div class=\"Message-box\"> <h4>";
		String re = "</h4><a href=\"Home\">Go To HomePage</a></div>";
		
		response.setContentType("text/html");
		request.getRequestDispatcher("NavBar.jsp").include(request, response);
		
		
		
		int n =  Integer.parseInt(pass);
		int count = 0;
		int t = 0;
		int l = 8;              //length for Random ticketId
		
		String[] Travlers = new String[n];
		String[] ages = new String[n];
		String[] seat = new String[n];
		String[] tid = new String[n];
		
		
		for(int i=0; i < n; i++) {
			Travlers[i]  = request.getParameter("travler"+i);
			ages[i]  = request.getParameter("agegroup"+i);
		}
		
		
// Reading cookie for userEnail 
		Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            // loop through the cookies and find the one with the name "myCookie"
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Email")) {
                    // do something with the cookie's value
                    Email = cookie.getValue();
                }
            }
        }
		
		 
		try {
			con = DriverManager.getConnection(url, user, pwd);
			stat = con.createStatement();
			
			PreparedStatement pstmt = con.prepareStatement(QUERY);
			
			
			pstmt.setString(1,Flight);
			pstmt.setString(2,Date);
			
			result = pstmt.executeQuery();
			
			while(count == 0 && result.next()) {
				if(Class.equals("Economy")) {
				
					for(int i = 3; i<= 12; i++) {
					
						if(result.getString(i).equals("0")) {
							if(i+n>13) {
								System.out.println("No Enough Seats only "+(13-i)+" seats left!");
								out.println(rs+"No Enough Seats only "+(13-i)+" seats left!"+re);
								
								break;
							}
							while(n>0) {
							if(i-12>10) {
								System.out.println("No More Seats are availble");
								out.println(rs+"No More Seats are availble"+re);
								
								break;
							}
							tid[t] = generateRandomString(l);
							String query3 = "UPDATE seats SET "+"E"+(i-2)+" ='"+tid[t]+"' WHERE Date = '"+Date+"'";
							pstmt.executeUpdate(query3);
							seat[t] =("E"+(i-2));
							System.out.println("'E"+(i-2)+"' is booked");
							t++;
							n--;
							if(n<=0) {
								out.println(gs+"Successfully Booked "+t+" Seats"+ge);
								System.out.println("<h3>Successfully Booked "+t+" Seats</h3>");
								
								
								break;
							}
							i++;
							}
							break;
						}else if(!result.getString(12).equals("0")) {
							System.out.println("This Class is Full");
							out.println(rs+"This Class is Full"+re);
							break;
						}
						
						
					}
				}else if(Class.equals("Bussiness")){
					for(int i = 13; i<= 22; i++) {
						
						if(result.getString(i).equals("0")) {
							if(i+n>23) {
								System.out.println("No Enough Seats only "+(23-i)+" seats left!");
								out.println(rs+"No Enough Seats only "+(23-i)+" seats left!"+re);
								break;
							}
							while(n>0) {
							if(i-12>10) {
								out.println(rs+"No More Seats are availble"+re);
								
								break;
							}
							tid[t] = generateRandomString(l);
							String query3 = "UPDATE seats SET "+("B"+(i-12))+" ='"+tid[t]+"' WHERE Date = '"+Date+"'";
							pstmt.executeUpdate(query3);
							seat[t] =("B"+(i-22));
							t++;
							System.out.println("b"+(i-22)+" is booked");
							n--;
							if(n<=0) {
								out.println(gs+" Successfully Booked "+t+" Seats " +ge);
								
								break;
							}
							i++;
							}
							break;
						}else if(!result.getString(22).equals("0")) {
							System.out.println("This Class is Full");
							out.println(rs+"This Class is Full"+re);
							break;
						}
						
					}
				}else if(Class.equals("Premium")){
					for(int i = 23; i<= 32; i++) {
						if(result.getString(i).equals("0")) {
							if(i+n>33) {
								System.out.println("No Enough Seats only "+(33-i)+" seats left!");
								out.println(rs+"No Enough Seats only "+(33-i)+" seats left!"+re);
								break;
							}
							while(n>0) {
							if(i-22>10) {
								System.out.println("No More Seats are availble");
								out.println(rs+"No More Seats are availble"+re);
								break;
							}
							tid[t] = generateRandomString(l);
							String query3 = "UPDATE seats SET "+("P"+(i-22))+" ='"+tid[t]+"' WHERE Date = '"+Date+"'";
							pstmt.executeUpdate(query3);
							seat[t] =("P"+(i-22));
							t++;
							System.out.println("e"+(i-22)+" is booked");
							n--;
							if(n<=0) {
								out.println(gs+" Successfully Booked "+t+" Seats "+ge);
								break;
							}
							i++;
							}
							break;
						}else if(!result.getString(32).equals("0")) {
							System.out.println("This Class is Full");
							out.println(rs+"This Class is Full"+re);
							break;
						}
						
					}
				}else {
					for(int i = 33; i<= 42; i++) {
						if(result.getString(i).equals("0")) {
							if(i+n>43) {
								System.out.println("No Enough Seats only "+(43-i)+" seats left!");
								out.println(rs+"No Enough Seats only "+(43-i)+" seats left!"+re);
								break;
							}
							while(n>0) {                                                                  // n travlers left
							if(i-32>10) {
								System.out.println("No More Seats are availble");
								out.println(rs+"No More Seats are availble"+re);
								break;
							}
							tid[t] = generateRandomString(l);
							String query3 = "UPDATE seats SET "+("F"+(i-32))+" ='"+tid[t]+"' WHERE Date = '"+Date+"'";
							pstmt.executeUpdate(query3);
							seat[t] =("F"+(i-32));
							t++;
							System.out.println("e"+(i-32)+" is booked");
							n--;
							if(n<=0) {
								out.println(gs+" Successfully Booked "+t+" Seats " +ge);
								break;
							}
							i++;
							}
							break;
						}else if(!result.getString(42).equals("0")) {
							System.out.println("This Class is Full");
							out.println(rs+"This Class is Full"+re);
							break;
						}
						
					}
				}
				
				
				
				System.out.println("phase 1 successfully executed");
				count++;
				break;
			}
			while(count == 0 && !result.next()) {
				
				   try {
					   String  QUERY2 = "INSERT INTO seats (date, Flight_Number) VALUES('"+Date+"','"+Flight+"')";
					pstmt.executeUpdate(QUERY2);
					   System.out.println("Flight Added Successfully");
					   request.getRequestDispatcher("Tickets").forward(request, response);
					   count++;
				   } catch (SQLException e) {
						 System.out.println("Flight Creation Unuccessfull");
				            e.printStackTrace();
				   }
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		System.out.println("Exception occured");
		}
//Fetch Flights data from Flights database
		
		String getFlightData = "select Flight_time, Departure, Destination from flights where Flight_Number = '"+Flight+"'";
		try {
			con2 = DriverManager.getConnection(url, user, pwd);
			PreparedStatement pstmt3 = con2.prepareStatement(getFlightData);
			result2 = pstmt3.executeQuery();
			while(result2.next()) {
			Time = result2.getString(1);
			From = result2.getString(2);
			To = result2.getString(3);
			
			}
			System.out.println("Data Fetched from Flights database Successfully");
			System.out.println(Email);
		} catch (SQLException e1) {
			System.out.println("Something wrong in fetching data from flights");
			e1.printStackTrace();
		}
		
		
		

		
		
		
// inserting data into tickets database
		try {
		for(int i= 0;i<t;i++) {
			
			String CreateTicket = "INSERT INTO TICKETS(id, name, class, seat, Date,Flight_time, Flight_Number, Departure, Destination, Email) VALUES('"+tid[i]+"','"+Travlers[i]+"','"+Class+"','"+seat[i]+"','"+Date+"','"+Time+"','"+Flight+"','"+From+"','"+To+"','"+Email+"')";
				PreparedStatement pstmt2 = con.prepareStatement(CreateTicket);
				pstmt2.executeUpdate(CreateTicket);
				System.out.println("Ticket "+(i+1)+" Created SuccessFully");
				
				
		}
		} catch (SQLException e) {
			System.out.println("Error in ticket creation");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		

	}
	public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

}
