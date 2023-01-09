<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.connection.DbCon"%>
<%@page import="entity.model.*"%>
<%@page import="entity.dao.CappelloDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>



<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}else{
	auth=new User();
	auth.setIsGuest(1);
}

CappelloDao pd = new CappelloDao(DbCon.getConnection());
List<Cappello> products = pd.getAllProducts();

// ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
// if (cart_list != null) {
// 	request.setAttribute("cart-list", cart_list);
// }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProdottiAdmin</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<%
	//out.print(DbCon.getConnection());
	%>
	
    
	<div class="container">
		<br>
		<div class="container text-center">
	<form action="reindirizzaServlet" method="Post">
                                <%
                                if(auth!=null){
                                %>
                                <input type="hidden" name="u_id" value="<%=auth.getId()%>">
                                <%
                                }
                                %>
                                <input type="submit" class="btn btn-primary" value="Inserisci Un Prodotto">
                            	</form>
                            	</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
					for (Cappello p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="product-images/<%=p.getFoto() %>" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=p.getNome() %></h5>
						<h6 class="price">Prezzo: <%=p.getPrezzo() %>$</h6>
						<h6 class="category">Categoria: <%=p.getCategoria() %></h6>
						<h6 class="description">Dettagli: <%=p.getDescrizione() %></h6>
						<div class="mt-3 d-flex float-right justify-content-between">
							<form action="remove" method="get">
                                <%if(auth!=null){ %>
                                <input type="hidden" name="u_id" value="<%= auth.getId() %>">
                                <%} %>
                                <input type="hidden" name="p_id" value="<%= p.getId() %>">
                                <input type="submit" class="btn btn-primary" value="Remove">
                            	</form>
                            	&emsp;&emsp;&ensp;
                            <form action="reindirizzaServlet" method="get">
                                <%if(auth!=null){ %>
                                <input type="hidden" name="u_id" value="<%= auth.getId() %>">
                                <%} %>
                                <input type="hidden" name="p_id" value="<%= p.getId() %>">
                                <input type="submit" class="btn btn-primary" value="Modifica">
                            	</form>
                            
                            	
						</div>
					</div>
				</div>
			</div>
			<%
				}
			}
			%>

		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>