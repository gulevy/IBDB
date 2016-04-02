(function() {

    function sessionService($http) {
        
       this.set = function(key,value) {
          return sessionStorage.setItem(key,value); 
       },
       
       this.get = function(key) {
    	  return sessionStorage.getItem(key);
       },
       
       this.destroy = function() {
    	  $http.post('data/destroy_session.php');
    	  return sessionStorage.removeItem(key);
        
       }
       
       
       
    	
    	

    myApp.factory("loginService", loginService);

})();
