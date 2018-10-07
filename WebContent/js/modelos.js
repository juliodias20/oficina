/*Função que chama a outra aba*/
function chamaAba() {
	$(document).ready(function() {
		$(".chamaAba").dblclick(function() {
			$('.nav-tabs a[href="#nav-cadastro"]').tab('show');
		});
	});
}
