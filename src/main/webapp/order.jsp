<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<html>
<head>
<title>Order</title>
    <link href="css/order.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/validate_order.js"></script>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="order" value='${requestScope.order}' />

<div class="main_block">
<div class="order_form">
<h3> Order info</h3>
<div class="form-group row">
    <label for="CustomerName" class="col-sm-2 col-form-label">Customer :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="CustomerName" value="${order.customerName} ${order.customerSurname}">
    </div>
 </div>


<div class="form-group row">
    <label for="Number" class="col-sm-2 col-form-label">Order Number :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Number" name="orderId" value="${order.id}">
    </div>
 </div>

<div class="form-group row">
    <label for="Device" class="col-sm-2 col-form-label">Device :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Device" value="${order.device}">
    </div>
</div>

<div class="form-group row">
    <label for="CustomerComment" class="col-sm-2 col-form-label">Comment :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="CustomerComment" value="${order.comment}">
    </div>
</div>


<div class="form-group row">
    <label for="orderServices" class="col-sm-2 col-form-label">Services :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="orderServices" value="${order.services}">
    </div>
 </div>


<div class="form-group row">
    <label for="creatingTime" class="col-sm-2 col-form-label">Creating Time :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="creatingTime" value="${order.creatingTime}">
    </div>
 </div>

<div class="form-group row">
    <label for="Master" class="col-sm-2 col-form-label">Master :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Master" value="${order.masterName} ${order.masterSurname}">
    </div>
 </div>


<div class="form-group row">
    <label for="Status" class="col-sm-2 col-form-label">Status :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Status" value="${order.status.value}">
    </div>
 </div>



<div class="form-group row">
    <label for="Price" class="col-sm-2 col-form-label">Price :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Price" value="${order.price}">
    </div>
 </div>

<button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>

</div>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>