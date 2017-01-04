<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerMartshowItems-9b61bc2a35m.css" type="text/css" media="all" />
    </head>
    <script type="text/javascript">
    function allcb(){
    	var checkbox = document.getElementById("selectcb");
    	var numcb = 0;
    	if(checkbox.checked==true){
    		var code_Values = document.getElementsByName("tids");
    		for(var i = 0;i < code_Values.length;i++){
	    		if(code_Values[i].type == "checkbox")
	    		{
	    			numcb++;
	    			code_Values[i].checked = true;
	    		}
    		}
    	}else{
    		var code_Values = document.getElementsByName("tids");
    		for(var i = 0;i < code_Values.length;i++){
	    		if(code_Values[i].type == "checkbox")
	    		{
	    			code_Values[i].checked = false;
	    		}
    		}
    	}
	    $("#numcb").html(numcb);
    }
    
    function onecb(){
    	var code_Values = document.getElementsByName("tids");
    	var numcb = 0;
		for(var i = 0;i < code_Values.length;i++){
    		if(code_Values[i].type == "checkbox" && code_Values[i].checked == true)
    		{
    			numcb++;
    		}
		}
    	$("#numcb").html(numcb);
    }
	</script>
	<body>
			<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
				<style>
                .ui-popup-stock{visibility:hidden;width:660px;margin-left:-330px;margin-top:-300px;text-align:center;z-index:2412;}.ui-popup-stock .title{font-size:14px;color:#666;line-height:20px;height:30px;}.ui-popup-stock p{text-align:left;line-height:23px;}.ui-popup-stock .batch-operate{text-align:right;border-top:1px solid #f0f0f0;padding:10px 0;margin-top:10px;}.ui-popup-stock .batch-operate input,.ui-popup-stock input{width:68px;height:20px;line-height:20px;border:1px solid #ececec;padding:0 5px;margin:0 5px 0 2px;border-radius:2px;}.ui-popup-stock .batch-operate input:hover,.ui-popup-stock .batch-operate input:active,.ui-popup-stock input:hover,.ui-popup-stock input:active{border:1px solid #ff4965;}.ui-popup-stock .batch-operate button{height:23px;background:#f5f5f5;padding:0 5px;border:1px solid #e6e6e6;color:#666;line-height:23px;width:75px;outline:0;}.ui-popup-stock .batch-operate button:hover{background:#fff;border:1px solid #ff4965;color:#ff4965;}.ui-popup-stock .btn-info{margin-top:10px;}.ui-popup-stock table{width:660px;border:1px solid #ececec;}.ui-table th{background:#f6f6f6;font-weight:normal;color:#999;}.ui-table td{text-align:left;color:#666;}.ui-table tr:nth-child(even),.ui-table-split,.ui-table-hover{background:#fff;}.ui-popup-stock .des{text-align:left;height:30px;line-height:30px;}.ui-popup-stock .des span a{color:#ff4965;display:inline-block;}.ui-popup-stock button{outline:0;}.ui-popup-stock .btn-save{margin-right:10px;}.ui-popup-stock .btn-cancel{border:1px solid #ececec;border-radius:2px;color:#666;background:#fff;}.ui-popup-stock .btn-save:hover{background:#f56c81;}.ui-popup-stock .btn-cancel:hover{color:#ff4965;border:1px solid #ff4965;}.ui-popup-stock th i{margin-left:2px;cursor:pointer;}.oflow-hide{overflow-y:auto;max-height:406px;width:677px;}
                </style>
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <div id="martshow-items">
                            <div class="mart-titlebar view-MartTitlebar">
                                <div class="mart-titlebar-statustag status-1">
                                    ${specialShowPojo.statusName}
                                </div>
                                <h2>
                                   ${specialShowPojo.title}
                                    <div class="martshow-info">
                                        <span class="martshow-info-id">
                                            专场ID:${specialShowPojo.id}
                                        </span>
                                        <!--
                                        <span class="martshow-info-id">
                                            退回原因：
                                            <i>
                                                <span class="iconfont">&#xf0031;</span>
                                                <span class="w-m-tit">
                                                    1
                                                </span>
                                            </i>
                                        </span>
                                        -->
                                    </div>
                                    <a target="_blank" class="mart_show_operate_qq" href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001503677&aty=0&a=0&curl=&ty=1">
                                        <img border="0" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/images/button_111.jpg" alt="点击咨询专员"
                                        title="点击咨询专员">
                                    </a>
                                </h2>
                                <div class="mart-titlebar-menu">
                                	<c:if test="${specialShowPojo.status == 0 ||  specialShowPojo.status== 3}">
                                    <a href="javascript:;" class="ui-button view-BtnShowAddDialog ui-button-swhite">
                                        <i class="iconfont">&#xf0175;</i>
                                        选择商品
                                    </a>
                                    <a href="#" class="ui-button view-BtnSubmitVerify ui-button-sgreen">
                                        <i class="iconfont">&#xf0024;</i>
                                        提交审核
                                    </a>
                                    </c:if>
                                </div>
                            </div>
                            <div class="bannder-container view-BannerContainer">
                                <!--<img src="" />
                                -->
                                <div class="g-side-left m-sbanner">
                                    <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/specialShow/${specialShowPojo.banner}"
                                    alt="${specialShowPojo.name}">
                                </div>
                                <div class="g-side-right">
                                    <div class="m-info">
                                        <div class="logoInfo clearfix">
                                            <div class="logo">
                                                <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/businessCenter/brandDic/${userBrandPojo.logo}"
                                                alt="${userBrandPojo.brandName}">
                                            </div>
                                            <div class="info">
                                                <p>
                                                    专场ID：${specialShowPojo.id}
                                                </p>
                                                <p>
                                                    ${userBrandPojo.brandName}
                                                </p>
                                            </div>
                                        </div>
                                        <div class="m-detail">
                                            <h3>
                                                ${specialShowPojo.name}
                                            </h3>
                                            <div class="promotion">
                                            	<c:if test="${specialShowPojo.discountType != 0}">
                                                <em>
                                                    ${specialShowPojo.discountTypeName}
                                                </em>
                                                <span>
                                                    ${specialShowPojo.discountContext}
                                                </span>
                                                </c:if>                                         
                                                <a class="btn-review" target="_blank" href="goMartShowPreview.do?specialId=${specialShowPojo.id}">
                                                    预览
                                                </a>                                              
                                            </div>
                                            <!-- @@@ -->
                                            <div class="re-address clearfix">
                                                <label>
                                                    退货地址：
                                                </label>
                                                <div>
                                                    ${specialShowPojo.refundAddress}
                                                </div>
                                            </div>
                                            <p>
                                                联系电话：${specialShowPojo.consigneePhone} 18316862042<!-- ，13417126772 -->
                                            </p>
                                            <p>
                                                联系 QQ：${specialShowPojo.QQ} 4001503677<!-- ，1875258447 -->
                                            </p>
                                            <p>
                                                默认快递：圆通，韵达，汇通，天天，快捷快递
                                            </p>
                                            <c:if test="${specialShowPojo.status == 0 ||  specialShowPojo.status == 3}">
	                                            <a href="applyMartShowWeb.do?specialId=${specialId}" class="btn-modify">
	                                                修改
	                                            </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--没有商品时 start-->
                            <div class="no-item">
                            	<c:if test="${specialShowPojo.status == 0 ||  specialShowPojo.status == 3}">
                                <p>
                                    此品牌专场需要添加商品，赶快去选择吧！
                                </p>
                                <a href="javascript:;" class="ui-button view-BtnShowAddDialog ui-button-swhite">
                                    <i class="iconfont">&#xf0175;</i>
                                    选择商品
                                </a>
                                </c:if>
                            </div>
                            <!--没有商品时 end-->
                            <div class="m-s-info">
                            	<!--
                                <ul class="m-s-category clearfix">
                                    <li class="current">
                                        <a href="#">
                                            全部 (20)
                                        </a>
                                    </li>
                                    <li class="">
                                        <a href="#">
                                            待修改 (7)
                                        </a>
                                    </li>
                                </ul>
                                -->
                            </div>
                            <div id="itemlist">
                            </div>
                            <div class="pagination">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		
		
		<div class="miniDialog_mask" style="display: none;"></div>
        <!--选择商品-->
        <div class="miniDialog_wrapper AddDialog" style="width: 900px; height: 520px; margin-left:-450px; margin-top:-250px;">
            <div class="miniDialog_title x-dialog-title">
                选择商品
            </div>
            <div class="miniDialog_content_outer" style="height:auto;">
                <div class="miniDialog_content addDialog-content">
                    <div style="display: block;">
                        <div class="m-c-search">
                        	<form action="#" method="post" accept-charset="utf-8" id="sysform">
                        	<input type="hidden" name="page.pageNo" value=0 id="pageNo">
                            <input class="J_search_input ui_s_input" placeholder="请输入商品型号" type="text" value="" name="productPojo.productNum">
                            &nbsp;
                            <input class="J_search_input ui_s_input" placeholder="请输入商品名称" type="text" value="" name="productPojo.productName">
                            &nbsp;
                            <a href="javascript:;" class="J_search_btn ui-button w-mart-button ui-button-mwhite ui-btn-theme">
                                <i class="iconfont">&#xf012c;</i>
                                搜索
                            </a>
                            </form>
                            <span class="tips">
                                <i class="iconfont">&#xf00b7;</i>
                                商品必须为正常售卖状态，
                                <span class="view-minPrice">
                                    商品的品牌与该专场品牌一致，
                                </span>
                                     商家的店铺通过审核验证
                            </span>
                            <%-- <span class="tips">
                            	<i class="iconfont">&#xf00b7;</i>
                                <span class="view-minPrice">
                            		<font color="red">红色：已添加在进行中活动</font>
                                </span>
                            </span> --%>
                        </div>
                        <form action="addMartShowItems.do" id="proForm"  method="post" >
                        <table class="pure-table ui-table view-goodsList" style="width:99%; margin:2px 0 0 5px">
                            <thead>
                                <tr>
                                    <th width="5%">
                                        <input type="checkbox" class="view-CheckAll" id="selectcb"  onclick="allcb()">
                                    </th>
                                    <th width="10%">
                                        商品序号
                                    </th>
                                    <th width="10%">
                                        商品图片
                                    </th>
                                    <th width="20%">
                                        商品名称
                                    </th>
                                    <!--
                                    <th width="10%">
                                        库存
                                    </th>
                                    -->
                                    <th width="20%">
                                        商品品牌
                                    </th>
                                    <th width="15%">
                                        商品型号
                                    </th>
                                    <th width="10%">
                                        状态
                                    </th>
                                    <th width="10%">
                                        特卖价格
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="body"></tbody>
                        </table>
                        </form>
                    </div>
                </div>
            </div>
            <div class="miniDialog_buttons_area x-dialog-buttonsArea addDialog-buttonsArea">
            	<!--
                <div class="page">
                    <a class="pure-button view-BtnPrevPage">
                        上页
                    </a>
                    <a class="pure-button view-BtnNextPage">
                        下页
                    </a>
                    &nbsp;&nbsp;
                    <span class="now_pageNum">
                        ${page.No}
                    </span>
                    /
                    <span class="total_pageNum">
                        <s:property value="page.rowCount"/>
                    </span>
                </div>
                -->
                <!-- 页码开始 -->
                <div class="page" id="Pagination">
                </div>
                <!-- 页码结束 -->
                <div class="confirm" style="margin: 20px 0px 15px;">
                    已选
                    <span class="J_c_num">
                        <em class="red" id="numcb">0</em>
                    </span>
                    件商品&nbsp;&nbsp;
                    <a class="pure-button button-success view-BtnAddItems">
                        确定
                    </a>
                </div>
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--选择商品-->
        <!--设置限购-->
        <div class="miniDialog_wrapper J_setLimitDialog" style="width: 450px; height: 180px;margin-top:-90px;margin-left:-225px;">
            <div class="miniDialog_title x-dialog-title">
                设置限购
            </div>
            <div class="miniDialog_content_outer confirmDialog-content-outer">
                <div class="miniDialog_content confirmDialog-content">
                    <div style="text-align:center;">
                        <span>限购数量</span>&nbsp;
                        <select name="" id="">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="0">不限购</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="miniDialog_buttons_area x-dialog-buttonsArea confirmDialog-buttonsArea">
                <a class="ui-button ui-button-mred view-BtnConfirm mr-10">
                    确定
                </a>
                <a class="ui-button ui-button-mwhite view-BtnCancelConfirm">
                    取消
                </a>
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--设置限购-->
        <!--设为新品-->
        <div class="miniDialog_wrapper J_setNewDialog" style="width: 450px; height: 180px;margin-top:-90px;margin-left:-225px;">
            <div class="miniDialog_title x-dialog-title">
                设为新品
            </div>
            <div class="miniDialog_content_outer confirmDialog-content-outer">
                <div class="miniDialog_content confirmDialog-content">
                    是否设为新品？
                </div>
            </div>
            <div class="miniDialog_buttons_area x-dialog-buttonsArea confirmDialog-buttonsArea">
                <a class="ui-button ui-button-mred view-BtnConfirm mr-10">
                    确定
                </a>
                <a class="ui-button ui-button-mwhite view-BtnCancelConfirm">
                    取消
                </a>
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--设为新品-->
        <!--批量修改-->
        <div class="J_skuInfoDetail ui-popup ui-popup-stock ui-table">
            <div class="close">
                <span class="J_hideThisTwo">
                    x
                </span>
            </div>
            <div class="title">
                批量修改
            </div>
            <p>
                1. 修改商品库存为淘竹马的统一库存，修改后，所有特卖活动将按新库存进行售卖。
            </p>
            <p>
                2. 若有特卖正在售卖中，更新库存可能导致超卖，请谨慎操作。
            </p>
            <!-- <p>
                3. 特卖价格必须大于等于15元。
            </p> -->
            <div class="batch-operate">
                <span>
                    特卖价格
                </span>
                <input type="text" class="J_batchPrice" value="" onkeyup="value=value.replace(/[^\d.]/g,'')"
                onafterpaste="value=value.replace(/[^\d.]/g,'')" maxlength="10" onchange="testinputdouble(this)" id="inputBatchPrice">
                &nbsp;
                <span>
                    库存
                </span>
                <input type="text" class="J_batchNums" value="" onkeyup="this.value=this.value.replace(/\D/g,'')"
                onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="10" onchange="testinputint(this)" id="inputBatchNums">
                &nbsp;
                <button type="button" class="J_batchFillCoding">
                    批量填充
                </button>
            </div>
            <form action="#" id="batchModifyForm"  method="post" >
            <div class="oflow-hide">
                <table>
                    <thead>
                        <tr>
                            <th>
                                颜色
                            </th>
                            <th>
                                尺寸
                            </th>
                            <th>
                                原价
                            </th>
                            <th>
                                本专场特卖价格
                            </th>
                            <th>
                                库存
                            </th>
                            <th>
                                商家编码
                            </th>
                        </tr>
                    </thead>
                    <tbody id="batchModify">
                    </tbody>
                </table>
            </div>
            </form>
            <div class="btn-info">
                <button class="J_btnSaveStock btn-save" id="savebtn">
                    保存
                </button>
                <button class="J_hideThisTwo btn-cancel">
                    取消
                </button>
            </div>
        </div>
        <!--批量修改-->
        <!--移除商品-->
        <div class="miniDialog_wrapper cont-deleteDialog" style="width: 450px; height: 180px;margin-top:-90px;margin-left:-225px;">
            <div class="miniDialog_title x-dialog-title">
                移除商品
            </div>
            <div class="miniDialog_content_outer confirmDialog-content-outer">
                <div class="miniDialog_content confirmDialog-content">
                    确认移除此商品？
                </div>
            </div>
            <div class="miniDialog_buttons_area x-dialog-buttonsArea confirmDialog-buttonsArea">
                <a class="ui-button ui-button-mred view-BtnConfirm mr-10">
                    确定
                </a>
                <a class="ui-button ui-button-mwhite view-BtnCancelConfirm">
                    取消
                </a>
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--设为新品-->
        <!--提交审核-->
        <div class="miniDialog_wrapper J_upToCheckialog" style="width: 450px; height: 180px;margin-top:-90px;margin-left:-225px;">
            <div class="miniDialog_title x-dialog-title">
                提交审核
            </div>
            <div class="miniDialog_content_outer confirmDialog-content-outer">
                <div class="miniDialog_content confirmDialog-content">
                    确认提交审核？
                </div>
            </div>
            <div class="miniDialog_buttons_area x-dialog-buttonsArea confirmDialog-buttonsArea">
                <a class="ui-button ui-button-mred view-BtnConfirm mr-10">
                    确定
                </a>
                <a class="ui-button ui-button-mwhite view-BtnCancelConfirm">
                    取消
                </a>
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--提交审核-->
	</body>
</html>
<script type="text/javascript">
		$(function(){
	 		initPage();
		});
		//页面初始化
		function initPage(){
			listItems();
	 		
			//退回原因
			<!--
			$(".view-VerifyResult").hover(function(){
				$(this).next().show();
			},function(){
				$(this).next().hide();
			});
			-->
			//添加商品
			$(".view-BtnShowAddDialog").off("click");
			$(".view-BtnShowAddDialog").on("click",function(){
				$(".AddDialog,.miniDialog_mask").show();
				query();
			});
			//确定添加商品
			$(".view-BtnAddItems").off("click");
			$(".view-BtnAddItems").on("click",function(){
				// $(".AddDialog,.miniDialog_mask").hide();
				// $("#proForm").submit();
				addItems();
			});
			
			//设置限购
			$(".J_setLimit").on("click",function(){
				$(".J_setLimitDialog,.miniDialog_mask").show();
			});
			
			//设为新品
			$(".J_setNew").on("click",function(){
				$(".J_setNewDialog,.miniDialog_mask").show();
			});
			
			//批量修改
			$(".J_setBatchModify").on("click",function(){
				$(".J_skuInfoDetail").css("visibility","visible");
				$(".miniDialog_mask").show();
			});
			$(".J_hideThisTwo").on("click",function(){
				//$(this).parents(".ui-popup").hide();
				$(".J_skuInfoDetail").css("visibility","hidden");
				$(".miniDialog_mask").hide();
			});
			$(".J_batchFillCoding").on("click",function(){
				if(i2 == 0 && j2 == 0){
					$(".J_priceKeyDown").val($(".J_batchPrice").val());
					$(".J_numKeyDown").val($(".J_batchNums").val());
				}else{
					alert("您的输入有误！无法批量填充！");
				}
			});
			$(".J_btnSaveStock").off("click");
			$(".J_btnSaveStock").on("click",function(){
				submitBatchMod();
			});
			// 商品查询
			$(".J_search_btn").off("click");
			$(".J_search_btn").on("click",function(){
				query();
			});
			
			//移除商品
			//$(".cont-delete").on("click",function(){
			//	$(".cont-deleteDialog,.miniDialog_mask").show();
			//});
			
			//提交审核
			//$(".J_upToCheck").on("click",function(){
			//	$(".J_upToCheckialog,.miniDialog_mask").show();
			//});
			//专场提交审核
			$(".view-BtnSubmitVerify").off("click");
			$(".view-BtnSubmitVerify").on("click",function(){
				submitMartShow();
			});
		}
		//选择商品提交
		function addItems(){
			var parameter = $("#proForm").serializeArray();
			MAOWU.ajax.get("addMartShowItems.do?specialId=${specialId}", parameter, goRefreshPage);
		}
		
		function goRefreshPage(result){
			var rand=Math.random() * ( 100000 + 1);
			if(result=="1"){
				$(".AddDialog,.miniDialog_mask").hide();
				initPage();
			}else if(result=="2"){
				alert("部分商品重复添加！");
				$(".AddDialog,.miniDialog_mask").hide();
				initPage();
			}else{
				alert("添加失败");
			}
		}
		//SKU展示
		var skuBody = "";
		function listSkus(pid){
			var parameter = "";
			MAOWU.ajax.get("getMartShowItemSkuList.do?specialId=${specialId}&pid="+pid, parameter, listEachSku);
		}
		
		function listEachSku(skuResult){
			var rand=Math.random() * ( 100000 + 1);
			var c = eval("(" + skuResult + ")");
			skuBody = "";
			$.each(c, initSku);
		}
		
		function initSku(){
			skuBody += 
					"<tr data-sku-id='"+this.id+"' class='m-sku-tr'>"+
					"<td class='m-sku-elem'>"+this.colorValue+"</td>"+
					"<td class='m-sku-elem'>"+this.formatValue+"</td>"+
					"<td><span style='color: #e72645;' class='price'>¥"+this.price+"</span><input class='price-input' value='"+this.price+"'></td>"+
					//"<td rowspan='2'>"+
					//"<span style='color: #e72645;'>¥68"+
					//"</span>"+
					//"</td>"+
					"<td><span class='stock'>"+this.stock+"</span><input class='stock-input' value='"+this.stock+"' surplus='"+this.stock+"'></td>"+
					"<td><span class='outerId'>"+this.businessCode+"</span><input class='outerId-input' value='"+this.businessCode+"'></td>"+
					"</tr>";
		}
		//主页面展示
		function listItems(){
			var parameter = "";
			MAOWU.ajax.get("getMartShowItemList.do?specialId=${specialId}", parameter, listEachItem);
		}
		
		function listEachItem(result){
			var rand=Math.random() * ( 100000 + 1);
			var c = eval("(" + result + ")");
			$("#itemlist").html("");
			$.each(c, initItem);
		}
		
		function initItem(){
			listSkus(this.productId);
			var op = "";
			var op2 = "";
			var speStatus='${specialShowPojo.status}';
			var userId='${specialShowPojo.userId}';
			if(this.status==0 && speStatus!=4 && speStatus!=5 || userId==220){
					op2 = "<button type='button' class='pure-button view-BtnDeleteItem cont-delete' onclick='rmProduct("+this.id+")'>移除商品</button>";
					op = "<a href='javascript:;' class='J_setBatchModify pure-button pure-button-pink ' onclick='listBatchModify("+this.productId+")'>批量修改</a>";
				//"<a href='javascript:;' class='J_upToCheck pure-button pure-button-pink' onclick='checkProduct("+this.id+")'>提交审核</a>"+
			}
			$("#itemlist").append(
					"<ul class='item-list view-MartshowItemList'>"+
					"<li class='item view-MartshowItem' apply-id='"+this.specialId+"' product-id='"+this.productId+"' style='height: 230px;'>"+
					"<div class='item-info'>"+
					"<div class='info-title'>"+ this.productName + "</div>"+
					"<div class='info-detail'>"+
					"<div class='J_detailImg detail-img'>"+
					"<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/small/"+this.image+"'></div>"+
					"<ul class='detail-cont' style='word-wrap: break-word;'>"+
					"<li><span class='cont-name'>序号：</span><span>"+ this.productNo + "</span></li>"+
					"<li><span class='cont-name'>货号：</span><span>"+ this.productNum + "</span></li>"+
					"<li><span class='cont-name'>品牌：</span><span>"+ this.brandName+"</span></li>"+
					"<li product-id='"+this.productId+"' class='J_status status-4'>"+
					"<span class='cont-name' style='line-height: 20px;'>状态：</span><span>"+this.statusName+"</span>"+
					//"<a href='javascript:;' class='iconfont verify-reason view-VerifyResult'>&#xf0142;</a>"+
					//"<span class='verify-reason-info view-VerifyResultInfo'>"+
					//"<span class='triangle'>"+
					//"</span>退回理由：68"+
					"</span></li></ul></div></div>"+
					"<table class='opration-group' style='width: 12%;'>"+
					"<thead><tr><th>操作</th></tr></thead>"+
					"<tbody><tr><td>"+
					"<div class='J_allOperate' product-id='"+this.productId+"'>" + op +
					"<c:if test='${specialShowPojo.status == 0 ||  specialShowPojo.status== 3 }'>"+
					
					//"<a href='javascript:;' class='J_setBatchModify pure-button pure-button-pink ' onclick='listBatchModify("+this.productId+")'>批量修改</a>"+
					op2 +
					
					"</c:if></div>"+
					"</td></tr></tbody>"+
					"</table>"+
					"<table class='item-sku' style='width: 60%;'>"+
					"<thead><tr>"+
					"<th style='width: 20%;'>颜色</th>"+
					"<th style='width: 20%;'>尺寸</th>"+
					"<th style='width: 20%;'>本专场特卖价格</th>"+
					//"<th style='width: 20%;'>历史最低价</th>"+
					"<th style='width: 20%;'>剩余库存</th>"+
					"<th style='width: 20%;'>商家编码</th>"+
					"</tr></thead>"+
					"<tbody>"+ skuBody + 
					"<tr data-sku-id='' class='' style='height: 500px; border: 0px;'>"+
					"<td class='m-sku-elem' style='border-top-width: 0px;'></td>"+
					"<td style='border-top-width: 0px;'></td>"+
					"<td style='border-top-width: 0px;'></td>"+
					"<td style='border-top-width: 0px;'></td></tr>"+
					"</tbody></table></li></ul>");
		}
		//库存批量修改显示
		function listBatchModify(pid){
			var parameter = "";
			MAOWU.ajax.get("getMartShowItemSkuList.do?specialId=${specialId}&pid="+pid, parameter, listEachModify);
		}
		
		function listEachModify(skuResult){
			var rand=Math.random() * ( 100000 + 1);
			var c = eval("(" + skuResult + ")");
			$("#batchModify").html("");
			$.each(c, initModify);
		}
		
		function initModify(){
			$("#batchModify").append( 
					"<tr sku-id='"+this.id+"' event-id='"+${specialId}+"' product-id='"+this.productId+"'>"+
					"<input name='tids' type='hidden' value="+this.id +" />"+
					"<td>"+this.colorValue+"</td>"+
					"<td>"+this.formatValue+"</td>"+
					"<td>"+this.sellingPrice+"</td>"+
					"<td><input type='hidden' id='batchPriceTrue"+this.id+"' value='"+this.price+"'>"+
					"<input class='J_priceKeyDown' type='text' id='batchPrice"+this.id+"' name='batchPrice' value='"+this.price+
					"' onkeyup='value=value.replace(/[^\d.]/g,'')' onafterpaste='value=value.replace(/[^\d.]/g,'')' maxlength='10' onchange='testdouble(this,"+this.id+")'></td>"+
					"<td><input type='hidden' id='batchStockTrue"+this.id+"' value='"+this.stock+"'>"+
					"<input class='J_numKeyDown' type='text' id='batchStock"+this.id+"' name='batchStock' value='"+this.stock+
					"' onkeyup='this.value=this.value.replace(/\D/g,'')' onafterpaste='this.value=this.value.replace(/\D/g,'')' maxlength='10' onchange='testint(this,"+this.id+")'></td>"+
					"<td><input type='text' name='batchBusCode' value='"+this.businessCode+"' ></td></tr>");
		}
		//验证输入的值是否有误
		var i = 0,j = 0;
		function testdouble(t,id){
			var batchPriceTrueId = "#batchPriceTrue" + id;
			var batchPriceTrue = $(batchPriceTrueId).val();
			var reg = /^(\d|[1-9]\d+)(\.\d+)?$/;
			if(isNaN(t.value) || t.value <= 0 || !reg.test(t.value)){
				alert("特卖价格必须是大于0的数！");
				var batchPriceId = "#batchPrice" + id;
				$(batchPriceId).val(batchPriceTrue);
				//$("#savebtn").attr("disabled","disabled");
				//i = 1;
			} else {
				//$('#savebtn').removeAttr("disabled");
				//i = 0;
			}
		}
		
		//验证输入的值是否有误
		function testint(t,id){
			var batchStockTrueId = "#batchStockTrue" + id;
			var batchStockTrue = $(batchStockTrueId).val();
			var reg = /^[1-9]\d*$/;
			if(isNaN(t.value) || t.value <= 0 || !reg.test(t.value)){
				alert("库存必须是大于0的整数！");
				var batchStockId = "#batchStock" + id;
				$(batchStockId).val(batchStockTrue);
				//$("#savebtn").attr("disabled","disabled");
				//j = 1;
			} else {
				//$('#savebtn').removeAttr("disabled");
				//j = 0;
			}
		}
		
		//验证输入的值是否有误
		var i2 = 1,j2 = 1;
		function testinputdouble(t){
			var reg = /^(\d|[1-9]\d+)(\.\d+)?$/;
			if(isNaN(t.value) || t.value <= 0 || !reg.test(t.value)){
				alert("特卖价格必须是大于0的数！");
				$("#inputBatchPrice").val("");
				//$("#savebtn").attr("disabled","disabled");
				i2 = 1;
			} else {
				//$('#savebtn').removeAttr("disabled");
				i2 = 0;
			}
		}
		
		//验证输入的值是否有误
		function testinputint(t){
			var reg = /^[1-9]\d*$/;
			if(isNaN(t.value) || t.value <= 0 || !reg.test(t.value)){
				alert("库存必须是大于0的整数！");
				$("#inputBatchNums").val("");
				//$("#savebtn").attr("disabled","disabled");
				j2 = 1;
			} else {
				//$('#savebtn').removeAttr("disabled");
				j2 = 0;
			}
		}
		
		//批量修改提交
		function submitBatchMod(){
			var parameter = $("#batchModifyForm").serializeArray();
			MAOWU.ajax.get("batchModifySku.do?specialId=${specialId}", parameter, goRefreshPage2);
		}
		
		function goRefreshPage2(result){
			if(i == 0 && j == 0){
				var rand=Math.random() * ( 100000 + 1);
				if(result=="1"){
					$(".J_skuInfoDetail").css("visibility","hidden");
					$(".miniDialog_mask").hide();
					initPage();
				}else{
					alert("修改失败");
				}
			} else {
				alert("您的输入有误！无法保存！");
			}
		}
		//移除商品
		function rmProduct(pid){
			$(".cont-deleteDialog .view-BtnConfirm").off("click");
			$(".cont-deleteDialog,.miniDialog_mask").show();
			$(".cont-deleteDialog .view-BtnConfirm").on("click",function(){
				removeProduct(pid);
			});
		}
		function removeProduct(pid){
			var parameter = "";
			MAOWU.ajax.get("delMartShowProduct.do?specialId=${specialId}&pid="+pid, parameter, goRefreshPage3);
		}
		
		function goRefreshPage3(result){
			var rand=Math.random() * ( 100000 + 1);
			if(result=="1"){
				$(".cont-deleteDialog,.miniDialog_mask").hide();
				initPage();
			}else{
				$(".cont-deleteDialog,.miniDialog_mask").hide();
				alert("删除失败");
			}
		}
		//商品提交审核
		function checkProduct(pid){
			$(".J_upToCheckialog .view-BtnConfirm").off("click");
			$(".J_upToCheckialog,.miniDialog_mask").show();
			$(".J_upToCheckialog .view-BtnConfirm").on("click",function(){
				alert(111);
			});
		}
		function submitProduct(pid){
			var parameter = "";
			MAOWU.ajax.get("delMartShowProduct.do?specialId=${specialId}&pid="+pid, parameter, goRefreshPage4);
		}
		
		function goRefreshPage4(result){
			if(result=="1"){
				$(".J_upToCheckialog,.miniDialog_mask").hide();
				initPage();
			}else{
				$(".J_upToCheckialog,.miniDialog_mask").hide();
				alert("专场需要先添加商品再提交审核！");
			}
		}
		
		//专场提交审核
		function submitMartShow(){
			var parameter = "";
			MAOWU.ajax.get("submitMartShow.do?specialId=${specialId}", parameter, goRefreshPage5);
		}
		
		function goRefreshPage5(result){
			var rand=Math.random() * ( 100000 + 1);
			if(result=="1"){
				window.location.href='getMartShowWeb.do';
			}else{
				alert("提交审核失败,请添加商品!");
			}
		}
		
		//选择商品
		var ctx  ="<s:property value="ctx" />";
		var pagecount = "${page.rowCount}"; 
		function query() {
			if(tt.validate()){
				var rand=Math.random() * ( 100000 + 1);
				queryData("getUserBrandProductsCount.do?specialId=${specialId}", "getUserBrandProducts.do?specialId=${specialId}&randquery="+rand,10);
			}
		}
	
		/**
		 *分页展现页面函数 
		 **/
		function installPage() {
			var isRedB = "";
			var isRedE = "";
			if(this.isAdd == true){
				isRedB = "<font color='red'>";
				isRedE = "</font>";
			}
			
			$("#body").append(
					"<tr template-display='' style='display: table-row;'>"+
                    "<td class='view-priceok'><input type='checkbox' name='tids' value='"+this.id+"' onclick='onecb()'></td>"+
                    "<td>"+isRedB+""+this.productNo+""+isRedE+"</td>"+
                    "<td><img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/small/"+this.image+"' style='width:60px; height:60px'></td>"+
                    "<td>"+isRedB+"" + this.productName + ""+isRedE+"</td>" +
                    "<td>"+isRedB+"" + this.brandName + ""+isRedE+"</td>" +
                    "<td>"+isRedB+"" + this.productNum + ""+isRedE+"</td>" +
                    "<td>"+isRedB+"" + this.statusName + ""+isRedE+"</td>" +
                    "<td class='price'>"+isRedB+"" + this.distributionPrice + ""+isRedE+"</td></tr>");
		}
</script>