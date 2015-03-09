<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport"
	content="width=1500, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>SMS For Google Analyics</title>
<meta name="description" content="SMS For Google Analyics" />

<script type="text/javascript" src="js/include/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/accountSelection.js"></script> 
\
 


		<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.core.min.js"></script> 
		<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.widget.min.js"></script>
		<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.tabs.min.js"></script>
		<script type="text/javascript" src="js/include/ui-1.10.0/jquery.ui.position.min.js"></script>

	<script type="text/javascript" src="js/jquery.ui.timepicker.js?v=0.3.3"></script>
<!--  <script type="text/javascript" src="JSONLoop.js"></script>  -->

		<link href="style/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" media="all" href="./style/plans.css">
		<link rel="stylesheet" type="text/css" media="all" href="./style/accountSelection.css">
		<link rel="stylesheet" href="style/include/ui-1.10.0/ui-lightness/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		<link rel="stylesheet" href="./style/jquery.ui.timepicker.css?v=0.3.3" type="text/css" />
		<link rel="stylesheet" type="text/css" media="all" href="./style/accountSelection.css">
		<link rel="stylesheet" type="text/css" media="all" href="./style/plans.css">
</head>
<body>
	<div id="ajaxError"></div>

	<div id="container">



		<div id="wrapper">



			<h2>Register Now and Get Your Analytics Data by SMS before your
				first coffee!</h2>
			<form id="planSelection" name="planSelection"
				action="/accountselectionservlet" method="post">

				<div id="accountSelection" class="row"> 
					<h2>Account List</h2>
					


					<c:set var="count" value="0" scope="page" />  
					<c:set var="checked" value=" checked" scope="page" />
					<c:set var="accounts" value="${accountNameList}" scope="page" /> 
					<c:set var="userIdVar" value="${userId}" scope="page" />
					<c:set var="accountsJson" value="${accountsJson}" scope="page" /> 
					
					
					
					<input type="hidden" id="accountListJsonHidden" name="accountListJsonHidden" value="accountListJson : ${accountsJson}" />
					<input type="hidden" id="userId" name="userId" value="userId : ${userId}"/>
					

					<select id="accountDropdown" name = "accountDropdown" onchange="displayWebPropertyPerAccount()">
						<c:set var="count" value="0" scope="page" />
						  <option value="0">  
									<c:out value="--- Please Select ---" />  
					       </option>
						<c:forEach var="accountNameList" items="${accountNameList}">
							<c:set var="count" value="${count + 1}" scope="page"/>
						   <option value='${count}'>  
									<c:out value="${accountNameList.accountName}" />  
					       	</option>
						</c:forEach>
					</select>	
					<select id="webPropertyDD" name = "webPropertyDD" onchange="displayProfileForAccountAndDeafaultWebProperty()">
					</select>    
					<select id="profileDD"></select>       	 
						    	 
					       
					

<!-- 

						<div id='accountSelection${count}'>
							<tr>
								<td><input type='radio' name='accountSelectionRadio'
									${checked} value='${count}'></td>
								<span class='largeFont' id='accountSelectionRadioSpan'${count}'>
								</span>
							</tr>
						</div> 
				-->
					
				

					<div id="metricsDiv">
						<h2>Which Data do you want to recieve in Your SMS?</h2>
						<input type="checkbox" disable name="metrics" value="ga:users" checked>Number of Unique Visitors in the last 24 hours <br> 
						<input type="checkbox" disable name="metrics" value="ga:sessions" checked>Total users and pageviews for the specified time period<br>
						<input type="checkbox" name="metrics" value="ga:goalValueAll" checked>Goal Completion<br>
					</div>

					<!--		
					<div id="submitButton">

						<input type="submit" value="Sign Up!" name="submit" id="submit"
							class="sub-btn" tabindex="10"> </input>
					</div>
  -->
				</div>



				<div id="timepickerDIV">
					<h2>Which Time You want to recieve your SMS</h2>
					<input type="text" style="width: 170px"
						name="timepicker_customminutes" id="timepicker_customminutes"
						value="08:00" />

					<script type="text/javascript">
						$(document).ready(function() {
							$('#timepicker_customminutes').timepicker({
								minutes : {
									interval : 30,
									manual : [ 00, 30 ]
								},
								showPeriodLabels : false,
								beforeShow : function() {
									$(".ui-datepicker").css('font-size', 18)
								}
							})
						});
					</script>
					
					<select id="timeZoneDropdown" name = "timeZoneDropdown" >
						<c:forEach var="availableTimeZone" items="${availableTimeZoneIDs}">
						   <option value='${availableTimeZone}'>  
									<c:out value="${availableTimeZone}" />  
					       	</option>
						</c:forEach>
					</select>	

				</div>
				
				<div id="choosePlanDiv">

					<div class="center">
						<div class="wbg">
							<h2>
								<strong>Choose</strong> the right Plan for your business
							</h2>
							<input type="hidden" id="chosenPlanHidden"
								name="chosenPlanHidden" />


							<div id="pricing-table" class="clear">
								<div class="plan">
									<h3>
										Bronze<span>5$ Per Month</span>
									</h3>

									<a class="signup" id="plan" onclick="chosenPlan('5$');">Pay
										Now</a>

									<ul>
										<li><b>1 SMS</b> per day</li>
										<li><b>Only 1 </b> telephone no.</li>
										<li><b>Sent </b> By Email</li>
										<li><b>Unlimited</b> subdomains</li>
									</ul>
								</div>
								<div class="plan">
									<h3>
										Silver<span>$30 Per Month</span>
									</h3>
									<input type="submit" value="Pay Now" name="chosenPlan"
										id="chosenPlan" class="signup" tabindex="10"></input>

									<ul>
										<li><b>2 SMS</b> per day</li>
										<li><b>Up to 10</b> diffrent tel no.</li>
										<li><b>20</b> Email Accounts</li>
										<li><b>Unlimited</b> subdomains</li>
									</ul>
								</div>
								<div class="plan">
									<h3>
										Gold<span>$99 per Month</span>
									</h3>
									<a class="signup" href="/register3.html?plan=Gold">Pay Now</a>
									<ul>
										<li><b>10 SMS</b> per day</li>
										<li><b>Up to 100</b> diffrent tel no.</li>
										<li><b>200</b> Email Accounts</li>
										<li><b>Unlimited</b> subdomains</li>
									</ul>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- whitebg -->




			</form>
		</div>


	</div>
	<div id="footer" class="footer"> 
		<div class="center">(C) 2014, All Rights Reserved to SMSGA LTD</div>
	</div>

	</div>
	</div>
</body>
</html>
<script> 
    var accountsJson= '${accountsJson}' ; 
    var userIdVar = '{userIdVar}';
</script>

