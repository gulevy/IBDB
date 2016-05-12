(function() {

	function ReviewController($rootScope,$scope,reviewService ,$location, $state, $stateParams,bookService) {
		//$scope.reviews = [];
		$scope.review ={};	
		$scope.updateReview = {}
		$scope.book = {};
	
		initController();
					
		function initController() {
			$scope.book = bookService.getBook($stateParams.bookId).then(function(book) {	
				$scope.book = book;
				
				sum = 0;
				count =0;
				$scope.avgRating = 0;
				
				for (var i in $scope.book.reviews) {
					sum += $scope.book.reviews[i].rating;
					++count;
				}
				
				$scope.avgRating = sum/count;
				$scope.totalReviews = count; 
			});
			
			username = $rootScope.logInUser;
			$scope.user = $rootScope.user;
		}
				
		// I remove the given friend from the current collection.
		$scope.removeReview = function(id) {
			var isConfirmDelete = confirm('Are you sure you want to delete this review?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				reviewService.removeReview(id).then(function(response) {
					$scope.response = response;
					initController();	
				});
			} else {
				return false;
			}
		};
		
		$scope.edit = function(id) {
			reviewService.editReview($scope.review).then(function(response) {
				$scope.response = response;
				initController();
		//		getReviews();	
			});
		} 
		
		$scope.addReview = function() {
			$scope.review.user = $scope.user;
			$scope.review.book = $scope.book;
			
			var res = reviewService.addReview($scope.review,$scope.review.book.bookId).then(function(response) {
				$scope.response = response;	
				
				initController();
			});
		}
		
		$scope.showEditModel = function(id) {
			reviewService.getReview(id).then(function(review) {
				$scope.updateReview = review		
			});
			
			$('#CommentModal').modal('show');
		}
		
		$scope.save = function(id) {
			//save review update
			reviewService.editReview($scope.updateReview).then(function(response) {
				$scope.response = response;	
				$('#CommentModal').modal('hide');
				initController();
			});
			
		}	
			
	}

	myApp.controller("ReviewController", ReviewController);
})();

