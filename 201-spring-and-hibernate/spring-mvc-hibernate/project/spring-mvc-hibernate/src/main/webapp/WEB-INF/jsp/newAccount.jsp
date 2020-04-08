<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PS Bank: New Account</title>
</head>
<body>
	<h2>Create New Account</h2>
	<form name="accountForm" action="saveAccount" method="post">
		<table>
			<tr>
				<td>Account #:</td>
				<td><input type="text" name="accountNo"></td>
			</tr>
			<tr>
				<td>Account Holder Name:</td>
				<td><input type="text" name="accountHolderName"></td>
			</tr>
			<tr>
				<td>Account Balance:</td>
				<td><input type="text" name="accountBalance"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Create Account" name="btnSubmit"></td>
			</tr>
		</table>
	</form>
</body>
</html>