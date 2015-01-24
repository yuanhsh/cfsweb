<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<% String role = (String)request.getSession().getAttribute("loginAs");
%>




<div id="content">
    
   <div class="mainContent">


    <h3  style="text-align: center">Search Fund </h3>

<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="search_fund.do">
    <fieldset>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Fund Name:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="name" value="" placeholder="Customer name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Fund Ticker:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="symbol" value="" placeholder="Customer ID">
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
<div class="bs-docs-section">
	<div class="row">
		<div class="col-md-8">
			<h3 id="tables">Fund List</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th>Fund ID</th>
							<th>Fund Name</th>
							<th>Fund Symbol</th>
							<th style="text-align: right">Price</th>
							<%if(role.equals("cust")) { %>
							<th style="text-align: right">Action</th>
							<%} %>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fundList}" var="fund">
							<tr>
								<td>${fund.fund_id}</td>
								<td>${fund.name}</td>
								<td>${fund.symbol}</td>
								<td style="text-align: right">${fund.price}</td>
								<%if(role.equals("cust")) { %>
							<td style="text-align:right">
                                            <li style="list-style:none" class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle action" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" class="buy-fund-link" 
                    fund-id="${fund.fund_id }" fund-name="${fund.name }" fund-symbol="${fund.symbol }">Buy</a></li>   
                    <li><a href="javascript:void(0)" class="view-price-link" 
                    fund-id="${fund.fund_id }" fund-name="${fund.name }" fund-symbol="${fund.symbol }">View Price History</a></li>               
                </ul>
            </li>
                                        </td>
							<%} %>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="buy-modal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg-6" style="margin:100px auto">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Buy Fund</h4>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-dismissable alert-success">
                            </div>
                            <div class="alert alert-dismissable alert-warning">
                            </div>
                            <form class="form-horizontal" id="form-buy-fund">
                                <fieldset>
                                    <div class="form-group">
                                        <label for="fundName" class="col-lg-3 control-label">Fund:</label>
                                        <div class="col-lg-6">
                                        	<input type="hidden" name="fund_id" class="hidden-fund-id"/>
                                            <div class="form-control-wrapper"><label class="col-lg-8 control-label fund-name-label"></label><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="fundAmount" class="col-lg-3 control-label">Amount:</label>
                                        <div class="col-lg-6">
                                            <div class="form-control-wrapper"><input type="text" class="form-control empty" id="fundAmount" name="amount" placeholder="Amount" required><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-6 col-lg-offset-4">
                                    <!--        <button class="btn btn-default">Cancel</button>
                                             <button class="btn btn-primary submit-buy-fund">Submit</button> -->
                                            <a href="javascript:void(0)" class="btn btn-primary submit-buy-fund">Submit<div class="ripple-wrapper"></div></a>
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
            	$(".action").click(function(){
            		$(".submit-buy-fund").show();
            	});
            	$(".buy-fund-link").click(function(){
            		var fundId = $(this).attr("fund-id");
					var fundName = $(this).attr("fund-name");
					var fundSymbol = $(this).attr("fund-symbol");
					$(".hidden-fund-id").val(fundId);
					$("#fundAmount").val("");
					$(".fund-name-label").text(fundName+" ("+fundSymbol+")");
					$(".alert-success").hide();
					$(".alert-warning").hide();
            		$("#buy-modal").modal();
                });
            	
            	$(".submit-buy-fund").click(function(){
            		$.post( 'cust_ajax_buy_fund.do', $('form#form-buy-fund').serialize(), function(data) {
            	         if(data.success == "true") {
            	        	 $(".alert-success").text(data.info).show();
            	        	 $(".alert-warning").text(data.error).hide();
            	        	 $(".submit-buy-fund").hide();
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
