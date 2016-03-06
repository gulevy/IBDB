(function(){
	'use strict';
	
	angular.module('ibdb.views.books', [])
		.controller('BooksCtrl', [
			'$rootScope',
			'$scope',
			'$http',
				function($rootscope, $scope,$http){
				    $http.get("/books")
				    .then(function(response) {
				    	$scope.title = 'books';
						$scope.list = response.data;
				    });
			}])
		.controller('BooksDetailsCtrl', [
			'$rootScope',
			'$scope',
			'$state',
			'$stateParams',
			function(
				$rootscope, 
				$scope, 
				$state,
				$stateParams
			){

				$scope.list = [{
					id: 1,
					name: 'book1',
					details: 'book 1 details'
				},
				{
					id: 2,
					name: 'book2',
					details: 'book 2 details'
				},
				{
					id: 3,
					name: 'book3',
					details: 'book 3 details'
				}];

				$scope.book = _.find($scope.list, {id: parseInt($stateParams.id)});

				console.log($scope.book);

			}])
})()