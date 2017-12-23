angular.module('mainApp.services', [])


.service('CommonService',  function ($http) {
	this.localUserId= window.localStorage.getItem('userId');
})

.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
            
            
            var reader = new FileReader();
            reader.onload = function (e) {
                scope.testimage = e.target.result;
                scope.$apply();
            }
  
            element.on('change', function() {
                reader.readAsDataURL(element[0].files[0]);
            });
        }
    };
}])

.service('fileUpload', ['$http','$log', function ($http,$log) {
	
    this.createFileToUrl = function(entity,file, uploadUrl,successCallback,errorCallback){
        var fd = new FormData();

        fd.append('createFile', file); 
        fd.append("data", angular.toJson(entity));
        

     return $http.post( uploadUrl, fd, {
            transformRequest: angular.identity, 
            transformResponse: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(successCallback, errorCallback);
    },
    
    this.updateFileToUrl = function(entity,file,uploadUrl,successCallback,errorCallback){
        var fd = new FormData();

        fd.append('createFile',file); 
        fd.append("data", angular.toJson(entity));

     return $http.post( uploadUrl, fd, {
            transformRequest: angular.identity, 
            transformResponse: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(successCallback, errorCallback);
    }
}])
   
.factory('$custom', function($rootScope,$state,$http, $q ,$timeout, transformRequestAsFormPost, $window, localStorageService) {
    var self = this;

    //HTTP POST request
    self.notAuthPost = function(url,data){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: endPoint + url,
                withCredentials: true,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'},
                data : (data!= null) ? data : {}
            }).then(function(success){
                deferred.resolve(success.data);
            },function(error){
                if(error.status==401)
                $state.go('public.login');
                deferred.reject(error);
            });
            return deferred.promise;
    }
    //HTTP GET request
    self.notAuthGet = function(url,id){
            var deferred = $q.defer();
            if(id!=null){
                var final_url = endPoint + url+'/'+id;
            }else{
                var final_url = endPoint + url;
            }
            $http({
                method: 'GET',
                url: final_url,
                withCredentials: true,
           }).then(function(success){
                deferred.resolve(success.data);
            },function(error){
                if(error.status==401)
                    $state.go('public.login');
                deferred.reject(error);
            });
            return deferred.promise;
    }
    //HTTP POST request
    self.post = function(url,data){
        if(self.isLoggedIn()){
            base64.settings.ascii = true;
            var token = localStorageService.get('token')
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: endPoint + url,
                withCredentials: true,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization' : 'Grant ' + base64.encode(token) },
                data : (data!= null) ? data : {}
            }).then(function(success){
                deferred.resolve(success.data);
            },function(error){
                if(error.status==401)
                $state.go('public.login');
                deferred.reject(error);
            });
            return deferred.promise;
        }else if(self.isLoggedIn()==false && endPointMooc == true){
              base64.settings.ascii = true;

              var deferred = $q.defer();
              $http({
                  method: 'POST',
                  url: endPoint + url,
                  withCredentials: true,
                  headers:{
                      'Content-Type': 'application/json;charset=UTF-8',
                    },
                  data : (data!= null) ? data : {}
              }).then(function(success){
                  deferred.resolve(success.data);
              },function(error){
                  if(error.status==401)
                  $state.go('public.login');
                  deferred.reject(error);
              });
              return deferred.promise;
        }
    }

    //HTTP PUT request
    self.put = function(url,data){
        if(self.isLoggedIn()){
            base64.settings.ascii = true;
            var token = localStorageService.get('token')
            var deferred = $q.defer();
            $http({
                method: 'PUT',
                url: endPoint + url,
                withCredentials: true,
                headers:{
                'Content-Type': 'application/json;charset=UTF-8',
                'Authorization' : 'Grant ' + base64.encode(token) },
                data : (data!= null) ? data : {}
            }).then(function(success){
                deferred.resolve(success.data);
            },function(error){
                if(error.status==401)
                $state.go('public.login');
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }

    //HTTP GET request
    self.get = function(url,id){
        if(self.isLoggedIn()){

            base64.settings.ascii = true;
            var token = localStorageService.get('token')

            var deferred = $q.defer();
            if(id!=null){
                var final_url = endPoint + url+'/'+id;
            }else{
                var final_url = endPoint + url;
            }
            $http({
                method: 'GET',
                url: final_url,
                withCredentials: true,
                headers:{'Authorization' : 'Grant ' + base64.encode(token) }
           }).then(function(success){
                deferred.resolve(success.data);
            },function(error){
                if(error.status==401)
                    $state.go('public.login');
                deferred.reject(error);
            });
            return deferred.promise;
          }else if(self.isLoggedIn()==false && endPointMooc == true){
                base64.settings.ascii = true;

                var deferred = $q.defer();
                if(id!=null){
                    var final_url = endPoint + url+'/'+id;
                }else{
                    var final_url = endPoint + url;
                }
                $http({
                    method: 'GET',
                    url: final_url
               }).then(function(success){
                    deferred.resolve(success.data);
                },function(error){
                    if(error.status==401)
                        $state.go('public.login');
                    deferred.reject(error);
                });
                return deferred.promise;
          }


    }

    //HTTP GET request
    self.delete = function(url,data){
        if(self.isLoggedIn()){

            base64.settings.ascii = true;
            var token = localStorageService.get('token')

            var deferred = $q.defer();
            $http({
                method: 'DELETE',
                url: endPoint + url,
                withCredentials: true,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization' : 'Grant ' + base64.encode(token) },
                data : (data!= null) ? data : {}
            }).then(function(success){
                deferred.resolve(success.data);
            },function(error){
                if(error.status==401){
                $state.go('public.login');
                deferred.reject(error);
            }
        });
            return deferred.promise;
        }
    }

    self.isLoggedIn = function(){
        var token = localStorageService.get('token');

            // var credentials = localStorageService.get('credentials');
            if(typeof token !== 'undefined' && token != null){
                return true;
            }
            if(endPointMooc==true){
              return false;
            }else{
               $state.go('public.login');
            }

    }

    return self;
})

//I provide a request-transformation method that is used to prepare the outgoing
//request as a FORM post instead of a JSON packet.
.factory("transformRequestAsFormPost",function() {
      // I prepare the request data for the form post.
      function transformRequest( data, getHeaders ) {
        var headers = getHeaders();
        headers[ "Content-type" ] = "application/x-www-form-urlencoded; charset=utf-8";
        return( serializeData( data ) );
      }
      // Return the factory value.
      return( transformRequest );
      // ---
      // PRVIATE METHODS.
      // ---
      // I serialize the given Object into a key-value pair string. This
      // method expects an object and will default to the toString() method.
      // --
      // NOTE: This is an atered version of the jQuery.param() method which
      // will serialize a data collection for Form posting.
      // --
      // https://github.com/jquery/jquery/blob/master/src/serialize.js#L45
      function serializeData( data ) {
        // If this is not an object, defer to native stringification.
        if ( ! angular.isObject( data ) ) {
          return( ( data == null ) ? "" : data.toString() );
        }
        var buffer = [];
        // Serialize each key in the object.
        for ( var name in data ) {
          if ( ! data.hasOwnProperty( name ) ) {
            continue;
          }
          var value = data[ name ];
          buffer.push(
              encodeURIComponent( name ) +
              "=" +value
              //encodeURIComponent( ( value == null ) ? "" : value )
          );
        }
        // Serialize the buffer and clean it up for transportation.
        var source = buffer
        .join( "&" )
        .replace( /%20/g, "+" )
        ;
        return( source );
      }
    }
)
 

.directive('userAvatar', function() {
  return {
    replace: true,
    templateUrl: 'img/svg/male.svg'
  };
});