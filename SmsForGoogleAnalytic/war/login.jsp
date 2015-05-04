
<!doctype html>










<html class="page-login"> 
<head>
<title>Analytics Bytes - SMS that connects you directly to your analytics</title> 
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Analytics Bytes is a simple way to get SMS  with your analytics data before your first coffee ">
<meta name="keywords" content="Analytics Bytes Google Analytic Data ">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">


<!-- Google Webfonts -->
<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="/style/smsga/fonts/social.css">
<link rel="stylesheet" href="/style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="/style/smsga/fonts/standard.css">



<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap-responsive.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap.min.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/theme.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/flexslider.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery-ui.css?v=184" />





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

<c:set var="login_error" value="${login_error}" scope="page" />

  <div id="wrap">
    <header>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<div id="logo" style="display: none;">
					<a href="smsga.html" title="Go to Home page"></a>
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
						<li class="nav-tour">
							<a href="/smsga.html">
								<span class="link-wrap">Home</span>
							</a>
						</li>
						<!-- 
						<li class="nav-pricing">
							<a href="/pricing">
								<span class="link-wrap">Pricing</span>
							</a>
						</li>
						 -->
					
						<li  class="nav-support">
							<a href="/support">
								<span class="link-wrap">Support</span>
							</a>
						</li>
                        <li class="login">
							<a href="/login">
								<span class="link-wrap">Login</span>
							</a>
						</li>
						<li class="signup">
							<a href="/register" class="btn-primary"  >Try it for Free</a>
						</li>								
					</ul>
					<!-- / Primary Navigation -->

					
					<ul class="login-sign-up nav-slide">
						<li class="login nav-login"> 
							<a href="/login">
								<span class="link-wrap">Login</span>
							</a>
						</li>
						<li class="signup nav-sign-up">
							<a href="/register" class="btn-primary" >
								<span class="link-wrap">Try it for Free</span>
							</a>
						</li>
					</ul>
					
				</nav>
			</div>
		</div>
	</div><!-- / container-fluid -->
</header>	
  
<div class="hero-unit">
	<h1>Login</h1>
</div>
                     
<div class="feature alt-bg">
	<div class="row-fluid">
		
		<div class="form-wrapper">
				<form name="loginForm" action="/securelogin" method="post" class="form-horizontal login-form">	
				<div class="control-group row-fluid">
					<label class="control-label">Email <span class="required">*</span></label>
					<div class="controls">
						<input  class="input-xlarge" id="username" name="username" placeholder="Enter your email..."  title="Enter your Email" type="text" value="" placeholder="Enter your email...">
					</div>
				</div>
				<div class="control-group"> 
					<label class="control-label">Password <span class="required">*</span></label>
					<div class="controls">
						<input  class="input-xlarge" id="j_password" name="j_password" placeholder="Enter your password..."  title="Enter your Password" type="password" value="" placeholder="Enter your password...">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="checkbox" value="remember-me" id="remember_me"> Remember me</label>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"></label>
					<input class="btn" type="submit" value="Login" style="min-width:270px;"/>
					<label for="forgot_link_label"><a href="#" id="forgot_link" alt="Forgot your password?">Forgot your password?</a>
				</div>
				</form>
				<div id="forgot_container"  style="display:none;">
					<form id="forgotForm" class="form-horizontal">	
					<div class="control-group" >
						<label class="control-label">Email</label>
						<div class="controls">
						<input class="input-xlarge" type="text" id="forgot_input" name="forgot_input" placeholder="Enter your email..."  title="Enter your Email" />
						</div>
					</div>				
					<div class="control-group" >
						<label class="control-label"></label>
						<span>
							<input class="btn" type="submit" id="forgot_submit" value='Reset my password' />
						</span>
					</div>	
					</form>			
				</div>

		</div>
		
	</div>	

	</div>

<!-- 
  
<footer>
	<div class="container-fluid">
		<div class="copyright">
			<p>&copy; Copyright SMSGA Inc. 2014. All rights reserved.
			<span> 
			<a href="terms">Terms and Conditions</a>
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

-->


</div>

<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>	
<script type="text/javascript" src="js/smsga/jquery.min.js"></script>
<script src="js/smsga/jquery.pageslide.min.js"></script>
<script src="js/smsga/jquery.flexslider-min.js?v=184"></script>
<script src="js/smsga/scripts.js"></script>
<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/login.js?v=184"></script>

<script type="text/javascript">
var isLoginError = '${login_error}';
</script>

</body>
</html>

