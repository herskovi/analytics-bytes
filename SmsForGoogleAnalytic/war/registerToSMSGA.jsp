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

<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic' rel='stylesheet' type='text/css'>
<!--  
<link rel="stylesheet" href="../style/smsga/bootstrap-responsive.css">
<link rel="stylesheet" href="../style/smsga/bootstrap.min.css">
-->
<link rel="stylesheet" href="../style/smsga/theme.css">
<link rel="stylesheet" href="../style/smsga/animation.css">
<link rel="stylesheet" href="../style/smsga/fonts/social.css">
<link rel="stylesheet" href="../style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="../style/smsga/fonts/standard.css">
<link rel="stylesheet" href="../style/smsga/flexslider.css" />
<link rel="stylesheet" href="../style/smsga/jquery.pageslide.css">
<link rel="stylesheet" href="../style/smsga/jquery-ui.css">
<link rel="stylesheet" href="../js/smsga/telephoneWithCountry/build/css/intlTelInput.css">
<link rel="stylesheet" href="../style/smsga/tooltip/tooltipster.css">
<link rel="stylesheet" href="../style/smsga/testimonial.css">
<!-- Custom styles for this template -->
<link href="css/jumbotron.css" rel="stylesheet"> <!-- added by oran -->
<link href="css/bootstrap.min.css" rel="stylesheet">    <!-- added by oran -->
<link rel="stylesheet" href="css/analyticsbytes.css"> <!-- added by oran -->



