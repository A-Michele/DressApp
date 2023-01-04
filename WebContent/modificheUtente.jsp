<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="entity.model.*"%>
   
   <% User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
    	request.setAttribute("auth", auth);
    }else{
    	auth=new User();
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifiche Utente</title>
</head>

<body>
<!-- include file="includes/navbar.jsp" -->

<div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Modifica</div>
            <div class="card-body">
                <form action="ModificaUtenteServlet" method="post" name="ins">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" class="form-control" name="email" placeholder="Inserisci E-mail">
                        <input type="hidden" name="auth_id" value="<%= auth.getId() %>">
                        <button type="submit" class="btn btn-primary" name="modificaEmail">Modifica</button>
                    </div>
                    <div class="form-group">
                        <label>Password</label> 
                        <input type="text" class="form-control" name="password" placeholder="Inserisci Password">
                        <input type="hidden" name="auth_id" value="<%= auth.getId() %>">
                        <button type="submit" class="btn btn-primary" name="modificaPassword">Modifica</button>
                    </div>
                </form>
                 <form action="CancellaProfiloServlet" method="post" name="delete">
                    <input type="hidden" name="auth_email" value="<%= auth.getEmail() %>">
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" name="cancellaProfilo">Cancella Profilo</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</body>

</html>