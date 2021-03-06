(function() {

	//This controller job is to perform review actions
	function ReviewController($rootScope,$scope,CommonFactory , userService ,AuthenticationService, reviewService ,$location, $state, $stateParams,bookService) {
		//$scope.reviews = [];
		$scope.review ={};	
		$scope.updateReview = {}
		$scope.book = {};
		$scope.isReviewed = false;
		initController();
					
		function initController() {
			username = $rootScope.logInUser;
			$scope.user = $rootScope.user;
			
			$scope.book = bookService.getBook($stateParams.bookId).then(function(book) {
				$scope.isReviewed = false;
				$scope.book = book;
				
				sum = 0;
				count =0;
				$scope.avgRating = 0;
				
				for (var i in $scope.book.reviews) {
					if ($scope.book.reviews[i].user.userId == $scope.user.userId) {
						$scope.isReviewed = true;
					}
					
					sum += $scope.book.reviews[i].rating;
					++count;
				}
				
				if (sum ==0 ) {
					$scope.avgRating = 0 ;
				} else {
					$scope.avgRating = sum/count;
				}
				
				$scope.totalReviews = count; 
			});
		}
				
		//Remove existing review
		$scope.removeReview = function(id) {
			bootbox.confirm('Are you sure you want to delete this review?', function(result) {
				if (result) {
					 //remove review by id and then get the current review list
					 reviewService.removeReview(id,$stateParams.bookId).then(function(response) {
					      CommonFactory.checkReponse('Review remove action was failed' , response)	
						  $scope.response = response;
						  initController();	
					 });
				} 
			}); 
		};
			
		//add review
		$scope.addReview = function() {
			$scope.review.user = $scope.user;
			$scope.review.book = $scope.book;
			
			if ($scope.isReviewed) {
				CommonFactory.sendPopUpMessage('Review submit deny' , 'Hi ' + $scope.user.firstName  + ' ' +  $scope.user.lastName + ' you cannot review book because you already reviewed it.');
				$scope.response = {'success' : false , 'message': 'You cannot review book twice'};
				
				return;
			}
			
			var res = reviewService.addReview($scope.review,$scope.review.book.bookId).then(function(response) {
				CommonFactory.checkReponse('Review add action was failed' , response)
				$scope.response = response;	
				
				status = $scope.review.user.userType;
				
				initController();
				
				userService.getuser($scope.review.user.userId).then(function(user) {
					if ((user.points >=  CommonFactory.adminPointsLimit) && (status != 'administrator')) {
						CommonFactory.popupAdminLimitMessage();
						AuthenticationService.setCredentials('ibdb_token',user);
					}
					
				});
			});
			
			
		}
		
		// make modal visible
		$scope.showEditModel = function(id) {
			$scope.response = {};
			reviewService.getReview(id).then(function(review) {
				$scope.updateReview = review		
			});
			
			$('#CommentModal').modal('show');
		}
		
		$scope.save = function(id) {
			//save review update
			reviewService.editReview($scope.updateReview,$stateParams.bookId).then(function(response) {
				CommonFactory.checkReponse('Review update action was failed' , response)
				$('#CommentModal').modal('hide');
				initController();
			});
			
		}	
			
	}

	myApp.controller("ReviewController", ReviewController);
})();

