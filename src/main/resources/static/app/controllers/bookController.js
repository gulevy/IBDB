(function() {

	function BooksController($scope, proposalService , bookService, bookCategoryService,authorService, $uibModal , $location) {
		$scope.proposals = [];
		$scope.proposal = {};
		$scope.book ={};
		$scope.selectedCategory = {};
		$scope.categories = {};
		$scope.authors = {};
		$scope.selectedAuthor = {};
		
		getBookCategories();
		getAuthors();
		
		initController();
		
		function initController() {
			getBooks();
		}
		
		// I load the remote data from the server.
		function getBooks() {
			// The friendService returns a promise.
			proposalService.getProposals().then(function(proposals) {
				$scope.proposals = proposals;
			});
		}
		
		function getBookCategories() {
			bookCategoryService.getBookCategories().then(function(categories) {
				$scope.categories = categories
				
				$scope.selectedCategory = $scope.categories[0];
			});
		}
		
		function getAuthors() {
			authorService.getAuthors().then(function(authors) {
				$scope.authors = authors
				$scope.selectedAuthor = $scope.authors[0];
			});
		}

		// I remove the given friend from the current collection.
		$scope.removeBook = function(id) {
			var isConfirmDelete = confirm('Are you sure you want this record?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				
				proposalService.removeProposal(id).then(function(response) {
					showMessage(response);			
					getBooks();
				});
				
//				bookService.removeBook(id).then(function(response) {
//					showMessage(response);			
//					getBooks();
//				});	
			} else {
				return false;
			}
		};

		//show modal form
		$scope.toggle = function(modalstate, id) {
			$scope.modalstate = modalstate;

			switch (modalstate) {
			case 'add':
				$scope.form_title = "Add New Book";
				$scope.book = {};

				break;
			case 'edit':
				
				 proposalService.getProposal(id).then(function(proposal) {
					$scope.form_title = "Edit Book id: " + proposal.book.bookId;
					$scope.id =  proposal.book.bookId;
					$scope.proposal = proposal;
					$scope.book = proposal.book
					$scope.book.releaseDate = new Date($scope.book.releaseDate);
					
				});
				
				break;
			default:
				break;
			}

			$('#bookModal').modal('show');
		}
		
		$scope.go = function(path) {
			$location.path(path);
		}

		//save new record / update existing record
		$scope.save = function(modalstate) {		
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				
				proposalService.editProposal($scope.proposal).then(function(response) {
					showMessage(response);
					getBooks()
					$('#bookModal').modal('hide');
				});
				
			} else {
				
				var timestamp = new Date().getUTCMilliseconds();
				imageName = timestamp + ".png";
				bookService.uploadBook('/upload',$scope.book.file,imageName).then(function(response) {
					showMessage(response);
				});
				
				$scope.book.imageName = imageName;
				
				var res = bookService.addBook($scope.book).then(function(response) {
					showMessage(response);
					$scope.response = response;
					getBooks()
					$('#bookModal').modal('hide');
				});
			}
		}	
	}
	
	function showMessage(response) {
		 if (!response.success) { 
			 alert(response.message)
		 }
	}

	myApp.controller("BookController", BooksController);

})();

