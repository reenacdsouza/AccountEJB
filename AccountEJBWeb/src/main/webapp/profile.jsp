<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="include/header.jsp">
	<c:param name="title" value="Profile" />
</c:import>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<div class="wrapper">
	<header class="header">Profile</header>
	<article class="main">
		<p>
			Username:
			<%=request.getSession().getAttribute("username")%>
			First Name:
			<%=request.getSession().getAttribute("first_name")%>
			<br /> <br /> <a
				href="<%=request.getContextPath()%>/Dashboard?action=showAll">Show
				All Accounts.</a> <br />
		</p>
	</article>
	<footer class="footer">©2021 Web Exchange Ltd. webXchange</footer>
</div>