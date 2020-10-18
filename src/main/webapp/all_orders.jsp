<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>
<c:set var="PATH" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css">
    <title>All orders</title>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<br/>
<h3 align="center">All orders info</h3>
<br/>

<div class="table">

  <div class="toolbar">
    <form action="orders" method="Get">
        <input type="hidden" name="orderBy" value="${orderBy}">
        <input type="hidden" name="currentPage" value="${currentPage}">
        <input type="hidden" name="reverse" value="${reverse}">
        <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">

           <label class="filter_block" for="Filter"> Filter </label>
            <select class="filter_block" name="filter" id="Filter" onchange="submit()">
              <option value="" ${filter == null ? 'selected' : ''}>None</option>
                <option value="STATUS" ${filter == 'STATUS' ? 'selected' : ''}>Status</option>
                <option value="MASTER" ${filter == 'MASTER' ? 'selected' : ''}>Master</option>
            </select>
    </form>

    <form action="orders" method="Get">
            <input type="hidden" name="currentPage" value="${currentPage}">
            <input type="hidden" name="filter" value="${filter}">

            <c:if test="${requestScope.filter == 'MASTER'||requestScope.filter == 'STATUS'}">
                <c:if test="${requestScope.filter == 'MASTER'}">
                <select name="filterParam" onchange="submit()">
                <option value="" ${filterParam == null ? 'selected' : ''}>None</option>
                    <c:forEach items="${masters}" var="master">
                       <option value="${master.id}" ${requestScope.filterParam == master.id ? 'selected' : ''}>${master.name} ${master.surName}</option>
                   </c:forEach>
                    </select>
                </c:if>
                <c:if test="${requestScope.filter == 'STATUS'}">
                <select name="filterParam" onchange="submit()">
                <option value="" ${filterParam == null ? 'selected' : ''}>None</option>
                   <c:forEach items="${statuses}" var="status">
                      <option value="${status}" ${requestScope.filterParam == status ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="${status}"/></option>
                  </c:forEach>
                </select>
                </c:if>
            </c:if>


          <label for="ex">  Records on page </label>
          <input id="ex" type="number" name="recordsPerPage" value="${recordsPerPage}" min="1" max="50">

       <label for="orderBy"> Sort by </label>
         <select name="orderBy" id="orderBy">
           <option value="PRICE" ${orderBy == 'PRICE' ? 'selected' : ''}>Price</option>
           <option value="CREATING_TIME" ${orderBy == 'CREATING_TIME' ? 'selected' : ''}>Creating Time</option>
           <option value="STATUS" ${orderBy == 'STATUS' ? 'selected' : ''}>Status</option>
         </select>
       <label for="Reverse"> Order </label>
         <select name="reverse" id="Reverse">
           <option value="true" ${reverse == 'true' ? 'selected' : ''}>Descending</option>
           <option value="false" ${reverse == 'false' ? 'selected' : ''}>Ascending</option>
         </select>
           <input type="submit" value="Sort">

           <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <a class="download_link" href="${PATH}/orders_download">  Download report</a>
           </c:if>

    </form>

  </div>

        <table class="table table-striped">
            <tr>
              <th scope="col">№</th>
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

            <c:forEach items="${orders}" var="order">
                <tr>
                    <form method="Get" action="updateOrder">
                        <td> ${order.id} </td>
                        <td> ${order.device} </td>
                        <td> ${order.comment} </td>
                        <td> ${order.services} </td>
                        <td> ${order.price} </td>
                        <td> <ctg:enumTranslate locale="${sessionScope.language}" value="${order.status}"/></td>
                        <td> ${order.customerName} ${order.customerSurname} </td>
                        <td> ${order.masterName} ${order.masterSurname}</td>
                        <td> ${order.creatingTime} </td>
                        <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info">More info</button></td>
                        </form>
                </tr>
            </c:forEach>
        </table>

    <nav aria-label="Navigation for countries">
        <ul class="pagination_down">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                    href="orders?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&orderBy=CREATING_TIME&reverse=false&filter=${filter}&filterParam=${filterParam}">Previous</a>
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
                            href="orders?recordsPerPage=${recordsPerPage}&currentPage=${i}&orderBy=${orderBy}&reverse=${reverse}&filter=${filter}&filterParam=${filterParam}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                    href="orders?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&orderBy=${orderBy}&reverse=${reverse}&filter=${filter}&filterParam=${filterParam}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>