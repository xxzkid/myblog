define(function(require, exports, module){
    var $ = require('jquery');

    var go = function(num) {
    	$('#j-page-num').val(num);
        $('form').submit();
    }
    
    var init = function() {
        $('.j-num').on('click', function(){
            var num = $(this).attr('data-num');
            go(num);
        });
        $('.j-sel-num').change(function(){
        	var num = $(this).val();
        	go(num);
        })
    };
    
    var resetPageNum = function() {
    	$('#j-page-num').val(1);
    }

    exports.init = init;
    exports.resetPageNum = resetPageNum;

});