var osEncerradasModulo = angular.module('osEncerradasModulo',['ngCookies']);

osEncerradasModulo.controller("osEncerradasController", function ($http, $location, $scope, $rootScope, $cookies){
	
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
    
    urlOs = 'http://localhost:8080/Oficina/rest/os';
    urlItens = 'http://localhost:8080/Oficina/rest/subos';
    
    //função que lista os Itens(produtos) vinculados a OS
    $scope.listarItensOs = function(){
    	$http.get(urlItens+'/'+$scope.osEncerrada.numOs).success(function (itens){
    		$scope.itens = itens;
    	}).error(function (erro){
    		alert(erro);
    	})
    }
	
	//função que lista todas as OS Encerradas
	$scope.listarOs = function (){
		$http.get(urlOs+'/-1').success(function (oss){
			for(var i = 0 ; i < oss.length ; i++ ){
				oss[i].dhAbertura = unixToDate(oss[i].dhAbertura);
				oss[i].dhEncerramento = unixToDate(oss[i].dhEncerramento);
			}
			$scope.osEncerradas = oss;
		}).error(function (erro){
			alert(erro);
		})
	}
	
	//função que seleciona uma OS da lista de OS's Encerradas
	$scope.selecionaOs = function(osSelecionada){
		$scope.osEncerrada = osSelecionada;
		document.getElementById('nomeModeloCar').value =  osSelecionada.modeloModel.nomeModelo+' / '+osSelecionada.modeloModel.qtdPortas+'P / '+osSelecionada.modeloModel.ano;
		document.getElementById('cliente').value =  osSelecionada.clienteModel.nomeCliente;
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
    
    $scope.listarOs();
    
});