(function(){

	$(function(){
		g_init();

		/*
		* 全浏览器跳转
		*/
		$("a.winLink").bind("click", function(){
			var aLink = $(this).attr("href");
			var parentWin = window.parent.document;
			parentWin.location.href = aLink;
			return false;
		});


		/*
		* 图片预览
		*/
		$(".upLoadImg").each(function(index, el) {
			var ImgSrc = $(el).find(".upLoadImg-show img").attr("src");
			if(ImgSrc == ""){
				$(el).addClass("no-upLoadImg");
			}else{
				$(el).removeClass("no-upLoadImg");
			}
		});
		$('.upLoadImg .upLoadImg-file input[type="file"]').bind("change", function(){
			var _this = $(this);
			var url = _this.val();
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(_this.get(0).files[0]);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(_this.get(0).files[0]);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
			}

			//图片宽高
			var img = new Image();
		    img.src = url;
		    img.onload=function(){
		    	_this.parents(".upLoadImg").removeClass("no-upLoadImg");
		    	_this.parents(".upLoadImg").find(".upLoadImg-show img").attr("src",url);

		    	_this.parents(".upLoadImg").find("label.error").remove();
		    }
		});

	});

})();


/*
* 数据加载后初始化
*/
function g_init(){
	//自定义checkbox
	g_checkbox();

	//内容滚动条
	$('.nano-main').nanoScroller({preventPageScrolling: true});
}


/*
* 弹窗
* obj      -- 弹框id
* openFun  -- 打开弹窗触发函数
* sureFun  -- 点击确定触发函数
* closeFun -- 点击关闭触发函数
*/
function showPopup(e){
	$(e.obj).show();
	!!e.openFun ? e.openFun() : '';
	$(e.obj).find(".popup-close")
		.unbind("click")
		.bind("click",function(){
			$(e.obj).hide();
			!!e.closeFun ? e.closeFun() : '';
		});
	$(e.obj).find(".popup-sure")
		.unbind("click")
		.bind("click",function(){
			!!e.sureFun ? e.sureFun() : '';
			$(e.obj).hide();
		});
}


/*
* checkbox
*/
var ifCheckAll = true;
function g_checkbox(){
	if($("input.ckx").length<=0) return false;
	$("input.ckx").each(function(index, el) {
		if($(el).parents(".ckx-box").length<=0){
			if($(el).is(":checked")){
				$(el).wrap('<lable class="ckx-box active"></div>');
			}else{
				$(el).wrap('<lable class="ckx-box"></div>');
			}
		}
	});
	$(".ckx-box").unbind("click").bind("click", function(){
		if($(this).find("input").is(":checked")){
			$(this).removeClass("active");
			$(this).find("input").prop("checked",false);
		}else{
			$(this).addClass("active");
			$(this).find("input").prop("checked",true);
		}
	})
	$("#check-all").unbind("click").bind("click", function(){
		if(ifCheckAll){
			ifCheckAll = false;
			$(".main-table input.ckx").each(function(index, el) {
				$(el).prop("checked",true);
				$(el).parent().addClass("active");
			});
		}else{
			ifCheckAll = true;
			$(".main-table input.ckx").each(function(index, el) {
				$(el).prop("checked",false);
				$(el).parent().removeClass("active");
			});
		}
	});
}