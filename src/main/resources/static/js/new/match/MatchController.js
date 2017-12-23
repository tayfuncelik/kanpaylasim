"use strict";
var routerApp = angular.module('MatchController', []);
routerApp.config(function($sceProvider) {
    $sceProvider.enabled(false);
 });
routerApp.controller('MatchListController', function($window,$scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,
		MatchService,CommonService) {
 	
	$scope.showLoader = true;
	$scope.screenWidth = $window.innerWidth;
    $scope.searchQuery = {"start":0,"offset":6};
	   
	$scope.change = function(id){
	  if(id==0 || id){
		  $scope.button = $scope.dataArray[id].name;	  
		  $scope.searchQuery.filterMap.bloodType = [$scope.dataArray[id].value];
		  $scope.getDataList();
	  }else{
		  $scope.button = "All";
		  $scope.searchQuery.filterMap.bloodType = [];
		  $scope.getDataList();
	  }
	}
	
	$scope.selected = [];
	$scope.selectedForCard =undefined;
	
    $scope.selectAll = function (){
    	
    	$scope.test =!$scope.test ;
    	angular.forEach($scope.userList ,function(value, key) {
    		
    		 var idx = $scope.selected.indexOf(value.id);
    		 value.selected = !value.selected;
    		 
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
        }else {
        	$scope.selected.push(userItem);
        }
        
        if($scope.selected.length==0){
        	$scope.selectedForCard =undefined;
        }
    };
    
	$scope.exists = function (item) {
	    return $scope.selected.indexOf(item) > -1;
	};

  $scope.totalItems = 64;
  $scope.currentPage =  1;  
   
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };

  $scope.pageChanged = function() {
    $log.log('Page changed to: ' + $scope.currentPage);
  };

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
		
		
  $rootScope.stateName = $state.current.name;
  console.log("state: "+$rootScope.stateName);
    
 
  $scope.ownId = window.localStorage.getItem('userId');
  $scope.entity ={};
  
  $scope.getDataList = function (){
//	    $scope.showLoader = true;
//	    
//	    MatchService.getMatched($scope.searchQuery).then(function successCallback(response) {
//			    	console.log("succes"); 
//				    $scope.dataList = response.data.resultSet;
//				    $scope.getDataCount = response.data.totalRecords; 
//				    $scope.showLoader = false;
//			  }, function errorCallback(response) {
//				    console.log("error");
//				    alertify.error("Errorl");
//				    $scope.showLoader = false;
//		   }); 
     
    };
    
    
    MatchService.getLastDonorRecord(CommonService.localUserId,$scope.searchQuery).then(function successCallback(response) {
    	console.log("succes"); 
    	 
	    $scope.lastDonor = response.data.donor;
	    
	    $scope.setProject($scope.lastDonor.xLocation,$scope.lastDonor.yLocation);
	    
	    $scope.dataList = response.data.eventDto.resultSet;
	    $scope.getDataCount = response.data.eventDto.totalRecords; 
	     
	    $rootScope.$broadcast('matchedCount',$scope.getDataCount);
	    $scope.showLoader = false;
  }, function errorCallback(response) {
	    console.log("error");
	    alertify.error("Errorl");
	    $scope.showLoader = false;
}); 
    
    
    $scope.removeSelectedData = function (id){
    	$scope.removeSelected = [];
    	$scope.removeSelected.push(id);
    	$scope.removeDataCommon($scope.removeSelected);
    };
    
    $scope.removeData = function (){
    	
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
    
    
 
    
    $scope.refleshPage = function (){
   	   $state.reload();
    }; 
    

    $scope.orderByDate = function (){
   	   alert("orderBy donation date")
    }; 
    
    
    $scope.redirectForMap = function(x,y){
  	   
	    $scope.entity.locationImage= 'http://maps.google.com/maps/api/staticmap?markers=color:red|'+x+','+y+'|'+x+','+y+'&size=350x350';
	    $window.location.href=$scope.entity.locationImage;
   };
    
 
  
   	$scope.setProject = function (x,y) {
	    $scope.entity.locationImage= 'http://maps.google.com/maps/api/staticmap?markers=color:red|'+x+','+y+'|'+x+','+y+'&size=350x350';

        $scope.currentProjectUrl =$scope.entity.locationImage;
    }
   	
    
	$scope.getDataList(); 
	
});
 