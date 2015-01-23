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
                                        <th>Fund ID(Ticker)</th>
                                        <th style="text-align:right">Customer ID</th>
                                        <th style="text-align:right">Shares</th>
                                        <th style="text-align:right">Shares</th>
                                        <th style="text-align:right">Status</th>
                                        
                                        <th style="text-align:right">Date</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${transaction}" var="t" >
                                    <tr>
                                        <td>${t.transaction_id }</td>
                                        <td>${t.fund_id }</td>
                                        <td style="text-align:right">${t.shares }</td>
                                        <td style="text-align:right">${t.amount }</td>
                                        <td style="text-align:right">${t.status }</td>
                                         <td style="text-align:right">${t.execuate_date }</td>
                                       
                                    </tr>
                                </c:forEach>    
                                </tbody>
                            </table>
                    </div>
                </div>
            </div></div>
            <jsp:include page="template-bottom.jsp" />