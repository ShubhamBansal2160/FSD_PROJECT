	angular.module("managerApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'Project' ];

function GeneralController($scope, Project) {
	
	$scope.projects = Project.query();

	$scope.project = {};
	
	$scope.AddButtonText="Add";
	$scope.SearchButtonText="Search";
	$scope.ResetButtonText="Reset";
	
	$scope.saveProject = function() {
		if ($scope.project.id !== undefined) {
			Project.update($scope.project, function() {
				$scope.projects = Project.query();
				$scope.project = {};
				$scope.buttonText="Submit";
			});
		} else {
			Project.save($scope.project, function() {
				$scope.projects = Project.query();
				$scope.project = {};
			});
		}
	}

	$scope.updateProjectInit = function(project) {
		$scope.buttonText="Update";
		$scope.project = project;
	}

	$scope.deleteProject = function(project) {
		project.$delete({id: project.id}, function() {
			$scope.projects = Project.query();
		});
	}	
}

GeneralController.inject = [ '$scope', 'Users' ];

function GeneralController($scope, Users) {
	
	$scope.users = Users.query();

	$scope.user = {};
	
	$scope.AddButtonText="Add";
	$scope.SearchButtonText="Search";
	$scope.ResetButtonText="Reset";
	
	$scope.saveUsers = function() {
		if ($scope.user.id !== undefined) {
			Project.update($scope.user, function() {
				$scope.users = Users.query();
				$scope.user = {};
				$scope.buttonText="Submit";
			});
		} else {
			Users.save($scope.user, function() {
				$scope.users = Users.query();
				$scope.user = {};
			});
		}
	}

	$scope.updateUsersInit = function(users) {
		$scope.buttonText="Update";
		$scope.user = users;
	}

	$scope.deleteUsers = function(users) {
		users.$delete({id: users.id}, function() {
			$scope.users = Users.query();
		});
	}	
}