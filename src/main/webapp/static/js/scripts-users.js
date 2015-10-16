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
        	  
        	   $("#user\\.id").val(data.user.id);
        	   $("#user\\.firstName").val(data.user.firstName);
        	   $("#user\\.lastName").val(data.user.lastName);
        	   $("#user\\.login").val(data.user.login);    
        	   $("#user\\.profile option").each(function(){
        		   if($(this).val() == data.user.profile){
        			   $(this).prop("selected", true);
        		   }        			  
        	   });  
        	   if(data.user.photo)
        		   $(".fileupload-new img").attr('src', data.user.photo)
           },
           error: function(){
                 alert('error handing here');
           }
       });	   	   	   	
   });      
})(jQuery);