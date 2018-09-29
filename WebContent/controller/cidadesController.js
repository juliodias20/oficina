var cidadesModulo = angular.module('cidadesModulo',[]);

cidadesModulo.controller("cidadesController", function ($http, $scope){
	
	urlCidade = 'http://localhost:8080/Oficina/rest/cidades'
	urlEstado = 'http://localhost:8080/Oficina/rest/estados';
	
	$scope.listarCidades = function(){
		$http.get(urlCidade).success(function (cidades){
			$scope.cidades = cidades;
		}).error(function (erro){
			alert(erro);
		})
		
	}
	
	$scope.listarEstados = function (){
		$http.get(urlEstado).success(function (estados){
			$scope.estados = estados;
		}).error(function (erro){
			alert(erro);
		});
	}

	$scope.selecionaCidade = function(cidadeSelecionado){
		$scope.cidade = cidadeSelecionado;
	}

	$scope.limparCampos = function(){
		$scope.cidade = "";
	}

	$scope.salvar = function() {
		
		if($scope.cidade.codigo == undefined){
			if($scope.cidade.estadoModel != undefined && $scope.cidade.nomeCidade != undefined){
				$http.post(urlCidade,$scope.cidade).success(function(cidade){
					$scope.cidades.push($scope.cidade);
					$scope.limparCampos();
					$scope.listarCidades();
				}).error(function(erro){
					alert(erro);
				});
			}
		}else{
			if($scope.cidade.estadoModel != undefined){
				$http.put(urlCidade,$scope.cidade).success(function(cidade){
					$scope.listarCidades();
					$scope.limparCampos();
				}).error(function (erro){
					alert(erro);
				});
			}
		}
	}

	$scope.excluir = function(){
		if($scope.cidade.codigo== undefined){
			alert("Favor selecionar um registro para excluir!");
			console.log("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlCidade+'/'+$scope.cidade.codigo).success(function(){
				$scope.listarCidades();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}


	//executa
	$scope.listarEstados();
	$scope.listarCidades();

});