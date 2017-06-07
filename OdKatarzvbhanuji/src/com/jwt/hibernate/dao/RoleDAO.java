package com.jwt.hibernate.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.controller.Encryptor;

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
	        return null;
	    } finally {
	        s.flush();
	        s.close();
	    }
		if (rolesList.isEmpty()) return null;
		return rolesList.get(0);
	}
	public Role getRole(Long id){
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction trns = null;	
		List<Role> rolesList = new ArrayList<Role>();
		try{
			trns = s.beginTransaction();            
			String hql = "FROM Role WHERE id = '" + id + "'";
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
	
	public boolean Update(Role role){
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Transaction trans = session.beginTransaction();
		try{
			session.update(role);
			trans.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
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
	
	public List<Role> fetchAll(Long userId){
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction trns = null;	
		List<Role> rolesList = new ArrayList<Role>();
		try{
			trns = s.beginTransaction();            
			String hql = "FROM role r WHERE ur.user_id='" + userId + "' AND r.id=ur.role_id";
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
	public static void delete (long id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();	
		
		Transaction trn = session.beginTransaction();
		Long a = new Long(id);
		
        Role role = (Role) session.get(Role.class,a);
        String roles = "DELETE FROM user_role where role_id=" +a.toString();
        session.createSQLQuery(roles).executeUpdate();
                    
        session.delete(role);
        trn.commit();
       
        
		
	}
	
	public void insertRoles(){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();			           		

			Transaction trns = session.beginTransaction();          
			
			Role admin = new Role("Admin");
			admin.setRoles("CRUD");
			admin.setAppointments("CRUD");
			admin.setClients("CRUD");
			admin.setTreatments("CRUD");
			admin.setUsers("CRUD");
			
			Role role1 = new Role("Recepcjonista");
			role1.setAppointments("CRDU");
			role1.setClients("CRUD");
			role1.setTreatments("CRUD");
			
			Role role2 = new Role("Asystent");
			role2.setAppointments("CRUD");
			role2.setClients("CRUD");
			
			Role role3 = new Role("Dentysta");	
			role3.setClients("CRUD");	

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
