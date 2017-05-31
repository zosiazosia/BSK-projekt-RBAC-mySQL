<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
    <%@page import="com.jwt.hibernate.dao.HibernateUtil" 
    import="com.jwt.hibernate.bean.Client" 
    import="com.jwt.hibernate.bean.Treatment"
    import="com.jwt.hibernate.dao.ClientDAO" 
    import="com.jwt.hibernate.dao.UserDAO"  
    import="com.jwt.hibernate.dao.TreatmentDAO" 
    import="org.hibernate.*"
    import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nowa wizyta</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="appointmentAdded" method="post">
		<center>
		<table border="0.5"  cellpadding="3">
			<thead>
				<tr>
					<th class="header">Nowa wizyta</th>
				</tr>
			</thead>
			<tbody>
				
				<tr>
					<td><input placeholder="cena" type="text" name="price" value="" class="inputbox" required/></td>
				</tr>
				<tr>
					<td>
					
					<%
					List<Treatment> treatList = new ArrayList<Treatment>();
					TreatmentDAO tDAO = new TreatmentDAO();
					treatList = tDAO.fetchAll();
					
					String[] treatTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
					int i = 0;
					for (Treatment treat : treatList){
						String name = treat.getName();
						%>
						<input type="checkbox" name="<%=treatTable[i++]%>" value="<%=name%>" /><%=name%></br>
						<%
					}
					
					%>					
					<input type="hidden" name="treatsNumber" value="<%=i%>" />
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