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
		
		HttpSession session = request.getSession(true);
		try {
			UserDAO userDAO = new UserDAO();
			boolean result = userDAO.update(id, login, password, name, surname, pesel);
			
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
