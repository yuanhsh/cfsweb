<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


        <form class="form-horizontal" action="#">
    
        <div class="form-group">
           <p style="font-size:medium; background-color:#009393 ; color:#FFFFFF; align:center"> Search Result:</p>
         			
				<table >
					<thead>
						<tr>
						
							<th>Fund Name: ${searchfunds.name}</th>
							
							<th>Fund Symbol:${searchfunds.symbol}</th>
							
						    <th>Fund Price:${fundInfoList.price}</th>
						</tr>
					</thead></table>
            </div>
            
        </div>
        
</form>
