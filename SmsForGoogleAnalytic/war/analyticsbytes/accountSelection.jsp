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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="shortcut icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">


<link href="/analyticsbytes/css/jumbotron.css" rel="stylesheet">
<link href="/analyticsbytes/css/bootstrap.min.css" rel="stylesheet">
<link href="/analyticsbytes/bootstrap/dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">


<link rel="stylesheet" href="/analyticsbytes/css/analyticsbytes.css">

<!-- Google Webfonts -->
<link
	href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic'
	rel='stylesheet' type='text/css'>
<!-- 
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap-responsive.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/bootstrap.min.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/themeAccountSelection.css?v=184" />

 -->

<link rel="stylesheet" href="/style/smsga/fonts/social.css">
<link rel="stylesheet" href="/style/smsga/fonts/symbolicons.css">
<link rel="stylesheet" href="/style/smsga/fonts/standard.css">
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/flexslider.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery.pageslide.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/jquery-ui.css?v=184" />
<link rel="stylesheet" type="text/css" media="all" href="../style/plans.css">
<link rel="stylesheet" type="text/css" media="all" href="/analyticsbytes/css/accountSelectionForAnalyticsBytes.css">
<link rel="stylesheet" href="../style/include/ui-1.10.0/ui-lightness/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<link rel="stylesheet" href="../style/jquery.ui.timepicker.css?v=0.3.3" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="../style/plans.css">







