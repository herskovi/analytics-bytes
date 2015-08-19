
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>AnalyticsBytes - SMS that connects you directly to your analytics</title>

    <!-- Bootstrap core CSS -->
    <link href="/analyticsbytes/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" media="all" href="/style/smsga/firstTimeLogin.css?v=184" />
    <link rel="stylesheet" type="text/css" media="all" href="/analyticsbytes/css/jumbotron.css">
	<link rel="stylesheet" type="text/css" media="all" href="/analyticsbytes/css/analyticsbytes.css"> 
	<script src="/analyticsbytes/js/assets/ie-emulation-modes-warning.js"></script>

  </head>

  <body id="getsms">
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
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/home"><strong>AnalyticsBytes</strong></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/whatwedo">What we do?</a></li>
                    <li><a href="/support">Support</a></li>  
                </ul>
        



            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>

      <div class="rapper graydiv toppadding"> <!-- oran added - main gray div -->
            <div class="container">
            	<!--  Adding 1, 2, 3 images -->
		<div class="row toppadding">
		
        	<div class="col-md-4">
            	 <img src="/analyticsbytes/images/step1.png" class="img-circle" alt="Step 1">
            	 	<br>
            	 	<div class="col-lg-12">
						<blockquote>
							<p>Your mobile number where you wish to recieve your FREE text messages</p>
						</blockquote>
					</div>
        	</div>
        	<div class="col-md-4 rec-div">
            	  <img src="/analyticsbytes/images/step2.png" class="img-circle" alt="Step 2">
            	  <br>  
            	  <div class="col-lg-12">
            	  		<blockquote>
							<p>Connect your Google Analytics Account and select your Website&#47App Analytics</p>
						</blockquote>
				 </div>   
        	</div>
        	<div class="col-md-4 rec-div">
            	  <img src="/analyticsbytes/images/step3.png" class="img-circle" alt="Step 3">
            	  <br>
            	  <div class="col-lg-12">
	            	   <blockquote>
							<p><b>Start recieving your Google Analytics results: Sessions, Users, Conversions!</b></p>
						</blockquote>    
				 </div>
        	</div>
    	</div>

                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h1><span class="hero-title">Thats it! You're a click away from fresh Analytics Bytes..</span></h1>  
                        <p class="lead">Click "Get SMS" to start receiving you analytics SMS..  </p>
                        <button id="getSmsNow" type="button" class="btn btn-success btn-lg btn-block ">Get SMS</button>
                        <p>P.S - Please check your email inbox to validate your account</p>
                    </div> 
                </div>

                <div class="row" id="sms-recieved-container" name="sms-recieved-container" style="display: none;">
                    <div class="col-md-8 col-md-offset-2">
                        <p>Did you receive SMS?</p>
                        <button id="btnConfirmationYes" type="button" class="btn btn-success btn-lg btnsmsval selectorbtn">Yes</button>
                        <button id="btnConfirmationNo"  type="button" class="btn btn-danger btn-lg btnsmsval selectorbtn">No</button>
                    </div> 
                </div>
                
                <div class="row" id="smsvalsuccess" style="display: none;">
                    <div class="col-md-8 col-md-offset-2">
                            <div class="alert alert-success btnmargin" role="alert"><strong>Great,</strong> We hope you'll enjoy it! Reach to us anytime at  <b>Support@analyticsbytes.com</b></div>
                    </div> 
                </div>
                <div class="row" id="smsvalfail" style="display: none;">
                    <div class="col-md-8 col-md-offset-2">
                            <div class="alert alert-danger btnmargin" role="alert"><strong>Oops,</strong> There was a problam sending your SMS.. We will conatct you shortly to fix that!</div>
                    </div> 
                </div> 
            </div>
     </div> <!-- oran added - main gray div close -->
      

 <!-- /container -->
      
      
      
      
      <div class="whitediv navbar navbar-fixed-bottom">  <!-- oran added - footer div -->
        <hr> 
        <p><span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was created by Analytics geeks, for Analytics geeks. And it will keep getting better.</p>
       
      </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="/js/smsga/jquery.min.js?v=184"></script>
    <script type="text/javascript" src="/analyticsbytes/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/analyticsbytes/js/getsms.js?v=184"></script>
    <script type="text/javascript" src="/analyticsbytes/js/assets/ie10-viewport-bug-workaround.js"></script>
    
    <script type="text/javascript">
		var userId = "${userid}";
		var useridStr = "userid=" + userId;
	</script>
    
  </body>
</html>
