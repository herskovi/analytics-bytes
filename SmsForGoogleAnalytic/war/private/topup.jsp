
<!doctype html>
<html>
<title>
	SMSGA - Dashboard
</title>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="SMSGA is a simple way to get SMS with your analytic data before your first coffee.">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">


<!-- Google Webfonts -->
<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/style/smsga/fonts/social.css">
<link rel="stylesheet" href="/style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="/style/smsga/fonts/standard.css">



<link rel="stylesheet" href="/style/smsga/bootstrap-responsive.css">
<link rel="stylesheet" href="/style/smsga/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/dashboard.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/flexslider.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery-ui.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.jqplot.css?v=184" />

<script type="text/javascript" src="/js/smsga/jquery.min.js?v=184"></script>

<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>
<script language="javascript" type="text/javascript" src="/resources/js/kit/jqplot/excanvas.min.js"></script>
<![endif]-->
</head>
<body id="dashboard">


<!-- Google Tag Manager -->
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-5SJWBM"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-5SJWBM');</script>
<!-- End Google Tag Manager -->

  <div class="page-header gradient">
  		

<div class="container-fluid " id="header-wrapper">
<!--	<a href="" class="page-logo"><img src="/resources/img/nexmo_logo.png"></a> -->
	
		<div class="welcome hide-mobile"><span>Hi, ${username} </span></div>
			
		<div class="header-right clearfix hide-mobile">
			
	<a href="/private/topup" class="balance" style="color:#ffffff">
		Balance: <strong>${balance} &euro;</strong> <span>+</span>
	</a>


			
			<div class="profile">
				<a href="#">
					<img width="30" height="30" alt=" " src="/style/images/default-avatar.png">
					<span class="toggle ss-navigatedown"></span>
				</a>
				<ul class="delist sub-menu">
					<li>
						<a href="/logout">Logout</a>
					</li>
					<li>
						<a href="/support"  target="_blank">Help</a>
					</li>												
				</ul>
			</div>
		</div>
		
 		<div class="nav_link">
			<a href="#nav" class="open">
				<span class="ss-icon ss-rows">&#xE9A1;</span>
			</a>
		</div>
</div>


  </div>
 

<nav class="dash-nav">
	<div class="container-fluid">
		<ul class="delist clearfix dash-menu" id="nav">
			
			<li id="menu_dashbaord">
				<a href="/private/dashboard">Dashboard <span class="toggle ss-navigatedown"></span></a>
			</li>
			
			<li id="menu_number">
				<a href="/private/numbers">Numbers <span class="toggle ss-navigatedown"></span></a>
			</li>
			
			
			<li id="menu_topup">
				<a href="/private/topup">Top Up <span class="toggle ss-navigatedown"></span></a>
			</li>
			
			<li class="mobile-nav-item profile-nav-push">
				<a href="/private/profile">${username} <span class="mobile-nav-icon pull-right ss-write"></span></a>
			</li>
			
			<li class="mobile-nav-item">
				<a href="/private/topup">Balance: 
				<span class="mobile-balance">
					${balance} &euro;	
				</span> <span class="mobile-nav-icon pull-right ss-plus"></span></a>
			</li>			
			
			<li class="mobile-nav-item">
				<a href="/logout">Logout <span class="mobile-nav-icon pull-right ss-settings"></span></a>
			</li>		
		</ul>
		
	</div>
	
</nav>




 <div class="container-fluid">
	<div class="dashboard-wrap clearfix">
 		
 <div class="dashboard-header clearfix">
  	<h1 class="title">Top Up</h1>
  	
  		<a class="dash-header-link get-started-link" id="getting_started_trigger" href="#" style="display:none">Getting Started</a>
  </div>

	<!-- 
	<form action="https://www.paypal.com/il/cgi-bin/webscr" method="post">
