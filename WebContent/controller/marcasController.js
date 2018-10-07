var marcasModulo = angular.module('marcasModulo',[]);

marcasModulo.controller("marcasController", function ($http, $scope){
	
	urlMarca = 'http://localhost:8080/Oficina/rest/marcas';
	$scope.listarMarcas = function (){
		$http.get(urlMarca).success(function (marcas){
			$scope.marcas = marcas;
		}).error(function (erro){
			alert(erro);
		});
	};

	$scope.selecionaMarca = function(marcaSelacionada){
		$scope.marca = marcaSelacionada;
	}

	$scope.limparCampos = function(){
		$scope.marca = "";
	}

	$scope.salvar = function() {
		if($scope.marca.codMarca == undefined){
			$http.post(urlMarca,$scope.marca).success(function(marca){
				$scope.limparCampos();
				$scope.listarMarcas();
			}).error(function(erro){
				alert(erro);
			});
		}else{
			$http.put(urlMarca,$scope.marca).success(function(marca){
				$scope.listarMarcas();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}

	$scope.excluir = function(){
		if($scope.marca.codMarca == undefined){
			alert("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlMarca+'/'+$scope.marca.codMarca).success(function(){
				$scope.listarMarcas();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}	


	//executa
	$scope.listarMarcas();

});