<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java"%>

 <html>
 <head>
     <title>Registration</title>
     <link href="css/registration.css" rel="stylesheet" type="text/css">
     <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
     <script src="js/jquery-3.5.1.min.js"></script>
     <script src="js/registration.js"></script>
 </head>
 <body>
 <jsp:include page="/page_component/header.jsp"></jsp:include>
<div class="main_block">
 <h1 align="center"> Registration new user  </h1>
 <h3 align="center"> <em>Enter your information </em> </h3>

     <form id="login_form" class="inner_block" action = "registration" method = "POST">
       <div class="form-group row">
         <label for="inputEmail3" align="center" class="col-sm-2 col-form-label">Email</label>
          <div class="col-sm-10">
            <input type="email" id="email" class="form-control" name="email" placeholder="Email" required>
          </div>
       </div>

       <div class="form-group row">
           <label for="inputPassword3" align="center" class="col-sm-2 col-form-label">Password</label>
           <div class="col-sm-10">
             <input type="password" id="password" class="form-control" name="password"placeholder="Password" required>
           </div>
        </div>

       <div class="form-group row">
           <label for="inputName3" align="center" class="col-sm-2 col-form-label">Name</label>
           <div class="col-sm-10">
             <input type="text" id="name" class="form-control" name="name" placeholder="Name" required>
           </div>
       </div>

       <div class="form-group row">
              <label for="inputLastName3" align="center" class="col-sm-2 col-form-label">Surname</label>
           <div class="col-sm-10">
              <input type="text" id="surName" class="form-control"name="surname" placeholder="Surname" required>
          </div>
       </div>

       <div class="form-group row">
             <label for="inputPhone3" align="center" class="col-sm-2 col-form-label">Phone</label>
           <div class="col-sm-10">
             <input type="text" id="phone" class="form-control"name="phone" placeholder="Phone number" required>
           </div>
       </div>

       <fieldset class="form-group">
         <div class="row">
           <legend class="col-form-label col-sm-2 pt-0" align="center">Language</legend>
           <div class="col-sm-10">
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="EN" align="center" checked>
                 English
               </label>
             </div>
             <div class="form-check">
               <input class="form-check-input" id="locale" type="radio" name="locale" value="RU">
                 Russian
               </label>
           </div>
         </div>
       </fieldset>
       <div class="form-group" align="center">
         <div class="col-sm-10">
           <button type="submit" class="btn btn-primary">Registration</button>
         </div>
       </div>
     </form>
     </div>
     <jsp:include page="/page_component/footer.jsp"></jsp:include>
 </body>
 </html>