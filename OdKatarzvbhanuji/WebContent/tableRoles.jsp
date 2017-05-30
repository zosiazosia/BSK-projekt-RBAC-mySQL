<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Role's list</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<div class="container myrow-container">
    

        <div class="panel-body">
            <c:if test="${empty rolesList}">
                There are no roles
            </c:if>
            <c:if test="${not empty rolesList}">   
            	            	
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Id</th>
                        <th>Type</th>
                        <th>User</th>
                        <th>Client</th>
                        <th>Role</th>

                        <th>Appointment</th>

                        <th>Treatment</th>           
                        
                        <c:if test="${not empty delete}"> 
                        	<th>delete</th>
                        </c:if>
                        <c:if test="${not empty update}"> 
                        	<th>update</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${rolesList}" var="el">
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
                        	                 	
                        	<c:if test="${not empty delete}"> 
	                        	<th>
	                        	<form method="post" action="roles" />
	                        	<input type="hidden" name="id" value="<c:out value='${el.id}'/>"/>
	                        	<input type="hidden" name="typ" value="delete"/>                        		
									<button class="okbutton" id="registerbutton">delete</button>		
								</form>                        	
	                        	</th>
                        	</c:if>
                        	<c:if test="${not empty update}"> 
	                        	<th>                        	
	                        	<form method="post" action="roles"/>
	                        		<input type="hidden" name="id" value="<c:out value='${el.id}'/>"/>
	                        		<input type="hidden" name="typ" value="update"/>             		
									<button class="okbutton" id="registerbutton">update</button>		
								</form>
								</th>
							</c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <c:if test="${not empty create}">
	            <form method="post" action="roles">
	                <input type="hidden" name="typ" value="add"/>
	                <input type='submit' value='add new' class='okbutton' />             				
				</form>
			</c:if>
			
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
</body>
</html>