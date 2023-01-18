<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Book</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="findbooks" method="post">
		<h1>Search for a Book by title</h1>
		<p>
			<div align="center"><label for="title">Title</label></div>
			<div align="center"><input id="title" name="title" value="${fn:escapeXml(param.title)}"></div>
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<br/>
	<h1>Matching Books</h1>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>description</th>
            	<th>authors</th>
            	<th>categories</th>
                <th>publishedDate</th>
                <th>ratingsCount</th>
               
            </tr>
            <c:forEach items="${books}" var="books" >
                <tr>
                    <td><c:out value="${books.getTitle()}" /></td>
                    <td><c:out value="${books.getDescription()}" /></td>
                   	<td><c:out value="${books.getAuthors()}" /></td>
                   	<td><c:out value="${books.getCategories()}" /></td>
                    <td><fmt:formatDate value="${books.getPublishedDate()}" pattern="yyyy-MM-dd"/></td>
                   <td><c:out value="${books.getRatingsCount()}" /></td>
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
