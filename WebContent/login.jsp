<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  

<title>DressApp - Login</title>
<link rel=stylesheet href="style.css">
</head>

<body>

<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="rec-servlet" method="post">
			<h1>Registrati</h1>
			<input type="text" placeholder="Nome" name="rec-name" required/>
			<input type="text" placeholder="Cognome" name="rec-surname" required/>
			<input type="email" placeholder="Email" name="login-email" required/>
			<input type="password" placeholder="Password" name="login-password" required/>
			<%String error1 = null;
					    error1 = (String) getServletContext().getAttribute("errorRec");
							if(error1 != null){%>
						<p style="color: red">*Email già associata ad un account!</p>
						<%
							getServletContext().removeAttribute("errorRec");}
							%>
			<button type="submit">Registrati</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="user-login" method="post" name="sigin">
					<h1>Login</h1>
					<div class="social-container">
						<img src="product-images/logo.jpg">
					</div>
					<input type="email" name="login-email" placeholder="Email" required/>
					<input type="password" name="login-password" placeholder="Password" required/>
						<%
						String error = null;
					     error = (String) getServletContext().getAttribute("errorLogin");
							if(error != null){%>
						<p style="color: red">*Email o password non corrette!</p>
						<%
							getServletContext().removeAttribute("errorLogin");}
							%>
						<button type="submit">Login</button>
					
				</form>
		
		
		
		
		
		
		
		
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Bentornato!</h1>
				<p>Sei già dei nostri? Allora cosa aspetti...</p>
				<button class="ghost" id="signIn">Accedi</button><br><br>
				<h2><a href="index.jsp">TORNA ALLA HOME</a></h2>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Ciao!</h1>
				<p>Se non sei ancora dei nostri è ora di farlo...</p>
				<button class="ghost" id="signUp">Registrati</button><br><br>
				<h2><a href="index.jsp">TORNA ALLA HOME</a></h2>
			</div>
		</div>
	</div>
</div>
<script id="rendered-js">
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

</script>





</body>
</html>