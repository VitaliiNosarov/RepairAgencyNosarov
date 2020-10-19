<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>
<html>
 <head>
     <title><fmt:message key="registration.page_title"/></title>
     <link rel="shortcut icon" href="image/icon.png" />
     <link href="css/registration.css" rel="stylesheet" type="text/css">
     <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
     <script src="js/jquery-3.5.1.min.js"></script>
     <script src="js/registration.js"></script>
 </head>
     
 <body>
 <jsp:include page="/page_component/header.jsp"></jsp:include>

     <div class="content">

     <div class="main_block">
        <h1 align="center"> <fmt:message key="registration.title"/> </h1>
        <h3 align="center"> <em><fmt:message key="registration.title_text"/></em> </h3>

     <form id="registration_form" class="inner_block" action = "registration" method = "POST">

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-danger" role="alert">
                <center><ctg:enumTranslate locale="${sessionScope.language}" value="${infoMessage}"/></center>
                <c:remove var="infoMessage"/>
         </div>
    </c:if>

       <div class="form-group row">
         <label for="inputEmail3" align="center" class="col-sm-2 col-form-label"><fmt:message key="registration.email"/></label>
          <div class="col-sm-10">
            <input type="email" id="email" class="form-control" name="email" minlength="5" maxlength = "25" placeholder="<fmt:message key="registration.email"/>" required>
          </div>
       </div>

       <div class="form-group row">
           <label for="inputPassword3" align="center" class="col-sm-2 col-form-label"><fmt:message key="registration.password"/></label>
           <div class="col-sm-10">
             <input type="password" id="password" class="form-control" name="password" minlength="5" maxlength = "30" placeholder="<fmt:message key="registration.password"/>" required>
           </div>
        </div>

       <div class="form-group row">
           <label for="inputName3" align="center" class="col-sm-2 col-form-label"><fmt:message key="registration.name"/></label>
           <div class="col-sm-10">
             <input type="text" id="name" class="form-control" name="name" minlength="2" maxlength = "30" placeholder="<fmt:message key="registration.name"/>" required>
           </div>
       </div>

       <div class="form-group row">
              <label for="inputLastName3" align="center" class="col-sm-2 col-form-label"><fmt:message key="registration.surname"/></label>
           <div class="col-sm-10">
              <input type="text" id="surName" class="form-control" name="surname" minlength="2" maxlength = "35" placeholder="<fmt:message key="registration.surname"/>" required>
          </div>
       </div>

       <div class="form-group row">
             <label for="inputPhone3" align="center" class="col-sm-2 col-form-label"><fmt:message key="registration.phone"/></label>
           <div class="col-sm-10">
             <input type="text" id="phone" class="form-control" name="phone" minlength="6" maxlength = "12" placeholder="<fmt:message key="registration.phone_placeholder"/>" required>
           </div>
       </div>

       <fieldset class="form-group">
         <div class="row">
           <legend class="col-form-label col-sm-2 pt-0" align="center"><fmt:message key="registration.language"/></legend>
           <div class="col-sm-10">
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="EN" align="center" checked>
                <label class="form-check-label" for="gridRadios1">
                 <fmt:message key="registration.language_en"/>
                </label>
             </div>
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="RU">
                <label class="form-check-label" for="gridRadios1">
                 <fmt:message key="registration.language_ru"/>
               </label>
           </div>
         </div>
       </div>
       <div class="form-group" align="center">
         <div class="col-sm-10">
           <button type="submit" class="btn btn-info"><fmt:message key="registration.button"/></button>
           <button align="center" class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info"><fmt:message key="registration.back_button"/></button>
         </div>
       </div>
     </fieldset>
     </form>
     </div>
     </div>

     <jsp:include page="/page_component/footer.jsp"></jsp:include>
 </body>
 </html>