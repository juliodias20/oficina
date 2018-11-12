var usuariosModulo = angular.module('usuariosModulo',['ngCookies']);

usuariosModulo.controller("usuariosController", function ($http, $location, $scope, $rootScope, $cookies){
	
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

	urlUsuario = 'http://localhost:8080/Oficina/rest/usuarios';
	urlPerfil = 'http://localhost:8080/Oficina/rest/perfis';
	
	function unixToDate(unixDate){
		var data = new Date(unixDate);
		var dia = data.getDate();
	    var mes = data.getMonth() + 1;
	    var ano = data.getFullYear();
	    var hora = data.getHours();
	    var minuto = data.getMinutes();
		
	    if (hora == '0'){hora = '00'};
	    if (minuto == '0'){minuto = '00'};
	    
	    var dataConvertida = dia+'/'+mes+'/'+ano+' - '+hora+':'+minuto;
		
		return dataConvertida;
	}
	
	$scope.listarUsuarios = function (){
		$http.get(urlUsuario).success(function (usuarios){
			/* isso faz com que seja possivel filtrar pela data  no campinho de busca
			for(var i = 0 ; i < usuarios.length ; i++ ){
				usuarios[i].dtCriacao = unixToDate(usuarios[i].dtCriacao);
			}*/
			$scope.usuarios = usuarios;
		}).error(function (erro){
			alert(erro);
		});
	};
	
	$scope.listarPerfis = function (){
		$http.get(urlPerfil).success(function (perfis){
			$scope.perfis = perfis;
		}).error(function (erro){
			alert(erro);
		});
	};

	$scope.selecionaUsuario = function(usuarioSelacionado){
		$scope.usuario = usuarioSelacionado;
	}
	
	$scope.limparCampos = function(){
		$scope.usuario = {};
		$scope.usuario.codUsuario = undefined;
		$scope.usuario.login = "";
		$scope.usuario.senha = "";
		//$scope.usuario.dtCriacao = '2018-10-20 00:00:00';
		$scope.usuario.ativo = "";
		$scope.usuario.perfilModel = "";
	}

	$scope.salvar = function() {
		if($scope.usuario.codUsuario == undefined){
			dt = new Date().getTime();
			$scope.usuario.dtCriacao = dt;
			
			$scope.usuario.ativo = 'SIM';
			console.log($scope.usuario);
			$http.post(urlUsuario,$scope.usuario).success(function(usuario){
				$scope.limparCampos();
				$scope.listarUsuarios();
			}).error(function(erro){
				alert(erro);
			});
		}else{
			$http.put(urlUsuario,$scope.usuario).success(function(usuario){
				$scope.listarUsuarios();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}

	$scope.excluir = function(){
		if($scope.usuario.codUsuario == undefined){
			alert("Favor selecionar um registro para excluir!");
		}else{
			$http.delete(urlUsuario+'/'+$scope.usuario.codUsuario).success(function(){
				$scope.listarUsuarios();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}	


	//executa
	$scope.listarUsuarios();
	$scope.listarPerfis();
});