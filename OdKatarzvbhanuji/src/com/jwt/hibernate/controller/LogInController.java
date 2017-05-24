package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.HibernateUtil;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.UserDAO;

public class LogInController extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
				
		try{
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			
			String role = null;
			String res = request.getParameter("role");
			
			List<Role> rolesList = new ArrayList<Role>();
			RoleDAO rc = new RoleDAO();
			Role r = rc.getRole(res);
			role = r.getType();
			
			if (role != null) user = UserDAO.login(user, role);
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.err.println(ex);
			System.out.println("error in LoggedIn");
		}
		HttpSession session = request.getSession(true);
		if (user != null && user.isValid()){
			session.setAttribute("currentSessionUser", user);
			response.sendRedirect("welcome");
		}
		else {
			session.setAttribute("registerResult", "Niepoprawne logowanie");
			response.sendRedirect("NoSuccess");
		}
		return;
	}
	

}
