define(function(require, exports, module) {

	var $ = require('jquery'), URL = require('URL'), http = require('http'), layer = require('layer');

	var init = function() {
		$('#j-logout').on('click', function() {
			layer.confirm(null, "确认退出？", function(b) {
				if (b) {
					logout({}, function(data) {
						if (data && data.code === 0) {
							window.location.href = URL.userLogin();
						}
					});
				}
			});
		});
	};

	var logout = function(params, callback) {
		http.post(URL.userLogout(), params, function(data) {
			if (typeof callback === 'function')
				callback(data);
		});
	}

	exports.init = init;
	exports.logout = logout;

});