"use strict";
var routerApp = angular.module('mainControllers', []);


//routerApp.config(function($sceDelegateProvider) {
//	  $sceDelegateProvider.resourceUrlWhitelist([
//	    'self',
//	    'https://www.youtube.com/**'
//	  ]);
//});

routerApp.controller('menuController', function($scope,$state,$stateParams,$http, $rootScope,$custom, $log,alertify,CommonService,MatchService,FoundDonorService) {


	$scope.clicked = false;
	$scope.ownId = window.localStorage.getItem('userId');
 
    $scope.$on('ownId', function(event, data) {
    	CommonService.setLocalUser(data);
		 $scope.ownId = data;
    }); 
    
    $scope.isim =  $state.$current.name;
   
	 
	$scope.endPoint=endPoint;
  
	$scope.$watch(function(){
		 $scope.isim = $state.$current.name;
	    return $state.$current.name;
	}, function(newVal, oldVal){
//		 console.log("userId:"+window.localStorage.getItem('userId'));
		
		if(!window.localStorage.getItem('userId') && $scope.isim=="private.login" ){
			 $state.go("private.login"); 
		}else if(window.localStorage.getItem('userId')== null || window.localStorage.getItem('userId')== undefined ){
//			 $state.go("private.login"); 
			 $state.go("private.mainpage"); 
			console.log("wathing state");
			 //routing user login page if he/she didn't signed in who can not see private pages
		}
		
		
	});
//	$scope.$parent
	
	
	 $scope.$on('showLoader', function(event, data) {
		 $scope.showLoader = data;
    });  
	 
	 
	 $scope.$on('matchedCount', function(event, data) {
		 $scope.matchedCount = data;
    });  
	 
	 $scope.$on('foundDonorCount', function(event, data) {
		 $scope.foundDonorCount = data;
    });  
	 
	
	 

    $scope.logOut = function(){
	    window.localStorage.clear();
	    localStorage.clear(); 
	    $scope.ownId = window.localStorage.getItem('userId');
        $rootScope.$broadcast('myEvent', 'my data');
        $rootScope.$broadcast('ownId', $scope.ownId);
//        $state.go("public.home"); 
        $state.go("private.login"); 
   
     };
     
     
     setInterval( function (){
    	 
    	 if(CommonService.localUserId){
    		  MatchService.getLastDonorRecord(CommonService.localUserId,{}).then(function successCallback(response) {
  		    	console.log("MatchService.getLastDonorRecord");
  		    	if(response.data && response.data.eventDto &&response.data.eventDto.totalRecords){
  		    		$scope.data = response.data.eventDto.totalRecords; 	
  		    	}else{
  		    		$scope.data = 0;
  		    	}
  		    	
  			    $rootScope.$broadcast('matchedCount',$scope.data);
  	    	  }); 
    	 }
			
		  
	   }, 15000);
	   
	 
	   setInterval(function (){
		   
		   if(CommonService.localUserId){
			   
		  
		    FoundDonorService.getDonorList(CommonService.localUserId,{}).then(function successCallback(response) {
				    	console.log("succes"); 
				    	
				    	if(response.data && response.data.donorDto && response.data.donorDto.totalRecords ){
//				    		$scope.lastDonor = response.data.event; 
//						    $scope.dataList = response.data.donorDto.resultSet;
						    $scope.getDataCount = response.data.donorDto.totalRecords; 
				    	}else{
				    		$scope.getDataCount = 0;
				    	}
					     
					    $rootScope.$broadcast('foundDonorCount',$scope.getDataCount);
				  }); 
		   }
	    }, 19000);
	    
	
});
 

routerApp.controller('loginController', function($scope,$http,$state,$window, $rootScope, alertify,UserService) {

     $rootScope.stateName = $state.current.name;
     console.log("state: "+$rootScope.stateName);

 	 $scope.isLogin = true ;
     $scope.stateName = $state.current.name;
     $scope.entity ={};

     $scope.login = function (entity){

    	 
    	   UserService.checkUser(entity, function successCallback(response) {
			      console.log("succes");
			      var userId = response.data;
			      window.localStorage.setItem('userId',userId );
			      localStorage.setItem("userId",userId );
 		          $rootScope.$broadcast('myEvent', 'my data');
   		          $rootScope.$broadcast('ownId', userId); 
   		          $state.go("private.events");
			  }, function errorCallback(response) {
			      console.log("error");
			      alertify.error("Kullanıcı adı ya da şifrenizi kontrol ediniz");
			  });
     };


	$scope.saveAfterCheck = function(entity) {
		
		 UserService.checkUser(entity,function successCallback(response) {
			      console.log("succes");
			      alertify.error("Already Exist User"); 
			  }, function errorCallback(response) {
			      console.log("userNot exist so it can be created");
			      $scope.save(entity);
		  });
	}; 
	
	$scope.save = function(entity) {
		
		 UserService.saveUser(entity,function successCallback(response) {
			      console.log("succes"); 
			      alertify.success("Hoş Geldiniz !");
			      $scope.login(entity); 
			  }, function errorCallback(response) {
				  alertify.success("Kullanıcı oluşturulamadı !");
		  });
	}; 
	 
	
});
 
routerApp.controller('profileController', function($scope,$state,$stateParams,$http,$rootScope, alertify,UserService) {
  
	$scope.myProfile = false;
    $rootScope.stateName = $state.current.name;
    console.log("state: "+$rootScope.stateName);
    $scope.myUserId = window.localStorage.getItem('userId');
    $scope.personId = $stateParams.userId ;
     
    if($scope.personId == $scope.myUserId){
    	$scope.myProfile = true;
    }
    
    $scope.$on('someEvent', function(event, data) {
    	alert("someEvent");
    	console.log(data); 
	});
	
    $scope.entity ={};
    $scope.enableEdit =false;
    
    
    $scope.inputType = 'password';
    $scope.hideShowPassword = function(){
      if ($scope.inputType == 'password')
        $scope.inputType = 'text';
      else
        $scope.inputType = 'password';
    };
    
    
    $scope.getUserProfile = function (){
    	
    	UserService.getUser($scope.personId,function successCallback(response) {
			    console.log("succes"); 
			    $scope.entity = response.data; 
			  }, function errorCallback(response) { 
				  alertify.error("Errorl");
			  }); 
    };
    
	$scope.updateUser = function(entity) {
		
		UserService.updateUser(entity,function successCallback(response) {
			      console.log("succes");
			      alertify.success("user Updated!");
			      $state.reload();
			  }, function errorCallback(response) {
				  alertify.error("Errorl");
			      console.log("error");
		  });
	};
    
    $scope.getUserProfile();   
     
    
}); 