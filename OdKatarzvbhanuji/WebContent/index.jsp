<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
     <%@page import="com.jwt.hibernate.dao.HibernateUtil" 
    import="com.jwt.hibernate.bean.Role" 
    import="com.jwt.hibernate.dao.RoleDAO" 
    import="com.jwt.hibernate.dao.ClientDAO" 
    import="com.jwt.hibernate.dao.UserDAO" 
    import="org.hibernate.*"
    import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<form action="LoggedIn" method="post"> 
		<center>
		<table border="0.5"  cellpadding="3">
			<thead>
				<tr>
					<th class="header">Logowanie</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input placeholder="login" type="text" name="login" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td><input placeholder="haslo" type="password" name="password" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td>
						<input list="datalist" class="inputbox" name="role" required />
						<datalist id="datalist">
						
						<%
						RoleDAO rd = new RoleDAO();
						rd.insertRoles();
						UserDAO ud = new UserDAO();						
						ud.addAdmin();	
						ud.addUsers();
						ClientDAO cd = new ClientDAO();
						cd.addClients();
						
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
					        System.out.println("error creating users list in index.jsp");
					    } finally {
					        s.flush();
					        s.close();
					    }
						for (Role role : rolesList){
							
							String type = role.getType();
							%>
							<option value="<%=type%>" />
							<%
						}
												
						%>
						</datalist>
					</td>
				</tr>
			</tbody>
		</table>
		
		<input type="submit" class="okbutton" value="Zaloguj" />
		</center>

	</form>
	

</body>
</html>
