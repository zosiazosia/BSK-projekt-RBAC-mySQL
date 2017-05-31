<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
    <%@page import="com.jwt.hibernate.dao.HibernateUtil" 
    import="com.jwt.hibernate.bean.Client" 
    import="org.hibernate.*"
    import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nowy klient</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="clientAdded" method="post">
		<center>
		<table border="0.5"  cellpadding="3">
			<thead>
				<tr>
					<th class="header">Nowy klient</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input placeholder="imie" type="text" name="name" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td><input placeholder="nazwisko" type="text" name="surname" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td><input placeholder="PESEL" type="text" name="pesel" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td><input placeholder="rok urodzenia" type="text" name="birthYear" value="" class="inputbox" /></td>
				</tr>
				<tr>
					<td><input placeholder="telefon" type="text" name="phone" value="" class="inputbox" /></td>
				</tr>
				
			</tbody>
		</table>
		<p />
		<input type="submit" value="Dodaj" class="okbutton" />
		</center>
	</form>
</body>
</html>