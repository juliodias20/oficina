var clientesModulo = angular.module('clientesModulo',['ngCookies']);

clientesModulo.controller("clientesController", function ($http, $location, $scope, $rootScope, $cookies){
	
	//--------------------------------------------------------autenticação de login
	$rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray(window.location.href, ['http://localhost/Oficina/login.html']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	window.location.href="http://localhost/Oficina/login.html";

        }
        
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });
	
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://localhost/Oficina/login.html";	
    };
    //--------------------------------------------------------autenticação de login
	urlCep = 'http://localhost/Oficina/rest/ceps/';
	urlCliente = 'http://localhost/Oficina/rest/parceiros';
	
	/*função remove mascara CPF */
	function removeMascaraCpfCnpj(campoComMascara) {
		var campoSemMascara = campoComMascara;
		
		for( i = 0 ; i < 4 ; i++){
			campoSemMascara = campoSemMascara.replace('.','');
		}
		campoSemMascara = campoSemMascara.replace('-','');
		
		return campoSemMascara;
	}
	
	/*função aplica mascara CPF */
	function aplicaMascaraCpf(campoSemMascara) {
		var campoComMascara;
		var parte1, parte2, parte3, parte4;
			
			parte1 = campoSemMascara.substring(0,3);
			parte2 = campoSemMascara.substring(3,6);
			parte3 = campoSemMascara.substring(6,9);
			parte4 = campoSemMascara.substring(9,11);
			
			campoComMascara = parte1+'.'+parte2+'.'+parte3+'-'+parte4;
			
		return campoComMascara;
	}
	
	/*função aplica mascara CNPJ */
	function aplicaMascaraCnpj(campoSemMascara) {
		var campoComMascara;
		var parte1, parte2, parte3, parte4, parte5;
		
			parte1 = campoSemMascara.substring(0,2);
			parte2 = campoSemMascara.substring(2,5);
			parte3 = campoSemMascara.substring(5,8);
			parte4 = campoSemMascara.substring(8,12);
			parte5 = campoSemMascara.substring(12,14);
			
			campoComMascara = parte1+'.'+parte2+'.'+parte3+'.'+parte4+'-'+parte5;
		return campoComMascara
	}
	
	/*lista todos os clientes*/
	$scope.listarClientes = function (){
		$http.get(urlCliente).success(function (clientes){
			
			$scope.clientes = clientes;
			
			for (i=0 ; i<clientes.length ; i++){
				if($scope.clientes[i].tipoPessoa == 'F'){
				
					$scope.clientes[i].cpf = aplicaMascaraCpf($scope.clientes[i].cpf);
				}else{
				
					$scope.clientes[i].cnpj = aplicaMascaraCnpj($scope.clientes[i].cnpj);
				}
			}
			
		}).error(function (erro){
			alert(erro);
		});	
		
	};

	/*seleciona o tipo de pessoa (fisica ou juridica)*/
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
	
	/*limpa os campos do formulario de cadastro de cliente*/
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

	/*valida o formulario de cadastro de cliente*/
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
	
	
	
	/* registra um novo cadastro/atualiza cadastro de cliente */
	$scope.salvar = function() {
		if(cadastroCompleto()){
	
			var existeCPF, CPF, existeCNPJ, CNPJ, codCliente;
			
			if($scope.cliente.tipoPessoa == 'F'){
				
				$http.get(urlCliente+'/'+removeMascaraCpfCnpj($scope.cliente.cpf)+'/cpf').success(function (c){
					existeCPF = c.length; 
					
					/*verifica se já existe um cliente cadastrado para este CPF*/
					if(existeCPF == 0){
						/*verifica se já existe um ID para este cliente*/
						if($scope.cliente.codCliente == undefined){
							$scope.cliente.cpf = removeMascaraCpfCnpj($scope.cliente.cpf);
							
							$http.post(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente cadastrado com sucesso!');
								//$scope.limparCampos();
								//$('#nav-lista-tab').tab('show');
								//$scope.listarClientes();
							}).error(function(erro){
								alert(erro);
							});
						}else{
							$scope.cliente.cpf = removeMascaraCpfCnpj($scope.cliente.cpf);
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								//$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});						
						}
					}else{
						CPF = c[0].cpf;
						codCliente = c[0].codCliente;
						
						if ($scope.cliente.codCliente == codCliente && removeMascaraCpfCnpj($scope.cliente.cpf) == CPF){							
							$scope.cliente.cpf = removeMascaraCpfCnpj($scope.cliente.cpf);
							
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								//$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}else{
							$scope.chamarModalMensagens('Erro!','Já existe um cliente cadastrado com esse CPF');
						}					
					}
				}).error(function (erro){
					alert(erro);
				})
				
			}else{
				$http.get(urlCliente+'/'+removeMascaraCpfCnpj($scope.cliente.cnpj)+'/cnpj').success(function (p){
					existeCNPJ = p.length;
					
					if(existeCNPJ == 0){		
						if($scope.cliente.codCliente == undefined){
							$scope.cliente.cnpj = removeMascaraCpfCnpj($scope.cliente.cnpj);
							
							$http.post(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente cadastrado com sucesso!');
								//$scope.limparCampos();
								//$('#nav-lista-tab').tab('show');
								//$scope.listarClientes();
							}).error(function(erro){
								alert(erro);
							});
						}else{
							$scope.cliente.cnpj = removeMascaraCpfCnpj($scope.cliente.cnpj);
							
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								//$scope.listarClientes();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}
					}else{
						CNPJ = p[0].cnpj;
						codCliente = p[0].codCliente;
					
						if($scope.cliente.codCliente == codCliente && removeMascaraCpfCnpj($scope.cliente.cnpj) == CNPJ){
							$scope.cliente.cnpj = removeMascaraCpfCnpj($scope.cliente.cnpj);
							
							$http.put(urlCliente,$scope.cliente).success(function(cliente){
								$scope.chamarModalMensagens('Mensagem','Cliente atualizado com sucesso!');
								//$scope.listarClientes();
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
		$http.get(urlCep+c, {
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
    
	//executa
	$scope.listarClientes();

});