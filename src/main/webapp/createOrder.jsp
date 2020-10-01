<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
<link href="../RepairAgency/css/order.css" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>
<div class="main_block">
<form class="order_form" action="order" method="post">
 <div class="comment_field">
 <textarea id="subject" name="comment" style="height:200px" placeholder="Write your comment"></textarea>

 </div>
 <div class="services">
    <h2>Chose services</h2>
  <c:forEach items="${list}" var="list">
     <td> <h3><input name="service" type="checkbox" value="${list.id}" /> ${list.name}</h3> </td>
  </c:forEach>
    <button class="submit_button" type="submit" value="Submit">Submit</button>
 </div>
</form>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>