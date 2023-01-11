<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="entity.model.*" %>
    <%@page import="entity.dao.*" %>
    <%@page import="entity.connection.*" %>
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
	CardDAO cDao=new CardDAO(DbCon.getConnection());
	ArrayList<Card> card_list = cDao.getAllCards(auth.getId());
	OrderDao oDao=new OrderDao(DbCon.getConnection());
	int o_id = (oDao.doRetriveByIdBuy(auth.getId())).getId();
	
	%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">
<title>DressApp - Scelta Pagamento</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Gestione metodi di pagamento</div>
			<div class="card-body">
				<% for(Card p: card_list){ %>
					<div class="form-group">
						<label><%= p.getNumeroCarta() %></label>
						<form action="selectPayment" method="post">
							<input type="hidden" name="o_id" value="<%= o_id %>">
							<button type="submit" class="btn btn-primary" name="Scegli">Scegli</button>
						</form>
						<form action="deleteServlet" method="post">
							<input type="hidden" name="c_id" value="<%= p.getId() %>">
							<button type="submit" class="btn btn-primary" name="Elimina">X</button>
						</form>
					</div>
				<% } %>
				<form action="payment.jsp" method="post">
					<div class="text-center">
						<input type="hidden" name="auth_id" value="<%= auth.getId() %>">
						<!-- Se inserito vanno messe le parentesi angolate con il percentuale -->
						<button type="submit" class="btn btn-primary" name="aggiungiMetodoDiPagamento">Aggiungi metodo di pagamento</button>
					</div>
				</form>
			</div>
		</div>	
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>	
	<%@ include file="includes/footer.jsp"%>
</body>
</html>
