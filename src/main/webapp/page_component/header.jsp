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
<link href="../RepairAgency/css/header.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>

<body>
<header>
  <a href="index.jsp" class="logo">RepairAgency</a>
   <div class="language">
        <a href="?locale=RU"><fmt:message key="language.russian" /></a>
        <a href="?locale=EN"><fmt:message key="language.english" /></a>
            <c:choose>
            <c:when test="${empty sessionScope.user}">
                <a href="login">Log in</a>
            </c:when>
            <c:otherwise>
                <a href="logout">Log out</a>
            </c:otherwise>
            </c:choose>
    </div>
   <div class="right-header">
    <a href="#contact">Home</a>
    <a href="#contact">Contact</a>
    <a href="#my account">My account</a>
  </div>
  </header>
</body>
</html>