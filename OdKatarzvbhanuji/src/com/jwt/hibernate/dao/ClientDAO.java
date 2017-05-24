package com.jwt.hibernate.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Appointment;
import com.jwt.hibernate.bean.Client;
import com.jwt.hibernate.bean.Treatment;

public class ClientDAO extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void addClients(){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();			           		
			Transaction trns = session.beginTransaction();          
			Client client = new Client("Zofia", "Klientka");
			client.setBirthYear(45);
			client.setPesel("95485147521");
			
			Set<Appointment> appSet = new HashSet<Appointment>(0);
			Set<Treatment> treatSet = new HashSet<Treatment>(0);
			treatSet.add(new Treatment("mycie", 63, 4));
			treatSet.add(new Treatment("proteza", 245, 6));
			Set<Treatment> treatSet2 = new HashSet<Treatment>(0);
			treatSet2.add(new Treatment("usuwanie", 623, 3));
			treatSet2.add(new Treatment("kamien", 25, 6));
			appSet.add(new Appointment((float)97, treatSet));
			appSet.add(new Appointment((float)378, treatSet2));
			
			client.setAppointments(appSet);
			session.save(client);
			trns.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n error w RoleController");			
		}
	}
	public boolean add(Client client){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();			           		
			Transaction trns = session.beginTransaction();  
						
			session.save(client);			
			trns.commit();
			
			return true;
			
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n error w RoleController");
			return false;
			
		}
	}
	public List<Client> fetchAll(){
		List<Client> clients = new ArrayList<Client>();
        Transaction trns = null;
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns = session.beginTransaction();            
			String hql = "FROM client ";
			clients = session.createQuery(hql).list();
			
		}
		catch (Exception e){
        e.printStackTrace();
        System.out.println("error creating clients list");
	    } finally {
	        session.flush();
	        session.close();
	    }
		return clients;
	}
}
