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

import com.jwt.hibernate.bean.Client;
import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.ClientDAO;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.TreatmentDAO;
import com.jwt.hibernate.dao.UserDAO;

public class TableTreatmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4540831107622084648L;
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			HttpSession session = request.getSession(false);
			
			User userr = new User();
			userr = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = userr.getActiveRoleString();
			RoleDAO rc = new RoleDAO();
			Role sessionRole = rc.getRole(sessionRoleString);
			
			
			if (sessionRole.isCreateTreatment()){
				request.setAttribute("create", 1);
			}else{
				request.setAttribute("create", null);
			}
			
			
			if (sessionRole.isDeleteTreatment()){
				request.setAttribute("delete", 1);
			}
			else{
				request.setAttribute("delete", null);
			}
			
			
			if (sessionRole.isReadTreatment()){
				request.setAttribute("read", 1);
			}
			else{
				request.setAttribute("read", null);
			}
			
			
			if (sessionRole.isUpdateTreatment()){
				request.setAttribute("update", 1);
			}
			else{
				request.setAttribute("update", null);
			}
			
			
			
			if(typ.equalsIgnoreCase("delete")){
				
				
				long id = Long.valueOf(request.getParameter("id")).longValue();
				
				TreatmentDAO c = new TreatmentDAO();
				//c.delete(id);
				List<Treatment> treatments = c.fetchAll();
				
				request.setAttribute("treatmentsList", treatments);
				request.getRequestDispatcher("tableTreatments").forward(request, response);
				
			}
			else if(typ.equalsIgnoreCase("read")){
				
				
				TreatmentDAO u = new TreatmentDAO();
				List<Treatment> treatments = u.fetchAll();
				
				for(Treatment treatment : treatments){
					System.out.println(treatment.getName());
				}
				
				request.setAttribute("treatmentsList", treatments);
				request.getRequestDispatcher("tableTreatments").forward(request, response);
				
				
			}else if(typ.equalsIgnoreCase("update")){
				
				request.getRequestDispatcher("welcome").forward(request, response);
				
				
			}else{ 
				
				request.getRequestDispatcher("welcome").forward(request, response);
				
				
			}
			
			
	}
}
