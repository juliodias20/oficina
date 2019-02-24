var modelosModulo = angular.module('modelosModulo',['ngCookies']);

modelosModulo.controller("modelosController",function($http, $location, $scope, $rootScope, $cookies){

	//autenticação de login
	$rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray(window.location.href, ['http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/login.html']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	window.location.href="http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/login.html";

        }
        
        
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });
	
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/login.html";	
    };
	
	
	urlMarca = 'http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/rest/marcas'
	urlModelo = 'http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/rest/modelos'

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
		$scope.modelo = {};
		$scope.modelo.codModelo = "";
		$scope.modelo.ano = "";
		$scope.modelo.nomeModelo = "";
		$scope.modelo.qtdPortas = "";
		$scope.modelo.marcaModel = {};
		$scope.modelo.marcaModel.codMarca = "";
	}
	
	function cadastroModelo(){
		if(!$scope.modelo.ano || !$scope.modelo.nomeModelo || 
				!$scope.modelo.qtdPortas || !$scope.modelo.marcaModel.codMarca){
			return false;
		}else{
			return true;
		}
	}
	
	$scope.salvar = function() {
		
		if($scope.modelo.codModelo == undefined || $scope.modelo.codModelo ==""){
			if(cadastroModelo()){
				$http.post(urlModelo,$scope.modelo).success(function(modelo){
					$('#nav-lista-tab').tab('show');
					$scope.chamarModalMensagens('Mensagem!','Modelo de carro cadastrado com sucesso!');
					$scope.limparCampos();
					$scope.listarModelos();
					
				}).error(function(erro){
					alert(erro);
				});
			}else{
				$scope.chamarModalMensagens('Mensagem!','Formulário incompleto, preencha todos os campos obrigatórios do formulário!');
			}
		}else{
			if(cadastroModelo()){
				$http.put(urlModelo,$scope.modelo).success(function(modelo){
					$scope.chamarModalMensagens('Mensagem!','Modelo de carro atualizado com sucesso!');
					$scope.listarModelos();
				}).error(function (erro){
					alert(erro);
				});
			}else{
				$scope.chamarModalMensagens('Mensagem!','Formulário incompleto, preencha todos os campos obrigatórios do formulário!');
			}
		}
	}
	
	$scope.excluir = function(){
		if($scope.modelo.codModelo == undefined || $scope.modelo.codModelo == ""){
			$scope.chamarModalMensagens('Mensagem!','Favor selecionar um registro para excluir!');
		}else{
			if(cadastroModelo())
				$http.delete(urlModelo+'/'+$scope.modelo.codModelo).success(function(){
					$scope.chamarModalMensagens('Mensagem!','Modelo de carro excluído com sucesso!');
					$scope.listarModelos();
					$scope.limparCampos();
					$('#nav-lista-tab').tab('show');
				}).error(function (erro){
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
	
	//executa
	$scope.listarMarcas();
	$scope.listarModelos();

});