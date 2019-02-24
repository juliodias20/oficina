/*Função que chama a outra aba*/
function chamaAba() {
	$(document).ready(function() {
		$(".chamaAba").dblclick(function() {
			$('.nav-tabs a[href="#nav-cadastro"]').tab('show');
		});
	});
}


function carregaDados(){
	document.getElementById('modalCodProduto').value = document.getElementById('codProduto').value;
	document.getElementById('modalNomeProduto').value = document.getElementById('nomeProduto').value;
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

/*função para mascara do campo PORTA*/
function trataCampoPorta(p){
	var porta;
		if(p == 'D') {porta = 'Direita (2p)'}
		if(p == 'E') {porta = 'Esquerda (2p)'}
		if(p == 'DE') {porta = 'Dianteira esquerda (4p)'}
		if(p == 'DD') {porta = 'Dianteira direita (4p)'}
		if(p == 'TE') {porta = 'Traseira esquerda (4p)'}
		if(p == 'TD') {porta = 'Traseira direita (4p)'}
		if(p == 'PM') {porta = 'Porta-malas'}
		if(p == 'U') {porta = 'Universal'}
	
	return porta;
}

