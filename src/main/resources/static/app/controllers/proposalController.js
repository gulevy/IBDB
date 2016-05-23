(function() {
     //This controller job is to perform proposal actions
	function ProposalController($rootScope,$scope, CommonFactory, proposalService ,$location) {
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
				
				$scope.proposal.proposalDate = CommonFactory.getCurrentDate();
				$scope.proposal.proposalStatus = 'pending';			
			}
			
			username = $rootScope.logInUser;	
			$scope.user = $rootScope.user;
		}
		
		//Get the machine current time 
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
		// get all proposal
		function getProposals() {
			if ($scope.user.userType == 'administrator') {
				getAllProposals();
			} else {
				getUserProposals($scope.user.userId);
			}
		}
		
		//get all proposals 
		function getAllProposals() {
			proposalService.getProposals().then(function(proposals) {
				applyRemoteData(proposals);
			});
		}
		
		// get specific proposal
		function getUserProposals(userId) {
			proposalService.getUserProposals(userId).then(function(proposal) {
				applyRemoteData(proposal);
			});
		}
		
		//Remove existing proposal 
		$scope.removeProposal = function(id) {
			var isConfirmDelete = confirm('Are you sure you want to delete this proposal?');
			if (isConfirmDelete) {
				//remove proposal by id and then get the current proposal list
				proposalService.removeProposal(id).then(function(response) {
					$scope.response = response;
					CommonFactory.checkReponse('Proposal remove action was failed' , response)
					getProposals();	
				});
			} else {
				return false;
			}
		};
		
		//use for deny and approve function
		$scope.changeProposalStatus = function(id,status) {
			proposalService.getProposal(id).then(function(proposal) {
				$scope.proposal = proposal
				$scope.proposal.proposalStatus =  status		
				$scope.edit();
				
				CommonFactory.sendInfoPopUpMessage('proposal was update','proposal id ' + $scope.proposal.proposalId + ' was ' + $scope.proposal.proposalStatus)
				
				status = $scope.proposal.user.userType;
				
				
				if ($scope.proposal.user.userId ==  $scope.user.userId) {
					//popup message only if
					userService.getuser($scope.proposal.user.userId).then(function(user) {
						   //if user reach admin point and its status different then administrator	
						   if ((user.points >=  CommonFactory.adminPointsLimit) && (status != 'administrator')) {
							   CommonFactory.popupAdminLimitMessage();
							   AuthenticationService.setCredentials('ibdb_token',user);
						   }
					});
				} 
				
				
			});
		} 
		
		//approve proposal
		$scope.approveProposal = function(id) {
			$scope.changeProposalStatus(id,'approved')
		} 
		
		//deny the porposal
		$scope.denyProposal = function(id) {
			$scope.changeProposalStatus(id,'denied')	
		} 
		
		$scope.changeView = function(view){
		    $location.path(view);
		}

		//edit existing proposal
		$scope.edit = function() {
			proposalService.editProposal($scope.proposal).then(function(response) {
				CommonFactory.checkReponse('Proposal edit action was failed' , response)
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

