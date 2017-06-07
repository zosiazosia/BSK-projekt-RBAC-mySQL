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

public class UpdateRoleById extends HttpServlet {


	private static final long serialVersionUID = 8060801190148669905L;

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
					+ "<head>"
							+ "<title>Edytuj role</title></head>" 
							+ "<body> <center><table><tr><center>");
			
			
			//button to users table
			writer.print("<th><form action='updateRole' method='post'>"
					+ "<input type='hidden' name='typ' value='update'/>"
					+ "<input type='hidden' name='currentSessionUser' value=" + userr + " />"
					+ "<input name='id' value='' placeholder='id'/>"
				+	"<input type='submit' value='Edytuj' class='okbutton' /></form></th>" );
			
			//button to logout
			writer.print("</center></tr></table><form action='logout' method='post'>"
				+ 	"<input type='submit' value='Logout' class='okbutton' /></form>" );
			
			
			writer.print("</center>" + "</body>" + "</html>");	
			
			
			
			
	}

}

