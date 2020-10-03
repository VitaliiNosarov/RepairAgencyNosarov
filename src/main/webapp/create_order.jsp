<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/create_order.css" rel="stylesheet" type="text/css">
<title>New order</title>
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="main_block">
<form class="order_form" action="create_order" method="post">
<h3><center>Choose appropriate options for order</center></h3>
<br/>
<table class="table table-striped">
 <c:forEach items="${list}" var="list">
    
<tr>
<th>
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="${list.id}" name="service" id="defaultCheck1">
      <label class="form-check-label" for="defaultCheck1">
      ${list.name}
      </label>
    </div>
    </th>
</tr>
</c:forEach>

</table>
            
  <div class="form-group">
    <h4><label for="exampleFormControlTextarea1">Write your comment</label></h4>
    <textarea class="form-control" id="exampleFormControlTextarea1" name="comment" rows="3"></textarea>
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