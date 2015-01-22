<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	<c:forEach var="error" items="${errors}">
		<h3 style="font-size:medium; color:red"> ${error} </h3>
	</c:forEach>