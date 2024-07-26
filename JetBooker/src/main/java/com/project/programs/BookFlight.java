package com.project.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookFlight
 */
@WebServlet("/BookFlight")
public class BookFlight extends HttpServlet {
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
		try {
		String Flight = request.getParameter("Flight");
		String Date = request.getParameter("Date");
		String pass = request.getParameter("pass");
		String Class = request.getParameter("Class");
//		System.out.println(Flight);
//		System.out.println(Date);
//		System.out.println(pass);
//		
		//converting string into integer
		int n = Integer.parseInt(pass);
		request.getRequestDispatcher("NavBar.jsp").include(request, response);
		request.getRequestDispatcher("Travlers.jsp").include(request, response);
		}
		catch(Exception e) {
			System.out.println("Error Page");
			
		}
		
		
		
	}

	
}
