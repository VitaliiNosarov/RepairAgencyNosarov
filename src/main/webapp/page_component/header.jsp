<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<c:set var="PATH" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
</head>

<body>
<header>

  <a href="${PATH}/index.jsp" class="logo" ><font color="#0a91ab">Computer</font> <br/><font color="#ffc045">RepairAgency</font></a>

    <c:if test="${sessionScope.user.role == 'ADMIN'}">
      <div class="order_count_info">
          <div class="alert alert-info" role="alert">
          <a href="${PATH}/orders?currentPage=1&filter=STATUS&filterParam=WAITING_FOR_PROCESSING&recordsPerPage=10&orderBy=STATUS&reverse=false">
           <fmt:message key="new.orders.admin.message"/> : <h:font number="${applicationScope.countOfNewOrders}"/></a>
          </div>
      </div>
    </c:if>

   <div class="right-header">

    <c:choose>
    <c:when test="${empty sessionScope.user}">
        <a href="${PATH}/login" class="header_link"><fmt:message key="login.login_button"/></a>
    </c:when>
    <c:otherwise>
        <a href="${PATH}/logout" class="header_link"><fmt:message key="header.logout"/></a>
    </c:otherwise>
    </c:choose>

    <c:if test="${sessionScope.user.role == 'ADMIN'}">
      <a href="${PATH}/users?currentPage=1&recordsPerPage=10" class="header_link"><fmt:message key="header.all_users.link"/></a>
      <a href="${PATH}/orders?currentPage=1&recordsPerPage=10&orderBy=CREATING_TIME&reverse=true" class="header_link"><fmt:message key="header.all_orders.link"/></a>
      <a href="${PATH}/serviceManager" class="header_link"><fmt:message key="header.services.link"/></a>
    </c:if>

    <a href="${PATH}/createOrder" class="header_link"><fmt:message key="header.new_order.link"/></a>

    <c:choose>
         <c:when test="${sessionScope.user.role == 'MASTER'}">
             <a href="${PATH}/orders?currentPage=1&recordsPerPage=10&orderBy=CREATING_TIME&reverse=true&filter=MASTER&filterParam=${user.id}"
              class="header_link"><fmt:message key="header.my_orders.link"/></a>
         </c:when>
         <c:otherwise>
             <a href="${PATH}/userOrders" class="header_link"><fmt:message key="header.my_orders.link"/></a>
         </c:otherwise>
    </c:choose>

    <a href="${PATH}/updateUser" class="header_link"><fmt:message key="header.my_account.link"/></a>

    <div class="language">
       <form>
           <label for="locale"></label>
           <select id="locale" class="form-control" name="language" onchange="submit()">
           <option value="EN" ${language == 'EN' ? 'selected' : ''}><fmt:message key="header.language.english"/></option>
           <option value="RU" ${language == 'RU' ? 'selected' : ''}><fmt:message key="header.language.russian"/></option>
           </select>
       </form>
    </div>

   </div>
  </header>
</body>
</html>