<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h1>Registration</h1>
	<form:form modelAttribute="registration">
		<table>
			<tr>
				<td><spring:message code="name" /></td>
				<td><form:input path="name"/></td>	
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save Changes" /></td>	
			</tr>
		</table>
	</form:form>
</body>
</html>