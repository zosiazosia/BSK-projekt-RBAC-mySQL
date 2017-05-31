package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.RoleDAO;

public class SelectRole extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
			
			List<Role> roles = (List<Role>)request.getAttribute("rolesList");

			User userr = new User();
			userr = (User) request.getAttribute("currentSessionUser");
			RoleDAO rDAO = new RoleDAO();
			Role currentUserRole = rDAO.getRole(userr.getActiveRoleString());
			String read = (String) request.getAttribute("typ");
			
			
			
			PrintWriter writer;
			try {
				writer = response.getWriter();
				
				writer.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Wybierz role</title> "
						+ "<link href='css/login.css' rel='stylesheet' type='text/css' /></head><body><form action='tableRoles' method='post'>"
						+ "	<center><table border='0.5'  cellpadding='3'><thead>"
						+ " <tr><th class='header'>Wybierz role do wyswietlenia</th></tr></thead><tbody>");
				
				String[] rolesTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
				int i = 0;
				for (Role role : roles){
					
					String type = role.getType();
					
					writer.print("<tr><th><input type='checkbox' name=" + rolesTable[i++] + " value=" + type + " />" + type + "</th></tr>");
					
				}
				Integer delete = (Integer) request.getAttribute("delete");
				Integer create = (Integer) request.getAttribute("create");
				Integer update = (Integer) request.getAttribute("update");
				writer.print("<tr><td><input type='hidden' name='delete' value='"+delete+"' /></td></tr>");
				writer.print("<tr><td><input type='hidden' name='create' value="+create+" /></td></tr>");
				writer.print("<tr><td><input type='hidden' name='update' value='"+update+"' /></td></tr>");
				
				writer.print("<tr><td><input type='hidden' name='rolesNumber' value=" + i + " /></td></tr>");
				writer.print("<tr><td><input type='hidden' name='currentSessionUser' value=" + userr + " /></td></tr>");
				writer.print("<tr><td><input type='hidden' name='typ' value='" + read + "' /></td></tr>");
				
				writer.print("</tbody></table>"
						+ "<input type='submit' value='Pokaz' class='okbutton' /></center></form></body></html>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
			
}
