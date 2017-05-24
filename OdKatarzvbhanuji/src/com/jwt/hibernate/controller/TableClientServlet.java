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
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.ClientDAO;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.UserDAO;

public class TableClientServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = -4396513884510909136L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			HttpSession session = request.getSession(false);
			
			User userr = new User();
			userr = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = userr.getActiveRoleString();
			RoleDAO rc = new RoleDAO();
			Role sessionRole = rc.getRole(sessionRoleString);
			
			
			if (sessionRole.isCreateClient()){
				request.setAttribute("create", 1);
			}else{
				request.setAttribute("create", null);
			}
			
			
			if (sessionRole.isDeleteClient()){
				request.setAttribute("delete", 1);
			}
			else{
				request.setAttribute("delete", null);
			}
			
			
			if (sessionRole.isReadClient()){
				request.setAttribute("read", 1);
			}
			else{
				request.setAttribute("read", null);
			}
			
			
			if (sessionRole.isUpdateClient()){
				request.setAttribute("update", 1);
			}
			else{
				request.setAttribute("update", null);
			}
			
			
			
			if(typ.equalsIgnoreCase("delete")){
				
				
				long id = Long.valueOf(request.getParameter("id")).longValue();
				
				ClientDAO c = new ClientDAO();
				//c.delete(id);
				List<Client> clients = c.fetchAll();
				
				request.setAttribute("clientsList", clients);
				request.getRequestDispatcher("tableClients").forward(request, response);
				
			}
			else if(typ.equalsIgnoreCase("read")){
				
				
				ClientDAO u = new ClientDAO();
				List<Client> clients = u.fetchAll();
				
				for(Client client : clients){
					System.out.println(client.getName());
				}
				
				request.setAttribute("clientsList", clients);
				request.getRequestDispatcher("tableClients").forward(request, response);
				
				
			}else if(typ.equalsIgnoreCase("update")){
				
				
				
			}else{ //if(typ.equalsIgnoreCase("add")){
				//create
				
				request.getRequestDispatcher("welcome").forward(request, response);
				
				
			}
			
			
	}
}
