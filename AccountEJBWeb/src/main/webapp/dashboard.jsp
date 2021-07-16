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
			<h2>
				<c:out value="${fname}" />
				<c:out value=" " />
				<c:out value="${lname}" />
			</h2>
		</div>
		<div>
			<h3 class="bodytitle">Account Balances</h3>
		</div>
		<div class="table">
			<div class="thead">
				<div class="tr">
					<div class="td">Account Number</div> 
					<div class="td">Currency</div> 
					<div class="td">Account Balance</div> 
					<div class="td">Actions</div>
				</div>
			</div>
			<div class="tbody">
			<c:forEach var="custAccMap" items="${accSet}">
				<form class="tr" action="<%=request.getContextPath()%>/Dashboard?action=loadtransaction" method="post">
					<div class="td"><c:out value="${custAccMap.accountNumber}" /></div>
					<input type="hidden" name="custAccountNumber" value="${custAccMap.accountNumber}" />
					<div class="td"><c:out value="${custAccMap.accountType}" /></div>
					<input type="hidden" name="custAccountType" value="${custAccMap.accountType}" />
					<div class="td"><c:out value="${custAccMap.accountBalance}" /></div>
					<input type="hidden" name="custAccountBalance" value="${custAccMap.accountBalance}" />
					<div class="td">
						<span><button name="transaction" type="submit" value="deposit">Deposit</button></span>
						<span><button name="transaction" type="submit" value="withdraw">Withdraw</button></span>
						<span><button name="transaction" type="submit" value="transfer">Transfer</button></span>
					</div>
				</form>
			</c:forEach>
			</div>
		</div>
	</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</div>