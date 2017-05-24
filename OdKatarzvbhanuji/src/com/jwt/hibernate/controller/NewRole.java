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
		
		role.setCreateRole(request.getParameter("createRole") != null);
		role.setReadRole(request.getParameter("readRole") != null);
		role.setUpdateRole(request.getParameter("updateRole") != null);
		role.setDeleteRole(request.getParameter("deleteRole") != null);

		role.setCreateUser(request.getParameter("createUser") != null);
		role.setReadUser(request.getParameter("readUser") != null);
		role.setUpdateUser(request.getParameter("updateUser") != null);
		role.setDeleteUser(request.getParameter("deleteUser") != null);

		role.setCreateClient(request.getParameter("createClient") != null);
		role.setReadClient(request.getParameter("readClient") != null);
		role.setUpdateClient(request.getParameter("updateClient") != null);
		role.setDeleteClient(request.getParameter("deleteClient") != null);

		role.setCreateAppointment(request.getParameter("createAppointment") != null);
		role.setReadAppointment(request.getParameter("readAppointment") != null);
		role.setUpdateAppointment(request.getParameter("updateAppointment") != null);
		role.setDeleteAppointment(request.getParameter("deleteAppointment") != null);

		role.setCreateTreatment(request.getParameter("createTreatment") != null);
		role.setReadTreatment(request.getParameter("readTreatment") != null);
		role.setUpdateTreatment(request.getParameter("updateTreatment") != null);
		role.setDeleteTreatment(request.getParameter("deleteTreatment") != null);
		
		session.save(role);
		trns.commit();
		
		writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
			+ "<body> <center> </br> Rola dodana pomyslnie");

		writer.println( "<form method='get' action='welcome'> "
				+ "<input type='submit' value='Powrot' class='okbutton' />" 
				+ "</form> </center>" + "</body>" + "</html>");
		
	
	}
}
