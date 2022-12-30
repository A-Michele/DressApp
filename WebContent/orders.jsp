<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.connection.DbCon" %>
<%@page import="entity.model.*" %>
<%@page import="entity.dao.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList" %>
	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
		request.setAttribute("dcf", dcf);
		
		User auth =(User) request.getSession().getAttribute("auth");
		if(auth!=null){
			request.setAttribute("auth",auth);
		}else{
			auth=new User();
			auth.setIsGuest(1);
		}
		CappelloDao pDao = new CappelloDao(DbCon.getConnection());
		CartDao cDao=new CartDao(DbCon.getConnection());
		ArrayList<Cart> cart_list = cDao.retriveOrdersPerUser(auth.getId());
		ArrayList<Cart> carrello=new ArrayList<Cart>();
		for(Cart c:cart_list){
			Cart c1=pDao.completeCart(c);
			carrello.add(c1);
		}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ORDERS</title>
<%@ include file="includes/header.jsp" %>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

<%@ include file="includes/footer.jsp" %>
</body>
	<div class="container">
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Data</th>
					<th scope="col">Compra di nuovo</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (carrello != null) {
					for (Cart c : carrello) {
				%>
				<tr>
					<td><%=c.getNome()%></td>
					<td><%=c.getCategoria()%></td>
					<td><%=dcf.format(c.getPrezzo())%></td>
					<td><%=c.getQuantita()%></td>
					<td><%=c.getData()%></td>
					<td>
						<form action="add-to-cart" method="get">
							<input type="hidden" name="u_id" value="<%= c.getId_utente() %>">
							<input type="hidden" name="p_id" value="<%= c.getId_prodotto() %>">
							<input type="submit" class="btn btn-primary" value="Buy again">								
						</form>
					</td>
				</tr>
				<%
					}
				}
				%>				
			</tbody>
		</table>
	</div>
</html>