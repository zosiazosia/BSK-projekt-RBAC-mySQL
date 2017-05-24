package com.jwt.hibernate.dao;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ConnectionManager;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.jwt.hibernate.bean.Role;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.controller.Encryptor;
import com.jwt.hibernate.dao.HibernateUtil;

public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public String addUserDetails(String login, String password, String name,
			String surname, String pesel, Set<Role> roles) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();			           		
            Encryptor encryptor = new Encryptor();

    		List<User> users = new ArrayList<User>();
    		List<User> usersPesel = new ArrayList<User>();

			Transaction trns = session.beginTransaction();            
			String hql = "FROM User e WHERE e.login = '" + login + "'";
			users = session.createQuery(hql).list();
	          
			hql = "FROM User e WHERE e.pesel = '" + pesel + "'";
			usersPesel = session.createQuery(hql).list();
			
			if (users.isEmpty() && usersPesel.isEmpty()){
            
				User user = new User();
				user.setLogin(login);
				user.setPassword(encryptor.encrypt(password));
				user.setName(name);
				user.setSurname(surname);
				user.setPesel(pesel);
				
				Set<Role> _roles = new HashSet<Role>(0);
				if(roles != null){
					trns = session.beginTransaction();
					hql = "FROM Role";
					List<Role> roleList = new ArrayList<Role>();
					roleList = session.createQuery(hql).list();
					
					for (Role role : roles){
						Long usrId = user.getId();
						Long rlId = null;
						for (Role roleFromDB : roleList){
							if (role.getType().equals(roleFromDB.getType())) {
								_roles.add(roleFromDB);
							}
						}
					}
				} 
				user.setRoles(_roles);
				
				Transaction transaction = session.beginTransaction();			
				session.save(user); 			
				transaction.commit();
				System.out.println("\n\n Details Added \n");
			}
			else {
				return ("Ten login jest juz zajety");
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return ("HibernateException");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return("NoSuchAlgorithmException");
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("NoSuchPaddingException");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("InvalidKeyException");
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ("IllegalBlockSizeException");
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ("BadPaddingException");
		}
		return ("Success");

	}

	public static User login(User user, String currentRole) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		Encryptor encryptor;
		String login="", password ="";
		Transaction trns = null;		
		List<User> resultUsers = new ArrayList<User>();
		encryptor = new Encryptor();			
		login = user.getLogin();
		password = encryptor.encrypt(user.getPassword());
        
		Session session = HibernateUtil.getSessionFactory().openSession();
						
		try{
			trns = session.beginTransaction();            
			String hql = "FROM User e WHERE e.login = '" + login + "'";
			resultUsers = session.createQuery(hql).list();				
		}
		catch (Exception e){
	        e.printStackTrace();
	        System.out.println("error creating users list");
	    }
		
		if (resultUsers.size() == 1 && resultUsers.get(0).getPassword().equals(password)){
			String name = "", surname = "";
			Set<Role> roles = new HashSet<Role>(0);
			String activeRoleString = null;
			boolean possibleRole = false;
			try {
				User u = resultUsers.get(0);
				Hibernate.initialize(u.getRoles());
				name = u.getName();
				surname = u.getSurname();
				roles = (Set<Role>) u.getRoles();
				
				activeRoleString = u.getActiveRoleString();
				for (Role role : roles) {
					System.out.println("role: " + role.getId());
					if (role.getType().equals(currentRole)) possibleRole = true;
				}
								
				if (((activeRoleString == null || activeRoleString.equals("NULL")) && possibleRole) || (activeRoleString.equals(currentRole))){
					System.out.println("Welcome " + name + " " + surname);
					user.setName(name);
					user.setSurname(surname);
					user.setValid(true);
					user.setActiveRoleString(currentRole);
					
					Transaction trans = session.beginTransaction();
					String query = "UPDATE User u SET u.activeRoleString = '" + currentRole + "' "
							+ "WHERE u.id = '" + u.getId() + "'";
					int updateResult = session.createQuery(query).executeUpdate();
					System.out.println(updateResult);
					trans.commit();
					return user;
				}
				else return null;
			}
			catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Error in UserDAO");
			}
		}
		else return null;
		return user;
	}

	public static boolean logout (User user){
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Transaction trans = session.beginTransaction();
		String stringQuery = "UPDATE User u SET u.activeRoleString = 'NULL' "
				+ "WHERE u.login = '" + user.getLogin() + "'";
		int updateResult = session.createQuery(stringQuery).executeUpdate();
		trans.commit();
		
		return true;
	}
	
	public boolean update(Long id, String login, String password, String name, String surname, String pesel){
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Transaction trans = session.beginTransaction();
		Encryptor encryptor = new Encryptor();
		String stringQuery;
		try {
			stringQuery = "UPDATE User u SET u.login = '"+ login +"', u.password='"
					+ encryptor.encrypt(password) + "', u.name = '"+name+"', u.surname='" + surname 
					+ "', u.pesel = '" + pesel + "' WHERE u.id = '" + id + "'";
			
			int updateResult = session.createQuery(stringQuery).executeUpdate();
			trans.commit();
			return true;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	public static List<User> fetchAll(){

		List<User> users = new ArrayList<User>();
        Transaction trns = null;
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns = session.beginTransaction();            
			String hql = "FROM User ";
			users = session.createQuery(hql).list();
			
		}
		catch (Exception e){
        e.printStackTrace();
        System.out.println("error creating users list");
	    } finally {
	        session.flush();
	        session.close();
	    }

		return users;
	}
	
	public boolean add(User user){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction trns = session.beginTransaction(); 
			session.save(user);
			trns.commit();
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			System.out.println("error in RoleController --> insertRole");
			return false;
		}		
	}
	
	public void addAdmin(){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction trns = session.beginTransaction(); 
			Encryptor encryptor = new Encryptor();
			User user = new User("admin", encryptor.encrypt("a"));
			Set<Role> roles = new HashSet<Role>();
			RoleDAO roleDao = new RoleDAO();
			roles.add(roleDao.getRole("Admin"));
			user.setRoles(roles);
			
			session.save(user);
			trns.commit();
		}
		catch (Exception ex){
			ex.printStackTrace();
			System.out.println("error during adding admin");
		}		
	}
	
	public void addUsers(){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction trns = session.beginTransaction(); 
			Encryptor encryptor = new Encryptor();
			
			User user = new User("u", encryptor.encrypt("u"));
			Set<Role> roles = new HashSet<Role>();
			RoleDAO roleDao = new RoleDAO();
			roles.add(roleDao.getRole("Recepcjonista"));
			roles.add(roleDao.getRole("Asystent"));
			user.setRoles(roles);			
			session.save(user);
			
			user = new User("n", encryptor.encrypt("n"));
			roles = new HashSet<Role>();
			roles.add(roleDao.getRole("Dentysta"));
			roles.add(roleDao.getRole("Asystent"));
			user.setRoles(roles);			
			session.save(user);
			
			trns.commit();
		}
		catch (Exception ex){
			ex.printStackTrace();
			System.out.println("error during adding users");
		}		
	}
	
	public boolean delete(User user){
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Transaction trans = session.beginTransaction();
		int deleteCount = session.createQuery("delete from user where login = :login") 
			    .setParameter("login", user.getLogin())
			    .executeUpdate();
		
		
		System.out.println("delete count: " + deleteCount);
		trans.commit();
				
		return false;
	}
	
	public User get(String login){
		User user = new User();
        Transaction trns = null;
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns = session.beginTransaction();            
			String hql = "FROM User WHERE login = '" + login + "'";
			user = (User) session.createQuery(hql).list().get(0);
			Hibernate.initialize(user.getRoles());
		}
		catch (Exception e){
        e.printStackTrace();
        System.out.println("error creating users list");
	    } finally {
	        session.flush();
	        session.close();
	    }
		return user;
	}
	
	public User get(Long id){
		User user = new User();
        Transaction trns = null;
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns = session.beginTransaction();            
			String hql = "FROM User WHERE id = '" + id + "'";
			user = (User) session.createQuery(hql).list().get(0);
			Hibernate.initialize(user.getRoles());
		}
		catch (Exception e){
        e.printStackTrace();
        System.out.println("error creating users list");
	    } finally {
	        session.flush();
	        session.close();
	    }
		return user;
	}
	
	public static void delete (long id){
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();		
		Transaction trans1 = session1.beginTransaction();
		
		
		try {
			  // your code
			
			  String hql1 = "delete from user_role where user_id= :id";
			  Query query1 = session1.createQuery(hql1);		  
			  query1.setString("id", Long.toString(id));		  
			  System.out.println(query1.executeUpdate());
			  trans1.commit();

			  
			} catch (Throwable t) {
			  trans1.rollback();
			  System.out.println("errory");		  
			  throw t;
			}
		

		  // your code
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();		
			Transaction trans2 =session2.beginTransaction();
			
			try {		  
		  String hql2 = "delete from user where id= :id";
		  Query query2 = session2.createQuery(hql2);		  
		  query2.setString("id", Long.toString(id));		  
		  System.out.println(query2.executeUpdate());
		  trans2.commit();

		  
		} catch (Throwable t) {
		  trans2.rollback();
		  System.out.println("errory");		  
		  throw t;
		}
		
	}
	
	

}
