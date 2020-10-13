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

<c:set var="order" value='${requestScope.order}' />

<div class="main_block">
<form class="order_form" method="Post" action="updateOrder">

<h3 align="center"> Order info</h3>

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-success" role="alert">
                <center>${infoMessage}</center>
                <c:remove var="infoMessage"/>
         </div>
    </c:if>

<div class="form-group row">
    <label for="CustomerName" class="col-sm-2 col-form-label">Customer :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="CustomerName" value="${order.customerName} ${order.customerSurname}">
    </div>
 </div>


<div class="form-group row">
    <label for="Number" class="col-sm-2 col-form-label">Order № :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="orderId" name="orderId" value="${order.id}">
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
    <label for="creatingTime" class="col-sm-2 col-form-label">Was created :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="creatingTime" value="${order.creatingTime}">
    </div>
 </div>

<c:if test="${requestScope.feedback != null}">
      <div class="form-group row">
           <label for="Rate" class="col-sm-2 col-form-label">Rate :</label>
           <div class="col-sm-10">
             <input type="text" readonly class="form-control" id="Rate" value="${feedback.rate}">
           </div>
      </div>
      <div class="form-group row">
          <label for="Feedback" class="col-sm-2 col-form-label">Feedback :</label>
          <div class="col-sm-10">
            <input type="text" readonly class="form-control" id="Feedback" value="${feedback.comment}">
          </div>
     </div>
</c:if>

<div class="form-group row">
 <label for="Master" class="col-sm-2 col-form-label">Master :</label>
 <div class="col-sm-10">
 <c:choose>
       <c:when test="${sessionScope.user.role == 'ADMIN'}">
           <select class="custom-select my-1 mr-sm-2" id="Master" name="masterId">
               <option selected value="${order.masterId}">${order.masterName} ${order.masterSurname}</option>
                <c:forEach items="${masters}" var="master">
                <c:if test="${order.masterId != master.id}">
                         <option value="${master.id}">${master.name} ${master.surName}</option>
                </c:if>
               </c:forEach>
            </select>
       </c:when>
       <c:otherwise>
           <input type="text" id="Master" readonly class="form-control" id="Master" value="${order.masterName} ${order.masterSurname}">
           <input type="hidden" name="masterId" value="${order.masterId}">
       </c:otherwise>
     </c:choose>

  </div>
</div>


<div class="form-group row">
 <label for="Price" class="col-sm-2 col-form-label">Price :</label>
  <div class="col-sm-10">
    <c:choose>
      <c:when test="${sessionScope.user.role == 'ADMIN'}">
          <input type="text" min="0" class="form-control" id="Price" name="price" value="${order.price}" placeholder="${order.price}">
      </c:when>
      <c:otherwise>
          <input type="text" readonly class="form-control" id="Price" name="price" value="${order.price}" placeholder="${order.price}">
      </c:otherwise>
    </c:choose>
  </div>
</div>



<div class="form-group row">
 <label for="Status" class="col-sm-2 col-form-label">Status :</label>
  <div class="col-sm-10">

   <select class="custom-select my-1 mr-sm-2" id="Status" name="status" >
      <c:if test="${sessionScope.user.role == 'ADMIN'}">
          <option value="WAITING_FOR_PAYMENT" ${order.status == 'WAITING_FOR_PAYMENT' ? 'selected' : ''}>Waiting for payment</option>
          <option value="WAITING_FOR_PROCESSING" ${order.status == 'WAITING_FOR_PROCESSING' ? 'selected' : ''}>Waiting for processing</option>
          <option value="PAID" ${order.status == 'PAID' ? 'selected' : ''}>Paid</option>
          <option value="CANCELED" ${order.status == 'CANCELED' ? 'selected' : ''}>Canceled</option>
          <option value="COMPLETED" ${order.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
      </c:if>
      <c:if test="${sessionScope.user.role == 'MASTER' && order.masterId == sessionScope.user.id}">
          <option value="IN_WORK" ${order.status == 'IN_WORK' ? 'selected' : ''}>In work</option>
          <option value="READY_TO_ISSUE" ${order.status == 'READY_TO_ISSUE' ? 'selected' : ''}>Ready to issue</option>
      </c:if>
   </select>
  </div>
</div>

<button class="btn btn-info" type="submit" class="btn btn-info">Update</button>
<button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
</form>

<c:if test="${order.status == 'PAID' || order.status == 'COMPLETED' && sessionScope.user.role == 'ADMIN'}">
  <form action="payment" method="Get" class="payment_info">
    <button class="btn btn-info" type="submit" name="orderId" value="${order.id}" class="btn btn-info">Payment Info</button>
  </form>
</c:if>

</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>