var data = "";
var listWebPropertyItems="";
var listProfilesItems="";
var listGoalItems="";

var webPropertyMap = new Object(); // or var map = {};
var profileMap = new Object();
var profileNameMap = new Object();
var goalMap = new Object();
var goalNameMap = new Object();
var numberOfUsersVistedInYourSite = 'XXX';
var numberOfSessionVistedInYourSite = 'YYY';
var goalsCompletionValue = 0;
var goalsCompletionValueInPrecentage = 0;
var goalNameToisplay = "";
var mobileWidth = $( window ).width();



function get(k) {
    return webPropertyMap[k];
}
$(document).ready(
				function() {

					data= JSON.parse(accountsJson);
					disableProertyDropDownAndPrfofileDropDown();
				}); 


/**
$(document).ready(
		function() {
			$('#timepicker_customminutes')
					.timepicker(
							{
								minutes : {
									interval : 60
								},
								showPeriodLabels : false,
								beforeShow : function() {
									$(
											".ui-datepicker")
											.css(
													'font-size',
													18)
								}

							})
		});

**/
$(document).ready(
		function() 
		{
			var tzname = get_time_zone_name();
			$('#timeZoneDropdown').val(tzname);
		});



/**
 * 
 */
function displayWebPropertyForAccount(index) 
{
	listWebPropertyItems = "<option value='0'>--- Please Select ---</option>";
	// Check If Account was cleared
	if (index <0)
	{
		$("#webPropertyDD").html(listWebPropertyItems);
		disableProertyDropDownAndPrfofileDropDown();
		$("#accountIdHiddenValue").val("");
		$("#webPropertyIdHiddenValue").val("");
		$("#profileIdHiddenValue").val("");
		$("#profileNameHiddenValue").val("");
		$("#goalIdHiddenValue").val("");
		$("#goalNameHiddenValue").val("");		
		return;
	}
	enablePropertyDropDown();
	var accountId = data[index].accountId;
	$("#accountIdHiddenValue").val(accountId);

	$.ajax({
		type: "GET",
		url:"/webpropertyselection",
		dataType: "json",
		data: "accountId=" + accountId,
		contentType: 'application/json',
	    mimeType: 'application/json',
	    beforeSend: function() 
		{
			//$("#sms-recieved-container").html('<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />');
			$("#spinnerForWebProperty").show();

		},
		success: function(jsonResponse) 
		{ 
			
			for ( var i = 0; i < jsonResponse.length; i++) 
			{
				listWebPropertyItems += "<option value='" + (i+1) + "'>"+ jsonResponse[i].webPropertyName + "</option>";
				webPropertyMap[i] =  jsonResponse[i].webPropertyId;
			}
			
			$("#webPropertyDD").html(listWebPropertyItems);
			disableProfileDropDown();
			disableGoalNameDropDown();
			
		},
		error:function(data,status,er) {
	            
	     },
	    complete: function () 
		{
			$("#spinnerForWebProperty").hide();
		}
	});
	
	return listWebPropertyItems;
}

/**
 * 
 */
function displayProfileForAccountAndDeafaultWebProperty(accountNumberIndex, webPropertyIndex) 
{
	var listProfilesItems = "<option value='0'>--- Please Select ---</option>";
	// Check If Account was cleared
	if (accountNumberIndex <0 || webPropertyIndex<0)
	{
		$("#profileDD").html(listProfilesItems);	
		disableProfileDropDown();
		disableGoalNameDropDown();
		return;
	}
	enableProfileDropDown();
	var accountId = data[accountNumberIndex].accountId
	var webPropertyId = webPropertyMap[webPropertyIndex];
	
	$.ajax({
		type: "GET",
		url:"/profileselection",
		dataType: "json",
		data: "accountId=" + accountId + "&" + "webPropertyId=" +webPropertyId,
		contentType: 'application/json',
	    mimeType: 'application/json',
	    beforeSend: function() 
		{
			$("#spinnerForProfile").show();

		},
		success: function(jsonResponse) 
		{ 
			
			for ( var i = 0; i < jsonResponse.length; i++) 
			{
				listProfilesItems += "<option value='" + (i+1) + "'>"+ jsonResponse[i].profileName + "</option>";
				profileMap[i] =  jsonResponse[i].profileId;
				profileNameMap[i] =  jsonResponse[i].profileName;
			}
			$("#profileDD").html(listProfilesItems);		
			$("#webPropertyIdHiddenValue").val(webPropertyId);

			
		},
		error:function(data,status,er) {
			if (window.console) 
					console.log(er);

	     },
		complete: function () 
		{
			$("#spinnerForProfile").hide();
		}
	});
	
	return listProfilesItems;
}

