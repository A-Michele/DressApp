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

CappelloDao pd = new CappelloDao(DbCon.getConnection());
List<Cappello> products = pd.getAllProducts();

ArrayList<Ordine> ordini=null;
ArrayList<DettaglioOrdine> dettagliOrdini= new ArrayList<DettaglioOrdine>();
OrderDao oDao=null;
CappelloDao pDao = null;
ArrayList<OrdineCompleto> tutto= new ArrayList<OrdineCompleto>();
ArrayList<OrdineCompleto> tuttiDett= new ArrayList<OrdineCompleto>();
DettaglioOrdineDAO dett_o=null;

ArrayList<Ordine> order_list = (ArrayList<Ordine>) request.getAttribute("search-orders");
ArrayList<OrdineCompleto> tuttoTrovato=null;
oDao=new OrderDao(DbCon.getConnection());
ordini = oDao.getAllOrders();
if(auth.getIsAdmin()!=0){
		pDao = new CappelloDao(DbCon.getConnection());
		
		 dett_o= new DettaglioOrdineDAO(DbCon.getConnection());
		if(ordini!=null){
				for(Ordine o:ordini){
					tuttiDett=dett_o.searchOrdiniCompleti(o.getId());
					for(OrdineCompleto c: tuttiDett)
						tutto.add(c);
				}
				
		}
		if(order_list!=null){
			if(order_list.size()>0){
				tuttoTrovato= new ArrayList<OrdineCompleto>();
				ArrayList<OrdineCompleto> tuttiDettagliTrovati= new ArrayList<OrdineCompleto>();
				for(Ordine ord: order_list){
					tuttiDettagliTrovati= dett_o.searchOrdiniCompleti(ord.getId());
					for(OrdineCompleto oc: tuttiDettagliTrovati)
						tuttoTrovato.add(oc);
				}
			}
		}
		
}	


%>


<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg"> 
<title>DressApp - Ordini Utenti</title>

<body>
       <%@ include file="includes/navbar.jsp"%>
		<%if(auth.getIsAdmin()!=1){ %>
		<div class="container">
			<h1>SPIACENTI MA NON PUOI VISUALIZZARE GLI ORDINI DEGLI UTENTI SE NON SEI ADMIN</h1>
			<h3>Ti invitiamo a fare il Login altrimenti dai un'occhiata alla nostra home</h3>
			<a class="mx-3 btn btn-primary" href="login.jsp" style="align:center;background: #404040;border:#404040">Login</a>
			<a class="mx-3 btn btn-primary" href="show-products" style="align:center;background: #404040;border:#404040">Home</a>
			
		</div><br>
		<%}else{%>
	
        <div class="container">
        <form align=center class="search" action="AllOrdersServlet" >
  			<br><input type="text" placeholder="Cerca..." name="search">
  			<input type="date" name="dataInizio" >
  			<input type="date" name="dataFine" >
  			<button type="submit"><i class="fa fa-search">Cerca</i></button>
  			
		</form>
		<br>
		<table class="table table-loght">
			<%if(order_list!=null){			
				if(order_list.size()==0){
					%>
					<div class="container text-center">
							<br><br><h3>Nessun ordine trovato...</h3>
					</div>
			<%	}else{%>
				<thead>
				<tr>
					<th scope="col">Mail Utente</th>
					<th scope="col">Nome Cappello</th>
					<th scope="col">Categoria</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Data</th>
					
				</tr>
			</thead>
			
			<%
			int cont=0;
					for(OrdineCompleto ordineCompleto:tuttoTrovato){
						cont++;
			%>
					
				<tbody>
				<tr>
					<td><%=ordineCompleto.getEmail() %></td>
					<td><%=ordineCompleto.getNomeC() %></td>
					<td><%=ordineCompleto.getCategoria()%></td>
					<td><%=dcf.format(ordineCompleto.getPrezzo())%></td>
					<td><%=ordineCompleto.getQuantita()%></td>
					<td><%=ordineCompleto.getData()%></td>
				
				</tr>
			<%		if(cont<tuttoTrovato.size())if(!(ordineCompleto.getData().equals(tuttoTrovato.get(cont).getData()))){%>
			<tr><td colspan=6>
					<hr style="width:100%;height:5px;background-color:#404040;"></td></tr>
					</tbody>
			<% 
				}}}
				}else if (ordini != null) {%>
				<thead>
				<tr>
					<th scope="col">Mail Utente</th>
					<th scope="col">Nome Cappello</th>
					<th scope="col">Categoria</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Data</th>
					
				</tr>
			</thead>
				
				
				
				<%
				int x=0;
					for (OrdineCompleto o : tutto) {
					x++;
					%>
						
			<tbody>
				<tr>
					<td><%=o.getEmail() %></td>
					<td><%=o.getNomeC() %></td>
					<td><%=o.getCategoria()%></td>
					<td><%=dcf.format(o.getPrezzo())%></td>
					<td><%=o.getQuantita()%></td>
					<td><%=o.getData()%></td>
				
				</tr>
				<% if(x<tutto.size())if(!(o.getData().equals(tutto.get(x).getData()))){ %>
				<tr><td colspan=6>
					<hr style="width:100%;height:5px;background-color:#404040;"></td></tr>
				<% } }
				 }%>	
				<tr><td colspan=6>
					<hr style="width:100%;height:5px;background-color:#404040;"></td></tr>			
			</tbody>
	</table>
	</div>
	
	<% } %>
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
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
	</body>
	
</html>