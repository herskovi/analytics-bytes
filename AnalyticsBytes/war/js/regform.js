$(document).ready(function() {
	$(".reg-input, .sel-input").focus(function() {
		var theinput = $(this).attr("id");
		var thenote  = "#note-"+theinput;
		
		$(thenote).fadeIn("fast");
	});
	
	$(".reg-input").blur(function(){
		var theinput = $(this).attr("id");
		var thenote  = "#note-"+theinput;
		var currval  = $(this).val();
		
		if(currval == "" || currval == " ") {
			$(thenote).fadeOut("fast");
		} else {
			// we do nothin'
		}
	});
});

$(document).ready(function()
{
	var plan = getUrlParameter('plan');

		
	if (plan == 'Bronze')
	{
		$('#plan option:eq(Bronze)').attr("selected", "selected");
	}else if (plan == 'Silver')
	{
		$('#plan option:eq(Silver)').attr("selected", "selected");
	}
    
});




(function($){

	  tile = function() {

	    $("body").append('See? It works.');
	
	  };
	
	})(jQuery);

function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}​