package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.TreatmentDAO;

public class UpdateTreatment extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Treatment treatment = new Treatment();
		TreatmentDAO tDAO = new TreatmentDAO();
		String name = request.getParameter("id");
		treatment = tDAO.get(name);
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession(false);
		User userr = new User();
		userr = (User) session.getAttribute("currentSessionUser");
		String sessionRoleString = userr.getActiveRoleString();
		RoleDAO rc = new RoleDAO();
		Role sessionRole = rc.getRole(sessionRoleString);
		String none = "";
		
		writer.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Edycja zabiegu</title> "
				+ "<link href='css/login.css' rel='stylesheet' type='text/css' /></head><body><form action='treatmentUpdated' method='post'>"
				+ "	<center><table border='0.5'  cellpadding='3'><thead>"
				+ " <tr><th class='header'>Edycja zabiegu '" +(sessionRole.isReadTreatment() ? name  : none) + "'</th></tr></thead><tbody>"
				+ "<tr><td>Cena: <input placeholder='cena' type='text' name='price' value='" +(sessionRole.isReadTreatment() ? treatment.getPrice()  : none) + "' class='inputbox'/></td></tr>"
				+ "<tr><td>Czas trwania: <input placeholder='czas trwania' type='text' name='duration' value='" +(sessionRole.isReadTreatment() ? treatment.getDuration()  : none) + "' class='inputbox'/></td></tr>" );


		writer.print("<input type='hidden' name='name' value=" + name + " /></td></tr></tbody></table><p />"
				+ "<input type='submit' value='Zapisz' class='okbutton' /></center></form></body></html>");
	}
}
