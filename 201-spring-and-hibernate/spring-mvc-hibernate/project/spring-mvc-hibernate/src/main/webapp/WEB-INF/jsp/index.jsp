<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to PS Bank</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="jumbotron">
					<h1 class="display-4">Welcome to PS Bank</h1>
					<p class="lead">Happiness = A Good Bank Account, A Good Cook
						and A Good Digestion - Jean</p>
					<a href="user/new" class="btn btn-lg btn-success">Register Here</a>
					<p>
						<br />Existing Users: <a href="user/login">Login Here</a>
					</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
				<div class="card" style="height: 200px;">
					<div class="card-header">Online</div>
					<img alt="Online"
						src='<spring:url value="/resources/static/stay-online.png"></spring:url>'
						class="card-img-top" style="width: fit-content; margin: auto;" />
					<div class="card-body">
						<p class="card-text">200+ Transactions via NetBanking</p>
					</div>
				</div>
			</div>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
				<div class="card" style="height: 200px;">
					<div class="card-header">Phone</div>
					<img alt="Online"
						src='<spring:url value="/resources/static/smartphone.png"></spring:url>'
						class="card-img-top" style="width: fit-content; margin: auto;" />
					<div class="card-body">
						<p class="card-text">75+ Transactions on your smartphone</p>
					</div>
				</div>
			</div>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
				<div class="card" style="height: 200px;">
					<div class="card-header">Social Media</div>
					<img alt="Online"
						src='<spring:url value="/resources/static/social-media.png"></spring:url>'
						class="card-img-top" style="width: fit-content; margin: auto;" />
					<div class="card-body">
						<p class="card-text">Social Media - Chatting, Sharing +
							Banking</p>
					</div>
				</div>
			</div>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
				<div class="card" style="height: 200px;">
					<div class="card-header">Watch</div>
					<img alt="Online"
						src='<spring:url value="/resources/static/smartwatch.png"></spring:url>'
						class="card-img-top" style="width: fit-content; margin: auto;" />
					<div class="card-body">
						<p class="card-text">Banking at the flick of the wrist now</p>
					</div>
				</div>
			</div>
			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>