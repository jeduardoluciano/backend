(function ($) {
    "use strict";
    $(document).ready(function () { });

   
	   	  	  	   	
	$(".update").on("click", function(){
		var id =$(this).attr('data-update'); 
		$.ajax({
           type: "PUT",
           url: urlApp + "users/" + id,           
           dataType: "json",           
           success: function(data) {
        	   console.log(data);
        	   $("#user\\.id").val(data.user.id);
        	   $("#user\\.firstName").val(data.user.firstName);
        	   $("#user\\.lastName").val(data.user.lastName);
        	   $("#user\\.description").val(data.user.description);
        	   $("#user\\.website").val(data.user.website);
        	   $("#user\\.fanpage").val(data.user.fanpage);
        	   $("#user\\.address\\.phone").val(data.user.address.phone);
        	   $("#user\\.address\\.name").val(data.user.address.name);
        	   $("#user\\.address\\.number").val(data.user.address.number);
        	   $("#user\\.address\\.district").val(data.user.address.district);
        	   $("#user\\.address\\.city").val(data.user.address.city);
        	   $("#user\\.address\\.lat").val(data.user.address.lat);
        	   $("#user\\.address\\.lng").val(data.user.address.lng);        	           	           	 
           },
           error: function(){
                 alert('error handing here');
           }
       });	   	   	   	
   });      
})(jQuery);