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
			
			.state('bookReviews', {
				url: "/book/reviews/:bookId",
				templateUrl: 'app/views/bookDetail.html',
				controller: 'ReviewController',
				reloadOnSearch: false
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
			
			.state('proposals', {
				url: "/proposal",
				templateUrl: 'app/views/proposal.html',
				controller: 'ProposalController'		
			})
			
			.state('pRegistration', {
				url: "/proposalRegister",
				templateUrl: 'app/views/proposals.registration.html',
				controller: 'ProposalRegistrationController'		
			})
			
			
			// nested states 
	        // each of these sections will have their own view
	        // url will be nested (/form/profile)
//	        .state('pRegistration.author', {
//	            url: '/proposalsAuthor',
//	            templateUrl: 'app/views/proposals.author.html'
//	        })
	        
	        // url will be /form/interests
//	        .state('pRegistration.book', {
//	            url: '/proposalsBook',
//	            templateUrl: 'app/views/proposals.book.html'
//	        })
	        
//	        // url will be /form/payment
//	        .state('form.payment', {
//	            url: '/payment',
//	            templateUrl: 'form-payment.html'
//	        });
//			
//			
//			
//			.state('bla', {
//				url: "/bla",
//			    templateUrl: 'app/views/proposals.author.html',
//			    controller: 'ProposalController'	
//			 });
//			
		
			
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





