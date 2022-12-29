<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.connection.DbCon"%>
<%@page import="entity.model.*"%>
<%@page import="entity.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.*" %>
<%@page import="java.sql.*" %>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}else{
	auth=new User();
	auth.setIsGuest(1);
}

ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

ProductDao pDao = new ProductDao(DbCon.getConnection());
	CartDao cDao=new CartDao(DbCon.getConnection());
	ArrayList<Cart> cart_list = cDao.retriveOrders();
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
<%@ include file="includes/header.jsp"%>

       <HEAD>
       <TITLE>Ordini Utenti</TITLE>
       </HEAD>
       <BODY>
       <%@ include file="includes/navbar.jsp"%>

           <div class="container">
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Utente</th>
					<th scope="col">Nome Prodotto</th>
					<th scope="col">Categoria</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Data</th>
					
				</tr>
			</thead>
			<tbody>
				<%
				if (carrello != null) {
					for (Cart c : carrello) {
				%>
				<tr>
					<td><%=cDao.getEmail(c.getId_utente()) %></td>
					<td><%=c.getNome()%></td>
					<td><%=c.getCategoria()%></td>
					<td><%=dcf.format(c.getPrezzo())%></td>
					<td><%=c.getQuantita()%></td>
					<td><%=c.getData()%></td>
				
				</tr>
				<%
					}
				}
				%>				
			</tbody>
</table>
	</div>
</html>