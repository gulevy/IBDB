(function() {
    'use strict';

    angular.module('ibdb.book.factory', [])
        .factory('bookFactory', [
            '$http',
            
            function($http) {
                var urlBase = '/book';
//                var bookData = {};

                var getBooks = function() {
                    return $http.get('/books');
                };

                var getBook = function(id) {
                    return $http.get(urlBase + '/' + id);
                };
//
//                bookFactory.insertBook = function(book) {
//                    return $http.post(urlBase, cust);
//                };
//
//                bookFactory.updateBook = function(book) {
//                    return $http.put(urlBase + '/' + book.id, book)
//                };
//
                var deleteBook = deleteBook = function(id) {
                    return $http.delete(urlBase + '/' + id);
                };

                return {
                	getBooks: getBooks,
                	getBook: getBook
                }
            }
        ]);
})()



