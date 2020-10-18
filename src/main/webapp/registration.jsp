<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <!DOCTYPE html>
 <head>
     <title>Registration</title>
     <link href="css/registration.css" rel="stylesheet" type="text/css">
     <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
     <script src="js/jquery-3.5.1.min.js"></script>
     <script src="js/registration.js"></script>
 </head>
     
 <body>
 <jsp:include page="/page_component/header.jsp"></jsp:include>

     <div class="content">

     <div class="main_block">
        <h1 align="center"> Registration new user  </h1>
        <h3 align="center"> <em>Enter your information </em> </h3>

     <form id="registration_form" class="inner_block" action = "registration" method = "POST">

    <c:if test="${sessionScope.infoMessage != null}">
         <div class="alert alert-danger" role="alert">
                <center>${infoMessage}</center>
                <c:remove var="infoMessage"/>
         </div>
    </c:if>

       <div class="form-group row">
         <label for="inputEmail3" align="center" class="col-sm-2 col-form-label">Email</label>
          <div class="col-sm-10">
            <input type="email" id="email" class="form-control" name="email" minlength="5" maxlength = "25" placeholder="Email" required>
          </div>
       </div>

       <div class="form-group row">
           <label for="inputPassword3" align="center" class="col-sm-2 col-form-label">Password</label>
           <div class="col-sm-10">
             <input type="password" id="password" class="form-control" name="password" minlength="5" maxlength = "30" placeholder="Password" required>
           </div>
        </div>

       <div class="form-group row">
           <label for="inputName3" align="center" class="col-sm-2 col-form-label">Name</label>
           <div class="col-sm-10">
             <input type="text" id="name" class="form-control" name="name" minlength="2" maxlength = "30" placeholder="Name" required>
           </div>
       </div>

       <div class="form-group row">
              <label for="inputLastName3" align="center" class="col-sm-2 col-form-label">Surname</label>
           <div class="col-sm-10">
              <input type="text" id="surName" class="form-control" name="surname" minlength="2" maxlength = "35" placeholder="Surname" required>
          </div>
       </div>

       <div class="form-group row">
             <label for="inputPhone3" align="center" class="col-sm-2 col-form-label">Phone</label>
           <div class="col-sm-10">
             <input type="text" id="phone" class="form-control" name="phone" minlength="6" maxlength = "12" placeholder="Phone number" required>
           </div>
       </div>

       <fieldset class="form-group">
         <div class="row">
           <legend class="col-form-label col-sm-2 pt-0" align="center">Language</legend>
           <div class="col-sm-10">
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="EN" align="center" checked>
                <label class="form-check-label" for="gridRadios1">
                 English
                </label>
             </div>
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="RU">
                <label class="form-check-label" for="gridRadios1">
                 Russian
               </label>
           </div>
         </div>
       </div>
       <div class="form-group" align="center">
         <div class="col-sm-10">
           <button type="submit" class="btn btn-info">Registration</button>
           <button align="center" class="btn btn-info" type="button" onclick="history.back();" class="btn btn-info">Back</button>
         </div>
       </div>
     </fieldset>
     </form>
     </div>
     </div>

     <jsp:include page="/page_component/footer.jsp"></jsp:include>
 </body>
 </html>