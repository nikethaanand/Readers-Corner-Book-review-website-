<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new review</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
	<div align="center">
		<h1>Add a new Review for a Book</h1>
		<form action="addreview" method="post">	
			<p>
				<div align="center"><label for="title">Title</label></div>
				<div align="center"><input id="title" name="title" value=""></div>
			</p>
			<p>
				<div align="center"><label for="userName">Username</label></div>
				<div align="center"><input id="userName" name="userName" value=""></div>
			</p>
			<p>
				<div align="center"><label for="score">Score</label></div>
				<div align="center"><input id="score" name="score" value=""></div>
			</p>
			<p>
				<div align="center"><label for="shortSummary">Short Summary</label></div>
				<div align="center"><input id="shortSummary" name="shortSummary" value=""></div>
			</p>
			<p>
				<div align="center"><label for="summary">Summary</label></div>
				<div align="center"><input id="summary" name="summary" value=""></div>
			</p>
			<p>
				<div align="center"><label for="helpfulness">Helpfulness</label></div>
				<div align="center"><input id="helpfulness" name="helpfulness" value=""></div>
			</p>
			<p>
				<div align="center"><input type="submit"></div>
			</p>
		</form>
		<br/><br/>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>

		
	</div>
	<footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>
