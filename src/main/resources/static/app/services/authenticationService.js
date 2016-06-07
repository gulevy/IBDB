(function() {
    // this service responsible for authentication service
	// check authentication , check if user already login and option to logout
    function AuthenticationService( $rootScope , $q,$http, $cookieStore,userService, $timeout) {
    	var service = {};
   	 
    	//try to login check username and password
    	service.login = function (username, password) {
    		console.log('enter login');

    		return $http.get("/user/auth/" + username + "/" + password).then(function(response) {
    			if (response.data.success) {
    				console.log('password is  correct');
    			}
    
    			return response.data;
    		});
 
        };
        
        //get specific Credentials
        service.getCredentials = function (key) {
        	token = $cookieStore.get(key);
        	
        	if (!token) {
        	 	console.log('cannot find cookie');
        	}
        
        	return token;
        }
  
        service.setCredentials = function (key , user) {
        	token = {
                user : user
            };
        	
        	var now = new Date();
            now.setDate(now.getDate() + 7);

            $cookieStore.put(key, token , {
                expires: now
            });
            
            console.log('cookie was set'); 
        };
  
        //logout remove the cookie 
        service.logout = function (key) {
        	console.log('cookie was clean');
            //$rootScope.globals = {};
        	$cookieStore.remove(key);

        	//sleep for a second	
        	$timeout(a =2, 1000);
        };
        
      //check if user is login
        service.isLogin = function(key) {
            if ( $cookieStore.get(key)) {
            	return true;
            } else {
            	console.log('cannot find cookie');
            	return false;
            }
		} 
        
        return service;
    } 
    myApp.factory("AuthenticationService", AuthenticationService);
  
})();




