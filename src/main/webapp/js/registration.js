$(document).ready(function() {

  $(document).on('submit', '#login_form', function(e){
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
    if (phone.length < 1) {
          $('#phone').after('<span class="error">This field is required</span>');
          isValid = false;
        } else {
          var regEx = /[+]{0,1}\d{6,12}/;
          var validPhone = regEx.test(phone);
          if (!validPhone) {
            $('#phone').after('<span class="error">Enter a valid phone</span>');
            isValid = false;
          }
        }
     if (password.length < 4) {
           $('#password').after('<span class="error">Password must be at least 4 characters long</span>');
           isValid = false;
         }
     if (name.length < 2) {
          $('#name').after('<span class="error">Name must be at least 2 characters long</span>');
          isValid = false;
        }
        if (surName.length < 2) {
          $('#surName').after('<span class="error">Surname must be at least 2 characters long</span>');
          isValid = false;
        }
    if(isValid){
         $('#login_form')[0].submit();
    }

  });

});

