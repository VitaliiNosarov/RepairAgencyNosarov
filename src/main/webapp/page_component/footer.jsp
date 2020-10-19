<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>

<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" type="text/css">
</head>
<body>

<footer>
  <p>Author: Vitalii Nosarov<br>
  <a href="mailto:vitalii@gmail.com">vitalii@gmail.com</a></p>
</footer>
</body>
</html>