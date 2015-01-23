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

<div class="mainContent">


    <h3 style="text-align: center">Create an Account for employee</h3>


<form class="form-horizontal" method="post" action="create-employee-account.do">
    <fieldset>
        <div class="form-group">
            <label for="inputusername" class="col-lg-4 control-label">User Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputusername" name="userName" value="${form.userName}" placeholder="User Name">
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
            <div class="col-lg-7 col-lg-offset-5">
                <button class="btn btn-default">Cancel</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
    </body>
</html>