angular.module('managerUsersApp').factory('User', User);

User.$inject = [ '$resource' ];

function User($resource) {
	var resourceUrl = 'api/user/:user_id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}

