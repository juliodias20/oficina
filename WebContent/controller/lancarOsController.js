var lancarOsModulo = angular.module('lancarOsModulo',['ngCookies']);

lancarOsModulo.controller("lancarOsController", function ($http, $location, $scope, $rootScope, $cookies){
	
	//--------------------------------------------------------autenticação de login
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
    //--------------------------------------------------------autenticação de login
    
    urlModelo = 'http://localhost:8080/Oficina/rest/modelos'
    urlCliente = 'http://localhost:8080/Oficina/rest/clientes';
    urlOs = 'http://localhost:8080/Oficina/rest/os';
    	
    $scope.listarModelos = function(){
		$http.get(urlModelo).success(function (modelos){
			$scope.modelos = modelos;
		}).error(function (erro){
			alert(erro);
		})
	}
    
    $scope.listarClientes = function (){
		$http.get(urlCliente).success(function (clientes){
			$scope.clientes = clientes;
		}).error(function (erro){
			alert(erro);
		});
	};
    
    $scope.limparCampos = function(){
    	$scope.lancaros = {};
    	$scope.lancaros.modeloModel = {};
    	$scope.lancaros.modeloModel.codModelo = "";
    	$scope.lancaros.clienteModel = {};
    	$scope.lancaros.clienteModel.codCliente = "";
    	$scope.lancaros.placaCarro = "";
    	$scope.lancaros.dhAbertura = "";
    	$scope.lancaros.status = "";
    	$scope.lancaros.tipoOs = "";
    	document.getElementById('nomeModeloCar').value = "";
    	document.getElementById('cliente').value = "";
    }
    
    $scope.pesquisaCarro = function(modeloSelecionado){
		$scope.lancaros.modeloModel = modeloSelecionado;
		document.getElementById('nomeModeloCar').value =  modeloSelecionado.nomeModelo+' / '+modeloSelecionado.qtdPortas+'P / '+modeloSelecionado.ano;
	}
    
    $scope.buscaCliente = function(clienteSelecionado){
		$scope.lancaros.clienteModel = clienteSelecionado;
		document.getElementById('cliente').value =  clienteSelecionado.nomeCliente;
	}
    
    
    $scope.salvar = function() {
    	if($scope.lancaros.clienteModel.codCliente == ""){
    		$scope.chamarModalMensagens("Erro!","Para lançar uma OS é necessário preencher todos os campos!");
    	}else{
    	
	    	$scope.lancaros.dhAbertura = new Date().getTime();
	    	$scope.lancaros.status = "PENDENTE";
	    	
			$http.post(urlOs,$scope.lancaros).success(function(os){
				alert("Ordem de Serviço lançada com sucesso!");
				$scope.limparCampos()
			}).error(function(erro){
				alert(erro);
			});			
    	}
    }
	   
    
    $scope.testar = function(){
    	document.getElementById('pTitulo').innerHTML = 'Erro';
    	document.getElementById('pMsg').innerHTML = "Erro do sistema, TODO MUNDO EM PÂNICO!";
    	$('#modalMensagens').modal('show');
    }
    
    $scope.fecharModal = function(){
    	document.getElementById('pTitulo').innerHTML = "";
    	document.getElementById('pMsg').innerHTML = "";
    	$('#modalMensagens').modal('hide');
    }
    
    $scope.chamarModalMensagens = function(vTitulo, vMensagem){
    	document.getElementById('pTitulo').innerHTML = vTitulo;
    	document.getElementById('pMsg').innerHTML = vMensagem;
    	$('#modalMensagens').modal('show');
    }
    
    $scope.limparCampos();
    
});