<jsp:include page="template-top.jsp" />


<jsp:include page="error-list.jsp" />


   <c:forEach items="${fundList}" var="fund">

<p>
	<form method="post" action="search_funds.do"> 									
		<table>
			<tr>
				<td>${fund.name} </td> 	
				<td>${fund.symbol} </td>
				<td>${fund.price} </td> 
			</tr>
			
			
		</table>
	</form>
</p>

</c:forEach>
<jsp:include page="template-bottom.jsp" />
