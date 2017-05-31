<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Client's list</title>
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
            <c:if test="${empty clientsList}">
                There are no clients
            </c:if>
            <c:if test="${not empty clientsList}">   
            	            	
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>ID</th>
                        <th>Imie</th>
                        <th>Nazwisko</th>
                        <th>PESEL</th>
                        <th>Telefon</th>
                        <th>Rok urodzenia</th>  
                        <c:if test="${not empty delete}"> 
                        	<th>Usuwanie</th>
                        </c:if>
                        <c:if test="${not empty update}"> 
                        	<th>Edycja</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clientsList}" var="el">
                        <tr>
                        	<th><c:out value="${el.id}"/></th>
                        	<th><c:out value="${el.name}"/></th>
                        	<th><c:out value="${el.surname}"/></th>
                        	<th><c:out value="${el.pesel}"/></th>
                        	<th><c:out value="${el.phone}"/></th>
                        	<th><c:out value="${el.birthYear}"/></th>                        	
                        	<c:if test="${not empty delete}"> 
	                        	<th>
	                        	<form method="post" action="clients" />
	                        	<input type="hidden" name="id" value="<c:out value='${el.id}'/>"/>
	                        	<input type="hidden" name="typ" value="delete"/>                        		
									<button class="okbutton" id="registerbutton">Usun</button>		
								</form>                        	
	                        	</th>
                        	</c:if>
                        	<c:if test="${not empty update}"> 
	                        	<th>                        	
	                        	<form method="post" action="clients"/>
	                        		<input type="hidden" name="id" value="<c:out value='${el.id}'/>"/>
	                        		<input type="hidden" name="typ" value="update"/>             		
									<button class="okbutton" id="registerbutton">Edytuj</button>		
								</form>
								</th>
							</c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <c:if test="${not empty create}">
	            <form method="post" action="clients">
	                <input type="hidden" name="typ" value="add"/>
	                <input type='submit' value='Nowy klient' class='okbutton' />             				
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
</center>
</body>
</html>