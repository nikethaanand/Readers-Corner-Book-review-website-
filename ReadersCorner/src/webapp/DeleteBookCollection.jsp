<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Book From Collection</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<h1>Delete Book From Collection</h1>
	<h2>${messages.title}</h2>
	<form action="deletebookcollection" method="post">
		<p>
			 <c:if test="${messages.disableSubmit}">style="display:none"</c:if>
				<div align="center"><label for="username">Username</label></div>
				<div align="center"><input id="username" name="username" value="">
			</div>
		</p>
		<p>
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>
				<div align="center"><label   for="title">Title</label></div>
				<div align="center"><input id="title" name="title" value="">
			</div>
		</p>
		<p>
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>
				<div align="center"> <label   for="bookcollectionId">Book Collection Id</label></div>
				<div align="center"><input id="bookcollectionId" name="bookcollectionId" value="">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	</div>
	<footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>