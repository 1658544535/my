var idx = 0;//按钮索引
var stock_price_data = new Object;
var del_obj = new Object;//删除记录表
var sku_attr = new Object;//sku记录表
var init_sku = new Object;//初始化表
window.onload=function(){
//选择规格
$(document).delegate(".gg2","click", function(){
	$("#gs").removeClass("display-none");
	$("#gsId").removeClass("display-none");
	$("#color").empty();
	$("#memory").empty();
	$("#parameter_info_tb").empty();
	stock_price_data = new Object;
	del_obj = new Object;
	sku_attr = new Object;
	$("#stock").text(0);
});
$(document).delegate(".gg1","click", function(){
	$("#gs").addClass("display-none");
	$("#gsId").addClass("display-none");
	$("#color").empty();
	$("#memory").empty();
	$("#parameter_info_tb").empty();
	stock_price_data = new Object;
	del_obj = new Object;
	sku_attr = new Object;
	$("#stock").text(0);
});
//修改按钮样式
$(".ggBtn").click(function(){
	if($(this).children().text() == "一种规格"){
		$("#norm").val(1);
		$(".gsdisplay").css('display','none');
	} else if($(this).children().text() == "二种规格"){
		$("#norm").val(2);
		$(".gsdisplay").css('display','');
	}
	$(".ggBtn").removeClass("mybtn-hover");
	$(this).addClass("mybtn-hover");
});
//关闭属性添加按钮div
$(".addSX-main .addSX-close").click(function(){
	$(this).parents(".addSX-main").hide();
});
//关闭格式添加按钮div
$(".addGS-main .addGS-close").click(function(){
	$(this).parents(".addGS-main").hide();
});

//添加属性按钮
$(".addSX .add").click(function(){
	$(this).next().show();
});
$(".addSX-main .addSX-sure").click(function(){
	//添加按钮
	var val = $(this).prev().val();
	if(val == null || val == 'undefined' || val == ''){
		return;
	}
    //var dicid = $('select[name="sxType"]').val();
	var dicid = '1';
	if(val != ""){
		idx--;
		var html = '<div class="parameter_color sxBtn" attValue="'+idx+'" attName="brandcolor" rcdid="0" dicid="'+dicid+'" datatext="'+val+'"><a class="delDiv"></a>'+val+'</div>';
		$(this).parents(".addSX").prev("#color").append(html);
		$(this).prev().val('');
		$(this).parents(".addSX-main").hide();
	}
	//遍历sku表格
	var norm = $("#norm").val();
	var str = '';
	//var proVal = idx;
	//var rcdid = '0';
	var datatxt = val;
	if(typeof(sku_attr[dicid])=='undefined'){
		var arr = new Array();
		var obj = new Object();
		obj[idx] = datatxt;
		arr[0] = obj;
		sku_attr[dicid] = arr;
	} else {
		var len = sku_attr[dicid].length;
		var obj = new Object();
		obj[idx] = datatxt;
		sku_attr[dicid][len] = obj;
	}
	console.log(JSON.stringify(sku_attr));
	if(norm == 1){
		//规格1
		$(".parameter_color").each(function(){
			var par_pc = $(this);
			var rcdid = $(this).attr("rcdid");
	        var key_index = par_pc.attr('attValue')+'_0';
	        var value_stock = '';
	        var value_businessCodes = '';
	        var value_skuImage = '';
	        var value_skuStatuss = '1';
	        if (typeof(stock_price_data[key_index]) != "undefined") {
	            value_stock = stock_price_data[key_index]['stock'];
	            value_businessCodes = stock_price_data[key_index]['businessCodes'];
	            value_skuImage = stock_price_data[key_index]['skuImage'];
	            value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
	        } else {
	            var obj = new Object;
	            obj['stock'] = '';
	            obj['color'] = par_pc.attr('datatext');
                obj['format'] = '0';
                obj['businessCodes'] = '';
                obj['skuImage'] = '';
                obj['skuStatuss'] = '1';
                obj['rcdid'] = '0';
	            stock_price_data[key_index] = obj;
	        }
	        var statusStr = '';
	        if(value_skuStatuss == "1"){
	        	statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
	        } else {
	        	statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
	        }
	        var imgStr = '';
	        if(rcdid > 0){
	        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>';   
	        } else {
	        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"style="display:none;"><img src="" style="width:50px;height:50px;"/></div>'; 
	        }
	        str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
		           //'<td>0</td>'+
		           '<td><input type="text" class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
		           '<td><input type="text" class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
		           '<td>'+imgStr+'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
		           '<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
		});
		total_stock();
        $('#parameter_info_tb').html(str);
	} else {
		//规格2
	    $('.parameter_color').each(function () {
	        var par_pc = $(this);
	        $('.parameter_memory').each(function () {
	            var par_pm = $(this);
	            var key_index = par_pc.attr('attValue')+'_'+par_pm.attr('attValue');
	            var value_stock = '';
	            var value_businessCodes = '';
		        var value_skuImage = '';
		        var value_skuStatuss = '1';
		        var value_rcdid = '0';
	            if (typeof(stock_price_data[key_index]) != "undefined") {
	                value_stock = stock_price_data[key_index]['stock'];
	                value_businessCodes = stock_price_data[key_index]['businessCodes'];
	                value_skuImage = stock_price_data[key_index]['skuImage'];
		            value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
		            value_rcdid = stock_price_data[key_index]['rcdid'];
	            } else {
					var obj = new Object;
					obj['stock'] = '';
					obj['color'] = par_pc.attr('datatext');
					obj['format'] = par_pm.attr('datatext');
					obj['businessCodes'] = '';
	                obj['skuImage'] = '';
	                obj['skuStatuss'] = '1';
					obj['rcdid'] = '0';
					stock_price_data[key_index] = obj;
				}
	            var statusStr = '';
		        if(value_skuStatuss == "1"){
		        	statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
		        } else {
		        	statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
		        }
		        var imgStr = '';
		        if(value_rcdid != '0'){
		        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>';   
		        } else {
		        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"style="display:none;"><img src="" style="width:50px;height:50px;"/></div>'; 
		        }
		        str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
		           '<td>'+par_pm.attr('datatext')+'</td>'+
		           '<td><input class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
		           '<td><input class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
		           '<td>'+imgStr+'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
		           '<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
	        });
	    }); 
	    total_stock();
	    $('#parameter_info_tb').html(str);
	}
});

//添加格式按钮
$(".addGS .add").click(function(){
	$(this).next().show();
});
$(".addGS-main .addGS-sure").click(function(){
	//添加按钮
	var val = $(this).prev().val();
	if(val == null || val == 'undefined' || val == ''){
		return;
	}
	//var dicid = $('select[name="gsType"]').val();
	var dicid = '2';
	if(val != ""){
		idx--;
		var html = '<div class="parameter_memory sxBtn" attValue="'+idx+'" id="memory'+idx+'" attName="brandmemory" rcdid="0" dicid="'+dicid+'" datatext="'+val+'"><a class="delDiv"></a>'+val+'</div>';
		$(this).parents(".addGS").prev("#memory").append(html);
		$(this).prev().val('');
		$(this).parents(".addGS-main").hide();
	}
	//遍历sku表格
	var str = '';
	//var proVal = idx;
	//var rcdid = '0';
	//var dicid = '2';
	var datatxt = val;
	if(typeof(sku_attr[dicid])=='undefined'){
		var arr = new Array();
		var obj = new Object();
		obj[idx] = datatxt;
		arr[0] = obj;
		sku_attr[dicid] = arr;
	} else {
		var len = sku_attr[dicid].length;
		var obj = new Object();
		obj[idx] = datatxt;
		sku_attr[dicid][len] = obj;
	}
	console.log(JSON.stringify(sku_attr));
    $('.parameter_color').each(function () {
        var par_pc = $(this);
        $('.parameter_memory').each(function () {
            var par_pm = $(this);
            var key_index = par_pc.attr('attValue')+'_'+par_pm.attr('attValue');
            var value_stock = '';
            var value_businessCodes = '';
	        var value_skuImage = '';
	        var value_skuStatuss = '1';
	        var value_rcdid = '0';
            if (typeof(stock_price_data[key_index]) != "undefined") {
                value_stock = stock_price_data[key_index]['stock'];
                value_businessCodes = stock_price_data[key_index]['businessCodes'];
                value_skuImage = stock_price_data[key_index]['skuImage'];
	            value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
	            value_rcdid = stock_price_data[key_index]['rcdid'];
            } else {
				var obj = new Object;
				obj['stock'] = '';
				obj['color'] = par_pc.attr('datatext');
				obj['format'] = par_pm.attr('datatext');
				obj['businessCodes'] = '';
                obj['skuImage'] = '';
                obj['skuStatuss'] = '1';
				obj['rcdid'] = '0';
				stock_price_data[key_index] = obj;
			}
            var statusStr = '';
	        if(value_skuStatuss == "1"){
	        	statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
	        } else {
	        	statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
	        }
	        var imgStr = '';
	        if(value_rcdid != '0'){
	        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>';   
	        } else {
	        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"style="display:none;"><img src="" style="width:50px;height:50px;"/></div>'; 
	        }
	        str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
	           '<td>'+par_pm.attr('datatext')+'</td>'+
	           '<td><input class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
	           '<td><input class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
	           '<td>'+imgStr+'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
	           '<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
        });
    }); 
    total_stock();
    $('#parameter_info_tb').html(str);
});


//删除属性按钮
$(document).on("click", ".delDiv", function(){
	var norm = $("#norm").val();
	var proNm = $(this).parent().attr('attName');
	var rcdid = $(this).parent().attr('rcdid');
	var proVal = $(this).parent().attr('attValue');
	var dicid = $(this).parent().attr('dicid');
	var datatxt = $(this).parent().attr('datatext');
	if(rcdid == '0') {
		sku_attr[dicid].splice($.inArray(datatxt, sku_attr[dicid]), 1);
	}
	if(norm == 1){
		recordDelete('attr',rcdid);
		var _idx = proVal+'_0';
		recordDelete('sku',stock_price_data[_idx]['rcdid']);
		delete stock_price_data[_idx];
	} else {
		if(proNm == 'brandcolor'){
			recordDelete('attr',rcdid);
			$('.parameter_memory').each(function () {
				var _idx = proVal+'_'+$(this).attr("attValue");
				recordDelete('sku',stock_price_data[_idx]['rcdid']);
				delete stock_price_data[_idx];
			});
		} else if(proNm == 'brandmemory'){
			recordDelete('attr',rcdid);
			$('.parameter_color').each(function () {
				var _idx = $(this).attr("attValue")+'_'+proVal;
				recordDelete('sku',stock_price_data[_idx]['rcdid']);
				delete stock_price_data[_idx];
			});
		}
	}
	$(this).parent().remove();
	var str = '';
	$('.parameter_color').each(function () {
		var par_pc = $(this);
		if(norm == 1){
	        var key_index = par_pc.attr('attValue')+'_0';
	        var value_stock = '';
	        var value_businessCodes = '';
	        var value_skuImage = '';
	        var value_skuStatuss = '1';
	        var value_rcdid = par_pc.attr('attValue');
	        if (typeof(stock_price_data[key_index]) != "undefined") {
	            value_stock = stock_price_data[key_index]['stock'];
	            value_businessCodes = stock_price_data[key_index]['businessCodes'];
	            value_skuImage = stock_price_data[key_index]['skuImage'];
	            value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
	            value_rcdid = stock_price_data[key_index]['rcdid'];
	        } else {
	            var obj = new Object;
	            obj['stock'] = '';
	            obj['color'] = par_pc.attr('datatext');
	            obj['format'] = '0';
	            obj['businessCodes'] = '';
                obj['skuImage'] = '';
                obj['skuStatuss'] = '1';
	            obj['rcdid'] = '0';
	            stock_price_data[key_index] = obj;
	        }
	        var statusStr = '';
	        if(value_skuStatuss == "1"){
	        	statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
	        } else {
	        	statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
	        }
	        var imgStr = '';
	        if(value_rcdid != '0'){
	        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>';   
	        } else {
	        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"style="display:none;"><img src="" style="width:50px;height:50px;"/></div>'; 
	        }
	        str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
	           //'<td>0</td>'+
	           '<td><input class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
	           '<td><input class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
	           '<td>'+imgStr+'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
	           '<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
		} else {
	        $('.parameter_memory').each(function () {
	            var par_pm = $(this);
	            var key_index = par_pc.attr('attValue')+'_'+par_pm.attr('attValue');
	            var value_stock = '';
	            var value_businessCodes = '';
		        var value_skuImage = '';
		        var value_skuStatuss = '1';
		        var value_rcdid = '0';
	            if (typeof(stock_price_data[key_index]) != "undefined") {
	                value_stock = stock_price_data[key_index]['stock'];
	                value_businessCodes = stock_price_data[key_index]['businessCodes'];
	                value_skuImage = stock_price_data[key_index]['skuImage'];
		            value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
		            value_rcdid = stock_price_data[key_index]['rcdid'];
	            } else {
					var obj = new Object;
					obj['stock'] = '';
					obj['color'] = par_pc.attr('datatext');
					obj['format'] = par_pm.attr('datatext');
					obj['businessCodes'] = '';
	                obj['skuImage'] = '';
	                obj['skuStatuss'] = '1';
					obj['rcdid'] = '0';
					stock_price_data[key_index] = obj;
				}
	            var statusStr = '';
		        if(value_skuStatuss == "1"){
		        	statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
		        } else {
		        	statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
		        }
		        var imgStr = '';
		        if(value_rcdid != '0'){
		        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>';   
		        } else {
		        	imgStr='<div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn"><i class="iconfont">&#xf00f7;</i></button><div class="uploadPreview_img"style="display:none;"><img src="" style="width:50px;height:50px;"/></div>'; 
		        }
		        str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
		           '<td>'+par_pm.attr('datatext')+'</td>'+
		           '<td><input class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
		           '<td><input class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
		           '<td>'+imgStr+'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
		           '<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
	        });
		}
    }); 
    total_stock();
	$('#parameter_info_tb').html(str);
});
//价钱 库存失去焦点
$(document).delegate('.stock_price_blur', 'change', function() { 
	var stock_price_attr = $(this).attr('data_attr');
	var stock_price_value = $(this).val();
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test(stock_price_value) && stock_price_attr == "stock"){
		$(this).val("0");
		stock_price_value = "0";
		alert("库存请输入正整数!");
	}
    if(stock_price_attr == 'skuImage'){
    	var _this = $(this);
		var url = _this.val();
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(_this.get(0).files[0]);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(_this.get(0).files[0]);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
		}
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
    }
    
    if (stock_price_value == ''){
        return false;
    }
    var dataid = $(this).attr('dataid');
    if (typeof(stock_price_data[dataid]) == "undefined"){
        var obj = new Object;	
        if (stock_price_attr == 'stock'){
            total_stock();
            obj['stock'] = stock_price_value;
			obj['color'] = '';
			obj['format'] = '';
			obj['businessCodes'] = '';
            obj['skuImage'] = '';
            obj['skuStatuss'] = '0';
			obj['rcdid'] = '';
        } else if (stock_price_attr == 'businessCodes'){
            obj['stock'] = '';
			obj['color'] = '';
			obj['format'] = '';
			obj['businessCodes'] = stock_price_value;
            obj['skuImage'] = '';
            obj['skuStatuss'] = '0';
			obj['rcdid'] = '';
        } else if (stock_price_attr == 'skuImage'){
            obj['stock'] = '';
			obj['color'] = '';
			obj['format'] = '';
			obj['businessCodes'] = '';
            obj['skuImage'] = stock_price_value;
            obj['skuStatuss'] = '0';
			obj['rcdid'] = '';
        } else if (stock_price_attr == 'skuStatuss'){
            obj['stock'] = '';
			obj['color'] = '';
			obj['format'] = '';
			obj['businessCodes'] = '';
            obj['skuImage'] = '';
            obj['skuStatuss'] = stock_price_value;
			obj['rcdid'] = '';
        }
        stock_price_data[dataid] = obj;
    } else {
        if (stock_price_attr == 'price'){
            stock_price_data[dataid]['price'] = stock_price_value;
        } else if (stock_price_attr == 'stock'){
            total_stock();
            stock_price_data[dataid]['stock'] = stock_price_value;
        } else if (stock_price_attr == 'businessCodes'){
            stock_price_data[dataid]['businessCodes'] = stock_price_value;
        } else if (stock_price_attr == 'skuImage'){
        	var skuId = $(this).attr('id');
    		$.ajaxFileUpload({
    	        url: 'upProductSkuImage.do',//处理上传用的后台程序,可以是PHP,也可以是ASP等
    	        secureuri: false,//异步
    	        fileElementId: skuId,//上传控件ID
    	        dataType: 'json',
    	        success: function(result) {
    	        	if(result == '0' || result == ''){
    	        		alert("上传失败,请重新上传!");
    	        	} else if (result != '') {
    	            	stock_price_data[dataid]['skuImage'] = result;
    	            }
    	        },
    	        error: function(result) {
    	        	alert("上传失败,请重新上传!");
    	        }
    	    });
        } else if (stock_price_attr == 'skuStatuss'){
            stock_price_data[dataid]['skuStatuss'] = stock_price_value;
        }
    }
    console.log('skulist:'+JSON.stringify(stock_price_data));
    return true;
});

