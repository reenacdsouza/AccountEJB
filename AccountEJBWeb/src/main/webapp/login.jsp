<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link href="assets/webexchange.css" rel="stylesheet">
</head>
<body>
	<%
	if (request.getAttribute("error") != null) {
		out.println(request.getAttribute("error"));
	} else if (request.getAttribute("failure") != null) {
		out.println(request.getAttribute("failure"));
	}
	%>
	<div>
		<h1 class="title">MultiCurrency Accounts</h1>
	</div>
	<div align=center>
		<form action="<%=request.getContextPath()%>/Login" method="post">
			<div>
				<b>Username</b>
			</div>
			<br/>
			<div>
				<input maxlength="12" size="12" type="text" name="username" required>
			</div>
			<br />
			<div>
				<b>Password</b>
			</div>
			<br />
			<div>
				<input maxlength="12" size="12" type="password" name="password" required>
			</div>
			<br />
			<div>
				<input type="hidden" name="action" value="loginCheck">
			</div>
			<div>
				<button type="submit">Login</button>
			</div>
		</form>
	</div>
	<br/>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</body>
</html>