(function(){
	'use strict';

	angular.module('ibdb', [
		'ui.router', 
		'ibdb.helpers.routes', 
		'ibdb.views.main',
		'ibdb.views.books'
		]);
})();