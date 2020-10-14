<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>
<c:set var="language" value="${sessionScope.language}" scope="request"/>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<title>Repair Agency</title>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="intro_inner">
    <h1 class="intro_title">Welcome to <br> computer repair agency<br></h1>
    <a class="consultation_button" href="${PATH}/create_order">Enter</a>
    <ctg:info-time/>
    <ctg:statusTranslate locale="${language}" status="PAID"/>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>