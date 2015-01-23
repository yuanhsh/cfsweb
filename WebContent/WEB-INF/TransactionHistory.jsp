

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Transaction History </h3>

<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="transactionHistory.do">
 <table border="1"><tr><th>Customer ID</th> <th>Transaction amount</th><th>transaction type</th><th>transaction day</th><th>transaction status</th></tr>
 </table>
</form>
</div>
 
</div>
 
 
 
<jsp:include page="template-bottom.jsp" />