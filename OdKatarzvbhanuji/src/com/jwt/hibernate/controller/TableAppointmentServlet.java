package com.jwt.hibernate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Appointment;
import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.AppointmentDAO;
import com.jwt.hibernate.dao.RoleDAO;


public class TableAppointmentServlet extends HttpServlet {
	
	

	private static final long serialVersionUID = -6140291582784996971L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			HttpSession session = request.getSession(false);
			
			User userr = new User();
			userr = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = userr.getActiveRoleString();
			RoleDAO rc = new RoleDAO();
			Role sessionRole = rc.getRole(sessionRoleString);
			
			
			if (sessionRole.isCreateAppointment()){
				request.setAttribute("create", 1);
			}else{
				request.setAttribute("create", null);
			}
			
			
			if (sessionRole.isDeleteAppointment()){
				request.setAttribute("delete", 1);
			}
			else{
				request.setAttribute("delete", null);
			}
			
			
			if (sessionRole.isReadAppointment()){
				request.setAttribute("read", 1);
			}
			else{
				request.setAttribute("read", null);
			}
			
			
			if (sessionRole.isUpdateAppointment()){
				request.setAttribute("update", 1);
			}
			else{
				request.setAttribute("update", null);
			}
			
			
			
			if(typ.equalsIgnoreCase("delete")){
				
				
				long id = Long.valueOf(request.getParameter("id")).longValue();
				
				AppointmentDAO a = new AppointmentDAO();
				a.delete(id);
				List<Appointment> appointments = a.fetchAll();
				
				request.setAttribute("appointmentsList", appointments);
				request.getRequestDispatcher("tableAppointments").forward(request, response);
				
			}
			else if(typ.equalsIgnoreCase("read")){
				
				
				AppointmentDAO a = new AppointmentDAO();
				List<Appointment> appointments = a.fetchAll();
				
				for(Appointment appointment : appointments){
					System.out.println(appointment.getId());
				}
				
				request.setAttribute("appointmentsList", appointments);
				request.getRequestDispatcher("tableAppointments").forward(request, response);
				
				
			}else if(typ.equalsIgnoreCase("update")){
				
				
				
			}else{ //if(typ.equalsIgnoreCase("add")){
				//create
				
				request.getRequestDispatcher("welcome").forward(request, response);
				
				
			}
			
			
	}
}
