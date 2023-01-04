<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="entity.model.*" %>
    <%@page import="entity.dao.*" %>
    <%@page import="entity.connection.*" %>
<%@page import="java.util.ArrayList" %>
	<%
	User auth =(User) request.getSession().getAttribute("auth");
	if(auth!=null){
		request.setAttribute("auth",auth);
	}else{
		auth=new User();
	}
	
	CardDAO cDao=new CardDAO(DbCon.getConnection());
	ArrayList<Card> card_list = cDao.getAllCards(auth.getId());
	
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transition</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
<!--  include file="includes/navbar.jsp"-->

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Gestione metodi di pagamento</div>
			<div class="card-body">
				<form action="selectPayment" method="post">
				<% for(Card p: card_list){ %>
					<div class="form-group">
						<label><%= p.getNumeroCarta() %></label>
						<input type="hidden" name="p_id" value="<%= p.getId() %>">
						<button type="submit" class="btn btn-primary" name="Scegli">Scegli</button>
						<button type="submit" class="btn btn-primary" name="Elimina">X</button>
					</div>
				<% } %>
				</form>
				<form action="payment.jsp" method="post">
					<div class="text-center">
						<!--  <input type="hidden" name="auth_id" value=" auth.getId() ">-->
						<!-- Se inserito vanno messe le parentesi angolate con il percentuale -->
						<button type="submit" class="btn btn-primary" name="aggiungiMetodoDiPagamento">Aggiungi metodo di pagamento</button>
					</div>
				</form>
			</div>
		</div>	
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>