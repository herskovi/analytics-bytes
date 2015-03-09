
$(document).ready(function()
		{
//	$(function(){  
//	$("html").bind("ajaxStart", function(){  
//	$(this).addClass('busy');  
//	}).bind("ajaxStop", function(){  
//	$(this).removeClass('busy');  
//	});  
//	});

	$("#getSmsNow").click(function (event) 
			{ 

		event.preventDefault();
		//$("#sms-recieved-container").css("display", "block");
		//Adding Sandclock



		$.ajax(
				{
					type: "GET",
					url:"/sendsms",
					dataType: "json",
					//contentType: "application/x-www-form-urlencoded",
					data: useridStr, 
					beforeSend: function() 
					{
						//$("#sms-recieved-container").html('<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />');
						$("#spinner").show();

					},
					success: function() 
					{ 
						$("#sms-recieved-container").css("display", "block");
						$("#spinner").hide();

					},
					error: function (jqXHR) {
						$("#sms-recieved-container").css("display", "block");
						$("#spinner").hide();
					},
					complete: function () 
					{
						$("#spinner").hide();
					}
				});


			});

	$("#smsRecievedForm").click(function( event ) 
			{
		event.preventDefault();
        if (event.target.value == "Yes")
        {
        	submitButton = "Yes";	
        }else{
        	submitButton = "No";
        }
		
		submitButton = "smsRecieved="  + submitButton;
		

		$.ajax(
				{
					type: "GET",
					url:"/smsconfirmation",
					dataType: "json",
					//contentType: "application/x-www-form-urlencoded",
					data: useridStr + "&"+ submitButton, 
					beforeSend: function() 
					{
						//$("#sms-recieved-container").html('<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />');
						$("#spinnerForConfirmationButton").show();

					},
					success: function() 
					{ 
						//$("#sms-recieved-container").css("display", "block");
						$("#spinnerForConfirmationButton").hide();

					},
					error: function (jqXHR) {
						//$("#sms-recieved-container").css("display", "block");
						$("#spinnerForConfirmationButton").hide();

					},
					complete: function () 
					{
						$("#spinnerForConfirmationButton").hide();
					}
				});

			});

		});	



