(function() {

	function ProposalRegistrationController($rootScope,$scope,authorService,userService, bookService,bookCategoryService, proposalService ,$location, $state, $stateParams) {
		$scope.proposal = {};
		$scope.proposal.book = {};
		$scope.proposal.book.author = {};
		$scope.categories = {};
		$scope.authors  = [];
		initController();
		getBookCategories();
		
		function initController() {
			path = $location.path();
			
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit Proposal detail';
				$scope.mode = 1;
				
				$scope.proposal = proposalService.getProposal($stateParams.proposalId).then(function(proposal) {	
					$scope.proposal = proposal;
					$scope.proposal.book.author.dateOfBirth = new Date($scope.proposal.book.author.dateOfBirth);
					$scope.proposal.book.releaseDate = new Date($scope.proposal.book.releaseDate);
				});
				
			} else {
				$scope.title =  'Help us increase book knowledge and start adding books';
				$scope.mode = 2;
				
				$scope.proposal.proposalDate = getCurrentDate();
				$scope.proposal.proposalStatus = 'pending';				
			}
			
			username = $rootScope.logInUser;	
			$scope.user = $rootScope.user;
			$scope.proposal.user = $rootScope.user;
						
			authorService.getAuthors().then(function(authors) {
				$scope.authors = authors;
				
				for (i = 0; i < $scope.authors.length; i++) { 
					$scope.authors[i].dateOfBirth = new Date($scope.authors[i].dateOfBirth);
					$scope.authors[i].selectTitle = $scope.authors[i].firstName + " " + $scope.authors[i].lastName;
				}
				
				$scope.authors.push({"authorId":-1,"firstName":"","lastName":"","linkWiki":"","dateOfBirth":"","selectTitle": "new"})
				
				$scope.proposal.book.author = $scope.authors[$scope.authors.length -1];
			});
		}
		
		function getBookCategories() {
			bookCategoryService.getBookCategories().then(function(categories) {
				$scope.categories = categories
				
				$scope.selectedCategory = $scope.categories[0];
			});
		}
		
		function getCurrentDate(){
			a = new Date();
			b = a.getFullYear();
			c = a.getMonth();
			(++c < 10)? c = "0" + c : c;
			
			d = a.getDate();
			(d < 10)? d = "0" + d : d;
			
			convertedDate = b + "-" + c + "-" + d; 

			return convertedDate;
		} 
		
		function applyRemoteData(proposals) {
			$scope.proposals = proposals;
		}
		
		$scope.changeView = function(view){
		    $location.path(view);
		}
		
		$scope.addProposal = function() {
			var timestamp = new Date().getUTCMilliseconds();
			imageName = timestamp + ".png";
			bookService.uploadBook('/upload',$scope.proposal.book.file,imageName,'books').then(function(response) {
				$scope.response = response;
			});
			
			$scope.proposal.book.imageName = imageName;
			$scope.proposal.book.rate = 0 ;
			
			var res = proposalService.addProposal($scope.proposal).then(function(response) {
				$scope.response = response;
				$scope.changeView("/proposal");
			});
		}
		
		
		$scope.editProposal = function() {	
			var res = proposalService.editProposal($scope.proposal).then(function(response) {
				$scope.response = response;
				$scope.changeView("/proposal");
			});
		}

	}

	myApp.controller("ProposalRegistrationController", ProposalRegistrationController);
})();

