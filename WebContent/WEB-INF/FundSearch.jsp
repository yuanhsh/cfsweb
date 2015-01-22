<jsp:include page="template-top.jsp" />


<jsp:include page="error-list.jsp" />


   <c:forEach items="${fundList}" var="fund">

<p>
	<form method="post" action="search_funds.do"> 										// is the action name correct
		<table>
			<tr>
				<td><a><href="search_fund.do?id=${fund.fundId}">${fund.name}</a> </td> 	// is this correct
				<td><input type="text" name="email" /></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Login"/>
				</td>
			</tr>
		</table>
	</form>
</p>

</c:forEach>
<jsp:include page="template-bottom.jsp" />
