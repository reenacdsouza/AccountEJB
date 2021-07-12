<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MultiCurrency Accounts</title>
<link href="assets/webexchange.css" rel="stylesheet">
</head>
<body>
	<div>
		<h1 class="title">MultiCurrency Accounts</h1>
	</div>
	<ul class="nav navend">
		<li><a
			href="<%=request.getContextPath()%>/Dashboard?action=dashboard">Dashboard</a></li>
		<li><a
			href="<%=request.getContextPath()%>/Transactions?action=transactions">Transactions</a></li>
		<li><a href="<%=request.getContextPath()%>/Payees?action=payees">Payees</a></li>
		<li><a
			href="<%=request.getContextPath()%>/Dashboard?action=profile">Profile</a></li>
		<li><a href="<%=request.getContextPath()%>/Logout?action=logout">Logout</a></li>
	</ul>
</body>
</html>