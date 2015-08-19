<!DOCTYPE html>
<html>
<head>
<title>Analytics Bytes - Text Messages that connects you directly to your analytics</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="SMSGA is a simple way to push SMS with google analytics">
<meta name="keywords" content="Analytics Bytes">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">
<%@page contentType="text/html;charset=UTF-8"%>


<!-- Google Webfonts -->
<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic' rel='stylesheet' type='text/css'>
<link type="text/css" href="/analyticsbytes/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="/analyticsbytes/css/jumbotron.css" rel="stylesheet">
<link type="text/css" href="/analyticsbytes/css/analyticsbytes.css" rel="stylesheet">



<link rel="stylesheet" href="../style/smsga/animation.css">
<link rel="stylesheet" href="../style/smsga/fonts/social.css">
<link rel="stylesheet" href="../style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="../style/smsga/fonts/standard.css">
<link rel="stylesheet" href="../style/smsga/flexslider.css" />
<link rel="stylesheet" href="../style/smsga/jquery.pageslide.css">
<link rel="stylesheet" href="../style/smsga/jquery-ui.css">
<link rel="stylesheet" href="../js/smsga/telephoneWithCountry/build/css/intlTelInput.css">
<script type="text/javascript" src="/js/smsga/jquery.min.js"></script>
<script type="text/javascript" src="/analyticsbytes/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/smsga/jquery.min.js?v=182"></script>
</head>
<body id="home">
	<!-- Google Tag Manager -->
	<noscript>
		<iframe src="//www.googletagmanager.com/ns.html?id=GTM-5SJWBM"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<script type="text/javascript">(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-5SJWBM');</script>
	<!-- End Google Tag Manager -->


	<div id="wrap">
		<header>
			<nav class="navbar navbar-default navtop">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="/home"><strong>AnalyticsBytes</strong></a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="/whatwedo">What we do?</a></li>
							<li><a href="/support">Support</a></li>
						</ul>

						<div class="nav navbar-nav navbar-right navbarsignin">
							<a href="/login" type="button" class="btn">Log in</a> <a
								href="/register" type="button" class="btn btn-default topbtn">Test
								it now, It's Free</a>


						</div>


					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
	</header>
		
		<div class="rapper graydiv toppadding">
			<!-- added by oran -main gray div -->
			<div class="container">

			
		
		<div class="container whitediv toppadding">
		<!--  Adding 1, 2, 3 images -->
		<div class="row toppadding">
		
        	<div class="col-md-4">
            	 <img src="/analyticsbytes/images/step1.png" class="img-circle" alt="Step 1">
            	 	<br>
            	 	<div class="col-lg-12">
						<blockquote>
							<p>Your mobile number where you wish to recieve your FREE text messages</p>
						</blockquote>
					</div>
        	</div>
        	<div class="col-md-4 rec-div">
            	  <img src="/analyticsbytes/images/step2.png" class="img-circle" alt="Step 2">
            	  <br>  
            	  <div class="col-lg-12">
            	  		<blockquote>
							<p><b>Connect your Google Analytics Account and select your Website&#47App Analytics</b></p>
						</blockquote>
				 </div>   
        	</div>
        	<div class="col-md-4 rec-div">
            	  <img src="/analyticsbytes/images/step3.png" class="img-circle" alt="Step 3">
            	  <br>
            	  <div class="col-lg-12">
	            	   <blockquote>
							<p>Start recieving your Google Analytics results: Sessions, Users, Conversions!</p>
						</blockquote>    
				 </div>
        	</div>
    	</div>
    		
       	<div class="row">
       		<div class="form-wrapper">
       		 <form name="emailSelectionWizardForm" action="/emailgaselectionwizard" method="post" class="form-horizontal login-form">
       		 		<input id="uniqueAccountNumber" name="uniqueAccountNumber" type="hidden" value='${uniqueAccountNumber}' />
       		             	  <br>  
       		             	  <br>
       		             	  
       		             	   <br>
       		  <div class="form-group">           	   
            	 <div class="col-lg-12">
							<p style="font-style:italic;font-size: 1.875em;"> Please enter your Google Analytics account email</p>    
				 </div>  
				</div>
       		 
       				<div class="form-group">
 							<label for="empty" class="col-sm-2 control-label"></label>
							<div class="col-sm-8">
								<input id="email_address" name="email_address" placeholder="Example: myname@mywebsite.com" class="form-control input-lg" type="text" value="" tabindex=1 />
								<span id="email_address_error" class="error inline-error" style="display: none;">Please enter a valid email address.</span>
								<p style="font-style:italic;font-size: 1.825em; display:none"> * Make sure you are using a Google Analytics account</p>
								
							</div>
					</div>	
       				<div class="form-group">
 						<label for="empty" class="col-sm-2 control-label"></label>
 				  		<div class="col-sm-8">
 				  		<button type="submit" class="btn btn-success btn-lg btn-block" role="button">Next Step</a>
                  		</div>
                  	</div>
                  	<div class="form-group">
 						<label for="empty" class="col-sm-2 control-label"></label>
 				  		<div class="col-sm-8">
 				  			<br>
 				  			<br>
 				  			<img src="/analyticsbytes/images/googleAnalyticsPermissionScreen.png" class="img-rounded" alt="">
                  		</div>
                  </div>
			 </form>
			</div>
        	</div>
		</div>
	</div>
</div>

	  <div class="whitediv navbar"> <!-- added by oran - footer div -->
      <hr> 
        <p><span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was created by Analytics geeks, for Analytics geeks. And it will keep getting better.</p>
       
      </div>
	

	</div>
		<div></div>

	<input type="hidden" id="_webapp_path" value="">



	<!-- GOOGLE ANALYTICS -->



	<!--
<script type="text/javascript" src="https://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="https://www.googleadservices.com/pagead/conversion/1001951389/?value=0&amp;label=wrcPCNObmgQQnaHi3QM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
-->





	<script type="text/javascript" src="../js/smsga/jquery.min.js"></script> 
	<script src="../js/smsga/scripts.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>


	<script type="text/javascript">
		var isError = '${isError}';
		var errorMsg = '${errorMsg}';
		var name = '${name}';
		var mobile_number = '${mobile_number}';
		var countryCode = '${countryCode}';
		var email_address = '${email_address}';
		var password = '${password}';
		var uniqueAccountNumber = '${uniqueAccountNumber}';
	</script>


</body>
</html>

