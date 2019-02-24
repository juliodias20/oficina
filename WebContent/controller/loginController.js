var loginModulo = angular.module('loginModulo',['ngCookies']);

loginModulo.controller("loginController", function ($http, $location, $scope, $rootScope, $cookies){
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
    });
	
    urlLogin = 'http://localhost:8080/Oficina/rest/login';
    
    $scope.logar = function(){
    	
    	$http.post(urlLogin,$scope.usuario).success(function(usuario){
    		
    		$rootScope.globals = {
                currentUser: {
                    usuario: usuario,
                    authdata: 'xyz'
                }
            };
    		
    		var cookieExp = new Date();
            cookieExp.setDate(cookieExp.getDate() + 7);
            $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp });
            
    		window.location.href="http://localhost:8080/Oficina/index.html";
    		
	    	}).error(function(erro){
	    		$('#loginModal').modal('show');
	    	})
    	}
    	
    	
    
});
