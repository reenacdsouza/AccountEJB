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
			<h3 class="bodytitle">Withdraw Money</h3>
		</div>
		<div>
			<form
				action="<%=request.getContextPath()%>/Dashboard?action=createtransaction"
				method="post">
				<div class="table">
					<div class="tbody">
						<div class="tr">
							<div class="td">Account Number</div>
							<div class="td">
								<c:out value="${transactionMap.custAccountNumber}" />
							</div>
							<input type="hidden" name="account_number"
								value="${transactionMap.custAccountNumber}" />
						</div>
						<div class="tr">
							<div class="td">Account Type</div>
							<div class="td">
								<c:out value="${transactionMap.custAccountType}" />
							</div>
							<input type="hidden" name="account_type"
								value="${transactionMap.custAccountType}" />
						</div>
						<div class="tr">
							<div class="td">Account Balance</div>
							<div class="td">
								<c:out value="${transactionMap.custAccountBalance}" />
							</div>
							<input type="hidden" name="account_balance"
								value="${transactionMap.custAccountBalance}" />
						</div>
					</div>
				</div>
				<br />
				<div>
					<label for="amount">Withdraw Amount</label>
				</div>
				<br />
				<div>
					<input type="number" min="1.00"
						max="${transactionMap.custAccountBalance}" value="1.00" step=".01"
						id="amount" name="amount" required />
				</div>
				<div>
					<input type="hidden" name="transaction_type" id="transaction_type"
						value="withdraw" />
				</div>
				<br />
				<button type="submit">Submit</button>
			</form>
			<br /> 
			<br />
			<%
			Map<String, String> tranMap = (Map<String, String>) request.getAttribute("transactionMap");
			if (tranMap.get("success") != null) {
			%>
			<div class="success">
				<c:out value="${transactionMap.success}" />
			</div>
			<%
			} else if (tranMap.get("failure") != null) {
			%>
			<div class="failure">
				<c:out value="${transactionMap.failure}" />
			</div>
			<%
			}
			%>
		</div>
	</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</div>