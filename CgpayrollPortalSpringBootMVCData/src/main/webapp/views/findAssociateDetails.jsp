<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
.error{
color:maroon; 
size:20;
font-weight: bold;
}
</style>
</head>
<body>
<div align="center">
<form action="findAssociate" method="post">
<table>
<tr>
<td> Associate Id<td>
<td><input type="text" name="associateId"/></td>
<td><input type ="submit" name="find"/></td>
<td><a href="Index">||HOME||</a></td>
</tr>
</table>
</form>
</div>
<br><br><br><br>
<div align="center" class="error">
	<h2>${errorMessage}</h2>
	</div>
	<br><br>
<div align="center">
	<table>
		<tr>
			<th>AssociateId</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email Id</th>
		</tr>
		<tr>
		<td>${ associate.associateId}</td>
		<td>${ associate.firstName}</td>
		<td>${ associate.lastName}</td>
		<td>${ associate.emailId}</td>
		</tr>
	</table>
</div>
</body>

</html>