(function() {

	myApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
		$urlRouterProvider.otherwise('/');

		$stateProvider
			.state('login', {
				url: "/",
				templateUrl: 'app/views/login.html',
				controller: 'UserController'
			})
		
			.state('main', {
				url: "/dashboard",
				templateUrl: 'app/views/main.html',
				controller: 'UserController',
				resolve: {
				    checkLogin:
				      function($location, $rootScope) {
				        if (!$rootScope.isLogin) {
				        	$location.path('/');
				        } else {
				        	templateUrl: 'app/views/main.html'
				        }
				      }
				    }
			   
				
			})
			.state('books', {
				url: "/books/",
				templateUrl: 'app/views/books.html',
				controller: 'BookController'
			})
			
		
			
	}]);


})();












