<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<style type="text/css">
.error {
	color: #FF0000;
}

.errorblock {
	color: #000;
	background-color: #FFEEEE;
	border: 3px solid #FF0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<h1>Registration</h1>
	<form:form modelAttribute="registration">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td><spring:message code="name" /></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" element="p" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save Changes" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>