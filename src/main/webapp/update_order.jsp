<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
<title>Order</title>
<link href="css/order.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="user" value='${requestScope.order}' />

<div class="main_block">
<form class="order_form" method="Post" action="order">

<div class="form-group row">
    <label for="CustomerName" class="col-sm-2 col-form-label">Customer</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="CustomerName" value="${requestScope.customer}">
    </div>
 </div>


<div class="form-group row">
    <label for="orderId" class="col-sm-2 col-form-label">Order Id</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="orderId" value="${order.id}">
    </div>
 </div>

<div class="form-group row">
    <label for="CustomerComment" class="col-sm-2 col-form-label">Comment</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="CustomerComment" value="${order.comment}">
    </div>
 </div>


<div class="form-group row">
    <label for="orderServices" class="col-sm-2 col-form-label">Services</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="orderServices" value="${order.services}">
    </div>
 </div>


<div class="form-group row">
    <label for="creatingTime" class="col-sm-2 col-form-label">Creating Time</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="creatingTime" value="${order.creatingTime}">
    </div>
 </div>

<div class="form-group row">
 <label for="Master" class="col-sm-2 col-form-label">Master</label>
  <div class="col-sm-10">
  <input type="email" class="form-control" id="Master" placeholder="${requestScope.master}">
  </div>
</div>


<div class="form-group row">
 <label for="Price" class="col-sm-2 col-form-label">Price</label>
  <div class="col-sm-10">
  <input type="email" class="form-control" id="Price" placeholder="${requestScope.price}">
  </div>
</div>


<div class="form-group row">
 <label for="Status" class="col-sm-2 col-form-label">Status</label>
  <div class="col-sm-10">
  <input type="email" class="form-control" id="Status" placeholder="${requestScope.status}">
  </div>
</div>

<button class="btn btn-info" type="submit" class="btn btn-info">Update</button>
<button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>

</form>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>