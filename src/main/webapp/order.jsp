<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="order" value='${requestScope.order}' />
<div class="line">
<h3>ID :  ${order.id}</h3>
</div>

<div class="line">
<h3>Status :  ${order.status}</h3>
</div>

<div class="line">
<h3>Creating time :  ${order.creatingTime}</h3>
</div>

<div class="line">
<h3>Price :  ${order.price}</h3>
</div>

<div class="line">
<h3>Services :  ${order.services}</h3>
</div>

<div class="line">
<h3>Client comment :  ${order.comment}</h3>
</div>

<div class="line">
<h3>PaymentId :  ${order.paymentId}</h3>
</div>

<div class="line">
<h3>Master :  ${order.masterId}</h3>
</div>

<div class="line">
<h3>Customer :  ${order.customerId}</h3>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>