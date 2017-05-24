<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User's list</title>
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
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
            	  <ul class="nav nav-tabs">
				    <li class="active"><a href="#">Clients</a></li>
				    <li><a href="getAllEmployees">Employees</a></li>
				    <li><a href="#">Menu 2</a></li>
				    <li><a href="#">Menu 3</a></li>
				  </ul>
				  <br>
            </h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty usersList}">
                There are no users
            </c:if>
            <c:if test="${not empty usersList}">   
            	            	
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>pesel</th>
                        <th>delete</th>
                        <th>update</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${usersList}" var="user">
                        <tr>
                        	<th><c:out value="${user.id}"/></th>
                        	<th><c:out value="${user.name}"/></th>
                        	<th><c:out value="${user.surname}"/></th>
                        	<th><c:out value="${user.pesel}"/></th>
                        	<th>
                        	<form method="post" action="users" />
                        	<input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
                        	<input type="hidden" name="typ" value="delete"/>                        		
								<button class="okbutton" id="registerbutton">delete</button>		
							</form>                        	
                        	</th>
                        	<th>                        	
                        	<form method="post" action="users"/>
                        		<input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
                        		<input type="hidden" name="typ" value="update"/>             		
								<button class="okbutton" id="registerbutton">update</button>		
							</form>
							</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <form method="post" action="users"/>
                <input type="hidden" name="typ" value="add"/>             		
				<button class="okbutton" id="registerbutton">add</button>		
			</form>
            
        </div>
        
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
     --%>
</div>
</body>
</html>