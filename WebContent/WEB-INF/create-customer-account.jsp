<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
 <div class="mainContent">


    <h3 style="text-align: center">Create an Account for customer</h3>
<jsp:include page="error-list.jsp" />
<p style="font-size:medium">${message}</p>

<form class="form-horizontal" method="post" action="create-customer-account.do">
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
                 <option value="0">Please select your state</option>
                    <option value="AL">Alabama</option>
    <option name="state" value="Alaska">Alaska</option>
    <option name="state" value="Alaska">Alaska</option>
    <option name="state" value="Arkansas">Arkansas</option>
    <option name="state" value="California">California</option>
    <option name="state" value="Colorado">Colorado</option>
    <option name="state" value="Connecticut">Connecticut</option>
    <option name="state" value="Delaware">Delaware</option>
    <option name="state" value="District Of Columbia">District Of Columbia</option>
    <option name="state" value="Florida">Florida</option>
    <option name="state" value="Georgia">Georgia</option>
    <option name="state" value="Hawaii">Hawaii</option>
    <option name="state" value="Idaho">Idaho</option>
    <option name="state" value="Illinois">Illinois</option>
    <option name="state" value="Indiana">Indiana</option>
    <option name="state" value="Iowa">Iowa</option>
    <option name="state" value="Kansas">Kansas</option>
    <option name="state"value="Kentucky">Kentucky</option>
    <option name="state"value="Louisiana">Louisiana</option>
    <option name="state"value="Maine">Maine</option>
    <option name="state"value="Maryland">Maryland</option>
    <option name="state"value="Massachusetts">Massachusetts</option>
    <option name="state"value="Michigan">Michigan</option>
    <option name="state"value="Minnesota">Minnesota</option>
    <option name="state"value="Mississippi">Mississippi</option>
    <option name="state"value="Missouri">Missouri</option>
    <option name="state"value="Montana">Montana</option>
    <option name="state"value="Nebraska">Nebraska</option>
    <option name="state"value="Nevada">Nevada</option>
    <option name="state"value="New Hampshire">New Hampshire</option>
    <option name="state"value="New Jersey">New Jersey</option>
    <option name="state"value="New Mexico">New Mexico</option>
    <option name="state"value="New York">New York</option>
    <option name="state"value="North Carolina">North Carolina</option>
    <option name="state"value="North Dakota">North Dakota</option>
    <option name="state"value="Ohio">Ohio</option>
    <option name="state"value="Oklahoma">Oklahoma</option>
    <option name="state"value="Oregon">Oregon</option>
    <option name="state"value="Pennsylvania">Pennsylvania</option>
    <option name="state"value="Rhode Island">Rhode Island</option>
    <option name="state"value="South Carolina">South Carolina</option>
    <option name="state"value="South Dakota">South Dakota</option>
    <option name="state"value="Tennessee">Tennessee</option>
    <option name="state"value="Texas">Texas</option>
    <option name="state"value="Utah">Utah</option>
    <option name="state"value="Vermont">Vermont</option>
    <option name="state"value="Virginia">Virginia</option>
    <option name="state"value="Washington">Washington</option>
    <option name="state"value="West Virginia">West Virginia</option>
    <option name="state" value="Wisconsin">Wisconsin</option>
    <option name="state" value="Wyoming">Wyoming</option>
                </select>
            </div>
          </div>  
        <div class="form-group">
            <div class="col-lg-7 col-lg-offset-5">
                <button class="btn btn-default">Cancel</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
</div>

<jsp:include page="template-bottom.jsp" />