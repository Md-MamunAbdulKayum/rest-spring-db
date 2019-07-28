<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User information</title>
</head>
<body>
	<div align="center">
	<h1> User information </h1>
		<br>
		User Name: <strong>${user.userName}</strong> <br>
		User Age: <strong>${user.age}</strong> <br>
        User Salary: <strong>${user.salary}</strong> <br>
        User Address: <strong>${user.address}</strong> <br>
	</div> 
</body>
</html>