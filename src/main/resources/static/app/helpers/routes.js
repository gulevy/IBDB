(function() {

	myApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
		$urlRouterProvider.otherwise('/');

		$stateProvider
			.state('login', {
				url: "/login",
				templateUrl: 'app/views/login.html',
				controller: 'LoginController'
			})
		
			.state('main', {
				url: "/",
				templateUrl: 'app/views/main.html',
				controller: 'LoginController',
				authenticated: true
			})
			.state('books', {
				url: "/books/",
				templateUrl: 'app/views/books.html',
				controller: 'BookController'
			})
			
			.state('users', {
				url: "/users/",
				templateUrl: 'app/views/users.html',
				controller: 'UserController'
			})
			
			.state('register', {
				url: "/register",
				templateUrl: 'app/views/register.html',
				controller: 'UserController'
			})
			
		
			
	}]);


})();






//resolve: {
//    checkLogin:
//      function($location, $rootScope) {
//        if (!$rootScope.isLogin) {
//        	$location.path('/');
//        } else {
//        	templateUrl: 'app/views/main.html'
//        }
//      }
//    }





