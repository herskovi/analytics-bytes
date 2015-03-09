
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
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/theme.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/firstTimeLogin.css?v=184" />


<script type="text/javascript" src="/js/smsga/jquery.min.js?v=184"></script>

<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>
<script language="javascript" type="text/javascript" src="/resources/js/kit/jqplot/excanvas.min.js"></script>
<![endif]-->
</head>
<body id="dashboard">
  <div class="page-header gradient">
  		

<div class="container-fluid " id="header-wrapper">
<!--	<a href="" class="page-logo"><img src="/resources/img/nexmo_logo.png"></a> -->
	
		<div class="welcome hide-mobile"><span>Hi, ${username} </span></div>
			
		<div class="header-right clearfix hide-mobile">
			
	<a href="/private/topup" class="balance" style="color:#ffffff">
		Balance: <strong>
		
			
				
			${balance} &euro;
			
			
		</strong> <span>+</span>
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
						<a href="/private/profile">Profile</a>
					</li>
					<li>
						<a href="https://help.nexmo.com"  target="_blank">Help</a>
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
 




 <div class="container-fluid">
	<div class="dashboard-wrap clearfix">
 		
 <div class="dashboard-header clearfix">
  	<h1 class="title">Dashboard</h1>
  	
  		<a class="dash-header-link get-started-link" id="getting_started_trigger" href="#">Getting Started</a>
  </div>

	

	

	<div class="get-started dashboard-body clearfix centered container-fluid" id="welcome_message" style="display: block;">
			<input type="hidden" id="_activation" value="true">
			<h2 class="title">Welcome to Nexmo</h2>
			<p>We need to verify your email address ${email} before you can start using our service. </p>
			<p>Check your inbox to complete your registration</p>
			<p>Get SMS now to verify that all details were entered properly</p>
			<div id="chartdiv" style="height: 20px; width: 100%; position: relative;"></div>
			<p><a id="getSmsNow" class="btn" >Get SMS now</a></p>
			<div id="spinner">
							<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />
			</div>
					
			
			<div id="sms-recieved-container" style="display: none;"> 
					<form id="smsRecievedForm" class="form-horizontal" novalidate="novalidate" action="/smsconfirmation" method="post">
						<h1 class="title">Did you recieve the SMS?</h2>
						<input type="hidden" id="userid" name="userid" value='${userId}'> 
						
					<div class="control-group">
						
						<span>
							<input class="btnConfirmationYes" type="submit" id="smsRecieved" name="smsRecieved" value="Yes">
						</span>
						<td></td>
						<span>
							<input class="btnConfirmationNo" type="submit" id="smsRecieved" name="smsRecieved" value="No" >
						</span>
						<div id="spinnerForConfirmationButton" style="display: none;">
							<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />
						</div>
					</div>	
					</form>			
				</div>
			
	</div>
  
	

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
			<div id="chartdiv" style="height: 150px; width: 100%; position: relative;"></div>
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


<!-- GOOGLE ANALYTICS -->

	
<script type="text/javascript">
	var userId = "${userid}";
	var useridStr = "userid=" + userId;
</script>

<script type="text/javascript">
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

</script>



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
<script type="text/javascript" src="/js/smsga/firstTimeLogin.js?v=184"></script>

</body>
</html>
