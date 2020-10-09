<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<html>
<head>
    <link href="css/order.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/validate_order.js"></script>
      <title>New order</title>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="main_block">
 <form id="order_form" class="order_form" action="create_order" method="post">
    <h3 align="center">Choose appropriate options for order</h3>
    <br/>
    <div class="table_block">
    <table class="table table-striped">

     <c:forEach items="${list}" var="list">
    <tr>
    <th>
        <div class="form-check">
          <input class="form-check-input" type="checkbox" value="${list.id}" default="1" name="service" id="Check" ${list.id == '1' ? 'checked' : ''}>
          <label class="form-check-label" for="Check">
          ${list.name}
          </label>
        </div>
        </th>
    </tr>
    </c:forEach>

    </table>
    </div>
      <div class="form-group">
         <br/>
        <h5 align="center"><label for="userDevice">Write your Device model</label></h5>
        <input type="text" class="form-control" id="userDevice" name="device" placeholder="Device model" required>
        <br/>
        <h5 align="center"><label for="userComment">Write your comment</label></h5>
        <textarea class="form-control" id="userComment" name="comment" rows="3" placeholder="Describe the device malfunction" required></textarea>
      </div>
    <div class="alert alert-info" role="alert">
    The price and other details of the order will be agreed with the manager
    </div>
     <button class="btn btn-info" type="submit" class="btn btn-info">Create</button>
     <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
 </form>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>