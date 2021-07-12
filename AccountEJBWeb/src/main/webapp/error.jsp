<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="include/header.jsp">
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
			Error: <% 
			if(request.getAttribute("error") != null){
			out.println(request.getAttribute("error"));
			}else if(request.getAttribute("failure") != null){
				out.println(request.getAttribute("failure"));
			}
			%>
		</p>
	</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd. multiCurrencyXchange</footer>
</div>