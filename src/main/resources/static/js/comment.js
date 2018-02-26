$(function(){

    var articleId = $("#article_id").val();

    $("#submit").on("click", function(){
        var content = $("#comment").val();
        var nickName = $("#author").val();
        var email = $("#email").val();
        var site = $("#url").val();
        var parentId = $("#comment_parent").val();
        saveComment(articleId, content, nickName, email, site, parentId);
    });

    var pageNum = 1;
    var pageSize = 10;

    list(articleId, pageNum, pageSize);

});

var saveComment = function(articleId, content, nickName, email, site, parentId) {

    var params = {
        articleId: articleId,
        content: content,
        nickName: nickName,
        email: email,
        site: site,
        parentId: parentId
    };

    $.post(_contextPath + "/comment/save", params, function(data){
        if (data && data.code === 0) {
            window.location.reload(true);
        } else {
            alert(data.msg);
        }
    });

}

var reply = function(id) {
    $("#comment_parent").val(id);
    $("#comment").focus();
}

var list = function(articleId, pageNum, pageSize) {
    var pn = "?pageNum=" + pageNum + "&pageSize=" + pageSize;
    $.get(_contextPath + "/comment/list/" + articleId + pn, function(data) {
        if (data && data.code === 0) {
            render(articleId, data.data);
        }
    })
}

var render = function(articleId, data) {
    if (data) {
        var total = data.total;
        var list = data.list;
        var $comments = $("#postcomments");
        $("#total").text("(" + total + ")");
        for (var i=0, len=list.length; i<len; i++) {
            var li = list[i];
            var tpl = commentTpl.replace(/{%id%}/g, li.id)
                .replace(/{%staticPath%}/g, _staticPath)
                .replace(/{%content%}/g, li.toNickName == null ? li.content : ("<span style='color: #00a67c;'>@" + li.toNickName + "</span>&emsp;" + li.content))
                .replace(/{%nickName%}/g, li.nickName)
                .replace(/{%createTime%}/g, li.createTime);
            $comments.append($(tpl));
        }
        if (data.pages > 1 && data.hasNextPage) {
            var dom = '<p id="loadmore" class="ias_trigger"><a href="javascript:;" onclick="next({%articleId%}, {%pageNum%}, {%pageSize%})">加载更多</a></p>'.replace(/{%articleId%}/g, articleId)
                .replace(/{%pageNum%}/g, data.nextPage)
                .replace(/{%pageSize%}/g, data.pageSize);
            $comments.append($(dom));
        }
    }
}

var next = function(articleId, pageNum, pageSize) {
    $("#loadmore").remove();
    list(articleId, pageNum, pageSize);
}

var commentTpl = ''+
'<ol class="commentlist">'+
  '<li class="comment even thread-even depth-1" id="comment-{%id%}">'+
      '<div class="c-avatar"><img class="avatar" height="54" width="54" src="{%staticPath%}/images/default.png" style="display: block;">'+
          '<div class="c-main" id="div-comment-20508">{%content%}'+
              '<div class="c-meta"><span class="c-author">{%nickName%}</span>{%createTime%} <a rel="nofollow" class="comment-reply-link" href="javascript:;" onclick="reply(\'{%id%}\')">回复</a></div>'+
          '</div>'+
      '</div>'+
  '</li>'+
'</ol>';