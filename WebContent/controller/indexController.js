var indexModulo = angular.module('indexModulo',['ngCookies']);

indexModulo.controller("indexController", function ($http, $location, $scope, $rootScope, $cookies){
	
	//--------------------------------------------------------autenticação de login
	//define variáveis globais
	$rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    //valida usuário logado
    	//se um usuário tentar acessar uma pagina sem estar logado  será redirecionado para pagina de login
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray(window.location.href, ['http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/login.html']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	window.location.href="http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/login.html";

        }
        
        // variavel guarda na pagina qual é o perfil do usuário logado, para que possa pelo ANGULAR fazer
        //o controle de permissões de usuários na pagina
        $scope.perfil = $rootScope.globals.currentUser.usuario.perfilModel.nomePerfil;
    });
	
    	//função para deslogar do sistema, chamada no botão SAIR
    $scope.sair = function(){
    	 $rootScope.globals = {};
         $cookies.remove('globals');
         $http.defaults.headers.common.Authorization = 'Basic';
         window.location.href="http://ec2-54-207-85-166.sa-east-1.compute.amazonaws.com/Oficina/login.html";	
    };
    //--------------------------------------------------------autenticação de login

});