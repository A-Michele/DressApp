<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="entity.model.*" %>
<%@page import="java.util.ArrayList" %>
	<%
	User auth =(User) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth", auth);
	}else{
		auth=new User();
		auth.setIsGuest(1);
		request.getSession().setAttribute("auth", auth);
	}
	%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">
<title>DressApp - Aggiungi Pagamento</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Dati carta</div>
			<div class="card-body">
				<form action="payment" method="post">
					<div class="form-group">
						<label>Proprietario</label> <input type="text"
							class="form-control" name="payment-proprietario"
							placeholder="Nome Cognome proprietario" required>
					</div>
					<div class="form-group">
						<label>Numero carta</label> <input type="text"
							class="form-control" name="payment-num" placeholder="n."
							required>
					</div>
					
					<div class="form-group">
						<label>Data di scadenza</label> <input type="text"
							class="form-control" name="payment-scadenza" placeholder="--/----"
							required>
					</div>
					<div class="form-group">
						<label>CVV</label> <input type="text"
							class="form-control" name="payment-cvv" placeholder="***"
							required>
					</div>
					
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Aggiungi</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>