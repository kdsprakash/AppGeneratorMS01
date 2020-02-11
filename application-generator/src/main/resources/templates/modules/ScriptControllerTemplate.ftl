app.controller('${name}Controller', function($scope, $http) {
	var URL_PREFIX = AndromedaConstants.PROTOCOL + "://" + AndromedaConstants.HOST + ":" + AndromedaConstants.PORT;
	${r"$scope.data = [];"}
	$scope.formData = {};

	$scope.add = function(${name?uncap_first}) {
		$http.post(URL_PREFIX + "/service/${module}", ${name?uncap_first})
		.then(function sampleSucces(response) {
			// Update status
		}, function myError(response) {
			$scope.statusText = response.statusText;
		});
		
		angular.element('#${name}Modal').modal('hide');
		$scope.clearForm();
		$scope.getAll();
    };

	$scope.update = function(${name?uncap_first}) {
		$http.put(URL_PREFIX + "/service/${module}", ${name?uncap_first})
		.then(function sampleSucces(response) {
			// Update status
		}, function myError(response) {
			$scope.statusText = response.statusText;
		});
		
		angular.element('#${name}Modal').modal('hide');
		$scope.clearForm();
		$scope.getAll();
    };

	$scope.delete = function(id) {
		$http.delete(URL_PREFIX + "/service/${module}/" + id)
		.then(function sampleSucces(response) {
			$scope.clearForm();
		}, function myError(response) {
			$scope.statusText = response.statusText;
		});
		
		$scope.getAll();
    };
    
    $scope.getAll = function() {
		$http.get(URL_PREFIX + "/service/${module}")
		.then(function sampleSucces(response) {
			$scope.data = response.data;
		}, function myError(response) {
			$scope.statusText = response.statusText;
		});    
    };

	$scope.getById = function(id) {
		$http.get(URL_PREFIX + "/service/${module}/" + id)
		.then(function sampleSucces(response) {
			$scope.formData = response.data;
		}, function myError(response) {
			$scope.statusText = response.statusText;
		});    
    };
    
    $scope.clearForm = function() {
		$scope.formData = {};
    };

	$scope.showAddForm = function() {
		$scope.clearForm();
		jQuery("#addButtonDiv").show();
		jQuery("#editButtonDiv").hide();
    };

	$scope.showEditForm = function(id) {
		$scope.getById(id);
		jQuery("#addButtonDiv").hide();
		jQuery("#editButtonDiv").show();
	};
});
