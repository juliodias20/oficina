var bairrosModulo = angular.module('bairrosModulo',[]);

bairrosModulo.controller("bairrosController", function ($http, $scope){
	
	urlBairro = 'http://localhost:8080/Oficina/rest/bairros';
	urlCidade = 'http://localhost:8080/Oficina/rest/cidades';
	
	$scope.listarBairros = function (){
		$http.get(urlBairro).success(function (bairros){
			$scope.bairros = bairros;
		}).error(function (erro){
			alert(erro);
		});
	}
	
	$scope.listarCidades = function(){
		$http.get(urlCidade).success(function (cidades){
			$scope.cidades = cidades;
		}).error(function (erro){
			alert(erro);
		})
		
	}
	
	$scope.selecionaBairro = function(bairroSelecionado){
		$scope.bairro = bairroSelecionado;
	}

	$scope.limparCampos = function(){
		$scope.bairro = "";
	}

	$scope.salvar = function() {
		
		if($scope.bairro.codBairro == undefined){
			if($scope.bairro.cidadeModel != undefined && $scope.bairro.nomeBairro != undefined){
				$http.post(urlBairro,$scope.bairro).success(function(bairro){
					$scope.bairros.push($scope.bairro);
					$scope.limparCampos();
					$scope.listarBairros();
				}).error(function(erro){
					alert(erro);
				});
			}
		}else{
			if($scope.cidade.estadoModel != undefined){
				$http.put(urlBairro,$scope.bairro).success(function(bairro){
					$scope.listarBairros();
					$scope.limparCampos();
				}).error(function (erro){
					alert(erro);
				});
			}
		}
	}

	$scope.excluir = function(){
		if($scope.bairro.codBairro == undefined){
			alert("Favor selecionar um registro para excluir!");
			console.log("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlBairro+'/'+$scope.bairro.codBairro).success(function(){
				$scope.listarBairros();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}


	//executa
	$scope.listarCidades();
	$scope.listarBairros();

});