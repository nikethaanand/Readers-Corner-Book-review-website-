<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Home Page</title>
<style>
	body {
	  background-color: DarkSalmon;
	  color: white;
	}
</style>
</head>
<body>
	<header align="center">
		<h1>READER'S CORNER</h1>
<h3>Users Page</h3>
	</header>
	<div align="center">
		<form action="readerusercreate">
			<p>
				<input type="submit" value ="Sign Up As Reader">
			</p>
		</form>
		<form action="userdetailspage">
			<p>
				<input type="submit" value ="Reader">
			</p>
		</form>
		<form action="userbookspage">
			<p>
				<input type="submit" value ="Books">
			</p>
		</form>
 		<form action="userreadlistpage">
			<p>
				<input type="submit" value ="ReadList">
			</p>
		</form>
		<form action="usercollectionspage">
			<p>
				<input type="submit" value ="Collections">
			</p>
		</form>
		<form action="userpostspage">
			<p>
				<input type="submit" value ="Posts">
			</p>
		</form>
		<br/><br/>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</div>
	<footer>
	<div align="center">
	<br>
		 <p>© Reader's Corner 2022</p>
	</div>
	</footer>
</body>
</html>