var produtosModulo = angular.module('produtosModulo',['ngCookies']);

produtosModulo.controller("produtosController",function($http, $location, $scope, $rootScope, $cookies){
	
	//autenticação de login
	$rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray(window.location.href, ['http://localhost:8080/Oficina/login.html']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	window.location.href="http://localhost:8080/Oficina/login.html";

        }
        
        
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });
	
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://localhost:8080/Oficina/login.html";	
    };
	
	urlModelo = 'http://localhost:8080/Oficina/rest/modelos'
	urlProduto = 'http://localhost:8080/Oficina/rest/produtos'
		
	$scope.listarModelos = function(){
		$http.get(urlModelo).success(function (modelos){
			$scope.modelos = modelos;
		}).error(function (erro){
			alert(erro);
		})
	}
	
	$scope.listarProdutos = function(){
		$http.get(urlProduto).success(function (produtos){
			$scope.produtos = produtos;
		}).error(function (erro){
			alert(erro);
		})
	}
	
	$scope.getModelo = function(){
		$http.get(urlModelo+'/'+$scope.pesquisaCarro.codModelo).success(function (modelo){
			console.log(modelo);
		}).error(function (erro){
			alert(erro);
		})	
	}
	
	$scope.abrirModalLancarProdutoEstoque = function(){
		$('#lancarProdutosEstoque').modal('show');
	}
	
	$scope.pesquisaCarro = function(modeloSelecionado){
		$scope.produto.modeloModel = modeloSelecionado;
		document.getElementById('nomeModeloCar').value =  modeloSelecionado.nomeModelo+' / '+modeloSelecionado.qtdPortas+'P / '+modeloSelecionado.ano;
	}
    
	$scope.selecionaProduto = function(produtoSelecionado){
		$scope.produto = produtoSelecionado;
		document.getElementById('nomeModeloCar').value =  produtoSelecionado.modeloModel.nomeModelo+' / '+produtoSelecionado.modeloModel.qtdPortas+'P / '+produtoSelecionado.modeloModel.ano;
	}
	
	$scope.lancaEstoque = function(){
		if(Number(document.getElementById('modalQtdProduto').value) <= 0){
			alert("\'Quantidade\' precisa ser maior que zero.");
			//limpa campos do modal
			document.getElementById('modalQtdProduto').value = null;
			document.getElementById('modalVlrPago').value = null;
			document.getElementById('modalVlrVenda').value = null;
			return false;
		}else {
			
			//atualiza formulário
			$scope.produto.qtdEstoque = $scope.produto.qtdEstoque + Number(document.getElementById('modalQtdProduto').value);
			if(Number(document.getElementById('modalVlrPago').value) != 0){
				$scope.produto.vlrPago = Number(document.getElementById('modalVlrPago').value);
			}
			if(Number(document.getElementById('modalVlrVenda').value) != 0){
				$scope.produto.vlrVenda = Number(document.getElementById('modalVlrVenda').value);
			}
			
			
			//limpa campos do modal
			document.getElementById('modalQtdProduto').value = null;
			document.getElementById('modalVlrPago').value = null;
			document.getElementById('modalVlrVenda').value = null
			
			//lança produtos no estoque
			$http.put(urlProduto,$scope.produto).success(function(produto){
				$scope.listarProdutos();
				alert('Produtos inseritos no estoque.')
			}).error(function (erro){
				alert(erro);
			});
			
			$('#lancarProdutosEstoque').modal('hide');
			
		}
		
	}
	
	$scope.teste = function(){
		console.log($scope.produto);
	}
	
	$scope.limparCampos = function(){
			$scope.produto.tipoProduto="N";
			$scope.produto.codProduto="";
			$scope.produto.modeloModel="";
			$scope.produto.nomeProduto="";
			$scope.produto.observacao="";
			$scope.produto.porta="";
			$scope.produto.qtdEstoque="";
			$scope.produto.vlrPago="";
			$scope.produto.vlrVenda="";
			document.getElementById('nomeModeloCar').value = "";
		
	}
	
	$scope.salvar = function() {
		if($scope.produto.codProduto == undefined){
			if($scope.produto.qtdEstoque == ""){
				$scope.produto.qtdEstoque=0;
			}
			$http.post(urlProduto,$scope.produto).success(function(produto){
				$scope.limparCampos();
				$scope.listarProdutos();
			}).error(function(erro){
				alert(erro);
			});			
		}else{
			$http.put(urlProduto,$scope.produto).success(function(produto){
				$scope.listarProdutos();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}
	
	$scope.excluir = function(){
		if($scope.produto.codProduto == undefined){
			alert("Favor selecionar um registro para excluir!");
			console.log("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlProduto+'/'+$scope.produto.codProduto).success(function(){
				$scope.listarProdutos();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}
	
	//executa
	$scope.listarModelos();
	$scope.listarProdutos();

});