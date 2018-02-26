$(function() {
	$('.toggle-search').on('click', function() {
		$(".toggle-search").toggleClass("active");
		$(".search-expand").fadeToggle(250);
		$(".search-expand input").focus()
	});
	$('.screen-nav').on('click', function(){
		var y = $(".navbar .nav");
        var B = $(".navbar .nav");
        y.toggleClass("active");
        B.slideToggle(300);
	});
	$('.j-num').on('click', function(){
		var num = $(this).attr('data-num');
        go(num);
	});
	$('.rollto').on('click', function(){
		
	});
});
var go = function(num) {
    $('#j-page-num').val(num);
    $('form').submit();
};
