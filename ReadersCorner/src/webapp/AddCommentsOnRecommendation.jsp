<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Comments on Recommendations</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<h1>Add Comments on Recommendations</h1>
	<form action="addcomments" method="post">
		<p>
			<div align="center"><label for="recommendationId">Recommendation Id</label></div>
			<div align="center"><input id="recommendationId" name="recommendationId" value=""></div>
		</p>
		<p>
			<div align="center"><label for="userName">Username</label></div>
			<div align="center"><input id="userName" name="userName" value=""></div>
		</p>
		<p>
			<div align="center"><label for="comment">Comment</label></div>
			<div align="center"><input id="comment" name="comment" value=""></div>
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