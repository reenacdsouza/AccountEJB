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
		<table>
			<caption>Account Balances</caption>
			<tr>
				<th>Account</th>
				<th>Balance</th>
			</tr>
			<c:forEach var="custAccMap" items="${accList}">
				<tr>
					<td><c:out value="${custAccMap.accountType}" /></td>
					<td><c:out value="${custAccMap.accountBalance}" /></td>
				</tr>
			</c:forEach>
		</table>
	</article>
	<ul class="nav navbegin">
		<li><a
			href="<%=request.getContextPath()%>/Dashboard?action=dashboard">Add Account</a></li>
		<li><a
			href="<%=request.getContextPath()%>/Transactions?action=transactions">Transfer</a></li>
		<li><a href="<%=request.getContextPath()%>/Payees?action=payees">Withdraw</a></li>
		<li><a
			href="<%=request.getContextPath()%>/Dashboard?action=profile">Deposit</a></li>
	</ul>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</div>