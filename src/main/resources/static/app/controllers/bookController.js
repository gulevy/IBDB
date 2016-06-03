(function() {
    //This controller job is to perform book crud action
	function BooksController($scope,NgTableParams,CommonFactory, proposalService , bookService, bookCategoryService,authorService, $uibModal , $location) {
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
		
		//get all books
		function getBooks() {
			// get all book proposal 
			proposalService.getProposals().then(function(proposals) {
				$scope.proposals = proposals;
				 $scope.data = $scope.proposals;
				 $scope.tableParams = new NgTableParams({}, { dataset:  $scope.data });
			});
		}
		
		//get all book categories
		function getBookCategories() {
			bookCategoryService.getBookCategories().then(function(categories) {
				$scope.categories = categories
				
				$scope.selectedCategory = $scope.categories[0];
			});
		}
		
		//get all authors
		function getAuthors() {
			authorService.getAuthors().then(function(authors) {
				$scope.authors = authors
				$scope.selectedAuthor = $scope.authors[0];
			});
		}

		// remove existing book
		$scope.removeBook = function(id) {
			bootbox.confirm('Are you sure you want to delete this book ?' , function(result) {
				  if (result) {
					    //remove book by id and then get the current book list
						proposalService.removeProposal(id).then(function(response) {
							CommonFactory.checkReponse('Book remove action was failed' , response);			
							getBooks();
						});
				  } else {
					return false;
				  }
			}); 
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
		
		$scope.popupImage = function(imagePath) {
			CommonFactory.popupImage("Image viewer" , imagePath)
		}

		//save new record / update existing record
		$scope.save = function(modalstate) {		
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				
				proposalService.editProposal($scope.proposal).then(function(response) {
					CommonFactory.checkReponse('Book edit action was failed' , response)
					getBooks()
					$('#bookModal').modal('hide');
				});
				
			} else {
				
				var timestamp = new Date().getUTCMilliseconds();
				imageName = timestamp + ".png";
				bookService.uploadBook('/upload',$scope.book.file,imageName).then(function(response) {
					CommonFactory.checkReponse('Book image upload action was failed' , response)
				});
				
				$scope.book.imageName = imageName;
				
				//add new book and update the book list
				var res = bookService.addBook($scope.book).then(function(response) {
					CommonFactory.checkReponse('Book add action was failed' , response)
					$scope.response = response;
					getBooks()
					$('#bookModal').modal('hide');
				});
			}
		}	
	}
	
	myApp.controller("BookController", BooksController);

})();

