 
angular.module('user.services', [])
 
.service('UserService',  function ($http) {
	
   this.getUserList = function(searchQuery,successCallback,errorCallback){
		   return   $http({
				  method: 'POST',
				  url: endPoint + '/getUserList',
				  data: searchQuery,
				  headers: { 'Content-Type': 'application/json' }
	 		}).then(successCallback,errorCallback);
    },  
	 
	
    this.saveUser = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/saveUser',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
     
    this.checkUser = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/checkUser',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
	
    this.getUser = function(dataId,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/getUser/'+dataId, 
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
    this.updateUser = function(entity,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/updateUser',
				  data: entity,
				  headers: { 'Content-Type': 'application/json' } 
	    		}).then(successCallback,errorCallback);
    },
    
    this.deleteUser = function(idList,successCallback,errorCallback){
	    return   $http({
				  method: 'POST',
				  url: endPoint + '/deleteUser',
				  data: idList,
				  headers: { 'Content-Type': 'application/json' }
	    		}).then(successCallback,errorCallback);
    }
    
 
})