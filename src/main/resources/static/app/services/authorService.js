(function() {

    function authorService($http, $q,$location) {
        var url = "/author/"
   	  	
        this.getAuthor = function(id) {
            var request = $http.get(url  + id);
            return (request.then(handleSuccess));
        }

        this.getAuthors = function() {
            var request = $http.get("/authors");
  
            return (request.then(handleSuccess));		
        }
             
        // I remove the book with the given ID from the remote collection.
        this.removeAuthor = function(id) {
        	return $http.delete(url  + id).then(handleSuccess);
        }

        // edit specific author
        this.editAuthor = function(author) {
        	 return  $http({
                 method: 'PUT',
                 url: url,
                 data: author,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             }).then(handleSuccess);
        }

        this.addAuthor = function(author) {
        	 var request = $http({
                 method: 'POST',
                 url: url,
                 data: author,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             });
        	
           return (request.then(handleSuccess));
        }
                  
        //return data on success
        function handleSuccess(response) {
            return (response.data);
        }
    }

    myApp.service("authorService", authorService);

})();
