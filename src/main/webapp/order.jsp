<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
<title>Order</title>
<link href="${pageContext.request.contextPath}/css/order.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="user" value='${requestScope.order}' />

<form method="Post" action="order">
<div class="line">
<h3>Customer :  ${requestScope.customer}</h3>
</div>

<div class="line">
<h3>Order Id :  ${order.id}</h3>
</div>

<div class="line">
<h3>Email :  ${order.status}</h3>
</div>

<div class="line">
<h3>Comment :  ${order.comment}</h3>
</div>

<div class="line">
<h3>Services :  ${order.services}</h3>
</div>

<div class="line">
<h3>Creating time :  ${order.creatingTime}</h3>
</div>

<div class="line">
<h3>Master :  ${requestScope.master}</h3>
</div>

<div class="line">
<h3>Price :  ${requestScope.price}</h3>
</div>

<button class="btn btn-info" type="submit" class="btn btn-info">Update</button>
<button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
</form>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>