<!DOCTYPE html>
<html>
<head>
<title>SMSGA - Dashboard</title>
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
<link rel="stylesheet" href="style/smsga/bootstrap-responsive.css">
<link rel="stylesheet" href="style/smsga/bootstrap.min.css">
<link rel="stylesheet" href="style/smsga/theme.css">
<link rel="stylesheet" href="style/smsga/animation.css">
<link rel="stylesheet" href="style/smsga/fonts/social.css">
<link rel="stylesheet" href="style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="style/smsga/fonts/standard.css">
<link rel="stylesheet" href="style/smsga/flexslider.css" />
<link rel="stylesheet" href="style/smsga/jquery.pageslide.css">
<link rel="stylesheet" href="style/smsga/jquery-ui.css">
<link rel="stylesheet" href="js/smsga/telephoneWithCountry/build/css/intlTelInput.css">
<link rel="stylesheet" href="style/smsga/tooltip/tooltipster.css">



<script type="text/javascript" src="/js/smsga/jquery.min.js?v=182"></script>
<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>
<![endif]-->
</head>
<body id="blog">
	<div id="wrap">
		<header>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span2">
						<div id="logo" style="display: none;">
							<a href="/nexmo.html" title="Go to Home page"></a>
						</div>
					</div>
					<div class="span10">
						<!-- Primary Navigation -->
						<div class="nav_link">
								<a class="open" href="#nav">
									<span id="main_mobile_menu_toggle">&#9776;</span>
								</a>
						</div>
						
						<nav>
							<ul id="nav" class="nav-slide">
								<li class="nav-tour"><a href="/smsga.html"><span
										class="link-wrap">Home</span></a></li>
								<li class="nav-pricing"><a href="/pricing"><span
										class="link-wrap">Pricing</span></a></li>
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
		<!-- remove after U.S Short Codes 
		<div class="hero-unit">
			  <h1>Go live with Analytics Bytes in minutes.</h1>  
			<p>
				Sign up and we'll give you <strong>30 days FREE </strong> your
				analytics data by SMS
			</p>
		</div>
		<!-- remove after U.S Short Codes  -->
		<div class="feature">
			<div class="row-fluid">
				<div class="form-wrapper">
				
			<div id="formResponseError"></div>
				

					<form id="registration_form" class="form-horizontal"
						action="/smsforgoogleanalyticloginservlet" method="post"
						autocomplete="on">

						<div class="control-group">
							<label class="control-label">Name<span class="required">*</span></label>
							<div class="controls">
								<input id="name" name="name"
									title="Please enter your name."
									placeholder="Enter your name..." class="input-xlarge" type="text" value="" />
									<span for="user_name_message"
									class="error inline-error" style="display: none;">username should be 3 to 20 characters and can have alphanumeric and '_' character </span>
							</div>
						</div>
						
						
						<div class="control-group">
							<label class="control-label">Mobile Number<span
								class="required">*</span></label>
							<div class="controls">
								<input id="tooltipMobileNumber" class="tooltip" title="Use the number for which you want to receive your daily stats."/>    
								<input type="tel" id="demo" name="mobile_number"
									placeholder="Please insert your phone number...">
								<input id="countryCode" name="countryCode" type="hidden" value="" />
								<span for="mobile_number_error"
									class="error inline-error" style="display: none;"> </span>
								<span for="mobile_number_message"
									class="error inline-error" style="display: none;">Note: Use this number to receive your daily stats </span>
									
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">Email Address<span
								class="required">*</span></label>
							<div class="controls">
								<input id=tooltip class="tooltip" title="Please use same email that has access to Analytics account">
								<input id="email_address" name="email_address" 
									placeholder="Enter your email..." class="input-xlarge"
									type="text" value="" />
						<!-- 	<div class="tooltip" title="Please use same email that has access to Analytics account">  Please use same email that has access to Analytics account </div>  -->
									
								<span id="email_address_error"
									class="error inline-error" style="display: none;">Please enter a valid email address.</span>
									
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Password<span
								class="required">*</span></label>
							<div class="controls">
								<input id="PasswordTooltip" class="tooltip" title="This doesn't have to be the same password as your GA account" autocomplete="off"/>
								<input id="password" name="password"
									title="Password should not be less than 6 characters."
									placeholder="Create a password..." class="input-xlarge"
									type="password" value="" autocomplete="off"/> 
								<span for="password" 
									class="error inline-error" style="display:none;">Password
									should be at least 6 characters with a maximum of 25
									characters.
								</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Confirm password<span class="required">*</span></label>
							<div class="controls">
								<input id="confirmPassword" name="confirmPassword"
									title="Please enter the same password as above."
									placeholder="Confirm your password..." class="input-xlarge"
									type="password" value="" autocomplete="off"/>
								<span for="confirmPassword"
									class="error inline-error" style="display: none;"> Please enter the same password as above.</span>
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
								<input class="btn" style="min-width:270px;" type="submit" value="Sign Up"/>
							</div>
						</div>
						
						<div class="control-group" style="display: block;">
						<input type="checkbox" name="confirmationCheckBox"  id="confirmationCheckBox" value="0">
						<input id="tooltipCheckBox" class="tooltip" title="Please check this box if you want to proceed"/>    
						
							<span class="confirmationCheckBox"  id ="terms">
							You will receive no more than 2 msgs/day. 
							To opt-out at any time, send STOP to 98975. 
							To receive more information, send HELP to 98975. 
							Message and Data Rates May Apply. 
							The terms and conditions can be viewed at here. 
							Our Privacy Policy can be reviewed at <a href="/privacy/"
								target="_blank">here </a>.
												 				
							</span>		
												
							<span for="terms_condition_error" class="error inline-error" style="display: none;">Please accept terms and conditions</span>
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
						<p class="last">
							By clicking the button you agree to Analytcis Bytes <a href="/terms/"
								target="_blank">Terms of Use </a>.
						</p>
					</form>

				</div>
			</div>
		</div>






	</div>




	<footer>
		<div class="container-fluid">
			<div class="copyright">
				<p>
					&copy; Copyright SMSGA Inc. 2014. All rights reserved. <span>
						<a href="terms">Terms and Conditions</a>
					</span>
				</p>

			</div>



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
		</div>
	</footer>


	<input type="hidden" id="_webapp_path" value="">



	<!-- GOOGLE ANALYTICS -->



	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-59479511-1' ]);
		_gaq.push([ '_setDomainName', 'dailyreportbysmsforga.appspot.com' ]);
		_gaq.push([ '_setAllowLinker', true ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();

		$(document).ready(function() {
			$(".gaq").click(function() {
				_gaq.push([ '_link', this.href ]);
				return false;
			});
		});
	</script>


	<!-- Google Code for Home 90 Remarketing List -->
	<script type="text/javascript">
		/* <![CDATA[ */
		/*
		 var google_conversion_id = 1001951389;
		 var google_conversion_language = "en";
		 var google_conversion_format = "3";
		 var google_conversion_color = "ffffff";
		 var google_conversion_label = "wrcPCNObmgQQnaHi3QM"; 
		 var google_conversion_value = 0;
		 */
		/* ]]> */
	</script>

	<!--
<script type="text/javascript" src="https://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="https://www.googleadservices.com/pagead/conversion/1001951389/?value=0&amp;label=wrcPCNObmgQQnaHi3QM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
-->


	<script type="text/javascript"
		src="/js/smsga/modernizr.custom.10128.js?v=182"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.flexslider-min.js?v=182"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.pageslide.min.js?v=182"></script>
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=182"></script>

	<script type="text/javascript"
		src="/js/smsga/jquery.validate.min.js?v=182"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=182"></script>
	<script type="text/javascript"
		src="/js/smsga/telephoneWithCountry/build/js/intlTelInput.js"></script> 
 	<script type="text/javascript" src="/js/registration.js?v=182"></script>
 	<script type="text/javascript" src="/js/smsga/jquery.tooltipster.min.js"></script>
 	
 	 <script>
        $(document).ready(function() {
            $('.tooltip').tooltipster();
        });
    </script>    

</body>
</html>

