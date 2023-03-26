

$(document).ready(function(){
	
	$("#submitReg").click(function(){
	$('#erroreEmail2').remove();
	$('#errorePassword2').remove();
	$('#errorenome').remove();
	$('#errorecognome').remove();
		
	var allletters=/^[a-zA-Z]+$/;
	var letters = /^[0-9a-zA-Z]+$/;	
	var expEm= /^([a-z1-9.-])*@([a-z])+(.com)$/;
	var email= $('#email2').val();
	var password= $('#password2').val();
	var passlen= password.length;
	
	var nome1= $('#nomerec').val();
	var nome= nome1.replace(/ /g,'');
	var cognome= $('#cognorec').val();	
	var controlli = true;
		if(!(nome.match(allletters)) ){
			controlli=false;
			$('#nomerec').after("<b><p id=errorenome style='color: red;'>Errore nell'inserimento del nome</p></b>");
		}
		if(!(cognome.match(allletters))){
			controlli=false;
			$('#cognorec').after("<b><p id=errorecognome style='color: red;'>Errore nell'inserimento del cognome</p></b>");
		}
		if (!(email.match(expEm)) ){
			controlli=false;
			$('#email2').after("<b><p id=erroreEmail2 style='color: red;'>Errore nell'inserimento dell'email</p></b>");
		}
		if(!(password.match(letters)) || (passlen<5 || passlen>15)){
			controlli=false;
			$('#password2').after("<b><p id=errorePassword2 style='color: red;'>Errore nell'inserimento della password</p></b>");
		}
		
		if(email.match(expEm)){
			$('#erroreEmail2').remove();
		}
		if(password.match(letters) && passlen>4 && passlen<15){
			$('#errorePassword2').remove();
		}
		if(cognome.match(allletters)){
			$('#errorecognome').remove();
		}
		if(nome.match(allletters)){
			$('#errorenome').remove();
		}
					
		if (controlli){
			
			$.ajax({
				type: 'POST',
				data: {email: email, password: password, nome:nome, cognome:cognome},
				url: 'rec-servlet',
				success: function(result){
					if(result=="Error" ){
						$('#email2').after("<b><p id=erroreUtente style='color: red;'>Errore Mail gia esistente</p></b>");		
					}
					if(result!="Error"){
						$('#erroreUtente').remove();
					}
					if (result == "OK"){
						alert("utente registrato");
						window.location.href="login.jsp";
					}
				}
			})
		}
	});
	
	
	
	$('#submitlogin').click(function(){
		
		$('#erroreEmail').remove();
		$('#errorePassword').remove();
		var email= $('#email1').val();
		var password= $('#password1').val();
		var letters = /^[0-9a-zA-Z]+$/;	
		var expEm= /^([a-z1-9.-])*@([a-z])+(.com)$/;
		var passlen= password.length;
		var control= true;
		if (!(email.match(expEm)) ){
			control=false;
			$('#email1').after("<b><p id=erroreEmail style='color: red;'>Errore nell'inserimento dell'email</p></b>");
		}
		if(!(password.match(letters)) || passlen<5 || passlen>15){
			control=false;
			$('#password1').after("<b><p id=errorePassword style='color: red;'>Errore nell'inserimento della password</p></b>");
		}
		if(email.match(expEm))
			$('#erroreEmail').remove();
			
		if((password.match(letters)) && passlen>4 && passlen<15)
			$('#errorePassword').remove();
		
		if (control){
			$.ajax({
				type: 'POST',
				data: {email: email, password: password},
				url: 'user-login',
				success: function(result){
					if(result=="Nulla"){
						$('#email1').after("<b><p id=erroreUtente2 style='color: red;'>Errore, Utente non trovato</p></b> <br>");		
					}
					
					if(result!="Nulla")
						$('#erroreUtente2').remove();
					
					if (result == "LoginOk"){
						window.location.href="show-products";
					}
				}
			})
		}
	})
	
	$('#addCard').click(function(){
	
	$('#erroreProprietario').remove();
	$('#erroreData').remove();
	$('#erroreCVV').remove();
	$('#erroreNumeroCarta').remove();
	var letters = /^[a-zA-Z]+$/;
	var numbers = /^\d+$/;
	var formatoData = /^(0?[1-9]|1[012])[/]((20)[0-9]{2})+$/;
	
	var prop1 = $('#prop').val();
	var prop= prop1.replace(/ /g,'');
	var numero = $('#numCarta').val();
	
	var dataScadenza = $('#dataScadenza').val();
	var cvv = $('#cvv').val();
	var control= true;
	if(!(dataScadenza.match(formatoData))){
		control=false;
		$('#dataScadenza').after("<b><p id=erroreData style='color: red;'>Errore nell'inserimento della data</p></b>");
	}
	if (!(prop.match(letters)) ){
			control=false;
			$('#prop').after("<b><p id=erroreProprietario style='color: red;'>Errore nell'inserimento del proprietario</p></b>");
		}
	if (!(numero.match(numbers))){
			control=false;
			$('#numCarta').after("<b><p id=erroreNumeroCarta style='color: red;'>Errore nell'inserimento del numero della carta</p></b>");
		}
	if (!(cvv.match(numbers)) || cvv.length != 3 ){
			control=false;
			$('#cvv').after("<b><p id=erroreCVV style='color: red;'>Errore nell'inserimento del cvv</p></b>");
		}
	
	if(prop.match(letters))
		$('#erroreProprietario').remove();
	if(numero.match(numbers))
		$('#erroreNumeroCarta').remove();
	if(cvv.match(numbers) && cvv.length == 3)
		$('#erroreCVV').remove();
		
	if(dataScadenza.match(formatoData))
		$('#erroreData').remove();
		
		
	if (control){
			$.ajax({
				type: 'POST',
				data: {proprietario: prop, num: numero, cvv: cvv, scadenza: dataScadenza },
				url: 'payment',
				success: function(result){
					if(result=="Error"){
						$('#cvv').after("<b><p id=errorecard style='color: red;'>Errore, La carta risulta esistente</p></b> <br>");		
					}
					
					if(result!="Error")
						$('#errorecard').remove();
					
					if (result == "Registrato"){
						alert("Metodo di pagamento aggiunto");
						window.location.href="sceltaPagamento.jsp";
					}
				}
			})
		}
	})
	
	
	
	
})



