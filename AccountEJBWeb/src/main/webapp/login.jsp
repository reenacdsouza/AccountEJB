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
		<div align=center>
		<form action="<%=request.getContextPath()%>/Login" method="post">
			<h1 class="title">webXchange</h1>
			<b>Username</b><br /> <input type="text" name="username" required><br />
			<b>Password</b><br /> <input type="password" name="password"
				required><br /> <br /> <input type="hidden" name="action"
				value="loginCheck">
			<button type="submit">Login</button>
		</form>
		</div>
		<footer class="footer">©2021 Web Exchange Ltd. webXchange</footer>
</body>
</html>