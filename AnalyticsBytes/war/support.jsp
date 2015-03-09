<!doctype html>

<html class="page-login"> 
<head>
<title>SMSGA - Dashboard</title> 
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="SMSGA is a simple way to get SMS  with your analytics data before your first coffee ">
<meta name="keywords" content="SMSGA Google Analytic Data ">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">


<!-- Google Webfonts -->

<link rel="stylesheet" href="/style/smsga/fonts/social.css">
<link rel="stylesheet" href="/style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="/style/smsga/fonts/standard.css">



<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap-responsive.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap.min.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/theme.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/flexslider.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery-ui.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/support-form.css?v=184" />


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
				<div id="logo"><a href="http://www.nexmo.com" title="Go to Home page"></a></div>
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
						<li class="nav-tour">
							<a href="/smsga.html">
								<span class="link-wrap">Home</span>
							</a>
						</li>
						<li class="nav-pricing">
							<a href="/pricing">
								<span class="link-wrap">Pricing</span>
							</a>
						</li>
					
						<li  class="nav-support">
							<a href="/support">
								<span class="link-wrap">Support</span>
							</a>
						</li>
                        
                        
                        <li class="login">
							<a href="/login" onClick="_gaq.push(['_trackEvent', 'Login', 'Button Click', 'Header']);">
								<span class="link-wrap">Login</span>
							</a>
						</li>
						<li class="signup">
							<a href="/register" class="btn-primary"  onClick="_gaq.push(['_trackEvent', 'Try It For Free', 'Button Click', 'Header']);">Try it for Free</a>
						</li>						
						
						
					</ul>
			
					<!-- / Primary Navigation -->
					<ul class="login-sign-up nav-slide">
						<li class="login nav-login">
							<a href="/login"><span class="link-wrap">Login</span></a>
						</li>
						<li class="signup nav-sign-up">
							<a href="/register" class="btn-primary"><span class="link-wrap">Try it for Free</span></a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div><!-- / container-fluid -->
</header>  	



<div class="hero-unit">
	<h1>Let us know how we can help you.</h1>
</div>

<div class="hvp-container">
		<div class="container-fluid">
			<div class="row-fluid centered">
				<h3 id="hvp_form">Submit a Request</h3>
				
			</div>
			
			<div class="row-fluid">
				<div class="span6 offset3">
					<form class="support-form" action="submitaticket" method="POST">
						
						<div class="form-item">
							<label for="first_name">First Name <span  class="req">*</span> </label>
							<input  id="first_name" maxlength="40" name="first_name" size="20" type="text" required/>
						</div>
						<div class="form-item">
							<label for="last_name">Last Name <span  class="req">*</span>  </label>
							<input  id="last_name" maxlength="80" name="last_name" size="20" type="text" required />
					    </div>
					    <div class="form-item">
							<label for="email">Email  <span  class="req">*</span> </label>   <input  id="email" maxlength="80" name="email" size="20" type="text" required />
					    </div>
					    <div class="form-item">
							<label for="company">Company <span  class="req">*</span>  </label>  <input  id="company" maxlength="40" name="company" size="20" type="text" required/>
						</div>
						<div class="form-item">
							<label for="description">How can we help you today?</label><textarea  id="description" rows="10" cols="8" name="description"></textarea>
						</div>
				  			<div class="form-item">
								<input class="btn" type="submit" name="submit">
							</div>
					</form>
				</div>		
			</div>

		</div><!-- / container-fluid --> 
	</div>	


  <div class="cta-2">
	  <div class="container-fluid centered">
		<p><span>Sign up is instant. Connect in minutes.</span> <a class="btn" href="/register">Try it for Free</a></p>
	  </div>
	</div>
  
  
  
<footer>
	<div class="container-fluid">
		<div class="copyright">
			<p>&copy; Copyright SMSGA Inc. 2014. All rights reserved.
			<span>
			<a href="terms">Terms and Condition</a>
			</span></p>
			
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


<input type="hidden" id="_webapp_path" value="">



<!-- GOOGLE ANALYTICS -->

<!--  
	
<script type="text/javascript">

  var isLoginError= "${login_error}"; // 1 is true;
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-59479511-1']);
  _gaq.push(['_setDomainName', 'smsga.com']);
  _gaq.push(['_setAllowLinker', true]); 
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  
  $(document).ready(function() {
	$(".gaq").click(function() {
	_gaq.push(['_link', this.href]);
	return false;
	});
  });


</script>

-->


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
<script type="text/javascript" src="https://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="https://www.googleadservices.com/pagead/conversion/1001951389/?value=0&amp;label=wrcPCNObmgQQnaHi3QM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>

  -->
<script type="text/javascript" src="js/smsga/jquery.min.js"></script>
<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>

</body>
</html>

