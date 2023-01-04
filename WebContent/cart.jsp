<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.connection.DbCon"%>
<%@page import="entity.model.*"%>
<%@page import="entity.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>

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
CappelloDao pDao = new CappelloDao(DbCon.getConnection());
CartDao cDao=new CartDao(DbCon.getConnection());
ArrayList<Cart> cart_list = cDao.retrivePerUser(auth.getId());
ArrayList<Cart> carrello=new ArrayList<Cart>();
for(Cart c:cart_list){
	Cart c1=pDao.completeCart(c);
	carrello.add(c1);
}

	

	double total = pDao.getTotal(carrello);
//	request.setAttribute("cart-list", cart_list);
	request.setAttribute("total", total);
//}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CART</title>
<%@ include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Totale: $ ${ (total>0)?dcf.format(total):0 }</h3>
			<a class="mx-3 btn btn-primary" href="check-out">Check out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Cancella</th>
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
					<td>
						<form method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justifay-content-between w-50">
								<a class="btn btn-sm btn-decre"
									href="dec?p_id=<%=c.getId_prodotto()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-cntrol w-50"
									value="<%=c.getQuantita()%>" readonly> <a
									class="btn btn-sm btn-incre"
									href="inc?p_id=<%=c.getId_prodotto()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger"
						href="removeCart?p_id=<%=c.getId_prodotto()%>">Rimuovi</a></td>
				</tr>
				<%
					}
				}
				%>

			</tbody>
		</table>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>