<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search reviews for a Book</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="readreview" method="post">
		<h1>Search reviews for a Book</h1>
		<p>
			<div align="center"><label for="title">Book Title</label></div>
			<div align="center"><input id="title" name="title" value="${fn:escapeXml(param.title)}"></div>
		</p>
		<p>
			<div align="center"><input type="submit"></div>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<br/>
	<h1>Reviews for a Book</h1>
        <table border="1">
            <tr>
            	<th>Review Id</th>
                <th>Title</th>
                <th>Username</th>
            	<th>Score</th>
            	<th>Short Summary</th>
                <th>Summary</th>
                <th>Helpfulness</th>
               
            </tr>
            <c:forEach items="${reviews}" var="reviews" >
                <tr>
                	<td><c:out value="${reviews.getReviewId()}" /></td>
                    <td><c:out value="${reviews.getBook().getTitle()}" /></td>
                    <td><c:out value="${reviews.getUser().getUserName()}" /></td>
                   	<td><c:out value="${reviews.getScore()}" /></td>
                   	<td><c:out value="${reviews.getShortSummary()}" /></td>
                    <td><c:out value="${reviews.getSummary()}" /></td>
                   <td><c:out value="${reviews.getHelpfulness()}" /></td>
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
