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
