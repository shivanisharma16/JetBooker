package com.project.programs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Schedule
 */
@WebServlet("/Schedule")
public class Schedule extends HttpServlet {
	
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
		request.getRequestDispatcher("NavBar.jsp").include(request, response);
		request.getRequestDispatcher("Schedule.html").include(request, response);
	}

}
