package com.jwt.hibernate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.UserDAO;

public class UsersTableServlet extends HttpServlet {
	
	
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
			String search2 = (String) session.getAttribute("search");
			String search = request.getParameter("search");
			
			
			
			if (sessionRole.isCreateUser()){
				request.setAttribute("create", 1);
				request.setAttribute("typ", "create");
			}else{
				request.setAttribute("create", null);
			}
			
			
			if (sessionRole.isDeleteUser()){
				request.setAttribute("delete", 1);
				request.setAttribute("typ", "delete");
			}
			else{
				request.setAttribute("delete", null);
			}
			
			
			if (sessionRole.isReadUser()){
				request.setAttribute("read", 1);
				request.setAttribute("typ", "read");
			}
			else{
				request.setAttribute("read", null);
			}
			
			
			if (sessionRole.isUpdateUser()){
				request.setAttribute("update", 1);
				request.setAttribute("typ", "update");
			}
			else{
				request.setAttribute("update", null);
			}
			
			
			if(typ.equalsIgnoreCase("update")){
				request.getRequestDispatcher("updateUser").forward(request, response);				

			}else if(typ.equalsIgnoreCase("delete")){
				long id = Long.valueOf(request.getParameter("id")).longValue();
				
				
				
				UserDAO u = new UserDAO();
				u.delete(id);
				List<User> users = u.fetchAll();
				
			/*	if (search != null && search != ""){
					for (User user : users){
						if(!user.getName().contains(search)){
							users.remove(user);
						}
					}
				}*/
				
				request.setAttribute("usersList", users);
				request.getRequestDispatcher("tabelki").forward(request, response);
				
			}
			else if(typ.equalsIgnoreCase("read")){
				
				
				UserDAO u = new UserDAO();
				List<User> users = u.fetchAll();
				
				for(User user : users){
					System.out.println(user.getName());
				}
				
				if (search != null && search != ""){
					Iterator<User> iterator = users.iterator();
					while(iterator.hasNext()){
						User user = iterator.next();
						if(!user.getName().contains(search)){
							iterator.remove();
						}
					}
				}
				System.out.println(search);
				for(User user : users){
					System.out.println(user.getName());
				}
				request.setAttribute("usersList", users);
				request.getRequestDispatcher("tabelki").forward(request, response);
				
				
			}else{ 
				
				request.getRequestDispatcher("welcome").forward(request, response);
				
				
			}
			
			
	}
}
