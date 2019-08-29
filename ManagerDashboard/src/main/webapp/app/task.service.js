angular.module('managerTaskApp').factory('Task', Task);

Task.$inject = [ '$resource' ];

function Task($resource) {
	var resourceUrl = 'api/task/:task_id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}

