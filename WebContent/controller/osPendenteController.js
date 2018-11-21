var osPendenteModulo = angular.module('osPendenteModulo',['ngCookies']);

osPendenteModulo.controller("osPendenteController", function ($http, $location, $scope, $rootScope, $cookies){
	
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
    
    function unixToDate(unixDate){
		var data = new Date(unixDate);
		var dia = data.getDate();
	    var mes = data.getMonth() + 1;
	    var ano = data.getFullYear();
	    var hora = data.getHours();
	    var minuto = data.getMinutes();
	    
	    if (hora == '0'){hora = '00'};
	    if (hora.toString().length == 1){hora = '0'+hora};
	    if (minuto == '0'){minuto = '00'};
	    if (minuto.toString().length == 1){minuto = '0'+minuto};
	    
	    var dataConvertida = dia+'/'+mes+'/'+ano+' '+hora+':'+minuto;
		
		return dataConvertida;
	}
    
    
    urlModelo = 'http://localhost:8080/Oficina/rest/modelos'
    urlCliente = 'http://localhost:8080/Oficina/rest/clientes';
    urlOs = 'http://localhost:8080/Oficina/rest/os';
    urlItens = 'http://localhost:8080/Oficina/rest/subos';
    urlProduto = 'http://localhost:8080/Oficina/rest/produtos';
    
    $scope.buscaCliente = function(clienteSelecionado){
		$scope.osPendente.clienteModel = clienteSelecionado;
		document.getElementById('cliente').value =  clienteSelecionado.nomeCliente;
	}
    
    $scope.getProduto = function () {
    	var x = $scope.itens;
    	console.log(x);
    	
    	/*$http.get(urlProduto+'/'+codProduto).success(function (produto){
    		alert(produto);
    	}).error(function (erro){
    		alert(erro);
    	})*/
    }
    
    
    
    $scope.listarItens = function(){
    	$http.get(urlItens+'/'+$scope.osPendente.numOs).success(function (itens){
    		$scope.itens = itens;
    	}).error(function (erro){
    		alert(erro);
    	})
    }
    
    
    $scope.listarModelos = function(){
		$http.get(urlModelo).success(function (modelos){
			$scope.modelos = modelos;
			console.log(modelos);
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
	
	$scope.listarOs = function (){
		$http.get(urlOs+'/0').success(function (oss){
			for(var i = 0 ; i < oss.length ; i++ ){
				oss[i].dhAbertura = unixToDate(oss[i].dhAbertura);
			}
			$scope.osPendentes = oss;
			console.log(oss);
		}).error(function (erro){
			alert(erro);
		})
	}
	
	$scope.selecionaOs = function(osSelecionada){
		$scope.osPendente = osSelecionada;
		document.getElementById('nomeModeloCar').value =  osSelecionada.modeloModel.nomeModelo+' / '+osSelecionada.modeloModel.qtdPortas+'P / '+osSelecionada.modeloModel.ano;
		document.getElementById('cliente').value =  osSelecionada.clienteModel.nomeCliente;
	}
	
    /*
    $scope.limparCampos = function(){
    	$scope.osPendente = {};
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
    */
    $scope.pesquisaCarro = function(modeloSelecionado){
		$scope.osPendente.modeloModel = modeloSelecionado;
		document.getElementById('nomeModeloCar').value =  modeloSelecionado.nomeModelo+' / '+modeloSelecionado.qtdPortas+'P / '+modeloSelecionado.ano;
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
    
    //$scope.limparCampos();
    $scope.listarOs();
    
    
    
    
    
});