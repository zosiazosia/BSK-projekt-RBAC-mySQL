package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.TreatmentDAO;

public class DeleteTreatmentById extends HttpServlet {
	
	
	private static final long serialVersionUID = -648525694385061L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			String name = request.getParameter("id");
			HttpSession session = request.getSession(false);
			PrintWriter writer = response.getWriter();
			
			User user = new User();
			user = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = user.getActiveRoleString();
			TreatmentDAO tDAO = new TreatmentDAO();
			try{
				tDAO.delete(name);
	
				writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
						+ "<body> <center>Zabieg usuniety pomyslnie"
						+ "<form method='get' action='welcome'> "
						+ "<input type='hidden' name='currentActiveUser' value='" + user + "' />"
						+ "<input type='submit' value='Powrot' class='okbutton' />" 
						+ "</form> </center></body></html>");
			}catch(Exception ex){
				ex.printStackTrace();
				writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
						+ "<body> <center>Nie udalo sie usunac zabiegu"
						+ "<form method='get' action='welcome'> "
						+ "<input type='hidden' name='currentActiveUser' value='" + user + "' />"
						+ "<input type='submit' value='Powrot' class='okbutton' />" 
						+ "</form> </center></body></html>");
			}
	}
}
