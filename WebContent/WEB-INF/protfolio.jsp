<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.cfs.bean.CustomerBean" %>
<jsp:include page="template-top.jsp" />

<% String role = (String)request.getSession().getAttribute("loginAs");
if(role.equals("cust")) {
%>
          <div class="bs-docs-section">
                <div class="row">
                    <div class="col-md-8">
                     <h3 id="tables">Cash Balance</h3>
                     <div class="form-group">
                                        <h4 class="col-lg-3 cust-cash-label">USD ${balance }</h4>
                                        <div class="col-lg-6">
                                        <button class="btn btn-primary btn-request-check">Request Check<div class="ripple-wrapper"></div></button>
                                        <!-- <a href="javascript:void(0)" class="btn btn-default btn-raised btn-request-check">Request Check</a> -->
                                        </div>
                                    </div>
                    </div>
                </div>
          </div>                                    
         <% }%>
         
<div class="bs-docs-section">
                <div class="row">
                    <div class="col-md-8">
                        <h3 id="tables">Protfolio</h3>
                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Fund Name(Ticker)</th>
                                        <th style="text-align:right">Shares</th>
                                        <th style="text-align:right">Price</th>
                                        <th style="text-align:right">Amount</th>
                                        <%if(role.equals("cust")) { %>
                                        <th style="text-align:right">Action</th>
                                        <%} %>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${funds}" var="fund" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${fund.fund_name } (${fund.fund_symbol })</td>
                                        <td style="text-align:right">${fund.disp_shares }</td>
                                        <td style="text-align:right">${fund.disp_price }</td>
                                        <td style="text-align:right">${fund.disp_amount }</td>
                                         <% if(role.equals("cust")) {%>
                                        <td style="text-align:right">
                                            <li style="list-style:none" class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" class="sell-fund-link" 
                    fund-id="${fund.fund_id }" fund-name="${fund.fund_name }" fund-symbol="${fund.fund_symbol }">Sell</a></li>
                    <li><a href="javascript:void(0)" class="buy-fund-link" 
                    fund-id="${fund.fund_id }" fund-name="${fund.fund_name }" fund-symbol="${fund.fund_symbol }">Buy</a></li>                  
                </ul>
            </li>
                                        </td>
                                        <% } %>
                                    </tr>
                                </c:forEach>    
                                </tbody>
                            </table>
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
            
            <div id="sell-modal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg-6" style="margin:100px auto">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Sell Fund</h4>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-dismissable alert-success">
                            </div>
                            <div class="alert alert-dismissable alert-warning">
                            </div>
                            <form class="form-horizontal" id="form-sell-fund">
                                <fieldset>
                                    <div class="form-group">
                                        <label for="fundName" class="col-lg-3 control-label">Fund:</label>
                                        <div class="col-lg-6">
                                        	<input type="hidden" name="fund_id" class="hidden-fund-id"/>
                                            <div class="form-control-wrapper"><label class="col-lg-8 control-label fund-name-label"></label><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="fundAmount" class="col-lg-3 control-label">Shares to sell:</label>
                                        <div class="col-lg-6">
                                            <div class="form-control-wrapper"><input type="text" class="form-control empty" id="sellShares" name="shares" placeholder="Share number" required><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-6 col-lg-offset-4">
                                            <a href="javascript:void(0)" class="btn btn-primary submit-sell-fund">Submit<div class="ripple-wrapper"></div></a>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="request-modal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg-6" style="margin:100px auto">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Request Check</h4>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-dismissable alert-success">
                            </div>
                            <div class="alert alert-dismissable alert-warning">
                            </div>
                            <form class="form-horizontal" id="form-request-check">
                                <fieldset>
                                   
                                    <div class="form-group">
                                        <label for="fundAmount" class="col-lg-5 control-label">Request Check Amount:</label>
                                        <div class="col-lg-5">
                                            <div class="form-control-wrapper"><input type="text" class="form-control empty" id="requestAmount" name="requestAmount" placeholder="Request Check Amount" required><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-6 col-lg-offset-4">
                                            <a href="javascript:void(0)" class="btn btn-primary submit-request-check">Submit<div class="ripple-wrapper"></div></a>
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
            	$(".sell-fund-link").click(function(){
            		var fundId = $(this).attr("fund-id");
					var fundName = $(this).attr("fund-name");
					var fundSymbol = $(this).attr("fund-symbol");
					$(".hidden-fund-id").val(fundId);
					$("#sellShares").val("");
					$(".fund-name-label").text(fundName+" ("+fundSymbol+")");
					$(".alert-success").hide();
					$(".alert-warning").hide();
            		$("#sell-modal").modal();
                });
            	$(".btn-request-check").click(function(){
					$("#requestAmount").val("");
					$(".alert-success").hide();
					$(".alert-warning").hide();
            		$("#request-modal").modal();
                });
            	$(".submit-buy-fund").click(function(){
            		$.post( 'cust_ajax_buy_fund.do', $('form#form-buy-fund').serialize(), function(data) {
            	         if(data.success == "true") {
            	        	 $(".alert-success").text(data.info).show();
            	         } else {
            	        	 $(".alert-warning").text(data.error).show();
            	         }
            	      }, 'json' // I expect a JSON response
            	    );
                });
            	$(".submit-sell-fund").click(function(){
            		$.post( 'cust_ajax_sell_fund.do', $('form#form-sell-fund').serialize(), function(data) {
            	         if(data.success == "true") {
            	        	 $(".alert-success").text(data.info).show();
            	         } else {
            	        	 $(".alert-warning").text(data.error).show();
            	         }
            	      }, 'json' // I expect a JSON response
            	    );
                });
            	$(".submit-request-check").click(function(){
            		$.post( 'cust_ajax_request_check.do', $('form#form-request-check').serialize(), function(data) {
            	         if(data.success == "true") {
            	        	 $(".alert-success").text(data.info).show();
            	        	 $(".cust-cash-label").text(data.cash);
            	         } else {
            	        	 $(".alert-warning").text(data.error).show();
            	         }
            	      }, 'json' // I expect a JSON response
            	    );
                });
            });
        </script>
<jsp:include page="template-bottom.jsp" />