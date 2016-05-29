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
        
        
        this.sendMail = function(proposal) {
       	 var request = $http({
                method: 'POST',
                url: url + 'mail/',
                data: proposal ,
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
