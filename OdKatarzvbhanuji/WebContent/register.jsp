<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
    <%@page import="com.jwt.hibernate.dao.HibernateUtil" 
    import="com.jwt.hibernate.bean.Role" 
    import="com.jwt.hibernate.dao.RoleDAO" 
    import="com.jwt.hibernate.dao.UserDAO" 
    import="com.jwt.hibernate.bean.User" 
    import="com.jwt.hibernate.dao.ClientDAO" 
    import="org.hibernate.*"
    import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodawanie uzytkownika</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="register" method="post">
		<center>
		<table border="0.5"  cellpadding="3">
			<thead>
				<tr>
					<th class="header">Nowy uzytkownik</th>
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
					<td><input placeholder="imie" type="text" name="name" value="" class="inputbox"/></td>
				</tr>
				<tr>
					<td><input placeholder="nazwisko" type="text" name="surname" value="" class="inputbox"/></td>
				</tr>
				<tr>
					<td><input placeholder="PESEL" type="text" name="pesel" value="" class="inputbox"/></td>
				</tr>
				
				<tr>
					<td>
					
					<%
					
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
				    } finally {
				        s.flush();
				        s.close();
				    }
					
					String[] rolesTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
					int i = 0;
					for (Role role : rolesList){
						
						String type = role.getType();
						%>
						<input type="checkbox" name="<%=rolesTable[i++]%>" value="<%=type%>" /><%=type%></br>
						<%
					}
					
					%>					
					<input type="hidden" name="rolesNumber" value="<%=i%>" />
					</td>
				</tr>
			</tbody>
		</table>
		<p />
		<input type="submit" value="Dodaj" class="okbutton" />
		</center>
	</form>
</body>
</html>