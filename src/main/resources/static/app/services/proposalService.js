(function() {

    function proposalService($http, $q,$location) {
        var url = "/proposal/"
   	  	
        this.getProposal = function(id) {
            var request = $http.get(url  + id);
            return (request.then(handleSuccess));
        }

        this.getProposals = function() {
            var request = $http.get("/proposals");
  
            return (request.then(handleSuccess));		
        }
        
        this.getUserProposals = function(userId) {
            var request = $http.get("/proposal/user/" + userId);
  
            return (request.then(handleSuccess));		
        }
           
        // I remove the book with the given ID from the remote collection.
        this.removeProposal = function(id) {
        	return $http.delete(url  + id).then(handleSuccess);
        }
        
        this.getStatusProposals = function(status,user) {
        	 return  $http({
                 method: 'GET',
                 url: url + status,
                 data: user,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             }).then(handleSuccess);
        }

        this.editProposal = function(proposal) {
        	 return  $http({
                 method: 'PUT',
                 url: url,
                 data: proposal,
                 headers: {
                     "Content-Type": "application/json",
                     "Accept": "text/plain"
                 }
             }).then(handleSuccess);
        }

        this.addProposal = function(proposal) {
        	 var request = $http({
                 method: 'POST',
                 url: url,
                 data: proposal,
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

    myApp.service("proposalService", proposalService);

})();
