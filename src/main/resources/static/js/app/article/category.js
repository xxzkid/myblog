define(function(require, exports, module) {
	
	var http = require("http"),
		URL = require("URL"),
		juicer = require('juicer');
	
	var categoryTpl = "" +
		"{@each list as li}"+
		"<li><a href=\""+_contextPath+"/article/list?categoryId=${li.id}\">${li.name}</li>"+
		"{@/each}"+
		"";
	
	var categoryTpl2 = ""+
		"<option value=\"${id}\" selected>${name}</option>"
		"";
	
	var getCategorys = function(fn) {
		http.get(URL.categorys(), {}, function(data){
			if(typeof fn === 'function') fn(juicer(categoryTpl, {"list":data.data}));
		});
	};
	
	var addCategory = function(params, fn) {
    	http.post(URL.addCategory(), params, function(data){
    		if(typeof fn === 'function') {
    			var html = "";
    			if(data.code === 0) {
    				html = juicer(categoryTpl2, {id:data.data.id, name:data.data.name});
    			}
    			fn(data, html);
    		}
    	})
    };
	
	exports.getCategorys = getCategorys;
	exports.addCategory = addCategory;
});