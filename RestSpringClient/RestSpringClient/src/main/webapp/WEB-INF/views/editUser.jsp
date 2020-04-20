<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
  <title>Edit User</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.meniItem {
	color: white;
}
</style>
</head>
<body>
<div class="navbar">
	<nav style="background: teal;" class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
			</div>
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/createuser" />"><h4
							class="meniItem">Add User</h4></a></li>
				<li><a href="<c:url value="/" />"><h4
							class="meniItem">Show Users</h4></a></li>
			</ul>
		</div>
	</nav>
</div>
<div class="container">
  <h2>Edit User</h2>
  <form:form class="form-horizontal" role="form" action="../updateuser" modelAttribute="userObject" method="POST">
    <div class="form-group">
      <label for="userName">Name:</label>
      <form:input type="userName" class="form-control" id="userName" name="userName" path="userName"/>
    </div>
    <div class="form-group">
      <label for="age">Age:</label>
      <form:input type="age" class="form-control" id="age" placeholder="Enter age" name="age" path="age"/>
    </div>
    <div class="form-group">
      <label for="salary">Salary:</label>
      <form:input type="salary" class="form-control" id="salary" placeholder="Enter salary" name="salary"  path="salary"/>
    </div>
    <div class="form-group">
      <label for="address">Address:</label>
      <form:input type="address" class="form-control" id="address" placeholder="Enter address" name="address"  path="address"/>
    </div>
    <form:input type="hidden" path="userId"/>
    <form:button type="submit" class="btn btn-default">Update</form:button>
</form:form>
</div>

</body>
</html>
