$(document).ready(function() {

  $(document).on('submit', '#login_form', function(e){
  var path = window.location.pathname;
  var page = path.split("/").pop();

    e.preventDefault();
    var email = $('#email').val();
    var password = $('#password').val();
    var isValid = true;

    $(".error").remove();

    if (email.length < 1) {
      $('#email').after('<span class="error">This field is required</span>');
      isValid = false;
    } else {
      var regEx = /[a-zA-Z]+@{1}[a-zA-Z]+\.{1}[a-zA-Z]{2,4}/;
      var validEmail = regEx.test(email);
      if (!validEmail) {
        $('#email').after('<span class="error">Enter a valid email</span>');
        isValid = false;
      }
    }
    if (password.length < 1) {
          $('#password').after('<span class="error">This field is required</span>');
          isValid = false;
        } else {
          var regExPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{5,}$/;
          var validPass = regExPass.test(password);
          if (!validPass) {
            $('#password').after('<span class="error">Password must be at least 5 symbols long and contains digits and capital letters</span>');
            isValid = false;
          }
        }

    if(isValid){
         $('#login_form')[0].submit();
    }

  });

});

