<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="entity.model.*"%>
   
   <% User auth = (User) request.getSession().getAttribute("auth"); %>
   <!-- if (auth != null) {
    	request.setAttribute("auth", auth);
    }else{
    	auth=new User();
    } -->
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="includes/header.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<link rel="icon" href="product-images/logo.jpg" type="image/jpg">  
<title>DressApp - Modifiche Utente</title>
</head>

<body>
<%@ include file="includes/navbar.jsp"%>

<div class="container">
    <%if(auth.getIsGuest()!=0){ %>
		<div class="container">
			<h1>SPIACENTI MA NON PUOI MODIFICARE IL PROFILO DI UN UTENTE SE NON SEI LOGGATO</h1>
			<h3>Ti invitiamo a fare il Login altrimenti dai un'occhiata alla nostra home</h3>
			<a class="mx-3 btn btn-primary" href="login.jsp" style="align:center;background: #404040;border:#404040">Login</a>
			<a class="mx-3 btn btn-primary" href="show-products" style="align:center;background: #404040;border:#404040">Home</a>
			
		</div><br>
		<%}else{%>
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center" style="background: #404040;color:white;">Modifica profilo</div>
            <div class="card-body">
                <form action="ModificaUtenteServlet" method="post" name="ins">

                    <div class="form-group">
                        <table><tr><td>&emsp;&emsp;&emsp;<td><td><label>Email &emsp;&emsp;&nbsp;&nbsp;</label></td><td>
                        <input type="text" class="form-control" name="email" placeholder="Inserisci E-mail">
                        <input type="hidden" name="auth_id" value="<%= auth.getId() %>"></td><td>&emsp;<td></td><td>
                        <button type="submit" class="btn btn-primary" name="modificaEmail" style="background: #404040;border:#404040">Modifica</button>
                    	<td></tr></table>
                    </div><br>
                    <div class="form-group">
                        <table><tr><td>&emsp;&emsp;&emsp;<td><td><label>Password&emsp;</label></td><td>
                        <input type="text" class="form-control" name="password" placeholder="Inserisci Password">
                        <input type="hidden" name="auth_id" value="<%= auth.getId() %>"></td><td>&emsp;<td></td><td>
                        <button type="submit" class="btn btn-primary" name="modificaPassword" style="background: #404040;border:#404040">Modifica</button>
                    	<td></tr></table>
                    </div><br>
                </form>
                 <form action="CancellaProfiloServlet" method="post" name="delete">
                    <input type="hidden" name="auth_id" value="<%= auth.getId() %>">
                    
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" name="cancellaProfilo" style="background: #404040;border:#404040">Cancella Profilo</button>
                    </div>
                </form>
            </div>
        </div><% } %>
    </div><br><br><br><br><br>
<%@ include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
