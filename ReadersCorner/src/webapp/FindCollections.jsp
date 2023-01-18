<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Collection</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="findcollections" method="post">
		<h1>Search for a Collection by UserName</h1>
		<p>
			<div align="center"><label for="userName">User Name</label></div>
			<div align="center"><input id="userName" name="userName" value="${fn:escapeXml(param.userName)}"></div>
		</p>
		<p>
			<div align="center"><input type="submit"></div>
			<br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<br/>
	<h1>Matching Collections</h1>
        <table border="1">
            <tr>
                <th>Collection Id</th>
                <th>Collection Name</th>
                 <th>User Name</th>               
            </tr>
            <c:forEach items="${collections}" var="collections" >
                <tr>
                    <td><c:out value="${collections.getCollectionId()}" /></td>
                    <td><c:out value="${collections.getCollectionName()}" /></td>
                    <td><c:out value="${collections.getUser().getUserName()}" /></td>
                 </tr>
            </c:forEach>
       </table>
       </div>
       <footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>
