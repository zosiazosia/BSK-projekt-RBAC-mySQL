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
import com.jwt.hibernate.dao.ClientDAO;
import com.jwt.hibernate.dao.RoleDAO;

public class WelcomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession(true);
		User user = new User();
		user = (User) session.getAttribute("currentSessionUser");
		String sessionRoleString = user.getActiveRoleString();
		RoleDAO rc = new RoleDAO();
		Role sessionRole = rc.getRole(sessionRoleString);
				
		String registerResult = (String) session.getAttribute("registerResult");

		writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
			+   "<body> <center> </br>"
			+	"</br> </br> " );
		
		if (sessionRole.isReadUser()){
			//button to users table
			writer.print("<form action='users' method='post'>"
					+ "<input type='hidden' name='typ' value='read'/>"
				+	"<input type='submit' value='Tabela uzytkownikow' class='okbutton' /></form>" );
		}
		
		if (sessionRole.isCreateRole()){
			writer.print("<form action='addRole' method='post'>"
					+	"<input type='submit' value='Nowa rola' class='okbutton' /></form>");
		}
		
		if(sessionRole.isCreateUser()){
			writer.println("<form method='post' action='register.jsp'><center>"
					+ "<button class='okbutton'>Nowy uzytkownik</button>"
					+ "</center></form>");
		}
		
		if(sessionRole.isCreateClient()){
			writer.println("<form method='post' action='newClient.jsp'><center>"
					+ "<button class='okbutton'>Nowy klient</button>"
					+ "</center></form>");
		}
		if(sessionRole.isReadClient()){
			writer.print("<form action='clients' method='post'>"
					+ "<input type='hidden' name='typ' value='read'/>"
				+	"<input type='submit' value='Tabela klientow' class='okbutton' /></form>" );
		}
		if(sessionRole.isReadAppointment()){
			writer.print("<form action='appointments' method='post'>"
					+ "<input type='hidden' name='typ' value='read'/>"
				+	"<input type='submit' value='Tabela wizyt' class='okbutton' /></form>" );
		}
		if(sessionRole.isReadRole()){
			writer.print("<form action='roles' method='post'>"
					+ "<input type='hidden' name='typ' value='read'/>"
				+	"<input type='submit' value='Tabela rol' class='okbutton' /></form>" );
		}
		
		if(sessionRole.isReadTreatment()){
			writer.print("<form action='treatments' method='post'>"
					+ "<input type='hidden' name='typ' value='read'/>"
				+	"<input type='submit' value='Tabela zabiegow' class='okbutton' /></form>" );
		}
		
		//button to logout
		writer.print("<form action='logout' method='post'>"
			+ 	"<input type='submit' value='Logout' class='okbutton' /></form>" );
		
		
		writer.print("</center>" + "</body>" + "</html>");
	}
}


/*
select u.id, u.login, u.activeRoleString, ur.user_id, r.type from user u,
 user_role ur, role r where u.id=ur.user_id and r.id=ur.role_id;
*/