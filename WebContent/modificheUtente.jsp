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
<title>Modifiche Utente</title>
</head>

<body>
<%@ include file="includes/navbar.jsp"%>

<div class="container">
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
        </div>
    </div><br><br><br><br><br>
<%@ include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
