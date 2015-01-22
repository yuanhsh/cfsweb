<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<table>
	<c:forEach items="${fund}" var="funds">
		<tr>

			<td valign="top">
				<form method="POST" action="createFund.do">
					<input type="text" name="fundname" value="${funds.name}" /> <input
						type="text" value="fundid" /> FundID: ${fund.id} } <br>
					<br>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="template-bottom.jsp" />