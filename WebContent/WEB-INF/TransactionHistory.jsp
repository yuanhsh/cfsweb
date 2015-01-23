<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<table>
	<c:forEach items="${history}" var="transactionList">
		<tr>

			<td valign="top">
				<form method="POST" action="TransactionHistory.do">
					CustomerID:${transactionList.customer_id}<br> transaction ID: ${transactionList.transaction_Id} <br> transaction type:${transactionList.transaction_type}<br> transaction status:${transactionList.status} <br>Price: ${transactionList.price}  <br>
					Price-date: ${transactionList.execuate_date}<br>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
