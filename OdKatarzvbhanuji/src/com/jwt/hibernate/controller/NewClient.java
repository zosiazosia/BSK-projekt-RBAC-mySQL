package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Client;
import com.jwt.hibernate.dao.ClientDAO;
import com.jwt.hibernate.dao.HibernateUtil;

public class NewClient extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		Client client = new Client();
		ClientDAO cDAO = new ClientDAO();
		
		client.setName(request.getParameter("name"));
		client.setSurname(request.getParameter("surname"));
		client.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
		client.setPesel(request.getParameter("pesel"));
		client.setPhone(request.getParameter("phone"));
		client.setAppointments(null);
		
		boolean result = cDAO.add(client);

		if (result) {
			writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
			+ "<body> <center> </br> Klient dodany pomyslnie");
		}
		else{
			writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
					+ "<body> <center> </br> Dodanie klienta nie powiodlo sie");
		}

		writer.println( "<form method='get' action='welcome'> "
				+ "<input type='submit' value='Powrot' class='okbutton' />" 
				+ "</form> </center>" + "</body>" + "</html>");
		
	}
}
