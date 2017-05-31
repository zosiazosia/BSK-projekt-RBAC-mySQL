<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  
    import="com.jwt.hibernate.bean.Role" 
    import="com.jwt.hibernate.dao.RoleDAO" 
    import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Role</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="css/login.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<center>
<div class="container myrow-container">
    

        <div class="panel-body">
        
        <%
        String roleNum = request.getParameter("rolesNumber");
        int req = 0;
        if (roleNum != null){
			req = Integer.parseInt(roleNum);
        }
		String delete = request.getParameter("delete");
    	String update = request.getParameter("update");
    	String create = request.getParameter("create");
    	String read = request.getParameter("read");
		Set<Role> roles = new HashSet<Role>(0);
		
		String[] rolesTable = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
		for (int i=0; i<req; i++){
			String stringRole = request.getParameter(rolesTable[i]);
			if (stringRole != null){
				RoleDAO rDAO = new RoleDAO();
				Role role = rDAO.getRole(stringRole);
				roles.add(role);
			}
		}
    	 %>            	            	
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Id</th>
                        <th>Typ</th>
                        <th>Uzytkownicy</th>
                        <th>Klienci</th>
                        <th>Role</th>

                        <th>Wizyty</th>

                        <th>Zabiegi</th>           
                        <%
                        if(delete.equals("1")){
                        
                        %>
                        	<th>Usuwanie</th>
                        <%
                        }
                        if(update.equals("1")){
                        %>
                        
                        	<th>Edycja</th>
                       	<%} %>
                    </tr>
                    </thead>
                    <tbody>
                  <c:forEach items="<%=roles %>" var="el">
                        <tr>
                        	<th><c:out value="${el.id}"/></th>
                        	<th><c:out value="${el.type}"/></th>

                        	<th>
                        	<c:set var="U" value="${el.updateUser eq true ? 'U': '-'}"/>
                        	<c:set var="R" value="${el.readUser eq true ? 'R': '-'}"/>
                        	<c:set var="D" value="${el.deleteUser eq true ? 'D': '-'}"/>
                        	<c:set var="C" value="${el.createUser eq true ? 'C': '-'}"/>
                        	
                        	<%
                        	
                        	
						    String resp = "";
                        	resp = resp + (String)pageContext.getAttribute("C")
                        				+ (String)pageContext.getAttribute("R")
                        				+ (String)pageContext.getAttribute("U")
                        				+ (String)pageContext.getAttribute("D");
                        	
						    out.println(resp);
  							%>  
                        	</th>
                        	<th>
                        	<c:set var="U" value="${el.updateClient eq true ? 'U': '-'}"/>
                        	<c:set var="R" value="${el.readClient eq true ? 'R': '-'}"/>
                        	<c:set var="D" value="${el.deleteClient eq true ? 'D': '-'}"/>
                        	<c:set var="C" value="${el.createClient eq true ? 'C': '-'}"/>
                        	
                        	<%
						    resp = "";
                        	resp = resp + (String)pageContext.getAttribute("C")
                        				+ (String)pageContext.getAttribute("R")
                        				+ (String)pageContext.getAttribute("U")
                        				+ (String)pageContext.getAttribute("D");
                        	
						    out.println(resp);
  							%>  
                        	</th>
                        	<th>
                        	<c:set var="U" value="${el.updateRole eq true ? 'U': '-'}"/>
                        	<c:set var="R" value="${el.readRole eq true ? 'R': '-'}"/>
                        	<c:set var="D" value="${el.deleteRole eq true ? 'D': '-'}"/>
                        	<c:set var="C" value="${el.createRole eq true ? 'C': '-'}"/>
                        	
                        	<%
						    resp = "";
                        	resp = resp + (String)pageContext.getAttribute("C")
                        				+ (String)pageContext.getAttribute("R")
                        				+ (String)pageContext.getAttribute("U")
                        				+ (String)pageContext.getAttribute("D");
                        	
						    out.println(resp);
  							%>  
                        	</th>
                        	<th>
                        	<c:set var="U" value="${el.updateAppointment eq true ? 'U': '-'}"/>
                        	<c:set var="R" value="${el.readAppointment eq true ? 'R': '-'}"/>
                        	<c:set var="D" value="${el.deleteAppointment eq true ? 'D': '-'}"/>
                        	<c:set var="C" value="${el.createAppointment eq true ? 'C': '-'}"/>
                        	
                        	<%
						    resp = "";
                        	resp = resp + (String)pageContext.getAttribute("C")
                        				+ (String)pageContext.getAttribute("R")
                        				+ (String)pageContext.getAttribute("U")
                        				+ (String)pageContext.getAttribute("D");
                        	
						    out.println(resp);
  							%>  
                        	</th>
                        	                        	                        	<th>
                        	<c:set var="U" value="${el.updateTreatment eq true ? 'U': '-'}"/>
                        	<c:set var="R" value="${el.readTreatment eq true ? 'R': '-'}"/>
                        	<c:set var="D" value="${el.deleteTreatment eq true ? 'D': '-'}"/>
                        	<c:set var="C" value="${el.createTreatment eq true ? 'C': '-'}"/>
                        	
                        	<%
						    resp = "";
                        	resp = resp + (String)pageContext.getAttribute("C")
                        				+ (String)pageContext.getAttribute("R")
                        				+ (String)pageContext.getAttribute("U")
                        				+ (String)pageContext.getAttribute("D");
                        	
						    out.println(resp);
  							%>  
                        	</th>
                        	                 	<%
                        
                	   if(delete.equals("1")){
                        
                        %>
	                        	<th>
	                        	<form method="post" action="roles" >
	                        	<input type="hidden" name="id" value="<c:out value='${el.id}'/>"/>
	                        	<input type="hidden" name="typ" value="delete"/>                              		
								<button class="okbutton" id="registerbutton">Usun</button>		
								</form>                        	
	                        	</th>
 						<%
                        }
                  	    if(update.equals("1")){
                        %>
                        
	                        	<th>                        	
	                        	<form method="post" action="updateRole">
	                        	<input type="hidden" name="id" value="<c:out value='${el.id}'/>"/>     
	                        	<input type="hidden" name="delete" value="<%=delete%>"/>               
	                        	<input type="hidden" name="update" value="<%=update%>"/>             
	                        	<input type="hidden" name="create" value="<%=create%>"/>               
	                        	<input type="hidden" name="read" value="<%=read%>"/>       
	                			<input type="hidden" name="typ" value="update"/>   
									<button class="okbutton" id="registerbutton">Edytuj</button>		
								</form>
								</th>
								<%} %>
                        </tr>
                       </c:forEach>
                    </tbody>
                </table>
                
            <%if (create.equals("1")){ %>
	            <form method="post" action="addRole">
	                <input type="hidden" name="typ" value="create"/>          
                     	<input type="hidden" name="delete" value="<%=delete%>"/>               
                     	<input type="hidden" name="update" value="<%=update%>"/>             
                     	<input type="hidden" name="create" value="<%=create%>"/>               
                     	<input type="hidden" name="read" value="<%=read%>"/>        
	                <input type='submit' value='Nowa rola' class='okbutton' />             				
				</form>
			<%} %>
			</br>
			<form method='get' action='welcome'>
				<input type='submit' value='Powrot' class='okbutton' />
			</form>
            
        </div>
        
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
     --%>
</div>
</center>
</body>
</html>