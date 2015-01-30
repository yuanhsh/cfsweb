<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<% String role = (String)request.getSession().getAttribute("loginAs");
String fund_key = (String)request.getAttribute("fund_key");
%>
<jsp:include page="error-list.jsp" />
<%if("emp".equals(role)) { %>
	<div id="emp-alert-success" class="alert alert-dismissable alert-success hidden"></div>
	<div id="emp-alert-warning" class="alert alert-dismissable alert-warning hidden"></div>
<%} %>
<div class="bs-docs-section">
	<div class="row" style="width:1200px!important">
		<div  style="width:600px!important; float:left">
			<div class="col-lg-12" >
				<div class="col-lg-4" style="padding:0px">
					<h3 id="tables">Fund List</h3>
				</div>
				<%if("emp".equals(role) && (fund_key == null || fund_key.isEmpty())) { %>
				<div class="col-lg-8 text-right" style="padding-right:0px">
					<button class="btn btn-default btn-cancel-transition hidden" type="button">Cancel</button>
					<button type="button" class="btn btn-primary btn-transition">Transition Day</button>
				</div>
				<%} %>
			</div>
			<form id="form-transition" method="POST" action="emp_transition_day.do">
			<div class="col-lg-12">
				<table class="table table-striped table-hover" id="flTable">
					<thead>
						<tr>
							<th data-sortable="true">#</th>
							<th data-sortable="true">Fund Name</th>
							<th data-sortable="true">Fund Symbol</th>
							<th class="text-right">Price</th>
							<%if(role.equals("cust")) { %>
							<th class="text-right">Action</th>
							<%} %>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fundList}" var="fund">
							<tr fund-id="${fund.fund_id}" fund-symbol="${fund.symbol}">
								<td>${fund.fund_id}<input type="hidden" name="fundId" value="${fund.fund_id}"/></td>
								<td>${fund.name}</td>
								<td>${fund.symbol}</td>
								<td class="text-right currency">${fund.price/100.0}</td>
								<%if(role.equals("cust")) { %>
								<td class="text-right">
									<a href="javascript:void(0)" class="buy-fund-link"
												fund-id="${fund.fund_id }" fund-name="${fund.name }"
												fund-symbol="${fund.symbol }">Buy</a>
								</td>
								<%} %>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				
			</div>
				<div id="TransDateArea" class="col-lg-12 text-right hidden" >
				<fieldset>
						<div class="form-group">
					<label class="col-lg-9 control-label">Transition Date:</label>
					<div class="col-lg-3" >
						<div class="form-control-wrapper">
							<input type="text" class="form-control empty text-right" name="date" style="height: 20px"
								placeHolder="MM/dd/yyyy"><span class="material-input"></span>
						</div>
					</div>
					</div>
					</fieldset>
				</div>
			</form>
		</div>
		
		<div  style="width:580px!important; float:right; padding-right:20px">
			<h3>&nbsp;&nbsp;</h3>
			<div class="bs-component" id="fundChart" style="height:400px; min-width:310px"></div>
		</div>
	</div>
