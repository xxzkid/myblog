define(function(require, exports, module) {
    var $ = require('jquery'), 
    	page = require('page'),
    	http = require('http');
    
    
    var init = function() {
    	$('.j-sel-category').change(function(){
    		page.resetPageNum();
    		$('form').submit();
    	})
    	$('.j-sel-class').change(function(){
    		var val = $(this).val();
    		$('#j-class').attr('name', val);
    	});
    	if (http.getQueryString(window.location.href)['type']) {
            $('.j-sel-class').val();
        }
    }
    
    exports.init = init;
});