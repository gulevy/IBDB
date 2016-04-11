(function() {

    function bookService($http, $q) {
        var url = "/book"

        this.getBook = function(id) {
            var request = $http.get(url + "/" + id);
            return request.then(handleSuccess);
        }

        this.getBooks = function() {
            var request = $http.get("/books");
  
            return request.then(handleSuccess);		
        }

        // I remove the book with the given ID from the remote collection.
        this.removeBook = function(id) {
            var request = $http.delete(url + "/" + id);

            return request.then(handleSuccess);
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
        	
           return request.then(handleSuccess);
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
        	
           return request.then(handleSuccess);
        }
                  
        this.uploadBook = function(uploadUrl,file,destName){
            var fd = new FormData();
            fd.append('name', destName);
            fd.append('file', file);
            
            
            return $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(handleSuccess);
            
        }
          
        function handleSuccess(response) {
            return (response.data);
        }
    }

    myApp.service("bookService", bookService);

})();
