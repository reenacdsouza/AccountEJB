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
	<article class="main">
		<div class="header">
			<p>Profile Details</p>
		</div>
		<table>
			<tr>
				<td>Customer Id</td>
				<td><c:out value="${custId}" /></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><c:out value="${username}" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><c:out value="${fname}" /> <c:out value=" " /> <c:out
						value="${lname}" /></td>
			</tr>
			<tr>
				<td>Passport Number</td>
				<td><c:out value="${passnumber}" /></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><c:out value="${phone}" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><c:out value="${email}" /></td>
			</tr>
		</table>
	</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</div>