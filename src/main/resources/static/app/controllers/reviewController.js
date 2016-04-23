(function() {

	function ReviewController($rootScope,$scope,  reviewService ,$location) {
		$scope.reviews = [];
		$scope.review ={};

		initController();
		getReviews();
					
		function initController() {
			path = $location.path();
			
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit review detail';
				$scope.mode = 1;
			} else {
				$scope.title =  'Add new review';
				$scope.mode = 2;
			}
		}
		
		function applyRemoteData(newReviews) {
			$scope.reviews = newReviews;
		}
		// I load the remote data from the server.
		function getReviews() {
			// The friendService returns a promise.
			reviewService.getReviews().then(function(reviews) {
				applyRemoteData(reviews);
			});
		}

		// I remove the given friend from the current collection.
		$scope.removeReview = function(id) {
			var isConfirmDelete = confirm('Are you sure you want to delete this review?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				reviewService.removeReview(id).then(function(response) {
					$scope.response = response;
					getReviews();	
				});
			} else {
				return false;
			}
		};

		$scope.edit = function(id) {
			reviewService.editReview($scope.review).then(function(response) {
				$scope.response = response;
				getReviews();	
			});
		} 
		
		$scope.toggle = function(modalstate, id) {
			$scope.modalstate = modalstate;

			switch (modalstate) {
			case 'add':
				$scope.form_title = "Add New Review";
				$scope.review = {};

				break;
			case 'edit':
				$scope.form_title = "Edit Review id: " + id;
				$scope.id = id;
	
				reviewService.getReview(id).then(function(review) {
					$scope.review = review
				});

				break;
			default:
				break;
			}

			$('#reviewModal').modal('show');
		}

		//save new record / update existing record
		$scope.save = function(modalstate, id) {		
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				reviewService.editReview($scope.review).then(function(response) {
					getReviews()
					$('#reviewModal').modal('hide');
				});
			} else {
				var res = reviewService.addReview($scope.review).then(function(response) {
					$scope.response = response;
					getReviews()
					$('#reviewModal').modal('hide');
				});
			}
		}
	}

	myApp.controller("ReviewController", ReviewController);
})();

