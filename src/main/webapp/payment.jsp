<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
        <link href="css/payment.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="image/icon.png" />
        <title><fmt:message key="payment.page_title"/></title>
</head>

<body>

<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<c:set var="order" value='${requestScope.payment}' />

<div class="main_block">
 <div id="order_form" class="order_form" action="create_order" method="get">

<br/>
<h3 align="center"><fmt:message key="payment.title"/></h3>
<br/>
<c:choose>
    <c:when test="${empty requestScope.payment}">
        <h4 align="center"><fmt:message key="payment.info_message"/></h4>
    </c:when>
    <c:otherwise>

    <div class="form-group row">
        <label for="Payment number" class="col-sm-2 col-form-label"><fmt:message key="payment.order_number"/></label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control" id="Payment number" value="${payment.id}">
        </div>
     </div>


    <div class="form-group row">
        <label for="Sum" class="col-sm-2 col-form-label"><fmt:message key="payment.sum"/></label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control" id="Sum" value="${payment.sum}">
        </div>
     </div>

    <div class="form-group row">
        <label for="Time" class="col-sm-2 col-form-label"><fmt:message key="payment.creating_time"/></label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control" id="Time" value="${payment.paymentTime}">
        </div>
     </div>

    </c:otherwise>
    </c:choose>

 </div>
 <button align="center" class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info"><fmt:message key="payment.back_button"/></button>
 <br/>

 </div>
 </div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>