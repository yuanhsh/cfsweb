

<jsp:include page="template-bottom.jsp" />


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Transaction History </h3>

<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="TransactionHistory.do">
    <table>
	
		

			<tr>
				
					<th>CustomerID:${transactionList.customer_id}</th><th>transaction ID: ${transactionList.transaction_Id}</th> <th> transaction type:${transactionList.transaction_type}</th><th> transaction status:${transactionList.status} <th>Price: ${transactionList.price} </th> 
					<th>Price-date: ${transactionList.execuate_date}</th>
				
			</tr>
		
	
</table>
</form>
</div>
 
</div>
 
 
 
<jsp:include page="template-bottom.jsp" />