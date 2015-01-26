<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cfs.bean.TransactionBean" %>
<jsp:include page="template-top.jsp" />
         
<div class="bs-docs-section">
                <div class="row">
                    <div class="col-lg-10">
                        <h3 id="tables">Transaction History</h3>
                        <div class="bs-component">
                            <table class="table table-striped table-hover" data-toggle="table">
                                <thead>
                                    <tr>
                                        <th data-sortable="true">#</th>
                                        <th data-sortable="true">Date</th>
                                        <th data-sortable="true">Fund Name(Ticker)</th>
                                        <th data-sortable="true">Action</th>
                                        <th class="text-right">Shares</th>
                                        <th class="text-right">Price</th>
                                        <th class="text-right">Amount</th>
                                        <th data-sortable="true" class="text-right">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${transactionList}" var="t" >
                                    <tr><td>${t.transaction_id }</td>
                                        <td>${t.disp_date }</td>
                                        <td>${t.disp_fund }</td>
                                        <td>${t.transaction_type } </td>
                                        <td class="text-right">${t.disp_shares }</td>
                                        <td class="text-right">${t.disp_price }</td>
                                        <td class="text-right">${t.disp_amount}</td>
                                        <td class="text-right">${t.status }</td>
                                    </tr>
                                </c:forEach>    
                                </tbody>
                            </table>
                    </div>
                </div>
            </div></div>
            <jsp:include page="template-bottom.jsp" />