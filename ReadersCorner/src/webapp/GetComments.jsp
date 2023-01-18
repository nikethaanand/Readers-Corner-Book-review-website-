<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Comments of Post</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="getcomments" method="post">
		<h1>Get Comments of a Post</h1>
		<p>
			<label for="recommendationId">Recommendation Id</label>
			<input id="recommendationId" name="recommendationId" value="${fn:escapeXml(param.recommendationId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<br/>
	<h1>Matching comments</h1>
        <table border="1">
            <tr>
            	<th>UserName</th>
                <th>Comments</th>
            </tr>
            <c:forEach items="${comments}" var="comments" >
                <tr>
                	<td><c:out value="${comments.getUser().getUserName()}" /></td>   
                    <td><c:out value="${comments.getComment()}" /></td>                    
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