<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<table>
	<c:forEach items="${searchResult}" var="customerinfo">
		<tr>

			<td valign="top">
				<form method="POST" action="search_customer.do">
					<input type="hidden" name="customerId" value="${customerinfo.customer_id}" /> <input
						type="hidden" value="customer" /> Price: ${customerinfo.customers} <br>
					<br>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="template-bottom.jsp" />