<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
<title> <fmt:message key="login.page_title" /> </title>
<link href="../RepairAgency/css/login.css" rel="stylesheet" type="text/css">
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class = "login">
 <form action="login" method="post">
   <div class="form-group">
    <label for="exampleInputEmail1"><fmt:message key="login.email" /></label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="<fmt:message key="login.enter_email" />" name="email" required>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
   </div>
   <div class="form-group">
    <label for="exampleInputPassword1"><fmt:message key="login.password" /></label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="<fmt:message key="login.enter_password" />" name="password" required>
   </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="login.login_button" /></button> <a href="/RepairAgency/registration"><fmt:message key="login.registration" /></a>
 </form>
</div>

    <jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>