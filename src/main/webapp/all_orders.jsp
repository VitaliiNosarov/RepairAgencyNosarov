<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css">
    <title>All orders</title>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>
<h3 align="center">All orders info</h3>




<main class="m-3">
    <div class="row col-md-6">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th>ID</th>
                <th>Status</th>
            </tr>

            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.getId()}</td>
                    <td>${order.getStatus()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <nav aria-label="Navigation for countries">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                    href="orders?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                            href="orders?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                    href="orders?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</main>





<!--
<br>
<div class="table">
<table class="table table-striped";>

<c:set var="count" value="1" scope="request" />
<thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">ID</th>
      <th scope="col">Device</th>
      <th scope="col">Comment</th>
      <th scope="col">Services</th>
      <th scope="col">Price</th>
      <th scope="col">Status</th>
      <th scope="col">Customer</th>
      <th scope="col">Master</th>
      <th scope="col">Creating Time</th>
      <th scope="col"></th>
    </tr>
  </thead>
    <c:forEach items="${list}" var="list">

        <tr>
            <form method="Get" action="updateOrder">
            <th scope="row">${count}</th>
            <td> ${list.id} </td>
            <td> ${list.device} </td>
            <td> ${list.comment} </td>
            <td> ${list.services} </td>
            <td> ${list.price} </td>
            <td> ${list.status} </td>
            <td> ${list.customerName} ${list.customerSurname} </td>
            <td> ${list.masterName} ${list.masterSurname}</td>
            <td> ${list.creatingTime} </td>
            <td><button type="submit" name="orderId" value="${list.id}" class="btn btn-info">More info</button></td>
            </form>
        </tr>
        <c:set var="count" value="${count + 1}" scope="request"/>
    </c:forEach>
</table>
</div> -->
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>