<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<c:set var="PATH" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
    <title> <fmt:message key="login.page_title" /> </title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/validate_login.js"></script>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<div class = "login">
<div class="wrapper fadeInDown">
  <div id="formContent">
  <br/>
    <h2><font color="black"><fmt:message key="login.page_title" /></font> </h2>

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


    <!-- Login Form -->
    <form id="login_form" action="login" method="post" >

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

      <input type="email" id="email" maxlength = "25" class="fadeIn second" name="email" placeholder="<fmt:message key="login.enter_email" />" required>
      <br/>
      <input type="password" id="password" maxlength = "30" class="fadeIn third" name="password" placeholder="<fmt:message key="login.enter_password" />" required>
      <br/>
      <input type="submit" class="fadeIn fourth" value="<fmt:message key="login.login_button"/>">
    </form>


    <div id="formFooter">
      <a class="underlineHover" href="${PATH}/registration"><fmt:message key="login.registration" /></a>
    </div>

  </div>
</div>

</div>
</div>

    <jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>