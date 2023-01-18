 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>Delete Book</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<h1>Delete Book</h1>
	<h1>${messages.title}</h1>
	<form action="bookdelete" method="post">
		<p>
			 <c:if test="${messages.disableSubmit}">style="display:none"</c:if>
				<div align="center"><label for="title">Title</label></div>
				<div align="center"><input id="title" name="title" value="">
			</div>
		</p>
		
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	<footer>
	</div>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>