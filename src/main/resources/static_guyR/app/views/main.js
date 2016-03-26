(function(){
	'use strict';

	angular.module('ibdb.views.main', [])
		.controller('MainCtrl', [
			'$rootScope',
			'$scope',
			function($rootscope, $scope){
				$scope.title = 'main';
			}]);
})()