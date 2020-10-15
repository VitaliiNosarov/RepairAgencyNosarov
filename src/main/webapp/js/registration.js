$(document).ready(function() {

  $(document).on('submit', '#registration_form', function(e){
  var path = window.location.pathname;
  var page = path.split("/").pop();

    e.preventDefault();
    var email = $('#email').val();
    var password = $('#password').val();
    var name = $('#name').val();
    var surName = $('#surName').val();
    var phone = $('#phone').val();
    var isValid = true;

    $(".error").remove();


    if (email.length < 5 || email.length > 25) {
      $('#email').after('<span class="error">Enter a valid email</span>');
      isValid = false;

    } else {
      var regEx = /[a-zA-Z]+@{1}[a-zA-Z]+\.{1}[a-zA-Z]{2,25}/;
      var validEmail = regEx.test(email);
      if (!validEmail) {
        $('#email').after('<span class="error">Enter a valid email</span>');
        isValid = false;
      }
    }

    if (phone.length < 6 || phone.length > 12) {
          $('#phone').after('<span class="error">Enter a valid phone</span>');
          isValid = false;
        } else {
          var regEx = /[+]{0,1}\d{6,12}/;
          var validPhone = regEx.test(phone);
          if (!validPhone) {
            $('#phone').after('<span class="error">Enter a valid phone</span>');
            isValid = false;
          }
        }

    if (password.length < 5 || password.length > 30) {
          $('#password').after('<span class="error">Password must be 5-30 symbols long</span>');
          isValid = false;
        } else {
          var regExPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{5,30}$/;
          var validPass = regExPass.test(password);
          if (!validPass) {
            $('#password').after('<span class="error">Password must be at least 5 symbols long, contains digits and capital letters</span>');
            isValid = false;
          }
        }

     if (name.length < 2 || name.length > 30) {
          $('#name').after('<span class="error">Name must be 2-30 characters long</span>');
          isValid = false;
        }
        if (surName.length < 2 || surName.length > 35) {
          $('#surName').after('<span class="error">Surname must be 2-35 symbols long</span>');
          isValid = false;
        }
    if(isValid){
         $('#registration_form')[0].submit();
    }

  });

});

