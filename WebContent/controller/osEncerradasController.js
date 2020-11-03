var osEncerradasModulo = angular.module('osEncerradasModulo',['ngCookies','angularUtils.directives.dirPagination']);

osEncerradasModulo.controller("osEncerradasController", function ($http, $location, $scope, $rootScope, $cookies){
	
	//--------------------------------------------------------autenticação de login
	$rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray(window.location.href, ['http://localhost:80/Oficina/login.html']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	window.location.href="http://localhost:80/Oficina/login.html";

        }
        
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });
	
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://localhost:80/Oficina/login.html";	
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
    
    urlOs = 'http://localhost:80/Oficina/rest/os/encerradas';
    urlItens = 'http://localhost:80/Oficina/rest/subos';
    urlParceiro = 'http://localhost:80/Oficina/rest/parceiros';
    
    //função que lista os Itens(produtos) vinculados a OS
    $scope.listarItensOs = function(){
    	$http.get(urlItens+'/'+$scope.osEncerrada.numOs).success(function (itens){
    		$scope.itens = itens;
    	}).error(function (erro){
    		alert(erro);
    	})
    }
    
    // função que seleciona um parceiro da lista parceiros
    $scope.buscaParceiro = function(parceiroSelecionado){
    	document.getElementById('f_nomeParceiro').value =  parceiroSelecionado.nomeParceiro;
    	document.getElementById('f_codParceiro').value =  parceiroSelecionado.codParceiro;
	} 
    
	//função que lista todas as OS Encerradas
	$scope.listarOs = function (){
		$http.get(urlOs).success(function (oss){
			$scope.osEncerradas = oss;
		}).error(function (erro){
			alert(erro);
		})
	}

	
	//função que lista as OS com filtros
	$scope.listarOsComFiltros = function(){
		mostrarLoading();
		
		if(validaFiltro('periodo') == 'E' || validaFiltro('parceiro') == 'E' || validaFiltro('placa') == 'E'){
			return;
		}
		
		//Filtros: periodo
		if(validaFiltro('periodo') == 'C' && validaFiltro('parceiro') != 'C' && validaFiltro('placa') != 'C'){
			
			
			var f_dtini = document.getElementById('f_dtIni').value;
			var f_dtfim = document.getElementById('f_dtFim').value;
			
			$http.get(urlOs+'/dtabertura/'+f_dtini+'/'+f_dtfim).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
				
			})
			
			
		}
		
		//Filtros: Parceiro
		if(validaFiltro('periodo') != 'C' && validaFiltro('parceiro') == 'C' && validaFiltro('placa') != 'C'){
			var f_codParceiro = document.getElementById('f_codParceiro').value;
			
			$http.get(urlOs+'/parceiro/'+f_codParceiro).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
		}
		
		//Filtros: Placa
		if(validaFiltro('periodo') != 'C' && validaFiltro('parceiro') != 'C' && validaFiltro('placa') == 'C'){
			var f_placa = document.getElementById('f_placaCarro').value;
			
			$http.get(urlOs+'/placa/'+f_placa).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
			
		}
		
		//Filtros: Parceiro | Placa
		if(validaFiltro('periodo') != 'C' && validaFiltro('parceiro') == 'C' && validaFiltro('placa') == 'C'){
			var f_codParceiro = document.getElementById('f_codParceiro').value;
			var f_placa = document.getElementById('f_placaCarro').value;
			
			$http.get(urlOs+'/parceiro/'+f_codParceiro+'/placa/'+f_placa).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
		}
		
		//Filtros: Periodo | Parceiro
		if(validaFiltro('periodo') == 'C' && validaFiltro('parceiro') == 'C' && validaFiltro('placa') != 'C'){
			var f_dtini = document.getElementById('f_dtIni').value;
			var f_dtfim = document.getElementById('f_dtFim').value;
			var f_codParceiro = document.getElementById('f_codParceiro').value;
			
			$http.get(urlOs+'/parceiro/'+f_codParceiro+'/dtabertura/'+f_dtini+'/'+f_dtfim).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
		}
		
		//Filtros: Periodo | Placa
		if(validaFiltro('periodo') == 'C' && validaFiltro('parceiro') != 'C' && validaFiltro('placa') == 'C'){
			var f_dtini = document.getElementById('f_dtIni').value;
			var f_dtfim = document.getElementById('f_dtFim').value;
			var f_placa = document.getElementById('f_placaCarro').value;
			
			$http.get(urlOs+'/placa/'+f_placa+'/dtabertura/'+f_dtini+'/'+f_dtfim).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
		}
		
		//Filtros: Periodo | Parceiro | Placa
		if(validaFiltro('periodo') == 'C' && validaFiltro('parceiro') == 'C' && validaFiltro('placa') == 'C'){
			var f_dtini = document.getElementById('f_dtIni').value;
			var f_dtfim = document.getElementById('f_dtFim').value;
			var f_placa = document.getElementById('f_placaCarro').value;
			var f_codParceiro = document.getElementById('f_codParceiro').value;
			
			
			$http.get(urlOs+'/parceiro/'+f_codParceiro+'/placa/'+f_placa+'/dtabertura/'+f_dtini+'/'+f_dtfim).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
		}
		
		//Filtros: Nenhum
		if(validaFiltro('periodo') != 'C' && validaFiltro('parceiro') == null && validaFiltro('placa') == null){
			$http.get(urlOs).success(function (oss){
				$scope.osEncerradas = oss;
				
				ocultarLoading();
			}).error(function (erro){
				alert(erro);
			})
		}
		
	}
	
	//função que valida filtros
	function validaFiltro(p_filtro){		
	
		if (p_filtro == 'periodo'){
			var f_dtini = document.getElementById('f_dtIni').value;
			var f_dtfim = document.getElementById('f_dtFim').value;
			
			if(f_dtini != '' && f_dtfim != ''){
				if(f_dtini > f_dtfim) {
					$scope.chamarModalMensagens('Erro','A data inicial não pode ser menor que a data final.');
					return 'E';
				}
				return 'C';
			}
			
			if(f_dtini != '' && f_dtfim == '' || f_dtini == '' && f_dtfim != ''){
				$scope.chamarModalMensagens('Erro','Para o utilizar o filtro de período é necessário informar data inicial e data final.');
				return 'E';
			}
			return null;
		}

		if(p_filtro == 'parceiro'){
			var f_codParceiro = document.getElementById('f_codParceiro').value;
			
			if (f_codParceiro != ''){
				if(!isNaN(f_codParceiro)){
					return 'C';
				};
			}
			
			if (f_codParceiro == ''){
				return null;
			}
		}
		
		if(p_filtro == 'placa'){
			var f_placa = document.getElementById('f_placaCarro').value;
			
			if(f_placa != ''){
				if(f_placa.length != 8){
					$scope.chamarModalMensagens('Erro','Placa do carro inválida.');
					return 'E';
				}else{
					return 'C';
				}
				
			}
			return null;
		}
		
	}
	
	
	
	//função que lista todos os parceiros	
	$scope.listarParceiros = function (){
		$http.get(urlParceiro).success(function (parceiros){
			$scope.parceiros = parceiros;
		}).error(function (erro){
			alert(erro);
		});
	};
	
	//função que seleciona uma OS da lista de OS's Encerradas
	$scope.selecionaOs = function(osSelecionada){
		$scope.osEncerrada = osSelecionada;
		document.getElementById('nomeModeloCar').value =  osSelecionada.modeloModel.nomeModelo+' / '+osSelecionada.modeloModel.qtdPortas+'P / '+osSelecionada.modeloModel.ano;
		document.getElementById('parceiro').value =  osSelecionada.parceiroModel.nomeParceiro;
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
    
    $scope.limpar = function(){
		
		document.getElementById('f_dtIni').value = '';
		document.getElementById('f_dtFim').value = '';
		document.getElementById('f_placaCarro').value = '';
		document.getElementById('f_codParceiro').value = '';
		document.getElementById('f_nomeParceiro').value = '';
    }
    
});