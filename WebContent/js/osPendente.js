/*Função que chama a outra aba*/
function chamaAba() {
	$(document).ready(function() {
		$(".chamaAba").dblclick(function() {
			$('.nav-tabs a[href="#nav-cadastro"]').tab('show');
		});
	});
}

/*mascara de dinheiro*/
$(document).ready(function(){
	$(".money").maskMoney({
	    prefix: "R$ ",
	    decimal: ",",
	    thousands: "."
	});
});

/*função remove mascara de dinheiro*/
function removeMoneyMask(valor){
	var valorSemMascara = 0.0;
	var aux = valor;
	
	for (i=0 ; i<5 ; i++){
		aux = aux.replace('.','');
	}
	aux = aux.replace(',','.');
	aux = aux.replace('R$ ','');
	valorSemMascara = parseFloat(aux);
	
	return valorSemMascara;
}