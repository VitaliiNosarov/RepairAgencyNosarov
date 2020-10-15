$(document).ready(function() {

  $(document).on('submit', '#order_form', function(e){
  var path = window.location.pathname;
  var page = path.split("/").pop();

    e.preventDefault();
    var price = $('#price').val();
    var isValid = true;

    $(".error").remove();

    if (price.length > 0) {
      var regEx = /^[0-9]+$/;
          var validPrice = regEx.test(price);
      if (!validPrice) {
            $('#price').after('<span class="error">Wrong number format</span>');
            isValid = false;
      }
    }

    if(isValid){
         $('#order_form')[0].submit();
    }

  });

});

