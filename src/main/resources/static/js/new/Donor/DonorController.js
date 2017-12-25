"use strict";
var routerApp = angular.module('DonorController', []);
 
routerApp.controller('DonorListController', function($window,$scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,DonorService,CommonService) {
 	
	$scope.showLoader = true;
	$scope.screenWidth = $window.innerWidth;
    $scope.searchQuery = {"start":0,"offset":6};
    
//    $scope.localUserId= window.localStorage.getItem('userId');
    $scope.localUserId = CommonService.localUserId;
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
	    $scope.showLoader = true;
	    
	    DonorService.getDonors($scope.searchQuery).then(function successCallback(response) {
			    	console.log("succes"); 
				    $scope.dataList = response.data.resultSet;
				    $scope.getDataCount = response.data.totalRecords; 
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
    
    
    $scope.removeDataCommon = function (idList){
	    $scope.showLoader = true;
	    
	    DonorService.deleteDonor(idList).then(function successCallback(response) {
			    console.log("removed succes ");  
			    alertify.success("Welcome to alertify!");
			    $scope.getDataList(); 
			    $scope.showLoader = false;;
		  }, function errorCallback(response) {
			    console.log("error");
			    alertify.error("Errorl");
			    $scope.showLoader = false;
		  }); 

    };
    
    $scope.refleshPage = function (){
   	   $state.reload();
    }; 
    

    $scope.orderByDate = function (){
   	   alert("orderBy donation date")
    }; 
    
    $scope.redirectForMap = function(x,y){
 	   
	    $scope.entity.locationImage= 'http://maps.google.com/maps/api/staticmap?markers=color:red|'+x+','+y+'|'+x+','+y+'&size=350x350'+'&key=AIzaSyBM7_AvH62LvVNDHeEpx1nvumoHUyUhSME';
	    $window.location.href=$scope.entity.locationImage;
   }
    
	$scope.getDataList(); 
	
});




routerApp.controller('CreateDonorController', function($translate,$window,$scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,DonorService) {
 	
	$scope.showLoader = true;
	$scope.screenWidth = $window.innerWidth;
    $scope.searchQuery = {"start":0,"offset":6};
	    
    $scope.refleshPage = function (){
   	   $state.reload();
    };
   
    $scope.donorId = $stateParams.donorIdFromState ;
    $scope.entity={};
   
    $scope.saveDonor = function(entity) {
    	entity.createdBy=window.localStorage.getItem('userId');
		  DonorService.saveDonor(entity).then(function successCallback(response) {
//        DonorService.saveDonor(entity.createdBy,entity).then(function successCallback(response) {
			      console.log("succes");
			      alertify.success("Donör kaydı oluşturuldu!");
			      $state.go("private.donorList"); 
			  }, function errorCallback(response) {
				  alertify.error("Errorl");
			      console.log("error");
		   });
	 };	 
	  
	  $scope.getLocation = function() {
		    if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition($scope.showPosition, $scope.showError);
		    } else {
		        x.innerHTML = "Geolocation is not supported by this browser.";
		    }
		}
		
		$scope.showPosition = function(position) {
		    var latlon = position.coords.latitude + "," + position.coords.longitude;
		    $scope.entity.xLocation =position.coords.latitude ;
		    $scope.entity.yLocation = position.coords.longitude;
		    /*var img_url = "https://maps.googleapis.com/maps/api/staticmap?center="
		    +latlon+"&zoom=14&size=400x300&key=AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU";
		    document.getElementById("mapholder").innerHTML = "<img src='"+img_url+"'>";*/
		    
//		    'http://maps.google.com/?q=' +latlon;
		    
		    $scope.entity.locationImage= 'http://maps.google.com/maps/api/staticmap?markers=color:red|'+latlon+'|'+latlon+'&size=350x350'+'&key=AIzaSyBM7_AvH62LvVNDHeEpx1nvumoHUyUhSME';
		    
		}  
		
		$scope.showError = function(error) {
		    switch(error.code) {
		        case error.PERMISSION_DENIED:
		            alert( "Kullanıcı Konum bilgisini paylaşmayı reddeti.")
		            break;
		        case error.POSITION_UNAVAILABLE:
		        	alert( "Konum alınamıyor.")
		            break;
		        case error.TIMEOUT:
		        	alert( "Zaman aşımına uğradı")
		            break;
		        case error.UNKNOWN_ERROR:
		        	alert( "Bilinmeyen hata alındı.") 
		            break;
		    }
		};
		
		 $scope.getBloodTypes = function (){
			   
		    	DonorService.getBloodTypes(function successCallback(response) {
				     console.log("succes");
				     $scope.dataArray = response.data;
				     
				    	angular.forEach($scope.dataArray,function(value, key) {
				    		$scope.dataArray[key].name = $translate.instant( $scope.dataArray[key].name);
				    	});
				    	if($scope.dataArray && $scope.dataArray[7]){
					    	$scope.entity.bloodType=$scope.dataArray[7].value;				    		
				    	}
				    	

				  }, function errorCallback(response) {
				     console.log("error");
				     alertify.error("Errorl");
				 });
		  };
	  
	  $scope.getBloodTypes();
 
}); 

routerApp.controller('UpdateDonorController', function($window,$scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,DonorService) {
 	
    $scope.donorId = $stateParams.donorId ;

	  $scope.getDataById = function (){
		  
		  DonorService.getDonor($stateParams.donorId).then(function successCallback(response) {
			    $scope.entity = response.data.resultSet[0];

		    	angular.forEach($scope.dataArray,function(value, key) {
			    	if($scope.dataArray[key].name == $scope.entity.bloodType){
			    		$scope.entity.bloodType=$scope.dataArray[key].value;
			    	}  
		    	}); 
		  	    console.log("succes"); 
	    	  }, function errorCallback(response) {
	    		  console.log("error");
			      alertify.error("Errorl");
	    	});
	  };

	  $scope.updateDonor = function(){
		 

	       DonorService.updateDonor($scope.entity).then(function successCallback(response) {
	    	     alertify.success("Updated!");
			     $state.go("private.donorList");
	    	  }, function errorCallback(response) {
	    		  alertify.error("Errorl");
	    	});
	  };
  
	  $scope.getBloodTypes = function (){
		   
	    	DonorService.getBloodTypes(function successCallback(response) {
			     console.log("succes");
			     $scope.dataArray = response.data;
			  }, function errorCallback(response) {
			     console.log("error");
			     alertify.error("Errorl");
			 });
	  };
	  
	  $scope.getDataById();
	  
	  $scope.getBloodTypes();
 
});
