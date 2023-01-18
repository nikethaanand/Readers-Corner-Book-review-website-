<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a  Book From Read List</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<h1>Delete a Book From Read List</h1>
	<h2>${messages.title}</h2>
	<form action="deletereadlist" method="post">
		<p>
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>
				<div align="center"><label for="username">UserName</label></div>
				<div align="center"><input id="username" name="username" value=""></div>
		</p>
		<p>
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>
				<div align="center"><label for="readListId">ReadList Id</label></div>
				<div align="center"><input id="readListId" name="readListId" value="">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<div align="center"><input type="submit"></div>
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