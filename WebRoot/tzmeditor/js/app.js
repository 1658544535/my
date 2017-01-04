var phoneData = parent.phoneData;//编辑时读取数据库json
var url = parent.url;//保存json数据action
var imgUpfileUrl = parent.imgUpfileUrl;//上传图片action
var userFlag = parent.userFlag;
$(function(){
    defData = {};
    phoneData = phoneData||defData;
    preview();      //初始渲染

    //添加模块
    $("#phone_add button").bind("click", function(){
        var type = $(this).attr("data-type");
        var top = $("#phoneView").height();
        var data = {
	            type: "text",
	            data: {
	            	text: "文本内容"
	            }
        }
        if(type == "text"){
    	    data = {
                type: "text",
                data: {
                	text: "文本内容"
                }
            }
        } else if (type == "goods"){
        	data = {
                type: "goods",
                data: {
                    view: "big",
                    viewAttr: {
                        title: true,
                        price: true,
                        seeDetail: true
                    },
                    list: []
                }
            }
        } else if (type == "videos"){
        	data = {
                type: "video",
                data: {
                    src: ""
                }
            }
        } else if (type == "images"){
        	data = {
                type: "image",
                data: {
                    carousel: false,
                    list: [
                        //{goodId:"2", img: "images/img2.jpg", goodTitle: "商品2"},
                        //{goodId:"5", img: "images/img2.jpg", goodTitle: "Little Tikes美国小泰克红蓝小滑梯可折叠婴儿童室内小型宝宝滑梯"}
                    ]
                }
            }
        } else if (type == "coupon"){
	    	data = {
                type: "coupon",
                data: {
                    list: []
                }
            }
	    } else if (type == "ueditor"){
	    	data = {
                type: "ueditor",
                data: {
                    html: '<div>编辑器<b>内容</b></div>'
                }
            }
	    }
        var maxSortNum = 0;
        for(var item in phoneData){
            var itemNum = item.substring(4,item.length)
            itemNum = parseInt(itemNum);
            maxSortNum>itemNum ? '' : maxSortNum=itemNum+1;
        }
        var maxSort = "sort"+maxSortNum;
        data.id = maxSort;
        phoneData[maxSort] = data;
        preview(maxSort);
        module_edit($("#phoneView>section.active"));
        $(".phone-view").animate({"scrollTop": top});
    });

    //保存成草稿
    $("#submit .save").bind("click", function(){
        //console.log(getSaveData());     //打印得到的json数据
        var jsonStr = JSON.stringify(getSaveData());
        url = parent.url;
        //需ajax提交数据
        $.ajax({
          type: "POST",
          url: url,
          dataType: "json",
          data : {'saveData':jsonStr},
          success: function(data){
	    	 		if(data != null){
	    	 			alert("保存成功!");
	    	 			window.parent.history.go(-1);
	    	 		} else if(data == 0){
	    	 			alert("请先登录!");
	    	 		} else {
	    	 			alert("保存失败!");
	    	 		}
	              }
        });
    });

    //文本编辑
    $(document).delegate(".popover-text textarea", "keyup", function(){
        var id = $("#phoneView>section.active").attr("data-id");
        var aVal = $(this).val();
        phoneData[id]["data"]["text"] = aVal;
        preview(id);
    });
    //商品编辑
    $(document).delegate("#popover_goods_type input", "change", function(){
        var id = $("#phoneView>section.active").attr("data-id");
        var obj = $("#phoneView>section.active");
        phoneData[id]["data"]["view"] = $(this).val();
        if($(this).val() == "detail"){
            phoneData[id]["data"]["viewAttr"]["title"] = true;
            phoneData[id]["data"]["viewAttr"]["price"] = true;
        }
        module_edit(obj);
        preview(id);
    });
    $(document).delegate("#popover_goods_attr input", "change", function(){
        var id = $("#phoneView>section.active").attr("data-id");
        var obj = $("#phoneView>section.active");
        var attr = $(this).val();
        phoneData[id]["data"]["viewAttr"][attr] = $(this).is(":checked");
        module_edit(obj);
        preview(id);
    });
    $(document).delegate("#popover .good-rel", "click", function(){
        var goodId = $(this).parents("li").attr("data-goodid");
        var id = $("#phoneView>section.active").attr("data-id");
        var obj = $("#phoneView>section.active");
        $(this).parents("li").remove();
        var goodsList = phoneData[id]["data"]["list"];
        for(var i=0;i<goodsList.length;i++){
            if(goodsList[i]["goodId"] == goodId){
                phoneData[id]["data"]["list"].splice(i,1);
            }
        }
        preview(id);
    });
    $(document).delegate("#popover_goods_add", "click", function(){
        goodsList("", 1);
    });
    //视频编辑
    $(document).delegate(".popover-video textarea", "blur", function(){
        var id = $("#phoneView>section.active").attr("data-id");
        var aVal = $(this).val();
        var reg = /<iframe[^>]*src[=\'\"\s]+([^\"\']*)[\"\']?[^>]*>/gi; 
        if (reg.exec(aVal)){
            aVal = RegExp.$1;
        }
        phoneData[id]["data"]["src"] = aVal;
        preview(id);
    });
    //图片编辑
    $(document).delegate("#fileupload", "click", function(){
        $('#fileupload').fileupload({
            autoUpload: true,//是否自动上传
            url: imgUpfileUrl,//上传地址
            dataType: 'json',
            success: function (data){//设置文件上传完毕事件的回调函数
            	//console.log(data);
                var src = data.src;
                var id = $("#phoneView>section.active").attr("data-id");
                phoneData[id]["data"]["list"].push({"img":src,"goodId":"","goodTitle":"暂无"});
                preview(id);
                module_edit($("#phoneView>section.active"));
            },
            error: function() {
                alert("图片上传失败，请重试");
                $("#fileupload .mask").hide();
            },
            progressall: function (e, data) {//设置上传进度事件的回调函数
                $("#fileupload .mask").show();
            }
        });
    });
    //删除图片事件
    $(document).delegate(".popover-image .image-rel", "click", function(){
    	//console.log($(this).prev().attr("src"));
    	var path = $(this).prev().attr("src");
        $.ajax({
             type: "GET",
             url: "delSpecialImgUpfile.do",
             data: {
            	 filePath:path
             },
             dataType: "json",
             success: function(data){
                         if(data.result){
                        	 console.log("删除成功!");
                         } else {
                        	 alert("删除图片失败!");
                         }
                      }
        });
        var id = $("#phoneView>section.active").attr("data-id");
        var index = $(".popover-image>div.col-xs-3").index($(this).parent());
        phoneData[id]["data"]["list"].splice(index,1);
        preview(id);
        module_edit($("#phoneView>section.active"));
    });
    $(document).delegate(".popover-image .image-link", "click", function(){
    	$(".popover-image .image-link").removeClass("active");
        $(this).addClass("active");
        goodsList("",1);
    });
    $(document).delegate("#popover_images_type input", "change", function(){
        var id = $("#phoneView>section.active").attr("data-id");
        var obj = $("#phoneView>section.active");
        if($(this).val() == "0"){
            phoneData[id]["data"]["carousel"] = false;
        }else{
            phoneData[id]["data"]["carousel"] = true;
        }
        
        module_edit(obj);
        preview(id);
    });
    //编辑器编辑
    $(document).delegate(".view-ueditor", "click", function(){
        var ueNum = $(this).attr("data-ue");
        !!ueNum ? ++ueNum : ueNum=0;
        $("#ueditor").attr("id",'ueditor'+ueNum);
        $(this).attr("data-ue", ueNum);
        var ue = UM.getEditor('ueditor'+ueNum);
        ue.addListener('contentChange', function(editor){
            if(ue.isFocus()){
                var html = ue.getContent();
                var id = $("#phoneView>section.active").attr("data-id");
                phoneData[id]["data"]["html"] = html;
                preview(id);
            }
            
        })
    });
    //优惠券编辑
    $(document).delegate("#popover .coupon-rel", "click", function(){
        var couponId = $(this).parents("li").attr("data-couponid");
        var id = $("#phoneView>section.active").attr("data-id");
        var obj = $("#phoneView>section.active");
        $(this).parents("li").remove();
        var couponsList = phoneData[id]["data"]["list"];
        for(var i=0;i<couponsList.length;i++){
            if(couponsList[i]["couponId"] == couponId){
                phoneData[id]["data"]["list"].splice(i,1);
            }
        }
        preview(id);
    });
    $(document).delegate("#popover_coupons_add", "click", function(){
        couponsList();
    });


    
});

/* 
  渲染可视化函数
*/
function preview(activeId){
    $("#phoneView").html('<div>加载中...</div>');
    var bt = baidu.template;
    baidu.template.ESCAPE = false;
    data = phoneData;

    // 渲染可视化
    $("#phoneView").html('');
    for(var item in data){
        var itemType = data[item]["type"];
        var itemData = data[item];
        var itemHtml = baidu.template("tpl_"+itemType, itemData);
        $("#phoneView").append(itemHtml);
    }
    $("#phoneView>section").append('<div class="tpl-edit"><a class="btn btn-primary btn-xs edit">编辑</a><a class="btn btn-danger btn-xs del">删除</a></div>');
    if(!!activeId){
        $("#phoneView>section").each(function(index, el){
            if($(el).attr("data-id") == activeId){
                $(el).addClass("active");
            }
        });
    }
    // 拖拽效果初始化
    if($("#phoneView>section").length>0){
        window.x = new Sortable(phoneView, {
            group: "words",
            onStart: function(evt){
                //拖拽前执行
                $(".popover-box .popover").hide();
            },
            onEnd: function(evt){
                //拖拽完执行
                var itemEl = $(evt.item);
                itemEl.trigger("click");
                phoneData = getSaveData();
            }
        });
    }
    //绑定滚动事件
    $(".phone-view").bind("scroll", function(){
        if($(".popover-box .popover").is(":visible")){
            var osection = $("#phoneView>section.active");
            popover(osection, true);
        }
    });

    //绑定编辑事件
    $(".tpl-edit a.edit").bind("click", function(e){
        var osection = $(this).parent().parent();
        module_edit(osection);
        osection.addClass("active");
    });
    $("#phoneView>section").bind("click", function(){
        module_edit($(this));
        $(this).addClass("active");
    });
    //绑定删除事件
    $(".tpl-edit a.del").bind("click", function(){
        var osection = $(this).parent().parent();
        module_del(osection);
        osection.addClass("active");
        return false;
    });
    return data;
}

function module_edit(obj){
    var id = obj.attr("data-id");
    var data = phoneData[id];
    var type = obj.attr("data-type");
    var popoverHtml = baidu.template("popover_"+type, data.data);
    popover(obj);
    $("#popover").html(popoverHtml);
}

function module_del(obj){
    popover(obj);
    var popoverHtml = '<div class="popover-del text-center"><div><strong>确定删除？</strong></div><div><button class="btn btn-danger btn-sm"type="button">确定</button><button class="btn btn-default btn-sm"type="button">取消</button></div></div>';
    $("#popover").html(popoverHtml);
    $(".popover-del button:eq(0)").bind("click", function(){
        var id = $(obj).attr("data-id");
        delete phoneData[id];
        $(".popover-box .popover").hide();
        preview();
    });
    $(".popover-del button:eq(1)").bind("click", function(){
        $(".popover-box .popover").hide();
        obj.removeClass("active");
    });
}

function popover(obj, scroll){
    var osection = obj;
    var top = osection.offset().top - $(".phone-view").offset().top + 100;
    top<=0 ? top=0 : '';
    top>=710 ? top=710 : '';
    if(!scroll) $("#popover").html('<div>加载中...</div>');
    
    var id = obj.attr("data-id");
    $("#popover").attr("data-id", id);
    $("#phoneView>section").removeClass("active");
    obj.addClass("active");
    $(".popover-box .popover").show().stop().animate({"top":top});
}

function getSaveData(){
    var nowData = {};
    $("#phoneView>section").each(function(index,el){
        var id = $(el).attr("data-id");
        nowData[id] = phoneData[id];
    });
    return nowData;
}
//查询商品列表
function goodsList(searchTxt, pageNum){
    var data = {};
    $.ajax({
        type: "POST",
        url: "findActProBucket.do",
        async:false,
        data: {
            "productPojo.productName": searchTxt,
            "page.pageNo": pageNum,
            "userFlag": userFlag
        },
        success: function(products){
        	if(products != null){
        		//console.log(products);
        		data = eval("("+products+")");
        	} else {
        		alert("查询不到商品");
        	}
        },
        error: function() {
            alert("查询有误!");
        }
    });
    //console.log(data);
    var goodsListHtml = baidu.template("tpl_goodsList", data);
    $("#goodsList_box").html(goodsListHtml);
    var id = $("#phoneView>section.active").attr("data-id");
    var selectedGoods = phoneData[id]["data"]["list"];
    for(var item in selectedGoods){
        var selectedGoodId = selectedGoods[item]["goodId"];
        $("#goodsList tr").each(function(index, el){
            var goodId = $(el).attr("data-goodid");
            if(selectedGoodId == goodId){
                $(el).find(".goods-select").attr("disabled","disabled");
            }
        });
    };
    $("#goodsList .goods-select").bind("click", function(){
    	//console.log("选取商品1");
    	if($("#phoneView>section.active").attr("data-type") == "goods"){
            var id = $("#phoneView>section.active").attr("data-id");
            var goodId = $(this).parents("tr").attr("data-goodid");
            var goodData = {};
            for(var item in data["list"]){
                if(data["list"][item]["goodId"] == goodId){
                    goodData = data["list"][item];
                    phoneData[id]["data"]["list"].push(goodData);
                    $('#goods_add').modal('hide');
                    preview(id);
                    module_edit($("#phoneView>section.active"));
                    return false;
                }
            }
        }else{
            var id = $("#phoneView>section.active").attr("data-id");
            var goodId = $(this).parents("tr").attr("data-goodid");
            for(var item in data["list"]){
                if(data["list"][item]["goodId"] == goodId){
                    var newTitle = data["list"][item]["title"];
                    var newGoodId = data["list"][item]["goodId"];
                    var newActivityId = data["list"][item]["activityId"];
                    var index = $(".popover-image .image-link").index($(".popover-image .image-link.active"));
                    phoneData[id]["data"]["list"][index]["goodTitle"] = newTitle;
                    phoneData[id]["data"]["list"][index]["goodId"] = newGoodId;
                    phoneData[id]["data"]["list"][index]["activityId"] = newActivityId;
                    $(".popover-image .image-link").removeClass("active");
					$('#goods_add').modal('hide');
					preview(id);
					module_edit($("#phoneView>section.active"));
//					console.log(phoneData);
					return false;
                }
            }
        }
    });
    $("#goodsPager a").bind("click", function(){
    	console.log("点击下一页");
        var aPageNum = $(this).attr("data-page");
        if(!!aPageNum){
            var aSearchTxt = $("input[name='goodSearch']").val();
            goodsList(aSearchTxt, aPageNum);
        }
    });
    $("#goodSearch").bind("click", function(){
        var aSearchTxt = $("input[name='goodSearch']").val();
        goodsList(aSearchTxt, 1);
    });
}
//优惠劵列表
function couponsList(aSearchTxt,aPageNum){
	var data = {};
	/*data = {
	        list: [
	            {couponId: 1, disabled: false, title:"萌爸萌宝亲子大赛抽奖三等奖", price: "满200减30"},
	            {couponId: 2, disabled: true, title:"萌爸萌宝亲子大赛抽奖二等奖", price: "满200减50"},
	            {couponId: 3, disabled: false, title:"萌爸萌宝奖", price: "满1200减30"},
	            {couponId: 4, disabled: true, title:"萌爸奖", price: "满2200减50"}
	        ],
	        pager:{
	            count: 10,
	            now: 1
	        }
	    }*/
    $.ajax({
        type: "POST",
        url: "findcouponListAll.do",
        async:false,
        data: {
            "couponPojo.name": aSearchTxt,
            "page.pageNo": aPageNum
        },
        success: function(coupons){
        	if(coupons != null){
        		//console.log(coupons)
        		data = eval("("+coupons+")");
        	} else {
        		alert("查询不到商品");
        	}
        },
        error: function() {
            alert("查询有误!");
        }
    });
    //console.log(data);
    var couponsListHtml = baidu.template("tpl_couponsList", data);
    $("#couponsList_box").html(couponsListHtml);
    var id = $("#phoneView>section.active").attr("data-id");
    var selectedCoupons = phoneData[id]["data"]["list"];
    for(var item in selectedCoupons){
        var selectedCouponId = selectedCoupons[item]["couponId"];
        $("#couponsList tr").each(function(index, el){
            var couponId = $(el).attr("data-couponid");
            if(selectedCouponId == couponId){
                $(el).find(".coupon-select").attr("disabled","disabled");
            }
        });
    };
    $("#couponsList .coupon-select").bind("click", function(){
        var id = $("#phoneView>section.active").attr("data-id");
        var couponId = $(this).parents("tr").attr("data-couponid");
        var couponData = {};
        for(var item in data["list"]){
            if(data["list"][item]["couponId"] == couponId){
                couponData = data["list"][item];
                phoneData[id]["data"]["list"].push(couponData);
                $('#coupon_add').modal('hide');
                preview(id);
                module_edit($("#phoneView>section.active"));
                return false;
            }
        }
    });
    $("#couponsPager a").bind("click", function(){
        var aPageNum = $(this).attr("data-page");
        if(!!aPageNum){
            var aSearchTxt = $("input[name='couponSearch']").val();
            couponsList(aSearchTxt, aPageNum)
        }
    });
}

