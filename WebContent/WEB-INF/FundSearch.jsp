<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />


<jsp:include page="error-list.jsp" />


   

<p>
	<form method="post" action="search_funds.do"> 									
		<table>
		<c:forEach items="${fundList}" var="fund">
			<tr>
				<td>${fund.name} </td> 	
				<td>${fund.symbol} </td>
				<td>${fund.price} </td> 
			</tr>
			
			</c:forEach>
		</table>
	</form>
</p>


<jsp:include page="template-bottom.jsp" />
