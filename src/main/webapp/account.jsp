<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>


<c:set var="user" value='${sessionScope.user}' />

<c:if test="${empty requestScope.account}">
    <c:set var="account" value='${sessionScope.user}' />
</c:if>

<html>
<head>
<title>Order</title>
<link href="css/user_account.css" rel="stylesheet" type="text/css">
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="main_block">
 <h1 align="center"> Account  </h1>
 <h3 align="center"> <em>You can update your information </em> </h3>

     <form id="login_form" class="inner_block" action = "updateAccount" method = "POST">

     <input type="hidden" name="accountId" value="${account.id}">

       <div class="form-group row">
         <label for="email" align="center" class="col-sm-2 col-form-label">Email</label>
          <div class="col-sm-10">
            <input type="email" id="email" class="form-control" name="email" value="${account.email}" placeholder="${account.email}">
          </div>
       </div>

       <div class="form-group row">
           <label for="password" align="center" class="col-sm-2 col-form-label">Password</label>
           <div class="col-sm-10">
             <input type="password" id="password" class="form-control" name="password" value="${account.password}" placeholder="${account.password}">
           </div>
        </div>

       <div class="form-group row">
           <label for="name" align="center" class="col-sm-2 col-form-label">Name</label>
           <div class="col-sm-10">
             <input type="text" id="name" class="form-control" name="name" value="${account.name}" placeholder="${account.name}">
           </div>
       </div>

       <div class="form-group row">
              <label for="surName" align="center" class="col-sm-2 col-form-label">Surname</label>
           <div class="col-sm-10">
              <input type="text" id="surName" class="form-control" name="surname" value="${account.surName}" placeholder="${account.surName}">
          </div>
       </div>

       <div class="form-group row">
             <label for="phone" align="center" class="col-sm-2 col-form-label">Phone</label>
           <div class="col-sm-10">
             <input type="text" id="phone" class="form-control" name="phone" value="${account.phone}"  placeholder="${account.phone}">
           </div>
       </div>

 <c:choose>
    <c:when test="${user.role == 'ADMIN'}">
        <div class="form-group row">
                <label for="Role" class="col-sm-2 col-form-label">Role</label>
                 <div class="col-sm-10">
                   <select class="custom-select my-1 mr-sm-2" id="Role" name="role">
                          <option value="ADMIN" ${account.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
                          <option value="CUSTOMER" ${account.role == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
                          <option value="MASTER" ${account.role == 'MASTER' ? 'selected' : ''}>Master</option>
                   </select>
                </div>
              </div>

             <div class="form-group row">
                   <label for="Balance" align="center" class="col-sm-2 col-form-label">Balance</label>
                   <div class="col-sm-10">
                     <input type="text" id="Balance" class="form-control" name="balance" default="${account.balance}" placeholder="${account.balance}">
                   </div>
             </div>
    </c:when>
    <c:otherwise>
        <input type="hidden" name="role" value="${account.role}">
        <div class="form-group row">
           <label for="balance" align="center" class="col-sm-2 col-form-label">Balance</label>
           <div class="col-sm-10">
             <input type="text" readonly class="form-control" id="balance" class="balance" name="balance" value="${account.balance}">
           </div>
        </div>

    </c:otherwise>
    </c:choose>
       <fieldset class="form-group">
         <div class="row">
           <legend class="col-form-label col-sm-2 pt-0" align="center">Language</legend>
           <div class="col-sm-10">
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="EN" ${account.locale == 'EN' ? 'checked' : ''}>
                 English
               </label>
             </div>
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="RU" ${account.locale == 'RU' ? 'checked' : ''}>
                 Russian
               </label>
           </div>
         </div>
       </fieldset>
       <div class="form-group" align="center">
         <div class="col-sm-10">
           <button type="submit" class="btn btn-info">Update</button>
           <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
         </div>
       </div>
     </form>
     </div>
<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>