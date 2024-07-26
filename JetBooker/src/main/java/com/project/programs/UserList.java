package com.project.programs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Connection con = null;
	public static PreparedStatement pstat = null ;
	public static ResultSet result = null;
	String url = "jdbc:mysql://localhost:3306/ARS";
	String user = "root";
	String pwd = "root";
       
	
	
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Name = null;
		String Email = null;
		
		
		   String Age = null;  
		   String DOB = null;  
		   
		   String Mobile = null;  
		   String Gender = null;  
		   String Access = null; 
		   String uAccess = null; 
		   String uemail = null;  
		   String uName = null;  
		   
		   int n = 0;
		   
		//Check login
				Cookie[] cookies = request.getCookies();
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
		      	if (cookies != null) {
		      		// loop through the cookies and find the one with the name "myCookie"
		      		for (Cookie cookie : cookies) {
		      			if (cookie.getName().equals("Access")) {
		      				// do something with the cookie's value
		      				Access = cookie.getValue();
		      			}
		      			
		      		}
		      	}
		      	if(CheckEmail == null) {
		      		response.sendRedirect("login.jsp");
		      	}
		      	else if(!Access.equals("ADMIN")){
        			response.sendRedirect("Home");
        		}
		      

		      Cookie[] ck = request.getCookies();
		      
		      if (ck != null) {
		    	    for (Cookie ckName : ck) {
		    	        if (ckName.getName().equals("Name")) {
		    	            Name = ckName.getValue();
		    	            // do something with the cookie value
		    	           
		    	        }
		    	    }
		    	}
		      if (ck != null) {
		    	    for (Cookie ckName : ck) {
		    	        if (ckName.getName().equals("Email")) {
		    	            Email = ckName.getValue();
		    	            // do something with the cookie value
		    	           
		    	        }
		    	    }
		    	}
		      
		    PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
		String QUERY = "SELECT First_Name, Last_Name, DOB, Age, Mobile, Gender,email, Access FROM USER order by First_Name asc";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
	      pstat = con.prepareStatement(QUERY);
	      pstat.executeQuery(QUERY);
	      result = pstat.executeQuery(QUERY);
	      
	      	request.getRequestDispatcher("NavBar.jsp").include(request, response);
	      	out.println("<div class=\"ubody\">");
			out.println("<h2>Users</h2>");
			
	      while(result.next()){
	    	  uName = result.getString(1) + " " + result.getString(2);
	    	  DOB = result.getString(3);
	    	  Age = result.getString(4);
	    	  Mobile = result.getString(5);
	    	  Gender = result.getString(6);
	    	  uemail = result.getString(7);
	    	  uAccess = result.getString(8);
	    	  
//	    	  if(n == 0) {
//					out.printf("<div class=\"tagline\"><id  class=\"element\">Passenger</id> <from class=\"element\">Date</from> <to class=\"element\">Time</to> <departure class=\"element\">Seat No.</departure> <arival class=\"element\">Departure</arival> <price class=\"element\">Destination</price> <Action class=\"element\">Action</Action></div>");
//				}
//	    	  
	    	  out.println(" <box> <form action=\"changeAccess\" method = \"post\" class=\"element\"> <input type=\"hidden\" name=\"uemail\" value=\""+uemail+"\"> <input type=\"hidden\" name=\"uAccess\" value=\""+uAccess+"\"> <id  class=\"element\">"+uName+"</id>   <departure class=\"element\">"+result.getString(4)+"</departure> <arival class=\"element\">"+result.getString(5)+"</arival> <from class=\"element\">"+result.getString(7)+"</from> <price class=\"element\">"+uAccess+"</price> <button  type=\"submit\">Change Access</button> </form> </box>");
				n++;
	    	  
	      }
	      if(n==0) {
	    	  out.println("<h3>No Tickets Here!</h3>");
	      }
	      out.println("</div>");
	      
		} catch (SQLException e) {
			
//			e.printStackTrace();
			System.out.println("UserList line 153");
		}
		
	}

}
