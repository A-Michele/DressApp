<%@page import="entity.model.*"%>

<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="show-products"><img src="product-images/logo.jpeg" alt="..."></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="show-products">Home</a></li>
					
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge bage-danger px-2">${ cart_list.size() }</span></a></li>
				<%User auth1=(User)request.getSession().getAttribute("auth");
				  if(auth1==null){%>
					  <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%}else{
					if(auth1.getIsAdmin()==1){ %>
					
					<li class="nav-item"><a class="nav-link" href="admin.jsp">Ordini Degli Utenti</a></li>
					<li class="nav-item"><a class="nav-link" href="ProdottiAdmin.jsp">Modifica Prodotti</a><li>
					
					
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					Ciao <%=auth1.getName()%></a>
					
					<ul class="dropdown-menu">
            		<li><a class="dropdown-item" href="modificheUtente.jsp">Modifica Profilo</a></li>
            		<li><a class="dropdown-item" href="log-out">Logout</a></li>
          			</ul>
          			
					</li>
					
				<%}else if(auth1.getIsGuest()==0){%>
					<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
					
					 <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					Ciao <%=auth1.getName()%></a>
					
					<ul class="dropdown-menu">
            		<li><a class="dropdown-item" href="modificheUtente.jsp">Modifica Profilo</a></li>
            		<li><a class="dropdown-item" href="log-out">Logout</a></li>
          			</ul>
          			
					</li>
				<%}else{%>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%}}%>
			</ul>
		</div>

	</div>
</nav>