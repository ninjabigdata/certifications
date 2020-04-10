<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PS Bank: New Account</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	<h2>Create New Account</h2>
	<form:form modelAttribute="account" action="saveAccount">
		<table>
			<tr>
				<td><spring:message code="lbl.accountNo" />:</td>
				<td><form:input path="accountNo" /> <form:errors
						path="accountNo" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.accountHolderName" />:</td>
				<td><form:input path="accountHolderName" size="30" /> <form:errors
						path="accountHolderName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.accountBalance" />:</td>
				<td><form:input path="accountBalance" size="30" /> <form:errors
						path="accountBalance" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.accountType" />:</td>
				<td><form:select path="accountType">
						<form:option value="">Select Account Type</form:option>
						<form:option value="SAVINGS">Savings</form:option>
						<form:option value="CURRENT">Current</form:option>
					</form:select> <form:errors path="accountType" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.dob" />:</td>
				<td><form:input path="dateOfBirth" size="30" /> <form:errors
						path="dateOfBirth" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.psCode" />:</td>
				<td><form:input path="psCode" size="30" /> <form:errors
						path="psCode" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Create Account" name="btnSubmit"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>