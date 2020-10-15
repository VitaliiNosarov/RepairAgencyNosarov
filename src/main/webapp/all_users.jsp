<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css">
    <title>All users</title>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">
    
<br/>
<h3 align="center">All users info</h3>
<br/>

<table class="table table-striped">

<c:set var="count" value="1" scope="request" />
    <tr>
      <th scope="col">#</th>
      <th scope="col">User name</th>
      <th scope="col">Email</th>
      <th scope="col">Phone</th>
      <th scope="col">Balance</th>
      <th scope="col">Role</th>
      <th scope="col">Locale</th>
      <th scope="col"></th>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <form method="Get" action="updateUser">
                <th scope="row">${count}</th>
                <td> ${list.name} ${list.surName}</td>
                <td> ${list.email} </td>
                <td> ${list.phone} </td>
                <td> ${list.balance} </td>
                <td> <ctg:enumTranslate locale="${sessionScope.language}" value="${list.role}"/></td>
                <td> ${list.locale} </td>
                <td><button type="submit" name="accountId" value="${list.id}" class="btn btn-info">Update</button></td>
            </form>
        </tr>
        <c:set var="count" value="${count + 1}" scope="request"/>
    </c:forEach>
</table>
<nav aria-label="Navigation for countries">
        <ul class="pagination_down">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                    href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                            href="users?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                    href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
<br/>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>