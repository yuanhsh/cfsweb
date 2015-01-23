<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<table>
	<c:forEach items="${check}" var="checks">
		<tr>

			<td valign="top">
				<form method="POST" action="depositCheck.do">
					Customer ID: ${checks.customerID} <br> Cash:${checks.cash} <br> 
					
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="template-bottom.jsp" />