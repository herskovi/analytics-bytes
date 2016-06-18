<!DOCTYPE html>
<html>
<head>
<title>Analytics Bytes - Text Messages that connects you
	directly to your analytics</title>
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



<link rel="stylesheet" href="../style/smsga/animation.css">
<link rel="stylesheet" href="../style/smsga/fonts/social.css">
<link rel="stylesheet" href="../style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="../style/smsga/fonts/standard.css">
<link rel="stylesheet" href="../style/smsga/flexslider.css" />
<link rel="stylesheet" href="../style/smsga/jquery.pageslide.css">
<link rel="stylesheet" href="../style/smsga/jquery-ui.css">
<link rel="stylesheet"
	href="../js/smsga/telephoneWithCountry/build/css/intlTelInput.css">
<script type="text/javascript" src="/js/smsga/jquery.min.js"></script>
<script type="text/javascript"
	src="/analyticsbytes/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/smsga/jquery.min.js?v=182"></script>
<!-- Adding Grid Capabiliites - Start -->
<link rel="stylesheet" type="text/css" media="all"
	href="/style/smsga/grid.jquery.columns.clean.css?v=184" />
<script type="text/javascript"
	src="/js/smsga/jquery.columns-1.0.min.js?v=184"></script>
<!-- Adding Grid Capabiliites - End -->

<!--  Adding datetime capabilities -->

<script type="text/javascript"
	src="/analyticsbytes/bower_components/moment/min/moment.min.js"></script>
<script type="text/javascript"
	src="/analyticsbytes/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="/analyticsbytes/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />


