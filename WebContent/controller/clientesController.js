var clientesModulo = angular.module('clientesModulo',['ngCookies']);

clientesModulo.controller("clientesController", function ($http, $location, $scope, $rootScope, $cookies){
	
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
	urlCep = 'http://viacep.com.br/ws/';
	urlCliente = 'http://localhost:8080/Oficina/rest/clientes';
	
	$scope.listarClientes = function (){
		$http.get(urlCliente).success(function (clientes){
			$scope.clientes = clientes;
		}).error(function (erro){
			alert(erro);
		});
	};

	$scope.selecionaCliente = function(clienteSelecionado){
		$scope.cliente = clienteSelecionado;
		if($scope.cliente.tipoPessoa == 'F'){
			$('.cnpj').hide();
			$('.cpf').show();
		}else{
			$('.cnpj').show();
			$('.cpf').hide();
		}
	}

	$scope.limparCampos = function(){
		$scope.cliente.tipoPessoa="F";
		$scope.cliente.codCliente="";
		$scope.cliente.bairro="";
		$scope.cliente.cep="";
		$scope.cliente.cidade="";
		$scope.cliente.cnpj="";
		$scope.cliente.codCliente="";
		$scope.cliente.cpf="";
		$scope.cliente.email="";
		$scope.cliente.estado="";
		$scope.cliente.logradouro="";
		$scope.cliente.nomeCliente="";
		$scope.cliente.numero="";
		$scope.cliente.razaoSocial="";
		$scope.cliente.telefone="";
	}

	function cadastroCompleto(){
		if($scope.cliente.tipoPessoa == 'F'){
			if(!$scope.cliente.nomeCliente || 
			   !$scope.cliente.bairro || !$scope.cliente.cep ||
			   !$scope.cliente.cidade || !$scope.cliente.cpf ||
			   !$scope.cliente.email || !$scope.cliente.estado ||
			   !$scope.cliente.logradouro || !$scope.cliente.numero ||
			   !$scope.cliente.telefone || !$scope.cliente.tipoPessoa){
				return false;
			}else{
				return true;
			}
		}else if ($scope.cliente.tipoPessoa == 'J'){
			if(!$scope.cliente.nomeCliente || 
			   !$scope.cliente.bairro || !$scope.cliente.cnpj ||
			   !$scope.cliente.cidade || !$scope.cliente.email || 
			   !$scope.cliente.estado || !$scope.cliente.logradouro || 
			   !$scope.cliente.numero || !$scope.cliente.telefone || 
			   !$scope.cliente.tipoPessoa || !$scope.cliente.razaoSocial ||
			   !$scope.cliente.cep){
				return false;
			} else {
				return true;
			}
		}
	}
	
	$scope.salvar = function() {
		if(cadastroCompleto()){
			var existeCPF,CPF, existeCNPJ, CNPJ, codCliente;
			if($scope.cliente.tipoPessoa == 'F'){
				$http.get(urlCliente+'/'+$scope.cliente.cpf+'/cpf').success(function (c){
					existeCPF = c.length;
					
					if(existeCPF == 0){
						if($scope.cliente.codCliente == undefined){
							$http.post(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente cadastrado com sucesso!');
								$scope.limparCampos();
								$('#nav-lista-tab').tab('show');
								$scope.listarClientes();
							}).error(function(erro){
								alert(erro);
							});
						}else{
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});						
						}
					}else{
						CPF = c[0].cpf;
						codCliente = c[0].codCliente;
						
						if ($scope.cliente.codCliente == codCliente && $scope.cliente.cpf == CPF){
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}else{
							$scope.chamarModalMensagens('Erro!','Já existe um cliente cadastrado com esse CPF!');
						}					
					}
				}).error(function (erro){
					alert(erro);
				})
				
			}else{
				$http.get(urlCliente+'/'+$scope.cliente.cnpj+'/cnpj').success(function (p){
					existeCNPJ = p.length;
					console.log(existeCNPJ);
					
					if(existeCNPJ == 0){
						if($scope.cliente.codCliente == undefined){			
							$http.post(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente cadastrado com sucesso!');
								$scope.limparCampos();
								$('#nav-lista-tab').tab('show');
								$scope.listarClientes();
							}).error(function(erro){
								alert(erro);
							});
						}else{						
							
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}
					}else{
						CNPJ = p[0].cnpj;
						codCliente = p[0].codCliente;
					
						if($scope.cliente.codCliente == codCliente && $scope.cliente.cnpj == CNPJ){
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}else{
							$scope.chamarModalMensagens('Erro!','Já existe um cliente cadastrado com esse CNPJ!');
						}
					}
				}).error(function (erro){
					alert(erro);
				})
			}
		}else{
			console.log($scope.cliente);
			$scope.chamarModalMensagens('Mensagem!','Para salvar o cadastro do cliente é necessário preencher todos os campos do formulário!');
		}
	}

	$scope.excluir = function(){
		if($scope.cliente.codCliente == undefined || $scope.cliente.codCliente == ""){
			
			$scope.chamarModalMensagens('Mensagem','Favor selecionar um registro para excluir!');
			$scope.limparCampos();
		}else{
			$http.delete(urlCliente+'/'+$scope.cliente.codCliente).success(function(){
				$scope.chamarModalMensagens('Mensagem','Cliente excluído com sucesso!');
				$('#nav-lista-tab').tab('show');
				$scope.listarClientes();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}

	
	/* jeito novo get CEP*/
	$scope.buscaCEP = function(){
		var aux = $scope.cliente.cep;
		var c = aux.replace('-','');
		$http.get(urlCep+c+'/json', {
			headers: {
				'Access-Control-Allow-Origin':'*',
				'Access-Control-Allow-Headers':'origin, content-type, accept, authorization'
			}
		}).success(function(cep){
			$scope.cliente.logradouro = cep.logradouro;
			$scope.cliente.cidade = cep.localidade;
			$scope.cliente.estado = cep.uf;
			$scope.cliente.bairro = cep.bairro;
		}).error(function (erro){
			alert(erro);
		})
		
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
    
	
	$scope.teste = function (){
		console.log($scope.cliente);
	}
	
	//executa
	$scope.listarClientes();

});