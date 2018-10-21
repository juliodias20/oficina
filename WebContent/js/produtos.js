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

