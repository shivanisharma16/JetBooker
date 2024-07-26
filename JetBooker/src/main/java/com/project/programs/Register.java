package com.project.programs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class program2
 */
@WebServlet("/registeruser")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   public static Connection con = null;
	   public static Statement stat = null ;
	   public static ResultSet result = null;
	   String url = "jdbc:mysql://localhost:3306/ARS";
	   String user = "root";
	   String pwd = "root";
	   private static final String  QUERY = "INSERT into `USER` (`First_name`, `Last_name`, `DOB`,`Age`, `Mobile`, `Password`, `Gender`, `email`,`Access`) values (?,?,?,?,?,?,?,?,?)"; 
	   
	   
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			con = DriverManager.getConnection(url, user, pwd);
			stat = con.createStatement();
			result = stat.executeQuery("SELECT * from user ");
		
		
		
		
		
		
		String fname = noSpace(request.getParameter("fname"));
		String lname = noSpace(request.getParameter("lname"));
		String email = noSpace(request.getParameter("email"));
		String age = noSpace(request.getParameter("age"));
		String phone = noSpace(request.getParameter("phone"));
		String dob = noSpace(request.getParameter("dob"));
		String password = noSpace(request.getParameter("password"));
		String cpassword = noSpace(request.getParameter("co-password"));
		String gender = noSpace(request.getParameter("gender"));
	
	
		
		
		
			response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 PreparedStatement stmt = con.prepareStatement(QUERY);
	         
			 if(password.equals(cpassword)) {
				 
				 stmt.setString(1,fname);
				 stmt.setString(2, lname);
				 stmt.setString(3, dob);
				 stmt.setString(4, age);
				 stmt.setString(5, phone);
				 stmt.setString(6, password);
				 stmt.setString(7,gender);
				 stmt.setString(8,email);
				 stmt.setString(9,"USER");
				 
				 stmt.executeUpdate();
				 System.out.println("Registered Successfully");
				 
				 out.println(fname + "<h5> You have Registered Successfully</h5>");
				 request.getRequestDispatcher("login.jsp").include(request, response);
			 }
			 else {
				 out.println("<h5>Password doesn't match! Try Again...</h5>");
				 request.getRequestDispatcher("register.jsp").include(request, response);
				 System.out.println("Registeration Unuccessfull");
				 
				 
				 
			 }
	         
	        	
		 
        	 
             
		
		} catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        	response.sendRedirect("ErrorPage.html");
            e.printStackTrace();
        }
		

	} 
	
	public void distroy(){
		try {
			result.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private static String noSpace(String s){
	      s = s.replaceAll(" ", "");
	      return s;
	    }

}
