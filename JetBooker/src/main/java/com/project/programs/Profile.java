package com.project.programs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        	if(CheckEmail == null) {
        		response.sendRedirect("login.jsp");
        	}
		request.getRequestDispatcher("NavBar.jsp").include(request, response);
		request.getRequestDispatcher("UserProfile.jsp").include(request, response);
	}


}
