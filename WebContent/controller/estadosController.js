var estadosModulo = angular.module('estadosModulo',[]);

estadosModulo.controller("estadosController", function ($http, $scope){
	
	urlEstado = 'http://localhost:8080/Oficina/rest/estados';
	$scope.listarEstados = function (){
		console.log('ListandoEstados');
		$http.get(urlEstado).success(function (estados){
			$scope.estados = estados;
		}).error(function (erro){
			alert(erro);
		});
	};

	$scope.selecionaEstado = function(estadoSelecionado){
		console.log('Selecionando estado');
		$scope.estado = estadoSelecionado;
	}

	$scope.limparCampos = function(){
		$scope.estado = "";
	}

	$scope.salvar = function() {
		if($scope.estado.codigo == undefined){
			console.log('Inserindo novo estado');
			$http.post(urlEstado,$scope.estado).success(function(estado){
				/*$scope.estados.push($scope.estado);*/
				$scope.limparCampos();
				$scope.listarEstados();
			}).error(function(erro){
				alert(erro);
			});
		}else{
			console.log('atualizando estado');
			$http.put(urlEstado,$scope.estado).success(function(estado){
				$scope.listarEstados();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}

	$scope.excluir = function(){
		if($scope.estado.codigo == undefined){
			alert("Favor selecionar um registro para excluir!");
			console.log("Favor selecionar um registro para excluir!");
		}else{
			console.log('Excluindo estado.');
			$http.delete(urlEstado+'/'+$scope.estado.codigo).success(function(){
				$scope.listarEstados();
				$scope.limparCampos();
			}).error(function (erro){
				alerto(erro);
			});
		}
	}


	//executa
	$scope.listarEstados();

});