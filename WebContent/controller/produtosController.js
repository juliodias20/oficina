var produtosModulo = angular.module('produtosModulo',[]);

produtosModulo.controller("produtosController",function($http, $scope){

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

	$scope.selecionaProduto = function(produtoSelecionado){
		$scope.produto = produtoSelecionado;
	}

	$scope.limparCampos = function(){
		$scope.produto = "";
	}

	$scope.salvar = function() {
		if($scope.produto.codProduto == undefined){console.log($scope.produto);
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