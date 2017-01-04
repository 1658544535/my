$(document).ready(function(){
	$('#banner').cycle({
	    fx: 'scrollRight',
	    speed: 'slow',
	    timeout: 5000,
	    pager: '#btn',
	    pagerEvent: 'mouseover',
	    pause: 1,
	    cleartype: 1,
        fit:true,
        width:'100%'
	});
	$("#btn a").html("");
	$("#btn a").hover(function () { $('#banner').cycle('pause') }, function () { $('#banner').cycle('resume') });
	$(".bigeyes #btn").css("margin-right", ($(".bigeyes").width() - $("#btn").width()) / 2);
	$(window).resize(function () {
	    $(".bigeyes #btn").css("margin-right", ($(".bigeyes").width() - $("#btn").width()) / 2);
	})
	if ($(".bigeyes2 #banner img").length==0) {
	    $(".bigeyes2").remove();
	}
});