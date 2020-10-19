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
<title><fmt:message key="order.page_title"/></title>
<link rel="shortcut icon" href="image/icon.png" />
    <script src="js/jquery-3.5.1.min.js"></script>
     <script src="js/validate_price.js"></script>
     <link href="css/order.css" rel="stylesheet" type="text/css">
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<c:set var="order" value='${requestScope.order}' />

<div class="main_block">
<form class="order_form" method="Post" action="updateOrder" id="order_form">

<h3 align="center"> <fmt:message key="order.title"/> </h3>

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-success" role="alert">
                <center><ctg:enumTranslate locale="${sessionScope.language}" value="${infoMessage}"/></center>
                <c:remove var="infoMessage"/>
         </div>
    </c:if>

<div class="form-group row">
    <label for="CustomerName" class="col-sm-2 col-form-label"><fmt:message key="order.customer"/></label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="CustomerName" value="${order.customerName} ${order.customerSurname}">
    </div>
 </div>


<div class="form-group row">
    <label for="orderServices" class="col-sm-2 col-form-label"><fmt:message key="order.services"/></label>
    <div class="col-sm-10" id="orderServices">|
            <c:choose>
               <c:when test="${sessionScope.language == 'RU'}">
                     <c:forEach items="${order.services}" var="service">
                       ${service.nameRu} |
                     </c:forEach>
               </c:when>
                   <c:otherwise>
                      <c:forEach items="${order.services}" var="service">
                         ${service.nameEn} |
                      </c:forEach>
                   </c:otherwise>
            </c:choose>
    </div>
</div>


<div class="form-group row">
    <label for="Number" class="col-sm-2 col-form-label">№</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="orderId" name="orderId" value="${order.id}">
    </div>
 </div>

<div class="form-group row">
    <label for="Device" class="col-sm-2 col-form-label"><fmt:message key="order.device"/></label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Device" value="${order.device}">
    </div>
</div>

<div class="form-group row">
    <label for="CustomerComment" class="col-sm-2 col-form-label"><fmt:message key="order.comment"/></label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="CustomerComment" value="${order.comment}">
    </div>
</div>

<div class="form-group row">
    <label for="creatingTime" class="col-sm-2 col-form-label"><fmt:message key="order.creating_time"/></label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="creatingTime" value="${order.creatingTime}">
    </div>
 </div>

<c:if test="${requestScope.feedback != null}">
      <div class="form-group row">
           <label for="Rate" class="col-sm-2 col-form-label"><fmt:message key="order.rate"/></label>
           <div class="col-sm-10">
             <input type="text" readonly class="form-control" id="Rate" value="${feedback.rate}">
           </div>
      </div>
      <div class="form-group row">
          <label for="Feedback" class="col-sm-2 col-form-label"><fmt:message key="order.feedback"/></label>
          <div class="col-sm-10">
            <input type="text" readonly class="form-control" id="Feedback" value="${feedback.comment}">
          </div>
     </div>
</c:if>

<div class="form-group row">
 <label for="Master" class="col-sm-2 col-form-label"><fmt:message key="order.master"/></label>
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
 <label for="price" class="col-sm-2 col-form-label"><fmt:message key="order.price"/></label>
  <div class="col-sm-10">
    <c:choose>
      <c:when test="${sessionScope.user.role == 'ADMIN'}">
          <input type="number" min="0" maxlength = "300" maxlength="5" step="any" class="form-control" id="price" name="price" value="${order.price}" placeholder="${order.price}">
      </c:when>
      <c:otherwise>
          <input type="text" readonly class="form-control" id="price" name="price" value="${order.price}" placeholder="${order.price}">
      </c:otherwise>
    </c:choose>
  </div>
</div>



<div class="form-group row">
 <label for="Status" class="col-sm-2 col-form-label"><fmt:message key="order.status"/></label>
  <div class="col-sm-10">

      <c:if test="${sessionScope.user.role == 'ADMIN'}">
           <select class="custom-select my-1 mr-sm-2" id="Status" name="status" >
             <option value="WAITING_FOR_PAYMENT" ${order.status == 'WAITING_FOR_PAYMENT' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="WAITING_FOR_PAYMENT"/></option>
             <option value="WAITING_FOR_PROCESSING" ${order.status == 'WAITING_FOR_PROCESSING' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="WAITING_FOR_PROCESSING"/></option>
             <option value="PAID" ${order.status == 'PAID' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="PAID"/></option>
             <option value="CANCELED" ${order.status == 'CANCELED' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="CANCELED"/></option>
             <option value="COMPLETED" ${order.status == 'COMPLETED' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="COMPLETED"/></option>
             <option value="IN_WORK" ${order.status == 'IN_WORK' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="IN_WORK"/></option>
             <option value="READY_TO_ISSUE" ${order.status == 'READY_TO_ISSUE' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="READY_TO_ISSUE"/></option>
          </select>
      </c:if>
      <c:if test="${sessionScope.user.role == 'MASTER' && order.masterId == sessionScope.user.id}">
      <c:choose>
               <c:when test="${order.status == 'IN_WORK' || order.status == 'READY_TO_ISSUE'|| order.status == 'PAID'}">
                     <select class="custom-select my-1 mr-sm-2" id="Status" name="status" >
                          <option value="PAID" ${order.status == 'PAID' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="PAID"/></option>
                          <option value="IN_WORK" ${order.status == 'IN_WORK' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="PAID"/></option>
                          <option value="READY_TO_ISSUE" ${order.status == 'READY_TO_ISSUE' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="READY_TO_ISSUE"/></option>
                     </select>
               </c:when>
               <c:otherwise>
                   <input type="text" readonly class="form-control" id="Status" name="status" value="${order.status}" placeholder="${order.status}">
               </c:otherwise>
      </c:choose>
      </c:if>

  </div>
</div>

<button class="btn btn-info" type="submit" class="btn btn-info"><fmt:message key="order.update_button"/></button>
<button class="btn btn-info" type="button" onclick="window.location.href='${PATH}/orders?currentPage=1&recordsPerPage=10&orderBy=CREATING_TIME&reverse=true'"
  class="btn btn-info"><fmt:message key="order.back_button"/></button>
</form>

<c:if test="${order.status == 'PAID' || order.status == 'COMPLETED' && sessionScope.user.role == 'ADMIN'}">
  <form action="payment" method="Get" class="payment_info">
    <button class="btn btn-info" type="submit" name="orderId" value="${order.id}" class="btn btn-info"><fmt:message key="order.payment_info_button"/></button>
  </form>
</c:if>

</div>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>