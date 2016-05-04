(function() {

	function ProposalController($rootScope,$scope , proposalService ,$location) {
		$scope.proposals = [];
		$scope.proposal ={};

		initController();
		getProposals();
					
		function initController() {
			path = $location.path();
			
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit Proposal detail';
				$scope.mode = 1;
			} else {
				$scope.title =  'Add new Proposal';
				$scope.mode = 2;
				
				
				$scope.proposal.proposalDate = getCurrentDate();
				$scope.proposal.proposalStatus = 'pending';
								
			}
			
			username = $rootScope.logInUser;	
			$scope.user = $rootScope.user;
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
		// I load the remote data from the server.
		function getProposals() {
			// The friendService returns a promise.
			proposalService.getProposals().then(function(proposals) {
				applyRemoteData(proposals);
			});
		}

		// I remove the given friend from the current collection.
		$scope.removeProposal = function(id) {
			var isConfirmDelete = confirm('Are you sure you want to delete this proposal?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				proposalService.removeProposal(id).then(function(response) {
					$scope.response = response;
					getProposals();	
				});
			} else {
				return false;
			}
		};
		
		$scope.changeProposalStatus = function(id,status) {
			proposalService.getProposal(id).then(function(proposal) {
				$scope.proposal = proposal
				$scope.proposal.proposalStatus =  status		
				$scope.edit();
			});
		} 
		
		$scope.approveProposal = function(id) {
			$scope.changeProposalStatus(id,'approved')
		} 
		
		$scope.denyProposal = function(id) {
			$scope.changeProposalStatus(id,'denied')	
		} 
		
		$scope.addProposal = function() {
			alert($scope.proposal);
		}
		
		$scope.changeView = function(view){
		    $location.path(view);
		}

		$scope.edit = function() {
			proposalService.editProposal($scope.proposal).then(function(response) {
				$scope.response = response;
				getProposals();	
			});
		} 
		
		$scope.toggle = function(modalstate, id) {
			$scope.modalstate = modalstate;

			switch (modalstate) {
			case 'add':
				$scope.form_title = "Add New Proposal";
				$scope.proposal = {};

				break;
			case 'edit':
				$scope.form_title = "Edit Proposal id: " + id;
				$scope.id = id;
	
				proposalService.getProposal(id).then(function(proposal) {
					$scope.proposal = proposal
				});

				break;
			default:
				break;
			}

			$('#proposalModal').modal('show');
		}
		
		
		

		//save new record / update existing record
		$scope.save = function(modalstate, id) {		
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				proposalService.editProposal($scope.proposal).then(function(response) {
					getProposals()
					$('#proposalModal').modal('hide');
				});
			} else {
				var res = proposalService.addProposal($scope.proposal).then(function(response) {
					$scope.response = response;
					getProposals()
					$('#proposalModal').modal('hide');
				});
			}
		}
	}

	myApp.controller("ProposalController", ProposalController);
})();

