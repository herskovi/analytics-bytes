$("#trainmodelSubmitButton").click(function() 
		{
	var email = $("#email_address").val();
	var modelType = $("#modelType").val();

	event.preventDefault();

	$.ajax(
			{
				type: "GET",
				url:"/trainthemodel",
				//contentType: "application/x-www-form-urlencoded",
				data: { "email_address": email, "modelType" : modelType} ,
				dataType: "json",
				mimeType: 'application/json',
				contentType: 'application/json; charset=utf-8',
				beforeSend: function() 
				{
					//$("#sms-recieved-container").html('<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />');
					$("#confusionMatrix").hide();

				},
				success: function(jsonResponse) 
				{ 

					$("#confusionMatrix").show();
					var dataDescription = jsonResponse.dataDescription;
					var data = JSON.parse(dataDescription);
					console.log("data " + data);
					var data2 = eval( '(' + dataDescription + ')' );
					console.log(data2); 
					$("#confusionMatrix").html(dataDescription);	
				},
				error:function(data,status,er) {
					if (window.console) console.log("error: "+data+" status: "+status+" er:"+er);
				},
				timeout: 500000 // sets timeout to 3 seconds
			});


		});