/**
 * 
 */
function displayWebPropertyPerAccount() 
{
	var index = $("#accountDropdown").val();
	clearSMSText();
	var listWebPropertyItems = displayWebPropertyForAccount(index - 1);
}

function displayProfilePerAccountAndWebProperty() 
{
	var accountIndex = $("#accountDropdown").val();
	var webPropertyIndex = $("#webPropertyDD").val();
	var listProfilesItems = displayProfileForAccountAndDeafaultWebProperty(accountIndex - 1, webPropertyIndex -1);
}



/**
 * 
*/

function get_time_zone_name() 
{
	var timezone = jstz.determine();
	var tzname = timezone.name();
	return tzname;
}

function disableProertyDropDownAndPrfofileDropDown()
{
	disablePropertyDropDown();
	disableProfileDropDown();
	disableGoalNameDropDown();
	

}

function enablePropertyDropDown()
{
	$("#webPropertyDD").prop("disabled", false);
}


function disablePropertyDropDown()
{
	$("#webPropertyDD").html("")
	$("#webPropertyDD").prop("disabled", true);
}

function enableProfileDropDown()
{
	$("#profileDD").prop("disabled", false);
}



function disableProfileDropDown()
{
	$("#profileDD").html("")
	$("#profileDD").prop("disabled", true);
}

function disableGoalNameDropDown()
{
	$("#goalNameDD").html("")
	$("#goalNameDD").prop("disabled", true);
}

function profileWasChosen()
{
	var profileIndex = $("#profileDD").val();
	var profileMapValue =  profileMap[profileIndex-1];
	var profileNameMapValue =  profileNameMap[profileIndex-1];
	$("#profileIdHiddenValue").val(profileMapValue);
	$("#profileNameHiddenValue").val(profileNameMapValue);
	displayGoalName();
	if (profileIndex > 0)
	{
		var metrics = ["ga:users","ga:sessions"];
			if (($( window ).width()) > 960)
			{
				replaceValueOnMobile(metrics,""); //TODO - Call it only in case mobile screen should be displayed
			}	
	}
}

function displayGoalName()
{
	listGoalItems = "<option value='0'>--- Please Select ---</option>";

	$("#goalNameDD").prop("disabled", false);
	var accountId = $("#accountIdHiddenValue").val();
	var webPropertyId=$("#webPropertyIdHiddenValue").val();
	var profileId = $("#profileIdHiddenValue").val();
	$.ajax({
		type: "GET",
		url:"/goalnameselection",
		dataType: "json",
		data: "accountId=" + accountId + "&" + "webPropertyId=" +webPropertyId + "&" + "profileId=" +profileId,
		contentType: 'application/json',
	    mimeType: 'application/json',
		success: function(jsonResponse) 
		{ 
			
			for ( var i = 0; i < jsonResponse.length; i++) 
			{
				listGoalItems += "<option value='" + (i+1) + "'>"+ jsonResponse[i].goalName + "</option>";
				goalMap[i] =  jsonResponse[i].goalId;
				goalNameMap[i] =  jsonResponse[i].goalName;
			}
			$("#goalNameDD").html(listGoalItems);		
			
		},
		error:function(data,status,er) {
			if (window.console) console.log("error: "+data+" status: "+status+" er:"+er);
	     }
	});
	return listGoalItems;

}

function goalWasChosen()
{
	var goalIndex = $("#goalNameDD").val();
	var goalMapValue =  goalMap[goalIndex-1];
	var goalNameMapValue =  goalNameMap[goalIndex-1];
	$("#goalIdHiddenValue").val(goalMapValue);
	$("#goalNameHiddenValue").val(goalNameMapValue);
	$("#goalNameOnMobileDisplay").text(goalNameMapValue);
	goalNameToisplay = goalNameMapValue;
	var metrics = ["ga:goal"+goalMapValue+"Completions"];
	if (($( window ).width()) > 960)
	{
		setTimeout(function() { replaceValueOnMobile(metrics,goalNameMapValue); }, 3500); 	 //FIXME - Check if goal was written properly.
	}
	
	
}

//FIXME - Pull Out metrics as argument and not hard coded!!! 

