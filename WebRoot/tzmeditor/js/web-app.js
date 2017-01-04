var phoneData = null;
$(function(){
	phoneData = window.opener.getPreviewData();
    //初始需ajax去请求全部模块的数据
	/*$.ajax({
        type: "POST",
        url: "getPageData.do",
        async:false,
        data: {
            "templatePageDataPojo.id": GetQueryString("jsonData"),
        },
        success: function(result){
        	if(result != null){
        		phoneData = eval("("+result+")");
        	} else {
        		alert("查询不到数据,请先保存再点击预览!");
        	}
        },
        error: function() {
            alert("查询有误!");
        }
    });*/
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