(function ($) {
    "use strict";
    $(document).ready(function () { });

   
    $("#place\\.address\\.district").on("blur", function(){
    	$("#ShowLocationGoogleMaps").click();
    })
	   
	$("#place\\.address\\.city").on("change", function(){
		$("#ShowLocationGoogleMaps").click();
	})
	   	  	  	   	
	$(".update").on("click", function(){
		var id =$(this).attr('data-update'); 
		$.ajax({
           type: "PUT",
           url: urlApp + "places/" + id,           
           dataType: "json",           
           success: function(data) {
        	   console.log(data);
        	   $("#place\\.id").val(data.place.id);
        	   $("#place\\.name").val(data.place.name);
        	   $("#place\\.description").val(data.place.description);
        	   $("#place\\.website").val(data.place.website);
        	   $("#place\\.fanpage").val(data.place.fanpage);
        	   $("#place\\.address\\.phone").val(data.place.address.phone);
        	   $("#place\\.address\\.name").val(data.place.address.name);
        	   $("#place\\.address\\.number").val(data.place.address.number);
        	   $("#place\\.address\\.district").val(data.place.address.district);
        	   $("#place\\.address\\.city").val(data.place.address.city);
        	   $("#place\\.address\\.lat").val(data.place.address.lat);
        	   $("#place\\.address\\.lng").val(data.place.address.lng);        	           	           	 
           },
           error: function(){
                 alert('error handing here');
           }
       });	   	   	   	
   });      
})(jQuery);