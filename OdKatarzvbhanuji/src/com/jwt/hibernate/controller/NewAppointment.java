package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Appointment;
import com.jwt.hibernate.bean.Client;
import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.dao.ClientDAO;
import com.jwt.hibernate.dao.HibernateUtil;
import com.jwt.hibernate.dao.TreatmentDAO;

public class NewAppointment extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		
		Float price = (Float.parseFloat(request.getParameter("price")));
		Integer treatsNumber = Integer.parseInt(request.getParameter("treatsNumber"));
		
		Appointment app = new Appointment();
		Set<Appointment> appSet = new HashSet<Appointment>();
		Set<Treatment> treatSet = new HashSet<Treatment>();
		
		String[] treatsTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
		for (int i=0; i<treatsNumber; i++){
			String stringTreat = request.getParameter(treatsTable[i]);
			if (stringTreat != null){
				Treatment treat = new Treatment();
				TreatmentDAO tDAO = new TreatmentDAO();
				treat = tDAO.get(stringTreat);
				treatSet.add(treat);
			}
		}
		
		app.setCost(price);
		app.setTreatments(treatSet);
				
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();	
			Transaction trns = session.beginTransaction(); 
			session.save(app);
			trns.commit();

			writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
				+ "<body> <center> </br> Wizyta dodana pomyslnie");
		}catch(Exception e){
			e.printStackTrace();
			writer.println("<html> <link href='css/login.css' rel='stylesheet' type='text/css' />" 
					+ "<body> <center> </br> Dodanie wizyty nie powiodlo sie");
		}

		writer.println( "<form method='get' action='welcome'> "
				+ "<input type='submit' value='Powrot' class='okbutton' />" 
				+ "</form> </center>" + "</body>" + "</html>");
		
	}
}
