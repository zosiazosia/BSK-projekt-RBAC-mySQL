package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.UserDAO;

public class LogoutController extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession(true);
		User user = new User();
		user = (User) session.getAttribute("currentSessionUser");
		UserDAO dao = new UserDAO();
		boolean loggedOut = dao.logout(user);
		
		writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
				+ "<body> <center> </br>" );
				if (loggedOut){
					session.setAttribute("currentSessionUser", null);
					writer.print("Wylogowano pomyslnie");
				}
				else {
					writer.print("Nie udalo sie wylogowac");
				}
				writer.print("</br> </br> <a class = 'okbutton' href='/HibernateWebApp'>Logowanie</a>");					
		
		
		writer.println("</center>" + "</body>" + "</html>");
		
	}
}
