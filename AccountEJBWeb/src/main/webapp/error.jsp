<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="include/header.jsp">
	<c:param name="title" value="Error" />
</c:import>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<div class="wrapper">
	<header class="header">Not Found</header>
	<article class="main">
		<p>
			404 Not Found 
			Error: <% out.println(request.getAttribute("error"));%>
		</p>
	</article>
	<footer class="footer">©2021 Web Exchange Ltd. webXchange</footer>
</div>