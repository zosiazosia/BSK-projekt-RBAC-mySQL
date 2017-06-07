<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
    <%@page import="com.jwt.hibernate.dao.HibernateUtil" 
    import="com.jwt.hibernate.bean.Role" 
    import="org.hibernate.*"
    import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nowa rola</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="roleAdded" method="post">
		<center>
		<table border="0.5"  cellpadding="3">
			<thead>
				<tr>
					<th class="header">Nowa rola</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Nazwa <input placeholder="nazwa" type="text" name="type" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td>Użytkownicy <input type="text" value="" class="inputbox" name="users"/></td>
				</tr>
				<tr>
					<td>Klienci <input type="text" value="" class="inputbox" name="clients"/> </td>
				</tr>
				<tr>
					<td>Role <input type="text" value="" class="inputbox" name="roles"/> </td>
				</tr>
				<tr>
					<td>Wizyty <input type="text" value="" class="inputbox" name="appointments"/> </td>
				</tr>
				<tr>
					<td>Zabiegi <input type="text" value="" class="inputbox" name="treatments"/> </td>
				</tr>
			</tbody>
		</table>
		<p />
		<input type="submit" value="Dodaj" class="okbutton" />
		</center>
	</form>
</body>
</html>