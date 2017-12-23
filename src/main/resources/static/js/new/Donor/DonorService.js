 
angular.module('donor.services', [])
 
.service('DonorService',  function ($http,$log) {
	
	this.getBloodTypes = function(successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/bloodTypes', 
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    },  
	 
	
   this.getDonors = function(searchQuery,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/getDonors',
				  data: searchQuery,
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    },  
	
    this.getDonor = function(donorId,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/getDonor/'+donorId,
			      headers: {'Content-Type': undefined}
	    		}).then(successCallback,errorCallback);
    }, 
	
    this.updateDonor = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/updateDonor',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
    this.saveDonor = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/saveDonor',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
//    this.saveDonor = function(userId,entity,successCallback,errorCallback){
//	    return   $http({
//				  method: 'POST',
//				  url: endPoint + '/saveDonor/'+userId,
//				  data: entity,
//				  headers: { 'Content-Type': 'application/json' } 
//	    		}).then(successCallback,errorCallback);
//    },
    
    this.deleteDonor = function(idList,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/deleteDonor',
				  data: idList,
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    }
    
 
})