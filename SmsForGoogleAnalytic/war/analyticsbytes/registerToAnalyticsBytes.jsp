<!DOCTYPE html>
<html>
<head>
<title>Analytics Bytes - SMS that connects you directly to your
	analytics</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="SMSGA is a simple way to push SMS with google analytics">
<meta name="keywords" content="Analytics Bytes">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">
<%@page contentType="text/html;charset=UTF-8"%>


<!-- Google Webfonts -->
<link
	href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic'
	rel='stylesheet' type='text/css'>

<link type="text/css"
	href="/analyticsbytes/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link type="text/css" href="/analyticsbytes/css/jumbotron.css"
	rel="stylesheet">
<link type="text/css" href="/analyticsbytes/css/analyticsbytes.css"
	rel="stylesheet">


<!--
<link rel="stylesheet" href="../style/smsga/theme.css"> 

<link rel="stylesheet" href="../style/smsga/bootstrap-responsive.css">
<link rel="stylesheet" href="../style/smsga/bootstrap.min.css">

-->
<link rel="stylesheet" href="../style/smsga/animation.css">
<link rel="stylesheet" href="../style/smsga/fonts/social.css">
<link rel="stylesheet" href="../style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="../style/smsga/fonts/standard.css">
<link rel="stylesheet" href="../style/smsga/flexslider.css" />
<link rel="stylesheet" href="../style/smsga/jquery.pageslide.css">
<link rel="stylesheet" href="../style/smsga/jquery-ui.css">
<link rel="stylesheet"
	href="../js/smsga/telephoneWithCountry/build/css/intlTelInput.css">
<link rel="stylesheet" href="../style/smsga/tooltip/tooltipster.css">
<link rel="stylesheet" href="../style/smsga/testimonial.css">

<script type=text/javascript src="/js/smsga/jquery.min.js"></script>
<script type=text/javascript
	src="/analyticsbytes/bootstrap/dist/js/bootstrap.min.js"></script>


