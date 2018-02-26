define(function(require, exports, module){

	var init = function(currentPage) {
    	console.log(currentPage);
    	
    	// 图片事件
    	_imgEventInit();
    	
        switch (currentPage) {
            case "admin/article/myList": {
            	_articleListInit();
            	return ;
            }
            case "admin/article/add": {
            	_userLogout();
            	return ;
            }
        }
    };
    
    function _categorys(el1, el2) {
    	var categorys = require("article.category"), $ = require("jquery");
    	$(el1).on('click', function(){
    		$(el2).toggle();
    	});
    	categorys.getCategorys(function(html){
    		$(el2).append(html);
    	});
    }
    
    function _userLogout() {
    	var logout = require('user.logout');
    	logout.init();
    }
    
    function _articleInit() {
    	var page = require('page');
        page.init();
    }
    
    function _articleListInit() {
    	_articleInit();
    	_userLogout();
    	var list = require("article.list");
    	list.init();
    }
    
    function _imgEventInit() {
    	var $ = require('jquery'),
    	zoomify = require('zoomify');
    	$('img').zoomify();
    }
    
    exports.init = init;
});