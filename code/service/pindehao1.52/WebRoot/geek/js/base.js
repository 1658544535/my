$(function(){
	
	/*------------------导航---------------------*/
	$(".ui-nav-item.has-sub").hover(function(){
		$(this).find("ul.ui-nav-submain").addClass("preview");
		$(this).children("a").css("background","#ffce00");
	},function(){
		$(this).find("ul.ui-nav-submain").removeClass("preview");
		$(this).children("a").css("background","");
	});
	
	
	/*------------------意见反馈---------------------*/
	$(".m-feedback").on("click",function(){
		$(".m-fb-shadow").show();
		$(".m-fb-dialog").addClass("in");
	})
	$(".m-fb-shadow,.m-fb-dialog .footer .btn-cancel").on("click",function(){
		$(".m-fb-shadow").hide();
		$(".m-fb-dialog").removeClass("in");
	})
	$(".m-fb-dialog .footer .btn-sure").on("click",function(){
		var aVal = $(".m-fb-dialog .mnc .control-text").val();
		if(aVal == ""){
			tipsPopup("亲，请你多说两句吧","error");
			return false;
		}
	})
	
	
	
	/*------------------全选---------------------*/
	$("input.J_checkedAll").on("change",function(){
		if($(this).prop("checked")){
			$(this).parents("table").find("input:checkbox").prop("checked",true);
		}else{
			$(this).parents("table").find("input:checkbox").prop("checked",false);
		}
	})
	
	
	
	/*------------------弹窗隐藏---------------------*/
	$(".miniDialog_close_x,.miniDialog_button_secondary,.view-BtnCancelConfirm").on("click",function(){
		$(".miniDialog_wrapper,.miniDialog_mask").hide();
	})
	
	/*$(".title-2").click(function(){
		tipsPopup("亲，请你多说两句吧","success");
	});*/
})

/*------------------提示---------------------*/
function tipsPopup(tips,state){
	clearTimeout(tipTimer);
	$(".messageBox").remove();

	if(state == 'error'){
		tips = '<i class="iconfont">&#xf0155;</i>' + tips;
		var aTip = '<div class="messageBox" style="display:none;position: fixed; top: 0px; left: 50%; width: 400px; margin-left: -200px; padding: 6px 10px; z-index: 9999; opacity: 0.97;"><div style="margin: 4px 0px; color: rgb(255, 255, 255); padding: 6px 28px 6px 10px; border-radius: 4px; line-height: 1.3em; font-size: 13px; position: relative; height: 17px; background: rgb(217, 83, 79);"><span class="msg">'+tips+'</span></div></div>'		
	}else if(state == 'success'){
		tips = '<i class="iconfont">&#xf0156;</i>' + tips;
		var aTip = '<div class="messageBox" style="display:none;position: fixed; top: 0px; left: 50%; width: 400px; margin-left: -200px; padding: 6px 10px; z-index: 9999; opacity: 0.97;"><div style="margin: 4px 0px; color: rgb(255, 255, 255); padding: 6px 28px 6px 10px; border-radius: 4px; line-height: 1.3em; font-size: 13px; position: relative; height: 17px; background: rgb(92, 184, 92);"><span class="msg">'+tips+'</span></div></div>'
	}
	
	$("body").append(aTip);
	$(".messageBox").stop().fadeIn();
	var tipTimer = setTimeout(function(){$(".messageBox").stop().fadeOut("fast",function(){$(".messageBox").remove();});},2400);
}