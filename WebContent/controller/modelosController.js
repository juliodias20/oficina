var modelosModulo = angular.module('modelosModulo',[]);

modelosModulo.controller("modelosController",function($http, $scope){

	urlMarca = 'http://localhost:8080/Oficina/rest/marcas'
	urlModelo = 'http://localhost:8080/Oficina/rest/modelos'

	$scope.listarMarcas = function(){
		$http.get(urlMarca).success(function (marcas){
			$scope.marcas = marcas;
		}).error(function (erro){
			alert(erro);
		})
	}

	$scope.listarModelos = function(){
		$http.get(urlModelo).success(function (modelos){
			$scope.modelos = modelos;
		}).error(function (erro){
			alert(erro);
		})
	}
	
	$scope.selecionaModelo = function(modeloSelecionado){
		$scope.modelo = modeloSelecionado;
	}

	$scope.limparCampos = function(){
		$scope.modelo = "";
	}

	$scope.salvar = function() {
		if($scope.modelo.codModelo == undefined){
			$http.post(urlModelo,$scope.modelo).success(function(modelo){
				$scope.limparCampos();
				$scope.listarModelos();
			}).error(function(erro){
				console.log($scope.modelo);
				console.log(erro);
				alert(erro);
			});			
		}else{
			if($scope.modelo.marcaModel != undefined){
				$http.put(urlModelo,$scope.modelo).success(function(modelo){
					$scope.listarModelos();
					$scope.limparCampos();
				}).error(function (erro){
					alert(erro);
				});
			}
		}
	}
	
	$scope.excluir = function(){
		if($scope.modelo.codModelo == undefined){
			alert("Favor selecionar um registro para excluir!");
			console.log("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlModelo+'/'+$scope.modelo.codModelo).success(function(){
				$scope.listarModelos();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}
	
	//executa
	$scope.listarMarcas();
	$scope.listarModelos();

});