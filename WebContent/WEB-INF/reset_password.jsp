<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Reset account</h3>


<form class="form-horizontal">
    <fieldset>
        <div class="form-group">
            <label for="inputvpassword" class="col-lg-4 control-label">Customer name:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputVpassword"value="" placeholder="Customer name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" value="" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Enter Password Again:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" value="" placeholder="Enter Password Again">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-6 col-lg-offset-4">
                
                <button type="submit" class="btn btn-primary">Reset</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
 
</div>
 
 
 
 <jsp:include page="template-bottom.jsp" />