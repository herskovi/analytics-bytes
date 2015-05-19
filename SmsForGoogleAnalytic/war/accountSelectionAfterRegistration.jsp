

<!doctype html>

<html class="page-login">
<head>
<title>Analytics Bytes - SMS that connects you directly to your analytics</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="SMSGA is a simple way to get SMS  with your analytics data before your first coffee ">
<meta name="keywords" content="SMSGA Google Analytic Data ">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">


<!-- Google Webfonts -->
<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="/style/smsga/fonts/social.css">
<link rel="stylesheet" href="/style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="/style/smsga/fonts/standard.css">
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap-responsive.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap.min.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/themeAccountSelection.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/flexslider.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery-ui.css?v=184" /> 
<link rel="stylesheet" type="text/css" media="all" ref="./style/plans.css">
<link rel="stylesheet" type="text/css" media="all" href="./style/accountSelection.css">
<link rel="stylesheet" href="style/include/ui-1.10.0/ui-lightness/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<link rel="stylesheet" href="./style/jquery.ui.timepicker.css?v=0.3.3" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="./style/plans.css">


 
 



<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>  
<![endif]-->
</head>
<body id="blog">

<!-- Google Tag Manager -->
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-5SJWBM"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-5SJWBM');</script>
<!-- End Google Tag Manager -->


	<div id="wrap">
		<header>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span2">
						<div id="logo" style="display: none;">
							<a href="/home" title="Go to Home page"></a>
						</div>
					</div>
					<div class="span10">
						<!-- Primary Navigation -->
						<div class="nav_link">
							<a class="open" href="#nav"> <span id="main_mobile_menu_toggle">&#9776;</span></a>
							
						</div>
						<nav>
							<ul id="nav" class="nav-slide">
					
								<li class="nav-tour"> <a href="/smsga.html"> <span class="link-wrap">Home</span></a></li>
								<!-- <li class="nav-pricing"><a href="/pricing"><span class="link-wrap">Pricing</span></a></li>  -->
								<li  class="nav-support"><a href="/support"><span class="link-wrap">Support</span></a></li>
									<li class="login"><a href="/login"
									onClick="_gaq.push(['_trackEvent', 'Login', 'Button Click', 'Header']);">
										<span class="link-wrap">Login</span>
								</a></li>
								<li class="signup"><a href="/register" class="btn-primary"	onClick="_gaq.push(['_trackEvent', 'Try It For Free', 'Button Click', 'Header']);">Try it for Free</a></li>
								
								
							</ul>
							<!-- / Primary Navigation -->
							<ul class="login-sign-up nav-slide">
								<li class="login nav-login"><a href="/login"><span
										class="link-wrap">Login</span></a></li>
								<li class="signup nav-sign-up"><a
									href="/register" class="btn-primary"><span
										class="link-wrap">Try it for Free</span></a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<!-- / container-fluid -->
		</header>
		<!-- <div class="hero-unit">
			<h1>Complete Registration</h1>
		</div>
 -->
 

	<div class="feature alt-bg">
		<div class="container-fluid">	
			<div class="row-fluid">
				<div class="span6 offset3"> 	
					<form id="planSelection" class ="form-horizontal" name="planSelection" action="/accountselectionservlet" method="post">
							
							 
							<c:set var="count" value="0" scope="page" />
							<c:set var="checked" value=" checked" scope="page" />
							<c:set var="accounts" value="${accountNameList}" scope="page" />
							<c:set var="userId" value="${userId}" scope="page" />
							<c:set var="accountsJson" value="${accountsJson}" scope="page" />
								
						
						<div class="control-group">
	
							<div class="control-row">
									<label class="control-label">Account <span class="required">*</span></label>
									<div class="controls">
									
										<input type="hidden" id="accountListJsonHidden" name="accountListJsonHidden"value="accountListJson : '${accountsJson}'" />  
										<input type="hidden" id="userId" name="userId" value='${userId}' />
										<input type="hidden" id="userName" name="userName" value='${userName}' />										
										<input type="hidden" id="accountIdHiddenValue" name="accountIdHiddenValue" value='' />
										<input type="hidden" id="accountNameHiddenValue" name="accountNameHiddenValue" value='' />
										<input type="hidden" id="webPropertyIdHiddenValue" name="webPropertyIdHiddenValue" value='' />
										<input type="hidden" id="webPropertyNameHiddenValue" name="webPropertyNameHiddenValue" value='' />
										<input type="hidden" id="profileIdHiddenValue" name="profileIdHiddenValue" value='' />
										<input type="hidden" id="profileNameHiddenValue" name="profileNameHiddenValue" value='' /> 
										<input type="hidden" id="goalIdHiddenValue" name="goalIdHiddenValue" value='' />
										<input type="hidden" id="goalNameHiddenValue" name="goalNameHiddenValue" value='' /> 
										 
										<select id="accountDropdown" name="accountDropdown" onchange="displayWebPropertyPerAccount()" class="input-xxlarge">
										
											<c:set var="count" value="0" scope="page" />
											<option value='0'>
													<c:out value="--- Please Select ---" />
											</option>
											<c:forEach var="accountNameList" items="${accountNameList}">
												<c:set var="count" value="${count + 1}" scope="page" />
												<option value='${count}'>
									 				<c:out value="${accountNameList.accountName}" />
												</option>
											</c:forEach>
										</select>
									</div>
							</div>
						</div>
							
						<div class="control-group">
							<div class="control-row"> 
									<label class="control-label">Property<span class="required">*</span></label>
									<div class="controls">
										<select id="webPropertyDD" name="webPropertyDD" onchange="displayProfilePerAccountAndWebProperty()" class="input-xxlarge">
											<option value='0'>
													<c:out value="--- Please Select ---" />
											</option>											
										</select>
										<div id="spinnerForWebProperty" style="display:none;" >
												<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />
										</div>
									</div>
									
							</div>
						</div>	
						<div class="control-group">
								<label class="control-label">View<span class="required">*</span></label>
								<div class="controls">
									<select id="profileDD" name="profileDD" class="input-xxlarge" onchange="profileWasChosen()" >
										<option value='0'><c:out value="--- Please Select ---" /></option> 
										
									</select>
									<div id="spinnerForProfile" style="display:none;" >
											<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />
									</div>
								</div>
						</div>
						
						<div class="control-group">
								<label class="control-label">Goal Name</label>
								<div class="controls">
									<select id="goalNameDD" name="goalNameDD" class="input-xxlarge" onchange="goalWasChosen()" >
										<option value='0'><c:out value="--- Please Select ---" /></option>
									</select>
								</div>
						</div>
						
														
						<div class="control-group">
							<div class="control-row">
							
								<label class="control-label">Time to Recieve SMS</label>
								<div class="controls">
									<div id="timepickerDIV">
										<input type="text" class="input-large"  name="timepicker_customminutes" id="timepicker_customminutes" value="08:00" style="max-width:90px;"/>
									
										<select id="timeZoneDropdown" name="timeZoneDropdown">
											<c:forEach var="availableTimeZone"
												items="${availableTimeZoneIDs}">
												<option value='${availableTimeZone}'>
													<c:out value="${availableTimeZone}" />
												</option>
											</c:forEach>
										</select>
									</div> 
								
								</div>
							</div>
						</div>
									
						
						<div id="metricsDiv" style="display: none;" > 
							<div class="control-group">
								<label class="control-label">Metric 1</label>
								<div class="controls">
								<input type="checkbox" name="metrics" value="ga:users" checked> 
								 <span class="required"> Number of Unique Visitors in the last 24 hours </span> 
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Metric 2</label>
								<div class="controls">
									<input type="checkbox" name="metrics" value="ga:sessions" checked> 
								 	<span class="required"> Total users and pageviews for the specified time period </span> 
								</div>
							</div> 
						</div>	
						<div class="control-group">
							<label class="control-label"></label>
								<div class="controls">
									<input class="btn" id="accountSelectionSubmitBtn" name="accountSelectionSubmitBtn" type="submit" value="Sign Up"/>
								</div>
						</div>
						
						<p class="last">
								By clicking the button you agree to the <a href="/terms/" target="_blank">Terms of Use and Privacy Policy</a>.
						</p>
					</form>
				</div>
			</div>	
		</div>	
	</div>
		<div class="container-fluid" id="displayOnMobileDiv" name="displayOnMobileDiv">	
			<div class="row-fluid">
				<div class="span6 offset4">
					<div id="displayOnMobile" class="feature centered clearfix cloud" style="display: none;">
						  <div  class="container-fluid"  >
									<span class="AppNamePreviewTitle firstType">Analytics Bytes</span> 
									<span class="letsStartBuilding" id="typedItOnMobileDevice"></span>
									<div id="MobileHiddenValue" style="display: none;">
						    			<span class="letsStartBuilding" id="hiUserName" >Hi ${userName},<br/></span>  
						    			<span class="displayOnMobileDevice" id="onWord" ></br> On </span>		    			
						    			<span class="displayOnMobileDevice" id="DateOnMobileDisplay">${yesterdayDate}</span>
						    			<span class="displayOnMobileDevice" id="numberOfUsersOnMobileDisplay">_____</span> 
						    			<span class="displayOnMobileDevice" id="userVisitedYourSite"> users visited your </span>
						    			<span class="displayOnMobileDevice" id="typeOfApplicationOnMobileDisplay"> website </span>
						    			<span class="displayOnMobileDevice" id="inWord"> in </span>
						    			<span class="displayOnMobileDevice" id="numberOfSessionsOnMobileDisplay">_____</span>
						    			<span class="displayOnMobileDevice" id="sessions"> sessions. </br></span>
						    			<span class="displayOnMobileDevice" id="goalsCompletionOnMobileDisplay"> _____ (%) </span> 
						    			<span class="displayOnMobileDevice" id="sessionsReachedYourGoal"> sessions reached your goal of </span>
						    			<span class="displayOnMobileDevice" id="goalNameOnMobileDisplay">______ </span> 
						 			</div>
					    	</div>
				    </div>
    			</div>
    		</div>
    	</div>
  

