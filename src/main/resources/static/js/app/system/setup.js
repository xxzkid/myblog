define(function(require, exports, module) {

    var $ = require("jquery"),
        http = require("http"),
        URL = require('URL'),
        layer = require('layer');


    var init = function(){
        $('.j-update').on('click', function () {
            update($(this));
        });
    }

    var update = function ($dom) {
        var prevDiv = $dom.parent("div").prev("div");
        var divInput = prevDiv.find("input");
        var name = divInput.attr('name');
        var value = divInput.val();
        var params = {paramName: name, paramValue: value};
        http.post(URL.setup(), params, function (data) {
            if (data.code === 0) {
                layer.alert(null, data.msg, function () {
                    window.location.reload(true);
                })
            }
        });
    }

    exports.init = init;
});
