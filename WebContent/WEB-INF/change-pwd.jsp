<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="success.jsp" />
<form class="form-horizontal"  method="POST" action="change-pwd.do">
    <fieldset>
        <div class="form-group">
            <label for="inputvpassword" class="col-lg-4 control-label">Old Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputVpassword" name="oldPassword" placeholder="Old Password" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputnpassword" class="col-lg-4 control-label">New Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputnpassword" name="newPassword" placeholder="New Password" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputcpassword" class="col-lg-4 control-label">Confirm New Password:</label>
            <div class="col-lg-4">
                <input type="Password" class="form-control" id="inputcpassword" name="confirmPassword" placeholder="Confirm New Password" value=""/>
            </div>
        </div>
     
        <div class="form-group">
            <div class="col-lg-6 col-lg-offset-4">
                
                <button type="submit" class="btn btn-primary" name="button">Change Password</button>
                <button class="btn btn-primary btn-request-check" onClick="javascript:history.back()" type="Button">Back</button>
            </div>
        </div>
    </fieldset>
</form>


<jsp:include page="template-bottom.jsp" />
<!-- <form class="form-horizontal"  method="POST" action="change-pwd.do"> -->
<!--     <fieldset> -->
    
<!-- <html> -->
<!-- <head> -->
<!--         <meta charset="utf-8"> -->
<!--         <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <title>Carnegie Financial Services</title> -->
<!-- <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"> -->
<!--         <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/material.min.css"> -->
<!--         <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/ripples.min.css"> -->
<!--         <link rel="stylesheet" href="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/css/material-wfont.min.css"> -->
<!--     <link href="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.css" rel="stylesheet"> -->
<!--         <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script> -->
<!--         <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<!--         <script src="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/js/ripples.min.js"></script> -->
<!--         <script src="http://cdn.jsdelivr.net/bootstrap.material-design/0.2.1/js/material.min.js"></script> -->
<!--         <script src="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.js"></script> -->
<!--         <script src="http://cdnjs.cloudflare.com/ajax/libs/noUiSlider/6.2.0/jquery.nouislider.min.js"></script> -->
<!--         <script src="http://cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.js"></script> -->
<!--         <script> -->
<!-- //             $(function() { -->
<!-- //                 $.material.init(); -->
<!-- //             }); -->
<!--         </script> -->
<!-- </head> -->

<!-- <body> -->
<!-- <div class="header-panel"> -->
<!--           <div class="container-fluid"> -->
<!--             <div class="row"> -->
<!--               <div class="bs-component"> -->
<!--                     <div class="navbar navbar-default"> -->
<!--                         <div class="navbar-header"> -->
<!--                             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse"> -->
<!--                                 <span class="icon-bar"></span> -->
<!--                                 <span class="icon-bar"></span> -->
<!--                                 <span class="icon-bar"></span> -->
<!--                             </button> -->
<!--                             <a class="navbar-brand" href="javascript:void(0)">Carnegie Financial<div class="ripple-wrapper"></div></a> -->
<!--                         </div> -->
<!--                         <div class="navbar-collapse collapse navbar-responsive-collapse"> -->
<!--                             <ul class="nav navbar-nav"> -->
<!--                                 <li class="active"><a href="javascript:void(0)">Portfolio</a></li> -->
<!--                                 <li><a href="javascript:void(0)">Transactions</a></li> -->
<!--                                 <li><a href="javascript:void(0)">Funds</a></li> -->
<!--                             </ul> -->
<!--                             <form class="navbar-form navbar-left"> -->
<!--                                 <div class="form-control-wrapper"><input type="text" class="form-control col-lg-8 empty" placeholder="Search Fund"><span class="material-input"></span></div> -->
<!--                             </form> -->
<!--                             <ul class="nav navbar-nav navbar-right"> -->
<!--                                 <li><a href="javascript:void(0)">Contact us<div class="ripple-wrapper"></div></a></li> -->
<!--                                 <li class="dropdown"> -->
<!--                                     <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Clinton<b class="caret"></b><div class="ripple-wrapper"></div></a> -->
<!--                                     <ul class="dropdown-menu"> -->
<!--                                         <li><a href="javascript:void(0)">View Personal Info</a></li> -->
<!--                                         <li><a href="javascript:void(0)">Change Password</a></li> -->
<!--                                         <li><a href="javascript:void(0)">Help</a></li> -->
<!--                                         <li><a href="javascript:void(0)">Logout</a></li> -->
<!--                                     </ul> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->

<!--   <div id="content"> -->
    
<!--    <div class="mainContent"> -->


<!--     <h3  style="text-align: center">Change Password</h3> -->
<%--     <jsp:include page="error-list.jsp" /> --%>
<!-- <p style="font-size:medium"> -->
<!-- 	Enter your new password -->
<!-- </p> -->


<!-- <form class="form-horizontal"  method="POST" action="change-pwd.do"> -->
<!--     <fieldset> -->
<!--         <div class="form-group"> -->
<!--             <label for="inputvpassword" class="col-lg-4 control-label">Old Password:</label> -->
<!--             <div class="col-lg-4"> -->
<!--                 <input type="Password" class="form-control" id="inputVpassword" name="oldPassword" placeholder="Old Password" value=""/> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="form-group"> -->
<!--             <label for="inputnpassword" class="col-lg-4 control-label">New Password:</label> -->
<!--             <div class="col-lg-4"> -->
<!--                 <input type="Password" class="form-control" id="inputnpassword" name="newPassword" placeholder="New Password" value=""/> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="form-group"> -->
<!--             <label for="inputcpassword" class="col-lg-4 control-label">Confirm New Password:</label> -->
<!--             <div class="col-lg-4"> -->
<!--                 <input type="Password" class="form-control" id="inputcpassword" name="confirmPassword" placeholder="Confirm New Password" value=""/> -->
<!--             </div> -->
<!--         </div> -->
     
<!--         <div class="form-group"> -->
<!--             <div class="col-lg-6 col-lg-offset-4"> -->
                
<!--                 <button type="submit" class="btn btn-primary" name="button">Change Password</button> -->
<!--             </div> -->
<!--         </div> -->
<!--     </fieldset> -->
<!-- </form> -->
<!-- </div> -->
 
<!-- </div> -->
<!-- </body> -->
<!-- </html> -->
    
<!--         <div class="form-group"> -->
<!--             <label for="inputopassword" class="col-lg-4 control-label">Old Password:</label> -->
<!--             <div class="col-lg-4"> -->
<!--                 <input type="Password" class="form-control" id="inputopassword" name="newPassword" placeholder="New Password" value=""/> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="form-group"> -->
<!--             <label for="inputnpassword" class="col-lg-4 control-label">New Password:</label> -->
<!--             <div class="col-lg-4"> -->
<!--                 <input type="Password" class="form-control" id="inputnpassword" name="newPassword" placeholder="New Password" value=""/> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="form-group"> -->
<!--             <label for="inputcpassword" class="col-lg-4 control-label">Confirm New Password:</label> -->
<!--             <div class="col-lg-4"> -->
<!--                 <input type="Password" class="form-control" id="inputcpassword" name="confirmPassword" placeholder="Confirm New Password" value=""/> -->
<!--             </div> -->
<!--         </div> -->
     
<!--         <div class="form-group"> -->
<!--             <div class="col-lg-6 col-lg-offset-4"> -->
                
<!--                 <button type="submit" class="btn btn-primary" name="button">Change Password</button> -->
<!--             </div> -->
<!--         </div> -->
<!--     </fieldset> -->
<!-- </form> -->
<!-- </div> -->
 
<!-- </div> -->
<!-- </body> -->
<!-- </html> -->
