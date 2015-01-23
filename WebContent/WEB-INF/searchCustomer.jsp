<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Search Customer </h3>

<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="searchCustomer.do">
    <fieldset>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Customer Name:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="newPassword" value="" placeholder="Customer name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Customer ID:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="confirmPassword" value="" placeholder="Customer ID">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-6 col-lg-offset-4">
                
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
<div class="container">
    		<div class="bs-docs-section">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 id="tables">Customer List</h3>
                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>
                                        <th>User Id</th>
                                        <th>User Name</th>
                                        <th>Customer's name</th>
                                        <th>Cash Balance</th>
                                        <th>Date of the last trading day</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>happy</td>
                                        <td>John Boo</td>
                                        <td>300</td>
                                        <td>01/14/2015</td>
                                        <td><li style=" list-style:none" class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">view account</a></li>
                    <li><a href="javascript:void(0)">view transactions</a></li>
                    <li><a href="javascript:void(0)">reset password</a></li>
                    <li><a href="javascript:void(0)">deposit check</a></li>                  
                </ul>
            </li></td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>zhsi</td>
                                        <td>Michael Robinson</td>
                                        <td>500</td>
                                        <td>01/14/2015</td>
                                        <td>
    
    <li  style=" list-style:none"class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">view account</a></li>
                    <li><a href="javascript:void(0)">view transactions</a></li>
                    <li><a href="javascript:void(0)">reset password</a></li>
                    <li><a href="javascript:void(0)">deposit check</a></li>                  
                </ul>
            </li>
</td>
                                        
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>kidj</td>
                                        <td>Jennifer Pinsker</td>
                                        <td>700</td>
                                        <td>01/13/2015</td>
                                       <td><li style=" list-style:none"class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">view account</a></li>
                    <li><a href="javascript:void(0)">view transactions</a></li>
                    <li><a href="javascript:void(0)">reset password</a></li>
                    <li><a href="javascript:void(0)">deposit check</a></li>                  
                </ul>
            </li></td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>djsjs</td>
                                        <td>Bob Robson</td>
                                        <td>600</td>
                                        <td>01/13/2015</td>
                                        <td><li style=" list-style:none"class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">view account</a></li>
                    <li><a href="javascript:void(0)">view transactions</a></li>
                    <li><a href="javascript:void(0)">reset password</a></li>
                    <li><a href="javascript:void(0)">deposit check</a></li>                  
                </ul>
            </li></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>shdd</td>
                                        <td>Alexander Robson</td>
                                        <td>200</td>
                                        <td>01/11/2015</td>
                                        <td><li style=" list-style:none" class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">view account</a></li>
                    <li><a href="javascript:void(0)">view transactions</a></li>
                    <li><a href="javascript:void(0)">reset password</a></li>
                    <li><a href="javascript:void(0)">deposit check</a></li>                  
                </ul>
            </li></td>
                                        
                                    </tr>
                                </tbody>
                            </table>
                            <ul class="pagination">
                                <li class="disabled"><a href="javascript:void(0)">«</a></li>
                                <li class="active"><a href="javascript:void(0)">1</a></li>
                                <li><a href="javascript:void(0)">2</a></li>
                                <li><a href="javascript:void(0)">3</a></li>
                                <li><a href="javascript:void(0)">4</a></li>
                                <li><a href="javascript:void(0)">5</a></li>
                                <li><a href="javascript:void(0)">»</a></li>
                            </ul>
        
                    
                </div>
            </div>

    	</div>
 
</div>
 
 
 
<jsp:include page="template-bottom.jsp" />