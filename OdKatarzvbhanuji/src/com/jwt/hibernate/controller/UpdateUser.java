package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.HibernateUtil;
import com.jwt.hibernate.dao.RoleDAO;
import com.jwt.hibernate.dao.UserDAO;

public class UpdateUser extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		UserDAO uDAO = new UserDAO();
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		user = uDAO.get(id);
		Set<Role> userRoleList = new HashSet<Role>(0);
		userRoleList = (Set<Role>)uDAO.getRoles(user);
		PrintWriter writer = response.getWriter();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction trns = null;	
		List<Role> rolesList = new ArrayList<Role>();
		try{
			trns = s.beginTransaction();            
			String hql = "FROM Role";
			rolesList = s.createQuery(hql).list();			
		}
		catch (Exception e){
	        e.printStackTrace();
	        System.out.println("error creating users list");
	    }
		Encryptor encryptor = new Encryptor();
		
		HttpSession session = request.getSession(false);
		User userr = new User();
		userr = (User) session.getAttribute("currentSessionUser");
		String sessionRoleString = userr.getActiveRoleString();
		RoleDAO rc = new RoleDAO();
		Role sessionRole = rc.getRole(sessionRoleString);
		String none = "";
		
		try {
			writer.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Edycja uzytkownika</title> "
					+ "<link href='css/login.css' rel='stylesheet' type='text/css' /></head><body><form action='userUpdated' method='post'>"
					+ "	<center><table border='0.5'  cellpadding='3'><thead>"
					+ " <tr><th class='header'>Edycja uzytkownika</th></tr></thead><tbody>"
					+ "<tr><td><input placeholder='login' type='text' name='login' value='" + (sessionRole.isReadUser() ? user.getLogin() : none) + "' class='inputbox' required/></td></tr>"
					+ "<tr><td><input placeholder='haslo' type='password' name='password' value='" + (sessionRole.isReadUser() ? encryptor.decrypt(user.getPassword()) : none) + "' class='inputbox' required/></td></tr>"
					+ "<tr><td><input placeholder='imie' type='text' name='name' value='" + (sessionRole.isReadUser() ? user.getName() : none) + "' class='inputbox' /></td></tr>"
					+ "<tr><td><input placeholder='nazwisko' type='text' name='surname' value='" + (sessionRole.isReadUser() ? user.getSurname() : none) + "' class='inputbox' /></td></tr>"
					+ "<tr><td><input placeholder='PESEL' type='text' name='pesel' value='" + (sessionRole.isReadUser() ? user.getPesel() : none) + "' class='inputbox' /></td></tr>");
				
			String[] rolesTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
			boolean[] boolTable = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
			
			int i = 0;
			for (Role role : rolesList){
				String type = role.getType();
				boolean printed =false;
				for (Role r : userRoleList){
					
					if (r.getType().equals(role.getType()) && sessionRole.isReadUser()){
						writer.print("<tr><td><input type='checkbox' name=" + rolesTable[i++] + " value=" 
								+ type + " checked />" + type + "</td></tr></br>");
						printed = true;
						break;
					}
				}
				if (!printed){
					writer.print("<tr><td><input type='checkbox' name=" + rolesTable[i++] + " value=" 
							+ type + " />" + type + "</td></tr></br>");
				}
			}
			writer.print("<input type='hidden' name='rolesNumber' value=" + i + " />"); 
			
			
			writer.print("<input type='hidden' name='id' value=" + id + " /></td></tr></tbody></table><p />"
					+ "<input type='submit' value='Zapisz' class='okbutton' /></center></form></body></html>");
			
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
