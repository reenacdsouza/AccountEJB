<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="include/header.jsp">
	<c:param name="title" value="Payees" />
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
			<caption>Payees</caption>
			<tr>
				<th>Payee Name</th>
				<th>Branch Name</th>
				<th>Sort Code</th>
				<th>Account Number</th>
			</tr>
			<c:forEach var="custPayeeMap" items="${payeeList}">
			<tr>
				<td><c:out value="${custPayeeMap.name}"/></td>
				<td><c:out value="${custPayeeMap.branchName}"/></td>
				<td><c:out value="${custPayeeMap.sortCode}"/></td>
				<td><c:out value="${custPayeeMap.accountNumber}"/></td>
			</tr>
			</c:forEach>
		</table>
	</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd. multiCurrencyXchange</footer>
</div>