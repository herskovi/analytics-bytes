$(document).ready(
		function() 
		{
			$("#demo").intlTelInput();
			var c= 	$("#demo").intlTelInput("getSelectedCountryData");
			var countryCode = c.iso2;	
			$("#countryCode").val(countryCode);				
			$("countryCode").attr("autocomplete", "off");
			$("#demo").intlTelInput("getSelectedCountryData")

			if (isError == 1)
			{
				$("#formResponseError").css("display", "block");
				//$("#formResponseError").next().show();
				$("#error_message").text(errorMsg);
				$("#name").val(name);
				$("#demo").val(mobile_number);
				$("#countryCode").val(countryCode);
				$("#email_address").val(email_address);
				$("#password").val(password);
				$("#uniqueAccountNumber").val(uniqueAccountNumber);

			}else
			{
				getUniqueAccountNumber();
			}

		});



/**
function getValidateRuleObject()
{
		var validator = $.data(this[0], 'validator');
		if ( validator ) {
			return validator;
		}
		// Add novalidate tag if HTML5.
		this.attr('novalidate', 'novalidate');
	    //HERE it is creating a new one using the constructor   
		validator = new $.validator( options, this[0] );
		$.data(this[0], 'validator', validator);
}

 */
$(document).ready( function() 
		{
//	Displaying first list email field 
	$("ul li:first").show();
//	Regular Expressions
	var ck_username = /^[a-zA-Z0-9()._\-\s]{3,20}$/;
	var ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var ck_password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
	var ck_phoneNumber = /^[+0-9]{9,20}$/;

	//var ck_phoneNumber  =/\+(9[976]\d|8[987530]\d|6[987]\d|5[90]\d|42\d|3[875]\d|2[98654321]\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\W*\d\W*\d\W*\d\W*\d\W*\d\W*\d\W*\d\W*\d\W*(\d{1,2})$/;
//	Email validation



	$('#email_address').blur(function(e)
			{
		var email=$(this).val();
		if (!ck_email.test(email)) 
		{
			//$(this).next().show().html("Enter valid email");
			$(this).next().show();
			$('#email_address').closest('.form-group').addClass('has-error');
			$('#email_address').closest('.form-group').addClass('has-feedback');


		}else{
//			hideElement(this); FIXME - REFACTOR FOR ONE METHOd to HIDE
			$(this).closest('.form-group').removeClass('has-error');
			$(this).closest('.form-group').removeClass('has-feedback');
			$(this).next().hide();

		}
			});
//	Username validation
	$('#name').blur(function(e)
	{
		var username=$(this).val();
		if (!ck_username.test(username)) 
		{
			//$(this).next().show().html("Min 3 charts no space");
			//$(this).next().show();
			$("#name").next().next().show();
			$("#name").closest('.form-group').addClass('has-error');
			event.preventDefault();				
		}
		else
		{
			$(this).next().next().hide();
			$("#name").closest('.form-group').removeClass('has-error');
		}
	});

	$('#mobile_number').blur(function(e)
			{

		if (!(ck_phoneNumber.test(phoneNumber)))
		{
			$("#demo").next().show();
			$("#mobile_number").closest('.form-group').addClass('has-error');
			event.preventDefault();				
		}else
		{
			$(this).next().hide();
			$("#mobile_number").closest('.form-group').removeClass('has-error');
		}
			});


//	Password validation
	$('#password').blur(function(e)
			{
		var password=$(this).val();
		if (!ck_password.test(password)) 
		{
			//$(this).next().show().html("Minimum 6 Characters");
			$(this).next().show();
			$("#password").next().next().show();
			$("#password").closest('.form-group').addClass('has-error');
		}
		else
		{
			$(this).next().hide();
			$("#password").closest('.form-group').removeClass('has-error');
		}
			});



	$('#confirmPassword').keyup(function()
			{
		var confirmPassword=$(this).val();
		var password=$('#password').val();

		if (password != (confirmPassword)) 
		{
			//$(this).next().show().html("Confirm Password should be exact as Password");
			$(this).next().show();

		}
		else
		{
			$(this).next().hide();
			//$("li").next("li.submit").slideDown({duration: 'slow',easing: 'easeOutElastic'});
		}
			});

//	Submit button action

	/**
	 *	Submit button action
	 *  PERFORM VALIDATION ON SUBMIT PAGE 
	 */
	$("#mobileSelectionWizardForm").submit(function( event ) 
			{
		$("#formResponseError").css("display", "none");
		var c= 	$("#demo").intlTelInput("getSelectedCountryData");
		var countryCode = c.iso2;	
		$("#countryCode").val(countryCode);
		var email=$("#email_address").val();
		var username=$("#name").val();
		var password=$("#password").val();
		var confirmPassword=$("#confirmPassword").val();
		var confirmationCheckBox = $('#confirmationCheckBox').val();
		var phoneNumber = $("#demo").val();
		

		if (!(ck_phoneNumber.test(phoneNumber)))
		{
			$("#mobile_number_error").show();
			$("#mobile_number").closest('.form-group').addClass('has-error');
			event.preventDefault();				
		}
			});
		});


function changeCountryCodeForMobileNumber()
{
	$("#country_code_mobile_number").val("");

}


function getUniqueAccountNumber() 
{


	$.ajax({
		type: "GET",
		url:"/uniqueaccountnumber",
		dataType: "json",
		contentType: 'application/json',
		mimeType: 'application/json',

		success: function(uniqueAccountNumberResponse) 
		{ 
			$("#uniqueAccountNumber").val(uniqueAccountNumberResponse);
		},
		error:function(data,status,er) {

		},
		complete: function () 
		{

		}
	});

}


//});