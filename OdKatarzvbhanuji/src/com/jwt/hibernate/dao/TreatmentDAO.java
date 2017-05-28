package com.jwt.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.bean.Treatment;
import com.jwt.hibernate.bean.User;

public class TreatmentDAO {
	
	
	
	public static List<Treatment> fetchAll(){
		
		List<Treatment> treatments = new ArrayList<Treatment>();
        Transaction trns = null;
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns = session.beginTransaction();            
			String hql = "FROM Treatment ";
			treatments = session.createQuery(hql).list();
			
		}
		catch (Exception e){
        e.printStackTrace();
        System.out.println("error creating treatments list");
	    } finally {
	        session.flush();
	        session.close();
	    }
		return treatments;
		
	}


	public static void delete(String name){
		
		Session session = HibernateUtil.getSessionFactory().openSession();			
		Transaction trn = session.beginTransaction();			
        Treatment treatment = (Treatment) session.get(Treatment.class,name);                   
        session.delete(treatment);
        trn.commit();
        
	}
}