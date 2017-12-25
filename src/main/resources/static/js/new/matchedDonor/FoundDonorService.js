 
angular.module('founDonor.services', [])
 
.service('FoundDonorService',  function ($http,$log) {
	
	this.foundDonorCount =0;

    this.getDonorList = function(userId,searchQuery,successCallback,errorCallback){
 	    return   $http({
 				  method: 'POST',
 				  url: endPoint + '/foundDonors/'+userId,
 				  data: searchQuery,
 				  headers: { 'Content-Type': 'application/json' }
 	    		}).then(successCallback,errorCallback);
     }
 
})