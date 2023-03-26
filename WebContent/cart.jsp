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
	request.getSession().setAttribute("auth", auth);
}
OrderDao oDao=new OrderDao(DbCon.getConnection());
Ordine ordine=oDao.doRetriveByIdBuy(auth.getId());
request.getSession().setAttribute("o_id", ordine.getId());
DettaglioOrdineDAO dtDao=new DettaglioOrdineDAO(DbCon.getConnection());
ArrayList<DettaglioOrdine> list=dtDao.searchDettaglioOrdineByOrdineId(ordine.getId());
request.getSession().setAttribute("listDettaglio", list);
CappelloDao cDao=new CappelloDao(DbCon.getConnection());
float total = 0;
ArrayList<Cappello> cappelli=new ArrayList<Cappello>(); 
for(DettaglioOrdine o:list){
	int id=o.getCappello();
	Cappello c=cDao.retriveProductById(id);
	cappelli.add(c);
	total+=c.getPrezzo()*o.getQuantita();
}



request.getSession().setAttribute("total", total);

%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  

<title>DressApp - Carrello</title>

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
	<%if(auth.getIsGuest()==1){
		response.sendRedirect("login.jsp");
	}else{%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Totale: &euro; ${ (total>0)?dcf.format(total):0 }</h3>
			<%if(total == 0 ){
				
				%>
				&ensp;&ensp;&ensp;<h4><p style="color: red">*Carrello vuoto...Inserisci almeno un prodotto</p></h4>
				<%
			}else{ %>
			<a class="mx-3 btn btn-primary" href="sceltaPagamento.jsp" style="background: #404040;border:#404040">Check out</a>
			<%}%>
		</div>
		<h6 style="text-align:right;"><%
						String error = null;
					     error = (String) getServletContext().getAttribute("errorIncrement");
							if(error != null){%>
						<p style="color: red">*Quantità non disponibile</p>
						<%
							getServletContext().removeAttribute("errorIncrement");}
							%>
			</h6>
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
				if (cappelli != null) {
					for (Cappello c : cappelli) {
						
				%>
				<tr>
					<td><%=c.getNome()%></td>
					<td><%=c.getCategoria()%></td>
					<td><%=dcf.format(c.getPrezzo())%></td>
					<td>
						<form method="get" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justifay-content-between w-50">
								<a  style="text-decoration:none"  href="dec?p_id=<%=c.getId()%>">
								    -</a> 
									<input id="num" type="text" name="quantity" class="form-cntrol w-50" value="<%=dtDao.getQuantita(c.getId(),ordine.getId())%>" readonly> 
									<a style="text-decoration:none" id="inc"  href="inc?p_id=<%=c.getId()%>">
									+</a>
							</div>
						</form>
					</td>
					
					<td><a class="btn btn-sm btn-danger"
						href="removeCart?p_id=<%=c.getId()%>">Rimuovi</a></td>
				</tr>
				<%
				
					}
				}
				 
	}
				%>

			</tbody>
		</table>
		<button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top" style="position: fixed;bottom: 20px;
        right: 20px;display:none;">
          GO Up!
        </button>
	</div><br><br><br><br><br><br><br><br><br><br><br><br>
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
