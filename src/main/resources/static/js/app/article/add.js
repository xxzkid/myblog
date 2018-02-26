define(function(require, exports, module) {
    var $ = require('jquery'),
        URL = require('URL'),
        layer = require('layer'),
        juicer = require('juicer'),
        WebUploader = require('WebUploader'),
        http = require('http'),
        clipboardUtil = require('clipboard-util'),
        category = require("article.category");

    /**
     * article add.html init
     */
    var init = function(){
        $('#j-submit').on('click', function(){
            var params = $('#j-article-form').serialize();
            submitArticle(params, function(data){
                layer.alert(null, data.msg, function(){
                    if(data.code === 0) {
                        window.location.reload(true);
                    }
                });
            });
        });

        $('#j-choose-pic').on('click', function(){
            getPic({}, function(data){
                if(data.code === 0) {
                    layer.panel("选择图片", juicer(thumbnailHtml, {"list":data.data}), function(){
                        var uploadBtnId = "#j-upload";
                        var $row = $(uploadBtnId).parent().find(".row");
                        var str = "已复制";
                        $row.delegate('.j-thumbnail', 'click', function(){
                        	var resUrl = $(this).find('img').attr('src');
                        	clipboardUtil.setData(resUrl);
                        	layer.alert(null, resUrl + str, null);
                        });
                        initUploader(uploadBtnId, function(file, response) {
                            if(response.code === 0) {
                                var resUrl = response.data[0].fullUrl;
                                clipboardUtil.setData(resUrl);
                                layer.alert(null, resUrl + str, null);
                                $row.append(juicer(thumbnailItemHtml, {"item":{"fullUrl":resUrl}}));
                            } else {
                                layer.alert(null, response.msg);
                            }
                        });
                    });
                } else {
                    layer.alert(null, data.msg);
                }
            });
        });
        
        $('#j-add-category').on('click', function(){
        	layer.panel('添加分类', categoryFormHtml, function(panel){
        		$('#j-category-form #j-add-category').on('click', function(){
        			var param = $(this).parents('#j-category-form').serialize();
        			category.addCategory(param, function(data, html){
        				layer.alert(null, data.msg);
        				if(data.code === 0) {
        					$("select[name='categoryId']").append(html);
        					layer.close(panel);
        				}
        			});
        		});
        	});
        });
    };

    var initUploader = function($dom, successFn) {
        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: URL.uploadSwf(),
            // 文件接收服务端。
            server: URL.upload(),
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: $dom,
            // 限制文件数量
            fileNumLimit: 1,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        uploader.on('uploadSuccess', function(file, response){
            if(typeof successFn === 'function') successFn(file, response);
        });

        uploader.on('uploadFinished', function(){
            this.reset();
        });
    };

    var getPic = function(params, fn) {
    	http.post(URL.getPic(), params, function(data){
            if(typeof fn === 'function') fn(data);
        });
    };

    var thumbnailItemHtml = '' +
        '<div class="col-xs-2 col-md-2">' +
        '<a href="javascript:;" class="thumbnail j-thumbnail" title="点击图片获取图片地址">' +
        '<img src="${item.fullUrl}" width="200" height="200">' +
        '</a>' +
        '</div>' +
        '';

    var thumbnailHtml = ''+
        '<p class="btn btn-success" id="j-upload">上传图片</p><p></p>' +
        '<div class="row">' +
        '{@each list as item}' +
        thumbnailItemHtml +
        '{@/each}' +
        '</div>' +
        '';
    
    var categoryFormHtml = ''+
    	'<form id="j-category-form" role="form" onsubmit="return false;">'+
    	'<p class="form-group">'+
    	'分类名称:<input type="text" class="form-control" name="name" placeholder="分类名称">'+
    	'</p>'+
    	'<p class="form-group">'+
    	'分类别名:<input type="text" class="form-control" name="aliasName" placeholder="分类别名，输入英文或者拼音">'+
    	'</p>'+
    	'<p class="form-group">'+
    	'<a href="javascript:;" class="btn btn-success" id="j-add-category">保存</a>'+
    	'</p>'+
    	'</form>'+
    	'';

    var submitArticle = function (params, callback) {
    	http.post(URL.articleAdd(), params, function(data){
            if(typeof callback === 'function') callback(data);
        });
    };

    exports.init = init;
    exports.submitArticle = submitArticle;
});