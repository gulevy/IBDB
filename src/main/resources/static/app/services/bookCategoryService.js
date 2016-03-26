(function() {

    function bookCategoryService($http, $q) {
        var book_cache = {}; // todo
        var url = "/book/category"

        this.getBookCategory = function(id) {
            var request = $http.get(url + "/" + id);
            return (request.then(handleSuccess, handleError));
        }

        this.getBookCategories = function() {
            var request = $http.get("/book/categories");
            return (request.then(handleSuccess, handleError));		
        }
 
        function handleError(response) {
           if (!angular.isObject(response.data) || !response.data.message) {
                    return ($q.reject("An unknown error occurred."));
           }
                
           return ($q.reject(response.data.message));
        }
     
        function handleSuccess(response) {
            return (response.data);
        }
    }

    myApp.service("bookCategoryService", bookCategoryService);

})();
