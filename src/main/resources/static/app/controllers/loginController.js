(function() {
    //This controller job is the perform user login actions
	function LoginController($scope, $rootScope, CommonFactory,$location,userService, AuthenticationService) {
		$rootScope.error = "";
		
        $scope.login = function () {
        	 userService.login($scope.username, $scope.password, function (err, response) {	 
        		 if(response.success == true) {
                 	console.log('login pass set cookie');
                 	
                 	userService.getUserbyUsername($scope.username).then(function(user) {	
                 		AuthenticationService.setCredentials('ibdb_token',user);
                 		
                        $rootScope.logInUser = user.username;
                        $rootScope.error = "";
                        //move to main page
                        $location.path('/');
        			 });
                 	
                    
                 } else {
                	 CommonFactory.sendPopUpMessage('User login action was failed' , response.message)
                	 $rootScope.error = response.message;
                 }
        		 
             });
        };
        
        $scope.isLogin = function() {
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

