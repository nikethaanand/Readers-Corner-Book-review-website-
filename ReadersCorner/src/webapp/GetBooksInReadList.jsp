<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Books in a ReadList</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="getbooksinreadlist" method="post">
		<h1>Get Books in a ReadList</h1>
		<p>
			<label for="userName">userName</label>
			<input id="userName" name="userName" value="${param.userName}">
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
            </tr>
            <c:forEach items="${books}" var="books" >
                <tr>
                    <td><c:out value="${books.getBook().getTitle()}" /></td>                    
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