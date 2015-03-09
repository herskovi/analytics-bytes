$(document).ready(
		function() {

			$("#contactUsForm").submit(
					function() {
						var represent = $('#represent').val();
						var email = $('#email').val();
						var fullName = $("#fullName").val();
						var telephone = $('#telephone').val();
						var company = $('#company').val();
						var comments = $('#comments').val();
						var dataString = JSON.stringify({
							"represent" : represent,
							"email" : email,
							"fullName" : fullName,
							"telephone" : telephone,
							"company" : company,
							"comments" : comments
						})

						$
								.ajax({
									type : 'POST',
									url : 'http://localhost:8080/newUser',
									dataType : 'json',
									data : dataString,
									success : function(data) {
										alert("WOW" + data);
										if (!data.answer) {
											alert('Error 1');
										}
									},
									error : function(jqXHR, setting,
											errorThrown) {

										// alert('Error 2' + data);
										alert('Error in: ' + settings.url
												+ ' - Error: ' + errorThrown
												+ " - Response: "
												+ jqXHR.responseText);

									}

								});
					});
		});

$('#ajaxError').ajaxError(
		function(e, xhr, settings, exception) {
			$(this).text(
					'Error in: ' + settings.url + ' - Error: ' + exception
							+ " - Response: " + xhr.responseText);
		});

$("#plan").click(function() {
	alert("Handler for .click() called.");
});

	function chosenPlan(chosenPlanVal) 
	{
        $('chosenPlanHidden').val('chosenPlanVal');
		$('planSelection').submit();
		
	}

