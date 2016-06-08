(function() {

	function ProposalRegistrationController($rootScope,$scope,authorService,userService,CommonFactory, bookService,bookCategoryService, proposalStateService,proposalService ,$location, $state, $stateParams) {
		$scope.proposal = {};
		$scope.proposal.book = {};
		$scope.proposal.book.author = {};
		$scope.categories = {};
		$scope.authors  = [];
		initController();
		getBookCategories();
		
		function initController() {
			path = $location.path();
			
			//prepare the data for the drop down list authors
			authorService.getAuthors().then(function(authors) {
				$scope.authors = authors;
				
				for (i = 0; i < $scope.authors.length; i++) { 
					$scope.authors[i].dateOfBirth = new Date($scope.authors[i].dateOfBirth);
					$scope.authors[i].selectTitle = $scope.authors[i].firstName + " " + $scope.authors[i].lastName;
				}
				
				$scope.authors.push({"authorId":0,"firstName":"","lastName":"","linkWiki":"","dateOfBirth":"","selectTitle": "new"});
				
				
				//first prepare a author list and only then continue
				
				if (path.indexOf('edit') > 0 ) {
					//edit proposal mode
					$scope.title = 'Edit Proposal detail';
					$scope.mode = 1;
					
					//try to get request proposal
					$scope.proposal = proposalService.getProposal($stateParams.proposalId).then(function(proposal) {	
						$scope.proposal = proposal;
						$scope.proposal.book.author.dateOfBirth = new Date($scope.proposal.book.author.dateOfBirth);
						$scope.proposal.book.releaseDate = new Date($scope.proposal.book.releaseDate);
					});
					
				} else {
					//new proposal mode
					$scope.title =  'Help us increase book knowledge and start adding books';
					$scope.mode = 2;
					
					$scope.proposal.stateHistory = [];
					$scope.proposal.stateHistory.push({"proposalDate":  CommonFactory.getCurrentDate() , "proposalStatus": "pending" , "comment" : "waiting for admin approval"})			
					$scope.proposal.book.author = $scope.authors[$scope.authors.length -1];
				}			
			});
			
			username = $rootScope.logInUser;	
			$scope.user = $rootScope.user;
			$scope.proposal.user = $rootScope.user;
		}
		
		function getBookCategories() {
			bookCategoryService.getBookCategories().then(function(categories) {
				$scope.categories = categories;
				
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

			if ($scope.proposal.book.file == undefined) {
				imageName = "book_default.png";
				//give default image because user didnot select photo
			} else {
				var timestamp = new Date().getUTCMilliseconds();
				imageName = timestamp + ".png";
				//upload new photo 
				bookService.uploadBook('/upload',$scope.proposal.book.file,imageName,'books').then(function(response) {
					CommonFactory.checkReponse('Book image upload action was failed' , response);	
					$scope.response = response;
				});
			}
			
			$scope.proposal.book.imageName = imageName;
			$scope.proposal.book.rate = 0 ;
			
			//add proposal
			var res = proposalService.addProposal($scope.proposal).then(function(response) {
				CommonFactory.checkReponse('Proposal add action was failed' , response);	
				$scope.response = response;
				$scope.changeView("/proposal");
			});
		}
		
		//edit proposal
		$scope.editProposal = function() {	
			var res = proposalService.editProposal($scope.proposal).then(function(response) {
				CommonFactory.checkReponse('Proposal edit action was failed' , response);
				$scope.response = response;
				
				//change proposal state to pending
				$scope.pendingProposal($scope.proposal.proposalId);				
				$scope.changeView("/proposal");
			});
		}
		
		
		//pending the porposal
		$scope.pendingProposal = function(id) {
			bootbox.prompt("Your proposal will move to pending please add state comment.", function(comment) {                
				if (comment == null) {
					comment = $scope.user.userName + ": proposal id " + id + " was moved to pending after edit proposal detail" ;
				} else {
					comment = $scope.user.userName + ": " + comment;
				}
				
				state = {"proposalDate":  CommonFactory.getCurrentDate() , "proposalStatus": 'pending' , "comment" : comment};
				
				//add proposal state
				proposalStateService.addProposalState(id,state).then(function(response) {
				   if (!response.success) {
					   CommonFactory.sendInfoPopUpMessage('Failed adding new proposal state ', 'Failed adding new proposal state ');
				       return;
				   }  else {
					   CommonFactory.sendInfoPopUpMessage('proposal was update','proposal id ' + id + ' was ' + status);
				   }
				});
				
			});
		} 		
	}

	myApp.controller("ProposalRegistrationController", ProposalRegistrationController);
})();

