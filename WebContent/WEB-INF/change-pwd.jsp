<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="success.jsp" />
<form class="form-horizontal"  method="POST" action="change-pwd.do">
    <fieldset>
        <div class="form-group">
            <label for="inputvpassword" class="col-lg-4 control-label">Old Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputVpassword" name="oldPassword" placeholder="Old Password" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">New Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="newPassword" placeholder="New Password" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputcpassword" class="col-lg-4 control-label">Confirm New Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputcpassword" name="confirmPassword" placeholder="Confirm New Password" value=""/>
            </div>
        </div>
     
        <div class="form-group">
            <div class="col-lg-6 col-lg-offset-4">
                <button class="btn btn-default btn-request-check" onClick="javascript:history.back()" type="Button">Cancel</button>
                <button type="submit" class="btn btn-primary" name="button">Submit</button>
                
            </div>
        </div>
    </fieldset>
</form>


<jsp:include page="template-bottom.jsp" />
