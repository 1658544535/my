var phoneData = null;
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
$(function(){
    //初始需ajax去请求全部模块的数据
	//alert(GetQueryString("specialId"));
	$.ajax({
        type: "POST",
        url: "getPageData.do",
        async:false,
        data: {
            "id": GetQueryString("id"),
            "type": GetQueryString("type"),
        },
        success: function(result){
        	if(result != null){
        		if(result == "1"){
            		alert("id不能为空!");
            	} else if(result == "2"){
            		alert("type不能为空!");
            	} else if(result == "3"){
            		alert("查无详细信息!");
            	} else {
            		phoneData = eval("("+result+")");
            	}
        	} else {
        		alert("查无详细信息!");
        	}
        },
        error: function() {
            alert("查询的过程中出错!");
        }
    });
	//查询笔记标签
	if(GetQueryString("type") != 4){
		$.ajax({
			type: "POST",
			url: "getVisTag.do",
			async:false,
			data: {
				"id": GetQueryString("id"),
				"type": GetQueryString("type"),
			},
			success: function(result){
				var yourName="#"+result.yourName;
				var youStr="";
				var pid="";
				var activityId="";
				var postId="";
				var userId="";
				var yourId="";
				if(result.pid != undefined){
					pid=result.pid;
				} 
				if(result.activityId != undefined){
					activityId=result.activityId;
				}
				if(result.postId != undefined){
					postId=result.postId;
				}
				if(result.userId != undefined){
					userId=result.userId;
				}
				if(result.yourId != undefined){
					yourId=result.yourId;
				}
				if(result != null){
					//console.log(result);
					if(result.sort == 1){
						youStr = "<a href='{\"type\":\"yourLike\",\"yourId\":\""+yourId+"\",\"yourName\":\""+result.yourName+"\",\"pid\":\""+pid+"\",\"activityId\":\""+activityId+"\",\"sort\":\""+result.sort+"\"}'>\#"+result.yourName+"</a>";
					} else if (result.sort == 2){
						youStr = "<a href='{\"type\":\"yourLike\",\"yourId\":\""+yourId+"\",\"yourName\":\""+result.yourName+"\",\"postId\":\""+postId+"\",\"userId\":\""+userId+"\",\"sort\":\""+result.sort+"\"}'>\#"+result.yourName+"</a>";
					} else if (result.sort == 3){
						youStr = "<a href='{\"type\":\"yourLike\",\"yourId\":\""+yourId+"\",\"yourName\":\""+result.yourName+"\",\"productTypeId\":\""+result.productTypeId+"\",\"sort\":\""+result.sort+"\"}'>\#"+result.yourName+"</a>";
					}
					var btnStr = "<a href='{\"type\":\"age\",\"value\":\""+result.ageType+"\"}'>\#"+result.ageTypeStr+"</a>"+
					"<a href='{\"type\":\"skill\",\"value\":\""+result.skillType+"\"}'>\#"+result.skillTypeStr+"</a>"+
					"<a href='{\"type\":\"product\",\"value\":\""+result.productType+"\"}'>\#"+result.productTypeStr+"</a>"+youStr;
					$("#w-btn").append(btnStr);
				} else {
					console.log("查无标签信息!");
				}
			},
			error: function() {
				console.log("查询的过程中出错!");
			}
		});
	}
	
    preview();      //初始渲染

    var swiper = new Swiper('.view-image .swiper-container', {
        pagination: '.view-image .swiper-container .swiper-pagination',
        paginationClickable: true,
        autoHeight: true
    });
});

/* 
  渲染可视化函数
*/
function preview(){
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
    return data;
}