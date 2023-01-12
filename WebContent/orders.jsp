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
		if (auth != null) {
			request.setAttribute("auth", auth);
		}else{
			auth=new User();
			auth.setIsGuest(1);
			request.getSession().setAttribute("auth", auth);
		}
		DettaglioOrdineDAO dettDao = new DettaglioOrdineDAO(DbCon.getConnection());
		OrderDao oD= new OrderDao(DbCon.getConnection());
		ArrayList<Ordine> ordiniUtente= oD.getOrdersByUser(auth.getId());
	
		
	%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">

<link rel="icon" href="product-images/logo.jpg" type="image/jpg"> 
<title>DressApp - Ordini</title>
<%@ include file="includes/header.jsp" %>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<%if(auth.getIsGuest()!=0){ %>
	<br>
		<div class="container">
			<h2>SPIACENTI MA NON PUOI VISUALIZZARE GLI ORDINI DI UN UTENTE SE NON SEI LOGGATO</h2>
			<h3>Ti invitiamo a fare il Login altrimenti dai un'occhiata alla nostra home</h3>
			<a class="mx-3 btn btn-primary" href="login.jsp" style="align:center;background: #404040;border:#404040">Login</a>
			<a class="mx-3 btn btn-primary" href="show-products" style="align:center;background: #404040;border:#404040">Home</a>
			
		</div><br>
		<%}else{%>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Data</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (ordiniUtente != null) {
					for (Ordine o : ordiniUtente) {
						ArrayList<OrdineCompleto> oC= dettDao.searchOrdiniCompleti(o.getId());
						for(OrdineCompleto oc: oC){
				%>
				<tr>
					<td><%=oc.getNomeC()%></td>
					<td><%=oc.getCategoria()%></td>
					<td><%=dcf.format(oc.getPrezzo())%></td>
					<td><%=oc.getQuantita()%></td>
					<td><%=oc.getData()%></td>
					
				</tr>
				
				
				<% } %>
					<tr><td colspan=5>
					<hr style="width:100%;height:5px;background-color:#404040;"></td></tr>	
				<%} } }%>
				
							
			</tbody>
		</table>
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
