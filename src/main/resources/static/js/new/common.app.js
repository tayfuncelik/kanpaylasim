"use strict";
angular.module('routerApp', ['ui.router','ui.bootstrap' , 'LocalStorageModule', 'mainApp.services' ,'event.services','user.services',
	'ngAlertify','UserControllers','mainControllers','EventControllers','toggle-switch',
	'donor.services','DonorController','match.services','MatchController','founDonor.services','FoundDonorController',
	'local_language'])
	
	/*'moment-picker',*/

.config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider) {

    $stateProvider
    
      .state('private', {
        url : '/private',
        abstract : true,
        cache:false,
        templateUrl : 'private/index.html'
      })

     .state('private.createUser', {
	     url: '/createUser',
	     templateUrl: 'private/users/createUser.html',
	     controller: 'createUserController'
       })

      .state('public.login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'loginController'
        })

  	  .state('private.login', {
  	    url: '/publicLogin',
  	    templateUrl: 'private/login.html',
        controller: 'loginController'
  	    })


      .state('private.profile', {
	        url: '/profile/:userId',
	        templateUrl: 'private/profile/personProfile.html',
            controller: 'profileController'
	    })

      .state('private.users', {
	        url: '/users',
	        templateUrl: 'private/users/users.html',
	        controller: 'listUserController'
	    })
  // //////////////////////////////////////////////////
  	    
      .state('private.events', {
  	        url: '/events',
  	        templateUrl: 'private/eventManagement/eventList.html',
  	        controller: 'EventListController'
  	  })
      .state('private.createEvent', {
  	        url: '/createEvent',
  	        templateUrl: 'private/eventManagement/addEvent.html',
  	        controller: 'CreateEventController'
  	  })
  	  .state('private.updateEvent', {
  	        url: '/updateEvent/:eventId',
  	        templateUrl: 'private/eventManagement/updateEvent.html',
  	        controller: 'UpdateEventController'
  	  }) 
  // //////////////////////////////////////////////////
  	  
  	  
      .state('private.donorList', {
  	        url: '/donors',
  	        templateUrl: 'private/Donor/donorList.html',
  	        controller: 'DonorListController'
  	  })
      .state('private.createDonor', {
  	        url: '/createDonor',
  	        templateUrl: 'private/Donor/addDonor.html',
  	        controller: 'CreateDonorController'
  	  })
  	  .state('private.updateDonor', {
  	        url: '/updateDonor/:donorId',
  	        templateUrl: 'private/Donor/updateDonor.html',
  	        controller: 'UpdateDonorController'
  	  }) 
  	  
 ///////////////////////////////////////////////////////////////
    .state('private.matchList', {
  	        url: '/matchs', 
        	templateUrl: 'private/macth/matchList.html' ,
  	        controller: 'MatchListController'
  	  })
/////////////////////////////////////////////////////////////////
    .state('private.matchDonorList', {
  	        url: '/matchDonors', 
        	templateUrl: 'private/matchedDonor/matchDonorList.html'  ,
   	        controller: 'FoundDonorListController'
  	  })
  	  
  	  
/////////////////////////////////////////////////////////////////

  	  .state('private.about', {
  	        url: '/about',
  	        templateUrl: 'private/About/about.html',
  	        controller: 'AboutController'
  	  })
      .state('private.createAbout', {
  	        url: '/createAbout',
  	        templateUrl: 'private/About/createAbout.html',
  	        controller: 'AboutController'
  	  })
  	  .state('private.updateAbout', {
  	        url: '/updateAbout/:eventId',
  	        templateUrl: 'private/About/updateAbout.html',
  	        controller: 'AboutController'
  	  })
  	  
  	  
       $urlRouterProvider.otherwise('private/login');
 }]);
