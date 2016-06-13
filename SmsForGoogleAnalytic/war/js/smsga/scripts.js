$(document).ready(function(){



	if (!Modernizr.inlinesvg) {
	    $('img[src$=".svg"]').each(function() {
	        $(this).attr('src', $(this).attr('src').replace('.svg', '.png'));
	    });
	}
	

//	$(".nav_link a").pageslide({
//		direction: "left"
//	}); 
	 
});


        

