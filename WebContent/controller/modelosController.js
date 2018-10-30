var modelosModulo = angular.module('modelosModulo',['ngCookies']);

modelosModulo.controller("modelosController",function($http, $location, $scope, $rootScope, $cookies){

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
        
        
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });
	
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://localhost:8080/Oficina/login.html";	
    };
	
	
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