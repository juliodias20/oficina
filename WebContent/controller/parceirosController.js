var parceirosModulo = angular.module('parceirosModulo',['ngCookies']);

parceirosModulo.controller("parceirosController", function ($http, $location, $scope, $rootScope, $cookies){
	
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
	urlCep = 'http://localhost:80/Oficina/rest/ceps/';
	urlParceiro = 'http://localhost:80/Oficina/rest/parceiros';
	
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
	
	/*lista todos os parceiros*/
	$scope.listarParceiros = function (){
		$http.get(urlParceiro).success(function (parceiros){
			
			$scope.parceiros = parceiros;
			
			for (i=0 ; i<parceiros.length ; i++){
				if($scope.parceiros[i].tipoPessoa == 'F'){
				
					$scope.parceiros[i].cpf = aplicaMascaraCpf($scope.parceiros[i].cpf);
				}else{
				
					$scope.parceiros[i].cnpj = aplicaMascaraCnpj($scope.parceiros[i].cnpj);
				}
			}
			
		}).error(function (erro){
			alert(erro);
		});	
		
	};

	/*seleciona o tipo de pessoa (fisica ou juridica)*/
	$scope.selecionaParceiro = function(parceiroSelecionado){
		$scope.parceiro = parceiroSelecionado;
		if($scope.parceiro.tipoPessoa == 'F'){
			$('.cnpj').hide();
			$('.cpf').show();
		}else{
			$('.cnpj').show();
			$('.cpf').hide();
		}
	}
	
	/*limpa os campos do formulario de cadastro de parceiro*/
	$scope.limparCampos = function(){
		$scope.parceiro.tipoPessoa="F";
		$scope.parceiro.codParceiro="";
		$scope.parceiro.bairro="";
		$scope.parceiro.cep="";
		$scope.parceiro.cidade="";
		$scope.parceiro.cnpj="";
		$scope.parceiro.codParceiro="";
		$scope.parceiro.cpf="";
		$scope.parceiro.email="";
		$scope.parceiro.estado="";
		$scope.parceiro.logradouro="";
		$scope.parceiro.nomeParceiro="";
		$scope.parceiro.numero="";
		$scope.parceiro.razaoSocial="";
		$scope.parceiro.telefone="";
	}

	/*valida o formulario de cadastro de parceiro*/
	function cadastroCompleto(){
		if($scope.parceiro.tipoPessoa == 'F'){
			if(!$scope.parceiro.nomeParceiro || 
			   !$scope.parceiro.bairro || !$scope.parceiro.cep ||
			   !$scope.parceiro.cidade || !$scope.parceiro.cpf ||
			   !$scope.parceiro.email || !$scope.parceiro.estado ||
			   !$scope.parceiro.logradouro || !$scope.parceiro.numero ||
			   !$scope.parceiro.telefone || !$scope.parceiro.tipoPessoa){
				return false;
			}else{
				return true;
			}
		}else if ($scope.parceiro.tipoPessoa == 'J'){
			if(!$scope.parceiro.nomeParceiro || 
			   !$scope.parceiro.bairro || !$scope.parceiro.cnpj ||
			   !$scope.parceiro.cidade || !$scope.parceiro.email || 
			   !$scope.parceiro.estado || !$scope.parceiro.logradouro || 
			   !$scope.parceiro.numero || !$scope.parceiro.telefone || 
			   !$scope.parceiro.tipoPessoa || !$scope.parceiro.razaoSocial ||
			   !$scope.parceiro.cep){
				return false;
			} else {
				return true;
			}
		}
	}
	
	
	
	/* registra um novo cadastro/atualiza cadastro de parceiro */
	$scope.salvar = function() {
		if(cadastroCompleto()){
	
			var existeCPF, CPF, existeCNPJ, CNPJ, codParceiro;
			
			if($scope.parceiro.tipoPessoa == 'F'){
				
				$http.get(urlParceiro+'/'+removeMascaraCpfCnpj($scope.parceiro.cpf)+'/cpf').success(function (c){
					existeCPF = c.length; 
					
					/*verifica se já existe um parceiro cadastrado para este CPF*/
					if(existeCPF == 0){
						/*verifica se já existe um ID para este parceiro*/
						if($scope.parceiro.codParceiro == undefined){
							$scope.parceiro.cpf = removeMascaraCpfCnpj($scope.parceiro.cpf);
							
							$http.post(urlParceiro,$scope.parceiro).success(function(parceiro){
								$scope.chamarModalMensagens('Mensagem','Parceiro cadastrado com sucesso!');
								//$scope.limparCampos();
								//$('#nav-lista-tab').tab('show');
								//$scope.listarParceiros();
							}).error(function(erro){
								alert(erro);
							});
						}else{
							$scope.parceiro.cpf = removeMascaraCpfCnpj($scope.parceiro.cpf);
							$http.put(urlParceiro,$scope.parceiro).success(function(parceiro){
								$scope.chamarModalMensagens('Mensagem','Parceiro atualizado com sucesso!');
								//$scope.listarParceiros();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});						
						}
					}else{
						CPF = c[0].cpf;
						codParceiro = c[0].codParceiro;
						
						if ($scope.parceiro.codParceiro == codParceiro && removeMascaraCpfCnpj($scope.parceiro.cpf) == CPF){							
							$scope.parceiro.cpf = removeMascaraCpfCnpj($scope.parceiro.cpf);
							
							$http.put(urlParceiro,$scope.parceiro).success(function(parceiro){
								$scope.chamarModalMensagens('Mensagem','Parceiro atualizado com sucesso!');
								//$scope.listarParceiros();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}else{
							$scope.chamarModalMensagens('Erro!','Já existe um parceiro cadastrado com esse CPF');
						}					
					}
				}).error(function (erro){
					alert(erro);
				})
				
			}else{
				$http.get(urlParceiro+'/'+removeMascaraCpfCnpj($scope.parceiro.cnpj)+'/cnpj').success(function (p){
					existeCNPJ = p.length;
					
					if(existeCNPJ == 0){		
						if($scope.parceiro.codParceiro == undefined){
							$scope.parceiro.cnpj = removeMascaraCpfCnpj($scope.parceiro.cnpj);
							
							$http.post(urlParceiro,$scope.parceiro).success(function(parceiro){
								$scope.chamarModalMensagens('Mensagem','Parceiro cadastrado com sucesso!');
								//$scope.limparCampos();
								//$('#nav-lista-tab').tab('show');
								//$scope.listarParceiros();
							}).error(function(erro){
								alert(erro);
							});
						}else{
							$scope.parceiro.cnpj = removeMascaraCpfCnpj($scope.parceiro.cnpj);
							
							$http.put(urlParceiro,$scope.parceiro).success(function(parceiro){
								$scope.chamarModalMensagens('Mensagem','Parceiro atualizado com sucesso!');
								//$scope.listarParceiros();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}
					}else{
						CNPJ = p[0].cnpj;
						codParceiro = p[0].codParceiro;
					
						if($scope.parceiro.codParceiro == codParceiro && removeMascaraCpfCnpj($scope.parceiro.cnpj) == CNPJ){
							$scope.parceiro.cnpj = removeMascaraCpfCnpj($scope.parceiro.cnpj);
							
							$http.put(urlParceiro,$scope.parceiro).success(function(parceiro){
								$scope.chamarModalMensagens('Mensagem','Parceiro atualizado com sucesso!');
								//$scope.listarParceiros();
								//$('#nav-lista-tab').tab('show');
								//$scope.limparCampos();
							}).error(function (erro){
								alert(erro);
							});
						}else{
							$scope.chamarModalMensagens('Erro!','Já existe um parceiro cadastrado com esse CNPJ!');
						}
					}
				}).error(function (erro){
					alert(erro);
				})
			}
		}else{
			$scope.chamarModalMensagens('Mensagem!','Para salvar o cadastro do parceiro é necessário preencher todos os campos do formulário!');
		}
	}

	$scope.excluir = function(){
		if($scope.parceiro.codParceiro == undefined || $scope.parceiro.codParceiro == ""){
			
			$scope.chamarModalMensagens('Mensagem','Favor selecionar um registro para excluir!');
			$scope.limparCampos();
		}else{
			$http.delete(urlParceiro+'/'+$scope.parceiro.codParceiro).success(function(){
				$scope.chamarModalMensagens('Mensagem','Parceiro excluído com sucesso!');
				$('#nav-lista-tab').tab('show');
				$scope.listarParceiros();
				$scope.limparCampos();
			}).error(function (erro){
				alert(erro);
			});
		}
	}

	
	/* jeito novo get CEP*/
	$scope.buscaCEP = function(){
		var aux = $scope.parceiro.cep;
		var c = aux.replace('-','');
		$http.get(urlCep+c, {
			headers: {
				'Access-Control-Allow-Origin':'*',
				'Access-Control-Allow-Headers':'origin, content-type, accept, authorization'
			}
		}).success(function(cep){
			$scope.parceiro.logradouro = cep.logradouro;
			$scope.parceiro.cidade = cep.localidade;
			$scope.parceiro.estado = cep.uf;
			$scope.parceiro.bairro = cep.bairro;
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
	$scope.listarParceiros();

});