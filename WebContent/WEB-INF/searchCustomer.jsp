<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

    
   <div class="mainContent">


<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="get" action="emp_search_customer.do">
    <fieldset>
        <div class="form-group">
        <div class="col-lg-3">
           <input type="text" class="form-control"  name="keyword" value="${keyword }" placeholder="Search customer id/name/username">    
        </div>
        <div class="col-lg-3" >
                <button type="submit" class="btn btn-primary" style="margin:-6px 0px">Search</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
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
                                        <th>Customer Name</th>
                                        <th>Cash Balance</th>
                                        <th>Last Trading Day</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${customers}" var="customer" varStatus="status">
                                    <tr>
                                        <td>${customer.customer_id }</td>
                                        <td>${customer.username }</td>
                                        <td>${customer.firstname } ${customer.lastname }</td>
                                        <td>${customer.cash }</td>
                                        <td>01/15/2015</td>
                                        <td><li style="list-style:none" class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="view_account_details.do?customer_id=${customer.customer_id }">view account</a></li>
                    <li><a href="view_protfolio.do?customer_id=${customer.customer_id }">view portfolio</a></li>
                    <li><a href="transactionHistory.do?customer_id=${customer.customer_id }">view transactions</a></li>
                    <li><a href="emp_reset_password.do?customer_id=${customer.customer_id }">reset password</a></li>
                    <li><a href="javascript:void(0)">deposit check</a></li>                  
                </ul>
            </li></td>
                                    </tr>
                                  </c:forEach>
                                </tbody>
                            </table>
                          
        
                    
                </div>
            </div>

    	</div>
 
</div>
 
 
 
<jsp:include page="template-bottom.jsp" />