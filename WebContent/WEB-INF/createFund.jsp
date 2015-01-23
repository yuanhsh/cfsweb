<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<table>
	<c:forEach items="${fund}" var="funds">
		<tr>

			<td valign="top">
				<form method="POST" action="createFund.do">
					Fund ID: ${funds.fundid} <br> Fund Name:${funds.name} <br> Symbol: ${funds.symbol} <input
						type="button" value="X" /> 
					
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="template-bottom.jsp" />