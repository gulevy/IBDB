(function() {

    function AuthenticationService( $rootScope , $q,$http, $cookieStore,userService, $timeout) {
    	var service = {};
   	 
    	service.login = function (username, password) {
    		console.log('enter login');

    		return $http.get("/user/auth/" + username + "/" + password).then(function(response) {
    			if (response.data.success) {
    				console.log('password is  correct');
    			}
    
    			return response.data;
    		});
 
        };
        
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
  
        service.logout = function (key) {
        	console.log('cookie was clean');
            //$rootScope.globals = {};
        	$cookieStore.remove(key);
            //$http.defaults.headers.common.Authorization = 'Basic ';
        };
        
        
        service.isLogin = function(key) {
            if ( $cookieStore.get(key)) {
            	return true;
            } else {
            	console.log('cannot find cookie');
            	return false;
            }
		}
        
        
        // I transform the error response, unwrapping the application data
	    // from
        // the API response payload.
        function handleError(response) {
                // The API response from the server should be returned in a
                // nomralized format. However, if the request was not handled by
				// the
                // server (or what not handles properly - ex. server error),
				// then we
                // may have to normalize it on our end, as best we can.
           if (!angular.isObject(response.data) || !response.data.message) {
                    return ($q.reject("An unknown error occurred."));
           }
                
           // Otherwise, use expected error message.
           return ($q.reject(response.data.message));
        }
            // I transform the successful response, unwrapping the application
			// data
            // from the API response payload.
        function handleSuccess(response) {
            return (response.data);
        }

  
        return service;
      
    
    }	
    
    myApp.factory("AuthenticationService", AuthenticationService);
  
})();