<!--[if lt IE 9]>
<script src="/resources/js/kit/selectivizr-min.js"></script>  
<![endif]-->
</head>
<body id="accountSelection">

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


	<nav class="navbar navbar-default navtop">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
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

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>




	<div class="rapper graydiv toppadding">
		<!-- added by oran -main gray div -->
		<div class="container">

			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h3>Now, Select the analytics you wish to recieve</h3>
					<br />
					<form id="planSelection" class="form-horizontal"
						name="planSelection" action="/accountselectionservlet"
						method="post">


						<c:set var="count" value="0" scope="page" />
						<c:set var="checked" value=" checked" scope="page" />
						<c:set var="accounts" value="${accountNameList}" scope="page" />
						<c:set var="userId" value="${userId}" scope="page" />
						<c:set var="accountsJson" value="${accountsJson}" scope="page" />


						<div class="form-group">
								<label class="col-sm-4 control-label">Account <span class="required">*</span></label>
								<div class="col-sm-8">
									<input type="hidden" id="accountListJsonHidden"	name="accountListJsonHidden"
										value="accountListJson : '${accountsJson}'" /> <input
										type="hidden" id="userId" name="userId" value='${userId}' />
									<input type="hidden" id="userName" name="userName"
										value='${userName}' /> <input type="hidden"
										id="accountIdHiddenValue" name="accountIdHiddenValue" value='' />
									<input type="hidden" id="accountNameHiddenValue"
										name="accountNameHiddenValue" value='' /> <input
										type="hidden" id="webPropertyIdHiddenValue"
										name="webPropertyIdHiddenValue" value='' /> <input
										type="hidden" id="webPropertyNameHiddenValue"
										name="webPropertyNameHiddenValue" value='' /> <input
										type="hidden" id="profileIdHiddenValue"
										name="profileIdHiddenValue" value='' /> <input type="hidden"
										id="profileNameHiddenValue" name="profileNameHiddenValue"
										value='' /> <input type="hidden" id="goalIdHiddenValue"
										name="goalIdHiddenValue" value='' /> <input type="hidden"
										id="goalNameHiddenValue" name="goalNameHiddenValue" value='' />

									<select id="accountDropdown" name="accountDropdown"
										onchange="displayWebPropertyPerAccount()"
										class="form-control">

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
						<div class="form-group">
								<label class="control-label col-sm-4">Property<span class="required">*</span></label>
								<div class="col-sm-8">
									<select id="webPropertyDD" name="webPropertyDD"
										onchange="displayProfilePerAccountAndWebProperty()"
										class="form-control">
										<option value='0'>
											<c:out value="--- Please Select ---" />
										</option>
									</select>
									<div id="spinnerForWebProperty" style="display: none;">
										<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />
									</div>
								</div>

							</div>
						
						<div class="form-group">
							<label class="control-label col-sm-4">View<span class="required">*</span></label>
							<div class="col-sm-8">
								<select id="profileDD" name="profileDD" class="form-control"
									onchange="profileWasChosen()">
									<option value='0'><c:out value="--- Please Select ---" /></option>

								</select>
								<div id="spinnerForProfile" style="display: none;">
									<img src="/images-new/smsga/spinner-icon.gif" alt="Wait" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4">Goal Name</label>
							<div class="col-sm-8">
								<select id="goalNameDD" name="goalNameDD" class="form-control"
									onchange="goalWasChosen()">
									<option value='0'><c:out value="--- Please Select ---" /></option>
								</select>
							</div>
						</div>


						<div class="form-group">

								<label class="control-label col-sm-4">Time to Recieve SMS</label>
								<div class="col-sm-8">
									<div id="timepickerDIV">
										 <select id="timeZoneDropdown" name="timeZoneDropdown" class="form-control">
											<c:forEach var="availableTimeZone"
												items="${availableTimeZoneIDs}">
												<option value='${availableTimeZone}'>
													<c:out value="${availableTimeZone}" />
												</option>
											</c:forEach>
										</select>
									</div>
									</br>
									<input type="time" class="form-control" id="time" placeholder="time" value="08:00">
									</br>
									<!-- 
									 <div class="input-append bootstrap-timepicker">
            							<input id="timepicker_customminutes" type="text" class="input-small">
            								<span class="add-on"><i class="icon-time"></i></span>
        							</div>
        							 -->
 								<!-- 	<input type="text" class="form-control" name="timepicker_customminutes" id="timepicker_customminutes" value="08:00" /> -->
									

								</div>
							</div>


						<div id="metricsDiv" style="display: none;">
							<div class="control-group">
								<label class="control-label">Metric 1</label>
								<div class="controls">
									<input type="checkbox" name="metrics" value="ga:users" checked>
									<span class="required"> Number of Unique Visitors in the
										last 24 hours </span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Metric 2</label>
								<div class="controls">
									<input type="checkbox" name="metrics" value="ga:sessions"
										checked> <span class="required"> Total users
										and pageviews for the specified time period </span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-4"></label>
							<div class="col-sm-8">
								<input class="btn btn-primary btn-lg btn-block"  id="accountSelectionSubmitBtn"
									name="accountSelectionSubmitBtn" type="submit" value="Finish" />
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid" id="displayOnMobileDiv" name="displayOnMobileDiv">
		<div class="row-fluid" style="display: none;">
			<div class="span6 offset4">
				<div id="displayOnMobile" class="feature centered clearfix cloud"
					style="display: none;">
					<div class="container-fluid">
						<span class="AppNamePreviewTitle firstType">Analytics Bytes</span>
						<span class="letsStartBuilding" id="typedItOnMobileDevice"></span>
						<div id="MobileHiddenValue" style="display: none;">
							<span class="letsStartBuilding" id="hiUserName">Hi
								${userName},<br />
							</span> <span class="displayOnMobileDevice" id="onWord"></br> On </span> <span
								class="displayOnMobileDevice" id="DateOnMobileDisplay">${yesterdayDate}</span>
							<span class="displayOnMobileDevice"
								id="numberOfUsersOnMobileDisplay">_____</span> <span
								class="displayOnMobileDevice" id="userVisitedYourSite">
								users visited your </span> <span class="displayOnMobileDevice"
								id="typeOfApplicationOnMobileDisplay"> website </span> <span
								class="displayOnMobileDevice" id="inWord"> in </span> <span
								class="displayOnMobileDevice"
								id="numberOfSessionsOnMobileDisplay">_____</span> <span
								class="displayOnMobileDevice" id="sessions"> sessions. </br></span> <span
								class="displayOnMobileDevice"
								id="goalsCompletionOnMobileDisplay"> _____ (%) </span> <span
								class="displayOnMobileDevice" id="sessionsReachedYourGoal">
								sessions reached your goal of </span> <span
								class="displayOnMobileDevice" id="goalNameOnMobileDisplay">______
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<footer>
	  <div class="whitediv"> <!-- added by oran - footer -->
      <hr> 
        <p><span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was created by Analytics geeks, for Analytics geeks. And it will keep getting better.</p>
       
      </div>
	</footer>
	</div>

	<input type="hidden" id="_webapp_path" value="">






	<script type="text/javascript" src="../js/smsga/jquery.min.js"></script>
	<script type="text/javascript" src="../js/smsga/typed.js"
		charset="utf-8"></script>
	<script type="text/javascript" src="../js/jquery.number.min.js"
		charset="utf-8"></script>
	<script type="text/javascript"
		src="../js/include/ui-1.10.0/jquery.ui.core.min.js"></script>
	<script type="text/javascript"
		src="../js/include/ui-1.10.0/jquery.ui.widget.min.js"></script>
	<script type="text/javascript"
		src="../js/include/ui-1.10.0/jquery.ui.tabs.min.js"></script>
	<script type="text/javascript"
		src="../js/include/ui-1.10.0/jquery.ui.position.min.js"></script>
	<!--  <script type="text/javascript"
		src="../js/jquery.ui.timepicker.js?v=0.3.3"></script> -->
	<script type="text/javascript" src="../js/jstz.min.js"></script>
	<script src="../js/smsga/jquery.pageslide.min.js"></script>
	<script src="../js/smsga/jquery.flexslider-min.js"></script>
	<script src="../js/smsga/scripts.js"></script>
	<script type="text/javascript" src="/js/smsga/modernizr.custom.10128.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/bootstrap.min.js?v=184"></script>
	<script type="text/javascript" src="/analyticsbytes/bootstrap/dist/js/bootstrap-datetimepicker.min.js"></script>
	
	
	<script type="text/javascript" src="/js/smsga/jquery.validate.min.js?v=184"></script>
	<script type="text/javascript" src="/js/smsga/jquery.metadata.js?v=184"></script>
	<script type="text/javascript" src="/analyticsbytes/js/accountSelectionForAnalyticsBytes.js" charset="utf-8"></script>
	


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