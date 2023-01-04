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
<title>Inserisci un prodotto</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Inserimento</div>
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
						<label>Disponibilità</label> <input type=text class="form-control"
							name="disp"
							placeholder="Inserisci numero di pezzi disponibili...">
					</div>
					<div class="form-group">
						<label>Foto</label> <input type=text class="form-control"
							name="foto" placeholder="Inserisci nome file...">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>