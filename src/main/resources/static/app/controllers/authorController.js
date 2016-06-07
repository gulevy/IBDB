(function() {
    //This controller job is to perform author crud actions
	function AuthorController($rootScope,$scope, CommonFactory, authorService ,$location) {
		$scope.authors = [];
		$scope.author ={};
		$scope.user = {};

		initController();
		getAuthors();
					
		function initController() {
			path = $location.path();
			
			//select page mode
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit Author detail';
				$scope.mode = 1;
			} else {
				$scope.title =  'Add new Author';
				$scope.mode = 2;
			}
			
			$scope.user = $rootScope.user;
		}
		
		function applyRemoteData(newAuthors) {
			$scope.authors = newAuthors;
		}

		function getAuthors() {
			//get all authors
			authorService.getAuthors().then(function(authors) {
				applyRemoteData(authors);
			});
		}

		// remove existing author
		$scope.removeAuthor = function(id) {
			bootbox.confirm('Are you sure you want to delete this author?', function(result) {
				  if (result) {
					    //remove book by id and then get the current book list
						authorService.removeAuthor(id).then(function(response) {
							$scope.response = response;
							CommonFactory.checkReponse('Author remove action was failed' , response);
							getAuthors();	
						});
					} else {
						return false;
					}
			}); 
		};

		//edit existing author
		$scope.edit = function(id) {
			authorService.editAuthor($scope.author).then(function(response) {
				$scope.response = response;
				CommonFactory.checkReponse('Author edit action was failed' , response)
				getAuthors();	
			});
		} 
		
		$scope.toggle = function(modalstate, id) {
			$scope.response = {};
			$scope.modalstate = modalstate;

			switch (modalstate) {
			case 'add':
				$scope.form_title = "Add New Author";
				$scope.author = {};

				break;
			case 'edit':
				$scope.form_title = "Edit Author id: " + id;
				$scope.id = id;
	
				authorService.getAuthor(id).then(function(author) {
					$scope.author = author
				});

				break;
			default:
				break;
			}

			$('#authorModal').modal('show');
		}

		//save new record / update existing record
		$scope.save = function(modalstate, id) {		
		
			if ($scope.modalstate == 'edit') {
				authorService.editAuthor($scope.author).then(function(response) {
					CommonFactory.checkReponse('Author edit action was failed' , response)
					getAuthors()
					$('#authorModal').modal('hide');
				});
			} else {
				var res = authorService.addAuthor($scope.author).then(function(response) {
					CommonFactory.checkReponse('Author add action was failed' , response)
					$scope.response = response;
					getAuthors()
					$('#authorModal').modal('hide');
				});
			}
		}
		
		$scope.go = function(path) {
			$location.path(path);
		}
	}

	myApp.controller("AuthorController", AuthorController);
})();

