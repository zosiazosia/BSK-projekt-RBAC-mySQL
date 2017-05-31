package com.jwt.hibernate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.dao.TreatmentDAO;

public class TreatmentUpdated extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Treatment treat = new Treatment();
		treat.setName(request.getParameter("name"));
		treat.setDuration(Integer.parseInt(request.getParameter("duration")));
		treat.setPrice(Integer.parseInt(request.getParameter("price")));
		
		TreatmentDAO tDAO = new TreatmentDAO();
		boolean result = tDAO.Update(treat);
		
		if (result) {
			session.setAttribute("registerResult", "Edycja poprawna");
		}
		else {
			session.setAttribute("registerResult", "Edycja niepoprawna");
		}
		
		response.sendRedirect("NoSuccess");
	}

}
