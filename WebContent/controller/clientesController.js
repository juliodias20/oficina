var clientesModulo = angular.module('clientesModulo',['ngCookies']);

clientesModulo.controller("clientesController", function ($http, $location, $scope, $rootScope, $cookies){
	
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
    });
	
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
		$scope.cliente.tipoPessoa="F";
		$scope.cliente.codCliente="";
		$scope.cliente.bairro="";
		$scope.cliente.cep="";
		$scope.cliente.cidade="";
		$scope.cliente.cnpj="";
		$scope.cliente.codCliente="";
		$scope.cliente.cpf="";
		$scope.cliente.email="";
		$scope.cliente.estado="";
		$scope.cliente.logradouro="";
		$scope.cliente.nomeCliente="";
		$scope.cliente.numero="";
		$scope.cliente.razaoSocial="";
		$scope.cliente.telefone="";
	}

	$scope.salvar = function() {
		if($scope.cliente.codCliente == undefined){
			$http.post(urlCliente,$scope.cliente).success(function(cliente){
				console.log($scope.cliente);
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
				alert(erro);
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