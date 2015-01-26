<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="success.jsp" />
<jsp:include page="error-list.jsp" />
    <%-- <c:items="${fund}" forEach var="fund" > --%>
<form class="form-horizontal" method="POST" action="emp_create_fund.do">

                          
                                <fieldset>
                                    <legend>Create Fund</legend>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-lg-3 control-label">Fund Name</label>
                                        <div class="col-lg-4">
                                            <div class="form-control-wrapper"><input name="name" type="text" class="form-control empty" value="${form.name}" placeholder="Fund Name"/><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword" class="col-lg-3 control-label">Fund Ticker</label>
                                        <div class="col-lg-4">
                                            <div class="form-control-wrapper"><input name="symbol" type="text" class="form-control empty" value="${form.symbol}" placeholder="Fund Ticker"/><span class="material-input"></span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-4 col-lg-offset-3">
                                            <button class="btn btn-default" onclick="javascript:history.back()" type="Button">Cancel</button>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form><%-- </c:forEach> --%>
                        

 
 
 
<jsp:include page="template-bottom.jsp" />