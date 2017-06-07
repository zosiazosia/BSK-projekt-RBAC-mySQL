package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.UserDAO;

public class RoleUpdated  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Role role = new Role();
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		role.setId(id);
		role.setType(request.getParameter("type"));
		
		role.setAppointments(request.getParameter("appointments"));
		role.setClients(request.getParameter("clients"));
		role.setRoles(request.getParameter("roles"));
		role.setTreatments(request.getParameter("treatments"));
		role.setUsers(request.getParameter("users"));
	
				
		HttpSession session = request.getSession(true);
		try {
			RoleDAO roleDAO = new RoleDAO();
			boolean result = roleDAO.Update(role);
			
			if (result) {
				session.setAttribute("registerResult", "Edycja poprawna");
			}
			else {
				session.setAttribute("registerResult", "Edycja niepoprawna");
			}
			
			response.sendRedirect("NoSuccess");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