<script type="text/javascript" src="/js/smsga/jquery.min.js?v=182"></script>
<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>
<![endif]-->
</head>
<body id="home">


	<!-- Google Tag Manager -->
	<noscript>
		<iframe src="//www.googletagmanager.com/ns.html?id=GTM-5SJWBM"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
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

		
		<div class="rapper graydiv toppadding">
			<!-- added by oran -main gray div -->
			<div class="container">

				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h1>
							<span class="hero-title">Lets get you up and running...</span>
						</h1>

						<p class="lead">Setting up your account is really easy and
							takes no longer then 72 seconds. Try us</p>
						<br />

					</div>
				</div>
			</div>
		</div>
		<div class="container whitediv toppadding">
			<!-- Oran Added - secondery white div -->
			<!-- Example row of columns -->
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<form autocomplete="off" id="registration_form" class="form-horizontal" action="/smsforgoogleanalyticloginservlet" 	method="post">

						<div class="form-group" id="formResponseError" style="display: none;">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								<div class="alert alert-error">
									
										 <span id="error_message" name="error_message"></span>
									
								</div>
							</div>
						</div>


						<input id="uniqueAccountNumber" name="uniqueAccountNumber" type="hidden" value="0" />

						<div class="form-group" >
							<label class="col-sm-4 control-label">Name<span class="required">*</span></label>
							<div class="col-sm-8">
								<input id="name" name="name" title="Please enter your name."
									placeholder="Enter your name..." class="form-control"
									type="text" value=""  aria-describedby="nameErrorStatus" tabindex=1 /> 
  									<span class="glyphicon glyphicon-remove form-control-feedback" style="display: none;"></span>
  									 <span id="nameErrorStatus" class="inline-error" style="display: none;">Name should be 3 to 20 characters</span>									
							</div>
						</div>


						<div class="form-group">
							<label for="mobile" class="col-sm-4 control-label">Mobile<span class="required">*</span></label>
							<div class="col-sm-8">
								<!-- Comment Out tooltip 
								<input id="tooltipMobileNumber" class="tooltip"
									title="Use the number for which you want to receive your daily stats." />
									 -->
								<input id="countryCode" name="countryCode" type="hidden" value="" /> 	 
								<input type="tel" id="demo" class="form-control" name="mobile_number" placeholder="SMS will be sent to this number" tabindex=2>
								<span id="mobile_number_error" name="mobile_number_error" class="error inline-error" style="display: none;">Please specify a valid phone number</span>
									

							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-sm-4 control-label">Email <span class="required">*</span></label>
							<div class="col-sm-8">
							<!--  
								<input id=tooltip class="tooltip"
									title="Please use same email that has access to Analytics account">
									 -->
								<input id="email_address" name="email_address"
									placeholder="name@myemail.com" class="form-control"
									type="text" value="" tabindex=3 />
								<!-- Comment Out tooltip
									<div class="tooltip" title="Please use same email that has access to Analytics account">  Please use same email that has access to Analytics account </div>  
									-->
										<span id="email_address_error" class="error inline-error"
									style="display: none;">Please enter a valid email address.</span>
									
								  <span class="glyphicon glyphicon-remove form-control-feedback" style="display: none;"></span>

							
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-4 control-label">Password<span class="required">*</span></label>
							<div class="col-sm-8">
							<!-- Comment Out tooltip
								<input id="PasswordTooltip" class="tooltip"	title="This doesn't have to be the same password as your GA account" 	autocomplete="off" />
								-->
								<input id="password" name="password" title="Password should not be less than 6 characters."
									placeholder="Password..." class="form-control"
									type="password" value="" autocomplete="off" tabindex=4 /> <span
									for="password" class="error inline-error"
									style="display: none;">Password should be at least 6
									characters </span>
							</div>
						</div>
						<div class="form-group">
							<label for="confirmPassword" class="col-sm-4 control-label">Confirm Password<span class="required">*</span></label>
							<div class="col-sm-8">
								<input id="confirmPassword" name="confirmPassword"
									title="Please enter the same password as above."
									placeholder="Confirm Password..." class="form-control"
									type="password" value="" autocomplete="off" tabindex=5 /> <span
									for="confirmPassword" class="error inline-error" style="display: none;"> Please enter the same password as above.</span>
							</div>
						</div>



 						<div class="form-group">
 							<label for="empty" class="col-sm-4 control-label"></label>
 								<div class="col-sm-8">
 						           <button type="submit" class="btn btn-primary btn-lg btn-block mainbtn" tabindex=6>Sign me in</button>
                            	</div>
                         </div>


					
						<div class="form-group" style="display: none;">
							<input type="checkbox" name="confirmationCheckBox"
								id="confirmationCheckBox" value="0"> <input
								id="tooltipCheckBox" class="tooltip"
								title="Please check this box if you want to proceed" /> <span
								class="confirmationCheckBox" id="terms"> You will receive
								no more than 1 msg/day. <!--  For U.S Only --> To opt-out at any
								time, send STOP to +19852083212. To receive more information,
								send HELP to +19852083212. Message and Data Rates May Apply. The
								terms and conditions can be viewed at here. Our Privacy Policy
								can be reviewed at <a href="/privacy/" target="_blank">here
							</a>.

							</span> <span for="terms_condition_error" class="error inline-error"
								style="display: none;">Please accept terms and conditions</span>
						</div>


						<!-- 	<div id="metricsDiv">
						<div class="control-group">
							<label class="control-label">Metric 1</label>
							<div class="controls">
							<input type="checkbox" name="metrics" value="ga:users" checked> 
							 <span class="required"> Number of Unique Visitors in the last 24 hours </span> 
							</div>
						</div>
 					
					-->
					
					 <div>
                                                   <label for="empty" class="col-sm-4 control-label"></label>
                                                  
                                                  <p class="last"> 
								By clicking the button you agree to Analytcis Bytes <a href="/terms/" target="_blank">Terms of Use </a>.
                                                  </p>
                                                  <br /> 
                                                  <h4><span class="forwardedToGA" id="forwardedToGoogleAnalytics"> 
                                                  <strong>Next step..</strong> you will be forwarded to 
							                     Google Analytics Safe Authentication form.</span></h4>
                                                 

                     </div>
                                 
					</form>

				</div>
			</div>
		</div>






	</div>

	  <div class="whitediv navbar"> <!-- added by oran - footer div -->
      <hr> 
        <p><span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was created by Analytics geeks, for Analytics geeks. And it will keep getting better.</p>
       
      </div>
	

	<!-- 
	<footer>
		<div class="container-fluid" id="testimonialDiv" name="testimonialDiv">
			<div class="copyright">
				<blockquote class="testimonial">
					<p>"” I knew I should have entered Google analytics every day
						but I just didn't have the time to do so. "”</p>
				</blockquote>
				<div class="arrow-down"></div>
				<p class="testimonial-author">
					Alon Heller | <span>COO at Correlsense</span>
				</p>
				 -->
	<!-- 
				<p>
					<strong>
					<p class="testimonial-author">Obi-Wan Kenobi | <span>Jedi Master</span></p>
					testimonial:before
				
				</strong> 
				</p>
 -->
	<!-- 		</div> -->


	<!-- 
			<ul id="social">
				<li class="twitter"><a
					href="https://twitter.com/googleanalytics" target="_blank"><span
						class='ss-icon ss-social'>&#xF611;</span> Twitter</a></li>
				<li class="facebook"><a
					href="https://www.facebook.com/pages/Google-Analytics/112070022138590"
					target="_blank"><span class='ss-icon ss-social'>&#xF610;</span>
						Facebook</a></li>
				<li class="linkedin"><a
					href="https://www.linkedin.com/groups?gid=1604367" target="_blank"><span
						class='ss-icon ss-social'>&#xF612;</span> LinkedIn</a></li>
			</ul>
-->
	<!--  		</div>
	</footer> 
	-->


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
	<script src="../js/smsga/jquery.pageslide.min.js"></script>
	<script src="../js/smsga/jquery.flexslider-min.js"></script>
	<script src="../js/smsga/scripts.js"></script>
	<script type="text/javascript"
		src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.validate.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/telephoneWithCountry/build/js/intlTelInput.js"></script>
	<script type="text/javascript" src="/analyticsbytes/js/registrationToAnalyticsBytes.js"></script> 
	<script type="text/javascript"
		src="/js/smsga/jquery.tooltipster.min.js"></script>

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

	<script>
        $(document).ready(function() {
            $('.tooltip').tooltipster();
        });
    </script>

</body>
</html>

