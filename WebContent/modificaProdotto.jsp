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

    Cappello p=(Cappello)request.getAttribute("item");
    System.out.println(p.getNome());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DressApp - Modifica prodotto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<%@ include file="includes/header.jsp"%>
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center" style="background: #404040;border:#404040;color:white">Modifica</div>
			<div class="card-body">
				<form action="edit-product" method="post" name="ins">
					<div class="form-group">
						<label>Nome</label> <input type="text" class="form-control"
							name="nomeP" placeholder="<%=p.getNome()%>" >
					</div>
					<div class="form-group">
						<label>Descrizione</label> <input type="text" class="form-control"
							name="descP" placeholder="<%=p.getDescrizione()%>" >
					</div>
					<div class="form-group">
						<label>Prezzo</label> <input type=text class="form-control"
							name="prezzo" placeholder="<%=p.getPrezzo()%>" >
					</div>
					<div class="form-group">
						<label>Categoria</label> <input type=text class="form-control"
							name="cat" placeholder="<%=p.getCategoria()%>" >
					</div>
					<div class="form-group">
						<label>Foto</label> <input type=text class="form-control"
							name="foto" placeholder="<%=p.getFoto()%>" >
					</div>
					<div class="form-group">
						<label>Disponibilità</label> <input type=text class="form-control"
							name="disp" placeholder="<%=p.getDisp()%>" >
					</div>
					<input type="hidden" name="p_id" value="<%= p.getId() %>">
					<div class="text-center">
						<button type="submit" class="btn btn-primary" style="background: #404040;border:#404040">Modifica</button>
					</div>
				</form>
			</div>
		</div>
	</div>
<button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top" style="position: fixed;bottom: 20px;
        right: 20px;display: none;">
          <i class="fas fa-arrow-up"></i>
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
