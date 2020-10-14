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
     <script src="js/jquery-3.5.1.min.js"></script>
     <script src="js/registration.js"></script>
</head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<div class="main_block">
  <h1 align="center"> Account  </h1>
  <h3 align="center"> <em>You can update your information </em> </h3>


     <form id="registration_form" class="inner_block" action = "updateAccount" method = "POST">
        <c:if test="${sessionScope.infoMessage != null}">
            <div class="alert alert-success" role="alert">
                <center>${infoMessage}</center>
                <c:remove var="infoMessage"/>
            </div>
        </c:if>

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
             <input type="password" id="password" class="form-control" name="password" placeholder="**********">
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

        <div class="form-group row">
           <label for="balance" align="center" class="col-sm-2 col-form-label">Balance</label>
           <div class="col-sm-10">

           <c:choose>
                <c:when test="${account.balance == null}">
                        <div class="alert alert-info" role="alert">
                           To top up the balance, contact the manager
                       </div>
                </c:when>
               <c:otherwise>
                  <input type="text" readonly class="form-control" id="balance" class="balance" value="${account.balance}">
               </c:otherwise>
               </c:choose>
           </div>
        </div>

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
   </div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>