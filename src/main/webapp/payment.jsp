<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<html>
<head>
    <link href="css/payment.css" rel="stylesheet" type="text/css">

      <title>Payment</title>
</head>

<body>

<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="order" value='${requestScope.payment}' />

<div class="main_block">
 <div id="order_form" class="order_form" action="create_order" method="get">

<br/>
<h3 align="center">Payment info</h3>
<br/>
<c:choose>
    <c:when test="${empty requestScope.payment}">
        <h4 align="center">Order was paid with cash in office</h4>
    </c:when>
    <c:otherwise>

    <div class="form-group row">
        <label for="Payment number" class="col-sm-2 col-form-label">Order №</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control" id="Payment number" value="${payment.id}">
        </div>
     </div>


    <div class="form-group row">
        <label for="Sum" class="col-sm-2 col-form-label">Sum :</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control" id="Sum" value="${payment.sum}">
        </div>
     </div>

    <div class="form-group row">
        <label for="Time" class="col-sm-2 col-form-label">Creating time :</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control" id="Time" value="${payment.paymentTime}">
        </div>
     </div>

    </c:otherwise>
    </c:choose>

 </div>
 <button align="center" class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
 <br/>
 </div>
<br/>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>