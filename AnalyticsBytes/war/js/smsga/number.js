var Number=(function()
		{});
//;/sendsms?email=${email}
//(function(A){
//A("body").ready(function()
//{
////A.jqplot.config.enablePlugins=true;
//var B;
//Menu.displayButtons("#menu_dashbaord");
//Dashboard.enableGettingStarted();
//if(A("#_not_used").is("*"))
//{
//A("#getting_started_trigger").click();
//}
//if(A("#_activation").is("*"))
//{
//A("#welcome_message").slideDown("fast");
//}
//if(A("#bulletin_close_link").is("*"))
//{
//A("#bulletin_close_link").click(function(){
//admin.closeBulletin(function(){
//A("#bulletin_message").slideUp("fast");
//});
//});
//}
//Menu.enableSettings(A("#_uid").val());
//Dashboard.loadLastChart(A("#select_type").val());
//A("#select_type").change(function(){
//Dashboard.showSummary(A("#select_type").val());
//});
//});
//Dashboard.showSummary=function(B){
//view.getView("/widget/get_last_activity_report?t="+B,
//function(C){A("#last_activity_container").html(C);
//Dashboard.loadLastChart(B);
//});
//};
//Dashboard.enableGettingStarted=function(){
//A("#getting_started_trigger").click(function()
//{
//if(A("#welcome_message").is(":hidden"))
//{
//A("#welcome_message").slideDown("fast");
//}else
//{
//A("#welcome_message").slideUp("fast");
//}
//});
//A("#get_started_close").click(function(){
//A("#welcome_message").slideUp("fast");
//});
//};
//Dashboard.loadLastChart=function(B)
//{
//A(window).bind("resize",function(C,D)
//{
//plot1.replot({resetAxes:true});
//});
//A("#chartdiv").html("");
//admin.getLastUserReport(B,function(C)
//{
//if(C!=null&&C!="")
//{
//plot1=A.jqplot("chartdiv",[C],
//{axesDefaults:
//{tickOptions:
//{showMark:false}
//},
//axes:
//{xaxis:
//{renderer:A.jqplot.DateAxisRenderer,
//tickRenderer:A.jqplot.CanvasAxisTickRenderer,
//tickOptions:
//{angle:-90,fontFamily:"Arial",fontSize:"8pt",
//formatString:"%d %b %Y",tickInterval:"1 day"
//}
//},
//yaxis:
//{min:0,tickOptions:
//{formatString:"%d"}
//}
//},
//seriesDefaults:
//{color:"#35b30e",shadow:false,
//markerOptions:	{shadow:false}
//},
//grid:
//{gridLineColor:"#e8e8e8",background:"#ffffff",
//borderColour:"#e8e8e8",borderWidth:0,shadow:false
//},
//highlighter:
//{show:true,sizeAdjust:7,showTooltip:true,
//tooltipLocation:"n",fadeTooltip:true,tooltipFadeSpeed:"fast",
//tooltipOffset:3,tooltipAxes:"y",useAxesFormatters:true
//}
//});
//}
//});
//};
//})(jQuery);

$(document).ready(
		function() 
		{

			$.ajax({
				type: "GET",
				url:"/number",
				dataType: "json",
				data: useridStr, 
				//data:  JSON.stringify( {email= userid }), //FIXME - To populate the right email
				success: function(json) 
				{ 
					//alert(userName);
					example2 = $('#numberGrid').columns({
						data:json,
						schema: [
						    
						         {"header":"Mobile No.", "key":"telephoneNumber"},
						         {"header":"Profile Name", "key":"profileName"},
						         {"header":"Goal Name", "key":"goalName"}

						         ]
					});
				}
			});
		});



//$.ajax({
//type: "GET",
//url:"/sendsms",
//dataType: "json",
//data: useridStr, 
////data:  JSON.stringify( {email= userid }), //FIXME - To populate the right email
//success: function(json) 
//{ 
////alert(userName);
//example2 = $('#dashboardGrid').columns({data:json})
//}
//});



//success: function(json) 
//{ 
//alert(json);
//example3 = $('#dashboardGrid').columns({
//data:json,
//schema: [
//{"header":"ID", "key":"id", "template":"000{{id}}"},
//{"header":"Description", "key":"Description"},
////{"header":"Email", "key":"email", "template":'<a href="mailto:{{email}}">{{email}}</a>'}
//{"header":"Gender", "key":"gender"}
//]

//}); 
//}


//$('#dashboardGrid').columns({
//data: [
//{'Emp. Number': 00001, 'First Name':'John', 'Last Name':'Smith' },
//{'Emp. Number': 00002, 'First Name':'Jane', 'Last Name':'Doe' },
//{'Emp. Number': 00003, 'First Name':'Ted', 'Last Name':'Johnson' },
//{'Emp. Number': 00004, 'First Name':'Betty', 'Last Name':'Smith' },
//{'Emp. Number': 00005, 'First Name':'Susan', 'Last Name':'Wilson' },
//{'Emp. Number': 00006, 'First Name':'John', 'Last Name':'Doe' },
//{'Emp. Number': 00007, 'First Name':'Bill', 'Last Name':'Watson' },
//{'Emp. Number': 00008, 'First Name':'Walter', 'Last Name':'Wright' }
//]
//});