//计算总库存
function total_stock(){
    var stockno = 0;
    var norm = $("#norm").val();
    if(norm == 1){
    	$('.parameter_color').each(function () {
    		var par_pc = $(this);
			var pre = par_pc.attr('attValue')+'_0';
			var stock = parseInt($('#stock_'+pre).val());
			if (isNaN(stock)){
				stock = 0;
			}
			stockno = stockno + stock; 
    	});
    } else {
    	$('.parameter_color').each(function () {
    		var par_pc = $(this);
    		$('.parameter_memory').each(function () {
    			var par_pm = $(this);
    			var pre = par_pc.attr('attValue')+'_'+par_pm.attr('attValue');
    			var stock = parseInt($('#stock_'+pre).val());
    			if (isNaN(stock)){
    				stock = 0;
    			}
    			stockno = stockno + stock; 
    		});
    	});
    }
    $('#stock').text(stockno);
}
//删除sku记录表
function recordDelete(type,rcdid){
	if(rcdid > 0){
		if (typeof(del_obj[type]) == "undefined"){
			var arr = new Array();
			arr[0] = rcdid;
			del_obj[type] = arr;
		} else {
			var del_len = del_obj[type].length;
			del_obj[type][del_len] = rcdid;
		}
	}
	console.log('delsku:'+JSON.stringify(del_obj));
}

