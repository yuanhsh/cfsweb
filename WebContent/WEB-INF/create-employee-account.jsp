<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<div class="mainContent">


    <h3 style="text-align: center">Create an account for employee</h3>

<jsp:include page="error-list.jsp" />
<jsp:include page="success.jsp" />
<form class="form-horizontal" method="post" action="emp_create_emp_account.do">
    <fieldset>
        <div class="form-group">
            <label for="inputusername" class="col-lg-4 control-label">User Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputusername" name="userName" value="${form.userName}" placeholder="User Name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputfirstname" class="col-lg-4 control-label">First Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputfname" name="firstName" value="${form.firstName}" placeholder="First Name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputulastname" class="col-lg-4 control-label">Last Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputlname" name="lastName" value="${form.lastName}" placeholder="Last Name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Password:</label>
            <div class="col-lg-5">
                <input type="Password" class="form-control" id="inputnpassword" name="password" value="" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label for="inputfpassword" class="col-lg-4 control-label">Confirm Password:</label>
            <div class="col-lg-5">
                <input type="Password" class="form-control" id="inputVpassword" name="confirm" value="" placeholder="Confirm Password">
            </div>
        </div>
        
            
        <div class="form-group">
            <div class="col-lg-7 col-lg-offset-5">
                <button class="btn btn-default" onClick="javascript:history.back()" type="Button">Cancel</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
<jsp:include page="template-bottom.jsp" />