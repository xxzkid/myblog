define(function(){

    return {
        'root' : function() {
            return _contextPath + "/";
        },
        'articleAdd': function() {
            return _contextPath + "/admin/article/add";
        },
        'articleDelete' : function(articleId) {
            return _contextPath + "/admin/article/delete/" + articleId;
        },
        'userLogin' : function() {
            return _contextPath + "/user/login";
        },
        'userLogout' : function() {
            return _contextPath + "/user/logout";
        },
        'articleMyList' : function() {
            return _contextPath + "/admin/article/myList";
        },
        'getPic' : function () {
            return _contextPath + "/getPic";
        },
        'upload' : function() {
            return _contextPath + "/upload";
        },
        'uploadSwf' : function() {
            return _staticPath + "/js/plugins/uploader/Uploader.swf";
        },
        'addCategory' : function() {
        	return _contextPath + "/admin/article/addCategory";
        },
        'categorys' : function() {
        	return _contextPath + "/article/categorys";
        },
        'setup' : function () {
            return _contextPath = "/admin/system/setupSave";
        }
    };

});