<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.cfs.bean.TransactionBean" %>
<jsp:include page="template-top.jsp" />

<javascript>

         
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
                                        <th >Number of Shares</th>
                                        <th >Price of Shares</th>
                                        <th >Amount</th>
                                        <th >Status</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>01/15/2015</td>
                                        <td>FMI Inc (FMIX)</td>
                                        <td>Sell</td>
                                        <td>20</td>
                                        <td>USD 25</td>
                                        <td>USD 500</td>
                                        <td>Pending Receipt</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>01/14/2015</td>
                                        <td>FMI Inc (FMIX)</td>
                                        <td>Buy</td>
                                        <td>10</td>
                                        <td>USD 20</td>
                                        <td>USD 200</td>
                                        <td>Pending Receipt</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>01/13/2015</td>
                                        <td>FMI Inc (FMIX)</td>
                                        <td>Buy</td>
                                        <td>10</td>
                                        <td>USD 20</td>
                                        <td>USD 200</td>
                                        <td>Cancelled</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>01/12/2015</td>
                                        <td>FMI Inc (FMIX)</td>
                                        <td>Sell</td>
                                        <td>10</td>
                                        <td>USD 20</td>
                                        <td>USD 200</td>
                                        <td>Pending Receipt</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>01/11/2015</td>
                                        <td>FMI Inc (FMIX)</td>
                                        <td>Buy</td>
                                        <td>20</td>
                                        <td>USD 20</td>
                                        <td>USD 400</td>
                                        <td>Complete</td>
                                    </tr>
                                <c:forEach items="${transaction}" var="t" >
                                    <tr><td></td>
                                        <td>${t.execuate_date }</td>
                                        <td>${t.fund_id }</td>
                                        <td> </td>
                                        <td >${t.shares }</td>
                                        <td >${t.amount }</td>
                                        <td >${t.amount}</td>
                                         <td >${t.status }</td>
                                       
                                    </tr>
                                </c:forEach>    
                                </tbody>
                            </table>
                    </div>
                </div>
            </div></div>
            <jsp:include page="template-bottom.jsp" />