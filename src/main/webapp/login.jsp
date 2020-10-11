<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title> <fmt:message key="login.page_title" /> </title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/validate_login.js"></script>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class = "login">

<h1 align="center"> Log in </h1>

<c:if test="${sessionScope.infoMessage != null}">
     <div class="alert alert-danger" role="alert">
            <center>${infoMessage}</center>
            <c:remove var="infoMessage"/>
     </div>
</c:if>

<c:if test="${sessionScope.infoMessageSuccess != null}">
     <div class="alert alert-success" role="alert">
            <center>${infoMessageSuccess}</center>
            <c:remove var="infoMessageSuccess"/>
     </div>
</c:if>

 <form id="login_form" action="login" method="post" >
   <div class="form-group">
    <label for="email"><fmt:message key="login.email" /></label>
    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="<fmt:message key="login.enter_email" />" name="email" required>
    <span class="error" aria-live="polite"></span>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
   </div>
   <div class="form-group">
    <label for="password"><fmt:message key="login.password" /></label>
    <input type="password" class="form-control" id="password" placeholder="<fmt:message key="login.enter_password" />" name="password" required>
   </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="login.login_button"/></button> <a href="/RepairAgency/registration"><fmt:message key="login.registration" /></a>
 </form>
</div>

    <jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>