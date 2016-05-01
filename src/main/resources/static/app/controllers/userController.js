(function() {

	function UserController($rootScope,$scope, userService,$location) {
		$scope.users = [];
		$scope.user ={};
		
		initController();
		getUsers();
		
		function initController() {
			path = $location.path();
			
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit user detail';
				$scope.mode = 1;
			} else {
				$scope.title =  'Registration';
				$scope.mode = 2;
			}
		}
		
		function applyRemoteData(newUsers) {
			$scope.users = newUsers;

			$scope.users.forEach(function(user) {
				if (user.userName == $rootScope.logInUser ) {
					$scope.user = user;
					$scope.user.dateOfBirth =  new Date($scope.user.dateOfBirth);
				}
			});
			
		}
		// I load the remote data from the server.
		function getUsers() {
			// The friendService returns a promise.
			userService.getUsers().then(function(users) {
				applyRemoteData(users);
			});
		}

		// I remove the given friend from the current collection.
		$scope.removeUser = function(id) {
			var isConfirmDelete = confirm('Are you sure you want this record?');
			if (isConfirmDelete) {
				//remove book by id and then get the current book list
				userService.removeUser(id).then(function(response) {
					$scope.response = response;
					getUsers();	
				});
					
				$location.path('/login');
			} else {
				return false;
			}
		};

		$scope.edit = function(id) {
			userService.editUser($scope.user).then(function(response) {
				$scope.response = response;
				getUsers();	
			});
		} 
	
		$scope.register = function() {
		    $scope.dataLoading = true;
	        $scope.user.points = 0;
	        $scope.user.userType = "member";
	        
	        var timestamp = new Date().getUTCMilliseconds();
			imageName = timestamp + ".png";
			userService.uploadBook('/upload',$scope.user.file,imageName).then(function(response) {
				$scope.response = response;
			});
			
			$scope.book.imageName = imageName;
	        
	        
	        userService.addUser($scope.user)
	           .then(function (response) {
	        	   		$scope.response = response;
	        	   
	                    if (response.success) {
	                        //FlashService.Success('Registration successful', true);
	                        $location.path('/login');
	                    } else {
	                       // FlashService.Error(response.message);
	                        $scope.dataLoading = false;
	                    }
	           });
	    }
		
		$scope.go = function (path) {
		  $location.path(path);
		};
	}

	myApp.controller("UserController", UserController);
})();

