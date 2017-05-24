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
		Hibernate.initialize(user.getRoles());
		List<Role> userRoleList = new ArrayList<Role>(0);
		RoleDAO rDAO = new RoleDAO();
		userRoleList = rDAO.fetchAll(id);
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
		try {
			writer.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Edycja uzytkownika</title> "
					+ "<link href='css/login.css' rel='stylesheet' type='text/css' /></head><body><form action='userUpdated' method='post'>"
					+ "	<center><table border='0.5'  cellpadding='3'><thead>"
					+ " <tr><th class='header'>Edycja uzytkownika</th></tr></thead><tbody>"
					+ "<tr><td><input placeholder='login' type='text' name='login' value=" + user.getLogin() + " class='inputbox' required/></td></tr>"
					+ "<tr><td><input placeholder='haslo' type='password' name='password' value=" + encryptor.decrypt(user.getPassword()) + " class='inputbox' required/></td></tr>"
					+ "<tr><td><input placeholder='imie' type='text' name='name' value=" + user.getName() + " class='inputbox'/></td></tr>"
					+ "<tr><td><input placeholder='nazwisko' type='text' name='surname' value=" + user.getSurname() + " class='inputbox'/></td></tr>"
					+ "<tr><td><input placeholder='PESEL' type='text' name='pesel' value=" + user.getPesel() + " class='inputbox'/></td></tr>");
					
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	String[] rolesTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
		int i = 0;
		for (Role role : rolesList){
			String type = role.getType();
			if (userRoleList.contains(role)) {
				writer.print("<tr><td><input type='checkbox' name=" + rolesTable[i++] + " value=" 
						+ type + " checked />" + type + "</br>");
			}
			else {
				writer.print("<input type='checkbox' name=" + rolesTable[i++] + " value=" 
						+ type + " />" + type + "</br>");				
			}
		}
		writer.print("<input type='hidden' name='rolesNumber' value=" + i + " />"); */
		writer.print("<input type='hidden' name='id' value=" + id + " /></td></tr></tbody></table><p />"
				+ "<input type='submit' value='Zapisz' class='okbutton' /></center></form></body></html>");
		
		
		
		
	}

}
