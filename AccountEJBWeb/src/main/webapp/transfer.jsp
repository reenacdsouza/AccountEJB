<%@ page import="java.io.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="include/header.jsp">
</c:import>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<div class="wrapper">
	<article class="main">
		<div class="header">
			<p>
				<c:out value="${fname}" />
				<c:out value=" " />
				<c:out value="${lname}" />
			</p>
		</div>
		</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</div>