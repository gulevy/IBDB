//(function() {
//
//    function loginService($http, $location,sessionService) {
//        
//       this.login = function(data,scope) {
//        	 var $promise = $http.post(user_authen);
//        	 $promise .then(function(msg)) {
//        	    var uid=msg.data;
//        	    
//        	    if (uid) {
//        	      sessionService.set('uid',uid);
//        	      $location.path('/home');
//        	    } else {
//        	    	scope.msgtxt = 'incorrect information';
//        	    	$location.path('/login');
//        	    }
//        	 
//        	 }
//        }
//       
//       this.logout = function() {
//      	 sessionService.destroy('user');
//      	 $location.path('/login');
//       }
//       
//       this.isLogin = function() {
////        	 var $checkSessionServer = $http.post('data/check_session');
////        	 return $checkSessionServer;
//    	   		return true;
//        }
//       
//       
//       
//    	
//    	
//
//    myApp.factory("loginService", loginService);
//
//})();
