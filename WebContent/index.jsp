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
	request.getSession().setAttribute("auth", auth);
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
                                <input type="submit" class="btn btn-primary" style="background: #404040;border:#404040" value="Add to cart">
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
<button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top" style="position: fixed;bottom: 20px;
        right: 20px;display: none;">
          <i class="fas fa-arrow-up"></i>
        </button>
		<%@ include file="includes/footer.jsp"%>
<script type="text/javascript">
//Get the button
let mybutton = document.getElementById("btn-back-to-top");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
scrollFunction();
};

function scrollFunction() {
if (
document.body.scrollTop > 20 ||
document.documentElement.scrollTop > 20
) {
mybutton.style.display = "block";
} else {
mybutton.style.display = "none";
}
}
// When the user clicks on the button, scroll to the top of the document
mybutton.addEventListener("click", backToTop);

function backToTop() {
document.body.scrollTop = 0;
document.documentElement.scrollTop = 0;
}


</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>	
</body>
</html>