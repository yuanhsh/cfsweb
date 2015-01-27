<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />


<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action="emp_transition_day.do"> 									
		<table border="1">
		
		<tr> 
		<td valign="top"> Enter Transition Date :<input type="date" name="date"> </td>	
		</tr>	
			<tr>
				<td valign="top">Fund Name </td> 	
				<td valign="top">New Price </td>  
			</tr>
		<c:forEach items="${fundList}" var="fund">
			<tr>
				<td>${fund.name} </td> 	
				<td>${fund.price} </td> 
				<td><input type="text" name="fundPrice"><input type="hidden" name="fundId" value="${fund.fund_id}"/>  </td> 
				
				
			</tr>
			
			</c:forEach>
			<tr>
			<td><input type="submit" value="Submit"> </td>
			</tr>
		</table>
	</form>
</p>


<jsp:include page="template-bottom.jsp" />
