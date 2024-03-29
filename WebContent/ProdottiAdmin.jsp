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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  
<title>DressApp - Prodotti Admin</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

.img {
	border: 1px solid #575757;
	margin-left: 12px;
}
</style>


</head>
<body>



	<%@ include file="includes/navbar.jsp"%>
	<%
	//out.print(DbCon.getConnection());
	%>
	<%if(auth.getIsAdmin()!=1){ %>
		<div class="container">
			<h1>SPIACENTI MA NON PUOI VISUALIZZARE GLI ORDINI DEGLI UTENTI SE NON SEI ADMIN</h1>
		</div>
		<%}else{%>
	
	
	
	
    
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
                                <input type="submit" class="btn btn-primary" value="Inserisci Un Prodotto" style="background: #404040;border:#404040">
                            	</form>
                            	</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
					for (Cappello p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<div id="immages">
					<img src="product-images/<%=p.getFoto() %>" class="card-img-top" alt="..."></div>
					<div class="card-body">
						<h5 class="card-title"><%=p.getNome() %></h5>
						<h6 class="price">Prezzo: <%=p.getPrezzo() %>&euro;</h6>
						<h6 class="category">Categoria: <%=p.getCategoria() %></h6>
						<h6 class="description">Dettagli: <%=p.getDescrizione() %></h6>
						<div class="mt-3 d-flex float-right justify-content-between">
							<form action="remove" method="get">
                                <%if(auth!=null){ %>
                                <input type="hidden" name="u_id" value="<%= auth.getId() %>">
                                <%} %>
                                <input type="hidden" name="p_id" value="<%= p.getId() %>">
                                <input type="submit" class="btn btn-primary" value="Rimuovi" style="background: #404040;border:#404040">
                            	</form>
                            	&emsp;&emsp;&ensp;
                            <form action="reindirizzaServlet" method="get">
                                <%if(auth!=null){ %>
                                <input type="hidden" name="u_id" value="<%= auth.getId() %>">
                                <%} %>
                                <input type="hidden" name="p_id" value="<%= p.getId() %>">
                                <input type="submit" class="btn btn-primary" value="Modifica" style="background: #404040;border:#404040">
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
          Go Up!
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



//Effetto fade applicato alle foto
$(function() {
// Opacità delle immagini impostate al 50%
$(".card-img-top").css("opacity","0.5");

// Al passaggio del mouse
$(".card-img-top").hover(function () {

// imposta l'opacità al 100%
$(this).stop().animate({
opacity: 1.0
}, "slow");
},

// quando il mouse non è sull'elemento
function () {

// imposta l'opacità al 50%
$(this).stop().animate({
opacity: 0.5
}, "slow");
});
});

//Effetto fade applicato al testo
$(function() {
// Opacità del testo impostata al 50%
$("#text p").css("opacity","0.5");

// Al passaggio del mouse
$("#text p").hover(function () {

// imposta l'opacità al 100%
$(this).stop().animate({
opacity: 1.0
}, "slow");
},

// quando il mouse non è sull'elemento
function () {

// imposta l'opacità al 50%
$(this).stop().animate({
opacity: 0.5
}, "slow");
});
});

//effetto fade applicato al div
$(function() {
// Opacità del div impostata al 50%
$("#div").css("opacity","0.5");

// Al passaggio del mouse
$("#div").hover(function () {

// imposta l'opacità al 100%
$(this).stop().animate({
opacity: 1.0
}, "slow");
},

// quando il mouse non è sull'elemento
function () {

// imposta l'opacità al 50%
$(this).stop().animate({
opacity: 0.5
}, "slow");
});
});

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>	
</body>
</html>

