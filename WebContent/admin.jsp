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

if(auth.getIsAdmin()!=0){
		pDao = new CappelloDao(DbCon.getConnection());
		oDao=new OrderDao(DbCon.getConnection());
		ordini = oDao.getAllOrders();
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
<meta charset="UTF-8">
<title>DressApp - ORDINI UTENTI</title>
<%@ include file="includes/header.jsp"%>
<body>
       <%@ include file="includes/navbar.jsp"%>
		<%if(auth.getIsAdmin()!=1){ %>
		<div class="container">
			<h1>SPIACENTI MA NON PUOI VISUALIZZARE GLI ORDINI DEGLI UTENTI SE NON SEI ADMIN</h1>
		</div>
		<%}else{%>
	
        <div class="container">
        <form align=center class="search" action="AllOrdersServlet" type="POST">
  			<br><input type="text" placeholder="Cerca..." name="search">
  			<button type="submit"><i class="fa fa-search"></i></button>
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
					for(OrdineCompleto ordineCompleto:tuttoTrovato){
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
				</tbody>
				
				
				
			<hr>
			<%		}
				}
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
					for (OrdineCompleto o : tutto) {%>
			
			<tbody>
				<tr>
					<td><%=o.getEmail() %></td>
					<td><%=o.getNomeC() %></td>
					<td><%=o.getCategoria()%></td>
					<td><%=dcf.format(o.getPrezzo())%></td>
					<td><%=o.getQuantita()%></td>
					<td><%=o.getData()%></td>
				
				</tr>
				<% }}%>				
			</tbody>
	</table>
	</div>
	
	<% } %>
	</body>
</html>