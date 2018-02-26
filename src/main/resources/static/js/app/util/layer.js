define(function(require, exports, module){
    var $ = require('jquery');
    // 当前页面 $(document)
    //var screenWidth = $(window).width(); //当前窗口宽度
    //var screenHeight = $(window).height(); //当前窗口高度
    var screenWidth = $(document).width(); //当前页面宽度
    var screenHeight = $(document).height(); //当前页面高度

    var grayLayerHtml = '<div style="position: fixed; z-index: 3; left: 0; top: 0; width:'+screenWidth+'px; height: '+screenHeight+'px; background-color: rgba(0,0,0,.5);">{%tpl%}</div>';

    var closeTpl = '<span style="cursor: pointer; float: right;" id="j-panel-close">×</span>';

    var panelMarginV = "margin: 15% auto;";
    
    var panelWithTitleTpl = ''+
        '<div class="panel panel-default" style="width: 600px; '+panelMarginV+'">' +
        '<div class="panel-heading">' +
        '<h3 class="panel-title">{%title%}&nbsp;&nbsp;' +
        closeTpl +
        '</h3></div>' +
        '<div class="panel-body">' +
        '{%html%}' +
        '</div>' +
        '</div>' +
        '';

    var alertTpl = '' +
        '<div class="panel panel-default" style="width: 300px; '+panelMarginV+'">' +
        '<div class="panel-heading">' +
        '<h3 class="panel-title">{%title%}&nbsp;&nbsp;' +
        closeTpl +
        '</h3></div>' +
        '<div class="panel-body">' +
        '<p style="word-break:break-all; word-wrap:break-word;">' +
        '{%html%}' +
        '</p>' +
        '</div>' +
        '</div>' +
        '';
    
    var confirmTpl = '' +
	    '<div class="panel panel-default" style="width: 300px; '+panelMarginV+'">' +
	    '<div class="panel-heading">' +
	    '<h3 class="panel-title">{%title%}&nbsp;&nbsp;' +
	    '</h3></div>' +
	    '<div class="panel-body">' +
	    '<p style="word-break:break-all; word-wrap:break-word;">' +
	    '{%html%}' +
	    '</p>' +
	    '</div>' +
	    '<div class="panel-footer">' + 
	    '<div class="text-right">' + 
	    '<a id="j-panel-confirm-ok" class="btn btn-info">确定</a>' + 
	    '&nbsp;&nbsp;' + 
	    '<a id="j-panel-confirm-cancel" class="btn btn-default">取消</a>' + 
	    '</div>' + 
	    '</div>' +
	    '</div>' +
	    '';

    var panel = function(title, html, fn) {
        var p = _buildDom(panelWithTitleTpl, title, html);
        _show(p);
        if(typeof fn === 'function') fn(p);
        return p;
    };

    var alert = function(title, html, fn, ss) {
        if(title === null) title = '提示';
        var p = _buildDom(alertTpl, title, html);
        _show(p);
        if(typeof fn === 'function') fn();
        if(ss !== -1) {
            setTimeout(function(){
                _close(p);
            }, ss == null || ss <= 0 ? 2.5 * 1000 : ss * 1000);
        }
    };
    
    var confirm = function(title, html, fn) {
    	if(title === null) title = '提示';
        var p = _buildDom(confirmTpl, title, html);
        _show(p);
        p.find('#j-panel-confirm-ok').on('click', function(){
        	if(typeof fn === 'function') {
        		_close(p);
        		fn(true);
        	}
        });
        p.find('#j-panel-confirm-cancel').on('click', function(){
        	if(typeof fn === 'function') {
        		_close(p);
        		fn(false);
        	}
        });
    };

    var _buildDom = function(tpl, title, html) {
    	return $(grayLayerHtml.replace(/{%tpl%}/g, tpl).replace(/{%title%}/g, title).replace(/{%html%}/, html));
    }
    
    var _show = function($dom) {
        $('body').append($dom).show();
        $dom.find('#j-panel-close').on('click', function(){
            $dom.remove();
        });
    };

    var _close = function($dom) {
        $dom.remove();
    }

    var close = function(dom) {
    	_close($(dom));
    }

    exports.panel = panel;
    exports.alert = alert;
    exports.confirm = confirm;
    exports.close = close;

});