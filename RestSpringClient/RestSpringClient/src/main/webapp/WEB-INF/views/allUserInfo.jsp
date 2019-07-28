<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Employees List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Name</th><th>Age</th><th>Salary</th><th>Address</th></tr>  
   <c:forEach var="emp" items="${users}">   
   <tr>  
   <td>${emp.userName}</td>  
   <td>${emp.age}</td>  
   <td>${emp.salary}</td>  
   <td>${emp.address}</td>  

   </tr>  
   </c:forEach>  
   </table>  
   <br/> 
</body>
</html>