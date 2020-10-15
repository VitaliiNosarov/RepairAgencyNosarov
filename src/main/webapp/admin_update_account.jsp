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
<title>Order</title>
    <link href="css/user_account.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
     <script src="js/validate_account_admin.js"></script>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<c:set var="user" value='${sessionScope.user}' />

<div class="main_block">
 <div class="inner_block">
    <form id="account_form" class="inner_block" action = "updateUser" method = "POST">

    <input type="hidden" name="accountId" value="${account.id}">

<h3> <center>Account info</center></h3>
<br/>

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-success" role="alert">
                <center>${infoMessage}</center>
                <c:remove var="infoMessage"/>
         </div>
    </c:if>

<c:if test="${sessionScope.infoMessageSuccess != null}">
     <div class="alert alert-success" role="alert">
            <center>${infoMessageSuccess}</center>
            <c:remove var="infoMessageSuccess"/>
     </div>
</c:if>

<div class="form-group row">
    <label for="Email" class="col-sm-2 col-form-label">Email :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Email" value="${user.email}">
    </div>
</div>

<div class="form-group row">
    <label for="Name" class="col-sm-2 col-form-label">Name :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Name" value="${user.name}">
    </div>
 </div>

<div class="form-group row">
    <label for="Surname" class="col-sm-2 col-form-label">Surname :</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control" id="Surname" value="${user.surName}">
    </div>
 </div>

<div class="form-group row">
    <label for="Phone" class="col-sm-2 col-form-label">Phone :</label>
    <div class="col-sm-10">
      <input type="tel" readonly class="form-control" id="Phone" value="${user.phone}">
    </div>
</div>

<div class="form-group row">
        <label for="Role" class="col-sm-2 col-form-label">Role</label>
         <div class="col-sm-10">
           <select class="custom-select my-1 mr-sm-2" id="Role" name="role">
                  <option value="ADMIN" ${account.role == 'ADMIN' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="ADMIN"/></option>
                  <option value="CUSTOMER" ${account.role == 'CUSTOMER' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="CUSTOMER"/></option>
                  <option value="MASTER" ${account.role == 'MASTER' ? 'selected' : ''}><ctg:enumTranslate locale="${sessionScope.language}" value="MASTER"/></option>
           </select>
        </div>
      </div>

     <div class="form-group row">
           <label for="balance" align="center" class="col-sm-2 col-form-label">Balance</label>
           <div class="col-sm-10">
             <input type="number" min="0" max="10000" step="any" id="balance" class="form-control" name="balance" default="${account.balance}" placeholder="${account.balance}">
           </div>
     </div>

    <button type="submit" class="btn btn-info">Update</button>
    <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>

   </form>
    </div>
</div>
</div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>
