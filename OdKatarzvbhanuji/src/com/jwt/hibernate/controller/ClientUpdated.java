package com.jwt.hibernate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Client;
import com.jwt.hibernate.dao.ClientDAO;

public class ClientUpdated extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String pesel = request.getParameter("pesel");
		String phone = request.getParameter("phone");
		Integer birthYear = Integer.parseInt(request.getParameter("birthYear"));
		
		Client client = new Client(name, surname, pesel);
		client.setId(id);
		client.setBirthYear(birthYear);
		client.setPhone(phone);
		
		ClientDAO cDAO = new ClientDAO();
		boolean result = cDAO.update(client);
				
		if (result) {
			session.setAttribute("registerResult", "Edycja poprawna");
			
		}
		else {
			session.setAttribute("registerResult", "Edycja niepoprawna");
		}
		
		response.sendRedirect("NoSuccess");
		

	}


}
