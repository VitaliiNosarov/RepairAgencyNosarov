<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<html>
<head>
<title>Order</title>
<link href="css/user_account.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<c:set var="user" value='${sessionScope.user}' />

<div class="main_block">
<div class="inner_block">
<h3> <center>Account info</center></h3>
</br>

<div class="form-group row">
    <label for="Email" class="col-sm-2 col-form-label">Email :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Email" name="email" value="${user.email}">
    </div>
</div>

<div class="form-group row">
    <label for="Name" class="col-sm-2 col-form-label">Name :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Name" name="userName" value="${user.name}">
    </div>
 </div>

<div class="form-group row">
    <label for="Surname" class="col-sm-2 col-form-label">Surname :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Surname" name="surname" value="${user.surName}">
    </div>
 </div>

<div class="form-group row">
    <label for="Phone" class="col-sm-2 col-form-label">Phone :</label>
    <div class="col-sm-10">
      <input type="tel" readonly class="form-control" id="Phone" value="${user.phone}">
    </div>
</div>


<div class="form-group row">
    <label for="Balance" class="col-sm-2 col-form-label">Balance :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Balance" value="${user.balance}">
    </div>
 </div>


<div class="form-group row">
    <label for="Language" class="col-sm-2 col-form-label">Language :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Language" value="${user.locale}">
    </div>
 </div>

<div class="form-group row">
    <label for="Password" class="col-sm-2 col-form-label">Password :</label>
    <div class="col-sm-10">
      <input type="password" readonly class="form-control" id="Password" value="${user.password}">
    </div>
 </div>

<button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>

</div>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>