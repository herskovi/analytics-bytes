
$(document).ready(
 	function findAll() {
 		var dataString="";
		$.ajax({
			type: 'GET',
		    contentType: "application/json; charset=utf-8",
			url: 'http://localhost:8080/iBario/rest/showContacts',
			dataType: "json", // data type of response
			data:dataString,
			success: function (data) 
			{
				var a=2;
				renderList(data);
				
			},
			error : function(jqXHR, setting, errorThrown){
				alert("Error: " + errorThrown + " Setting " + setting);
			}
			
		});
	});

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$.each(list, function(index, contactUs) {
		$('#dlg').append('<tr>'  
				+'<td>' + contactUs.fullName + '"</td>'
				+'<td>' + contactUs.company + '</td>'
				+'<td>' + contactUs.telephone+ '</td>'
				+'<td>' + contactUs.email+ '</td>'
				+'</tr>'
		);
	});
}


