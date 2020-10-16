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

<div class="content">

<c:set var="order" value='${requestScope.order}' />
<c:set var="PATH" value="${pageContext.request.contextPath}" scope="request"/>

<div class="main_block">
<div class="order_form">

<h3> Order info</h3>

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-info" role="alert">
                <center>${infoMessage}</center>
                <c:remove var="infoMessage"/>
         </div>
    </c:if>

<div class="form-group row">
    <label for="CustomerName" class="col-sm-2 col-form-label">Customer :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="CustomerName" value="${order.customerName} ${order.customerSurname}">
    </div>
 </div>


<div class="form-group row">
    <label for="Number" class="col-sm-2 col-form-label">Order № :</label>
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
    <label for="creatingTime" class="col-sm-2 col-form-label">Was created :</label>
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
      <input type="text" readonly class="form-control" id="Status" value="${order.status}">
    </div>
 </div>



<div class="form-group row">
    <label for="Price" class="col-sm-2 col-form-label">Price :</label>
    <div class="col-sm-10">
    <c:choose>
     <c:when test="${order.price == null}">
       <input type="text" readonly class="form-control" id="Price" value="The price will be set by the manager after processing">
     </c:when>
    <c:otherwise>
       <input type="text" readonly class="form-control" id="Price" value="${order.price}">
            <c:if test="${order.status == 'WAITING_FOR_PAYMENT' && sessionScope.user.id == order.customerId}">
              <form action="payment" method="Post">
                <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info">Pay</button></td>
              </form>
            </c:if>

    </c:otherwise>
    </c:choose>

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
            <input type="text" maxlength="300" readonly class="form-control" id="Feedback" value="${feedback.comment}">
          </div>
     </div>
</c:if>

<c:if test="${order.status == 'COMPLETED' && sessionScope.user.id == order.customerId && requestScope.feedback == null}">

    <form action="saveFeedback" method="Post" id="feedback_form">

    <input type="hidden" name="orderId" value="${order.id}">

      <div class="form-group row">
       <label for="Rate" class="col-sm-2 col-form-label">Feedback Rate :</label>
        <div class="col-sm-10">
         <select class="custom-select my-1 mr-sm-2" id="Rate" name="rate">
            <option value="GREAT" selected >Great</option>
            <option value="NORMAL" >Normal</option>
            <option value="BAD" >Bad</option>
         </select>
        </div>
      </div>

      <div class="form-group row">
          <label for="Feedback" class="col-sm-2 col-form-label">Leave your feedback :</label>
          <div class="col-sm-10">
            <textarea class="form-control" id="Feedback" name="comment" default="Like" maxlength = "500">Like</textarea>
          </div>
      </div>

      <button class="btn btn-info" type="submit" class="btn btn-info">Leave feedback</button>
    </form>

  </c:if>
    <c:if test="${requestScope.feedback == null}">
       <div class="alert alert-info" role="alert">
       You can leave feedback after the order is completed.
       </div>
    </c:if>

    <br/>
    <c:if test="${order.status == 'COMPLETED' || order.status == 'PAID' && sessionScope.user.id == order.customerId}">
          <form action="payment" method="Get">
            <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info">Payment Info</button></td>
          </form>
        </c:if>
    <br/>
    <button class="btn btn-info" type="button" onclick="window.location.href='${PATH}/userOrders'" class="btn btn-info">Back</button>
</div>
</div>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>