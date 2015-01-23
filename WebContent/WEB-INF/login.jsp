
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Carnegie Financial Services</title>
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
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="javascript:void(0)">Contact us<div class="ripple-wrapper"></div></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
          </div>
        </div>
<p>
<%-- 	<form method="post" action="login.do">
		<table>
			<tr>
				<td> User Name: </td>
				<td><input type="text" name="userName" value="${form.userName}"/></td>
			</tr>
			<tr>
				<td> Password: </td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Login"/>
				</td>
			</tr>
		</table>
	</form>
	 --%>
	  <div id="content">
    
   <div class="mainContent">


    
    <jsp:include page="error-list.jsp" />

<form class="form-horizontal" method="post" action="login.do">
    <fieldset>
    <div class="form-group">
            <div class="col-lg-6 col-lg-offset-5">
                <h3 style="padding:20px">Login</h3>
            </div>
        </div>
        <div class="form-group">
            <label for="inputvpassword" class="col-lg-4 control-label">Username:</label>
            <div class="col-lg-4">
                <input type="text" name="username" class="form-control" id="inputVpassword" placeholder="Username" value="${form.username}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">Password:</label>
            <div class="col-lg-4">
                <input type="Password" name="password" class="form-control" id="inputnpassword" placeholder="Password" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="select" class="col-lg-4 control-label">Login as:</label>
            <div class="col-lg-4">
                <select class="form-control" id="loginAs" name="loginAs">
                    <option value="cust">Customer</option>
   					<option value="emp">Employee</option>
     </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-6 col-lg-offset-5">
                
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
 
</div>
</body>
</html>