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
</head>
<body>
<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<%if(auth.getIsGuest()==1){%>
			<div class="container">
			<h1>SPIACENTI MA NON PUOI PAGARE SE NON SEI LOGGATO</h1>
			<h3>Ti invitiamo a fare il Login altrimenti dai un'occhiata alla nostra home</h3>
			<a class="mx-3 btn btn-primary" href="login.jsp" style="align:center;background: #404040;border:#404040">Login</a>
			<a class="mx-3 btn btn-primary" href="show-products" style="align:center;background: #404040;border:#404040">Home</a>
			
		</div><br>
		<%}else{%>
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center" style="background: #404040;border:#404040;color:white;">Gestione metodi di pagamento</div>
			<div class="card-body text-center">
			<table>
				<% for(Card p: card_list){ %><tr>
						
						<td><h5> <%= p.getNumeroCarta() %> </h5></td>
						<td class="align-middle"><form action="selectPayment" method="post">
							<input type="hidden" name="o_id" value="<%= o_id %>">
							<button type="submit" class="btn btn-dark" name="Scegli" style="background: #404040;border:#404040">Paga Ora!</button>
						</form></td>
						<td class="align-middle"><form action="deleteServlet" method="post">
							<input type="hidden" name="c_id" value="<%= p.getId() %>">
							<button type="submit" class="btn btn-dark" name="Elimina" style="background: #404040;border:#404040">X</button>
						</form></td>
					</tr>
				<% } %>
				</table>
				<form action="payment.jsp" method="post">
					<div class="text-center">
						<input type="hidden" name="auth_id" value="<%= auth.getId() %>">
						<button type="submit" class="btn btn-dark" name="aggiungiMetodoDiPagamento" style="background: #404040;border:#404040">Aggiungi metodo di pagamento</button>
					</div>
				</form>
				
			</div>
		</div>	<% } %>
	</div>
	
	<br><br><br><br><br><br>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>	
	
	<%@ include file="includes/footer.jsp"%>
</body>
</html>

