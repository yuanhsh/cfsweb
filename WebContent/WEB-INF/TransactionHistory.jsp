

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Transaction History </h3>

<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="transactionHistory.do">
    <fieldset>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Customer ID:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="newPassword" value="" placeholder="Customer ID">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Transaction Type:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="confirmPassword" value="" placeholder="Check">
            </div>
        </div>

    </fieldset>
</form>
</div>
 
</div>
 
 
 
<jsp:include page="template-bottom.jsp" />