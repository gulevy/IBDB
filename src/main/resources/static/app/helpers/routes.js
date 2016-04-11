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
				controller: 'MainController'
			})
			.state('books', {
				url: "/books/",
				templateUrl: 'app/views/books.html',
				controller: 'BookController'
			})
			
			.state('userEdit', {
				url: "/user/edit",
				templateUrl: 'app/views/register.html',
				controller: 'UserController'
	            
			})
			
			.state('register', {
				url: "/register",
				templateUrl: 'app/views/register.html',
				controller: 'UserController'		
			})
			
			.state('authors', {
				url: "/author",
				templateUrl: 'app/views/author.html',
				controller: 'AuthorController'		
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





