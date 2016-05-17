var myApp = angular.module("ibdbApp", [ 'ui.router' ,'ngTouch' ,'ngAnimate','ui.bootstrap', 'ngCookies']);

myApp.run(['$rootScope', '$location', 'AuthenticationService', function($rootScope,$location,AuthenticationService) {
          $rootScope.$on('$locationChangeStart', function (event, next, current) {
              // redirect to login page if not logged in
        	  token = AuthenticationService.getCredentials('ibdb_token')
  
        	  //parse only the uri from the full link
        	  var baseLen = $location.absUrl().length - $location.url().length;
        	  $rootScope.back =  current.substring(baseLen);

        	  //if cookie cannot be found move user to login page
        	  if (token) {
        		  $rootScope.logInUser = token.user.userName;
        		  $rootScope.user = token.user;
        		  $rootScope.isLogin =   function(key) {return true;};
        		  $rootScope.logout =   function() {res =  AuthenticationService.logout('ibdb_token'); $rootScope.logInUser = ""; $location.path('/login');return res;};
        		  
        	  } else{
        		  
        		  var allowedPage = $.inArray($location.path(), ['/login', '/register']) != -1;
        		  
        		  if (!allowedPage) {
        			  $location.path('/login');
        		  }
        	  }
          });

}]);