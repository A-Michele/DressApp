<nav class="navbar navbar-expand-lg bg-light">
	<div class="container">
		<a class="navbar-brand" href="show-products"><img src="product-images/logo.jpeg" alt="..."></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="show-products">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-2">${ cart_list.size() }</span></a></li>
				<%if(auth.getIsAdmin()==1){ %>
					<li class="nav-item"><a class="nav-link" href="admin.jsp">Ordini Degli Utenti</a></li>
					<li class="nav.item"><a class="nav-link" href="ProdottiAdmin.jsp">Modifica Prodotti</a><li>
					<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
				<%}else{%>
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%}%>
			</ul>
		</div>

	</div>
</nav>