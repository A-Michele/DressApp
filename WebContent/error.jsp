<%@ page language="java" isErrorPage="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DressApp - Errore</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
  		<h2>Errore individuato, ci scusiamo per il disagio, clicchi sul link per tornare alla home</h2> 
		
		<hr> 
	
	<br><br>
	
	<a href="<%= response.encodeURL("show-products") %>">
		<button >HOME</button>
		</a>
	
</body>
</html>