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
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>
	<body>
			<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                           .ui-nav-item-current a,.ui-nav-item-current a:hover{filter:none;background:none;color:#fff;}.ui-nav-item-current .ui-nav-submain{display:none;background:#fff;}.ui-nav-subitem-current a,.ui-nav-subitem-current a:hover{border:none;-webkit-box-shadow:none;box-shadow:none;}.uploadify{position:relative;height: 120px; width: 120px;text-align:center;border:1px solid #ddd;}.uploadPreview_note{width:120px;height:120px;line-height:120px;}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;}
                        </style>
                        <div class="addBrank">
                            <div class="title">
                                <label>
                                    添加/删除品牌
                                </label>
                                <a href="getBrandListWeb.do">
                                    返回品牌管理&gt;&gt;
                                </a>
                            </div>
                            <form action="getBrandAddSubmit.do" accept-charset="utf-8" class="addForm" method="post" id="form1" enctype="multipart/form-data">
                                <div class="brand-box clearfix mt10">
                                    <span class="tit fl">
                                        <%-- <span id="brandNameMsg">
                                            
                                        </span> --%>
                                        选择品牌：
                                        <%-- <span id="brandIdMsg">
                                            
                                        </span> --%>
                                    </span>
                                    <div class="chooseBrand fl">
                                        <input type="text" placeholder="输入关键词搜索品牌" class="view-SearchBrandInput"
                                        name="userBrandPojo.brandName" value="" onchange="selectBrand(this)">
                                        <i class="view-SearchBrandInputBtn searchBtn iconfont " onclick="selectBrand(this)">&#xf012c;</i>
                                        <select size="11" name="userBrandPojo.brandId" class="view-SelectBrandList" id="userBrandPojo.brandId" onchange="clickBrand(this)">
                                        <%-- <c:forEach items="${brandDicPojos }" var="s">
								        	<option value="${s.id }">${s.brand }</option>
								    	</c:forEach> --%>
                                        </select>
                                        <p class="errorInfo">
                                        </p>
                                        <p class="errorInfo">
                                        </p>
                                    </div>
                                    <div class="qqInfo fl">
                                        <p>
                                            找不到想要的品牌？联系客服。
                                        </p>
                                        <p>
                                            商家咨询：
                                            <span style="position:relative;top:7px;">
                                            <a target='_blank' href='http://crm2.qq.com/page/portalpage/wpa.php?uin=4001503677&aty=0&a=0&curl=&ty=1'><img alt="商家咨询" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/images/help_contact_d1.png"></a>
                                            </span>
                                        </p>
                                        <p>
                                            咨询电话：
                                            <span>
                                                400-150-3677
                                            </span>
                                        </p>
                                        <p>
                                            工作时间：周一到周六<br /> 9:00-12:00/13:30-17:30
                                        </p>
                                        <!-- <a class="red" style="margin-left:0px;" target="_blank" href="http://seller.beibei.com/help/brand_management.html">
                                            品牌申请被退回常见问题&gt;&gt;
                                        </a> -->
                                    </div>
                                    <span id="brandNameMsg">
                                            
                                    </span>
                                    <span id="brandIdMsg">
                                            
                                    </span>
                                </div>
                                <div class="brandName mt10">
                                    <span class="tit">
                                        已选品牌：
                                    </span>
                                    <span class="view-ChooseBrank">
                                    </span>
                                </div>
                                <div class="brandType mt10 clearfix">
                                    <span class="tit fl">
                                        <span id="grantTypeMsg">
                                            
                                        </span>
                                        授权类型：
                                    </span>
                                    <div class="brandTypeInfo fl">
                                        <label>
                                            <input type="radio" name="userBrandPojo.grantType" value="1" checked="checked">
                                            自有品牌
                                        </label>
                                        <label>
                                            <input type="radio" name="userBrandPojo.grantType" value="2">
                                            独家代理
                                        </label>
                                        <label>
                                            <input type="radio" name="userBrandPojo.grantType" value="3">
                                            一级代理
                                        </label>
                                        <label>
                                            <input type="radio" name="userBrandPojo.grantType" value="4">
                                            二级代理
                                        </label>
                                        <label>
                                            <input type="radio" name="userBrandPojo.grantType" value="5">
                                            三级代理
                                        </label>
                                        <p class="errorInfo">
                                        </p>
                                    </div>
                                </div>
                                <div class="brandDate mt10 clearfix">
                                    <span class="tit fl">
                                        <%-- <span id="dateStrMsg">
                                            
                                        </span> --%>
                                        品牌有效期/授权有效期：
                                    </span>
                                    <div class="brandDateInfo  fl">
                                        <input type="text" name="userBrandPojo.startDateStr" placeholder="开始日期" id="start-time" class="createtime-start view-CreatStartTime" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'end-time\')}'})" value="">
                                        -
                                        <input type="text" name="userBrandPojo.endDateStr" placeholder="结束日期" id="end-time" class="createtime-end view-CreatEndTime" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'start-time\')}'})" value="">
                                        <span>
                                            &nbsp;&nbsp;若为授权品牌，请按授权书的授权期限选择起止时间
                                        </span>
                                        <p class="errorInfo">
                                        </p>
                                    </div>
                                    <span id="startDateStrMsg">
                                            
                                    </span>
                                    <span id="endDateStrMsg">
                                            
                                    </span>
                                </div>
                                <div class="brandCategray mt10 clearfix">
                                    <span class="tit fl">
                                        <span>
                                            
                                        </span>
                                        主营类目：
                                    </span>
                                    <div class="brandCategrayInfo fl" style="width:666px;">
								        <%int i = 0; %>
                                        <c:forEach items="${productTypePojos }" var="p">
								        	<%i++;if(i == 1){ %>
								        	<label style="display:inline-block;">
                                            <input type="radio" name="userBrandPojo.mainCategory" value="${p.id }" checked="checked">
                                            ${p.name }</label>
                                            <%}else{ %>
								        	<label style="display:inline-block;">
                                            <input type="radio" name="userBrandPojo.mainCategory" value="${p.id }">
                                            ${p.name }</label>
                                            <%} %>
								    	</c:forEach>
                                        <p class="errorInfo">
                                        </p>
                                    </div>
                                </div>
                                <div class="brandRegBook mt10 clearfix">
                                    <span class="tit fl">
                                        <span>
                                            *
                                        </span>
                                        商标注册证/商标受理通知书：
                                    </span>
                                    <div class="fl picInfo">
                                        <div class="uploadify">
                                            <p class="uploadPreview_note">
                                                <i class="iconfont"></i>
                                                添加图片
                                            </p>
                                            <div class="uploadPreview_img" style="display:none;">
                                                <img style="width:120px;height:120px;">
                                            </div>
                                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="file1">
                                        </div>
                                        <%-- <span>
                                            &nbsp;&nbsp;若商标还在注册受理中，请提供商标受理通知书；若商标为个人所有，且为法人，请一并上传营业执照
                                        </span> --%>
                                        <p class="errorInfo">
                                        </p>
                                        <div class="view-ImgList picBorderInfo clearfix" num="10">
                                            <input type="hidden" class="input-PicUp1" name="trade_mark_license" value="">
                                        </div>
                                    </div>
                                </div>
                                <div class="brandImpower mt10 clearfix">
                                    <span class="tit fl">
                                        <span>
                                            *
                                        </span>
                                        品牌授权证书：
                                    </span>
                                    <div class="fl picInfo">
                                        <div class="uploadify">
                                            <p class="uploadPreview_note">
                                                <i class="iconfont">&#xf00f7;</i>
                                                添加图片
                                            </p>
                                            <div class="uploadPreview_img" style="display:none;">
                                                <img style="width:120px;height:120px;">
                                            </div>
                                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="file2">
                                        </div>
                                        <%-- <span>
                                            &nbsp;&nbsp; [
                                            <a href="http://b0.hucdn.com/files/seller/PinPai-ShouQuanShu-BeiBei.pdf"
                                            target="_blank">
                                                授权书模板
                                            </a>
                                            ] 若非一级代理，请上传各级代理的正规品牌授权文件或证明文件
                                        </span> --%>
                                        <p class="errorInfo">
                                        </p>
                                        <div class="view-ImgList picBorderInfo clearfix" num="10">
                                            <input type="hidden" class="input-PicUp2" name="cert" value="">
                                        </div>
                                    </div>
                                </div>
                                <div class="brandOther mt10 clearfix">
                                    <span class="tit fl">
                                        <span>
                                            *
                                        </span>
                                        质检报告/3C认证：
                                    </span>
                                    <div class="fl picInfo" style="width:650px;">
                                        <div class="uploadify">
                                            <p class="uploadPreview_note" style="line-height:120px;">
                                                <i class="iconfont">&#xf00f7;</i>
                                                添加图片
                                            </p>
                                            <div class="uploadPreview_img" style="display:none;">
                                                <img style="width:120px;height:120px;">
                                            </div>
                                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="file3">
                                        </div>                                        
                                        <p class="errorInfo">
                                        </p>
                                        <!-- <p>
                                            *质检报告及其他资质：
                                        </p>
                                        <p>
                                            包括但不限于：
                                        </p>
                                        <p>
                                            1. 每个品牌必须提供一份两年内的质检报告（一次性卫生用品要求一年内的质检报告）。
                                            <a href="#"
                                            target="_blank">
                                                查看详细说明&gt;&gt;
                                            </a>
                                        </p>
                                        <p>
                                            2. 符合强制性认证产品目录的商品（童车、童床、玩具类）必须提供CCC认证《强制性产品认证证书》。
                                        </p>
                                        <p>
                                            3. 进口商品需提供《中华人民共和国海关进口货物报送单》和《入境货物检验检疫证明》。
                                        </p>
                                        <p>
                                            4. 食品、美妆、洗护用品等特殊类目资质要求请
                                            <a href="#" target="_blank">
                                                查看明细&gt;&gt;
                                            </a>
                                        </p>
                                        <p class="red">
                                            请上传真实有效的品牌资质，若核实资质造假，将严格按照《淘竹马商家违规处罚规则》中“资料不实”进行处罚。
                                        </p> -->
                                    </div>
                                </div>
                                <button type="button" class="btn-submit view-BtnSubmit" id="sbutton">
                                    提交
                                </button>
                                <a class="btn-cancel" href="getBrandAddWeb.do">
                                    取消
                                </a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
        <script>
		$(function(){
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
		</script>
	</body>
</html>
<script>
var brandName =new tt.Field("品牌 ","userBrandPojo.brandName").setMsgId("brandNameMsg");
var brandId =new tt.Field("品牌 ","userBrandPojo.brandId").setMsgId("brandIdMsg");
//var grantType =new tt.Field("授权类型","userBrandPojo.grantType").setMsgId("grantTypeMsg");
var startDateStr =new tt.Field("开始日期","userBrandPojo.startDateStr").setMsgId("startDateStrMsg");
var endDateStr =new tt.Field("结束日期","userBrandPojo.endDateStr").setMsgId("endDateStrMsg");
tt.vf.req.add(brandName,brandId/* ,grantType */,startDateStr,endDateStr);
new tt.LV().set(0, 30).add();

$(document).ready(function() {
	$("#sbutton").click(function(){		
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
});

function selectBrand(t) {
	var v = $("input[name='userBrandPojo.brandName']").val();
	var o = "";
	if(v == ""){
		o = "option=0&";
	}
	$("select[name='userBrandPojo.brandId']").html("");
	$.ajax({
		type: "post",
		url: "brandDicListAll.do?"+o+"brandDicPojo.status=1&brandDicPojo.brand="+v,
		dataType: 'json',
		success: function (msg) {
			var o_msg = eval(msg);
			for (var i = 0; i < o_msg.length; i++) {
				var selectedStr = "";
                if(t.value == o_msg[i].brand){
                	selectedStr = "selected='selected'";
                }
				$("select[name='userBrandPojo.brandId']").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].brand + "</option>");
			}
		}
	});
}

function clickBrand(t) {
	var v = $("select[name='userBrandPojo.brandId']").find("option:selected").text();
	$("input[name='userBrandPojo.brandName']").val(v);
}
</script>