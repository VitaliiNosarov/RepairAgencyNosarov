$(document).ready(function() {

  $(document).on('submit', '#order_form', function(e){
  var path = window.location.pathname;
  var page = path.split("/").pop();

    e.preventDefault();
    var device = $('#userDevice').val();
    var comment = $('#userComment').val();
    var isValid = true;

    $(".error").remove();


 if (device.length < 5) {
      $('#userDevice').after('<span class="error">This field is required. Min 5 symbols</span>');
      isValid = false;
    }
    if (device.length >60) {
      $('#userDevice').after('<span class="error">Device can not be longer then 60 symbols</span>');
      isValid = false;
    }
    if (comment.length < 10) {
          $('#userComment').after('<span class="error">This field is required. Min 10 symbols</span>');
          isValid = false;
        }
    if (comment.length >1000) {
          $('#userComment').after('<span class="error">Comment can not be longer then 1000 symbols</span>');
          isValid = false;
        }

    if(isValid){
         $('#order_form')[0].submit();
    }

  });

});

