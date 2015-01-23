<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<div class="bs-docs-section">
                <div class="row">
                    <div class="col-md-8">
                        <h3 id="tables">Protfolio</h3>
                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Fund Name(Ticker)</th>
                                        <th>Shares</th>
                                        <th>Price</th>
                                        <th>Amount</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${funds}" var="fund" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${fund.fund_name } (${fund.fund_symbol })</td>
                                        <td>${fund.disp_shares }</td>
                                        <td>${fund.disp_price }</td>
                                        <td>${fund.disp_amount }</td>
                                        <td>
                                            <li style="list-style:none" class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Action<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">Sell</a></li>
                    <li><a href="javascript:void(0)">Buy</a></li>                  
                </ul>
            </li>
                                        </td>
                                    </tr>
                                </c:forEach>    
                                </tbody>
                            </table>
                    </div>
                </div>
            </div>

<jsp:include page="template-bottom.jsp" />