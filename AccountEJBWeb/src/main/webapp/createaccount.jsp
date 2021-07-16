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
		<div align=center>
			<form
				action="<%=request.getContextPath()%>/Dashboard?action=createaccount"
				method="post">
				<div>
					<b>Create Account</b>
				</div>
				<br /> 
				<label for="type">Select Account Type: </label> 
				<select name="type" id="type" required>
					<option selected="selected" value="British Pound (GBP)">British Pound (GBP)</option>
					<option value="United States Dollar (USD)">United States
						Dollar (USD)</option>
					<option value="Euro (EUR)">Euro (EUR)</option>
				</select>
				<br />
				<br/> 
				<label for="dep">Deposit: </label> 
				<input type="number" min="1.00" value="0.00" step=".01" id="dep" name="dep" required>
				<br />
				<br />
				<button type="submit">Submit</button>
			</form>
			<%
			if (request.getAttribute("success") != null) {
				out.println(request.getAttribute("success"));
			} else if (request.getAttribute("failure") != null) {
				out.println(request.getAttribute("failure"));
			}
			%>
		</div>
	</article>
	<footer class="footer">©2021 MultiCurrency Accounts Ltd.
		multiCurrencyXchange</footer>
</div>