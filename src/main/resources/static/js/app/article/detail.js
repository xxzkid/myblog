define(function(require, exports, module) {
    var $ = require('jquery'),
    	URL = require('URL'),
    	layer = require('layer'),
    	http = require('http');

    var init = function() {
        $('.j-delete-article').on('click', function () {
        	var articleId = $(this).attr('data-article-id');
        	layer.confirm(null, "确认删除该文章吗？", function(b) {
        		if(b){
                    deleteArticle(articleId, function(data){
                        layer.alert(null, data.msg, function(){
                            if(data.code === 0) {
                                window.location.href = URL.postMyList();
                            }
                        });
                    });
        		}
        	});
        });
    };

    var deleteArticle = function(articleId, callback) {
    	http.get(URL.articleDelete(articleId), {}, function(data){
            if(typeof callback === 'function') callback(data);
        });
    }

    exports.init = init;
    exports.deleteArticle = deleteArticle;

});