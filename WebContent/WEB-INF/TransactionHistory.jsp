<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cfs.bean.TransactionBean" %>
<jsp:include page="template-top.jsp" />
         
<div class="bs-docs-section">
                <div class="row">
                    <div class="col-md-8">
                        <h3 id="tables">Transaction History</h3>
                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Date</th>
                                        <th >Fund Name(Ticker)</th>
                                        <th >Action</th>
                                        <th style="text-align:right">Shares</th>
                                        <th style="text-align:right">Price</th>
                                        <th style="text-align:right">Amount</th>
                                        <th >Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${transactionList}" var="t" >
                                    <tr><td>${t.transaction_id }</td>
                                        <td>${t.execute_date }</td>
                                        <td>${t.fund_id } </td>
                                        <td>${t.transaction_type } </td>
                                        <td style="text-align:right">${t.shares }</td>
                                        <td style="text-align:right">${t.price }</td>
                                        <td style="text-align:right">${t.amount}</td>
                                         <td >${t.status }</td>
                                    </tr>
                                </c:forEach>    
                                </tbody>
                            </table>
                    </div>
                </div>
            </div></div>
            <jsp:include page="template-bottom.jsp" />