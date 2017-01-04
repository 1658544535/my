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
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerMartshowApply-acac2a4210m.css" type="text/css" media="all" />
    </head>
    <script type="text/javascript">
		$(function(){
			select3();
			$('#mainCategory1').bind("change", select2);
			var category1 = $('#mainCategory1').val();
			if(category1 != ""){
				select2();
			}
		});
		function select2() {
			var category = "${specialShowPojo.mainCategory2}";
            $("#mainCategory2").html("<option value=''>请选择二级分类</option>");
            if($('#mainCategory1').val() == ''){
            	return;
            }
            $.ajax(
            {
                type: "post",
                url: "getProductTypeByPid.do?level=1&pid="+$('#mainCategory1').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selStr = "";
                    	if(o_msg[i].id == category){
                    		selStr = " selected='selected' ";
                    	}
                        $("#mainCategory2").append("<option value=" + o_msg[i].id + selStr +">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
        function select3() {
   		    $("#channelId").append("");
            $.ajax(
            {
                type: "post",
                url: "findChildrenChannelList.do?childrenChannelPojo.status=1",
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        var selectedStr = "";
                    	if("${specialShowPojo.channelId}".indexOf(':'+ o_msg[i].id+':') != -1){
                    		selectedStr = "checked='true'";
                    	}
                        $("#channelId").append("<label><input type='checkbox' name='channelIds' value=" + o_msg[i].id + " "+selectedStr+"/>" + o_msg[i].name + "</label>&nbsp;&nbsp;");
                    }
                }
            })
        };
	</script>
	<body>
			<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .red-packet-tooltip i{ color: #C2C220; margin-right: 10px; padding-left:2px; } .red-packet-tooltip a:hover { text-decoration: none; } .more-packet-info a{ padding: 5px 0px 0 4px; }.upload-img{position:relative;height: 30px; line-height: 30px; width: 120px;background:#f64f61;border-radius:30px;text-align:center;color:#fff;}.upload-img input{position:absolute;top:0;left:0;width:100%;height:100%;opacity:0;filter:alpha(opacity=0);cursor:pointer;}.upload-img-box{margin-top:10px;}.upload-img-box img{display:block;height:100%;width:auto;}
                        </style>
                        <h1 class="seller-title">
                            专场管理
                        </h1>
                       <!--  <div class="order-import-box view-OrderImportBox">
                            <div class="ui-tipbox ui-tipbox-wait">
                                <div class="ui-tipbox-icon">
                                    <i class="iconfont" title="提示">&#xf00b6;</i>
                                </div>
                                <div class="ui-tipbox-content">
                                    <table class="tip">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <p>
                                                        为规范商家运营行为，提升运营质量，即日起，创建品牌特卖专场将进行包括但不仅限于以下限制：
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <p class="red">
                                                        1. 连续两个专场销售额均低于10000元的品牌不可创建专场；
                                                    </p>
                                                    <p class="red">
                                                        2. 品牌综合评分低于4.2分的品牌不可创建专场；
                                                    </p>
                                                    <p class="red">
                                                        3. 超过90天未运营的品牌不可创建专场；
                                                    </p>
                                                    <p class="red">
                                                        4. 品控质检超过2次不合格的品牌不可创建专场；
                                                    </p>
                                                    
                                                    <p>
                                                        更多内容，请查看
                                                        <a href="#" target="_blank">
                                                            《淘汰规则》
                                                        </a>
                                                    </p>
                                                   
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div> -->
                        <form action="submitMartShowWeb.do" accept-charset="utf-8" class="ui-form mt-20 view-MartShowForm clone-MartShowForm" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="specialShowPojo.id" value="${specialShowPojo.id}" id="specialShowPojo.id">
                            <div class="apply-box">
                                <h3 class="litter-title">
                                    专场信息
                                </h3>
                                <fieldset class="p50 seller-martshow-apply">
                                    <div class="ui-form-item brand ps-re">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                            专场品牌
                                        </label>
                                        <select name="specialShowPojo.userBrandId" class="ui-input" <s:if test="specialShowPojo.userBrandId != null">disabled="disabled"</s:if>>
                                            <s:iterator value="userBrandLt">
												<option value="<s:property value="id"/>" <s:if test="id == specialShowPojo.userBrandId">selected="selected"</s:if>>
													<s:property value="brandName" />
												</option>
											</s:iterator>
                                        </select>
                                        <span id="userbrand_tip" class="" />
                                        <p class="brand-expired-tip">
                                        </p>
                                        <p class="ui-form-explain">
                                            列表中只列出已审核通过且质检通过的品牌，
                                            <a href="getBrandListWeb.do" target="_blank">
                                                添加品牌
                                            </a>
                                        </p>
                                        <span class="ui-form-other">
                                        </span>
                                    </div>
                                    <div class="ui-form-item m-check-title" style="position: relative;">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                            专场标题
                                        </label>
                                        <input name="specialShowPojo.title" class="ui-input" style="width: 400px;" type="text" value="${specialShowPojo.title}">
                                        <span id="title_tip" class="" />
                                        <span class="ui-form-other">
                                        </span>
                                        <p class="ui-form-explain">
                                            1. 标题格式：品牌名称+品类+促销用语（选填）+（特卖）专场，其中品牌名称若包含中英文，可用中文+英文形式，或仅用中文、英文名称。
                                        </p>
                                        <p class="ui-form-explain">
                                            2. 标题只能输入中文汉字、英文字母、数字，不能包含特殊字符、空格，标题长度不能超过12个汉字。
                                        </p>
                                        <div class="m-check-memo" style="position: absolute; top: 5px; color: rgb(202, 60, 60); display: none; right: -30px; opacity: 0;">
                                            标题不能包含特殊字符、空格，且长度不能多于12个汉字
                                        </div>
                                    </div>
                                    <!-- start 首页专场海报 -->
                                    <div class="ui-form-item sbanner-input-container">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                            专场海报图
                                        </label>
                                        <div class="upload-sbanner view-UploadSbannerInput">
                                            <div class="uploadify-button upload-img">上传图片<input type="file" name="banner" accept="image/png,image/gif,image/jpg,image/jpeg" /></div>
                                            <c:if test="${specialShowPojo.banner == null || specialShowPojo.banner == ''}">
                                            <div class="upload-img-box" style="height:200px;display:none"><img src="" /></div>
                                            </c:if>
                                            <c:if test="${specialShowPojo.banner != null && specialShowPojo.banner != ''}">
                                            <div class="upload-img-box" style="height:200px;"><img src="upfiles/specialShow/${specialShowPojo.banner}" /></div>
                                            </c:if>
                                            <span id="banner_tip" class="" />
                                        </div>
                                        <p class="ui-form-explain view-UploadSbannerInfo">
                                            <span class="c-333">
                                                图片尺寸：828px*380px，请按模板说明上传相关信息。
                                            </span>
                                            <span class="red">
                                                海报图将应用于首页展示，对于销售额的影响非常重要，请务必重视！
                                            </span>
                                            <br>
                                            <!--
                                            <a target="_blank" href="#"
                                            class="blue mr-5">
                                                查看海报图设计规范&gt;&emsp;
                                            </a>
                                            <a target="_blank" href="#" class="blue">
                                                下载PSD文件（包括首页和方形图模版） &gt;
                                            </a>
                                            -->
                                        </p>
                                    </div>
                                    <!-- end 专场海报 -->
                                </fieldset>
                            </div>
                            <div class="apply-box">
                                <h3 class="litter-title">
                                    专场优惠
                                </h3>
                                <fieldset class="p50 seller-martshow-apply">
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            优惠活动
                                        </label>
                                        <p class="apply-mf-info">
                                            设置专场促销活动，审核通过后，买家在专场内购买商品，达到条件即可享受优惠。
                                        </p>
                                    </div>
                                    <div class="ui-form-item">
                                        <a class="btn" id="add-type-btn">
                                            <i class="iconfont">&#xf0175;</i>
                                            添加活动
                                        </a>
                                    </div>
                                    <div class="ui-form-item" id="type-box" style="display: none;">
                                        <label class="ui-label">
                                            选择类型
                                        </label>
                                        <div class="apply-mf-info">
                                            <label for="type-mj">
                                                满减
                                            </label>
                                            <input type="radio" name="specialShowPojo.discountType" id="type-mj" value="1">
                                            <label for="type-mz">
                                                满折
                                            </label>
                                            <input type="radio" name="specialShowPojo.discountType" id="type-mz" value="2">
                                            <a class="btn" id="clear-type-btn">
                                                取消
                                            </a>
                                        </div>
                                    </div>
                                    <div id="mj-box" style="display: none;">
                                        <div class="ui-form-item brand" id="mj1">
                                            <span>
                                                满：
                                            </span>
                                            <select style="width: 120px;" name="conditionmj" id="condition1">
                                                <option value="">
                                                    请选择
                                                </option>
                                            </select>
                                            <span>
                                                元，
                                            </span>
                                            <span>
                                                立减
                                            </span>
                                            <select style="width: 120px;" name="denominationmj" id="denomination1">
                                                <option value="">
                                                    请选择
                                                </option>
                                            </select>
                                            <span>
                                                元；
                                            </span>
                                        </div>
                                        <div class="ui-form-item brand" id="mj2" style="display: none;">
                                            <span>
                                                满：
                                            </span>
                                            <select style="width: 120px;" name="conditionmj" id="condition2">
                                                <option value="">
                                                    请选择
                                                </option>
                                            </select>
                                            <span>
                                                元，
                                            </span>
                                            <span>
                                                立减
                                            </span>
                                            <select style="width: 120px;" name="denominationmj" id="denomination2">
                                                <option value="">
                                                    请选择
                                                </option>
                                            </select>
                                            <span>
                                                元；
                                            </span>
                                            <a href="javascript:;" style="color: gray; text-decoration: none" onclick="delMj()">
                                                <i class="iconfont" color="gray">&#xf0155;</i>
                                            </a>
                                        </div>
                                        <div class="ui-form-item" id="mj-explain" style="display: none">
                                            <p class="ui-form-explain">
                                                专场满减最多可设置两级
                                            </p>
                                        </div>
                                        <div class="ui-form-item">
                                            <a class="btn" data="0" id="add-mj-btn">
                                                <i class="iconfont">&#xf0175;</i>
                                                增加
                                            </a>
                                        </div>
                                    </div>
                                    <div id="mz-box" style="display: none;">
                                        <div class="ui-form-item brand" id="mz1">
                                            <span>
                                                满：
                                            </span>
                                            <select style="width:100px;height:24px;margin-top:0;" name="conditionmz" id="mz_condition1">
                                                <option value="">
                                                    请选择
                                                </option>
                                            </select>
                                            <span>
                                                件，
                                            </span>
                                            <span>
                                                享受
                                            </span>
                                            <input name="denominationmz" type="text" id="mz_denomination1" class="ui-input" style="width:100px;padding:3px;">
                                            <span>
                                                折；
                                            </span>
                                            <span class="apply-mf-info">
                                                请输入
                                                <strong>
                                                    5.0 ~ 9.9
                                                </strong>
                                                之间的折扣优惠
                                            </span>
                                        </div>
                                        <div class="ui-form-item brand" id="mz2" style="display: none;">
                                            <span>
                                                满：
                                            </span>
                                            <select style="width:100px;height:24px;margin-top:0;" name="conditionmz"
                                            id="mz_condition2">
                                                <option value="">
                                                    请选择
                                                </option>
                                            </select>
                                            <span>
                                                件，
                                            </span>
                                            <span>
                                                享受
                                            </span>
                                            <input name="denominationmz" type="text" id="mz_denomination2" class="ui-input"
                                            style="width:100px;padding:3px;">
                                            <span>
                                                折；
                                            </span>
                                            <a href="javascript:;" style="color: gray; text-decoration: none" onclick="delMz()">
                                                <i class="iconfont" color="gray">&#xf0155;</i>
                                            </a>
                                        </div>
                                        <div class="ui-form-item" id="danger_tip" style="display: none">
                                            <p class="red">
                                                当前优惠力度超过3成哦！
                                            </p>
                                        </div>
                                        <div class="ui-form-item" id="mz-explain" style="display: none">
                                            <p class="ui-form-explain">
                                                专场满折最多可设置两级
                                            </p>
                                        </div>
                                        <div class="ui-form-item">
                                            <a class="btn" data="0" id="add-mz-btn">
                                                <i class="iconfont">&#xf0175;</i>
                                                增加
                                            </a>
                                        </div>
                                        <div class="ui-form-item">
                                        </div>
                                    </div>
                                </fieldset>
                            <div class="apply-box margin-bootem">
                                <h3 class="litter-title">
                                    专场属性
                                </h3>
                                <fieldset class="p50 seller-martshow-apply">
                                    <div class="ui-form-item  padding-bottom">
                                        <div class="brand ps-re">
                                            <label for="" class="ui-label">
                                                <span class="ui-form-required">
                                                    *
                                                </span>
                                                专场指定分类
                                            </label>
                                            <select class="J_categoryMap ui-input" name="specialShowPojo.mainCategory1" id="mainCategory1">
                                            	<option value="">请选择一级分类</option>
                                                <s:iterator value="productTypeLt">
													<option value="<s:property value="id"/>" <s:if test="id == specialShowPojo.mainCategory1">selected="selected"</s:if>>
														<s:property value="name" />
													</option>
												</s:iterator>
                                            </select>
                                            <span id="category1_tip" class="" />
                                            <select class="J_categoryMapChange ui-input" name="specialShowPojo.mainCategory2" id="mainCategory2">
                                                <option value="">
                                                    请选择二级分类
                                                </option>
                                            </select>
                                            <span id="category2_tip" class="" />
                                            <p class="ui-form-explain">
                                                请根据专场中商品类目属性选择指定分类，填写错误将不能审核通过。
                                            </p>
                                            <span class="ui-form-other">
                                            </span>
                                        </div>
                                    </div>
                                    <div class="ui-form-item  padding-bottom">
                                        <div class="brand ps-re">
                                            <label for="" class="ui-label">
                                                <span class="ui-form-required">
                                                    *
                                                </span>
                                                主要适用年龄
                                            </label>
                                            <select name="specialShowPojo.ageRange" class="ui-input">
                                                <option value="">
                                                    请选择年龄段
                                                </option>
                                                <s:iterator value="ageRangeLt">
													<option value="<s:property value="id"/>" <s:if test="id == specialShowPojo.ageRange">selected="selected"</s:if>>
														<s:property value="name" />
													</option>
												</s:iterator>
                                            </select>
                                            <span id="agerange_tip" class="" />
                                            <p class="ui-form-explain">
                                                请根据专场中商品属性选择主要适用年龄段，填写错误将不能审核通过。
                                            </p>
                                            <span class="ui-form-other">
                                            </span>
                                        </div>
                                    </div>
                                    <div class="ui-form-item  padding-bottom">
                                        <div class="brand ps-re">
                                            <label for="" class="ui-label">
                                                <span class="ui-form-required">
                                                    *
                                                </span>
                                                专场退货地址
                                            </label>
                                            <select name="specialShowPojo.refundAddrId" class="ui-input"  style="width:500px">
                                                <s:iterator value="addressRangeLt">
													<option value="<s:property value="refundAddrId"/>" <s:if test="refundAddrId == specialShowPojo.refundAddrId">selected="selected"</s:if>>
														<s:property value="refundAddress" />
													</option>
												</s:iterator>
                                            </select>
                                            <span id="addressrange_tip" class="" />
                                            <p class="ui-form-explain">
                                                请根据专场中商品属性选择退货地址，填写错误将不能审核通过。
                                            </p>
                                            <span class="ui-form-other">
                                            </span>
                                        </div>
                                    </div>
                                    <div class="ui-form-item  padding-bottom">
                                        <div class="brand ps-re">
                                            <label for="" class="ui-label">
                                                专场所属频道
                                            </label>
                                            <div id="channelId" style="font-size:13px;"></div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="apply-box boxs-botom">
                                <fieldset class="p50 seller-martshow-apply">
                                    <div class="ui-form-item">
                                        <label for="show_promise_protocol">
                                            <input class="ui-checkbox" id="show_promise_protocol" name="show_promise_protocol"
                                            value="1" type="checkbox"/>
                                            我已阅读并同意
                                            <a target="_blank" href="getCommitment.do">
                                                《商家专场活动承诺书》
                                            </a>                                       
                                        </label>
                                        <span class="ui-form-other">
                                        </span>
                                    </div>
                                    <span id="promise_tip" class="" />
                                    <div class="ui-form-item">
                                        <input type="button" style="padding-top: 0px;" class="ui-button ui-button-mred view-SubmitBtn" value="创建专场">
                                    </div>
                                </fieldset>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/base.js" type="text/javascript"></script>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		
		
		<div class="miniDialog_mask" style="display: none;"></div>
        <div class="miniDialog_wrapper minRequirements" style="width: 440px; height: 200px; margin-left:-220px;margin-top:-295px;">
            <div class="miniDialog_title">
                新建专场商品最低款数要求
            </div>
            <div class="miniDialog_content_outer">
                <div class="miniDialog_content">
                	<!--
                    <table class="ui-table ui-table-layout-fixed">
                        <tbody>
                            <tr>
                                <th>
                                    前台类目
                                </th>
                                <th>
                                    专场款数最低门槛
                                </th>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <td>
                                    童装
                                </td>
                                <td>
                                    50款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    婴儿装
                                </td>
                                <td>
                                    30款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    亲子装
                                </td>
                                <td>
                                    30款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    童鞋
                                </td>
                                <td>
                                    30款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    配饰
                                </td>
                                <td>
                                    20款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    玩具
                                </td>
                                <td>
                                    10-20款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    婴儿用品
                                </td>
                                <td>
                                    10-20款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    孕妈服装
                                </td>
                                <td>
                                    30款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    孕妈用品
                                </td>
                                <td>
                                    20款
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    孕妈护肤
                                </td>
                                <td>
                                    20款
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    -->
                    <br>
                    <span class="red">
                        注：暂未公布，详情咨询专员!如果没有达到要求，将不能通过审核!
                    </span>
                </div>
            </div>
            <div class="miniDialog_buttons_area">
                <a class="miniDialog_button miniDialog_last_button" href="javascript:;">
                    确定并创建
                </a>
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
	</body>
</html>
<script type="text/javascript">
	tt.Conf.reqStarCls = ""; 
	var userbrand = new tt.Field(" 专场品牌", "specialShowPojo.userBrandId").setMsgId("userbrand_tip");
	var title = new tt.Field(" 专场标题", "specialShowPojo.title").setMsgId("title_tip");
	//var banner = new tt.Field(" 专场海报", "banner").setMsgId("banner_tip");
	var category1 = new tt.Field(" 一级分类", "specialShowPojo.mainCategory1").setMsgId("category1_tip");
	var category2 = new tt.Field(" 二级分类", "specialShowPojo.mainCategory2").setMsgId("category2_tip");
	var agerange = new tt.Field(" 适用年龄", "specialShowPojo.ageRange").setMsgId("agerange_tip");
	var addressrange = new tt.Field("退货地址", "specialShowPojo.refundAddrId").setMsgId("addressrange_tip");
	tt.vf.req.add(userbrand,title,category1,category2,agerange,addressrange);
	
	 window.pageData = {
	 	'mj_promotion' : '[12,59,69,79,89,99,109,111,119,129,139,149,159,169,179,189,199,209,269,299,359,399,499,599,699,799,899,999,1999,2999]',
        'mj_denomination' : '[10,14,20,30,40,50,60,70,80,90,100,150,200,300,400,500]',
        'mz_promotion' : '[2,3,4,5,6,7,8,9,10]'
	 };
	$(function(){
			//上传图片
			$(document).delegate(".upload-img input","change",function(){
				var _this = $(this);
				var url = _this.val();
				if (window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(_this.get(0).files[0]);
				} else if (window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(_this.get(0).files[0]);
				} else if (window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
				}
				_this.parents(".upload-img").next(".upload-img-box").css("display","")
				_this.parents(".upload-img").next(".upload-img-box").find("img").attr("src", url);
			});
			
			//优惠活动
			$("#add-type-btn").on("click",function(){
				$("#type-box").show();
				$(this).parents(".ui-form-item").hide();
				$(".apply-mf-info input[type=radio]").attr("checked",false);
			});
			$("#clear-type-btn").on("click",function(){
				$(".apply-mf-info input[type=radio]").attr("checked",false);
				$("#type-box,#mj-box,#mz-box").hide();
				$("#add-type-btn").parents(".ui-form-item").show();
				delMzVal();
				delMz2Val();
				delMjVal();
				delMj2Val();
			});
			$(".apply-mf-info input[type=radio]").on("change",function(){
				if($("#type-mj").is(":checked")){
					$("#mj-box").show();
					$("#mz-box").hide();
					delMz();
					delMzVal();
					delMz2Val();
				}else{
					$("#mj-box").hide();
					$("#mz-box").show();
					delMj();
					delMjVal();
					delMj2Val();
				}
			});
			$("#add-mj-btn").on("click",function(){
				$("#mj2,#mj-explain").show();
				$(this).parents(".ui-form-item").hide();				
			});
			$("#add-mz-btn").on("click",function(){
				$("#mz2,#mz-explain").show();
				$(this).parents(".ui-form-item").hide();				
			});
			
			//提交
			$(".view-SubmitBtn").on("click",function(){
				if($("#show_promise_protocol").is(':checked')==true){
					if (tt.validate()) {
						$(".minRequirements,.miniDialog_mask").show();
					}
				} else {
					alert("请选择同意！");
				}
			});
			$(".miniDialog_last_button").on("click",function(){
				if (tt.validate()) {
					$(".view-MartShowForm").trigger("submit");
				} 
			});
			mj_promotion();
			mz_promotion();
			
			doupdate();//update操作
		})
		function delMj(){
			$("#mj2,#mj-explain").hide();
			delMj2Val();
			$("#add-mj-btn").parents(".ui-form-item").show();
		}
		function delMz(){
			$("#mz2,#mz-explain").hide();
			delMz2Val();
			$("#add-mz-btn").parents(".ui-form-item").show();
		}
		function delMjVal(){
			$("#condition1").val("");
			$("#denomination1").val("");
		}
		function delMj2Val(){
			$("#condition2").val("");
			$("#denomination2").val("");
		}
		function delMzVal(){
			$("#mz_condition1").val("");
			$("#mz_denomination1").val("");
		}
		function delMz2Val(){
			$("#mz_condition2").val("");
			$("#mz_denomination2").val("");
		}
		
		function mj_promotion() {
		    var gCondition1 = $('#condition1'),
		        gAmount1 = $('#denomination1'),
		        gCondition2 = $('#condition2'),
		        gAmount2 = $('#denomination2'),
		        //@note 满
		        data1 = {
		            data : JSON.parse(window.pageData.mj_promotion)
		        },
		        //@note 减
		        data2 = {
		            data : JSON.parse(window.pageData.mj_denomination)
		        } ,
		        //@note 减
		        data3 = {
		            data : JSON.parse(window.pageData.mj_denomination)
		        };
		
		    data1.condition = -1;
		    data1.maxNum = Number.MAX_VALUE ;
		    data2.condition = -1;
		    data2.maxNum = Number.MAX_VALUE ;
		    data3.condition = -1;
		    data3.maxNum = Number.MAX_VALUE ;
		
		    gCondition1.html(get_render(data1));
		
			gCondition1.change(function(){
				data1.condition = $(this).val();
				gCondition2.html(get_render(data1));
				data3.maxNum = $(this).val() ;
				gAmount1.html(get_render(data3));
			 });
			var gCondition2Change = function(){
		        data2.maxNum = gCondition2.val();
		        if(gAmount1.val() !== ''){
		            data2.condition = gAmount1.val() ;
		            gAmount2.html(get_render(data2));
		        }
		    }
			gCondition2.change(gCondition2Change);
			gAmount1.change(gCondition2Change) ;
		}
		
		function get_render(data) {
			var source = '<option value="">请选择</option>';
			for (var i = 0; i < data.data.length; i ++) {
				if (data.condition < data.data[i] && ( data.maxNum > data.data[i] || !data.maxNum ) ) {
					source += '<option value="'+data.data[i]+'">'+data.data[i]+'</option>';
				}
			}
		    return source;
		}
		
		function mz_promotion() {
		    var gCondition1 = $("#mz_condition1");
		    var data1 = {
		            data : JSON.parse(window.pageData.mz_promotion)
		    }
		    data1.condition = -1;
		    gCondition1.html(get_render(data1));
		
		    var gCondition2 = $("#mz_condition2");
		    gCondition1.change(function(){
				data1.condition = $(this).val();
				gCondition2.html(get_render(data1));
			 });
		}
		
		function doupdate(){
			//update
			if(${specialShowPojo.discountType != null && specialShowPojo.discountType != ""}){
				$("#type-box").show();
				$("#add-type-btn").parents(".ui-form-item").hide();
				$(".apply-mf-info input[type=radio]").attr("checked",false);
				if(${specialShowPojo.discountType == 1 }){
					$("#type-mj").attr("checked",true);
					$("#mj-box").show();
					$("#mz-box").hide();
					delMz();
					delMzVal();
					delMz2Val();
					if(${size > 1}){
						$("#mj2,#mj-explain").show();
						$("#add-mj-btn").parents(".ui-form-item").hide();
						$("#condition1").find("option[value='${conditionmj[0]}']").attr("selected",true);
						$("#condition1").change();
						$("#denomination1").find("option[value='${denominationmj[0]}']").attr("selected",true);
						$("#condition2").find("option[value='${conditionmj[1]}']").attr("selected",true);
						$("#condition2").change();
						$("#denomination2").find("option[value='${denominationmj[1]}']").attr("selected",true);
					}else{
						$("#condition1").find("option[value='${conditionmj[0]}']").attr("selected",true);
						$("#denomination1").find("option[value='${denominationmj[0]}']").attr("selected",true);
					}
				}else if(${specialShowPojo.discountType == 2 }){
					$("#type-mz").attr("checked",true);
					$("#mj-box").hide();
					$("#mz-box").show();
					delMj();
					delMjVal();
					delMj2Val();
					if(${size > 1}){
						$("#mz2,#mz-explain").show();
						$("#add-mz-btn").parents(".ui-form-item").hide();
						$("#mz_condition1").find("option[value='${conditionmz[0]}']").attr("selected",true);
						$("#mz_condition1").change();
						$("#mz_denomination1").val("${denominationmz[0]}");
						$("#mz_condition2").find("option[value='${conditionmz[1]}']").attr("selected",true);
						$("#mz_condition2").change();
						$("#mz_denomination2").val("${denominationmz[1]}");
					}else{
						$("#mz_condition1").find("option[value='${conditionmz[0]}']").attr("selected",true);
						$("#mz_denomination1").val("${denominationmz[0]}");
					}
				}
			}
		}
</script>