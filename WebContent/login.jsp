<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.model.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<script type="text/javascript">
function validation(){
	var valEmail = document.login.email;
	var valPass = document.login.password;

	if( validateEmail(valEmail)){
		if(validatePass(valPass,9,15))
			{
				return true;
			}
	}
	return false;
	
}

function validateEmail(valEmail){
	var x = valEmail.value;
	
	var atPos = x.indexOf("@");
	var dotPos = x.lastIndexOf(".");
	
	if( atPos < 1 || dotPos < atPos + 2 || dotPos + 2 >= x.length)
		{
			alert("Campo email ERRATO !!!");
			valEmail.focus();
			return false;
		}
	return true;
	
}


function validatePass(valPass, mx, my){
	var passLen = valPass.value.length;
	if(passlen >= my || passLen <= mx)
		{
			alert("La pass deve essere lunga almeno "+mx+" caratteri e massimo "+my+" caratteri.");
			valPass.focus();
			return false;
		}
	else
		{
			alert("Login fatto");
			window.location.reload()
			return true;
		}
}

</script>
<%@ include file="includes/header.jsp"%>
</head>
<body>

	<div class="container text-center">
		<a class="navbar-brand" href="show-products"><img
			src="product-images/logo.jpeg" alt="..."></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>


	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Login</div>
			<div class="card-body">
				<form action="user-login" method="post" name="sigin"
					onsubmit="return validation()">
					<div class="form-group">
						<label>Email</label> <input type="email" class="form-control"
							name="login-email" placeholder="mariorossi@gmail.com" required>
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-password" placeholder="********"
							required>
						<%String error = null;
					     error = (String) getServletContext().getAttribute("errorLogin");
							if(error != null){%>
						<p style="color: red">*Email o password non corrette!</p>
						<%
							getServletContext().removeAttribute("errorLogin");}
							%>
					</div>
					<p>
						Prima volta su DressApp?<a href="Registrazione.jsp">
							Registrati.</a>
					</p>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>