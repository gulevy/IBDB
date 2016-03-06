(function(){
	'use strict';

	angular.module('ibdb.helpers.routes', ['ui.router'])
		.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
			$urlRouterProvider.otherwise('/');

			$stateProvider
				.state('main', {
					url: "/",
					templateUrl: 'app/views/main.html',
					controller: 'MainCtrl'
				})
				.state('books', {
					url: "/books/",
					templateUrl: 'app/views/books.html',
					controller: 'BooksCtrl'
				})
				.state('books.details', {
					url: "details/{id}",
					templateUrl: 'app/views/books-details.html',
					controller: 'BooksDetailsCtrl'
				})
		}])
})()