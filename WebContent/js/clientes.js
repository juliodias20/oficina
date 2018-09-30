/*Função que chama a outra aba*/
function chamaAba() {
	$(document).ready(function() {
		$(".chamaAba").dblclick(function() {
			$('.nav-tabs a[href="#nav-cadastro"]').tab('show');
		});
	});
}


/*Funções que controlam campos CPF-CNPJ*/
$(document).ready(function(){
	$('.cnpj').hide();	
});

$(function(){
	$('.pessoa').click(function(){
		if($(this).val() == 'F'){
			$('.cnpj').hide();
			$('.cpf').show();
		}else if($(this).val() == 'J'){
			$('.cnpj').show();
			$('.cpf').hide();
		}
	})
});