//---------------------------------------------sku初始化 start-----------------------------------------------------
$(document).ready(function(){
	if(normType == 2){
		$(".gg2").click();
	} else {
		$(".gg1").click();
	}
	if(normType != null && normType != ""){
		//初始化属性按钮
		$.ajax({
			url: "getAttrSxBtn.do", 
			async: false,
			data: {"id":productId,"sxType":colorValue},
			success: function(data){
				sxBtn = eval("("+data+")");
				var html = "";
				var dicid = "1";
				for ( var i = 0; i < sxBtn.length; i++) {
					html += '<div class="parameter_color sxBtn" attValue="'+sxBtn[i]["sxId"]+'" attName="brandcolor" rcdid="'+sxBtn[i]["sxId"]+'" dicid="'+sxBtn[i]["sxId"]+'" datatext="'+sxBtn[i]["sxName"]+'"><a class="delDiv"></a>'+sxBtn[i]["sxName"]+'</div>';
					if(typeof(sku_attr[dicid])=='undefined'){
						var arr = new Array();
						var obj = new Object();
						obj[sxBtn[i]["sxId"]] = sxBtn[i]["sxName"];
						arr[0] = obj;
						sku_attr[dicid] = arr;
					} else {
						var len = sku_attr[dicid].length;
						var obj = new Object();
						obj[sxBtn[i]["sxId"]] = sxBtn[i]["sxName"];
						sku_attr[dicid][len] = obj;
					}
				}
				$("#color").append(html);
			}
		});
		//初始化格式按钮
		$.ajax({
			url: "getAttrGsBtn.do", 
			async:false,
			data: {"id":productId,"gsType":formatValue},
			success: function(data){
				gsBtn = eval("("+data+")");
				var html = "";
				var dicid = "2";
				for ( var i = 0; i < gsBtn.length; i++) {
					html += '<div class="parameter_memory sxBtn" attValue="'+gsBtn[i]["gsId"]+'" id="memory'+gsBtn[i]["gsId"]+'" attName="brandmemory" rcdid="'+gsBtn[i]["gsId"]+'" dicid="'+gsBtn[i]["gsId"]+'" datatext="'+gsBtn[i]["gsName"]+'"><a class="delDiv"></a>'+gsBtn[i]["gsName"]+'</div>';
					if(typeof(sku_attr[dicid])=='undefined'){
						var arr = new Array();
						var obj = new Object();
						obj[gsBtn[i]["gsId"]] = gsBtn[i]["gsName"];
						arr[0] = obj;
						sku_attr[dicid] = arr;
					} else {
						var len = sku_attr[dicid].length;
						var obj = new Object();
						obj[gsBtn[i]["gsId"]] = gsBtn[i]["gsName"];
						sku_attr[dicid][len] = obj;
					}
				}
				$("#memory").append(html);
			}
		});
		//初始化sku列表
		stock_price_data = skuListData[0];
		//console.log(skuListData[0]);
		init_skulist();
	}
});
function init_skulist() {
	var str = ''; 
	if(normType == 1){
		$('.parameter_color').each(function () {
			var par_pc = $(this);
			var key_index = par_pc.attr('attValue')+'_0';
			var value_stock = '';
			var value_businessCodes = '';
			var value_skuImage = '';
			var value_skuStatuss = '1';
			if (typeof(stock_price_data[key_index]) != "undefined") {
				value_stock = stock_price_data[key_index]['stock'];
				value_businessCodes = stock_price_data[key_index]['businessCodes'];
				value_skuImage = stock_price_data[key_index]['skuImage'];
				value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
			} else {
				var obj = new Object;
				obj['stock'] = "";
				obj['color'] = par_pc.attr('datatext');
				obj['format'] = '0';
				obj['businessCodes'] = '';
				obj['skuImage'] = '';
				obj['skuStatuss'] = '1';
				obj['rcdid'] = "0";
				stock_price_data[key_index] = obj;
			}
			var statusStr = '';
			if(value_skuStatuss == "1"){
				statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
			} else {
				statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
			}
			str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
			//'<td>0</td>'+
			'<td><input class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
			'<td><input class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
			'<td><div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button>'+
			'<div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>'+
			'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
			'<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
		});
	} else {
		$('.parameter_color').each(function () {
			var par_pc = $(this);
			$('.parameter_memory').each(function () {
				var par_pm = $(this);
				var key_index = par_pc.attr('attValue')+'_'+par_pm.attr('attValue');
				var value_stock = '';
				var value_businessCodes = '';
				var value_skuImage = '';
				var value_skuStatuss = '1';
				if (typeof(stock_price_data[key_index]) != "undefined") {
					value_stock = stock_price_data[key_index]['stock'];
					value_businessCodes = stock_price_data[key_index]['businessCodes'];
					value_skuImage = stock_price_data[key_index]['skuImage'];
					value_skuStatuss = stock_price_data[key_index]['skuStatuss'];
				} else {
					var obj = new Object;
					obj['stock'] = "";
					obj['color'] = par_pc.attr('datatext');
					obj['format'] = par_pm.attr('datatext');
					obj['businessCodes'] = '';
					obj['skuImage'] = '';
					obj['skuStatuss'] = '1';
					obj['rcdid'] = "0";
					stock_price_data[key_index] = obj;
				}
				var statusStr = '';
				if(value_skuStatuss == "1"){
					statusStr='<option value="1" selected="selected">上架</option><option value="0">下架</option></select>';   
				} else {
					statusStr='<option value="1">上架</option><option value="0" selected="selected">下架</option></select>';
				}
				str += '<tr><td>'+par_pc.attr('datatext')+'</td>'+
				'<td>'+par_pm.attr('datatext')+'</td>'+
				'<td><input class="form-control stock_price_blur" data_attr="businessCodes" dataid="'+key_index+'" id="businessCodes_'+key_index+'" value="'+value_businessCodes+'"/></td>'+
				'<td><input class="form-control stock_price_blur" data_attr="stock" dataid="'+key_index+'" id="stock_'+key_index+'" value="'+value_stock+'"/></td>'+
				'<td><div class="uploadify main_img mainImg"><button class="uploadPreview_note skuImgBtn" style="display:none;"><i class="iconfont">&#xf00f7;</i></button>'+
				'<div class="uploadPreview_img"><img src="'+imageUrl+value_skuImage+'" style="width:50px;height:50px;"/></div>'+
				'<input type="file" class="stock_price_blur skuImage" name="skuImage" data_attr="skuImage" dataid="'+key_index+'" id="skuImage_'+key_index+'" value="'+value_skuImage+'" accept="image/png,image/gif,image/jpg,image/jpeg"/></br></div></td>'+
				'<td><select class="stock_price_blur" data_attr="skuStatuss" name="skuStatuss" dataid="'+key_index+'" id="skuStatuss_'+key_index+'" value="'+value_skuStatuss+'">'+statusStr+'</td></tr>';
			});
		}); 
	}
	$('#parameter_info_tb').html(str);
	total_stock();
}
//---------------------------------------------sku初始化 end-----------------------------------------------------
};

//提交
function checkSkuJoin() {
	console.log('skulist:'+JSON.stringify(stock_price_data));
    console.log('sku:'+JSON.stringify(sku_attr));
}
