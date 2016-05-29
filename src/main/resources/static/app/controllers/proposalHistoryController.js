(function() {
     //This controller job is to perform proposal actions
	function ProposalHistoryController($rootScope,$scope, CommonFactory, proposalService ,$location, $state, $stateParams) {
		$scope.proposal ={};

		initController();
	
		function initController() {
			proposalService.getProposal($stateParams.proposalId).then(function(proposal) {
				applyRemoteData(proposal);
			});
			
			path = $location.path();
			
			username = $rootScope.logInUser;	
			$scope.user = $rootScope.user;
		}
		
		function applyRemoteData(proposal) {
			$scope.proposal = proposal;
		}
	}

	myApp.controller("ProposalHistoryController", ProposalHistoryController);
})();

