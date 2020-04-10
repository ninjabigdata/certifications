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
	<div class="container">
		<%@ include file="header.jsp"%>

		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h2 class="page-header">Please Fill Out The Form Details</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-6">
				<form:form action="saveAccount" modelAttribute="account"
					cssClass="form-horizontal" role="form">
					<div class="form-group row">
						<label for="accountNo" class="col-6 col-form-label"><spring:message
								code="lbl.accountNo" />:</label>
						<div class="col-6">
							<form:input path="accountNo" cssClass="form-control" />
							<form:errors path="accountNo" cssClass="alert-danger" />
							<p class="alert-danger">${message}</p>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-6 col-form-label" for="accountHolderName"><spring:message
								code="lbl.accountHolderName" />:</label>
						<div class="col-6">
							<form:input path="accountHolderName" cssClass="form-control" />
							<form:errors path="accountHolderName" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-6 col-form-label" for="accountBalance"><spring:message
								code="lbl.accountBalance" />:</label>
						<div class="col-6">
							<form:input path="accountBalance" cssClass="form-control" />
							<form:errors path="accountBalance" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-6 col-form-label" for="accountType"><spring:message
								code="lbl.accountType" />:</label>
						<div class="col-6">
							<form:select path="accountType">
								<form:option value="">Select Account Type</form:option>
								<form:option value="SAVINGS">Savings</form:option>
								<form:option value="CURRENT">Current</form:option>
							</form:select>
							<form:errors path="accountType" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-6 col-form-label" for="dateOfBirth"><spring:message
								code="lbl.dob" />:</label>
						<div class="col-6">
							<form:input path="dateOfBirth" cssClass="form-control" />
							<form:errors path="dateOfBirth" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-6 col-form-label" for="psCode"><spring:message
								code="lbl.psCode" />:</label>
						<div class="col-6">
							<form:input path="psCode" cssClass="form-control" />
							<form:errors path="psCode" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-6 col-6">
							<input class="btn btn-primary" type="submit"
								value="Create Account" name="btnSubmit">
						</div>
					</div>
				</form:form>
			</div>
		</div>

		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>