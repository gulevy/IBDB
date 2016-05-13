(function() {

	function AuthorController($rootScope,$scope,  authorService ,$location) {
		$scope.authors = [];
		$scope.author ={};
		$scope.user = {};

		initController();
		getAuthors();
					
		function initController() {
			path = $location.path();
			
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
		// I load the remote data from the server.
		function getAuthors() {
			// The friendService returns a promise.
			authorService.getAuthors().then(function(authors) {
				applyRemoteData(authors);
			});
		}

		// I remove the given friend from the current collection.
		$scope.removeAuthor = function(id) {
			var isConfirmDelete = confirm('Are you sure you want to delete this author?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				authorService.removeAuthor(id).then(function(response) {
					$scope.response = response;
					getAuthors();	
					
					showMessage(response)
				});
			} else {
				return false;
			}
		};

		$scope.edit = function(id) {
			authorService.editAuthor($scope.author).then(function(response) {
				$scope.response = response;
				getAuthors();	
			});
		} 
		
		$scope.toggle = function(modalstate, id) {
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
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				authorService.editAuthor($scope.author).then(function(response) {
					getAuthors()
					$('#authorModal').modal('hide');
				});
			} else {
				var res = authorService.addAuthor($scope.author).then(function(response) {
					$scope.response = response;
					getAuthors()
					$('#authorModal').modal('hide');
				});
			}
		}
		
		$scope.go = function(path) {
			$location.path(path);
		}
		
		function showMessage(response) {
			 if (!response.success) { 
				 alert(response.message)
			 }
		}
	}

	myApp.controller("AuthorController", AuthorController);
})();

