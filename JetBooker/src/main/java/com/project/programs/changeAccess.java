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
 * Servlet implementation class changeAccess
 */
@WebServlet("/changeAccess")
public class changeAccess extends HttpServlet {
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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        	
        	String uEmail = request.getParameter("uemail");
        	String uAccess = request.getParameter("uAccess");
        	String Change = null;
        	
        	 PrintWriter out = response.getWriter();
 			response.setContentType("text/html");
 			
 			if(uAccess.equals("ADMIN")) {
 				Change = "USER";
 			}
 			else if(uAccess.equals("USER")) {
 				Change = "ADMIN";
 			}
 			String QUERY = "UPDATE USER SET ACCESS = '"+Change+"' WHERE EMAIL = '"+uEmail+"'";
 			try {
 				con = DriverManager.getConnection(url, user, pwd);
 		      pstat = con.prepareStatement(QUERY);
 		      pstat.executeUpdate(QUERY);
 		     response.sendRedirect("UserList");
 		      
 		      
 		      
 			} catch (SQLException e) {
 				
// 				e.printStackTrace();
 				System.out.println("changAccess line 95");
 			}
 			
 			
        	
	}

}
