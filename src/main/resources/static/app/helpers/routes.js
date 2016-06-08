(function() {
    //single page route map
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
			
			.state('authors', {
				url: "/author",
				templateUrl: 'app/views/author.html',
				controller: 'AuthorController'		
			})
			
			.state('authorBooks', {
				url: "/author/books/:authorId",
				templateUrl: 'app/views/authorDetail.html',
				controller: 'AuthorDetailController',
				reloadOnSearch: false
			})
			
			.state('userBooks', {
				url: "/user/books/:userId",
				templateUrl: 'app/views/userBooks.html',
				controller: 'UserBookController',
			})
			
			.state('userEdit', {
				url: "/user/edit/:userId",
				templateUrl: 'app/views/register.html',
				controller: 'UserController'
			})
			
			.state('register', {
				url: "/register",
				templateUrl: 'app/views/register.html',
				controller: 'UserController'		
			})
			
			.state('users', {
				url: "/users/",
				templateUrl: 'app/views/users.html',
				controller: 'UserController'		
			})
			
			.state('proposals', {
				url: "/proposal",
				templateUrl: 'app/views/proposal.html',
				controller: 'ProposalController'		
			})
			
			.state('proposal_history', {
				url: "/proposalsHistory/:proposalId",
				templateUrl: 'app/views/proposalHistory.html',
				controller: 'ProposalHistoryController'		
			})
			
			.state('pRegistration', {
				url: "/proposalRegister",
				templateUrl: 'app/views/proposals.registration.html',
				controller: 'ProposalRegistrationController'		
			})
			
			.state('proposalEdit', {
				url: "/proposal/edit/:proposalId",
				templateUrl: 'app/views/proposals.registration.html',
				controller: 'ProposalRegistrationController'		
			})
					
	}]);


})();