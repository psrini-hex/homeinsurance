<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans|Source+Sans+Pro"
	rel="stylesheet">
</head>
<style type="text/css">
<%@ include file="css/bootstrap.min.css" %>
<%@ include file="css/styles.css" %>
</style>
<%@ page import="com.perscholas.home_insurance.models.CoverageDetails"%>
<script>
	function retrievePolicy(propId) {
		window.location.href = "/getPolicy";
	}
</script>
<body>
	<div id="wrapper">
		<div align="center">
			<h2 class="brown-text">
				<img class="border-resize"
					src="http://2.bp.blogspot.com/-ShuRVrIaSvA/USMNj7THdsI/AAAAAAAAIV4/X8WVshITaHY/s1600/red1.jpg">
				<p>Homeowners Insurance</p>
			</h2>
			<div class="topnav">
				<a class="active" href="/home">Home |</a> <a href="/get_quote">Get
					Quote |</a> <a href="#contact">Retrieve Quote |</a> <a href="/retrievePolicy">My
					Policies |</a> <a href="/logout">Logout</a>
			</div>

		</div>
	</div>
	<hr />
	<div align="center">
		<h2 class="brown-text">Get started</h2>

	</div>
	<div class="row">
		<div class="column">
			<div align="right">
				<img
					src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Ranch_style_home_in_Salinas%2C_California.JPG/220px-Ranch_style_home_in_Salinas%2C_California.JPG">
			</div>
		</div>
		<div class="column">
			<div align="left">
				<a href="/get_quote">
					<button type="submit" class="btn btn-success">Get a Quote</button>
				</a>
			</div>
			<br>
			<div align="left">
				<a href="/retrievePolicy">
					<button type="submit" class="btn btn-success">My Policies</button>
				</a>
			</div>
		</div>
	</div>

</body>

<hr />
<div align="center">
	<h2 class="brown-text">Copyright © 2018 Homeownersinsurance.com
		All Rights Reserved</h2>
</div>

</html>