function replaceValueOnMobile(metrics,goalNameMapValue)
{


	var accountId = $("#accountIdHiddenValue").val();
	var webPropertyId=$("#webPropertyIdHiddenValue").val();
	var profileId = $("#profileIdHiddenValue").val();
	$.ajax({
		type: "GET",
		url:"/metricsvaluebeforesubmittion",
		dataType: "json",
		data: "accountId=" + accountId + "&" + "webPropertyId=" +webPropertyId + "&" + "profileId=" +profileId +"&" + "metrics=" +metrics,
		contentType: 'application/json',
	    mimeType: 'application/json',
		success: function(jsonResponse) 
		{ 
			
			for ( var i = 0; i < jsonResponse.length; i++)  
			{
				metricName =  jsonResponse[i].metricName;
				metricValue =  jsonResponse[i].metricValue;
				if (metricName == "ga:users")
				{
					$("#numberOfUsersOnMobileDisplay").text(metricValue);
					$("#numberOfUsersVistedInYourSite").text(" " + metricValue);
					numberOfUsersVistedInYourSite = metricValue;
					
				}
				else if(metricName == "ga:sessions")
				{
					$("#numberOfSessionsOnMobileDisplay").text(metricValue);
					$("#numberOfSessionVistedInYourSite").text(" " + metricValue);
					numberOfSessionVistedInYourSite = metricValue;
					
				}else if (metricName.match("ga:goal")) 
				{
					var numberOfSessionsOnMobileDisplay = parseInt($("#numberOfSessionsOnMobileDisplay").text());
					var percentage = '0'; 
					if (numberOfSessionsOnMobileDisplay>0)
					{
					 	percentage = (metricValue/numberOfSessionsOnMobileDisplay)*100;
					 	percentage = $.number( percentage, 2 ); 
					}
					$("#goalsCompletionOnMobileDisplay").text(metricValue + " (" + percentage + "%)" );
					goalsCompletionValue = metricValue;
					goalsCompletionValueInPrecentage = percentage;
					//Display Goal On  Mobile
					displayGoalNameOnSMSText(goalsCompletionValue, goalsCompletionValueInPrecentage,goalNameMapValue);
					
				}	
			}
			if (goalNameMapValue == "")
			{
				displaySMSText(); //TODO - Call it only in case mobile screen should be displayed
			}
			
		},
		error:function(data,status,er) {
			if (window.console) console.log("error: "+data+" status: "+status+" er:"+er);
	     }
	});
	return listGoalItems;

}

function displaySMSText()
{

	$("#displayOnMobile").css("display", "block");
	 var hiUserName = $("#hiUserName").text();
	 var dateStr = $("#DateOnMobileDisplay").text();
	 var numberOfUsersOnMobileDisplay = $("#numberOfUsersOnMobileDisplay").text();
	 var numberOfSessionsOnMobileDisplay = $("#numberOfSessionsOnMobileDisplay").text();

	 $("#typedItOnMobileDevice").text("");
	 $("#typedItOnMobileDevice").typed({
	        strings: [hiUserName + "<br>"  +
	        		"On " +  dateStr + "," + "<span id='numberOfUsersVistedInYourSite'>" + " " + numberOfUsersVistedInYourSite +  "<\/span> " +
	        		"  " + 
	        		" users visited your website in " + "<span id='numberOfSessionVistedInYourSite'>" + numberOfSessionVistedInYourSite +  "<\/span> " + "  sessions. " +
	        		"<span id='goalsCompletionSpan'> " + "<\/span>  "],
	        typeSpeed: 10,
	        backDelay: 500,
	        loop: false,
	        loopCount: false,
	       callback: function() 
	        {
	        	$("#numberOfUsersVistedInYourSite").text(" " + numberOfUsersVistedInYourSite);
	        	$("#numberOfUsersVistedInYourSite").animate({opacity:0},200,"linear",function(){
	        		  $(this).animate({opacity:1},200);
	        	});
	        	$("#numberOfSessionVistedInYourSite").text(" " + numberOfSessionVistedInYourSite);
	        	$("#numberOfSessionVistedInYourSite").animate({opacity:0},200,"linear",function(){
	        		  $(this).animate({opacity:1},200);
	        	});

	        	
	        },

	      });
	 
}


function clearSMSText()
{
	
	 $("#typedItOnMobileDevice").text("");


}

function displayGoalNameOnSMSText(goalsCompletionValue, goalsCompletionValueInPrecentage, goalName)
{
	 $("#goalsCompletionSpan").text("");
	
	 $("#goalsCompletionSpan").typed({
	        strings: 
	        	["<br>"+ goalsCompletionValue + " ( " + goalsCompletionValueInPrecentage + "% ) sessions reached your goal of ' " + goalName + " ' "],
	        typeSpeed: 10,
	 	    backDelay: 500,
	 	    loop: false,
	 	    loopCount: false,	
	 	    showCursor :false,
	 	   // either html or text
            contentType: 'html',

     });

}

	 
	
	 
	

