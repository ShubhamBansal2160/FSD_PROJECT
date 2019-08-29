angular.module("managerUsersApp").controller("UserCtl", UserCtl);

UserCtl.inject = [ '$scope', 'User' ];

function UserCtl($scope, User) {
	
	$scope.users = User.query();

	$scope.user = {};
	
	$scope.AddButtonText="Add/Update";
	$scope.SearchButtonText="Search";
	$scope.ResetButtonText="Reset";
	
	$scope.saveUser = function() {
		if ($scope.user.user_id !== undefined) {
			User.update($scope.user, function() {
				$scope.users = User.query();
				$scope.user = {};
				$scope.buttonText="Submit";
			});
		} else {
			User.save($scope.user, function() {
				$scope.users = User.query();
				$scope.user = {};
			});
		}
	}

	$scope.updateUserInit = function(user) {
		$scope.buttonText="Update";
		$scope.user = user;
	}

	$scope.deleteUser = function(user) {
		user.$delete({user_id: user.user_id}, function() {
			$scope.users = User.query();
		});
	}	
}