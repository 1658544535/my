$(function(){
	/*提交订单成功页*/
	
	//其它支付方式
	$(".osp-altBut").bind('click',function(){
		$(".os-ment-alter").show();
	})
	
	//选中支付方式
	$(".payment input").bind('click',function(){
		$(this).parents(".os-ment-alter").hide();
		var pmentBg = $(this).siblings("b").attr("bgPos");
		$(".os-pay-con .osp-ment b").css('background-position',pmentBg);
	})

	//支付方式tab切换
	$(".oma-tit span").bind('click',function(){
		var omaTitLen = $(".oma-tit span").index($(this));
		$(".oma-tit span").removeClass("otsOn").eq(omaTitLen).addClass("otsOn");
		$(".oma-con-list .payment").hide().eq(omaTitLen).show();
	})
	
	//提交订单成功页end
	//支付成功或失败弹层
	var pSecWid = $(".paySec").width();
	var pSecHei = $(".paySec").height();
	var pSecLeft = ($(window).width()-pSecWid)/2;
	var pSecTop = ($(window).height()-pSecHei)/2;
	var top=$(window).scrollTop();
	
	$(".paySec").css({'left':pSecLeft,'top':pSecTop});
	$(".paySecMask").css({'width':'100%','height':'100%','top':top+'px'});
	
	$(window).scroll(function(){
		top=$(window).scrollTop();
		$(".paySecMask").css({'width':'100%','height':'100%','top':top+'px'});
		$(".paySec").css({'left':pSecLeft+'px','top':(pSecTop+top)+'px'});
		
	})
	$(window).resize(function(){
		pSecLeft = ($(window).width()-pSecWid)/2;
		pSecTop = ($(window).height()-pSecHei)/2;
		$(".paySec").css({'left':pSecLeft+'px','top':(pSecTop+top)+'px'});
	    $(".paySecMask").css({'width':'100%','height':'100%','top':top+'px'});
	})
	
	$(".payBut").bind('click',function(){
		$(".paySec").show();
		$(".paySecMask").show();
		$("body").css({'height':$(window).height()+'px','overflow':'hidden'});
	})
	
	$(".paySec-close").bind('click',function(){
		$(".paySec").hide();
		$(".paySecMask").hide();
		$("body").css({'height':'auto','overflow':'auto'});
	})
	
	$('.pc-pailure-a').bind('click',function(){
		$('.paySec-close').trigger("click");
		$('.osp-altBut').trigger("click");
	})
	//支付成功或失败弹层end
	
	$(".twoDimText").html("下载手机酒仙网<br>随时随地查物流");
})