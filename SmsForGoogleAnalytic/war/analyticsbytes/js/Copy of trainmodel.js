var Dashboard=(function()
		{});


$(document).ready(
		function() 
		{

			$.ajax({
				type: "GET",
				url:"/readgooglecloudstorage",
				dataType: "json",
				data: useridStr, 
				//data:  JSON.stringify( {email= userid }), //FIXME - To populate the right email
				success: function(json) 
				{ 

					example2 = $('#dashboardGrid').columns({
						data:json,
						schema: [

						         {"header":"Label", "key":"label"},
						         {"header":"Hour", "key":"hour"},
						         {"header":"Minute", "key":"minute"},
						         {"header":"Traffic Source", "key":"sourceMedium"},
						         {"header":"Campaign", "key":"campaign"},
						         {"header":"Country", "key":"country"},
						         {"header":"Device", "key":"mobileDeviceInfo"},
						         {"header":"Browser", "key":"browser"},
						         {"header":"Device Type", "key":"deviceCategory"},
						         {"header":"Page Path", "key":"pagePath"}
						         ]
					});
				}


			});
		});
