(function() {
     // user controller - for execute user crud actions
	function UserController($rootScope,$scope,CommonFactory,AuthenticationService, userService,$location,$state, $stateParams) {
		$scope.users = [];
		$scope.user ={};
		
		initController();
		getUsers();
		
		function initController() {		
			path = $location.path();
			
			//check page mode
			if (path.indexOf('edit') > 0 ) {
				$scope.title = 'Edit user detail';
				$scope.mode = 1;
			} else {
				$scope.title =  'Registration';
				$scope.mode = 2;
				$scope.user.userType = 'member'
			}
			
			$scope.loginUser = $rootScope.user;
		}
		
		function applyRemoteData(newUsers) {
			$scope.users = newUsers;
		
			//applying the data and converting the date
			if  ($stateParams.userId != "") {
				$scope.users.forEach(function(user) {
					if (user.userId == $stateParams.userId ) {
						$scope.user = user;
						$scope.user.dateOfBirth =  new Date($scope.user.dateOfBirth);
					}
				});
			} else {
				$scope.users.forEach(function(user) {
					if (user.userName == $rootScope.logInUser ) {
						$scope.user = user;
						$scope.user.dateOfBirth =  new Date($scope.user.dateOfBirth);
					}
				});
			}

		}
		
		//get all IBDB users
		function getUsers() {
			userService.getUsers().then(function(users) {
				applyRemoteData(users);
			});
		}

		//remove existing user
		$scope.removeUser = function(id) {
			bootbox.confirm("Are you sure you want to delete this user?", function(result) {
				  if (result) {
					//remove user by id and then get the current user list
					userService.removeUser(id).then(function(response) {
						$scope.response = response;
							
						if ($scope.response.success == true) {
							//if the remove action succeed only then jump to login page
							if (id == $scope.loginUser.userId) {
								//init user parameters
								AuthenticationService.logout('ibdb_token');
								$rootScope.loginUser= "";
								$rootScope.user = "";
								$scope.loginUser = "";
								$rootScope.isLogin = false;
								$scope.user= "";
								
								//if i delete myself i want to go back to login window
								$location.path('/login');
							} 
							
							getUsers();
						} else{
							CommonFactory.sendPopUpMessage('User remove action was failed' , response.message);
						    return false;	
						}
					});
							
				  } else {
					 return false;
				  }
			}); 
		};

		$scope.edit = function() {
			userService.editUser($scope.user).then(function(response) {
				
				 if (response.success) {
					 CommonFactory.sendInfoPopUpMessage('User detail was update successfully',response.message); 
		    	 } else {
		    		 CommonFactory.sendPopUpMessage('User update action was failed',response.message);
		    	 }
				
				$scope.response = response;				
			});
		} 
	
		//register a new user
		$scope.register = function() {
		    $scope.dataLoading = true;
	        $scope.user.points = 0;
	        
	        if ($scope.user.userType == undefined) {
	            $scope.user.userType = "member";
	        }
	        	     
			//set image name
			if ($scope.user.file == undefined) {
				$scope.user.imageName = "anonymous.png";
			} else {
				var timestamp = new Date().getUTCMilliseconds();
				imageName = timestamp + ".png";
				$scope.user.imageName = imageName;
				
				//upload the image to the server image folder
				userService.uploadUser('/upload',$scope.user.file,imageName).then(function(response) {
					CommonFactory.checkReponse('User upload image action was failed' , response)
					$scope.response = response;
				});
			}
			
	        userService.addUser($scope.user)
	           .then(function (response) {
	        	   	$scope.response = response;
	        	   
	                if (response.success) {
	                    $location.path($rootScope.back);
	                } else {
	                    CommonFactory.sendPopUpMessage('User add action was failed' , response.message)
	                    $scope.dataLoading = false;
	                }
	           });
	    }
		
		$scope.go = function (path) {
		  $location.path(path);
		};
		
		$scope.popupImage = function(imagePath) {
			CommonFactory.popupImage("Image viewer" , imagePath)
		}
	}
	
	myApp.controller("UserController", UserController);
})();

