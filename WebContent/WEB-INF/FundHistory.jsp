<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<table>
	<c:forEach items="${fundInfoList}" var="fundInfo">
		<tr>

			<td valign="top">
				<form method="POST" action="view_fund_info.do">
					<input type="hidden" name="fundId" value="${fundInfo.fundId}" /> <input
						type="Buy" value="X" /> Price: ${fundInfo.price} } <br>
					Price-date: ${fundInfo.price_date}<br>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="template-bottom.jsp" />