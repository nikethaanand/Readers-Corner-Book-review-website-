<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Book</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
	<header align="center">
		<h1>Add a Book</h1>

	</header>
	<div align="center">
	<form action="addbook" method="post">
		<p >
			<div align="center"><label for="title">Title</label></div>
			<div align="center"><input id="title" name="title" value=""></div>
		</p>
		<p>
			<div align="center"><label for="description">Description</label></div>
			<div align="center"><input id="description" name="description" value=""></div>
		</p>
		<p>
			<div align="center"><label for="authors">Authors</label></div>
			<div align="center"><input id="authors" name="authors" value=""></div>
		</p>
		<p>
			<div align="center"><label for="categories">Categories</label></div>
			<div align="center"><input id="categories" name="categories" value=""></div>
		</p>
		<p>
			<div align="center"><label for="publishedDate">Published Date</label></div>
			<div align="center"><input id="publishedDate" name="publishedDate" value=""></div>
		</p>
		<p>
			<div align="center"><label for="ratingsCount">Ratings Count</label></div>
			<div align="center"><input id="ratingsCount" name="ratingsCount" value=""></div>
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