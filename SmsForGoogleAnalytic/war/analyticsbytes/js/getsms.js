
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

	$("#btnConfirmationYes").click(function( event )
	{
		submitForm("Yes");
	});
	
	$("#btnConfirmationNo").click(function( event )
	{
		submitForm("No");
	});
	
	
	function submitForm(submitButtonTxt) 
	{
		var submitButton = "smsRecieved="  + submitButtonTxt;
		

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
						hideYesNoButtons(submitButtonTxt);
					},
					error: function (jqXHR) {
						hideYesNoButtons(submitButtonTxt);
					},
					complete: function () 
					{
						hideYesNoButtons(submitButtonTxt);
					}
					
					
				});
		}
		function hideYesNoButtons(eventValue)
		{
			$("#spinnerForConfirmationButton").hide();
			$("#didYouRecieveTheSMSQuestion").hide();
			$("#didYouRecieveTheSMSQuestion").css("display", "none");
			$("#yesNoButtonsDivForSMSConfirmation").hide();
			$("#getSmsNow").css("display", "none");
			$("#getSmsNowParagraph").css("display", "none");
			
			
			if (eventValue == "Yes")
			{		
				//$("#smileyForBtnConfirmationYes").show();
				$("#smsvalfail").hide();

				$("#smsvalsuccess").show();
				
			}
			if (eventValue == "No")
			{
				//$("#sadEmoticonForBtnConfirmationNo").show();
				$("#smsvalsuccess").hide();
				$("#smsvalfail").show();
				

			}
			
		}

			

		});	



