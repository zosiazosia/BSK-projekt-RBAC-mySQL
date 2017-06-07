package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.dao.HibernateUtil;

public class NewRole extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();	
		Transaction trns = session.beginTransaction();   
		PrintWriter writer = response.getWriter();
		Role role = new Role();
		
		role.setType(request.getParameter("type"));

		String roles = request.getParameter("roles");
		String users = request.getParameter("users");
		String clients = request.getParameter("clients");
		String appointments = request.getParameter("appointments");
		String treatments = request.getParameter("treatments");
		
		role.setAppointments(appointments);
		role.setClients(clients);
		role.setRoles(roles);
		role.setTreatments(treatments);
		role.setUsers(users);
		
		session.save(role);
		trns.commit();
		
		writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
			+ "<body> <center> </br> Rola dodana pomyslnie");

		writer.println( "<form method='get' action='welcome'> "
				+ "<input type='submit' value='Powrot' class='okbutton' />" 
				+ "</form> </center>" + "</body>" + "</html>");
		
	
	}
}
