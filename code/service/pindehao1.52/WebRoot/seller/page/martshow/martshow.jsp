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
    </head>
	<body>
			<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .ui-form-item select{height:25px;line-height:25px;border:1px solid #D8D8D8;width:100px;}.ui-form-item input[type=text]{height:25px;border:1px solid #D8D8D8;line-height:25px;padding:0 5px;}.ui-btn-theme{background:#f5f5f5;padding:0 10px;border:1px solid #e6e6e6;color:#666;font-size:12px;}.ui-btn-theme i{font-size:14px;margin-right:-2px;vertical-align:top;}a.ui-btn-theme:hover{color:#ff4965;border:1px solid #ff4965;background:#FFF;}.ui-input:hover,.ui-select-theme:hover{border:1px solid #888!important;}.qc_statuc_innercont{position:relative;*z-index:12;}.status_cont .iconfont{color:#999;display:inline-block;font-size-adjust:none;font-size:10px;-webkit-transform:scale(.8);cursor:pointer;}.qc_statuc_innercont{position:relative;*z-index:12;}.status_cont .qc_status_note{display:none;border-radius:3px;position:absolute;background:#eee;border:1px solid #ececec;top:25px;left:-50px;width:100px;padding:5px;z-index:20;*zoom:1;}.triangle{position:absolute;top:-16px;left:49px;width:0;height:0;border-width:8px;border-style:solid;border-color:transparent transparent #eee;}
                        </style>
                        <h1 class="seller-title">
                            专场管理
                            <div class="seller-title-head-more">
                            	<!--
                                <a style="font-size:10px; color:gray" href="http://pan.baidu.com/s/1c02JpwG">
                                    专场活动前必读
                                </a>
                                -->
                                <a class="ui-button ui-button-morange" href="applyMartShowWeb.do" id="create-martshow">
                                    <i class="iconfont">&#xf0175;</i>
                                    新建专场
                                </a>
                            </div>
                        </h1>
                        <!-- search start -->
                        <div class="seller-search-form" style="margin: 8px 0 -14px -8px;">
                            <form action="#" method="post" accept-charset="utf-8" id="sysform">
                                <div class="ui-box-content">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label" style="width: 36px;">
                                            搜索
                                        </label>
                                        <!--
                                        <select name="type" class="w-mart-select ui-select-theme" style="width:80px">
                                            <option value="title">
                                                标题
                                            </option>
                                            <option value="brand">
                                                品牌
                                            </option>
                                        </select>
                                        -->
                                    </div>
                                    <div class="ui-form-item">
                                        <input class="ui-input w-mart-input" type="text" name="specialShowPojo.id" value="" placeholder="请输入专场ID" style="width:150px; ">
                                        <!--
                                        <select name="brand_value" class="w-mart-select ui-select-theme" style="display: none;margin-left: -5px;">
                                            <option value="">
                                                全部
                                            </option>
                                            <option value="11449">
                                                万达隆/WANDERLONG
                                            </option>
                                            <option value="11441">
                                                柏晖玩具/BOHUI TOYS
                                            </option>
                                            <option value="2381">
                                                荣泰
                                            </option>
                                        </select>
                                        -->
                                    </div>
                                    <div class="ui-form-item">
                                        <input class="ui-input w-mart-input" type="text" name="specialShowPojo.title" value="" placeholder="请输入专场标题" style="width:150px; ">
                                    </div>
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label" style="width: 36px;">
                                            状态
                                        </label>
                                        <select name="specialShowPojo.status" class="w-mart-select ui-select-theme">
                                            <option value="">
                                                全部
                                            </option>
                                            <option value="0">
                                                未提交
                                            </option>
                                            <option value="1">
                                                待审核
                                            </option>
                                            <option value="2">
                                                待排期
                                            </option>
                                            <option value="3">
                                                退回修改
                                            </option>
                                            <option value="4">
                                                排期完成
                                            </option>
                                            <option value="5">
                                                已结束
                                            </option>
                                        </select>
                                    </div>
                                    <div class="ui-form-item search-btn">
                                    	<input type="hidden" name="page.pageNo" value=0 id="pageNo">
                                        <a id="query_btn" href="#" class="ui-button ui-btn-theme w-mart-button ui-button-mwhite">
                                            查询
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- search end -->
                        <div class="ui-table-container p20 martshow-index">
                            <table class="ui-table">
                                <thead>
                                    <tr>
                                        <th>
                                            专场ID
                                        </th>
                                        <th>
                                            专场标题
                                        </th>
                                        <th width="120px">
                                            专场满减
                                        </th>
                                        <!--
                                        <th width="55px">
                                            品牌红包
                                        </th>
                                        -->
                                        <th>
                                            开始时间
                                        </th>
                                        <th>
                                            结束时间
                                        </th>
                                        <th>
                                            创建时间
                                        </th>
                                        <!--
                                        <th style="width: 10%">
                                            活动报名
                                        </th>
                                        -->
                                        <th width="50px">
                                            状态
                                        </th>
                                        <th width="50px">
                                            咨询
                                        </th>
                                        <th width="50px">
                                            操作
                                        </th>
                                    </tr>
                                </thead>
                                <tbody id="body"></tbody>
                            </table>
                            <!-- 页码开始 -->
                            <div class="pagination" id="Pagination">
                            </div>
                            <!-- 页码结束 -->
                        </div>
                        
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	var pageSize = 10;
	function query() {
		var id = $("input[name='specialShowPojo.id']").val();
		var r = /^[0-9]*[1-9][0-9]*$/;
		if(id != "" && !r.test(id)){
			alert("专场ID必须为正整数！");
			return;
		}
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("getMartShowCountWeb.do", "getMartShowListWeb.do?randquery="+rand,pageSize);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var begin = "等待排期";
		var end = "等待排期";
		var discount ="无";
		if(this.beginTimeStr != ""){
			begin = this.beginTimeStr;
		}
		if(this.endTimeStr != ""){
			end = this.endTimeStr;
		}
		if(this.discountContext != ""){
			discount = this.discountContext;
		}
		$("#body").append(
				"<tr><td>"+this.id+"</td>"+
                "<td>"+this.title+"</td>"+
                "<td><p>"+discount+"</p></td>"+
                //"<td>无</td>"+
                "<td>"+begin+"</td>"+
                "<td>"+end+"</td>"+
                "<td>"+this.createDateStr+"</td>"+
                //"<td class='ui-table-action'></td>"+
                "<td class='status_cont'>"+this.statusName+"</td>"+
                "<td><a target='_blank' href='http://crm2.qq.com/page/portalpage/wpa.php?uin=4001503677&aty=0&a=0&curl=&ty=1'><img border='0' src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/images/button_121.jpg' alt='点击咨询专员' title='点击咨询专员'></a></td>"+
                "<td class='ui-table-action'><a target='_blank' href='getMartShowItemWeb.do?specialId="+this.id+"'>专场管理</a></td></tr>");
				
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "getMartShowListWeb.do",pageSize);
		
		$("#query_btn").click(query);
	});
	
</script>