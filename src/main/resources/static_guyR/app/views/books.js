(function() {
    'use strict';

    angular.module('ibdb.views.books', [])
        .controller('BooksCtrl', [
            '$rootScope',
            '$scope',
            '$http',
            'bookFactory',
            function($rootscope, $scope, $http, bookFactory) {
                bookFactory.getBooks()
                    .then(function(response) {
                        $scope.title = 'books';
                        $scope.list = response.data;
                    });


            }
        ])

    .controller('BooksDetailsCtrl', [
        '$rootScope',
        '$scope',
        '$state',
        '$stateParams',
        'bookFactory',
        function(
            $rootscope,
            $scope,
            $state,
            $stateParams,
            bookFactory
        ) {
            bookFactory.getBook($stateParams.id)
                .then(function(response) {
                    $scope.title = response.data.name;
                    $scope.book = response.data;
                });
        }
    ])




    .controller('DeleteBookCtrl', [
        '$rootScope',
        '$scope',
        '$http',
        'bookFactory',
        function($rootscope, $scope, $http, bookFactory,id) {
            bookFactory.deleteBook(id)
                .then(function(response) {
                    $scope.title = 'books';
                    $scope.list = [];
                });


        }
    ])



})()