<footer>
	<div class="container-fluid">
		<div class="copyright">
			<p>&copy; Copyright SMSGA Inc. 2014. All rights reserved.
			<span> 
				<a href="terms">Terms and Conditions</a>
			</span>
			</p>
			
		</div>
		
		 
		
		<ul id="social">
			<li class="twitter">
				<a href="https://twitter.com/googleanalytics" target="_blank"><span class='ss-icon ss-social'>&#xF611;</span> Twitter</a>
			</li>
			<li class="facebook">
				<a href="https://www.facebook.com/pages/Google-Analytics/112070022138590" target="_blank"><span class='ss-icon ss-social'>&#xF610;</span> Facebook</a>
			</li>
			<li class="linkedin">
				<a href="https://www.linkedin.com/groups?gid=1604367" target="_blank"><span class='ss-icon ss-social'>&#xF612;</span> LinkedIn</a>
			</li>
		</ul>
	</div>
</footer>
</div>

			<input type="hidden" id="_webapp_path" value="">



			<!-- Google Code for Home 90 Remarketing List -->
			<!-- 
			<script type="text/javascript">
				/* <![CDATA[ */
				var google_conversion_id = 1001951389;
				var google_conversion_language = "en";
				var google_conversion_format = "3";
				var google_conversion_color = "ffffff";
				var google_conversion_label = "wrcPCNObmgQQnaHi3QM";
				var google_conversion_value = 0;
				/* ]]> */
			</script>
			<script type="text/javascript"
				src="https://www.googleadservices.com/pagead/conversion.js">
				
			</script>
			<noscript>
				<div style="display: inline;">
					<img height="1" width="1" style="border-style: none;" alt=""
						src="https://www.googleadservices.com/pagead/conversion/1001951389/?value=0&amp;label=wrcPCNObmgQQnaHi3QM&amp;guid=ON&amp;script=0" />
				</div>
			</noscript>

 		-->




<script type="text/javascript" src="js/smsga/jquery.min.js"></script>
<script type="text/javascript" src="js/smsga/typed.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery.number.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.timepicker.js?v=0.3.3"></script>
<script type="text/javascript" src="js/jstz.min.js"></script> 
<script src="js/smsga/jquery.pageslide.min.js"></script>    
<script src="js/smsga/jquery.flexslider-min.js"></script>
<script src="js/smsga/scripts.js"></script>
<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
<script type="text/javascript" src="js/accountSelection.js" charset="utf-8"></script>



<script type="text/javascript">
    var accountsJson= '${accountsJson}' ;
</script>
<!--  
<script>
  $(function(){
      $("#goalNameOnMobileDisplay").typed({
        strings: ["Well, here we are.^2000: adasdafafaf asdasda "],
        typeSpeed: 20,
        backDelay: 500,
        loop: false,
        loopCount: false,
      });
  });
</script>

-->
