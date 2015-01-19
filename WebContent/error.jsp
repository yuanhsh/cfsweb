<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="error-list.jsp" />

<html>
<head>
<title>Error Page For Examples</title>
</head>
<body bgcolor="white">
Invalid username and/or password, please try
<a href='<%= response.encodeURL("index.jsp") %>'>again</a>.
</body>
</html>
