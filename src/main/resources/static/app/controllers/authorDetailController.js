(function() {

	function AuthorDetailController($rootScope,$scope, proposalService,authorService ,$location , $state, $stateParams) {
		$scope.books = [];
		$scope.author = {};

		getAuthorBooks();
			
		// get all books related to specific author
		function getAuthorBooks() {
			//get author detail
			authorService.getAuthor($stateParams.authorId).then(function(author) {
				$scope.author = author;
			});
			
			// The friendService returns a promise.
			proposalService.getProposals().then(function(proposals) {
				for (var i in proposals) {
					if (proposals[i].book.author.authorId == $stateParams.authorId) {
						$scope.books.push(proposals[i].book)
					}
				}
			});
		}
		
		$scope.go = function(view){
		    $location.path(view);
		}
	}

	myApp.controller("AuthorDetailController", AuthorDetailController);
})();