<script type="text/javascript" src="/js/smsga/jquery.min.js?v=182"></script>
<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>
<![endif]-->
</head>
<body id="blog">


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
							<a class="open" href="#nav"> <span
								id="main_mobile_menu_toggle">&#9776;</span>
							</a>
						</div>

						<nav>
							<ul id="nav" class="nav-slide">
								<li class="nav-tour"><a href="/home"><span
										class="link-wrap">Home</span></a></li>
							<!-- 	<li class="nav-pricing"><a href="/pricing"><span class="link-wrap">Pricing</span></a></li>  -->
								<li class="nav-docs"><a href="/support"><span
										class="link-wrap">Support</span></a></li>
								<li class="login"><a href="/login"><span
										class="link-wrap">Login</span></a></li>
								<li class="signup"><a href="/register" class="btn-primary">Try
										it for Free</a></li>
							</ul>
							<!-- / Primary Navigation -->
							<ul class="login-sign-up nav-slide">
								<li class="login nav-login"><a href="/login"><span
										class="link-wrap">Login</span></a></li>
								<li class="signup nav-sign-up"><a href="/register"
									class="btn-primary"><span class="link-wrap">Try it
											for Free</span></a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<!-- / container-fluid -->
		</header>

		<div class="hero-unit">
			<h1>Get your first text message in 2 minutes.</h1>
			<p>
				Sign up for a free 30 day trial - <strong>No credit card
					required </strong>
			</p>
		</div>

		<div class="feature">
		<!--  -->	<div class="row-fluid"> 
				<div class="form-wrapper">

					<form autocomplete="off" id="registration_form" class="form-horizontal"
						action="/smsforgoogleanalyticloginservlet" method="post">
						
								<div class="control-group" id="formResponseError" style="display: none;">
									<div class="ui-widget">
		    							<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"> 
		        							<p>
		            							<span class="ui-icon ui-icon-alert"  style="float: left; margin-right: .3em;"></span>            							
		            							<span id="error_message" name="error_message" class="error inline-error">
		        							</p>
		    							</div>
									</div>
								</div>
						<!-- 
							<div class="control-group">
								<label class="control-label">&nbsp&nbsp&nbsp&nbsp</label>
								<div class="controls">
									<div class="ui-widget" id="formResponseError"  style="display: block;">
										    <div class="ui-state-error ui-corner-all" style="padding: 0.7em;"> 
										        <p>
	            									<span class="ui-icon ui-icon-alert" 
	                										style="float: left; margin-right: .3em;"></span>
	            											<strong>Alert:</strong> Telephone Number with Same Email already Exists.
	        									</p>
										            
										    </div>
										</div>
								</div>
							</div>
						 -->
						
							<input id="uniqueAccountNumber" name="uniqueAccountNumber"
							type="hidden" value="0" />

						<div class="control-group">
							<label class="control-label">Name<span class="required">*</span></label>
							<div class="controls">
								<input id="name" name="name" title="Please enter your name."
									placeholder="Enter your name..." class="input-xlarge"
									type="text" value="" tabindex=1 />
								 <span for="user_name_message"
									class="error inline-error" style="display: none;">Name
									should be 3 to 20 characters.</span>
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Mobile No.<span
								class="required">*</span></label>
							<div class="controls">
								<input id="tooltipMobileNumber" class="tooltip"
									title="Use the number for which you want to receive your daily stats." />
								<input type="tel" id="demo" name="mobile_number"
									placeholder="SMS will be sent to this number" tabindex=2> <input
									id="countryCode" name="countryCode" type="hidden" value="" />
								<span for="mobile_number_error" class="error inline-error"
									style="display: none;"> </span> <span
									for="mobile_number_message" class="error inline-error"
									style="display: none;">Note: Use this number to receive
									your daily stats </span>

							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Email <span class="required">*</span></label>
							<div class="controls">
								<input id=tooltip class="tooltip"
									title="Please use same email that has access to Analytics account">
								<input id="email_address" name="email_address"
									placeholder="Enter your email..." class="input-xlarge"
									type="text" value="" tabindex=3/>
								<!-- 	<div class="tooltip" title="Please use same email that has access to Analytics account">  Please use same email that has access to Analytics account </div>  -->

								<span id="email_address_error" class="error inline-error"
									style="display: none;">Please enter a valid email
									address.</span>

							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Password<span class="required">*</span></label>
							<div class="controls">
								<input id="PasswordTooltip" class="tooltip"
									title="This doesn't have to be the same password as your GA account"
									autocomplete="off" /> <input id="password" name="password"
									title="Password should not be less than 6 characters."
									placeholder="Create a password..." class="input-xlarge"
									type="password" value="" autocomplete="off" tabindex=4/> <span
									for="password" class="error inline-error"
									style="display: none;">Password should be at least 6 characters </span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Confirm Password<span
								class="required">*</span></label>
							<div class="controls">
								<input id="confirmPassword" name="confirmPassword"
									title="Please enter the same password as above."
									placeholder="Confirm your password..." class="input-xlarge"
									type="password" value="" autocomplete="off" tabindex=5 /> <span
									for="confirmPassword" class="error inline-error"
									style="display: none;"> Please enter the same password
									as above.</span>
							</div>
						</div>




						<!--  End Yahoo Country Code -->
						<!--  
						<div class="control-group">
							<label class="control-label">Country<span
								class="required">*</span></label>
							<div class="controls">
								<select name="countryCode" id="countryCode" class="input-xlarge"
									onchange="changeCountryCodeForMobileNumber()";>
									<option value="IL">Israel</option>
									<option value="DZ">Algeria</option>
									<option value="AR">Argentina</option>
									<option value="AU">Australia</option>
									<option value="AT">Austria</option>
									<option value="BS">Bahamas</option>
									<option value="BH">Bahrain</option>
									<option value="BY">Belarus</option>
									<option value="BE">Belgium</option>
									<option value="BJ">Benin</option>
									<option value="BO">Bolivia</option>
									<option value="BW">Botswana</option>
									<option value="BR">Brazil</option>
									<option value="BG">Bulgaria</option>
									<option value="CA">Canada</option>
									<option value="KH">Cambodia</option>
									<option value="KY">Cayman Islands</option>
									<option value="CL">Chile</option>
									<option value="CN">China</option>
									<option value="CO">Colombia</option>
									<option value="CR">Costa Rica</option>
									<option value="HR">Croatia</option>
									<option value="CY">Cyprus</option>
									<option value="CZ">Czech Republic</option>
									<option value="DK">Denmark</option>
									<option value="DO">Dominican Republic</option>
									<option value="EG">Egypt</option>
									<option value="SV">El Salvador</option>
									<option value="EE">Estonia</option>
									<option value="FI">Finland</option>
									<option value="FR">France</option>
									<option value="GE">Georgia</option>
									<option value="DE">Germany</option>
									<option value="GH">Ghana</option>
									<option value="GR">Greece</option>
									<option value="GD">Grenada</option>
									<option value="HK">Hong Kong</option>
									<option value="HN">Honduras</option>
									<option value="HU">Hungary</option>
									<option value="ID">Indonesia</option>
									<option value="IS">Iceland</option>
									<option value="IN">India</option>
									<option value="IE">Ireland</option>
									<option value="IL">Israel</option>
									<option value="IT">Italy</option>
									<option value="JM">Jamaica</option>
									<option value="JP">Japan</option>
									<option value="KE">Kenya</option>
									<option value="LV">Latvia</option>
									<option value="LI">Liechtenstein</option>
									<option value="LT">Lithuania</option>
									<option value="LU">Luxembourg</option>
									<option value="MO">Macau</option>
									<option value="MY">Malaysia</option>
									<option value="MT">Malta</option>
									<option value="MU">Mauritius</option>
									<option value="MX">Mexico</option>
									<option value="MD">Moldova</option>
									<option value="NL">Netherlands</option>
									<option value="NZ">New Zealand</option>
									<option value="NG">Nigeria</option>
									<option value="NO">Norway</option>
									<option value="PK">Pakistan</option>
									<option value="PA">Panama</option>
									<option value="PE">Peru</option>
									<option value="PH">Philippines</option>
									<option value="PR">Puerto Rico</option>
									<option value="PL">Poland</option>
									<option value="PT">Portugal</option>
									<option value="RO">Romania</option>
									<option value="RU">Russia</option>
									<option value="RS">Serbia</option>
									<option value="SG">Singapore</option>
									<option value="SK">Slovakia</option>
									<option value="SI">Slovenia</option>
									<option value="ZA">South Africa</option>
									<option value="KR">South Korea</option>
									<option value="ES">Spain</option>
									<option value="SE">Sweden</option>
									<option value="CH">Switzerland</option>
									<option value="TJ">Tajikistan</option>
									<option value="TW">Taiwan</option>
									<option value="TH">Thailand</option>
									<option value="TT">Trinidad and Tobago</option>
									<option value="TR">Turkey</option>
									<option value="UA">Ukraine</option>

									<option value="AE">United Arab Emirates</option>

									<option value="GB">United Kingdom</option>

									<option value="US">United States</option>

									<option value="UY">Uruguay</option>

									<option value="UZ">Uzbekistan</option>

									<option value="VE">Venezuela</option>

								</select>
							</div>
						</div>
						-->


						<!-- Phone number in old Format 
		<div class="control-group">
			<label class="control-label">Mobile Number<span class="required">*</span></label>
			<input id="country_code_mobile_number" name="country_code_mobile_number" title="Please insert your phonenumber"  class="input-mini" type="text" value="972" readonly/> - 
			<input id="mobile_number" name="mobile_number" title="Please insert your phonenumber" placeholder="Please insert your phone number..." class="input-large" type="text" value=""/>
		</div>
		-->



						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<input class="btn" style="min-width: 270px;" type="submit"
									value="Start My Free Trial" tabindex=6 />
							</div>
						</div>

						<div class="control-group" style="display: none;">
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
					<div class="control-group" style="display: block;">
							<span class="forwardedToGA" id="forwardedToGoogleAnalytics"> 
							After clicking the button you will be forwarded to 
							Google Analytics authentication form
							</span>
					</div>
						<p class="last">
							By clicking the button you agree to Analytcis Bytes <a
								href="/terms/" target="_blank">Terms of Use </a>.
						</p>
					</form>

				</div>
			</div>
		</div>






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


 


	<script type="text/javascript" src="js/smsga/jquery.min.js"></script>
	<script src="js/smsga/jquery.pageslide.min.js"></script>
	<script src="js/smsga/jquery.flexslider-min.js"></script>
	<script src="js/smsga/scripts.js"></script>
	<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/telephoneWithCountry/build/js/intlTelInput.js"></script>
	<script type="text/javascript" src="/js/registration.js?v=182"></script>
	<script type="text/javascript" src="/js/smsga/jquery.tooltipster.min.js"></script>
	
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

