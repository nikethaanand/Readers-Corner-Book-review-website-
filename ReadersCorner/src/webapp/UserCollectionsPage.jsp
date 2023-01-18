<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Collections Page</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
	<!-- HEADER SECTION -->
	<div align="center">
		<h2>Collections</h2>
		<h3>Users Page</h3>
	</div>
		<div align="center">
		<a href="AddCollections.jsp">Create a New Collection</a>
	</div>
	<div align="center">
		<a href="FindCollections.jsp">Get Collections of user</a>
	</div>


	<div align="center">
		<h2>Books inside a Collection</h2>
	</div>
	<div align="center">
		<a href="AddBookToCollection.jsp">Add a new Book to Collection</a>
	</div>

		<div align="center">
		<a href="GetBooksInCollection.jsp">Get Book from a Collection</a>
	
	</div>
	
   <div align="center">
		<a href="DeleteBookCollection.jsp">Delete a Book from Collection</a></div>
	
	<footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>