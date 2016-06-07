(function() {
     //This controller job is to perform proposal actions
	function ProposalController($rootScope,$scope, CommonFactory,proposalStateService, proposalService ,userService,AuthenticationService,$location) {
		$scope.proposals = [];
		$scope.proposal ={};
		
		$scope.show = 'all';

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
		
		function applyRemoteData(proposals) {
			$scope.proposals = proposals;
		}
		// get all proposal
		function getProposals() {
			proposals = {};
			if ($scope.user.userType == 'administrator') {
				proposals = getAllProposals();
			} else {
				proposals = getUserProposals($scope.user.userId);
			}

		}
		
		function filterProposals(proposals) {
			if ($scope.show == 'all') {
				$scope.proposals = proposals;
			} else {
				$scope.proposals = [];
				for (i=0; i<proposals.length; i++) {
					if (proposals[i].stateHistory[0].proposalStatus == $scope.show) {
						$scope.proposals.push(proposals[i]);
					}
				}
			}
		}
		
		//get all proposals 
		function getAllProposals() {
			proposals = proposalService.getProposals().then(function(proposals) {
				filterProposals(proposals);
				
				return proposals;
			});
			
			return proposals;
		}
		
		// get specific proposal
		function getUserProposals(userId) {
			proposals = proposalService.getUserProposals(userId).then(function(proposal) {
				filterProposals(proposal);	
				return proposal;
			});
			
			return proposals;
		}
		
		//Remove existing proposal 
		$scope.removeProposal = function(id) {
			bootbox.confirm("Are you sure you want to delete this proposal?", function(result) {
				  if (result) {
						//remove proposal by id and then get the current proposal list
						proposalService.removeProposal(id).then(function(response) {
							$scope.response = response;
							CommonFactory.checkReponse('Proposal remove action was failed' , response)
							getProposals();	
						});
					}
			}); 
		};
		
		//use for deny and approve function
		$scope.changeProposalStatus = function(id,status,message) {
			proposalService.getProposal(id).then(function(proposal) {
				$scope.proposal = proposal;
				
				if (message == null) {
					message =  $scope.user.userName + ": proposal id " + id + " was " + status;
				} else {
					message = $scope.user.userName + ": " + message;
				}
				
				state = {"proposalDate":  CommonFactory.getCurrentDate() , "proposalStatus": status , "comment" : message};
				
				//add proposal state
				proposalStateService.addProposalState(id,state).then(function(response) {
				   if (!response.success) {
					   CommonFactory.sendInfoPopUpMessage('Failed adding new proposal state ', 'Failed adding new proposal state for book ' + proposal.book.name);
					   return;
				   }  else {
					   CommonFactory.sendInfoPopUpMessage('proposal was update','proposal id ' + $scope.proposal.proposalId + ' for book name ' 
							 + proposal.book.name +  ' was ' + status);
					   
					   //update result
					   getProposals();
					   
					   uType = $scope.proposal.user.userType;
						
					   //points check
					   if ($scope.proposal.user.userId ==  $scope.user.userId) {
							//popup message only if
							userService.getuser($scope.proposal.user.userId).then(function(user) {
								   //if user reach admin point and its status different then administrator	
								   if ((user.points >=  CommonFactory.adminPointsLimit) && (uType != 'administrator')) {
									   CommonFactory.popupAdminLimitMessage();
									   AuthenticationService.setCredentials('ibdb_token',user);
								   }
							});
					   } 
					   
					   //send mail
					   proposal.stateHistory[0] = state;
					   
					   proposalStateService.sendMail(proposal).then(function(response) {
						   $scope.response = response;
						   CommonFactory.checkReponse('Failed update user via mail about proposal state change' , response);
					   });
					   
					   
					   
				   }
				});

			});
		} 
		
		//approve proposal
		$scope.approveProposal = function(id) {
			$scope.changeProposalStatus(id,'approved', null);
		} 
		
		//deny the porposal
		$scope.denyProposal = function(id) {
			bootbox.prompt("please enter a reason for deny proposal", function(response) { 
				if (response !=null) {
					$scope.changeProposalStatus(id,'denied' , response);
				}
			});
			
		} 
		
		// porposal information is missing
		$scope.infoProposal = function(id) {
			bootbox.prompt("please enter what is missing in proposal detail?", function(response) {
				if (response !=null ){
					$scope.changeProposalStatus(id,'info' , response)
				}					
			});
			
		} 
		
		$scope.changeView = function(view){
		    $location.path(view);
		}
		
		// get all proposal
		$scope.getMyProposals = function(status) {
			proposalService.getStatusProposals(status,$scope.user).then(function(proposals) {
				applyRemoteData(proposals);
			});
		}
		
		$scope.filterTable = function(state) {
			$scope.show = state;
			//get most update proposals
			getProposals();
		}
	}

	myApp.controller("ProposalController", ProposalController);
})();

