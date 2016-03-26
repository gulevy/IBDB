(function() {

    function bookService($http, $q) {
        var book_cache = {}; // todo
        var url = "/book"

        this.getBook = function(id) {
            var request = $http.get(url + "/" + id);
            return (request.then(handleSuccess, handleError));
        }

        this.getBooks = function() {
            var request = $http.get("/books");
  
            return (request.then(handleSuccess, handleError));		
        }

        // I remove the book with the given ID from the remote collection.
        this.removeBook = function(id) {
            var request = $http.delete(url + "/" + id);

            return (request.then(handleSuccess, handleError));
        }

        this.editBook = function(book) {
        	 var request = $http({
                 method: 'PUT',
                 url: '/book/',
                 data: book,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             })
        	
           return (request.then(handleSuccess, handleError));
        }

        this.addBook = function(book) {
        	 var request = $http({
                 method: 'POST',
                 url: '/book/',
                 data: book,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             })
        	
           return (request.then(handleSuccess, handleError));
        }
                  
        this.uploadBook = function(uploadUrl,file,destName){
            var fd = new FormData();
            fd.append('name', destName);
            fd.append('file', file);
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(){
            })
            .error(function(){
            });
        }
           
      
 
        // I transform the error response, unwrapping the application data
	    // from
        // the API response payload.
        function handleError(response) {
                // The API response from the server should be returned in a
                // nomralized format. However, if the request was not handled by
				// the
                // server (or what not handles properly - ex. server error),
				// then we
                // may have to normalize it on our end, as best we can.
           if (!angular.isObject(response.data) || !response.data.message) {
                    return ($q.reject("An unknown error occurred."));
           }
                
           // Otherwise, use expected error message.
           return ($q.reject(response.data.message));
        }
            // I transform the successful response, unwrapping the application
			// data
            // from the API response payload.
        function handleSuccess(response) {
            return (response.data);
        }
    }

    myApp.service("bookService", bookService);

})();
