var marcasModulo = angular.module('marcasModulo',['ngCookies']);

marcasModulo.controller("marcasController", function ($http, $location, $scope, $rootScope, $cookies){
	
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