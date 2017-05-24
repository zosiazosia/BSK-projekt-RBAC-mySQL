package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NoSuccess extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		HttpSession session = request.getSession(true);

		String registerResult = (String) session.getAttribute("registerResult");

		writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
				+ "<body> <center> </br>" );
		if (registerResult == "Rejestracja poprawna"){
			writer.print(registerResult + 
					"</br> </br> <a class = 'okbutton' href='/HibernateWebApp/welcome'>Powrot do strony glownej</a>");					
		}	
		else if (registerResult == "Niepoprawne logowanie"){
			writer.print(registerResult + 
					"</br> </br> <a class = 'okbutton' href='/HibernateWebApp'>Logowanie</a>");				
		}
		else if(registerResult != null){
			writer.print(registerResult + 
					"</br> </br> <a class = 'okbutton' href='/HibernateWebApp/register.jsp'>Rejestracja</a>"
					+ "</br> </br> <a class = 'okbutton' href='/HibernateWebApp'>Logowanie</a>");
		}	
		writer.println("</center>" + "</body>" + "</html>");
	}
}
