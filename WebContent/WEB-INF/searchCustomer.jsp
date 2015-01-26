<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<div class="mainContent">
	<jsp:include page="error-list.jsp" />
	<form class="form-horizontal" method="get"
		action="emp_search_customer.do">
		<fieldset>
			<div class="form-group">
				<div class="col-lg-3">
					<input type="text" class="form-control" name="keyword"
						value="${keyword }" placeholder="Search customer id/name/username">
				</div>
				<div class="col-lg-3">
					<button type="submit" class="btn btn-primary"
						style="margin: -6px 0px">Search</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<div class="bs-docs-section">
	<div class="row">
		<div class="col-lg-10">
			<h3 id="tables">Customer List</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th>User Id</th>
							<th>User Name</th>
							<th>Customer Name</th>
							<th>Last Trading Day</th>
							<th class="decimal">Cash Balance</th>
							<th class="decimal">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${customers}" var="customer" varStatus="status">
							<tr>
								<td>${customer.customer_id }</td>
								<td>${customer.username }</td>
								<td>${customer.firstname } ${customer.lastname }</td>
								<td>01/15/2015</td>
								<td class="decimal">${customer.cash }</td>
								<td class="decimal"><li style="list-style: none" class="dropdown"><a
										href="javascript:void(0)" class="dropdown-toggle"
										data-toggle="dropdown">Action<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a
												href="view_account_details.do?customer_id=${customer.customer_id }">view
													account</a></li>
											<li><a
												href="view_protfolio.do?customer_id=${customer.customer_id }">view
													portfolio</a></li>
											<li><a
												href="transactionHistory.do?customer_id=${customer.customer_id }">view
													transactions</a></li>
											<li><a href="javascript:void(0)" class="deposit-check-link" customer-id="${customer.customer_id }" 
											customer-name="${customer.firstname } ${customer.lastname } (${customer.username })">deposit check</a></li>
											<li><a
												href="emp_reset_password.do?customer_id=${customer.customer_id }">reset
													password</a></li>
										</ul></li></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="deposit-modal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg-6" style="margin:100px auto">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Deposit Check</h4>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-dismissable alert-success"> </div>
                            <div class="alert alert-dismissable alert-warning"> </div>
                            <form class="form-horizontal" id="form-deposit-check">
                                <fieldset>
                                    <div class="form-group">
                                        <label for="fundName" class="col-lg-4 control-label">Customer:</label>
                                        <div class="col-lg-6">
                                        	<input type="hidden" name="customer_id" class="hidden-customer-id"/>
                                            <div class="form-control-wrapper">
                                            <input type="text" class="form-control empty disabled" id="depositCustomerName" name="customerName" readonly>
                                            <!-- <label class="col-lg-12 control-label customer-name-label"></label><span class="material-input"></span> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="depositAmount" class="col-lg-4 control-label">Deposit Amount($):</label>
                                        <div class="col-lg-6">
                                            <div class="form-control-wrapper">
                                            <input type="text" class="form-control empty" id="depositAmount" name="amount" placeholder="Deposit Check Amount">
                                            <span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-6 col-lg-offset-4">
                                            <a href="javascript:void(0)" class="btn btn-primary submit-deposit-check">Submit<div class="ripple-wrapper"></div></a>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
<script>
            $(function() {
            	$(".deposit-check-link").click(function(){
            		var customer_id = $(this).attr("customer-id");
					var customer_name = $(this).attr("customer-name");
					$(".hidden-customer-id").val(customer_id);
					//$(".customer-name-label").text(customer_name);
					$("#depositCustomerName").val(customer_name);
            		$(".submit-deposit-check").show();
					$("#depositAmount").val("");
					$(".alert-success").hide();
					$(".alert-warning").hide();
            		$("#deposit-modal").modal();
                });
            	$(".submit-deposit-check").click(function(){
            		$.post( 'emp_ajax_deposit_check.do', $('form#form-deposit-check').serialize(), function(data) {
            	         if(data.success == "true") {
            	        	 $(".alert-success").text(data.info).show();
            	        	 $(".alert-warning").text(data.error).hide();
            	        	 //$(".cust-cash-label").text(data.cash);
            	        	 $(".submit-deposit-check").hide();
            	         } else {
            	        	 $(".alert-warning").text(data.error).show();
            	        	 $(".alert-success").text(data.info).hide();
            	         }
            	      }, 'json' // I expect a JSON response
            	    );
                });
            });
</script>
<jsp:include page="template-bottom.jsp" />