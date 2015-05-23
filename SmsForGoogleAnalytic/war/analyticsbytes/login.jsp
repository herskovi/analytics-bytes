
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
	<link rel="stylesheet" href="/analyticsbytes/bootstrap/dist/css/bootstrap.min.css">
	
	
	<link rel="stylesheet" href="/analyticsbytes/bootstrap/dist/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" media="all" href="/analyticsbytes/css/jumbotron.css">
	<link rel="stylesheet" type="text/css" media="all" href="/analyticsbytes/css/analyticsbytes.css"> 
	
	
    <!-- Custom styles for this template -->
    <link href="/analyticsbytes/css/jumbotron.css" rel="stylesheet"> <!-- added by oran-->
    <!--  <link href="css/bootstrap.min.css" rel="stylesheet">  -->  <!-- added by oran-->
    <link rel="stylesheet" href="/analyticsbytes/css/analyticsbytes.css"> <!-- added by oran-->

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/analyticsbytes/js/assets/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body id="login">

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
        
                <div class="nav navbar-nav navbar-right navbarsignin">
                    <a href="/login" type="button" class="btn">Log in</a>
                    <a href="/register" type="button" class="btn btn-default topbtn">Test it now, It's Free</a>
        
          
                </div>


            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
      
      
      	
                                      

          <div class="rapper graydiv toppadding"> <!-- added by oran - main gray div - original html inside --> 
                <div class="container">

                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <h1><span class="hero-title">Hey Again!</span> <small>it's good to see you...</small>  </h1>
                
                            <div class="feature alt-bg">
                                <div class="row-fluid">
		
		                          <div class="form-wrapper">
                                    <form name="loginForm" action="/securelogin" method="post" class="form-horizontal login-form">	
                                       
                                        <div class="form-group row-fluid">
					                       <label class="col-sm-4 control-label">Email <span class="required">*</span></label>
					                       <div class="col-sm-8">
						                      <input  class="form-control" id="username" name="username" placeholder="Enter your email..."  title="Enter your Email" type="text" value="" placeholder="Enter your email...">
					                       </div>
				                        </div>
				                        <div class="form-group"> 
					                       <label class="col-sm-4 control-label">Password <span class="required">*</span></label>
					                       <div class="col-sm-8">
						                      <input  class="form-control" id="j_password" name="j_password" placeholder="Enter your password..."  title="Enter your Password" type="password" value="" placeholder="Enter your password...">
						                       <label class=" col-sm-4 control-label">
					                       		<input type="checkbox" value="remember-me" id="remember_me"> Remember me</label>
					                       </div>
				                        </div>
				                       
				
				                          <div class="form-group">
 												<label for="empty" class="col-sm-4 control-label"></label>
 												<div class="col-sm-8">
 						           						<button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
                            					</div>
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
  
                
                 
            </div> 
          </div>
      
    
      </div>
    </div>
      

 <!-- /container -->
      
      
      <br />
              </div></div></div></div>
      <div class="whitediv navbar navbar-fixed-bottom"> <!-- added by oran - footer div -->
      <hr> 
        <p><span class="glyphicon glyphicon-heart"></span> AnalyticsBytes was created by Analytics geeks, for Analytics geeks. And it will keep getting better.</p>
       
      </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="/analyticsbytes/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/analyticsbytes/js/assets/ie10-viewport-bug-workaround.js"></script>

  </body>
</html>
