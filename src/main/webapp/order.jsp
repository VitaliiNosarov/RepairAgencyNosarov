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

<h3><fmt:message key="order.title"/></h3>

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-info" role="alert">
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
      <input type="text" readonly class="form-control" id="Number" name="orderId" value="${order.id}">
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

<div class="form-group row">
    <label for="Master" class="col-sm-2 col-form-label"><fmt:message key="order.master"/></label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Master" value="${order.masterName} ${order.masterSurname}">
    </div>
 </div>


<div class="form-group row">
    <label for="Status" class="col-sm-2 col-form-label"><fmt:message key="order.status"/></label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Status" value="<ctg:enumTranslate locale="${sessionScope.language}" value="${order.status}"/>">
    </div>
 </div>



<div class="form-group row">
    <label for="Price" class="col-sm-2 col-form-label"><fmt:message key="order.price"/></label>
    <div class="col-sm-10">
    <c:choose>
     <c:when test="${order.price == null}">
       <input type="text" readonly class="form-control" id="Price" value="<fmt:message key="order.price_info_message"/>">
     </c:when>
    <c:otherwise>
       <input type="text" readonly class="form-control" id="Price" value="${order.price}">
            <c:if test="${order.status == 'WAITING_FOR_PAYMENT' && sessionScope.user.id == order.customerId}">
              <form action="payment" method="Post">
                <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info"><fmt:message key="order.pay"/></button></td>
              </form>
            </c:if>

    </c:otherwise>
    </c:choose>

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
            <input type="text" maxlength="300" readonly class="form-control" id="Feedback" value="${feedback.comment}">
          </div>
     </div>
</c:if>

<c:if test="${order.status == 'COMPLETED' && sessionScope.user.id == order.customerId && requestScope.feedback == null}">

    <form action="saveFeedback" method="Post" id="feedback_form">

    <input type="hidden" name="orderId" value="${order.id}">

      <div class="form-group row">
       <label for="Rate" class="col-sm-2 col-form-label"><fmt:message key="order.set_rate"/></label>
        <div class="col-sm-10">
         <select class="custom-select my-1 mr-sm-2" id="Rate" name="rate">
            <option value="GREAT" selected ><ctg:enumTranslate locale="${sessionScope.language}" value="GREAT"/></option>
            <option value="NORMAL" ><ctg:enumTranslate locale="${sessionScope.language}" value="NORMAL"/></option>
            <option value="BAD" ><ctg:enumTranslate locale="${sessionScope.language}" value="BAD"/></option>
         </select>
        </div>
      </div>

      <div class="form-group row">
          <label for="Feedback" class="col-sm-2 col-form-label"><fmt:message key="order.write_feedback"/></label>
          <div class="col-sm-10">
            <textarea class="form-control" id="Feedback" name="comment" default="Like" maxlength = "500"><fmt:message key="order.feedback.placeholder"/></textarea>
          </div>
      </div>

      <button class="btn btn-info" type="submit" class="btn btn-info"><fmt:message key="order.leave_feedback_button"/></button>
    </form>

  </c:if>
    <c:if test="${requestScope.feedback == null}">
       <div class="alert alert-info" role="alert">
            <fmt:message key="order.feedback_message"/>
       </div>
    </c:if>

    <br/>
    <c:if test="${order.status == 'COMPLETED' || order.status == 'PAID' && sessionScope.user.id == order.customerId}">
          <form action="payment" method="Get">
            <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info"><fmt:message key="order.payment_info_button"/></button></td>
          </form>
        </c:if>
    <br/>
    <button class="btn btn-info" type="button" onclick="window.location.href='${PATH}/userOrders'" class="btn btn-info"><fmt:message key="order.back_button"/></button>
</div>
</div>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>