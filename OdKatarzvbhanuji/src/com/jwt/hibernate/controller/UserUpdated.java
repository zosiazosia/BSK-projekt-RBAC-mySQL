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
import com.jwt.hibernate.dao.UserDAO;

public class UserUpdated  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.valueOf(request.getParameter("id")).longValue();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String pesel = request.getParameter("pesel");
		String roleNum = request.getParameter("rolesNumber");
		int req = Integer.parseInt(roleNum);

		Set<Role> roles = new HashSet<Role>(0);
		
		String[] rolesTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
		for (int i=0; i<req; i++){
			String stringRole = request.getParameter(rolesTable[i]);
			if (stringRole != null){
				Role role = new Role(stringRole);
				roles.add(role);
			}
		}
		
		HttpSession session = request.getSession(true);
		try {
			UserDAO userDAO = new UserDAO();
			User user = new User(login, password, name, surname, pesel, roles);
			boolean result = userDAO.update(user, id);
			
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
