var app = angular.module('mainApp' , ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
	.when('/' , {templateUrl: 'login.html'})
	.when('/dashboard' , {templateUrl: 'dashboard.html'})
	.otherwise({redirectTo: '/'});
});


app.controller('loginCtrl' , function($scope, $location) {
	$scope.submit = function() {
		var uname = $scope.username;
		var password = $scope.password;
		
		$http.post('/user/authenticate', { username: $scope.username, password: $scope.password })
        .success(function (response) {
            alert(response);
        });
		
//		if ($scope.username == 'admin' && $scope.password == 'admin') {
//			alert("in")
//			$location.path('../views/dashboard.html')
//			
//		}
		
	}
	
});