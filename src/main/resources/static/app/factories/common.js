(function() {
    //This is a common lib acts like a singleton init once
    function CommonFactory() { 	
    	var commonObject = {};
    
    	commonObject.sendPopUpMessage = function(title,message) {
    	   bootbox.dialog({
    		    title: title,
    			message: '<div class="alert alert-danger"><strong>Error!</strong>' + message + '</div>'	
    		  });
    	   }; 
    	     
    	commonObject.checkReponse = function(title,response) {
    	    if (!response.success) {
    	        this.sendPopUpMessage(title,response.message)
    	    }
    	};
    	
    	commonObject.getCurrentDate = function() {
			a = new Date();
			b = a.getFullYear();
			c = a.getMonth();
			(++c < 10)? c = "0" + c : c;
			
			d = a.getDate();
			(d < 10)? d = "0" + d : d;
			
			convertedDate = b + "-" + c + "-" + d; 

			return convertedDate;
		} 
    	
    	return commonObject;
        
    }
    
    myApp.factory("CommonFactory", CommonFactory);
})();



