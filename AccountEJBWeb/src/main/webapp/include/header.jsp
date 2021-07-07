<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Header</title>
<link href="assets/webexchange.css" rel="stylesheet">
</head>
<body>
	<header>
	<div align=center>
	<h1>webXchange</h1>
	</div>
	<div align=right>
	<form action="<%=request.getContextPath()%>/Dashboard" method="post">
		<input type="hidden" name="action" value="logout">
		<button type="submit">Logout</button>
	</form>
	</div>
	</header>
	<ul class="hnav">
  <li><a href="#">Dashboard</a></li>
  <li><a href="#">Transactions</a></li>
  <li><a href="#">Payees</a></li>
  <li><a href="#">Profile</a></li>
</ul>
</body>
</html>