<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entity.connection.DbCon"%>
<%@page import="entity.model.*"%>
<%@page import="entity.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
User auth = (User) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth", auth);
}else{
	auth=new User();
	auth.setIsGuest(1);
	request.setAttribute("auth", auth);
}

List<Cappello> products = (List<Cappello>) request.getSession().getAttribute("products");
ArrayList<Cappello> product_list = (ArrayList<Cappello>) request.getAttribute("search-product");
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  
<title>DressApp</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	
	<div class="container text-center">
		<form class="search" action="search-bar" type="POST">
  			<br><input type="text" placeholder="Cerca..." name="search">
  			<button type="submit"><i class="fa fa-search"></i></button>
		</form>
				
	
		
		
		<div class="row">
		<%if(product_list!=null){			
				if(product_list.size()==0){
					%>
					<div class="container text-center">
							<br><br><h3>Nessun prodotto trovato...</h3>
					</div>
					<%
					
				}
				else{
					for (Cappello p : product_list) {
					%>
					<div class="col-md-3 my-3">
						<div class="card w-100" style="width: 18rem;">
							<img src="product-images/<%=p.getFoto() %>" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title"><%=p.getNome() %></h5>
								<h6 class="price">Prezzo: <%=p.getPrezzo() %>&euro;</h6>
								<h6 class="category">Categoria: <%=p.getCategoria() %></h6>
								<h6 class="description">Dettagli: <%=p.getDescrizione() %></h6>
								<div class="mt-3 d-flex float-right justify-content-between">
									<form action="add-to-cart" method="get">
                                	<%if(auth!=null){ %>
                                	<input type="hidden" name="u_id" value="<%= auth.getId() %>">
                                	<%} %>
                                	<input type="hidden" name="p_id" value="<%= p.getId() %>">
                                	<input type="submit" class="btn btn-primary" value="Add to cart">
                            </form>
								</div>
							</div>
						</div>
					</div>
					<%
					}
				}
			}else{
				if(products!=null){
				for (Cappello p : products) {
					%>
					<div class="col-md-3 my-3">
						<div class="card w-100" style="width: 18rem;">
							<img src="product-images/<%=p.getFoto() %>" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title"><%=p.getNome() %></h5>
								<h6 class="price">Prezzo: <%=p.getPrezzo() %>&euro;</h6>
								<h6 class="category">Categoria: <%=p.getCategoria() %></h6>
								<h6 class="description">Dettagli: <%=p.getDescrizione() %></h6>
								<div class="mt-3 d-flex float-right justify-content-between">
									<form action="add-to-cart" method="get">
                                <%if(auth!=null){ %>
                                <input type="hidden" name="u_id" value="<%= auth.getId() %>">
                                <%} %>
                                <input type="hidden" name="p_id" value="<%= p.getId() %>">
                                <input type="submit" class="btn btn-primary" value="Add to cart">
                            	</form>
								</div>
							</div>
						</div>
					</div>
					<%
				}
			}
		}
		%>
		</div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>	
</body>
</html>