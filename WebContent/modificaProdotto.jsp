<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="entity.model.*"%>
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
    	request.setAttribute("auth", auth);
    }else{
    	auth=new User();
    }

    Cappello p=(Cappello)request.getAttribute("item");
    System.out.println(p.getNome());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica un prodotto</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
<%@ include file="includes/navbar.jsp"%>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Modifica</div>
            <div class="card-body">
                <form action="edit-product" method="post" name="ins">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" class="form-control" name="nomeP" placeholder="<%=p.getNome()%>"required>
                    </div>
                    <div class="form-group">
                        <label>Descrizione</label> <input type="text" class="form-control" name="descP" placeholder="<%=p.getDescrizione()%>"required>
                    </div>
                    <div class="form-group">
                        <label>Costo</label> <input type=text class="form-control" name="costo" placeholder="<%=p.getPrezzo()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Categoria</label> <input type=text class="form-control" name="cat" placeholder="<%=p.getCategoria()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Foto</label> <input type=text class="form-control" name="foto" placeholder="<%=p.getImg()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Disponibilità</label> <input type=text class="form-control" name="disp" placeholder="<%=p.getDisp()%>" required>
                    </div>
                    <input type="hidden" name="p_id" value="<%= p.getId() %>">
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>