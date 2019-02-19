var marcasModulo = angular.module('marcasModulo',['ngCookies']);

marcasModulo.controller("marcasController", function ($http, $location, $scope, $rootScope, $cookies){
	
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
	
    

	urlMarca = 'http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/rest/marcas';
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
		$scope.marca = {};
		$scope.marca.nomeMarca = "";
	}

	$scope.salvar = function() {
		if($scope.marca.codMarca == undefined){
			if($scope.marca.nomeMarca.length){
				$http.post(urlMarca,$scope.marca).success(function(marca){
					$scope.chamarModalMensagens('Mensagem!','Marca de carro cadastrada com sucesso!');
					$scope.listarMarcas();
					$scope.limparCampos();
					$('#nav-lista-tab').tab('show');
				}).error(function(erro){
					alert(erro);
				});
			}else{
				$scope.chamarModalMensagens('Mensagem!','O nome da marca é obrigatório!')
			}
		}else{
			if($scope.marca.nomeMarca){
				$http.put(urlMarca,$scope.marca).success(function(marca){
					$scope.chamarModalMensagens('Mensagem!','Cadastro da marca de carro foi atualizado com sucesso!');
					$scope.listarMarcas();
					$scope.limparCampos();
				}).error(function (erro){
					alert(erro);
				});
			}else{
				$scope.chamarModalMensagens('Mensagem!','O nome da marca é obrigatório!');
				$scope.listarMarcas();
			}
		}
	}

	$scope.excluir = function(){
		if($scope.marca.codMarca == undefined){
			$scope.chamarModalMensagens('Mensagem!','Favor selecionar um registro para excluir!');
		}else{
			$http.delete(urlMarca+'/'+$scope.marca.codMarca).success(function(){
				$scope.chamarModalMensagens('Mensagem!','Marca de carro excluída com sucesso!');
				$scope.listarMarcas();
				$scope.limparCampos();
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

});