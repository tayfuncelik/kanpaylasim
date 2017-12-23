 
angular.module('event.services', [])
 
.service('EventService',  function ($http,$log) {
	
	this.getBloodTypes = function(successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/bloodTypes', 
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    },  
	 
	
   this.getEvents = function(searchQuery,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/getEvents',
				  data: searchQuery,
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    },  
	
    this.getEvent = function(eventId,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/getEvent/'+eventId,
			      headers: {'Content-Type': undefined}
	    		}).then(successCallback,errorCallback);
    }, 
	
    this.updateEvent = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/updateEvent',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
    this.saveEvent = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/saveEvent',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
    this.deleteEvent = function(idList,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/deleteEvent',
				  data: idList,
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    }
    
 
})