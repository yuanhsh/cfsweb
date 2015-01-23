<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<table>
	<c:forEach items="${transactionList}" var="transactionList">
		<tr>

			<td valign="top">
				<form method="POST" action="csearch_transaction.do">
					<input type="hidden" name="fundId" value="${transactionList.transaction_Id}" /> <br> Price: ${transactionList.price}  <br>
					Price-date: ${transactionList.execuate_date}<br>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
