(function() {

	function UserController($rootScope,$scope, userService, $uibModal,$location) {
		$scope.users = [];
		$scope.user ={};
		$rootScope.isLogin = false;
		
		getUsers();

		function applyRemoteData(newUsers) {
			$scope.books = newUsers;
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
				userService.removeUser(id).then(getUsers);

			} else {
				return false;
			}
		};

		//show modal form
		$scope.toggle = function(modalstate, id) {
			$scope.modalstate = modalstate;

			switch (modalstate) {
			case 'add':
				$scope.form_title = "Add New User";

				break;
			case 'edit':
				$scope.form_title = "Edit user id: " + id;
				$scope.id = id;
	
				userService.getUser(id).then(function(user) {
					$scope.user = user
				});

				break;
			default:
				break;
			}

			$('#userModal').modal('show');
		}

		//save new record / update existing record
		$scope.save = function(modalstate, id) {
			//append employee id to the URL if the form is in edit mode
			if ($scope.modalstate == 'edit') {
				userService.editUser($scope.user).then(function(users) {
					getUsers()
					$('#userModal').modal('hide');
				});
			} else {
				
				var timestamp = new Date().getUTCMilliseconds();
				imageName = timestamp + ".png";
				userService.uploadUser('/upload',$scope.user.file,imageName);
				
				$scope.user.imageName = imageName;
				var res = userService.addUser($scope.user).then(function(users) {
					getUsers()
					$('#userModal').modal('hide');
				});
				
			
			}
		}	
		
		
		$scope.authenticate = function() {
			if ($scope.username == "admin@ibdb.com" && $scope.password == "admin") {
				$rootScope.logInUser = $scope.username;
				$rootScope.login = true;
				$location.path('/dashboard');
				alert("user or password are correct!!");
			} else {
				$rootScope.login = false;
				alert("user or password are incorrect!!");
			}
			
		}
		
		
		$rootScope.isLogin = function() {
			return $rootScope.login;
		}
		
		
		
		
		
	}

	myApp.controller("UserController", UserController);

})();

