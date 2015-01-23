<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:useBean id="cust" class="com.cfs.form.CreateCustomerAccountForm" scope="customer"/> --%>
<%@ page import="com.cfs.bean.CustomerBean" %>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
	 <div class="mainContent">


    <h3 style="text-align: center">View Account Details</h3>


<% String role = (String)request.getSession().getAttribute("loginAs");
if(role.equals("cust")) {
%>
<%-- <c:forEach var="customer" items="${customerInfo}"> --%>
<form class="form-horizontal" method="POST" action="view-account-details.do">
    <fieldset>
<%--             <div class="form-group">
            <label for="inputusername" class="col-lg-4 control-label">Customer Id:</label>
            <div class="col-lg-5">
                <input type="hidden" class="form-control" id="inputcustomerid" value="${customer.customer_id}" disabled/>
            </div>
        </div> --%>
        <div class="form-group">
            <label for="inputusername" class="col-lg-4 control-label">User Name:</label>
            <div class="col-lg-5">
                 ${customer.username}
            </div>
        </div>
        <div class="form-group">
            <label for="inputfirstname" class="col-lg-4 control-label">First Name:</label>
            <div class="col-lg-5">
                ${customer.firstname}
            </div>
        </div>
        <div class="form-group">
            <label for="inputulastname" class="col-lg-4 control-label">Last Name:</label>
            <div class="col-lg-5">
                ${customer.lastname}
            </div>
        </div>
         <div class="form-group">
            <label for="inputaddress1" class="col-lg-4 control-label">Address line1:</label>
            <div class="col-lg-5">
                ${customer.addr_line1}
            </div>
        </div>
         <div class="form-group">
            <label for="inputaddress2" class="col-lg-4 control-label">Address line2:</label>
            <div class="col-lg-5">
                ${customer.addr_line2}
            </div>
        </div>
         <div class="form-group">
            <label for="inputcity" class="col-lg-4 control-label">City:</label>
            <div class="col-lg-5">
                ${customer.city}
            </div>
        </div>
         <div class="form-group">
            <label for="select" class="col-lg-4 control-label">State:</label>
            <div class="col-lg-5">
             ${customer.state}
               <%--  <select class="form-control" id="select">
                    <option value="${customer.state}"></option>
                </select> --%>
            </div></div>
                    <div class="form-group">
            <label for="inputzip" class="col-lg-4 control-label">Zip:</label>
            <div class="col-lg-5">
               ${customer.zip}
            </div>
        </div>
            
<!--         <div class="form-group"> -->
<!--             <div class="col-lg-7 col-lg-offset-5"> -->
<!--                 <button class="btn btn-default">Cancel</button> -->
<!--                 <button type="submit" class="btn btn-primary">Submit</button> -->
<!--             </div> -->
<!--         </div> -->
    </fieldset>
</form>
<%-- </c:forEach> --%>
         <% }%>
         
         <% if(role.equals("emp")) {
%>
<c:forEach var="customer" items="${customerInfo}">
  <div class="form-group">
            <label for="inputusername" class="col-lg-4 control-label">User Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputusername" value="${customer.username}" disabled/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputfirstname" class="col-lg-4 control-label">First Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputfname" value="${customer.firstname}" disabled/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputulastname" class="col-lg-4 control-label">Last Name:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputlname" value="${customer.lastname}"/>
            </div>
        </div>
         <div class="form-group">
            <label for="inputaddress1" class="col-lg-4 control-label">Address line1:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputaddress1" value="${customer.addr_line1}"/>
            </div>
        </div>
         <div class="form-group">
            <label for="inputaddress2" class="col-lg-4 control-label">Address line2:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputaddress2" value="${customer.addr_line2}"/>
            </div>
        </div>
         <div class="form-group">
            <label for="inputcity" class="col-lg-4 control-label">City:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputcity" value="${customer.city}"/>
            </div>
        </div>
         <div class="form-group">
            <label for="select" class="col-lg-4 control-label">State:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputstate" value="${customer.state}" disabled/>
            </div></div>
                    <div class="form-group">
            <label for="inputzip" class="col-lg-4 control-label">Zip:</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" id="inputzip" value="${customer.zip}" disabled/>
            </div>
        </div>
            
<!--         <div class="form-group"> -->
<!--             <div class="col-lg-7 col-lg-offset-5"> -->
<!--                 <button class="btn btn-default">Cancel</button> -->
<!--                 <button type="submit" class="btn btn-primary">Submit</button> -->
<!--             </div> -->
<!--         </div> -->
<!-- <div class="form-group">
            <div class="col-lg-7 col-lg-offset-5">
                <button class="btn btn-default">OK</button>
                <button type="submit" class="btn btn-primary">View Portfolio</button>
            </div>
        </div> -->
    </fieldset>
</form>
</c:forEach>
         <% }%>
<jsp:include page="template-bottom.jsp" />