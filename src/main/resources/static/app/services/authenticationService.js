(function() {

    function AuthenticationService( $rootScope , $q,$http, $cookieStore,userService, $timeout) {
    	var service = {};
   	 
    	service.login = function (username, password) {
    		console.log('enter login');
    		//var res = userService.getUsers();
    		
//    		var response = userService.authenticate(username,password);
    		
    		return $http.get("/user/auth/" + username + "/" + password).then(function(response) {
    			if (response.data.success) {
    				console.log('password is  correct');
    			}
    	    		//		 
    			
    			
    			  return response.data;
    		});
    		
    		
    		
    		
    		
    		 //var response = $http.get("/user/auth/" + username + "/" + password);
    		
    		// $promise.then(function(msg) {
    		//	 if (msg.data.success) {
    		//		 console.log('password is  correct');
    		//	 } else {
    		//		 console.log('password is  incorrect');
    		//	 }
    			 
    			 
    			 
    		// })
    		 
    		 
    		// return (response.then(handleSuccess, handleError));
 //   		 callback($promise);
    		
    		
    		
//    		return response;
    		
    		
 
            /* Dummy authentication for testing, uses $timeout to simulate api call
             ----------------------------------------------*/
//            $timeout(function() {
//                var response = { success: username === 'admin@ibdb.com' && password === 'admin' };
//                if(!response.success) {
//                    response.message = 'Username or password is incorrect';
//                }
//                callback(response);
//            }, 1000);
 
 
            /* Use this for real authentication
             ----------------------------------------------*/
            //$http.post('/api/authenticate', { username: username, password: password })
            //    .success(function (response) {
            //        callback(response);
            //    });
 
        };
        
        service.getCredentials = function (key) {
        	token = $cookieStore.get(key);
        	
        	if (!token) {
        	 	console.log('cannot find cookie');
        	}
        
        	return token;
        }
  
        service.setCredentials = function (key , username, password) {
        	token = {
                 username: username,
                 password: password
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




