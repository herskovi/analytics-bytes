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
					$("#inProgress").show();
					$("#confusionMatrix").hide();

				},
				success: function(jsonResponse) 
				{ 
					$("#inProgress").hide();
					$("#confusionMatrix").show();
					var dataDescription = jsonResponse.dataDescription;
					var modelDescriptionjson = jsonResponse.modelDescription;
					var modelDescription = JSON.parse(modelDescriptionjson);
					console.log("modelDescription " + modelDescription);
					console.log("modelDescription.confusionMatrix " + modelDescription.confusionMatrix);
					console.log("modelDescription.confusionMatrix.FAILURE " + modelDescription.confusionMatrix.FAILURE);
					console.log("modelDescription.confusionMatrix.FAILURE.FAILURE " + modelDescription.confusionMatrix.FAILURE.FAILURE);
					console.log("modelDescription.confusionMatrix.FAILURE.SUCCESS " + modelDescription.confusionMatrix.FAILURE.SUCCESS);
					 var r = $("#confusionMatrixTable #failureVsFailure").text();
					console.log("#confusionMatrixTable #failureVsFailure " + r);
				    $("#failureVsFailure").text(modelDescription.confusionMatrix.FAILURE.FAILURE);
				    $("#failureVsSuccess").text(modelDescription.confusionMatrix.FAILURE.SUCCESS);
				    $("#successVsFailure").text(modelDescription.confusionMatrix.SUCCESS.FAILURE);
				    $("#successVsSuccess").text(modelDescription.confusionMatrix.SUCCESS.SUCCESS);
				    $("#successVsSuccess").css("font-size", "24px");
				    $("#numberOfInstancesValue").text(jsonResponse.numberOfInstances);
				    
				    var a = Number(parseFloat($("#failureVsFailure").text()).toFixed(2));
				    var b = Number(parseFloat($("#failureVsSuccess").text()).toFixed(2));
				    var c = Number(parseFloat($("#successVsFailure").text()).toFixed(2));
				    var d = Number(parseFloat($("#successVsSuccess").text()).toFixed(2));
				    
				    var sum = a+b+c+d;
				    
				    $("#accuracyRate").text(parseFloat(100*(a+d)/(a+b+c+d)).toFixed(2)+"%")
				    $("#trueSuccessRate").text(parseFloat(100*d/(c+d)).toFixed(2)+"%");
				    $("#falseSuccessRate").text(parseFloat(100*b/(a+b)).toFixed(2)+"%");
				    $("#trueFailureRate").text(parseFloat(100*a/(a+b)).toFixed(2)+"%");
				    $("#falseFailureRate").text(parseFloat(100*c/(c+d)).toFixed(2)+"%");
				    
				    $("#accuracyRate").css("font-size", "36px");
				    $("#trueSuccessRate").css("font-size", "36px");
				    $("#falseSuccessRate").css("font-size", "36px");
				    $("#trueFailureRate").css("font-size", "36px");
				    $("#falseFailureRate").css("font-size", "36px");


					

					var data = JSON.parse(dataDescription);
					console.log("data " + data);
					var data2 = eval( '(' + dataDescription + ')' );
					var numberOfInstancesValue ="";
				    

//					console.log(data2);
//					console.log("data2.count " + data2.count);
//					console.log("data2[0].count " + data2[0]);
//					$("#confusionMatrix").html(dataDescription);	
				},
				error:function(data,status,er) {
					if (window.console) console.log("error: "+data+" status: "+status+" er:"+er);
				},
				timeout: 500000 // sets timeout to 3 seconds
			});


		});

