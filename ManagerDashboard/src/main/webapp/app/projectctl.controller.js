angular.module("managerApp").controller("ProjectCtl", ProjectCtl);

ProjectCtl.inject = [ '$scope', 'Project' ];

function ProjectCtl($scope, Project) {
	
	$scope.projects = Project.query();

	$scope.project = {};
	
	$scope.AddButtonText="Add/Update";
	$scope.SearchButtonText="Search";
	$scope.ResetButtonText="Reset";
	
	$scope.saveProject = function() {
		if ($scope.project.project_id !== undefined) {
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
		project.$delete({project_id: project.project_id}, function() {
			$scope.projects = Project.query();
		});
	}	
}