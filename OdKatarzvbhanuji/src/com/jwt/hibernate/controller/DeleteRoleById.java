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

public class DeleteRoleById extends HttpServlet {
	
	
	private static final long serialVersionUID = -648521294120385061L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			Long id = Long.valueOf(request.getParameter("id")).longValue();
			HttpSession session = request.getSession(false);
			PrintWriter writer = response.getWriter();
			
			User user = new User();
			user = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = user.getActiveRoleString();
			RoleDAO rDAO = new RoleDAO();
		//	rDAO.getRole(id);
			rDAO.delete(id);
			writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
					+ "<body> <center>Rola usunieta pomyslnie"
					+ "<form method='get' action='welcome'> "
					+ "<input type='hidden' name='currentActiveUser' value='" + user + "' />"
					+ "<input type='submit' value='Powrot' class='okbutton' />" 
					+ "</form> </center></body></html>");
	}
}
