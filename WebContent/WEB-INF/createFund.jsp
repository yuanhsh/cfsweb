<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Create fund</h3>

<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="createFund.do">
    <fieldset>
        <div class="form-group">
            <label for="inputvpassword" class="col-lg-4 control-label">Fund ID:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputVpassword"value="" placeholder="Fund ID">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Fund Name:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="newPassword" value="" placeholder="Fund Name">
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-lg-6 col-lg-offset-4">
                
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
 
</div>
 
 
 
<jsp:include page="template-bottom.jsp" />


