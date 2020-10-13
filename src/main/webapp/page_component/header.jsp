<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<c:set var="PATH" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>

<body>
<header>

  <a href="${PATH}/index.jsp" class="logo" ><font color="#0a91ab">Computer</font> <br/><font color="#ffc045">RepairAgency</font></a>
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
      <a href="${PATH}/users?currentPage=1&recordsPerPage=10" class="header_link">All users</a>
    </c:if>
    <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'MASTER'}">
          <a href="${PATH}/orders?currentPage=1&recordsPerPage=10&orderBy=CREATING_TIME&reverse=false" class="header_link">All orders</a>
        </c:if>
    <a href="${PATH}/create_order" class="header_link">New order</a>
     <a href="${PATH}/userOrders" class="header_link">My orders</a>
    <a href="${PATH}/updateAccount" class="header_link">My account</a>
    <div class="language">
       <form>
           <label for="locale"></label>
           <select id="locale" class="form-control" name="language" onchange="submit()">
           <option value="EN" ${language == 'EN' ? 'selected' : ''}><fmt:message key="language.english"/></option>
           <option value="RU" ${language == 'RU' ? 'selected' : ''}><fmt:message key="language.russian"/></option>
           </select>
       </form>
       </div>

   </div>
  </header>
</body>
</html>