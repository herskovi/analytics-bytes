 var geocoder;
  var map;
  //var icon = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
  var parkIcon = 'img/park.png';
  var reserveParkIcon = 'img/reserveParking.jpg';
  var mobileDevice = false;
  var parkingLotMarkers = [];
  var parkingMarkers = [];
  var defaultZoom = 13;
  
  function initialize() {
  
	if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) )
	{
		mobileDevice = true;
	}
  
    geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(-25.363882,131.044922);
		
	if(navigator.geolocation)
	{
					var timeoutVal = 10 * 1000 * 1000;
			navigator.geolocation.getCurrentPosition(
				displayPosition, 
				displayError,
				{ enableHighAccuracy: true, timeout: timeoutVal, maximumAge: 0 }
			);
		navigator.geolocation.getCurrentPosition(function () {alert('Success');}, function() {alert('Error');});
	};
	
    var mapOptions = {
      zoom: defaultZoom,
      center: latlng
    }
    map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
	
  }

  function codeBaseAddress(address) {
	codeAddress(address);
	buildAddressParkingLots();
  }
  
  function codeAddress(address)
  {
	geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
      } else {
        console.log("Geocode was not successful for the following reason: " + status);
      }
    });
  }
  
    function returnMarkerForAddress(address, title, ajaxData, icon) {
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        //map.setCenter(results[0].geometry.location);
		var marker = returnMarker(map, results[0].geometry.location.lat(), results[0].geometry.location.lng(), title, ajaxData, icon);		
      } 
	  else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT || status == google.maps.GeocoderStatus.ZERO_RESULTS) {
		
		return false;
	  
	  }
	  else {
        alert("Geocode was not successful for the following reason: " + status);
		return false;
      }
	  
	  
	  		if(ajaxData)
		  {
			parkingMarkers.push(marker);
			
			google.maps.event.addListener(marker, 'click', function() {
						var contentString = getMarkerContent(ajaxData, true);
				
			var infowindow = new google.maps.InfoWindow({
					content: contentString
				});
			infowindow.open(map,marker);
		});		
		  }
		  else
		  {	
			parkingLotMarkers.push(marker);
		  }
	  
	  return true;
	  
    });
  }
  
  function returnMarker(map, lat, lng, title, retrieveLotParkingsData, icon)
  {
	  var marker = new google.maps.Marker({
      position: new google.maps.LatLng(lat, lng),
      map: map,
	  icon: icon,
      title: title
	  });
	  
	google.maps.event.addListener(marker, 'click', function() {
		if (retrieveLotParkingsData === undefined)
			callAjax(map, marker, title, lat, lng);
		else
		{
			
		}
	});
		  
	return marker;
  }
  
  function callAjax(map, marker, parkingLotName, lattitude, longtitude)
  {
	$.get("/amdocsparkingmarketplace", {'lattitude':lattitude, 'longtitude':longtitude, subscriber:'054838383', 'mobileDevice': mobileDevice} ,function(data, textStatus, jqXHR){
	
			var ajaxResponse = $.parseJSON(data);			
			buildParkingsMapOfTheLot(ajaxResponse);
	});
	
	// comment out  -start
	/*	var data = [ 
	 {"lattitude": 10004, "longitude": 45446, "name": 'My Parking1', "address":'Tel Aviv Feibel 6', "rate": '15 NIS per hour'}, 
	 {"lattitude": 10005, "longitude": 4536, "name": 'My Parking2', "address":'Tel Aviv Feibel 12', "rate": '25 NIS per hour'},
	  {"lattitude": 10008, "longitude": 4556, "name": 'My Parking3', "address":'Tel Aviv Feibel 3', "rate": '5 NIS per hour'},
	   {"lattitude": 10007, "longitude": 4556, "name": 'My Parking4', "address":'Tel Aviv Feibel 15', "rate": '45 NIS per hour'}
];
	
	buildParkingsMapOfTheLot(data);	*/
	// comment out - finish
  }
  
  function buildParkingsMapOfTheLot(parkingDataJSON)
  {		
	if(parkingDataJSON)
	{
		$.each(parkingDataJSON, function(i, item) {
			var marker = returnMarkerForAddress(parkingDataJSON[i].address,parkingDataJSON[i].address, parkingDataJSON[i], parkIcon);
			if(marker  === false)
			{
					setTimeout(returnMarkerForAddress(parkingDataJSON[i].address,parkingDataJSON[i].address, parkingDataJSON[i], parkIcon), 2000);		
			}
		});
		
		map.setZoom(15);
		clearMarkers(parkingLotMarkers);
		codeAddress('Tel Aviv Feibel');

	}
}
  
  function buildAddressParkingLots()
  {
	var foundPlaceText = 'Found parking at area of ';
	var parkLotNorth = foundPlaceText + document.getElementById("address").value + ' North';
	var parkLotWest = foundPlaceText + document.getElementById("address").value + ' West';
	var parkLotEast = foundPlaceText + document.getElementById("address").value + ' East';
	var parkLotSouth = foundPlaceText + document.getElementById("address").value + ' South';
	var ajaxData = undefined;
	
	returnMarkerForAddress('Tel Aviv Sderot Nordau', parkLotNorth, ajaxData, reserveParkIcon);
	returnMarkerForAddress('Tel Aviv Har Zion', parkLotSouth, ajaxData, reserveParkIcon);
	returnMarkerForAddress('Tel Aviv Ha-Yarkon', parkLotWest, ajaxData, reserveParkIcon);
	returnMarkerForAddress('Tel Aviv Feibel', parkLotEast, ajaxData, reserveParkIcon);
  }
  
  function getMarkerContent(specificParkingData)
  {
	var hrefAllLots = '';
	var payButtonHtml = '<div style="margin-top:10px"><input type="button" value="Pay" onclick="payForParking()"></div>';
		
		var contentString = '<div id="content" style="width:200px;height:100px;">'+
      '<h3 id="firstHeading" class="firstHeading" style="margin:0px;">'+specificParkingData.name+'</h3>'+
      '<div id="bodyContent">'+
	  '<div id="parkingAddress">Address:'+specificParkingData.address+'</div>'+
	  '<div id="parkingRate">Rate:'+specificParkingData.rate+'</div>'+
	  payButtonHtml+
      '</div>'+
      '</div>';

	 $('#content').parent().css('overflow', 'hidden');
	return contentString;  
  }
  
  function goBackToEnteredAddress()
  {
	clearMarkers(parkingMarkers);
	showMarkers(parkingLotMarkers);
	map.setZoom(defaultZoom);
	
  }
  
  // Shows any markers currently in the array.
