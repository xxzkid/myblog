define(function(require, exports, module){
	
	var $ = require("jquery");
	var layer = require("layer");
	
	var _ajax = function(type, url, data, success, dataType) {
		$.ajax({
			type: type,
			url: url,
			data: data,
			success: success,
			dataType: dataType
		});
	}
	
	var post = function(url, params, fn, type) {
		_ajax('POST', url, params, function(data){
			console.log(data);
			if(data/* && data.result*/) {
				if(typeof fn === 'function') fn(/*data.result*/data);
			} else {
				layer.alert(null, "服务端数据格式不正确");
			}
		}, type == null ? "json" : type);
	};
	
	var get = function(url, params, fn, type) {
		_ajax('GET', url, params, function(data){
			console.log(data);
			if(data/* && data.result*/) {
				if(typeof fn === 'function') fn(/*data.result*/data);
			} else {
				layer.alert(null, "服务端数据格式不正确");
			}
		}, type == null ? "json" : type);
	};
	
	var getQueryString = function(href) {
		var v = {};
		var idx = href.indexOf('?');
		if(idx > -1) {
			var queryString = href.substring(idx + 1);
			if($.trim(queryString) === '') {
				return v;
			}
			var kvs = queryString.split('&');
			for(var i in kvs) {
				var kv = kvs[i];
				var temp = kv.split('=');
				v[temp[0]] = temp[1];
			}
		}
		return v;
	}
	
	exports.post = post;
	exports.get = get;
	exports.getQueryString = getQueryString;
	
});