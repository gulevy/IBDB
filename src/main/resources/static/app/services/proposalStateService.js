(function() {

    function proposalStateService($http, $q,$location) {
        var url = "/proposalsStates/"
   	  	
        this.getProposalState = function(id) {
            var request = $http.get(url  + id);
            return (request.then(handleSuccess));
        }

        this.getProposalsStates = function() {
            var request = $http.get("/proposalsStates");
  
            return (request.then(handleSuccess));		
        }
        
        
//        // I remove the book with the given ID from the remote collection.
//        this.removeProposal = function(id) {
//        	return $http.delete(url  + id).then(handleSuccess);
//        }
//
//        this.editProposal = function(proposal) {
//        	 return  $http({
//                 method: 'PUT',
//                 url: url,
//                 data: proposal,
//                 headers: {
//                     "Content-Type": "application/json",
//                     "Accept": "text/plain"
//                 }
//             }).then(handleSuccess);
//        }

        this.addProposalState = function(id,proposalState) {
        	 var request = $http({
                 method: 'POST',
                 url: url + id,
                 data: proposalState,
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

    myApp.service("proposalStateService", proposalStateService);

})();
