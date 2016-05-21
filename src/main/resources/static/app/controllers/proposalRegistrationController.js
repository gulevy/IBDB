(function() {

	function ProposalRegistrationController($rootScope,$scope,authorService,userService,CommonFactory, bookService,bookCategoryService, proposalService ,$location, $state, $stateParams) {
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
				
				$scope.proposal.proposalDate = CommonFactory.getCurrentDate();
				$scope.proposal.proposalStatus = 'pending';				
			}
			
			username = $rootScope.logInUser;	
			$scope.user = $rootScope.user;
			$scope.proposal.user = $rootScope.user;
						
			//prepare the data for the drop down list authors
			authorService.getAuthors().then(function(authors) {
				$scope.authors = authors;
				
				for (i = 0; i < $scope.authors.length; i++) { 
					$scope.authors[i].dateOfBirth = new Date($scope.authors[i].dateOfBirth);
					$scope.authors[i].selectTitle = $scope.authors[i].firstName + " " + $scope.authors[i].lastName;
				}
				
				
				$scope.authors.push({"authorId":0,"firstName":"","lastName":"","linkWiki":"","dateOfBirth":"","selectTitle": "new"})
				
				$scope.proposal.book.author = $scope.authors[$scope.authors.length -1];
			});
		}
		
		function getBookCategories() {
			bookCategoryService.getBookCategories().then(function(categories) {
				$scope.categories = categories
				
				$scope.selectedCategory = $scope.categories[0];
			});
		}
		
		function applyRemoteData(proposals) {
			$scope.proposals = proposals;
		}
		
		//jump to different page
		$scope.changeView = function(view){
		    $location.path(view);
		}
		
		//add new proposal
		$scope.addProposal = function() {
			var timestamp = new Date().getUTCMilliseconds();
			imageName = timestamp + ".png";
			bookService.uploadBook('/upload',$scope.proposal.book.file,imageName,'books').then(function(response) {
				CommonFactory.checkReponse('Book image upload action was failed' , response)	
				$scope.response = response;
			});
			
			$scope.proposal.book.imageName = imageName;
			$scope.proposal.book.rate = 0 ;
			
			//add proposal
			var res = proposalService.addProposal($scope.proposal).then(function(response) {
				CommonFactory.checkReponse('Proposal add action was failed' , response)	
				$scope.response = response;
				$scope.changeView("/proposal");
			});
		}
		
		//edit proposal
		$scope.editProposal = function() {	
			var res = proposalService.editProposal($scope.proposal).then(function(response) {
				CommonFactory.checkReponse('Proposal edit action was failed' , response)	
				$scope.response = response;
				$scope.changeView("/proposal");
			});
		}

	}

	myApp.controller("ProposalRegistrationController", ProposalRegistrationController);
})();

