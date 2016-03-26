(function(){
	'use strict';

	 angular.module('ibdb', [
		'ui.router', 
		'ibdb.helpers.routes', 
		'ibdb.views.main',
		'ibdb.book.factory',
		'ibdb.views.books'
		
		]);
})();