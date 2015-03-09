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


function get(k) {
    return webPropertyMap[k];
}
$(document).ready(
				function() {

					data= JSON.parse(accountsJson);
					disableProertyDropDownAndPrfofileDropDown();
				});

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

$(document).ready(
		function() 
		{
			var tzname = get_time_zone_name();
			$('#timeZoneDropdown').val(tzname);
		});


$(document).ready(
$("#planSelection").submit(function( event ) 
	{
		alert( "Handler for .submit() called." );
		event.preventDefault();
	})
);


//	$(document).ready(
////		$("#accountSelectionSubmitBtn").click(function () {
////	
////			var webPropertyIndex = $("#webPropertyDD").val();
////			var webPropertyValue =  webPropertyMap[webPropertyIndex];
////			$("#webPropertyDD").html(webPropertyValue);
////			
////			var profileIndex = $("#profileDD").val();
////			var profileMapValue =  profileMap[profileIndex];
////			$("#profileDD").html(webPropertyValue);
////			
////			_gaq.push(['_trackEvent', 'Sign Up', 'Button Click', 'Registration']);
//	
//		 // event.preventDefault();
//		})
//);
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
	            alert("error: "+data+" status: "+status+" er:"+er);
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
	            alert("error: "+data+" status: "+status+" er:"+er);
	     },
		complete: function () 
		{
			$("#spinnerForProfile").hide();
		}
	});

	
	
	//Call Ajax to get all profile per account and webproperty
	
	
//	webPropertiesList[webPropertyIndex];
//	
//	var obj = data[accountNumberIndex].accountId
//	var length = data[accountNumberIndex].webPropertiesList[webPropertyIndex].profileDTList.length
////	
//	for ( var i = 0; i < length; i++) 
//	{
//		listProfilesItems += "<option value='" 	+ i + "'>"	+ obj.profileDTList[i].profileName + "</option>";
//	}
	
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
	$("#nameOfViewOnMobileDisplay").text(profileNameMapValue);
	displayGoalName();
	if (profileIndex > 0)
	{
		var metrics = ["ga:users","ga:sessions"];
		replaceValueOnMobile(metrics,"");
		

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
	            alert("error: "+data+" status: "+status+" er:"+er);
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
	replaceValueOnMobile(metrics,goalNameMapValue);  //FIXME - Check if goal was written properly.
	
	
}


function replaceValueOnMobile(metrics,goalNameMapValue)
{


	var accountId = $("#accountIdHiddenValue").val();
	var webPropertyId=$("#webPropertyIdHiddenValue").val();
	var profileId = $("#profileIdHiddenValue").val();
	; //FIXME - Pull Out metrics as argument and not hard coded!!! 
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
					 	percentage = (metricValue/numberOfSessionsOnMobileDisplay);
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
				displaySMSText();
			}
			
		},
		error:function(data,status,er) {
	            alert("error: "+data+" status: "+status+" er:"+er);
	     }
	});
	return listGoalItems;

}

function displaySMSText()
{

	$("#RightSide").css("display", "block");
	 var hiUserName = $("#hiUserName").text();
	 var yourDataFor = $("#yourDataFor").text();
	 var nameOfViewOnMobileDisplay = $("#nameOfViewOnMobileDisplay").text();
	 var dateStr = $("#DateOnMobileDisplay").text();
	 var numberOfUsersOnMobileDisplay = $("#numberOfUsersOnMobileDisplay").text();
	 var numberOfSessionsOnMobileDisplay = $("#numberOfSessionsOnMobileDisplay").text();

	 $("#typedItOnMobileDevice").text("");
	 $("#typedItOnMobileDevice").typed({
	        strings: [hiUserName + "<br>" + yourDataFor + " " + nameOfViewOnMobileDisplay + 
	        		" is here. <br> On " +  dateStr + "," + "<span id='numberOfUsersVistedInYourSite'>" + " " + numberOfUsersVistedInYourSite +  "<\/span> " +
	        		"  " + 
	        		" users visited your website in " + "<span id='numberOfSessionVistedInYourSite'>" + numberOfSessionVistedInYourSite +  "<\/span> " + "  sessions. " +
	        		"<span id='goalsCompletionSpan'> " + "<\/span>  "],
	        typeSpeed: 20,
	        backDelay: 500,
	        loop: false,
	        loopCount: false,
	       callback: function() 
	        {
	        	$("#numberOfUsersVistedInYourSite").text(" " + numberOfUsersVistedInYourSite);
	        	$("#numberOfUsersVistedInYourSite").animate({opacity:0},200,"linear",function(){
	        		  $(this).animate({opacity:1},200);
	        	});
	        	$("#numberOfSessionVistedInYourSite").text(" " + numberOfUsersVistedInYourSite);
	        	$("#numberOfSessionVistedInYourSite").animate({opacity:0},200,"linear",function(){
	        		  $(this).animate({opacity:1},200);
	        	});

	        	//$("#goalsCompletionOnMobileDisplay").text(" " + numberOfSessionVistedInYourSite);
	        	//$("#goalsCompletionSpan").text(" " + goalsCompletionValue + " ( " + goalsCompletionValueInPrecentage + "% ) sessions reached your goal of " );
	        	//$('#numberOfSessionVistedInYourSite').blink();
//	        	textStr.replace (/!/g,numberOfUsersVistedInYourSite);
//	        	textStr.replace(/@/g,numberOfSessionVistedInYourSite);
//	        	$("#typedItOnMobileDevice").text(textStr);
//	        	
	        },

	      });
	 
}


