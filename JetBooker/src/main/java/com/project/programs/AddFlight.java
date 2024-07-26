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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Connection con = null;
	   public static Statement stat = null ;
	   public static ResultSet result = null;
	   String url = "jdbc:mysql://localhost:3306/ARS";
	   String user = "root";
	   String pwd = "root";
	   private static final String  QUERY = "INSERT into `FLIGHTS` (`Departure`, `Destination`, `Flight_Time`,`Landing_Time`, `Flight_Number`, `E_Price`, `B_Price`, `PE_Price`, `FC_Price`, `Mon`,`Tue`,`Wed`, `Thu`, `Fri`, `Sat`, `Sun`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	   
	   static void close() throws SQLException, IOException {
	        if(result != null){
	            result.close();
	        }
	        if(stat != null){
	            stat.close();
	        }
	        if(con != null){
	            con.close();
	        }
	        
	    }
	   
	   public void init() throws ServletException{
		   try {
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   DriverManager.getConnection(url, user, pwd);
		   }
		   catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Check login
				Cookie[] cookies = request.getCookies();
				String CheckEmail = null;
		        	if (cookies != null) {
		                // loop through the cookies and find the one with the name "myCookie"
		                for (Cookie cookie : cookies) {
		                    if (cookie.getName().equals("Access")) {
		                        // do something with the cookie's value
		                        CheckEmail = cookie.getValue();
		                    }
		                    
		                }
		            }
		        	if(CheckEmail != null) {
		        		if(!CheckEmail.equals("ADMIN")){
		        			response.sendRedirect("Home");
		        		}
		        	} else {
		        		response.sendRedirect("login.jsp");
		        	}
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			stat = con.createStatement();
//			result = stat.executeQuery("SELECT * from user ");
		
		
		
		
		
		
		String Departure = request.getParameter("Departure");
		String Destination = request.getParameter("Destination");
		String Flight_Time = request.getParameter("Flight_Time");
		String Landing_Time = request.getParameter("Landing_Time");
		String Flight_Number = request.getParameter("Flight_Number");
		String E_Price = request.getParameter("E_Price");
		String B_Price = request.getParameter("B_Price");
		String PE_Price = request.getParameter("PE_Price");
		String FC_Price = request.getParameter("FC_Price");
		String Mon = request.getParameter("Mon");
		String Tue = request.getParameter("Tue");
		String Wed = request.getParameter("Wed");
		String Thu = request.getParameter("Thu");
		String Fri = request.getParameter("Fri");
		String Sat = request.getParameter("Sat");
		String Sun = request.getParameter("Sun");
		
		
	
			 PrintWriter out = response.getWriter();
			 PreparedStatement stmt = con.prepareStatement(QUERY);
   
			 
				 
				 stmt.setString(1,Departure);
				 stmt.setString(2,Destination);
				 stmt.setString(3,Flight_Time);
				 stmt.setString(4,Landing_Time);
				 stmt.setString(5,Flight_Number);
				 stmt.setString(6,E_Price);
				 stmt.setString(7,B_Price);
				 stmt.setString(8,PE_Price);
				 stmt.setString(9,FC_Price);
				 if(Mon == null) {
					 stmt.setString(10,"FALSE");
				 }
				 else {
					 stmt.setString(10,"TRUE");
				 }
				 if(Tue == null) {
					 stmt.setString(11,"FALSE");
					 
				 }
				 else {
					 stmt.setString(11,"TRUE");
				 }
				 if(Wed == null) {
					 
					 stmt.setString(12,"FALSE");
				 }
				 else {
					 stmt.setString(12,"TRUE");
				 }
				 if(Thu == null) {
					 stmt.setString(13,"FALSE");
					 
				 }
				 else {
					 stmt.setString(13,"TRUE");
				 }
				 if(Fri == null) {
					 stmt.setString(14,"FALSE");
					 
				 }
				 else {
					 stmt.setString(14,"TRUE");
				 }
				 if(Sat == null) {
					 stmt.setString(15,"FALSE");
					 
				 }
				 else {
					 stmt.setString(15,"TRUE");
				 }
				 if(Sun == null) {
					 stmt.setString(16,"FALSE");
					 
				 }
				 else {
					 stmt.setString(16,"TRUE");
				 }
				 
				 
				 stmt.executeUpdate();
				 System.out.println(Departure +" To "+ Destination +" Flight Added Successfully");
				 out.println("<h3>Flight Added Successfully</h3>");
				 request.getRequestDispatcher("AddFlight").include(request, response);
				 
		} catch (IOException e) {
			 System.out.println("Registeration Unuccessfull");
		
		
            e.printStackTrace();
        } catch (SQLException e) {
        	response.sendRedirect("ErrorPage.html");
            e.printStackTrace();
        }
	

	} 
		
	}


