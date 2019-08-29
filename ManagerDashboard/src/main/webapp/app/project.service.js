angular.module('managerApp').factory('Project', Project);

Project.$inject = [ '$resource' ];

function Project($resource) {
	var resourceUrl = 'api/project/:project_id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}

