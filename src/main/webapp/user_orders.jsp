<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link href="css/user_orders.css" rel="stylesheet" type="text/css">
    <title><fmt:message key="user_orders.page_title"/></title>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

    <div class="content">

    <div class="main_block">
     <div id="order_form" class="order_form" action="create_order" method="get">
        <h3 align="center"><fmt:message key="user_orders.page_title"/></h3>
        <br/>

            <c:if test="${sessionScope.infoMessage != null}">
                 <div class="alert alert-success" role="alert">
                        <center><ctg:enumTranslate locale="${sessionScope.language}" value="${infoMessage}"/></center>
                        <c:remove var="infoMessage"/>
                 </div>
            </c:if>

    <div class="order_table">
    <table class="table table-striped">

                <tr>
                  <th scope="col">№</th>
                  <th scope="col"><fmt:message key="user_orders_table.device"/></th>
                  <th scope="col"><fmt:message key="user_orders_table.comment"/></th>
                  <th scope="col"><fmt:message key="user_orders_table.services"/></th>
                  <th scope="col"><fmt:message key="user_orders_table.price"/></th>
                  <th scope="col"><fmt:message key="user_orders_table.status"/></th>
                  <th scope="col"><fmt:message key="user_orders_table.master"/></th>
                  <th scope="col"><fmt:message key="user_orders_table.creating_time"/></th>
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
                            <td> <ctg:enumTranslate locale="${sessionScope.language}" value="${order.status}"/></td>
                            <td> ${order.masterName} ${order.masterSurname}</td>
                            <td> ${order.creatingTime} </td>
                            <td><button type="submit" name="orderId" value="${order.id}" class="btn btn-info"><fmt:message key="user_orders.more_info_button"/></button></td>
                            </form>
                    </tr>
                </c:forEach>
            </table>

     </div>
     <br/>
     <button align="center" class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info"><fmt:message key="user_orders.back_button"/></button>
     </div>
</div>
</div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>