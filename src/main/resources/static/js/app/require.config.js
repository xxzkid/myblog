requirejs.config({
    //urlArgs: 'v=v2.0',
    urlArgs : 'v=' + new Date().getTime(),
    baseUrl: (typeof _contextPath === 'undefined' ? '/' : _contextPath + '/') + 'js/',
    paths: {
        'jquery': 'lib/jquery-1.12.4',
        'juicer': 'lib/juicer-min',
        'zoomify': 'lib/zoomify.min',
        'bootstrap': 'lib/bootstrap.min',

        'jquery.serialize': 'plugins/jquery.serialize',
        'WebUploader': "plugins/uploader/webuploader.min",
        'Clipboard': 'plugins/clipboard.min',

        'layer': 'app/util/layer',
        'http': 'app/util/http',
        'clipboard-util': 'app/util/clipboard-util',

        'URL': 'app/url',
        'page': 'app/page',
        'common': 'app/common',

        'article.add': 'app/article/add',
        'article.detail': 'app/article/detail',
        'article.category': 'app/article/category',
        'article.list': 'app/article/list',

        'user.login': 'app/user/login',
        'user.logout': 'app/user/logout',

        'system.setup': 'app/system/setup'

    },
    shim: {
        'bootstrap': ['jquery'],
        'juicer': {
            exports: 'juicer'
        },
        'WebUploader': {
            exports: 'WebUploader'
        },
        'Clipboard': {
            exports: 'Clipboard'
        },
        'zoomify': {
            exports: 'zoomify'
        }
    }
});

require(["jquery", "bootstrap", "common"], function ($, bootstrap, common) {

    var page = $("#page");

    var currentPage = page.attr("data-current-page");
    var targetModule = page.attr("data-target-module");

    common.init(currentPage);

    if (targetModule) {
        // 页面加载完毕后再执行相关业务代码比较稳妥
        $(function () {
            // 不要在这里写业务代码，全部统一调用init方法,也就是每个模块都暴露一个init方法用于事件监听，页面内容加载等
            require([targetModule], function (targetModule) {
                targetModule.init(currentPage);
            });
        });
    }
    return;
});
