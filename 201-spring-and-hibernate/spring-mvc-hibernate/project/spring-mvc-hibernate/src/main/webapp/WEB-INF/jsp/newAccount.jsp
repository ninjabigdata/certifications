<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PS Bank: New Account</title>
</head>
<body>
	<h2>Create New Account</h2>
	<form:form modelAttribute="account" action="saveAccount">
		<table>
			<tr>
				<td><spring:message code="lbl.accountNo" />: </td>
				<td><form:input type="text" path="accountNo" size="30" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.accountHolderName" />: </td>
				<td><form:input type="text" path="accountHolderName" size="30" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.accountBalance" />: </td>
				<td><form:input type="text" path="accountBalance" size="30" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Create Account" name="btnSubmit"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>