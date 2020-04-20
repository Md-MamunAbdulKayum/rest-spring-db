<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
.meniItem {
	color: white;
}
</style>
</head>
<body>
	<nav style="background: teal;" class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
			</div>
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/createuser" />"><h4
							class="meniItem">Add User</h4></a></li>
			</ul>
		</div>
	</nav>
	<div class="user-list" align="center">
	<h1>Users' List</h1>
	<table border="2" width="70%" cellpadding="2">
	<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Salary</th>
			<th>Address</th>
			<th>Action</th>
		</tr>
		
		<c:choose>
           <c:when test="${empty users}">
            <p>0 row(s) found!
           </c:when>
           <c:otherwise>
            <c:forEach var="emp" items="${users}">
			<tr>
				<td>${emp.userName}</td>
				<td>${emp.age}</td>
				<td>${emp.salary}</td>
				<td>${emp.address}</td>
                <td><a href="<c:url value="/updateuser/${emp.userId}"/>">Edit</a> |
                <a href="<c:url value="/deleteuser/${emp.userId}" />">Delete</a></td>
			</tr>
		</c:forEach>
   
           </c:otherwise>
        </c:choose>	
	</table>
	<br />
	</div>
</body>
</html>