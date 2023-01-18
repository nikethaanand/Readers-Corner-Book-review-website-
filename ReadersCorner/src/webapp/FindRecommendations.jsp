<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Posts for book</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="findrecommendations" method="post">
		<h1>Find Posts for book</h1>
		<p>
			<div align="center"><label for="title">Book Title</label></div>
			<div align="center"><input id="title" name="title" value="${fn:escapeXml(param.title)}">
			</div>
		</p>
		<p>
			<div align="center"><input type="submit"></div>
			<br/><br/><br/>
			<div align="center"><span id="successMessage"><b>${messages.success}</b></span></div>
		</p>
	</form>
	<br/>
	
	<br/>
	<h1>Matching Posts</h1>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Content</th>
            	<th>Created At</th>
            	<th>Delete</th>        	           
            </tr>
            <c:forEach items="${recommendations}" var="recommendations" >
                <tr>
                    <td><c:out value="${recommendations.getUser().getUserName()}" /></td>
                    <td><c:out value="${recommendations.getContent()}" /></td>
                   	<td><c:out value="${recommendations.getCreated()}" /></td>
                   	<td><a href="recommendationdelete?recommendationId=<c:out value="${recommendations.getRecommendationsId()}"/>&userName=<c:out value="${recommendations.getUser().getUserName()}"/>&bookName=<c:out value="${recommendations.getBook().getTitle()}"/>">Delete</a></td>
      
                </tr>
            </c:forEach>
       </table>
       <footer>
       </div>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>
