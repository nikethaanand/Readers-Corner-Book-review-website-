<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Collection</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<div align="center">
<body>
	<h1>Create Collections</h1>
	<h3>Users Page</h3>
	<form action="addcollection" method="post">
		<p>
			<div align="center"><label for="collectionName">Collection Name</label></div>
			<div align="center"><input id="collectionName" name="collectionName" value=""></div>
		</p>
		<p>
			<div align="center"><label for="userName">User Name</label></div>
			<div align="center"><input id="userName" name="userName" value=""></div>
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
</body>
	<footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</html>