</head>
<body id="home">
	<!-- Google Tag Manager -->
	<noscript>
		<iframe src="//www.googletagmanager.com/ns.html?id=GTM-5SJWBM"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<script type="text/javascript">
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
								it now, It's Free</a> <a href="/productrecommendation" type="button"
								class="btn btn-default topbtn">Product Recommendation</a>
						</div>
					</div>
				</div>
			</nav>
		</header>
		
		<div class="rapper graydiv toppadding">
			<!-- added by oran -main gray div -->
			<div class="container">



				<div class="container whitediv toppadding">


					<div class="row">
						<div class="form-wrapper">
							<form name="trainthemodelForm" class="form-horizontal login-form">
								<input id="uniqueAccountNumber" name="uniqueAccountNumber" type="hidden" value='${uniqueAccountNumber}' /> 
								<br> <br> <br> <br> <br> <br>
								<div class="form-group">
									<div class="col-lg-12">
										<p style="font-style: italic; font-size: 1.5em;">Please
											enter your Google Analytics account email which you enrolled
											with</p>
									</div>
								</div>
								<div class="form-group">
									<label for="mobile" class="col-sm-4 control-label">Email<span class="required">*</span></label>
									<div class="col-sm-4">
										<input id="email_address" name="email_address"
											placeholder="Example: myname@mywebsite.com"
											class="form-control input-lg" type="text" value="" tabindex=1 />
										
									</div>
								</div>
								
								<div class="form-group">
										<label for="mobile" class="col-sm-4 control-label">Model
											Type<span class="required">*</span>
										</label>
										<div class="col-sm-4">
											<select	class="form-control" id="modelType" name="modelType">
												<option>Google</option>
												<option>Logistic Regression</option>
												<option>Amazon ML</option>
												<option>TBD</option>
											</select>
										</div>
									</div>

									
								<div class="form-group">
									<label for="empty" class="col-sm-4 control-label"></label>
									<div class="col-sm-4">
										<button type="submit" id="trainmodelSubmitButton" class="btn btn-success btn-lg btn-block" role="button">
											Train The Model</button>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-12">
										<p style="font-style: italic; font-size: 3.5em;">Confusion Matrix</p>
									</div>
							
									
									<div class="col-lg-12">
										<div id = "confusionMatrix" style ="background-color:#eee;">
										<p>The entries in the confusion matrix have the following meaning in the context of our study:</p>
												<ul>
													<li><i>a</i> is the number of <b>correct</b> predictions that an instance is <b>FAILURE</b>,</li>
													<li><i>b</i> is the number of <b>incorrect</b> predictions that an instance is <b>SUCCESS</b>,</li>
													<li><i>c</i> is the number of <b>incorrect</b> of predictions that an instance <b>FAILURE</b>, and</li>
													<li><i>d</i> is the number of <b>correct</b> predictions that an instance is <b>SUCCESS</b>.</li>
												</ul>
												<div id="numberOfInstances" class="col-lg-12">
													Number Of Instances: 
														<p id="numberOfInstancesValue" style="text-align: center; font-size: 48px;"><br />0000</p>
													
												</div>
												
													<table id="confusionMatrixTable">
														<tr>
															<td colspan="2" rowspan="2"></td>
															<td colspan="2">Predicted</td>			
														</tr>
														<tr>
															<td>FAILURE</td>
															<td>SUCCESS</td>
														</tr>
														<tr>
															<td rowspan="2">Actual</td>
															<td>FAILURE</td>
															<td id="failureVsFailure">a</td>
															<td id="failureVsSuccess"><b>b</b></td>
														</tr>
														<tr>
															<td>SUCCESS</td>
															<td id="successVsFailure"><b>c</b></td>
															<td id="successVsSuccess"><b>d</b></td>
														</tr>
													</table>
													<p>Several standard terms have been defined for the 2 class matrix:</p>
													<ul>
														<li>The <i>accuracy</i> (<i>AC</i>) is the proportion of the total number of predictions that were correct. It is determined using the equation: (A+D)/(A+B+C+D)</li>
													</ul>
													<p id="accuracyRate" style="text-align: center;"><br />(A+D)/(A+B+C+D)</p>
													<ul>
														<li>The <i>recall</i> or <i>true SUCCESS rat</i>e (<i>TP</i>) is the proportion of SUCCESS cases that were correctly identified, as calculated using the equation: (D)/(C+D)</li>
													</ul>
														<p id="trueSuccessRate" style="text-align: center;"><br />(D)/(C+D)</p>
													<ul>
														<li>The <i>false SUCCESS rate</i> (<i>FP</i>) is the proportion of FAILURES cases that were incorrectly classified as positive, as calculated usingthe equation: (B/A+B)</li>
													</ul>
														<p id="falseSuccessRate" style="text-align: center;"><br />(B/A+B)</p>
													<ul>
														<li>The <i>true FAILURE rate</i> (<i>TF</i>) is defined as the proportion of negatives cases that were classified correctly, as calculated using the equation: (A/A+B)</li>
													</ul>
														<p id="trueFailureRate" style="text-align: center;"><br />A/A+B</p>
													<ul>
														<li>The<i> false FAILURE rate</i> (<i>FN</i>) is the proportion of positives cases that were incorrectly classified as negative, as calculated using the equation: (C/C+D)</li>
													</ul>
													<p id="falseFailureRate" style="text-align: center;"><br />(C/C+D)</p>  
										</div>
									</div>
								</div>
								<div class="form-group">
									<div id="inProgress" class="col-lg-12" style="display: none;">
										<img src="/images-new/smsga/spinner-icon.gif" alt="Wait"/>
									</div>
								</div>
							</form>
						</div>
					
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="whitediv navbar">
			<!-- added by oran - footer div -->
			<hr>
			<p>
				<span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was
				created by Analytics geeks, for Analytics geeks. And it will keep
				getting better.
			</p>

		</div>


	</div>
	<div></div>

	<input type="hidden" id="_webapp_path" value="">



	<!-- GOOGLE ANALYTICS -->








	<script type="text/javascript"
		src="/js/smsga/jquery-migrate-1.0.0.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.flexslider-min.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.pageslide.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
	<script type="text/javascript"
		src="/js/smsga/jquery.validate.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/menu.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/script.js?v=184"></script>
	<script type="text/javascript" src="/analyticsbytes/js/trainmodel.js"></script>  

	<script type="text/javascript">
		var userName = "${username}"
		var email = "${userid}";
		var useridStr = "email_address=" + email;
	</script>



</body>
</html>

