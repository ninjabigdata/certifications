<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PS Bank Account Holder Details</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th><spring:message code="lbl.accountNo" /></th>
				<th><spring:message code="lbl.accountHolderName" /></th>
				<th><spring:message code="lbl.accountBalance" /></th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${accounts }">
				<c:url var="updateLink" value="edit">
					<c:param name="accountNo" value="${account.accountNo}"></c:param>
				</c:url>
				<c:url var="deleteLink" value="delete">
					<c:param name="accountNo" value="${account.accountNo}"></c:param>
				</c:url>
				<tr>
					<td>${account.accountNo}</td>
					<td>${account.accountHolderName}</td>
					<td>${account.accountBalance}</td>
					<td><a href="${updateLink}">Edit</a></td>
					<td><a href="${deleteLink}"
						onclick="if (!(confirm('Are you sure to delete?'))) return false">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>