'use strict';
 
angular.module('analatycsbytes.login', ['ngRoute'])

 
// Declared route 
.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/securelogin', {
        templateUrl: 'analatycsbytes/private/app/dashboard.html',
        controller: 'DashboardCtrl'
    });
}])
 
// Home controller
.controller('DashboardCtrl', [function() {
 
}]);