function clearSMSText()
{
	
//	 $("#hiUserName").text("");
//	 $("#yourDataFor").text("");
//	 $("#nameOfViewOnMobileDisplay").text("");
//	 $("#DateOnMobileDisplay").text("");
//	 $("#numberOfUsersOnMobileDisplay").text("");
//	 $("#numberOfSessionsOnMobileDisplay").text("");
//	 $("#numberOfUsersVistedInYourSite").text("");
//	 $("numberOfSessionVistedInYourSite").text("");
//	
	 $("#typedItOnMobileDevice").text("");


}

function displayGoalNameOnSMSText(goalsCompletionValue, goalsCompletionValueInPrecentage, goalName)
{
	 $("#goalsCompletionSpan").text("");
	
	 $("#goalsCompletionSpan").typed({
	        strings: 
	        	["<br>"+ goalsCompletionValue + " ( " + goalsCompletionValueInPrecentage*100 + "% ) sessions reached your goal of ' " + goalName + " ' "],
	        typeSpeed: 20,
	 	    backDelay: 500,
	 	    loop: false,
	 	    loopCount: false,	
	 	   // either html or text
            contentType: 'html',

     });

}

	 
	
	 
	
	/**
	  <div id="RightSide" style="display: none;">
		<span class="AppNamePreviewTitle firstType">Analytics Bytes</span>
		<span class="letsStartBuilding" id="hiUserName" >Hi ${userName},<br/>  
		<span class="displayOnMobileDevice" id="yourDataFor">Your data for</span> 
		<span class="displayOnMobileDevice" id="nameOfViewOnMobileDisplay">_________</span>
		<span class="displayOnMobileDevice" id="isHere">is here.</span>
		<span class="displayOnMobileDevice" id="onWord" ></br> On </span>		    			
		<span class="displayOnMobileDevice" id="DateOnMobileDisplay">${yesterdayDate}</span>
		<span class="displayOnMobileDevice" id="numberOfUsersOnMobileDisplay">_____</span> 
		<span class="displayOnMobileDevice" id=userVisitedYourSite"> users visited your </span>
		<span class="displayOnMobileDevice" id="typeOfApplicationOnMobileDisplay"> website </span>
		<span class="displayOnMobileDevice" id="inWord"> in </span>
		<span class="displayOnMobileDevice" id="numberOfSessionsOnMobileDisplay">_____</span>
		<span class="displayOnMobileDevice" id="sessions"> sessions. </br></span>
		<span class="displayOnMobileDevice" id="goalsCompletionOnMobileDisplay"> _____ (%) </span> 
		<span class="displayOnMobileDevice" id="sessionsReachedYourGoal"> sessions reached your goal of </span>
		<span class="displayOnMobileDevice" id="goalNameOnMobileDisplay">______ </span> 

		<!--  <div class="PhoneContentOverlay">  -->
			
		<!--  <div class="discoveryLoader">Your Analytic</div>
		<div class="letsStartBuilding">asasdasdasd</div>  -->
	</div>
	 */


