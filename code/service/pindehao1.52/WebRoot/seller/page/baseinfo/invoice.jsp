<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/default.css" media="all" />
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
    </head>
	<body>
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style type="text/css">
                            .ui-form fieldset{border:0 none;margin:auto;padding:60px 0 100px;width:500px;}input.ui-button{padding:0 1.5em;}.uploadify{position:relative;margin-bottom:1em;}.uploadify-button{background-color:#ccc;background-image:linear-gradient(bottom,#ccc 0%,#eee 100%);background-image:-o-linear-gradient(bottom,#ccc 0%,#eee 100%);background-image:-moz-linear-gradient(bottom,#ccc 0%,#eee 100%);background-image:-webkit-linear-gradient(bottom,#ccc 0%,#eee 100%);background-image:-ms-linear-gradient(bottom,#ccc 0%,#eee 100%);background-image:-webkit-gradient(linear,left bottom,left top,color-stop(0,#ccc),color-stop(1,#eee));background-position:center top;background-repeat:no-repeat;-webkit-border-radius:30px;-moz-border-radius:30px;border-radius:2px;border:1px solid #888;color:#666;font:bold 12px Arial,Helvetica,sans-serif;text-align:center;width:100%;}.uploadify:hover .uploadify-button{background-color:#ccc;background-image:linear-gradient(top,#ccc 0%,#eee 100%);background-image:-o-linear-gradient(top,#ccc 0%,#eee 100%);background-image:-moz-linear-gradient(top,#ccc 0%,#eee 100%);background-image:-webkit-linear-gradient(top,#ccc 0%,#eee 100%);background-image:-ms-linear-gradient(top,#ccc 0%,#eee 100%);background-image:-webkit-gradient(linear,left bottom,left top,color-stop(0,#ccc),color-stop(1,#eee));background-position:center bottom;}.upload-tax-registration-certificate{background-image:url(http://b5.hucdn.com/upload/seller/1510/19/45551263126827_1000x727.jpg);background-size:120px 100px;}.upload-general-taxpayer-qualification{}.modify-stauts{display:block;}.u-note-tip{position:relative;top:4px;left:6px;cursor:pointer;*z-index:20;}.u-note-tip .ui-poptip-blue{display:none;position:absolute;width:325px;height:196px;top:22px;left:-155px;*z-index:100;}.u-note-tip li{list-style:circle;margin-left:16px;}.uploadify{position:relative;height: 120px; width: 120px;text-align:center;border:1px solid #ddd;}.uploadPreview_note{width:120px;height:120px;line-height:120px;}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;}
                        </style>
                        <div class="ui-tipbox ui-tipbox-success">
                            <div class="ui-tipbox-icon">
                                <i class="iconfont">&#xf0156;</i>
                            </div>
                            <div class="ui-tipbox-content">
                                <h3 class="ui-tipbox-title">
                                    操作提示
                                </h3>
                                <p class="ui-tipbox-explain">
                                    等待管理员审核
                                </p>
                            </div>
                        </div>
                        <form action="http://seller.beibei.com/account/invoice.html" method="post"
                        accept-charset="utf-8" id="seller-account-invoice" class="ui-form">
                            <div style="display:none">
                                <input type="hidden" name="hxcsrf" value="3296b3a93c1f9b1c21232aa9783275a6">
                            </div>
                            <fieldset>
                                <div class="ui-form-item ui-form-item-error">
                                    <label for="" class="ui-label">
                                        公司名称：
                                    </label>
                                    <input type="hidden" name="company" value="广东群宇互动科技有限公司">
                                    <p class="ui-form-text">
                                        广东群宇互动科技有限公司
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        公司税号：
                                    </label>
                                    <input name="tax_code" class="ui-input" type="text" value="440515324725040">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain">
                                        公司税号下面填写提示：“企业地税编码“、”企业编码“、“纳税编码”等非公司税号，请注意区分。
                                    </p>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        公司注册地址：
                                    </label>
                                    <input name="address" class="ui-input ui-input-large" type="text" value="汕头市澄海区莱美工业区全宇工业园二栋第四层">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        公司注册电话：
                                    </label>
                                    <input name="phone" class="ui-input" type="text" value="0754-88098777">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        开户行：
                                    </label>
                                    <input name="bank" class="ui-input" type="text" value="广东澄海潮商村镇银行股份有限公司">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        银行账号：
                                    </label>
                                    <input name="bank_account" class="ui-input" type="text" value="80020000006981077">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        收件人姓名：
                                    </label>
                                    <input class="ui-input" type="text" name="receiver_name" value="陈惜梅">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        收件人电话：
                                    </label>
                                    <input class="ui-input" type="text" name="receiver_phone" value="18316862042">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        收件地址：
                                    </label>
                                    <input name="receiver_address" class="ui-input" type="text" value="汕头市澄海区莱美工业区全宇工业园宇博电子商务园二栋第四层">
                                    <span class="ui-form-other">
                                        <a href="#">
                                        </a>
                                    </span>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        发票类型：
                                    </label>
                                    <select name="invoice_type" class="view-SelectTaxCategory">
                                        <option value="">
                                            请选择
                                        </option>
                                        <option value="1">
                                            增值税专用发票
                                        </option>
                                        <option value="2" selected="selected">
                                            增值税普通发票
                                        </option>
                                    </select>
                                    <span class="u-note-tip J_noteTip">
                                        <i class="iconfont blue">&#xf0143;</i>
                                        <div class="ui-poptip ui-poptip-blue J_popTip">
                                            <div class="ui-poptip-shadow">
                                                <div class="ui-poptip-container">
                                                    <div class="ui-poptip-arrow ui-poptip-arrow-12">
                                                        <em>
                                                        </em>
                                                        <span>
                                                        </span>
                                                    </div>
                                                    <div class="ui-poptip-content">
                                                        <p>
                                                            发票类型提示：
                                                        </p>
                                                        <p>
                                                            1、非增值税一般纳税人请选择增值税普通发票，请上传税务登记证。
                                                        </p>
                                                        <p>
                                                            2、增值税一般纳税人请选择增值税专用发票，请上传税务登记证及增值税一般纳税人资质证明。
                                                        </p>
                                                        <p>
                                                            增值税一般纳税人资质证明主要表现形式：
                                                        </p>
                                                        <ul>
                                                            <li>
                                                                加盖有“增值税一般纳税人”条章的税务登记证副本
                                                            </li>
                                                            <li>
                                                                《税务事项通知书》
                                                            </li>
                                                            <li>
                                                                《增值税一般纳税人申请认定表》
                                                            </li>
                                                            <li>
                                                                《增值税一般纳税人资格证书》
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </span>
                                    <p class="ui-form-explain">
                                        请选择开票票类型。
                                    </p>
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item TaxRegistrationCertificate ">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        税务登记证：
                                    </label>                                
                                    <div class="uploadify">
                                        <p class="uploadPreview_note">
                                            <i class="iconfont">&#xf00f7;</i>
                                            添加图片
                                        </p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="">
                                    </div>                                    
                                    <p class="ui-form-explain ui-tiptext ui-tiptext-error">
                                    </p>
                                </div>
                                <div class="ui-form-item">
                                    <input type="submit" class="ui-button ui-button-lred" value="保存开票信息">
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/base.js" type="text/javascript"></script>
        <script>
			$(function(){
				//发票类型提示
				$(".u-note-tip").hover(function(){
					$(".u-note-tip .ui-poptip-blue").show();	
				},function(){
					$(".u-note-tip .ui-poptip-blue").hide();					
				});
				
				//上传图片
				$(document).delegate(".uploadPreview_imgfile","change",function(){
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
					_this.siblings(".uploadPreview_note").hide()
				});
				
			})
		</script>
	</body>
</html>