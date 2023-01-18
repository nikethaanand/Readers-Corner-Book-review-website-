<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book To Collection</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<h1>Add Book To Collection</h1>
	<h3>User Page</h3>
	<form action="addbooktocollection" method="post">
		<p>
			<div align="center"><label for="username">User Name</label></div>
			<div align="center"><input id="username" name="username" value=""></div>
		</p>
		<p>
			<div align="center"><label for="collectionId">Collection Id</label></div>
			<div align="center"><input id="collectionId" name="collectionId" value=""></div>
		</p>
		<p>
			<div align="center"><label for="title">Book Title</label></div>
			<div align="center"><input id="title" name="title" value=""></div>
		</p>
		<p>
			<div align="center"><input type="submit"></div>
		</p>
		<br/><br/>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
			
	</form>
	</div>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>