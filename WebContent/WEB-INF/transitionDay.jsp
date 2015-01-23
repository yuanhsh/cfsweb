<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />


<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action="transition_day.do"> 									
		<table border="1">
		
			<tr>
				<td valign="top">Fund Name </td> 	
				<td valign="top">  Fund Ticker </td>
				<td valign="top">Current Price </td>
				<td valign="top"> Price </td>  
			</tr>
		<c:forEach items="${fundList}" var="fund">
			<tr>
				<td>${fund.name} </td> 	
				<td>${fund.symbol} </td>
				<td>${fund.price} </td> 
				<td><input type="text"> </td> 
				
			</tr>
			
			</c:forEach>
			<tr>
			<td><input type="submit" value="Transition"> </td>
			</tr>
		</table>
	</form>
</p>


<jsp:include page="template-bottom.jsp" />