function showMarkers(markersArr) {
  setAllMap(map, markersArr);
}

// Sets the map on all markers in the array.
function setAllMap(map , markersArr) {
  for (var i = 0; i < markersArr.length; i++) {
    markersArr[i].setMap(map);
  }
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers(markersArr) {
  setAllMap(null, markersArr);
}

function payForParking()
{
}

		function displayPosition(position) {
			var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
			var options = {
				zoom: 10,
				center: pos,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("map"), options);
			var marker = new google.maps.Marker({
				position: pos,
				map: map,
				title: "User location"
			});
			var contentString = "<b>Timestamp:</b> " + parseTimestamp(position.timestamp) + "<br/><b>User location:</b> lat " + position.coords.latitude + ", long " + position.coords.longitude + ", accuracy " + position.coords.accuracy;
			var infowindow = new google.maps.InfoWindow({
				content: contentString
			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map,marker);
			});
		}
		function displayError(error) {
			var errors = { 
				1: 'Permission denied',
				2: 'Position unavailable',
				3: 'Request timeout'
			};
			alert("Error: " + errors[error.code]);
		}
		function parseTimestamp(timestamp) {
			var d = new Date(timestamp);
			var day = d.getDate();
			var month = d.getMonth() + 1;
			var year = d.getFullYear();
			var hour = d.getHours();
			var mins = d.getMinutes();
			var secs = d.getSeconds();
			var msec = d.getMilliseconds();
			return day + "." + month + "." + year + " " + hour + ":" + mins + ":" + secs + "," + msec;
		}
  
  