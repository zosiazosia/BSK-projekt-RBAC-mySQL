package com.jwt.hibernate.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;

public class RoleDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RoleDAO(){}
	public boolean add(Role role){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction trns = session.beginTransaction(); 
			session.save(role);
			trns.commit();
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			System.out.println("error in RoleController --> insertRole");
			return false;
		}		
	}
	public Role getRole(String name){
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction trns = null;	
		List<Role> rolesList = new ArrayList<Role>();
		try{
			trns = s.beginTransaction();            
			String hql = "FROM Role WHERE type = '" + name + "'";
			rolesList = s.createQuery(hql).list();			
		}
		catch (Exception e){
	        e.printStackTrace();
	        System.out.println("error creating users list");
	    } finally {
	        s.flush();
	        s.close();
	    }
		return rolesList.get(0);
	}
	public List<Role> fetchAll(){
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
	        System.out.println("error creating roles list");
	    } finally {
	        s.flush();
	        s.close();
	    }
		return rolesList;
	}
	
	
	public void insertRoles(){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();			           		

			Transaction trns = session.beginTransaction();          
			
			Role admin = new Role("Admin");
			admin.setCreateAppointment(true);
			admin.setDeleteAppointment(true);
			admin.setUpdateAppointment(true);
			admin.setReadAppointment(true);
			admin.setCreateClient(true);
			admin.setDeleteClient(true);
			admin.setUpdateClient(true);
			admin.setReadClient(true);
			admin.setCreateTreatment(true);
			admin.setDeleteTreatment(true);
			admin.setUpdateTreatment(true);
			admin.setReadTreatment(true);
			admin.setCreateUser(true);
			admin.setDeleteUser(true);
			admin.setUpdateUser(true);
			admin.setReadUser(true);
			admin.setCreateRole(true);
			admin.setDeleteRole(true);
			admin.setUpdateRole(true);
			admin.setReadRole(true);
	/*		admin.setCreate(true);
			admin.setDelete(true);
			admin.setUpdate(true);
			admin.setRead(true); */
			
			Role role1 = new Role("Recepcjonista");
			role1.setCreateAppointment(true);
			role1.setDeleteAppointment(true);
			role1.setUpdateAppointment(true);
			role1.setReadAppointment(true);
			role1.setCreateClient(true);
			role1.setDeleteClient(true);
			role1.setUpdateClient(true);
			role1.setReadClient(true);
			role1.setCreateTreatment(true);
			role1.setDeleteTreatment(true);
			role1.setUpdateTreatment(true);
			role1.setReadTreatment(true);			
			
			Role role2 = new Role("Asystent");
			role2.setCreateClient(true);
			role2.setDeleteClient(true);
			role2.setUpdateClient(true);
			role2.setReadClient(true);
			role2.setCreateAppointment(true);
			role2.setDeleteAppointment(true);
			role2.setUpdateAppointment(true);
			role2.setReadAppointment(true);
			
			Role role3 = new Role("Dentysta");	
			role3.setCreateClient(true);
			role3.setDeleteClient(true);
			role3.setUpdateClient(true);
			role3.setReadClient(true);	

			session.save(admin);
			session.save(role1);
			session.save(role2);
			session.save(role3);
				
			trns.commit();
			System.out.println("\n\n Roles Added \n");

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n error w RoleController");
			
		}
	}
	
}
