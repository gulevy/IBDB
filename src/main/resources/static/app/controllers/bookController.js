(function() {

	function BooksController($scope, bookService, bookCategoryService, $uibModal) {
		$scope.books = [];
		$scope.book ={};
		$scope.categories = {};
		
		//getBookCategories();
		getBooks();
		
		function applyRemoteData(newBooks) {
			$scope.books = newBooks;
		}
		// I load the remote data from the server.
		function getBooks() {
			// The friendService returns a promise.
			bookService.getBooks().then(function(books) {
//				books.forEach(function(book) {
//					book["categoryName"] = $scope.categories[book.categoryId];
//				});
				
				applyRemoteData(books);
			});
		}
		
//		function getBookCategories() {
//			bookCategoryService.getBookCategories().then(function(books) {
//				
//				books.forEach(function(book) {
//					$scope.categories[book.id] = book.name;
//				});
//				
//			
//			});
//		}

		// I remove the given friend from the current collection.
		$scope.removeBook = function(id) {
			var isConfirmDelete = confirm('Are you sure you want this record?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				bookService.removeBook(id).then(function(response) {
					showMessage(response);
					
					getBooks();
				});	
			} else {
				return false;
			}
		};

//		//show modal form
		$scope.toggle = function(modalstate, id) {
			$scope.modalstate = modalstate;

			switch (modalstate) {
			case 'add':
				$scope.form_title = "Add New Book";

				break;
			case 'edit':
				$scope.form_title = "Edit Book id: " + id;
				$scope.id = id;
	
				bookService.getBook(id).then(function(book) {
					$scope.book = book
				});

				break;
			default:
				break;
			}

			$('#bookModal').modal('show');
		}

		//save new record / update existing record
		$scope.save = function(modalstate, id) {
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				alert("edit");
				bookService.editBook($scope.book).then(function(response) {
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
				
				 alert(res.value);
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

