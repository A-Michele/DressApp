<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.model.*" %>
<%@page import="java.util.ArrayList" %>
	<%
	User auth =(User) request.getSession().getAttribute("auth");
	if(auth!=null){
		response.sendRedirect("index.jsp");
	}
	
	
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTRAZIONE</title>
<script type="text/javascript">

function validationRegistration(){
	
	var email = document.registration.email;
	//var valNome= document.registration.nome;
	//var valCognome = document.registration.cognome;
	//var valPass = document.registration.password;
	//var valPass2 = document.registration.password2;
	
	if( validateEmail(valEmail))
		{
		 return true;
		}
	return false;
	
}

function validateEmail(valEmail){
	var x = valEmail.value;
	
	var atPos = x.indexOf("@");
	var dotPos = x.lastIndexOf(".");
	email.style.backgroundColor = "white";
	if( atPos < 1 || dotPos < atPos + 2 || dotPoss + 2 >= x.length)
		{
		email.placeholder = "enter value";
		email.style.backgroundColor = "indianred";
		email.focus();
		return false;
		
		}else{
			window.location.reload();
			return true;
		}
	
	
}



</script>








<%@ include file="includes/header.jsp"%>
</head>
<body>
	<div class="container text-center">
		<a class="navbar-brand" href="index.jsp"><img src="product-images/logo.jpeg" alt="..."></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>


	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Registrazione</div>
			<div class="card-body">
				<form action="rec-servlet" method="post" onsubmit="return validationRegistration()">
					
					<div class="form-group">
						<label>Nome</label> <input type="text"
							class="form-control" name="rec-name"
							placeholder="Mario" >
					</div>
					
					<div class="form-group">
						<label>Cognome</label> <input type="text"
							class="form-control" name="rec-surname"
							placeholder="Rossi">
					</div>
					
					<div class="form-group">
						<label>Email</label> <input type="email"
							class="form-control" name="login-email"
							placeholder="mariorossi@gmail.com" required>
					</div>
					
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-password" placeholder="********"
							required>
					</div>
					
					
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Registrati</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>