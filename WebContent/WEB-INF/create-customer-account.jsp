<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
 <div class="mainContent">


    <h3 style="text-align: center">Create an account for customer</h3>
<jsp:include page="error-list.jsp" />
<jsp:include page="success.jsp" />

<form class="form-horizontal" method="post" action="emp_create_cust_account.do">
    <fieldset>
        <div class="form-group">
            <label for="inputusername" class="col-lg-4 control-label">User Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputusername" name="userName" value="${form.userName}" placeholder="User Name">
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
            <label for="inputaddress1" class="col-lg-4 control-label">Address line1:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputaddress1" name="addressl1" value="${form.addressl1}"placeholder="Address line1">
            </div>
        </div>
         <div class="form-group">
            <label for="inputaddress2" class="col-lg-4 control-label">Address line2:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputaddress2"name="addressl2"value="${form.addressl2 }" placeholder="Address line2">
            </div>
        </div>
         <div class="form-group">
            <label for="inputcity" class="col-lg-4 control-label">City:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputcity" name="city"value="${form.city }" placeholder="City">
            </div>
        </div>
        <div class="form-group">
            <label for="inputzip" class="col-lg-4 control-label">Zip:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputzip" name="zip" value="${form.zip }" placeholder="Zip">
            </div>
        </div>
         <div class="form-group">
            <label for="select" class="col-lg-4 control-label">State:</label>
            <div class="col-lg-5">
                <select class="form-control" id="select" name="state" >
                 <option value="null">Please select your state</option>
                    <option value="Alabama">Alabama</option>
    <option  value="Alaska">Alaska</option>
    <option  value="Alaska">Alaska</option>
    <option  value="Arkansas">Arkansas</option>
    <option  value="California">California</option>
    <option  value="Colorado">Colorado</option>
    <option  value="Connecticut">Connecticut</option>
    <option  value="Delaware">Delaware</option>
    <option value="District Of Columbia">District Of Columbia</option>
    <option  value="Florida">Florida</option>
    <option  value="Georgia">Georgia</option>
    <option  value="Hawaii">Hawaii</option>
    <option  value="Idaho">Idaho</option>
    <option  value="Illinois">Illinois</option>
    <option  value="Indiana">Indiana</option>
    <option  value="Iowa">Iowa</option>
    <option  value="Kansas">Kansas</option>
    <option value="Kentucky">Kentucky</option>
    <option value="Louisiana">Louisiana</option>
    <option value="Maine">Maine</option>
    <option value="Maryland">Maryland</option>
    <option value="Massachusetts">Massachusetts</option>
    <option value="Michigan">Michigan</option>
    <option value="Minnesota">Minnesota</option>
    <option value="Mississippi">Mississippi</option>
    <option value="Missouri">Missouri</option>
    <option value="Montana">Montana</option>
    <option value="Nebraska">Nebraska</option>
    <option value="Nevada">Nevada</option>
    <option value="New Hampshire">New Hampshire</option>
    <option value="New Jersey">New Jersey</option>
    <option value="New Mexico">New Mexico</option>
    <option value="New York">New York</option>
    <option value="North Carolina">North Carolina</option>
    <option value="North Dakota">North Dakota</option>
    <option value="Ohio">Ohio</option>
    <option value="Oklahoma">Oklahoma</option>
    <option value="Oregon">Oregon</option>
    <option value="Pennsylvania">Pennsylvania</option>
    <option value="Rhode Island">Rhode Island</option>
    <option value="South Carolina">South Carolina</option>
    <option value="South Dakota">South Dakota</option>
    <option value="Tennessee">Tennessee</option>
    <option value="Texas">Texas</option>
    <option value="Utah">Utah</option>
    <option value="Vermont">Vermont</option>
    <option value="Virginia">Virginia</option>
    <option value="Washington">Washington</option>
    <option value="West Virginia">West Virginia</option>
    <option  value="Wisconsin">Wisconsin</option>
    <option  value="Wyoming">Wyoming</option>
                </select>
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