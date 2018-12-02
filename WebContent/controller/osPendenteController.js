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
    
    
    //Função que atualiza o preço total da Ordem de Serviço(soma o valor de cada produto da OS e seta na tabela "tbos")
    $scope.atualizaPrecoOs = function(){
    	$http.get(urlItens+'/'+$scope.osPendente.numOs).success(function (itens){
    		var vlrTotal = 0.0;
    		
    		for (i=0 ; i<itens.length ; i++){
    			vlrTotal = vlrTotal + itens[i].vlrTotal;
    		}
    		
    		$http.put(urlOs+'/'+$scope.osPendente.numOs+'/'+vlrTotal).success(function (){
    			$scope.osPendente.valorTotal = vlrTotal;
    		}).error(function (erro){
    			alert(erro);
    		})
    	}).error(function (erro){
    		alert(erro);
    	})
    	
	}  
    
    //função que lista os Itens(produtos) vinculados a OS
    $scope.listarItensOs = function(){
    	$http.get(urlItens+'/'+$scope.osPendente.numOs).success(function (itens){
    		$scope.itens = itens;
    	}).error(function (erro){
    		alert(erro);
    	})
    }
    
    //função que lista os Itens do estoque
    $scope.listarItensEstoque = function(){
    	$http.get(urlProduto).success(function (itensEstoque){
    		$scope.itensEstoque = itensEstoque;
    	}).error(function (erro){
    		alert(erro);
    	})
    }
    
	//função que seleciona um Item dos Itens do estoque
	$scope.selecionaItem = function(itemSelecionado){
		$scope.itemOs.produtoModel = itemSelecionado;
		document.getElementById('nomeItem').value =  itemSelecionado.nomeProduto+' - '+itemSelecionado.modeloModel.nomeModelo+'('+itemSelecionado.modeloModel.qtdPortas+' Portas)';
		$('#modalSelecionaItem').modal('hide');
	}
    
    //função que chama Modal para alterar item já lançado na OS
	$scope.alteraItem = function (itemSelecionado){
		$scope.operacao = 'U';
		$scope.itemOs = {};
		$scope.itemOs = itemSelecionado;
		document.getElementById('nomeItem').value =  itemSelecionado.produtoModel.nomeProduto+' - '+itemSelecionado.produtoModel.modeloModel.nomeModelo+'('+itemSelecionado.produtoModel.modeloModel.qtdPortas+' Portas)';
		$('#modalIncluiItem').modal('show');

		
	}
	
    //função que lista os modelos de carro
    $scope.listarModelos = function(){
		$http.get(urlModelo).success(function (modelos){
			$scope.modelos = modelos;
			console.log(modelos);
		}).error(function (erro){
			alert(erro);
		})
	}
    
    //função que seleciona um modelo de carro da lista de modelos de carro
    $scope.pesquisaCarro = function(modeloSelecionado){
		$scope.osPendente.modeloModel = modeloSelecionado;
		document.getElementById('nomeModeloCar').value =  modeloSelecionado.nomeModelo+' / '+modeloSelecionado.qtdPortas+'P / '+modeloSelecionado.ano;
	}  
    
    //função que lista os clientes cadastrados
    $scope.listarClientes = function (){
		$http.get(urlCliente).success(function (clientes){
			$scope.clientes = clientes;
		}).error(function (erro){
			alert(erro);
		});
	};
	
	//função que seleciona um cliente da lista clientes
    $scope.buscaCliente = function(clienteSelecionado){
		$scope.osPendente.clienteModel = clienteSelecionado;
		document.getElementById('cliente').value =  clienteSelecionado.nomeCliente;
	} 
	
	//função que lista todas as OS Pendentes(que ainda não foram encerradas)
	$scope.listarOs = function (){
		$http.get(urlOs+'/0').success(function (oss){
			/*
			for(var i = 0 ; i < oss.length ; i++ ){
				oss[i].dhAbertura = unixToDate(oss[i].dhAbertura);
			}
			*/
			$scope.osPendentes = oss;
			console.log(oss);
		}).error(function (erro){
			alert(erro);
		})
	}
	
	//função que seleciona uma OS da lista de OS's Pendente
	$scope.selecionaOs = function(osSelecionada){
		$scope.osPendente = osSelecionada;
		document.getElementById('nomeModeloCar').value =  osSelecionada.modeloModel.nomeModelo+' / '+osSelecionada.modeloModel.qtdPortas+'P / '+osSelecionada.modeloModel.ano;
		document.getElementById('cliente').value =  osSelecionada.clienteModel.nomeCliente;
	}
	
	
	
	//função que chama o Modal com formulario do item que será lançado
	$scope.mostraModalIncluiItem = function (){
		document.getElementById('nomeItem').value="";
		$scope.itemOs = {};
		$scope.itemOs.numOs = $scope.osPendente.numOs;
		$('#modalIncluiItem').modal('show');
	}

	
	//função que chama que busca itens do estoque
	$scope.mostraModalSelecionaItem = function (){
		$('#modalSelecionaItem').modal('show');
	}
    
    //função que atualiza o cadastro da OS
    $scope.salvar = function() {
    	
    		console.log($scope.osPendente);
    		
			$http.put(urlOs,$scope.osPendente).success(function(os){
				alert("Ordem de Serviço atualizada com sucesso!");
			}).error(function(erro){
				alert(erro);
			});
						
    }
    
    //função que insere novo item na OS
    $scope.salvarItem = function(){
    	$scope.itemOs.vlrTotal = (Number($scope.itemOs.qtd) * Number($scope.itemOs.valorUnit));
    	var codProduto, qtdProduto = 2;
    	
    	$http.get(urlProduto+'/'+$scope.itemOs.produtoModel.codProduto).success(function(p){
    		codProduto = p[0].codProduto;
    		qtdProduto = p[0].qtdEstoque;
    		
    		if(qtdProduto == 0){
        		$scope.chamarModalMensagens('Erro!','Não é possivel inserir este item! Este produto está em falta no estoque!');
        	}else{    	
    	    	if($scope.operacao == 'U'){//atualiza item
    	    		$http.put(urlItens,$scope.itemOs).success(function(){
    	    			alert('Item alterado com sucesso!');
    	    			$scope.atualizaPrecoOs();
    	    			$('#modalIncluiItem').modal('hide');
    	    		}).error(function (erro){
    	    			alert(erro);
    	    		})
    	    	}else{//insere novo item
    		    	$http.post(urlItens,$scope.itemOs).success(function(ite){
    		    		$scope.atualizaPrecoOs();
    		    		$('#modalIncluiItem').modal('hide');
    		    		alert("Item lançado na OS com  sucesso!");
    		    		$scope.listarItensOs();
    		    		
    		    	}).error(function(erro){
    		    		alert(erro);
    		    	})
    	    	}
        	}
    		
    		
    		
    	}).error(function(erro){
    		alert(erro);
    	});
    	
    	
    	
    }	
	
	//função que remove um item da OS
	$scope.excluirItem = function(itemSelecionado){
    	$http.delete(urlItens+'/'+itemSelecionado.numOs+'/'+itemSelecionado.produtoModel.codProduto).success(function(){
    		alert("Item removido");
    		$scope.atualizaPrecoOs();
    		$scope.listarItensOs();
    	}).error(function (erro){
    		alert(erro);
    	});
   
    }
    
    //função que chama um Modal para apresentar mensagens, recebe de parâmetro um título e uma mensagem
    $scope.chamarModalMensagens = function (vTitulo, vMensagem){
    	$('#modalMensagens').modal('show');
    	document.getElementById('pTitulo').innerHTML = vTitulo;
    	document.getElementById('pMsg').innerHTML = vMensagem;
    	
    }
    
    //função que fecha o modal de mensagem
    $scope.fecharModalMensagens = function(){
    	if (document.getElementById.value = "Ordem de Serviço encerrada com sucesso!" ){
    		window.location.href="http://localhost:8080/Oficina/osencerradas.html";
    	}
    	document.getElementById('pTitulo').innerHTML = "";
    	document.getElementById('pMsg').innerHTML = "";
    	$('#modalMensagens').modal('hide');
    }
    
    //função que encerra a OS
    $scope.encerrarOs = function(){
    	var dhEnc = new Date().getTime();
    	$scope.fechaOs = {};
    	
    	$http.get(urlOs+'/'+$scope.osPendente.numOs).success(function (os){
    		$scope.fechaOs = os[0];
    		$scope.fechaOs.dhEncerramento = dhEnc;
    		$scope.fechaOs.status = 'ENCERRADA';
    
    		
    		$http.put(urlOs,$scope.fechaOs).success(function (){
    			$scope.chamarModalMensagens('Mensagem','Ordem de Serviço encerrada com sucesso!');
        	}).error(function (erro){
        		alert(erro);
        	})
    		
    	}).error(function (erro){
    		alert(erro);
    	});
    
    }
    
    $scope.listarOs();
    
});