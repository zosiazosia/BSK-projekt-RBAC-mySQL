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

import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.UserDAO;

public class UsersTableServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = -4396513884510909136L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String typ = request.getParameter("typ");
			
			if(typ.equalsIgnoreCase("delete")){
				long id = Long.valueOf(request.getParameter("id")).longValue();
				
				
				
				UserDAO u = new UserDAO();
				u.delete(id);
				List<User> users = u.fetchAll();
				
				request.setAttribute("usersList", users);
				request.getRequestDispatcher("tabelki").forward(request, response);
				
			}
			else if(typ.equalsIgnoreCase("read")){
				
				UserDAO u = new UserDAO();
				List<User> users = u.fetchAll();
				
				for(User user : users){
					System.out.println(user.getName());
				}
				
				request.setAttribute("usersList", users);
				request.getRequestDispatcher("tabelki").forward(request, response);
				
				
			}else if(typ.equalsIgnoreCase("update")){
				
				
				
			}else{ //if(typ.equalsIgnoreCase("add")){
				//create
				
				request.getRequestDispatcher("regi").forward(request, response);
				
				
			}
			
			
	}
}
