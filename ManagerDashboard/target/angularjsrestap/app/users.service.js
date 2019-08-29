angular.module('managerApp').factory('Users', Users);

Users.$inject = [ '$resource' ];

function Users($resource) {
	var resourceUrl = 'api/users/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}

