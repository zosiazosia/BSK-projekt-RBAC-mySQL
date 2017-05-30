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
import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.dao.HibernateUtil;

public class NewTreatment extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();	
		Transaction trns = session.beginTransaction();   
		PrintWriter writer = response.getWriter();
		Treatment treat = new Treatment();
		
		treat.setName(request.getParameter("name"));
		treat.setDuration(Integer.parseInt(request.getParameter("duration")));
		treat.setPrice(Integer.parseInt(request.getParameter("price")));
		
		session.save(treat);
		trns.commit();

		writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
			+ "<body> <center> </br> Zabieg dodany pomyslnie");

		writer.println( "<form method='get' action='welcome'> "
				+ "<input type='submit' value='Powrot' class='okbutton' />" 
				+ "</form> </center>" + "</body>" + "</html>");
		
	}
}
