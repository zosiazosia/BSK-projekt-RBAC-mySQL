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
import com.jwt.hibernate.bean.User;

public class AppointmentDAO extends HttpServlet{
	public void addAppointments(){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();			           		
			Transaction trns = session.beginTransaction();
			Appointment app = new Appointment();
			
			Client client = new Client("Zofia", "Klientka");
						
			session.save(client);			
			trns.commit();
		} catch (HibernateException e) {
			e.printStackTrace();		
		}
	}
	public boolean add(Appointment appointment){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();			           		
			Transaction trns = session.beginTransaction();
									
			session.save(appointment);			
			trns.commit();
			return true;
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Appointment> fetchAll(){
		
		List<Appointment> appointments = new ArrayList<Appointment>();
        Transaction trns = null;
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns = session.beginTransaction();            
			String hql = "FROM Appointment ";
			appointments = session.createQuery(hql).list();
			
		}
		catch (Exception e){
        e.printStackTrace();
        System.out.println("error creating appointment list");
	    } finally {
	        session.flush();
	        session.close();
	    }
		return appointments;
	}
}
