(function() {

	function LoginController($scope, $rootScope, $location,userService, AuthenticationService) {
		$rootScope.error = "";
        $scope.login = function () {
        	 userService.login($scope.username, $scope.password, function (err, response) {	 
        		 if(response.success == true) {
                 	console.log('login pass set cookie');
                     AuthenticationService.setCredentials('ibdb_token',$scope.username, $scope.password);
                     
                     $rootScope.logInUser = $scope.username;
                     $rootScope.error = "";
                     //move to main page
                     $location.path('/');
                 } else {
                	 alert(response.message);
                	 $rootScope.error = response.message;
                 }
        		 
             });
        };
        
        $rootScope.isLogin = function() {
        	res =  AuthenticationService.isLogin('ibdb_token');		
        	return res;
		}
        
        $rootScope.logout = function() {
        	res =  AuthenticationService.logout('ibdb_token');
        	
        	$location.path('/login');
    		
        	return res;
        }
        
	}

	myApp.controller("LoginController", LoginController);

})();

