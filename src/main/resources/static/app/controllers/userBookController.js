(function() {

	// control the author detail page
	function UserBookController($rootScope,$scope, proposalService,userService ,$location , $state, $stateParams) {
		$scope.userBooks = [];
		$scope.nodes = null;
		$scope.edges = null;
		$scope.network = null;

		$scope.DIR = '../../assets/images/';
		$scope.EDGE_LENGTH_MAIN = 150;
		$scope.EDGE_LENGTH_SUB = 50;
		
		// Create a data table with nodes.
	    $scope.nodes = [];

	      // Create a data table with links.
	    $scope.edges = [];
	    $scope.user = {};
		
		getUserBooks();
			
		// get all user books
		function getUserBooks() {
			nodeId =1;
			
			
			userService.getuser($stateParams.userId).then(function(user) {
				// The friendService returns a promise.
				proposalService.getProposals().then(function(proposals) {
					for (var i in proposals) {
						
						if (proposals[i].stateHistory.length > 0) {
							//select only approve book
							if ((proposals[i].user.userId == $stateParams.userId) && (proposals[i].stateHistory[0].proposalStatus == 'approved')) {
								$scope.userBooks.push(proposals[i]);
								$scope.nodes.push({id: nodeId, label: proposals[i].book.name  , image: $scope.DIR + 'books/' + proposals[i].book.imageName , shape: 'image'});
							
								++nodeId;
							}	
						}
					}
					
					$scope.user = user;
					
					$scope.nodes.push({id: nodeId, label: $scope.user.firstName + ' ' + $scope.user.lastName , image: $scope.DIR + 'users/' + $scope.user.imageName , shape: 'image'});

					//draw all edge
					for (var i = 0; i < nodeId; i++) {
						$scope.edges.push({from: nodeId, to: i, length: $scope.EDGE_LENGTH_MAIN});
					}
					
					
					// create a network
				    container = document.getElementById('myVis');
				    data = {
				        nodes: $scope.nodes,
				        edges: $scope.edges
				    };
				    options = {};
				    network = new vis.Network(container, data, options);
				});
			});
			
		}
		
		$scope.go = function(view){
		    $location.path(view);
		}
	}

	myApp.controller("UserBookController", UserBookController);
})();

