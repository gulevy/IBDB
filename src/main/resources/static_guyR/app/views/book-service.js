//(function(){
//	'use strict';
//	
//	.service('BookService', [ 'BookService' , function(){
//	    return {
//	        
//	            getBooks: function() {
//	                    return $http.get('http://localhost:8080/books/')
//	                            .then(
//	                                    function(response){
//	                                        return response.data;
//	                                    }, 
//	                                    function(errResponse){
//	                                        console.error('Error while fetching books');
//	                                        return $q.reject(errResponse);
//	                                    }
//	                            );
//	            },
//	            
//	            createBook: function(book){
//	                    return $http.post('http://localhost:8080/books/', book)
//	                            .then(
//	                                    function(response){
//	                                        return response.data;
//	                                    }, 
//	                                    function(errResponse){
//	                                        console.error('Error while creating book');
//	                                        return $q.reject(errResponse);
//	                                    }
//	                            );
//	            },
//	            
//	            updateBook: function(book, id){
//	                    return $http.put('http://localhost:8080/books/'+id, book)
//	                            .then(
//	                                    function(response){
//	                                        return response.data;
//	                                    }, 
//	                                    function(errResponse){
//	                                        console.error('Error while updating book');
//	                                        return $q.reject(errResponse);
//	                                    }
//	                            );
//	            },
//	            
//	            deleteBook: function(id){
//	                    return $http.delete('http://localhost:8080/books/'+id)
//	                            .then(
//	                                    function(response){
//	                                        return response.data;
//	                                    }, 
//	                                    function(errResponse){
//	                                        console.error('Error while deleting book');
//	                                        return $q.reject(errResponse);
//	                                    }
//	                            );
//	            }
//	        
//	    };
//	}]);
//			
//		
//			
//			
//			
//})()
//
