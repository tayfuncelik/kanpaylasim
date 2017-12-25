"use strict";
var routerApp = angular.module('UserControllers', []);

routerApp.controller('listUserController', function($window,$scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,UserService) {
 	
	$scope.showLoader = true;
	$scope.screenWidth = $window.innerWidth;
    $scope.button = "All";
    $scope.maxSize = 5;
    
    $scope.bigTotalItems = 175;
    $scope.bigCurrentPage = 1;
    

    $scope.setPagingData = function() {
  	  $log.log('Page changed to: ' + $scope.currentPage);
  	  
  	  var currentPage = $scope.currentPage;
  	  var start = $scope.searchQuery.start;
  	  var offset = $scope.searchQuery.offset;
  	  
  	  start = (currentPage-1)*offset;
  	  
  	  $scope.searchQuery.start=	 start ;
  	  $scope.searchQuery.offset=offset;

  	  $scope.getDataList();
  	   
    };
    
    
    $scope.searchQuery = {
    		"start":0,
    		"offset":6 ,
    		"filterMap": {}
    };
 
	$scope.selected = [];
    $scope.selectAll = function (){
    	
    	$scope.test =!$scope.test ;
    	angular.forEach($scope.userList ,function(value, key) {
    		
    		 var idx = $scope.selected.indexOf(value.id);
    		 value.selected = !value.selected ;
    		if(idx == -1){
    			$scope.selected.push(value.id);	
    		}else{
    			$scope.selected.splice(idx, 1);
    		}
		});
    };
    
    $scope.selectData = function (userItem) {
        var idx = $scope.selected.indexOf(userItem);
        $scope.selectedForCard = userItem;
      
        if (idx > -1) {
        	$scope.selected.splice(idx, 1);
        }
        else {
        	$scope.selected.push(userItem);
        }
        if($scope.selected.length==0){
        	$scope.selectedForCard =undefined;
        }
    };
    
    $scope.exists = function (item) {
        return $scope.selected.indexOf(item) > -1;
    }; 
    
  $scope.pageChanged = function() {
    $log.log('Page changed to: ' + $scope.currentPage);
  };
  
  $scope.ownId = window.localStorage.getItem('userId');
  $scope.entity ={};
  
  $scope.getDataList = function (){
	    $scope.showLoader = true;

	    UserService.getUserList($scope.searchQuery,function successCallback(response) {
		    console.log("succes"); 
		    $scope.userList = response.data.resultSet;
		    $scope.getUserCount = response.data.totalRecords; 
		    $scope.showLoader = false;
		  }, function errorCallback(response) {
		    console.log("error");
		    alertify.error("Errorl");
		    $scope.showLoader = false;
		  });  
    };
    
    $scope.removeSelectedData = function (id){
    	$scope.removeSelected = [];
    	$scope.removeSelected.push(id);
    	$scope.removeDataCommon($scope.removeSelected);
    };
    
    $scope.removeUser = function (){
    	
    	if($scope.selected.length>0){
    		
			var idList =[];
	    	angular.forEach($scope.selected,function(value, key) {
	    		idList.push(value.id);	
			});
	    	$scope.removeDataCommon(idList);
	    }else{
		    alertify.error("please select at least one person");
	  	}
    };
    
    
    $scope.removeDataCommon = function (idList){
	    $scope.showLoader = true;

	    UserService.deleteUser(idList,function successCallback(response) {
		    console.log("removed succes ");  
		    alertify.success("Welcome to alertify!");
		    $scope.getDataList(); 
		    $scope.showLoader = false;
		  }, function errorCallback(response) {
		    console.log("error");
		    alertify.error("Errorl");
		    $scope.showLoader = false;
		  });  
    };
    
    $scope.refleshPage = function (){
   	   $state.reload();
   }; 
    $scope.getDataList(); 
});




routerApp.controller('createUserController', function($window,$scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,UserService) {
 	
	$scope.showLoader = true;
	$scope.screenWidth = $window.innerWidth;
    $scope.searchQuery = {"start":0,"offset":6};
	    
    $scope.refleshPage = function (){
   	   $state.reload();
   };
   
   $scope.eventId = $stateParams.eventIdFromState ;
   
   $scope.myDate={};
   
   
	  $scope.saveEntity = function(entity) {
	
		  if(entity.firstName == undefined || entity.firstName == null){
			  alertify.error("firstName giriniz");
		  } else if(entity.lastName == undefined || entity.lastName == null){
			  alertify.error("lastName giriniz");
		  } else if(entity.username == undefined || entity.username == null){
			  alertify.error("username giriniz");
		  }else if(entity.email == undefined || entity.email == null){
			  alertify.error("email giriniz");
		  } else {
			  
			    UserService.saveUser(entity,function successCallback(response) { 
				   console.log("succes");
				     alertify.success("Kullanıcı oluşturuldu!");
				     $state.go("private.users"); 
				  }, function errorCallback(response) {
					  if (response.status==409) {
						  alertify.error("Already Exist");
					 } 
			         console.log("error");
			   });
			    
			    
		  }
		  
		};
 
});

