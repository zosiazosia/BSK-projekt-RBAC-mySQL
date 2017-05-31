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
					<td><input placeholder="nazwa" type="text" name="type" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="createRole" />Create Roles</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="readRole" />Read Roles</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="updateRole" />Update Roles</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="deleteRole" />Delete Roles</br></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="createUser" />Create Users</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="readUser" />Read Users</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="updateUser" />Update Users</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="deleteUser" />Delete Users</br></td>
				</tr>
				
				
				<tr>
					<td><input type="checkbox" name="createClient" />Create Clients</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="readClient" />Read Clients</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="updateClient" />Update Clients</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="deleteClient" />Delete Clients</br></td>
				</tr>
				
				
				<tr>
					<td><input type="checkbox" name="createAppointment" />Create Appointments</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="readAppointment" />Read Appointments</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="updateAppointment" />Update Appointments</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="deleteAppointment" />Delete Appointments</br></td>
				</tr>
				
				
				<tr>
					<td><input type="checkbox" name="createTreatment" />Create Treatments</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="readTreatment" />Read Treatments</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="updateTreatment" />Update Treatments</br></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="deleteTreatment" />Delete Treatments</br></td>
				</tr>
			</tbody>
		</table>
		<p />
		<input type="submit" value="Dodaj" class="okbutton" />
		</center>
	</form>
</body>
</html>