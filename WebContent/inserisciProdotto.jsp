<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="entity.model.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}else{
	auth=new User();
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>DressApp - Inserimento Prodotto</title>
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  

</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		 <%if(auth.getIsAdmin()!=1){ %>
		<div class="container">
			<h1>SPIACENTI MA NON PUOI INSERIRE PRODOTTI SE NON SEI ADMIN</h1>
			<h3>Ti invitiamo a fare il Login altrimenti dai un'occhiata alla nostra home</h3>
			<a class="mx-3 btn btn-primary" href="login.jsp" style="align:center;background: #404040;border:#404040">Login</a>
			<a class="mx-3 btn btn-primary" href="show-products" style="align:center;background: #404040;border:#404040">Home</a>
			
		</div><br>
		<%}else{%>
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center" style="background: #404040;border:#404040;color:white">Inserimento nuovo prodotto</div>
			<div class="card-body">
				<form action="insert-product" method="post" name="ins">
					<div class="form-group">
						<label>Nome</label> <input type="text" class="form-control"
							name="nome" placeholder="Inserisci nome prodotto..." required>
					</div>
					<div class="form-group">
						<label>Descrizione</label> <input type="text" class="form-control"
							name="desc" placeholder="Inserisci una descrizione..." required>
					</div>
					<div class="form-group">
						<label>Costo</label> <input type=text class="form-control"
							name="prezzo" placeholder="--.--" required>
					</div>
					<div class="form-group">
						<label>Categoria</label> <input type=text class="form-control"
							name="cat" placeholder="Inserisci categoria..." required>
					</div>
					<div class="form-group">
						<label>Disponibilita </label> <input type=text class="form-control"
							name="disp"
							placeholder="Inserisci numero di pezzi disponibili...">
					</div>
					<div class="form-group">
						<label>Foto</label> <input type=text class="form-control"
							name="foto" placeholder="Inserisci nome file...">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary" style="background: #404040;border:#404040">Inserisci prodotto</button>
					</div>
				</form>
			</div>
		</div><% } %>
	</div>
<button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top" style="position: fixed;bottom: 20px;
        right: 20px;display: none;">
          Go Up!
        </button>
		<%@ include file="includes/footer.jsp"%>
<script type="text/javascript">
//Get the button
let mybutton = document.getElementById("btn-back-to-top");
// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
scrollFunction();
};
function scrollFunction() {
if (
document.body.scrollTop > 20 ||
document.documentElement.scrollTop > 20
) {
mybutton.style.display = "block";
} else {
mybutton.style.display = "none";
}
}
// When the user clicks on the button, scroll to the top of the document
mybutton.addEventListener("click", backToTop);
function backToTop() {
document.body.scrollTop = 0;
document.documentElement.scrollTop = 0;
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>	
</body>
</html>
