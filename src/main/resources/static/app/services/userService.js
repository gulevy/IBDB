(function() {

    function userService($http, $q,$location) {
        var url = "/user/"

        this.login = function (username,password,callback) {
        	$http.get("/user/auth/" + username + "/" + password)
                .success(function (data, status, headers, conf) {
                    callback(null, data);
                })
                .error(function (data, status, headers, conf) {
                    callback(null,data);
                });
        };
                
        this.logout = function (key) {
        	console.log('cookie was clean');
            //$rootScope.globals = {};
        	$cookieStore.remove(key);
        	$location.path("/login")
            //$http.defaults.headers.common.Authorization = 'Basic ';
        };
        	  	
        this.getuser = function(id) {
            var request = $http.get(url  + id);
            return (request.then(handleSuccess, handleError));
        }

        this.getUsers = function() {
            var request = $http.get("/users");
  
            return (request.then(handleSuccess, handleError));		
        }
        
        this.authenticate = function(user,password) {
        	 var request = $http.get('/user/auth/' + user + '/' + password);
        	  
             return (request.then(handleSuccess, handleError));	
        }
        
        // I remove the book with the given ID from the remote collection.
        this.removeUser = function(id) {
            var request = $http.delete(url  + id);

            return (request.then(handleSuccess, handleError));
        }

        this.editUser = function(user) {
        	 var request = $http({
                 method: 'PUT',
                 url: url,
                 data: user,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             })
        	
           return (request.then(handleSuccess, handleError));
        }

        this.addUser = function(user) {
        	 var request = $http({
                 method: 'POST',
                 url: url,
                 data: user,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             })
        	
           return (request.then(handleSuccess, handleError));
        }
                  
        this.uploadUser = function(uploadUrl,file,destName){
            var fd = new FormData();
            fd.append('name', destName);
            fd.append('file', file);
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(){
            })
            .error(function(){
            });
        }
           
        // I transform the error response, unwrapping the application data
	    // from
        // the API response payload.
        function handleError(response) {
           if (!angular.isObject(response.data) || !response.data.message) {
               return ($q.reject("An unknown error occurred."));
           }
                
           // Otherwise, use expected error message.
           return ($q.reject(response.data.message));
        }
        
        //return data on success
        function handleSuccess(response) {
            return (response.data);
        }
    }

    myApp.service("userService", userService);

})();
