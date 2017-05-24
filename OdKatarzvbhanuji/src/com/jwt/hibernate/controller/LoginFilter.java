package com.jwt.hibernate.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.RoleDAO;

@WebFilter("/addRole")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		User currentUser = new User();
		currentUser = (User)session.getAttribute("currentSessionUser");
		RoleDAO rDAO = new RoleDAO();
		Role currentRole = rDAO.getRole(currentUser.getActiveRoleString());
		if (session == null || !currentRole.isCreateRole()){
			response.sendRedirect(request.getContextPath());//+"/index.jsp");
		}
		else chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		
	}

	@Override
	public void destroy() {
		
	}

}
