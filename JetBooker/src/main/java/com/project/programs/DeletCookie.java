package com.project.programs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeletCookie")
public class DeletCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		Cookie ck1 = new Cookie("Name" ,"Del" );
		   Cookie ck2 = new Cookie("Email" ,"Del" );
		   Cookie ck3 = new Cookie("Access" ,"Del" );
		   ck1.setMaxAge(0);
		   ck2.setMaxAge(0);
		   ck3.setMaxAge(0);
		   response.addCookie(ck1);
		   response.addCookie(ck2);
		   response.addCookie(ck3);
		   response.sendRedirect("Home");
		   
		}
		catch(Exception e) {
			response.sendRedirect("ErrorPage.html");
		}
	}

	

}
