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
			
			writer.println(	"<tr><td>Tabela roli: </td></tr>");
			
			writer.println(	"<tr>"
						+ 	"<td><input type='checkbox' name='createRole' " 	+ (role.isCreateRole() 	? "checked" : "") + "/>C</td></tr> ");
			writer.println(	"<tr><td><input type='checkbox' name='updateRole' " 	+ (role.isUpdateRole() 	? "checked" : "") + "/>R</td></tr> ");
			writer.println(	"<tr><td><input type='checkbox' name='readRole' " 		+ (role.isReadRole() 	? "checked" : "") + "/>U</td></tr> ");
			writer.println(	"<tr><td><input type='checkbox' name='deleteRole' " 	+ (role.isDeleteRole() 	? "checked" : "") + "/>D</td> "
						+ 	"</tr>");

			writer.println("<tr><td>Tabela uzytkownikow: <td></tr>");
			writer.println("<tr><td><input type='checkbox' name='createUser' " + (role.isCreateUser() ? "checked" : "") + "/>C</td></tr>");
			writer.println("<tr><td><input type='checkbox' name='updateUser' " + (role.isUpdateUser() ? "checked" : "") + "/>R</td></tr>");
			writer.println("<tr><td><input type='checkbox' name='readUser' " + (role.isReadUser() ? "checked" : "") + "/>U</td></tr>");
			writer.println("<tr><td><input type='checkbox' name='deleteUser' " + (role.isDeleteUser() ? "checked" : "") + "/>D</td></tr>");

			writer.println("<tr><td>Tabela klientow: <td></tr>");
			writer.println("<tr><td><input type='checkbox' name='createClient' " + (role.isCreateClient() ? "checked" : "") + "/>C</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='updateClient' " + (role.isUpdateClient() ? "checked" : "") + "/>R</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='readClient' " + (role.isReadClient() ? "checked" : "") + "/>U</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='deleteClient' " + (role.isDeleteClient() ? "checked" : "") + "/>D</br></td></tr>");

			writer.println("<tr><td>Tabela wizyt: <td></tr>");
			writer.println("<tr><td><input type='checkbox' name='createAppointment' " + (role.isCreateAppointment() ? "checked" : "") + "/>C</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='updateAppointment' " + (role.isUpdateAppointment() ? "checked" : "") + "/>R</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='readAppointment' " + (role.isReadAppointment() ? "checked" : "") + "/>U</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='deleteAppointment' " + (role.isDeleteAppointment() ? "checked" : "") + "/>D</br></td></tr>");

			writer.println("<tr><td>Tabela zabiegow: <td></tr>");
			writer.println("<tr><td><input type='checkbox' name='createTreatment' " + (role.isCreateTreatment() ? "checked" : "") + "/>C</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='updateTreatment' " + (role.isUpdateTreatment() ? "checked" : "") + "/>R</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='readTreatment' " + (role.isReadTreatment() ? "checked" : "") + "/>U</br></td></tr>");
			writer.println("<tr><td><input type='checkbox' name='deleteTreatment' " + (role.isDeleteTreatment() ? "checked" : "") + "/>D</br></td></tr>");

			
			
			writer.print("<input type='hidden' name='id' value=" + id + " /></td></tr></tbody></table><p />"
					+ "<input type='submit' value='Zapisz' class='okbutton' />"
					+ "<table><tr><td>C = Create (Tworzenie), R = Read (Czytanie), </td></tr> <tr><td>U = Update (Aktualizowanie), D = Delete (Usuwanie)</td></tr>"
					+ "</table></center></form></body></html>");

			
		
		
		
		
	}

}
