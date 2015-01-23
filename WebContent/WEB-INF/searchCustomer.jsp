<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<table>
	<c:forEach items="${searchCustomer}" var="customerinfo">
		<tr>

			<td valign="top">
				<form method="POST" action="searchCustomer.do">
					Customer ID: ${customerinfo.customer_id} <br> Customer Name: ${customerinfo.customers} <br>
					<br>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="template-bottom.jsp" />