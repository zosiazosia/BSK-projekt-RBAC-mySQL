package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.HibernateUtil;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.UserDAO;

public class UpdateRole extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Role role = new Role();
		RoleDAO rDAO = new RoleDAO();		
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		role = rDAO.getRole(id);
		PrintWriter writer = response.getWriter();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction trns = null;
			writer.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Edycja uzytkownika</title> "
					+ "<link href='css/login.css' rel='stylesheet' type='text/css' /></head><body><form action='roleUpdated' method='post'>"
					+ "	<center><table><thead>"
					+ " <tr><th class='header'>Edycja roli</th></tr></thead><tbody>"
					+ "<tr><td><input type='text' name='type' value=" + role.getType() + " class='inputbox' required/></td></tr>");
			
			writer.println("<tr><td>Tabela roli: </td></tr>");			
			writer.println("<tr><td><input type='text' name='roles' value='" + (role.getRoles()) + "'/></td></tr> ");
			
			writer.println("<tr><td>Tabela uzytkownikow: <td></tr>");
			writer.println("<tr><td><input type='text' name='users' value='" + (role.getRoles()) + "'/></td></tr>");
			
			writer.println("<tr><td>Tabela klientow: <td></tr>");
			writer.println("<tr><td><input type='text' name='clients' value='" + (role.getClients()) + "'/></td></tr>");
			
			writer.println("<tr><td>Tabela wizyt: <td></tr>");
			writer.println("<tr><td><input type='text' name='appointments' value='" + (role.getAppointments()) + "'/></td></tr>");
			
			writer.println("<tr><td>Tabela zabiegow: <td></tr>");
			writer.println("<tr><td><input type='text' name='treatments' value='" + (role.getTreatments()) + "'/></td></tr>");
						
			
			writer.print("<input type='hidden' name='id' value=" + id + " /></td></tr></tbody></table><p />"
					+ "<input type='submit' value='Zapisz' class='okbutton' />"
					+ "<table><tr><td>C = Create (Tworzenie), R = Read (Czytanie), </td></tr> <tr><td>U = Update (Aktualizowanie), D = Delete (Usuwanie)</td></tr>"
					+ "</table></center></form></body></html>");

			
		
		
		
		
	}

}
