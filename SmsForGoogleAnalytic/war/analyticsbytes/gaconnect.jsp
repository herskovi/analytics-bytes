<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>AnalyticsBytes - SMS that connects you directly to your analytics</title>

    <!-- Bootstrap core CSS -->

    <!-- Custom styles for this template -->
    <link href="css/jumbotron.css" rel="stylesheet"> <!-- added by oran -->
    <link href="css/bootstrap.min.css" rel="stylesheet">    <!-- added by oran -->
    <link rel="stylesheet" href="css/analyticsbytes.css"> <!-- added by oran -->

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/assets/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body id="gaconnect">

	<!-- Google Tag Manager -->
	<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-5SJWBM"
	height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
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

      <div class="rapper graydiv toppadding"> <!-- added by oran -main gray div -->
            <div class="container">

                <div class="row">
                       <div class="col-sm-6 col-sm-offset-3">
                           <h3>Now, Select the analytics you wish to recieve</h3><br />
                        
                                <form class="form-horizontal">
                                	<c:set var="count" value="0" scope="page" />
									<c:set var="checked" value=" checked" scope="page" />
									<c:set var="accounts" value="${accountNameList}" scope="page" />
									<c:set var="userId" value="${userId}" scope="page" />
									<c:set var="accountsJson" value="${accountsJson}" scope="page" />
							
                                          <div class="form-group">
                                            <label for="account" class="col-sm-4 control-label">Account </label>
                                            <div class="col-sm-8">
                                              <select class="form-control" id="account" placeholder="Select your accout">
                                                    <option>Select your accout</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                  
                                              </select>
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label for="property" class="col-sm-4 control-label">Property </label>
                                            <div class="col-sm-8">
                                              <select class="form-control" id="property" placeholder="Your property">
                                                    <option>Your property</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                  
                                              </select>
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label for="view" class="col-sm-4 control-label">View </label>
                                            <div class="col-sm-8">
                                              <select class="form-control" id="View" placeholder="View">
                                                    <option>View</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                              </select>
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label for="goal" class="col-sm-4 control-label">Goal name </label>
                                            <div class="col-sm-8">
                                             <select class="form-control" id="goal" placeholder="Goal name">
                                                    <option>Goal name</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                              </select>                                                
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label for="time" class="col-sm-4 control-label">Time to receive SMS </label>
                                            <div class="col-sm-8">
                                             <select class="form-control" id="timezone" placeholder="Select time zone">
                                                    <option>Select time zone</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                              </select>                                                   
                                                <br />
                                              <input type="time" class="form-control" id="time" placeholder="time" value="08:00">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-8">
                                              <br />
                                              <button type="submit" class="btn btn-primary btn-lg">Finish</button>
                                            </div>
                                          </div>

                                </form>
                           </div> 
                </div>
            </div>
        </div>
      




      
        
    </div> <!-- /container -->
      <div class="whitediv"> <!-- added by oran - footer -->
      <hr> 
        <p><span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was created by Analytics geeks, for Analytics geeks. And it will keep getting better.</p>
       
      </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/js/smsga/jquery.min.js"></script>
    <script src="../js/smsga/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/assets/ie10-viewport-bug-workaround.js"></script>
    
    <script type="text/javascript">
    	var accountsJson= '${accountsJson}';
    	if (accountsJson == "")
    		{
    		accountsJson = " ";
    		}
    	//var accountsJson= '"account123"';
	</script>
	<script type="text/javascript" src="../js/jstz.min.js"></script> 
	<script type="text/javascript" src="js/gaconnect.js" charset="utf-8"></script>
    
  </body>
</html>
