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

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Client;
import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.ClientDAO;
import com.jwt.hibernate.dao.HibernateUtil;
import com.jwt.hibernate.dao.UserDAO;

public class UpdateClient extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Client client = new Client();
		ClientDAO cDAO = new ClientDAO();
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		client = cDAO.get(id);
		
		PrintWriter writer = response.getWriter();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction trns = null;	
		
		writer.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Edycja klienta</title> "
				+ "<link href='css/login.css' rel='stylesheet' type='text/css' /></head><body><form action='clientUpdated' method='post'>"
				+ "	<center><table border='0.5'  cellpadding='3'><thead>"
				+ " <tr><th class='header'>Edycja klienta</th></tr></thead><tbody>"
				+ "<tr><td>Imie: <input placeholder='imie' type='text' name='name' value=" + client.getName() + " class='inputbox' required/></td></tr>"
				+ "<tr><td>Nazwisko: <input placeholder='nazwisko' type='text' name='surname' value=" + client.getSurname() + " class='inputbox' required/></td></tr>"
				+ "<tr><td>PESEL: <input placeholder='pesel' type='text' name='pesel' value=" + client.getPesel() + " class='inputbox'/></td></tr>"
				+ "<tr><td>Rok urodzenia: <input placeholder='rok urodzenia' type='text' name='birthYear' value=" + client.getBirthYear() + " class='inputbox'/></td></tr>"
				+ "<tr><td>Telefon: <input placeholder='telefon' type='text' name='phone' value=" + client.getPhone() + " class='inputbox'/></td></tr>");
				
		writer.print("<input type='hidden' name='id' value=" + id + " /></td></tr></tbody></table><p />"
				+ "<input type='submit' value='Zapisz' class='okbutton' /></center></form></body></html>");
			
		
		
	}

}
