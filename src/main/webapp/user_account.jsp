<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
<title>Account</title>
<link href="${pageContext.request.contextPath}/css/user_account.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="user" value='${sessionScope.user}' />
<div class="line">
<h3>Name :  ${user.name}</h3>
</div>

<div class="line">
<h3>Surname :  ${user.surName}</h3>
</div>

<div class="line">
<h3>Email :  ${user.email}</h3>
</div>

<div class="line">
<h3>Phone :  ${user.phone}</h3>
</div>

<div class="line">
<h3>Balance :  ${user.balance}</h3>
</div>

<div class="line">
<h3>Language :  ${user.locale}</h3>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>