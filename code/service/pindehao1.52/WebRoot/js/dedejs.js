$.ajax({
		url:'InfoCount1.do',
		type:'post',
		dataType:'json',
		async:'false',
		error: function(XMLHttpRequest, textStatus, errorThrown){
		alert("加载失败!!!!!");
    	},
    	success: function(result){
    		$(function(){
				var count=result;
			/*  var tophtml="<div id=\"izl_rmenu\" class=\"izl-rmenu\"><a href=\"http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1\" target=\"_blank\" class=\"btn btn-qq\"></a><div class=\"btn btn-wx\"><img class=\"pic\" src=\"image/weixin.png\" onclick=\"window.location.href=\'#\'\"/></div><div class=\"btn btn-message\"><a href=\"systemInfoConWeb.do\"><div class=\"message\">今日有"+count+"条消息</div></a></div><div class=\"btn btn-top\"></div></div>";*/
				var tophtml="<div id=\"izl_rmenu\" class=\"izl-rmenu\"><div class=\"btn btn-qq\" onclick=\"javascript:if(confirm('客服人员将为您提供服务，请您确认')) {window.open('http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1');} else {return false;}\" ><a href=\"http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1\" target=\"_blank\"><div class=\"qq\">点击联系客服</div></a></div><div class=\"btn btn-wx\"><img class=\"pic\" src=\"image/weixin.png\" onclick=\"window.location.href=\'#\'\"/></div><div class=\"btn btn-message\" onclick=\"location.herf='systemInfoConWeb.do';\"><a href=\"systemInfoConWeb.do\"><div class=\"message\">您有"+count+"条消息未读</div></a></div><div class=\"btn btn-top\"></div></div>";
				$("#top").html(tophtml);
				$("#izl_rmenu").each(function(){
				$(this).find(".btn-wx").mouseenter(function(){
				$(this).find(".pic").fadeIn("fast");
					});
				$(this).find(".btn-wx").mouseleave(function(){
				$(this).find(".pic").fadeOut("fast");
					});
				$(this).find(".btn-message").mouseenter(function(){
				$(this).find(".message").fadeIn("fast");
					});
				$(this).find(".btn-message").mouseleave(function(){
				$(this).find(".message").fadeOut("fast");
					});
				$(this).find(".btn-qq").mouseenter(function(){
				$(this).find(".qq").fadeIn("slow");
					});
				$(this).find(".btn-qq").mouseleave(function(){
				$(this).find(".qq").fadeOut("fast");
					});
				$(this).find(".btn-top").click(function(){
				$("html, body").animate({
					"scroll-top":0
					},"fast");
					});
				});
		var lastRmenuStatus=false;
		$(window).scroll(function(){//bug
		var _top=$(window).scrollTop();
		if(_top>200){
			$("#izl_rmenu").data("expanded",true);
		}else{
			$("#izl_rmenu").data("expanded",false);
		}
		if($("#izl_rmenu").data("expanded")!=lastRmenuStatus){
			lastRmenuStatus=$("#izl_rmenu").data("expanded");
			if(lastRmenuStatus){
				$("#izl_rmenu .btn-top").slideDown();
			}else{
				$("#izl_rmenu .btn-top").slideUp();
			}
		}
		});
		});
    	
    }
});
