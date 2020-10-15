$(document).ready(function() {

  $(document).on('submit', '#login_form', function(e){
  var path = window.location.pathname;
  var page = path.split("/").pop();

    e.preventDefault();
    var email = $('#email').val();
    var password = $('#password').val();
    var isValid = true;

    $(".error").remove();

    if (email.length < 5 || email.length > 25) {
          $('#email').after('<span class="error">Enter a valid email</span>');
          isValid = false;
    } else {
      var regEx = /[a-zA-Z]+@{1}[a-zA-Z]+\.{1}[a-zA-Z]{2,4}/;
      var validEmail = regEx.test(email);
      if (!validEmail) {
        $('#email').after('<span class="error">Enter a valid email</span>');
        isValid = false;
      }
    }
if (password.length < 5 || password.length > 30) {
          $('#password').after('<span class="error">Password must be 5-30 symbols long</span>');
          isValid = false;
        } else {
          var regExPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{5,}$/;
          var validPass = regExPass.test(password);
          if (!validPass) {
            $('#password').after('<span class="error">Example : Password1</span>');
            isValid = false;
          }
        }

    if(isValid){
         $('#login_form')[0].submit();
    }

  });

});

