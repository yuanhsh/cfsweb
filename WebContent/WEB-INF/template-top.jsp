<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Transaction History</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/material.min.css"> -->
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/ripples.min.css">
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/material-wfont.min.css">
		<link href="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/js/ripples.min.js"></script>
        <script src="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/js/material.min.js"></script>
        <script src="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/noUiSlider/6.2.0/jquery.nouislider.min.js"></script>
        <script src="http://cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.js"></script>
        <script>
            $(function() {
                $.material.init();
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
                                <li class="active"><a href="javascript:void(0)">Portfolio</a></li>
                                <li><a href="javascript:void(0)">Transactions</a></li>
                                <li><a href="javascript:void(0)">Funds</a></li>
                            </ul>
                            <form class="navbar-form navbar-left">
                                <div class="form-control-wrapper"><input type="text" class="form-control col-lg-8 empty" placeholder="Search Fund"><span class="material-input"></span></div>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="javascript:void(0)">Contact us<div class="ripple-wrapper"></div></a></li>
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">${username }<b class="caret"></b><div class="ripple-wrapper"></div></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">View Personal Info</a></li>
                                        <li><a href="change-pwd.do">Change Password</a></li>
                                        <li><a href="javascript:void(0)">Help</a></li>
                                        <li><a href="logout.do">Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        <% 
                        	} else if("emp".equals(loginAs)) {
                        %>
                        	<ul class="nav navbar-nav">
                                <li class="dropdown active">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Fund Management <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">View Funds</a></li>
                                        <li><a href="javascript:void(0)">Create Fund</a></li>
                                        <li><a href="javascript:void(0)">Transition Day</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Customer Management <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">View Customers</a></li>
                                        <li><a href="javascript:void(0)">(account and transactions)</a></li>
                                        <li><a href="javascript:void(0)">Create Customer Account</a></li>
                                        <li><a href="javascript:void(0)">Deposit Check</a></li>
                                        <li><a href="javascript:void(0)">Reset Customer Password</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Employee Management<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">View Employees</a></li>
                                        <li><a href="javascript:void(0)">Create Employee Account</a></li>
                                    </ul>
                                </li>
                                <!-- <li><a href="javascript:void(0)">Transition Day</a></li> -->
                            </ul>
                            <form class="navbar-form navbar-left">
                                <div class="form-control-wrapper"><input type="text" class="form-control col-lg-8 empty" placeholder="Search Fund"><span class="material-input"></span></div>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <!-- <li><a href="javascript:void(0)">Help<div class="ripple-wrapper"></div></a></li> -->
                                <li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">${username }<b class="caret"></b><div class="ripple-wrapper"></div></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">Change Password</a></li>
                                        <li><a href="javascript:void(0)">Logout</a></li>
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
