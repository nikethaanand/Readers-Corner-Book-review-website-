<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book To Read List</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>

<body>
<div align="center">
	<h1>Add a Book To Read List</h1>
	<h3>User Page</h3>
	
	<form action="addreadlist" method="post">
		<p>
			<label for="username">User Name</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="title">Book Title</label>
			<input id="title" name="title" value="">
		</p>
		<p>
			<input type="submit">
		</p>
		<br/><br/>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
			
	</form>
	</div>
		<footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>