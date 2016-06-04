(function() {

    function reviewService($http, $q,$location) {
        var url = "/review/"
   	  	
        this.getReview = function(id) {
            var request = $http.get(url  + id);
            return (request.then(handleSuccess));
        }

        this.getReviews = function() {
            var request = $http.get("/reviews");
  
            return (request.then(handleSuccess));		
        }
             
        // I remove the book with the given ID from the remote collection.
        this.removeReview = function(id) {
        	return $http.delete(url  + id).then(handleSuccess);
        }

        this.editReview = function(review) {
        	 return  $http({
                 method: 'PUT',
                 url: url,
                 data: review,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             }).then(handleSuccess);
        }

        //http post for adding a review
        this.addReview = function(review,bookId) {
        	 var request = $http({
                 method: 'POST',
                 url: url + bookId,
                 data: review,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             })
        	
           return (request.then(handleSuccess));
        }
                  
        //return data on success
        function handleSuccess(response) {
            return (response.data);
        }
    }

    myApp.service("reviewService", reviewService);

})();
