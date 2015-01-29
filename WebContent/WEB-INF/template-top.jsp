<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Carnegie Financial Service</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/material.min.css"> -->
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/ripples.min.css">
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/material-wfont.min.css">
		<link href="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.css" rel="stylesheet">
		<link href="css/bootstrap-table.min.css" rel="stylesheet">
		<!-- 
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.5.0/bootstrap-table.min.css" rel="stylesheet">
        -->
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/js/ripples.min.js"></script>
        <script src="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/js/material.min.js"></script>
        <script src="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/noUiSlider/6.2.0/jquery.nouislider.min.js"></script>
        <script src="http://cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.js"></script>
        <script src="http://code.highcharts.com/stock/highstock.js"></script>
		<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
		<script src="https://jquery-formatcurrency.googlecode.com/files/jquery.formatCurrency-1.4.0.min.js"></script>
		<script src="js/bootstrap-table.js"></script>
		<script src="js/ajax-loader.js"></script>
        <script>
            $(function() {
                $.material.init();
                $('.currency').formatCurrency();
            });
        </script>
    </head>
    <body>
        <div class="header-panel">
          <div class="container-fluid">
            <div class="row">
              <div class="bs-component">
                    <div class="navbar navbar-default">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="javascript:void(0)">Carnegie Financial<div class="ripple-wrapper"></div></a>
                        </div>
                        
                        <div class="navbar-collapse collapse navbar-responsive-collapse">
                        <%
                        	String loginAs = (String)request.getSession().getAttribute("loginAs");
                        	if("cust".equals(loginAs)) {
                        		
                        %>
                            <ul class="nav navbar-nav">
                                <li class=""><a href="view_protfolio.do?customer_id=${customer_id }">Portfolio</a></li>
                                <li><a href="transactionHistory.do?customer_id=${customer_id }">Transactions</a></li>
                                <li><a href="search_fund.do">Funds</a></li>
                            </ul>
                            <form class="navbar-form navbar-left" method="get" action="search_fund.do">
                                <div class="form-control-wrapper"><input type="text" class="form-control col-lg-8 empty" name="fund_key" placeholder="Search Fund" value="${fund_key }"><span class="material-input"></span></div>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="javascript:void(0)">Contact us<div class="ripple-wrapper"></div></a></li>
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">${username }<b class="caret"></b><div class="ripple-wrapper"></div></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="view_account_details.do?customer_id=${customer_id }">View Account Info</a></li>
                                        <li><a href="change-pwd.do">Change Password</a></li>
                                        <li><a href="logout.do">Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        <% 
                        	} else if("emp".equals(loginAs)) {
                        %>
                        	<ul class="nav navbar-nav">
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Fund Management <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="search_fund.do">View Funds</a></li>
                                        <li><a href="emp_create_fund.do">Create Fund</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Account Management <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="emp_search_customer.do">View Customers</a></li>
                                        <li><a href="emp_create_cust_account.do">Create Customer Account</a></li>
                                        <li><a href="emp_create_emp_account.do">Create Employee Account</a></li>
                                    </ul>
                                </li>
                                <!-- <li><a href="emp_transition_day.do">Transition Day</a></li> -->
                            </ul>
                            <form class="navbar-form navbar-left" method="get" action="search_fund.do">
                                <div class="form-control-wrapper"><input type="text" class="form-control col-lg-8 empty" name="fund_key" placeholder="Search Fund" value="${fund_key }"><span class="material-input"></span></div>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <!-- <li><a href="javascript:void(0)">Help<div class="ripple-wrapper"></div></a></li> -->
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">${username }<b class="caret"></b><div class="ripple-wrapper"></div></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="change-pwd.do">Change Password</a></li>
                                        <li><a href="logout.do">Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        <% 	
                        	}
                        %>
                        </div>
                        
                    </div>
                </div>
            </div>
          </div>
        </div>
       <div class="container">