</div>
<%if("cust".equals(role)) { %>
<div id="buy-modal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg-6" style="margin: 100px auto">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Buy Fund</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-dismissable alert-success"></div>
				<div class="alert alert-dismissable alert-warning"></div>
				<form class="form-horizontal" id="form-buy-fund">
					<fieldset>
						<div class="form-group">
							<label for="fundName" class="col-lg-3 control-label">Fund:</label>
							<div class="col-lg-6">
								<input type="hidden" name="fund_id" class="hidden-fund-id" />
								<div class="form-control-wrapper">
									<label class="col-lg-8 control-label fund-name-label"></label><span
										class="material-input"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="fundAmount" class="col-lg-3 control-label">Amount($):</label>
							<div class="col-lg-6">
								<div class="form-control-wrapper">
									<input type="text" class="form-control empty" id="fundAmount"
										name="amount" placeholder="Amount"><span
										class="material-input"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-6 col-lg-offset-4">
								<!--        <button class="btn btn-default">Cancel</button>
                                             <button class="btn btn-primary submit-buy-fund">Submit</button> -->
								<a href="javascript:void(0)"
									class="btn btn-primary submit-buy-fund">Submit
									<div class="ripple-wrapper"></div>
								</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
<%} %>
<script>
            $(function() {
            	$("#emp-alert-success").hide();
            	$("#emp-alert-warning").hide();
            	$("#emp-alert-success").removeClass("hidden");
            	$("#emp-alert-warning").removeClass("hidden");
            	$(".buy-fund-link").click(function(){
            		var fundId = $(this).attr("fund-id");
					var fundName = $(this).attr("fund-name");
					var fundSymbol = $(this).attr("fund-symbol");
					$(".hidden-fund-id").val(fundId);
					$("#fundAmount").val("");
					$(".submit-buy-fund").show();
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
            	
            	$("#flTable tbody tr").click(function(){
            		if($(this).hasClass("info") || $("#flTable").hasClass("transitioning")) return;
            		refreshFundChart($(this).attr("fund-id"), $(this).attr("fund-symbol"));
            		$("#flTable tbody tr").removeClass("info");
            		$(this).addClass("info");
            	});
            	
            	<%if("emp".equals(role)) { %>
            	$(".btn-transition").click(function(){
            		if($("#flTable").hasClass("transitioning")) {
            			//$("#form-transition").submit();
            			$.post( 'emp_ajax_transition_day.do', $('form#form-transition').serialize(), function(data) {
	               	         if(data.success == "true") {
	               	        	 $("#emp-alert-success").text(data.info).show();
	               	        	 $("#emp-alert-warning").text(data.error).hide();
	               	        	 setTimeout(function(){
	               	        		location.reload();
	               	        	 }, 1000);
	               	         } else {
	               	        	 $("#emp-alert-warning").text(data.error).show();
	               	        	 $("#emp-alert-success").text(data.info).hide();
	               	         }
	               	      }, 'json' // I expect a JSON response
	               	    );
            		} else {
            			$(".btn-cancel-transition").removeClass("hidden");
            			$("#TransDateArea").removeClass("hidden");
                		$("#flTable").addClass("transitioning");
                		$(this).text("Submit");
                		$("#flTable tbody tr").removeClass("info");
                		$("#flTable thead tr").append("<th class='text-right col-lg-2'>New Price</th>");
                    	$("#flTable tr:gt(0)").append("<td class='text-right'><input type='text'"+ 
                    			"class='form-control text-right' style='height:20px' name='fundPrice'/></td>");
            		}
                });
            	
            	$(".btn-cancel-transition").click(function(){
            		$(this).addClass("hidden");
            		$("#emp-alert-success").hide();
                	$("#emp-alert-warning").hide();
            		$("#TransDateArea").addClass("hidden");
            		$("#flTable").removeClass("transitioning");
            		$(".btn-transition").text("Transition Day");
            		var len = $("#flTable thead th").length;
            		$('#flTable thead th:nth-child('+len+')').remove();
            		$('#flTable td:nth-child('+len+')').remove();
                });
            	<%} %>
            });
            
            function refreshFundChart(fundId, ticker) {
//$.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=aapl-c.json&callback=?', function (data) {
                disableIndicator();  
				$.getJSON('ajax_price_history.do?fund_id='+fundId, function(data) {
                    $('#fundChart').highcharts('StockChart', {
                        rangeSelector : {selected : 1},
                        title : {text : ticker + ' Price History'},
                        series : [{
                            name : ticker,
                            data : data,
                            tooltip: { valueDecimals: 2}
                        }]
                    });
                    enableIndicator();
                });
            }
        </script>
<jsp:include page="template-bottom.jsp" />
