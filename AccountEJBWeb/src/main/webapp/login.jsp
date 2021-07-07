<html>
<head>
<title>Login</title>
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
			<h1>Log in</h1>
			<b>Username</b><br /> <input type="text" name="username" required><br />
			<b>Password</b><br /> <input type="password" name="password"
				required><br /> <br /> <input type="hidden" name="action"
				value="loginCheck">
			<button type="submit">Login</button>
		</form>
		</div>
</body>
</html>