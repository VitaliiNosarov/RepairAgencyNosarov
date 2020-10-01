<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>All users</title>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>
<h3 align="center">All orders info</h3>

<br>
<table class="table table-striped">

<c:set var="count" value="1" scope="request" />
<thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">ID</th>
      <th scope="col">Comment</th>
      <th scope="col">Services</th>
    </tr>
  </thead>
    <c:forEach items="${list}" var="list">

        <tr>
            <th scope="row">${count}</th>
            <td> ${list.id} </td>
            <td> ${list.comment} </td>
            <td> ${list.services} </td>
        </tr>
        <c:set var="count" value="${count + 1}" scope="request"/>
    </c:forEach>
</table>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>