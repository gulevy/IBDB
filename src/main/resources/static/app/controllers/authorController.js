(function() {

	function AuthorController($rootScope,$scope,  authorService ,$location) {
		$scope.authors = [];
		$scope.author ={};
		
		
	    
		
		initController();
		getAuthors();
		
		$scope.gridOptions1 = {
			    paginationPageSizes: [25, 50, 75],
			    paginationPageSize: 25,
			    columnDefs: [
			      { name: 'name' },
			      { name: 'gender' },
			      { name: 'company' }
			    ]
	   };
		
		
		
		$scope.gridOptions1.data  = $scope.authors,
		
		function initController() {
			path = $location.path();
			
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit Author detail';
				$scope.mode = 1;
			} else {
				$scope.title =  'Add new Author';
				$scope.mode = 2;
			}
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
			var isConfirmDelete = confirm('Are you sure you want this record?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				authorService.removeAuthor(id).then(function(response) {
					$scope.response = response;
					getAuthors();	
				});
					
				$location.path('/login');
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
	}

	myApp.controller("AuthorController", AuthorController);
})();

