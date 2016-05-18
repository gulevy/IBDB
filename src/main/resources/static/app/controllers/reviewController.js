(function() {

	//This controller job is to perform review actions
	function ReviewController($rootScope,$scope,CommonFactory , reviewService ,$location, $state, $stateParams,bookService) {
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
				
		//Remove existing review
		$scope.removeReview = function(id) {
			var isConfirmDelete = confirm('Are you sure you want to delete this review?');
			if (isConfirmDelete) {
				//remove review by id and then get the current review list
				reviewService.removeReview(id).then(function(response) {
					CommonFactory.checkReponse('Review add action was failed' , response)	
					$scope.response = response;
					initController();	
				});
			} else {
				return false;
			}
		};
		
		//edit review
		$scope.edit = function(id) {
			reviewService.editReview($scope.review).then(function(response) {
				CommonFactory.checkReponse('Review edit action was failed' , response)
				$scope.response = response;
				initController();
			});
		} 
		
		//add review
		$scope.addReview = function() {
			$scope.review.user = $scope.user;
			$scope.review.book = $scope.book;
			
			var res = reviewService.addReview($scope.review,$scope.review.book.bookId).then(function(response) {
				CommonFactory.checkReponse('Review add action was failed' , response)
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
				CommonFactory.checkReponse('Review update action was failed' , response)
				$('#CommentModal').modal('hide');
				initController();
			});
			
		}	
			
	}

	myApp.controller("ReviewController", ReviewController);
})();

