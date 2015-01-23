<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div id="content">
    <%-- <c:forEach var="fund" items="${fund}"> --%>
<form class="form-horizontal" method="POST" action="emp_create_fund.do">

                          
                                <fieldset>
                                    <legend>Fund Information</legend>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-lg-3 control-label">Fund Name</label>
                                        <div class="col-lg-9">
                                            <div class="form-control-wrapper"><input type="text" class="form-control empty" id="inputEmail" placeholder="Fund Name"><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword" class="col-lg-3 control-label">Fund Ticker</label>
                                        <div class="col-lg-9">
                                            <div class="form-control-wrapper"><input type="text" class="form-control empty" id="inputPassword" placeholder="Fund Ticker"><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-9 col-lg-offset-3">
                                            <button class="btn btn-default">Cancel</button>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form><%-- </c:forEach> --%>
                        </div>

 
 
 
<jsp:include page="template-bottom.jsp" />