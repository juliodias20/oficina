var estoquesModulo = angular.module('estoquesModulo',['ngCookies']);

estoquesModulo.controller("estoquesController",function($http, $location, $scope, $rootScope, $cookies){
	
	//autenticação de login
	$rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray(window.location.href, ['http://localhost:80/Oficina/login.html']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	window.location.href="http://localhost:80/Oficina/login.html";

        }
        
        
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });    
    
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://localhost:80/Oficina/login.html";	
    };

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
    
	urlProduto = 'http://localhost:80/Oficina/rest/produtos';
	urlEstoque = 'http://localhost:80/Oficina/rest/estoques';
	

	
	$scope.listarEstoques = function(){
		$http.get(urlEstoque).success(function (estoques){
			$scope.estoques = estoques;
		}).error(function (erro){
			alert(erro);
		})
	}
	
	$scope.selecionaEstoque = function(estoqueSelecionado){
		$scope.estoque = estoqueSelecionado;
		document.getElementById('nomeModeloCar').value =  estoqueSelecionado.produtoModel.modeloModel.nomeModelo+' / '+estoqueSelecionado.produtoModel.modeloModel.qtdPortas+'P / '+estoqueSelecionado.produtoModel.modeloModel.ano;
	}
	
	$scope.abrirModalLancarProdutoEstoque = function(){
		$('#lancarProdutosEstoque').modal('show');
		document.getElementById('modalQtdProduto').value = "";
		document.getElementById('modalVlrCompra').value = "";
		document.getElementById('modalVlrVenda').value = "";
	}
	
	$scope.lancarEstoque = function(){
		if(Number(document.getElementById('modalQtdProduto').value) != 0 ||
		   Number(document.getElementById('modalVlrCompra').value) != 0 ||
		   Number(document.getElementById('modalVlrVenda').value) != 0 ){
				
			var v_qtd,v_venda, v_compra;
			
			if(isNaN(removeMoneyMask(document.getElementById('modalQtdProduto').value))){
				v_qtd = 0;
			}else{
				v_qtd = Number(removeMoneyMask(document.getElementById('modalQtdProduto').value))
			}
			
			if(isNaN(removeMoneyMask(document.getElementById('modalVlrCompra').value))){
				v_compra = 0;
			}else{
				v_compra = Number(removeMoneyMask(document.getElementById('modalVlrCompra').value))
			}
			if(isNaN(removeMoneyMask(document.getElementById('modalVlrVenda').value))){
				v_venda = 0;
			}else{
				v_venda = Number(removeMoneyMask(document.getElementById('modalVlrVenda').value))
			}
			
			$scope.estoque.qtdEstoque += v_qtd;
			if(v_compra != 0) {$scope.estoque.vlrCompra = v_compra};
			if(v_venda != 0) {$scope.estoque.vlrVenda = v_venda};
			
			//lança produtos no estoque
			$http.put(urlEstoque,$scope.estoque).success(function(estoque){
				$scope.listarEstoques();
				$scope.chamarModalMensagens('Mensagem!','Estoque atualizado com sucesso!');
			}).error(function (erro){
				alert(erro);
			});
			
			$('#lancarProdutosEstoque').modal('hide');
			
		}else{
			$scope.chamarModalMensagens('Mensagem!','Pelo menos um campo do formulário precisa ser preenchido com valor diferente de zero!');
		}
	}
	
	//função que chama um Modal para apresentar mensagens, recebe de parâmetro um título e uma mensagem
    $scope.chamarModalMensagens = function (vTitulo, vMensagem){
    	$('#modalMensagens').modal('show');
    	document.getElementById('pTitulo').innerHTML = vTitulo;
    	document.getElementById('pMsg').innerHTML = vMensagem;
    	
    }
    
	//função que fecha o modal de mensagem
    $scope.fecharModalMensagens = function(){
    	document.getElementById('pTitulo').innerHTML = "";
    	document.getElementById('pMsg').innerHTML = "";
    	$('#modalMensagens').modal('hide');
    }
	
    $scope.listarEstoques();
    
});