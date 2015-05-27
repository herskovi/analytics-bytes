$(document).ready(function() 
{
	if(isLoginError == 1)
	{
		$('#username').addClass("inline-error");
		$('#j_password').addClass("inline-error");
		$("#username").closest('.form-group').addClass('has-error');
		$("#j_password").closest('.form-group').addClass('has-error');
	}
});


$(document).ready(function() 
		{
 
                if (localStorage.chkbx && localStorage.chkbx != '') {
                    $('#remember_me').attr('checked', 'checked');
                    $('#username').val(localStorage.usrname);
                    $('#j_password').val(localStorage.pass);
                } else {
                    $('#remember_me').removeAttr('checked');
                    $('#username').val('');
                    $('#j_password').val('');
                }
 
                $('#remember_me').click(function() {
 
                    if ($('#remember_me').is(':checked')) {
                        // save username and password
                        localStorage.usrname = $('#username').val();
                        localStorage.pass = $('#j_password').val();
                        localStorage.chkbx = $('#remember_me').val();
                    } else {
                        localStorage.usrname = '';
                        localStorage.pass = '';
                        localStorage.chkbx = '';
                    }
                });
            }); 

 /*
        
        <div class="container">
            <form class="form-signin">
                <h2 class="form-signin-heading">Please sign in</h2>
                <input type="text" class="input-block-level" placeholder="Email address" id='username'>
                <input type="password" class="input-block-level" placeholder="Password" id="pass">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me" id="remember_me"> Remember me
                </label>
                <button class="btn btn-large btn-primary" type="submit">Sign in</button>
            </form>
        </div> 

*/

