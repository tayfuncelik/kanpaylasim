 
angular.module('match.services', [])
 
.service('MatchService',  function ($http,$log) {
	
	this.matchedCount =0;

   this.getMatched = function(searchQuery,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/getEvents',
				  data: searchQuery,
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    },
    

    this.getLastDonorRecord = function(userId,searchQuery,successCallback,errorCallback){
 	    return   $http({
 				  method: 'POST',
 				  url: endPoint + '/findFirstCreatedDonor/'+userId,
 				  data: searchQuery,
 				  headers: { 'Content-Type': 'application/json' }
 	    		}).then(successCallback,errorCallback);
     }
   
 
})