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
    <link href="css/order.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/validate_order.js"></script>
      <title><fmt:message key="create_order.page_title"/></title>
      <link rel="shortcut icon" href="image/icon.png" />
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<div class="main_block">
 <form id="order_form" class="order_form" action="create_order" method="post">
    <h3 align="center"><fmt:message key="create_order.title"/></h3>
    <br/>
    <div class="table_block">
    <table class="table table-striped">

        <c:choose>
           <c:when test="${sessionScope.language == 'RU'}">
                 <c:forEach items="${services}" var="office">
                   <tr>
                         <th>
                           <div class="form-check">
                             <input class="form-check-input" type="checkbox" value="${office.id}" default="1" name="service" id="Check" ${office.id == '1' ? 'checked' : ''}>
                             <label class="form-check-label" for="Check">
                             ${office.nameRu}
                             </label>
                           </div>
                         </th>
                       </tr>
                 </c:forEach>
           </c:when>
               <c:otherwise>
                  <c:forEach items="${services}" var="office">
                 <tr>
                       <th>
                         <div class="form-check">
                           <input class="form-check-input" type="checkbox" value="${office.id}" default="1" name="service" id="Check" ${office.id == '1' ? 'checked' : ''}>
                           <label class="form-check-label" for="Check">
                           ${office.nameEn}
                           </label>
                         </div>
                       </th>
                     </tr>
                  </c:forEach>
               </c:otherwise>
        </c:choose>


    </table>
    </div>
      <div class="form-group">
         <br/>
        <h5 align="center"><label for="userDevice"><fmt:message key="create_order.device"/></label></h5>
        <input type="text" class="form-control" id="userDevice" name="device" minlength="5" maxlength = "60" placeholder="<fmt:message key="create_order.device_placeholder"/>" required>
        <br/>
        <h5 align="center"><label for="userComment"><fmt:message key="create_order.comment"/></label></h5>
        <textarea class="form-control" id="userComment" name="comment" rows="3" minlength="10" maxlength = "200" placeholder="<fmt:message key="create_order.comment_placeholder"/>" required></textarea>
      </div>
    <div class="alert alert-info" role="alert">
        <fmt:message key="create_order.info_message_bottom"/>
    </div>
     <button class="btn btn-info" type="submit" class="btn btn-info"><fmt:message key="create_order.create_button"/></button>
     <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info"><fmt:message key="create_order.back_button"/></button>
 </form>
</div>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>