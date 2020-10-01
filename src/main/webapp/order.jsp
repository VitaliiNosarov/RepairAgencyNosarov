<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<h3>Chose services</h3>
    <c:forEach items="${list}" var="list">
       <tr>
        <td> ${list.name} </td>
        </tr>
    </c:forEach>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>