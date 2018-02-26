define(function(require, exports, module) {
    var $ = require("jquery"),
    	URL = require('URL'),
    	layer = require('layer'),
    	http = require('http');

    var init = function() {
        $('#j-submit').on('click', function(){
            var params = $('#j-user-login').serialize();
            loginSubmit(params, function(data){
                if(data) {
                    if(data.code === 0) {
                        window.location.href = URL.articleMyList();
                    } else {
                        layer.alert(null, data.msg);
                    }
                }
            });
        });
    };

    var loginSubmit = function(params, callback) {
    	http.post(URL.userLogin(), params, function(data){
            if(typeof callback === 'function') callback(data);
        });
    };

    exports.init = init;
    exports.loginSubmit = loginSubmit;

});