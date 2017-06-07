package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.RoleDAO;

public class UpdateClientById extends HttpServlet {


	private static final long serialVersionUID = 8246185705506542114L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			HttpSession session = request.getSession(false);
			
			User userr = new User();
			userr = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = userr.getActiveRoleString();
			RoleDAO rc = new RoleDAO();
			Role sessionRole = rc.getRole(sessionRoleString);
			
			PrintWriter writer = response.getWriter();
			writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />"
					+ "<head>");
			
			if (typ.equals("update")){
				writer.println("<title>Edycja klienta</title></head>" 
						+ "<body> <center><table><tr><center>");
				
				writer.print("<th><form action='updateClient' method='post'>"
						+ "<input type='hidden' name='typ' value='update'/>"
						+ "<input type='hidden' name='currentSessionUser' value=" + userr + " />"
						+ "Podaj ID klienta: </br><input name='id' value=''/>"
						+ "<input type='submit' value='Edytuj' class='okbutton' /></form></th>" );
			}
			if (typ.equals("delete")){
				writer.println("<title>Usuwanie klienta</title></head>" 
						+ "<body> <center><table><tr><center>");
				writer.print("<th><form action='deleteClient' method='post'>"
						+ "<input type='hidden' name='typ' value='delete'/>"
						+ "<input type='hidden' name='currentSessionUser' value=" + userr + " />"
						+ "Podaj ID klienta: </br><input name='id' value=''/>"
						+ "<input type='submit' value='Usun' class='okbutton' /></form></th>" );				
			}
			//button to logout
			writer.print("</center></tr></table>"
						+ "<form method='get' action='welcome'> "
						+ "<input type='hidden' name='currentActiveUser' value='" + userr + "' />"
						+ "<input type='submit' value='Powrot' class='okbutton' />" 
						+ "</form>"
						+ "<form action='logout' method='post'>"
						+ "<input type='submit' value='Logout' class='okbutton' /></form>" );
			
			
			writer.print("</center>" + "</body>" + "</html>");	
			
			
			
			
	}

}

