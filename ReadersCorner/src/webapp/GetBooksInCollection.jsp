<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Books in a Collection</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
<div align="center">
	<form action="getbooksincollection" method="post">
		<h1>Get Books in a BookCollection</h1>
		<p>
			<div align="center"><label for="collectionId">BookCollectionId</label></div>
			<div align="center"><input id="collectionId" name="collectionId" value="${fn:escapeXml(param.collectionId)}"></div>
		</p>
		<p>
			<div align="center"><input type="submit"></div>
			<br/><br/><br/>
			<div align="center"><span id="successMessage"><b>${messages.success}</b></span></div>
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
                    <td><c:out value="${books.getTitle()}" /></td>                    
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