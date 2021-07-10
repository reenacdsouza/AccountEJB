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
	<h1 class="title">webXchange</h1>
	</div>
	<div align=right>
	<form action="<%=request.getContextPath()%>/Logout" method="post">
		<input type="hidden" name="action" value="logout">
		<button type="submit">Logout</button>
	</form>
	</div>
	</header>
	<ul class="nav">
  <li><a href="<%=request.getContextPath()%>/Dashboard?action=dashboard">Dashboard</a></li>
  <li><a href="<%=request.getContextPath()%>/Transactions?action=transactions">Transactions</a></li>
  <li><a href="<%=request.getContextPath()%>/Payees?action=payees">Payees</a></li>
  <li><a href="<%=request.getContextPath()%>/Profile?action=profile">Profile</a></li>
</ul>
</body>
</html>