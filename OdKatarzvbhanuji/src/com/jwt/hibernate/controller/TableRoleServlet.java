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

public class TableRoleServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = -648521294120385061L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			HttpSession session = request.getSession(false);
			
			User userr = new User();
			userr = (User) session.getAttribute("currentSessionUser");
			String sessionRoleString = userr.getActiveRoleString();
			RoleDAO rc = new RoleDAO();
			Role sessionRole = rc.getRole(sessionRoleString);
			
			
			if (sessionRole.isCreateRole()){
				request.setAttribute("create", 1);
			}else{
				request.setAttribute("create", null);
			}
			
			
			if (sessionRole.isDeleteRole()){
				request.setAttribute("delete", 1);
			}
			else{
				request.setAttribute("delete", null);
			}
			
			
			if (sessionRole.isReadRole()){
				request.setAttribute("read", 1);
			}
			else{
				request.setAttribute("read", null);
			}
			
			
			if (sessionRole.isUpdateRole()){
				request.setAttribute("update", 1);
			}
			else{
				request.setAttribute("update", null);
			}
			
			
			
			if(typ.equalsIgnoreCase("delete")){
				
				
				long id = Long.valueOf(request.getParameter("id")).longValue();
				
				RoleDAO c = new RoleDAO();
				//c.delete(id);
				List<Role> roles = c.fetchAll();
				
				request.setAttribute("rolesList", roles);
				request.getRequestDispatcher("selectRole").forward(request, response);
				
			}
			else if(typ.equalsIgnoreCase("read")){
				
				
				RoleDAO u = new RoleDAO();
				List<Role> roles = u.fetchAll();
				
				for(Role role : roles){
					System.out.println(role.getId());
				}
				
				request.setAttribute("rolesList", roles);
				request.getRequestDispatcher("selectRole").forward(request, response);
				
				
			}else if(typ.equalsIgnoreCase("update")){
				
				
				
			}else{ 
				
				request.getRequestDispatcher("addRole").forward(request, response);
				
				
			}
			
			
	}
}
