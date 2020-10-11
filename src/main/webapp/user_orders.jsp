<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<html>
<head>
    <link href="css/user_orders.css" rel="stylesheet" type="text/css">

      <title>New order</title>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="main_block">
 <div id="order_form" class="order_form" action="create_order" method="get">
    <h3 align="center">My orders</h3>
    <br/>

        <c:if test="${sessionScope.infoMessage != null}">
             <div class="alert alert-success" role="alert">
                    <center>${infoMessage}</center>
                    <c:remove var="infoMessage"/>
             </div>
        </c:if>

<div class="order_table">
<table class="table table-striped">
            <tr>
              <th scope="col">№</th>
              <th scope="col">Device</th>
              <th scope="col">Comment</th>
              <th scope="col">Services</th>
              <th scope="col">Price</th>
              <th scope="col">Status</th>
              <th scope="col">Master</th>
              <th scope="col">Creating Time</th>
              <th scope="col"></th>
            </tr>

            <c:forEach items="${orders}" var="order">
                <tr>
                    <form method="Get" action="order">
                        <td> ${order.id} </td>
                        <td> ${order.device} </td>
                        <td> ${order.comment} </td>
                        <td> ${order.services} </td>
                        <td> ${order.price} </td>
                        <td> ${order.status.value} </td>
                        <td> ${order.masterName} ${order.masterSurname}</td>
                        <td> ${order.creatingTime} </td>
                        <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info">More info</button></td>
                        </form>
                </tr>
            </c:forEach>
        </table>

 </div>
 <br/>
 <button align="center" class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
 </div>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>