var clientesModulo = angular.module('clientesModulo',[]);

clientesModulo.controller("clientesController", function ($http, $scope){
	
	urlCep = 'https://viacep.com.br/ws/';
	
	urlCliente = 'http://localhost:8080/Oficina/rest/clientes';
	
	$scope.listarClientes = function (){
		$http.get(urlCliente).success(function (clientes){
			$scope.clientes = clientes;
		}).error(function (erro){
			alert(erro);
		});
	};

	$scope.selecionaCliente = function(clienteSelecionado){
		$scope.cliente = clienteSelecionado;
		if($scope.cliente.tipoPessoa == 'F'){
			$('.cnpj').hide();
			$('.cpf').show();
		}else{
			$('.cnpj').show();
			$('.cpf').hide();
		}
	}

	$scope.limparCampos = function(){
		$scope.cliente = "";
	}

	$scope.salvar = function() {
		if($scope.cliente.codCliente == undefined){
			$http.post(urlCliente,$scope.cliente).success(function(cliente){
				$scope.limparCampos();
				$scope.listarClientes();
			}).error(function(erro){
				console.log($scope.cliente);
				alert(erro);
			});
		}else{
			$http.put(urlCliente,$scope.cliente).success(function(cliente){
				$scope.listarClientes();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}

	$scope.excluir = function(){
		if($scope.cliente.codCliente == undefined){
			alert("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlCliente+'/'+$scope.cliente.codCliente).success(function(){
				$scope.listarClientes();
				$scope.limparCampos();
			}).error(function (erro){
				alerto(erro);
			});
		}
	}

	$scope.buscaCEP = function(){
		$http.get(urlCep+'/'+$scope.cliente.cep+'/json').success(function(cep){
			$scope.cliente.logradouro = cep.logradouro;
			$scope.cliente.cidade = cep.localidade;
			$scope.cliente.estado = cep.uf;
			$scope.cliente.bairro = cep.bairro;
		}).error(function (){
			console.log('deu ruim');
		})
	}
	
	
	//executa
	$scope.listarClientes();

});