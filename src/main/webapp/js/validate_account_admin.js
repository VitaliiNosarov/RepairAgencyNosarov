$(document).ready(function() {

  $(document).on('submit', '#account_form', function(e){
  var path = window.location.pathname;
  var page = path.split("/").pop();

    e.preventDefault();
    var balance = $('#balance').val();
    var isValid = true;

    $(".error").remove();

if(balance.length > 5){
        isValid = false;
}
    if (balance.length > 0) {
      var regEx = /^[0-9]+$/;
          var validBalance = regEx.test(balance);
      if (!validBalance) {
            $('#balance').after('<span class="error">Wrong number format</span>');
            isValid = false;
      }
    }

    if(isValid){
         $('#account_form')[0].submit();
    }

  });

});