<input type="hidden" name="cmd" value="_xclick">
<input type="hidden" name="business" value="you@youremail.com">
<input type="hidden" name="item_name" value="Item Name">
<input type="hidden" name="currency_code" value="USD">
<input type="hidden" name="amount" value="0.00">
<input type="image" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
</form>

 -->

				<p>Please select the amount you want to add to your account balance:</p>
					 
					 
				   <!--  <form id="topup_form" class="form-horizontal" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">  -->  
					<form id="topup_form" class="form-horizontal" action="/checkout" method="post">   
				  <!--  <form id="topup_form" class="form-horizontal" action="https://www.paypal.com/il/cgi-bin/webscr" method="post">  -->  
							<input type="hidden" name="cmd" value="_xclick">
							<input type="hidden" name="business" value="herskovi77@gmail.com">
							<input type="hidden" name="item_name" value="Analytics Bytes Top Up">
							<input type="hidden" name="currency_code" value="USD">
							<input type="hidden" name="return" value="http://www.analyticsbytes.com/private/orderConfirmation">
							<input type="hidden" name="rm" value="2">
							<input type="hidden" name="cancelUrl" value="http://www.analyticsbytes.com/private/orderWasCancel'">
							<input type="hidden" name="custom" value='${userid}' />
							<input type="hidden" name="SOLUTIONTYPE" value="Sole" />
							
							<!-- 
								<input type="hidden" name="on0" value='userIdPostBack' />
								<input type="hidden" name="os0" value='${userid}' />
							 -->
							<div class="control-group">
								<label class="control-label">Amount</label>
								<div class="controls">
								<select id="amount" name="amount">
							           <option value="">-Select-</option>
							           <option value="0.01">0.01$ - Trial</option>
							           <option value="5.99">5.99$ - 1 Month</option>
							           <option value="9.99">9.99$ - 3 Motnts</option>
							           <option value="29.99">29.99$ - 1 year </option>														           			
								</select>	
								</div>
							</div>
							
										
				           	
							<div class="control-group">
								<div id="topup_submit_container" class="controls">
								<span>
									<input id="topup_submit_image" type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_paynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
								</span>
								</div>
								<div id="topup_wait_container" class="waiting" style="display:none"></div>
							</div>	
				</form>

	

  <div class="get-started dashboard-body clearfix centered container-fluid" id="welcome_message" style="display: none;">
  
  	  <a class="ss-delete get-started-close" id="get_started_close" href="#">
		<span>close</span>
	  </a>
	  
	  <h2 class="title">Welcome to Nexmo, let&rsquo;s get started.</h2>
	  
	  <p>Here you can configure your account, access traffic analytics, top-up and add phone numbers.</p>
		  
	  <div class="row-fluid">
		  <div class="span4">
		  	<article class="subset-widget">
			  <h1 class="title"><span class="ss-cloud"></span> Connect</h1>
			  <p>You&rsquo;ll need to connect to Nexmo&rsquo;s <a href="https://docs.nexmo.com">Restful API</a></p>
			</article>
		  </div>
		  <div class="span4">
		  	<article class="subset-widget">
			  <h1 class="title"><span class="ss-book"></span> Libaries</h1>
			  <p>Check out our existing <a href="https://docs.nexmo.com/index.php/pre-built-libraries">Pre-built libraries</a></p>
			 </article>
		  </div>
		  <div class="span4">
		  	<article class="subset-widget">
			  	<h1 class="title"><span class="ss-creditcard"></span> Top up</h1>
			  <p>Don&rsquo;t forget to top up your <a href="/private/topup">account balance</a> and send live traffic</p>
		  	</article>
		  </div>
		</div>
		
		  <div class="row-fluid">
			  <div class="span">
			  	2&euro; free credits have been added to your account to try out the service. Send test messages up to <a href="/private/settings#test">10 phone numbers</a>.
		  	  </div>
		  </div>
		  <div class="row-fluid">
			  <div class="span">
			  	<article class="subset-widget" style="min-height:0px">
			  	<p>https://rest.nexmo.com/sms/json?api_key=ae4bdcb0&amp;api_secret=adeb90bd&amp;from=NEXMO&amp;to=972524265342&amp;text=Welcome+to+Nexmo</p>
				</article>
		  	  </div>
		  </div>
		 
		<h3 class="sub-title centered">Your API Settings</h3>
		  
		<div class="api-settings">
			<ul class="delist">
				<li>Key: <strong>ae4bdcb0</strong></li>
				<li>Secret: <strong>adeb90bd</strong></li>
			</ul>
		</div>
		<p>You can view and <a href="/private/settings">edit your API settings</a> at anytime.</p>
 </div>






	<div class="dashboard-body clearfix">
			<div id="chartdiv" style="height: 300px; width: 100%; position: relative;"></div>
	</div>
	<!-- 
	<div class="dashboard-footer clearfix" id="last_activity_container">
			
				
				  <ul class="delist snapshot">
					<li class="outbound">
						<div class="inner">
							 <span class="snapshot-title"><span class="ss-icon ">&#x2B05;</span>Outbound</span> <span class="snapshot-stat">13</span>
						 </div>
					</li>
					 <li class="bounced">
						<div class="inner">
							<span class="snapshot-title"><span class="ss-icon ss-delete"></span>Bounced</span> <span class="snapshot-stat">0</span>
						</div>
					</li>
					  <li class="spent">
						<div class="inner">
							<span class="snapshot-title"><span class="ss-icon ss-creditcard"></span>Spent</span> <span class="snapshot-stat">0.111 &euro;</span>
						</div>
					</li>
					</ul>
			
			
			
				
				
	</div>	
	
 -->
 
 	</div>
  </div>
  <footer class="page-footer">  
  

<div id="footer-wrapper">
		<ul>
			<li><a href="/home">Home</a></li>
			<li><a href="/pricing">Pricing</a></li>
			<li><a href="/support">Support</a></li>
		</ul>
		<ul>
			<li class="credit">&copy; Copyright Smsga Inc. 2014. All rights reserved.</li>
			<li><a href="/terms">Terms and Conditions</a></li>
		</ul>
</div>



  </footer>

<script type="text/javascript" src="/js/smsga/jquery-migrate-1.0.0.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.flexslider-min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.pageslide.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>  
<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/menu.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/script.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jqplot/jquery.jqplot.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jqplot/plugins/jqplot.dateAxisRenderer.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jqplot/plugins/jqplot.canvasTextRenderer.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js?v=184"></script>
<script type="text/javascript" src="/js/smsga/jqplot/plugins/jqplot.highlighter.js?v=184"></script>
<!-- <script type="text/javascript" src="/js/smsga/dashboard.js?v=184"></script>  -->
<script type="text/javascript" src="/js/smsga/topup.js?v=184"></script>

<!-- Add Digital goods in-context experience. Ensure that this script is added before the closing of html body tag -->

<script src='https://www.paypalobjects.com/js/external/dg.js' type='text/javascript'></script>


<script>

	var dg = new PAYPAL.apps.DGFlow(
	{
		trigger: 'paypal_submit',
		expType: 'instant'
		 //PayPal will decide the experience type for the buyer based on his/her 'Remember me on your computer' option.
	});

</script>



</body>
</html>
