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
    
    function cadastroOs(){
    	if(!$scope.lancaros.modeloModel.codModelo || !$scope.lancaros.clienteModel.codCliente ||
    	   !$scope.lancaros.placaCarro || !$scope.lancaros.tipoOs || !$scope.lancaros.descricao){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    $scope.teste = function(){
   
    	console.log($scope.lancaros);
    }
    
    //função que chama um Modal para apresentar mensagens, recebe de parâmetro um título e uma mensagem
    $scope.chamarModalMensagens = function(vTitulo, vMensagem){
    	document.getElementById('pTitulo').innerHTML = vTitulo;
    	document.getElementById('pMsg').innerHTML = vMensagem;
    	$('#modalMensagens').modal('show');
    }
    
    //função que fecha o modal de mensagem
    $scope.fecharModal = function(){
    	document.getElementById('pTitulo').innerHTML = "";
    	document.getElementById('pMsg').innerHTML = "";
    	$('#modalMensagens').modal('hide');
    }
    
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
    	$scope.lancaros.descricao ="";
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
    	if(!cadastroOs()){
    		$scope.chamarModalMensagens("Erro!","Para lançar uma OS é necessário preencher todos os campos!");
    	}else{
    	
	    	$scope.lancaros.dhAbertura = new Date().getTime();
	    	$scope.lancaros.status = "PENDENTE";
	    	
			$http.post(urlOs,$scope.lancaros).success(function(os){
				$scope.chamarModalMensagens('Mensagem','Ordem de Serviço lançada com sucesso!');
				$scope.limparCampos();
				window.location.href="http://localhost:8080/Oficina/ospendente.html";
			}).error(function(erro){
				alert(erro);
			});			
    	}
    }
	   
    //função que chama um Modal para apresentar mensagens, recebe de parâmetro um título e uma mensagem
    $scope.chamarModalMensagens = function (vTitulo, vMensagem){
    	$('#modalMensagens').modal('show');
    	document.getElementById('pTitulo').innerHTML = vTitulo;
    	document.getElementById('pMsg').innerHTML = vMensagem;
    	
    }
    
    //função que fecha o modal de mensagem
    $scope.fecharModalMensagens = function(){  	
    	$('#modalMensagens').modal('hide');
    	
    }
    
    
    $scope.limparCampos();
    
});