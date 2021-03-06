<!doctype html>

<html class="page-login">
<head>
<title>Analytics Bytes - SMS that connects you directly to your
	analytics</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="SMSGA is a simple way to get SMS  with your analytics data before your first coffee ">
<meta name="keywords" content="SMSGA Google Analytic Data ">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">


<!-- Google Webfonts -->

<link rel="stylesheet" href="/style/smsga/fonts/social.css">
<link rel="stylesheet" href="/style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="/style/smsga/fonts/standard.css">



<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/bootstrap-responsive.css?v=184" />
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/bootstrap.min.css?v=184" />
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/theme.css?v=184" />
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/flexslider.css?v=184" />
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/jquery-ui.css?v=184" />
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/support-form.css?v=184" />


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
	<script>
		(function(w, d, s, l, i) {
			w[l] = w[l] || [];
			w[l].push({
				'gtm.start' : new Date().getTime(),
				event : 'gtm.js'
			});
			var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l='
					+ l
					: '';
			j.async = true;
			j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl;
			f.parentNode.insertBefore(j, f);
		})(window, document, 'script', 'dataLayer', 'GTM-5SJWBM');
	</script>
	<!-- End Google Tag Manager -->

	<div id="wrap">
		<header>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span2">
						<div id="logo">
							<a href="/smsga.html" title="Go to Home page"></a>
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
								<li class="nav-tour"><a href="/smsga.html"> <span
										class="link-wrap">Home</span>
								</a></li>
								<li class="nav-pricing"><a href="/pricing"> <span
										class="link-wrap">Pricing</span>
								</a></li>

								<li class="nav-support"><a href="/support"> <span
										class="link-wrap">Support</span>
								</a></li>


								<li class="login"><a href="/login"> <span
										class="link-wrap">Login</span>
								</a></li>
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




		<div class="feature alt-bg">
			<div class="hvp-container">
				<div class="container-fluid">
					<div class="row-fluid centered">
						<div
							class="get-started dashboard-body clearfix centered container-fluid"
							id="welcome_message" style="display: block;">
							<input type="hidden" id="_activation" value="true">
							<h2 class="title">
								<p>
									Your free account has just been activated! </br> You will start
									getting sms with fresh </br> Google analytics data every day. </br>
								</p>

							</h2>
						</div>



					</div>
				</div>

			</div>
		</div>
		<!-- / hvp-containe -->
	</div>

	<footer>
		<div class="container-fluid">
			<div class="copyright">
				<p>
					&copy; Copyright SMSGA Inc. 2014. All rights reserved. <span>
						<a href="terms">Terms and Condition</a>
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

	<!--  
	
<script type="text/javascript">

  var isLoginError= "${login_error}"; // 1 is true;
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-20475086-1']);
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
	<script src="js/smsga/jquery.pageslide.min.js"></script>
	<script src="js/smsga/jquery.flexslider-min.js"></script>
	<script type="text/javascript"
		src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.validate.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>

	<script type="text/javascript" src="js/smsga/jquery.min.js"></script>

</body>
</html>

