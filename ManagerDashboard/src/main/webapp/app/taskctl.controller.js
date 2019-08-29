//angular.module("managerApp").controller("GeneralController", GeneralController);
angular.module("managerTaskApp").controller("TaskCtl", TaskCtl);

TaskCtl.inject = [ '$scope', 'Task' ];

function TaskCtl($scope, Task) {
	
	$scope.tasks = Task.query();

	$scope.task = {};
	
	$scope.AddButtonText="Add/Update";
	$scope.SearchButtonText="Search";
	$scope.ResetButtonText="Reset";
	
	$scope.saveTask = function() {
		if ($scope.task.task_id !== undefined) {
			Task.update($scope.task, function() {
				$scope.tasks = Task.query();
				$scope.task = {};
				$scope.buttonText="Submit";
			});
		} else {
			Task.save($scope.task, function() {
				$scope.tasks = Task.query();
				$scope.task = {};
			});
		}
	}

	$scope.updateTaskInit = function(task) {
		$scope.buttonText="Update";
		$scope.task = task;
	}

	$scope.deleteTask = function(task) {
		task.$delete({task_id: task.task_id}, function() {
			$scope.tasks = Task.query();
		});
	}	
}