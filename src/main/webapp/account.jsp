<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>
<c:set var="user" value='${sessionScope.user}' />

<c:if test="${empty requestScope.account}">
    <c:set var="account" value='${sessionScope.user}' />
</c:if>

<html>

    <head>
         <title><fmt:message key="account.page_title"/></title>
         <link rel="shortcut icon" href="image/icon.png" />
         <link href="css/user_account.css" rel="stylesheet" type="text/css">
         <script src="js/jquery-3.5.1.min.js"></script>
         <script src="js/account.js"></script>
    </head>

<body>
<jsp:include page="/page_component/header.jsp"></jsp:include>

<div class="content">

<div class="main_block">
  <h1 align="center"> <fmt:message key="account.page_title"/>  </h1>
  <h3 align="center"> <em><fmt:message key="account.page_title_text"/> </em> </h3>


     <form id="registration_form" class="inner_block" action = "updateAccount" method = "POST">
        <c:if test="${sessionScope.infoMessage != null}">
            <div class="alert alert-success" role="alert">
                <center><ctg:enumTranslate locale="${sessionScope.language}" value="${infoMessage}"/></center>
                <c:remove var="infoMessage"/>
            </div>
        </c:if>

     <input type="hidden" name="accountId" value="${account.id}">

       <div class="form-group row">
         <label for="email" align="center" class="col-sm-2 col-form-label"><fmt:message key="account.email"/></label>
          <div class="col-sm-10">
            <input type="email" id="email" class="form-control" name="email" minlength="5" maxlength = "25" value="${account.email}" placeholder="${account.email}">
          </div>
       </div>

       <div class="form-group row">
           <label for="password" align="center" class="col-sm-2 col-form-label"><fmt:message key="account.password"/></label>
           <div class="col-sm-10">
             <input type="password" id="password" class="form-control" minlength="5" maxlength = "30" name="password" placeholder="**********">
           </div>
        </div>

       <div class="form-group row">
           <label for="name" align="center" class="col-sm-2 col-form-label"><fmt:message key="account.name"/></label>
           <div class="col-sm-10">
             <input type="text" id="name" class="form-control" name="name" minlength="2" maxlength = "30" value="${account.name}" placeholder="${account.name}">
           </div>
       </div>

       <div class="form-group row">
              <label for="surName" align="center" class="col-sm-2 col-form-label"><fmt:message key="account.surname"/></label>
           <div class="col-sm-10">
              <input type="text" id="surName" class="form-control" name="surname" minlength="2" maxlength = "35" value="${account.surName}" placeholder="${account.surName}">
          </div>
       </div>

       <div class="form-group row">
             <label for="phone" align="center" class="col-sm-2 col-form-label"><fmt:message key="account.phone"/></label>
           <div class="col-sm-10">
             <input type="text" id="phone" class="form-control" minlength="6" maxlength = "12" name="phone" value="${account.phone}"  placeholder="${account.phone}">
           </div>
       </div>

        <div class="form-group row">
           <label for="balance" align="center" class="col-sm-2 col-form-label"><fmt:message key="account.balance"/></label>
           <div class="col-sm-10">

           <c:choose>
                <c:when test="${account.balance <1}">
                        <div class="alert alert-info" role="alert">
                            <fmt:message key="account.balance_message"/>
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
           <legend class="col-form-label col-sm-2 pt-0" align="center"><fmt:message key="account.language"/></legend>
           <div class="col-sm-10">
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="EN" ${account.locale == 'EN' ? 'checked' : ''}>
                 <fmt:message key="account.language_en"/>
               </label>
             </div>
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="RU" ${account.locale == 'RU' ? 'checked' : ''}>
                 <fmt:message key="account.language_ru"/>
               </label>
           </div>
         </div>
       </fieldset>
       <div class="form-group" align="center">
         <div class="col-sm-10">
           <button type="submit" class="btn btn-info"><fmt:message key="account.update_button"/></button>
           <button class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info"><fmt:message key="account.back_button"/></button>
         </div>
       </div>
     </form>
     </div>
   </div>

<jsp:include page="/page_component/footer.jsp"></jsp:include>
</body>
</html>