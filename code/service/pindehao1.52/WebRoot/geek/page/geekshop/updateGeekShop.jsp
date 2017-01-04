<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/default.css" media="all" />
    <link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
<script type="text/javascript">
	$(function(){
		select1();
		select3();
		$('#ageType').bind("change", select2);
		$('#province').bind("change", select4);
		$('#city').bind("change", select5);
	});
	function select1() {
		$.ajax(
		{
			type: "post",
			url: "getSysDictListByType.do?sysDict.type=user_age",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${userMakerShopPojo.ageType}" == o_msg[i].value){
                    	selectedStr = "selected='selected'";
                    }
					$("#ageType").append("<option value=" + o_msg[i].value +" "+selectedStr+ ">" + o_msg[i].name + "</option>");
				}
				if("${userMakerShopPojo.ageType}"!=null && "${userMakerShopPojo.ageType}"!=""){
					select2();
				}
			}
		})
	};
	function select2() {
            $("#ability").html("");
            $.ajax(
            {
                type: "post",
                url: "getSkillTypes.do?ageId="+$('#ageType').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
						var selectedStr = "";
                   	    if("${userMakerShopPojo.ability}" == o_msg[i].skillValue){
                    		selectedStr = "selected='selected'";
                   		}
                        $("#ability").append("<option value=" + o_msg[i].skillValue +" "+selectedStr+ ">" + o_msg[i].skillName + "</option>");
                    }
                }
            })
        };
    function select3() {
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                   	if("${userMakerShopPojo.province}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                   	}
					$("#province").append("<option value=" + o_msg[i].id +" "+selectedStr+ ">" + o_msg[i].name + "</option>");
				}
				if("${userMakerShopPojo.province}"!=null && "${userMakerShopPojo.province}"!=""){
					select4();
				}
			}
		})
	};
	function select4() {
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                   	if("${userMakerShopPojo.city}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                   	}
					$("#city").append("<option value=" + o_msg[i].id +" "+selectedStr+ ">" + o_msg[i].name + "</option>");
				}
				if("${userMakerShopPojo.city}"!=null && "${userMakerShopPojo.city}"!=""){
					select5();
				}
			}
		})
	};
	function select5() {
            $("#area").html("");
            $.ajax(
            {
                type: "post",
                url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                   	if("${userMakerShopPojo.area}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                   	}
                        $("#area").append("<option value=" + o_msg[i].id + " "+selectedStr+ ">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
</script>
</head>

<body>
    <jsp:include page="../geekHeader.jsp"></jsp:include>

        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}.apply-box select{width:190px;}.store-tab{border-top:1px solid #f0f0f0;}.store-tab-title{padding:30px 0 20px;text-align:center;}.store-tab-item{display:none;}
                    </style>
                    <h1 class="seller-title">店铺管理</h1>
                    <form action="updateGeekShopWeb.do" accept-charset="utf-8" class="ui-form p50" method="post" id="from1" enctype="multipart/form-data">
                        <input class="ui-input" name="userMakerShopPojo.shopType" type="hidden" value="" />
                        <input class="ui-input" name="userMakerShopPojo.id" type="hidden" value="${userMakerShopPojo.id}" />
                        <input class="ui-input" name="userMakerShopPojo.shopId" type="hidden" value="${userMakerShopPojo.shopId}" />
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 店铺名称
                                </label>
                                <input class="ui-input" id="userMakerShopPojo.shopName" name="userMakerShopPojo.shopName" type="text" value="${userMakerShopPojo.shopName}" />
                            </div>
                            <div id="shopName_mgId"></div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 店铺LOGO
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img">
                                            <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${userMakerShopPojo.shopLOGO}" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile1" />
                                    </div>                                
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 店铺主图
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img">
                                            <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${userMakerShopPojo.shopMainImage}" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile2" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 适用年龄
                                </label>
                                <td><select id="ageType" name="userMakerShopPojo.ageType" class="floatLeft" ></select>					
									<div id="ageType_mgId"></div></td>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 开发能力
                                </label>
                                <td><select id="ability" name="userMakerShopPojo.ability" class="floatLeft" ></select>					
									<div id="ability_mgId"></div></td>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 联系人
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.contact" type="text" value="${userMakerShopPojo.contact}" /><div id="contact_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 地址
                                </label>
                                <select id="province" name="userMakerShopPojo.province" class="ui-input ui-input-checkcode" style="width: 130px;">
                                </select>&nbsp;&nbsp;
                                <select id="city" name="userMakerShopPojo.city" class="ui-input ui-input-checkcode" style="width: 150px;">
                                </select>&nbsp;&nbsp;
                                <select id="area" name="userMakerShopPojo.area" class="ui-input ui-input-checkcode" style="width: 145px;">
                                </select>
                                <br/><br/><div id="province_mgId"></div><div id="city_mgId"></div><div id="area_mgId"></div>
                                <textarea name="userMakerShopPojo.detailedAddress" class="ui-textarea" placeholder="不需要重复填写省市区，必须大于5个字符，小于120个字符">${userMakerShopPojo.detailedAddress}</textarea><div id="detailedAddress_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 联系电话
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.phone" type="text" value="${userMakerShopPojo.phone}" />
                                <div id="phone_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 客服电话
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.servicePhone" type="text" value="${userMakerShopPojo.servicePhone}" />
                                <div id="servicePhone_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 支付宝帐号
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.alipayAccount" type="text" value="${userMakerShopPojo.alipayAccount}" />
                                <div id="alipayAccount_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 支付宝实名
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.alipayName" type="text" value="${userMakerShopPojo.alipayName}" />
                                <div id="alipayName_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 单平台内容产出量
                                </label>
                                <select name="userMakerShopPojo.contentOutput">
                                    <option value="0" <s:if test="userMakerShopPojo.contentOutput==0">selected="selected"</s:if>>1/7天</option>
                                    <option value="1" <s:if test="userMakerShopPojo.contentOutput==1">selected="selected"</s:if>>1/5天</option>
                                    <option value="2" <s:if test="userMakerShopPojo.contentOutput==2">selected="selected"</s:if>>1/3天</option>
                                    <option value="3" <s:if test="userMakerShopPojo.contentOutput==3">selected="selected"</s:if>>1/1天</option>
                                    <option value="4" <s:if test="userMakerShopPojo.contentOutput==4">selected="selected"</s:if>>2/1天</option>
                                    <option value="5" <s:if test="userMakerShopPojo.contentOutput==5">selected="selected"</s:if>>3/1天以上</option>
                                </select>
                                <div id="contentOutput_mgId"></div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 单平台原创内容产出量
                                </label>
                                <select name="userMakerShopPojo.contentOutputOriginal">
                                    <option value="0" <s:if test="userMakerShopPojo.contentOutputOriginal==0">selected="selected"</s:if>>1/7天</option>
                                    <option value="1" <s:if test="userMakerShopPojo.contentOutputOriginal==1">selected="selected"</s:if>>1/5天</option>
                                    <option value="2" <s:if test="userMakerShopPojo.contentOutputOriginal==2">selected="selected"</s:if>>1/3天</option>
                                    <option value="3" <s:if test="userMakerShopPojo.contentOutputOriginal==3">selected="selected"</s:if>>1/1天</option>
                                    <option value="4" <s:if test="userMakerShopPojo.contentOutputOriginal==4">selected="selected"</s:if>>2/1天</option>
                                    <option value="5" <s:if test="userMakerShopPojo.contentOutputOriginal==5">selected="selected"</s:if>>3/1天以上</option>
                                </select>
                                <div id="contentOutputOriginal_mgId"></div>
                            </div>
                        </div>

                        <div class="store-tab">
                            <div class="store-tab-title">
                                <a href="#store_company" class="ui-button ui-button-mwhite" onclick="showtype(0)">企业认证</a>
                                <a href="#store_pc" class="ui-button ui-button-mwhite" onclick="showtype(1)">个人认证</a>	
                            </div>
                            <!-- 企业认证 -->
                            <div id="store_company" class="store-tab-item">
                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 企业全称
                                        </label>
                                        <input class="ui-input" id="userMakerShopPojo.shopTypeName1" name="userMakerShopPojo.shopTypeName1" type="text" value="${userMakerShopPojo.shopTypeName}" />
                                        <div id="shopTypeName1_mgId"></div>
                                    </div>
                                </div>
                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 营业执照正/副本
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <div class="fl picInfo">
                                            <div class="uploadify">
                                                <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                                <div class="uploadPreview_img">
                                                    <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageOriginal}" style="width:120px;height:120px;">
                                                </div>
                                                <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile3" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span>  法人身份证正反面图
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <div class="fl picInfo">
                                            <div class="uploadify">
                                                <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                                <div class="uploadPreview_img">
                                                    <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageCopy}" style="width:120px;height:120px;">
                                                </div>
                                                <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile4" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 企业认证 end -->
                            <!-- 个人认证 -->
                            <div id="store_pc" class="store-tab-item">
                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 店铺平台
                                        </label>
                                        <input class="ui-input" id="userMakerShopPojo.shopTypeName2" name="userMakerShopPojo.shopTypeName2" type="text" value="${userMakerShopPojo.shopTypeName}" />
                                        <div id="shopTypeName2_mgId"></div>
                                    </div>
                                </div>

                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 店铺地址
                                        </label>
                                        <input class="ui-input" id="userMakerShopPojo.shopTypeUrl" name="userMakerShopPojo.shopTypeUrl" type="text" value="${userMakerShopPojo.shopTypeUrl}" />
                                        <div id="shopTypeUrl_mgId"></div>
                                    </div>
                                </div>

                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 店主手持身份证照片
                                        </label>
                                        <div class="fl picInfo">
                                            <div class="uploadify">
                                                <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                                <div class="uploadPreview_img">
                                                    <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageOriginal}" style="width:120px;height:120px;">
                                                </div>
                                                <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile5" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 个人认证 end -->
                        </div>
                        <div class="apply-box boxs-botom">
                            <div class="ui-form-item">
                                <input type="button" style="padding-top: 0px;" class="ui-button ui-button-mred" value="保存并提交审核" id="sbutton">
                                <input type="submit" style="padding-top: 0px;" class="ui-button ui-button-mred view-SubmitBtn" onclick="window.history.back()" value="返回">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="../geekFooter.jsp"></jsp:include>

    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/js/base.js" type="text/javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/area.js" type="text/javascript"></script>
    <script>
    var shopName              = new tt.Field("店铺名称 ","userMakerShopPojo.shopName").setMsgId("shopName_mgId");
    var ageType               = new tt.Field("适用年龄 ","userMakerShopPojo.ageType").setMsgId("ageType_mgId");
    var ability               = new tt.Field("开发能力 ","userMakerShopPojo.ability").setMsgId("ability_mgId");
    var contact               = new tt.Field("联系人 ","userMakerShopPojo.contact").setMsgId("contact_mgId");
    var province              = new tt.Field("省 ","userMakerShopPojo.province").setMsgId("province_mgId");
    var city                  = new tt.Field("市 ","userMakerShopPojo.city").setMsgId("city_mgId");
    var area                  = new tt.Field("区域 ","userMakerShopPojo.area").setMsgId("area_mgId");
    var detailedAddress       = new tt.Field("详细地址 ","userMakerShopPojo.detailedAddress").setMsgId("detailedAddress_mgId");
    var phone                 = new tt.Field("联系电话 ","userMakerShopPojo.phone").setMsgId("phone_mgId");
    var servicePhone          = new tt.Field("客服电话 ","userMakerShopPojo.servicePhone").setMsgId("servicePhone_mgId");
    var alipayAccount         = new tt.Field("支付宝帐号 ","userMakerShopPojo.alipayAccount").setMsgId("alipayAccount_mgId");
    var alipayName            = new tt.Field("支付宝实名 ","userMakerShopPojo.alipayName").setMsgId("alipayName_mgId");
    var contentOutput         = new tt.Field("单平台内容产出量","userMakerShopPojo.contentOutput").setMsgId("contentOutput_mgId");
    var contentOutputOriginal = new tt.Field("单平台原创内容产出量 ","userMakerShopPojo.contentOutputOriginal").setMsgId("contentOutputOriginal_mgId");
    var shopTypeName1         = new tt.Field("企业全称 ","userMakerShopPojo.shopTypeName1").setMsgId("shopTypeName1_mgId");
    var shopTypeName2         = new tt.Field("店铺平台","userMakerShopPojo.shopTypeName2").setMsgId("shopTypeName2_mgId");
   	var shopTypeUrl           = new tt.Field("店铺地址","userMakerShopPojo.shopTypeUrl").setMsgId("shopTypeUrl_mgId");
    tt.vf.req.add(shopName,ageType,ability,contact,province,city,area,detailedAddress,phone,servicePhone,alipayAccount,alipayName,
    contentOutput,contentOutputOriginal);
        $("#sbutton").click(function(){
        	if(tt.validate()){
				if(window.confirm("确定要提交修改吗？提交修改需等待管理员审核！")){
					document.getElementById("from1").submit();
				}
			}
		  });	
        $(function(){
        	
            //tab切换
            $(".store-tab-title>a").bind("click", function(){
                if(!$(this).hasClass("ui-button-mgreen")){
                    $(this).siblings().removeClass("ui-button-mgreen").addClass("ui-button-mwhite");
                    $(this).removeClass("ui-button-mwhite").addClass("ui-button-mgreen");
                    var oId = $(this).attr("href");
                    $(".store-tab-item").hide();
                    $(oId).show();
                }
                return false;
            });
            <s:if test = "userMakerShopPojo.shopType == 0 ">
            	$(".store-tab-title>a").eq(0).trigger("click");
            </s:if>
            <s:elseif test = "userMakerShopPojo.shopType == 1">
            	$(".store-tab-title>a").eq(1).trigger("click");
            </s:elseif>
            
            //图片更换
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
        });
        function showtype(v){
        	$("input[name='userMakerShopPojo.shopType']").val(v);
	        if(v == 0){
	       		document.getElementById('userMakerShopPojo.shopTypeName2').value='';
	       		document.getElementById('userMakerShopPojo.shopTypeUrl').value='';
	       		tt.vf.req.add(shopTypeName1);
	       		tt.vf.req.rm(shopTypeName2,shopTypeUrl);
	        }else if(v == 1){
	        	document.getElementById('userMakerShopPojo.shopTypeName1').value='';
	       		tt.vf.req.add(shopTypeName2,shopTypeUrl);
	        	tt.vf.req.rm(shopTypeName1);
	        }
        }
    </script>
</body>
</html>