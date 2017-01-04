<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<title>淘竹马分销平台</title>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script src="/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script type="text/javascript" src="js/_head.js"></script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="register-title">
	<div class="register-txt">淘竹马分销 |<span class="register-txt02">申请供货</span></div>
</div>

<div class="register-form">
	<div class="apply-form-title-fu" >公司基本信息</div>
	<form action="addManufacturer.do" method="post" id="from1" enctype="multipart/form-data">
	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table" width="606">
	<input name="manufacturer.userId" id="sysDictId" value="${session.wuser.id}" class="inputText" type="hidden" >
	<input name="manufacturer.channel" id="sysDictId" value="0" class="inputText" type="hidden" >
	<input name="manufacturer.status" id="sysDictId" value="0" class="inputText" type="hidden" >
	<input name="manufacturer.isread" id="sysDictId" value="0" class="inputText" type="hidden" >
    	<tr>
        	<td class="register-form-tableTxt">公司名称</td>
            <td><input name="manufacturer.company" type="text" class="register-form-tableInput" style="float:left"/><span id="company"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">主营类目 </td>
            <td>&nbsp;&nbsp;<select name="manufacturer.mainCategory" class="register-form-tableInput" style="float:left">
            <option value="1" >遥控/电动玩具</option>
            <option value="2" >早教/音乐玩具</option>
            <option value="3" >过家家玩具</option>
            <option value="4" >童车玩具</option>
            <option value="5" >益智玩具</option>
            <option value="6" >其他玩具</option>
            </select></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">公司总人数 </td>
            <td>&nbsp;&nbsp;<select name="manufacturer.scale" class="register-form-tableInput" style="float:left">
            <option value="1" >小于50人</option>
            <option value="2" >50~100人</option>
            <option value="3" >100~150人</option>
            <option value="4" >150~200人</option>
            <option value="5" >200~300人</option>
            <option value="6" >大于300人</option>
            </select></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">自营品牌</td>
            <td><input name="manufacturer.brand" type="text" class="register-form-tableInput" style="float:left"/><span id="brand"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">销售市场</td>
            <td><input name="manufacturer.salesArea" type="text"  value="淘宝网" onfocus="this.value=''" onblur="if(this.value==''){this.value='淘宝网'}" class="register-form-tableInput" style="float:left"/><span id="salesArea"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人</td>
            <td><input name="manufacturer.contact" type="text" class="register-form-tableInput" style="float:left"/><span id="contact"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人职务</td>
            <td><input name="manufacturer.duty" type="text" class="register-form-tableInput" style="float:left"/><span id="duty"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人Email</td>
            <td><input name="manufacturer.email" type="text" class="register-form-tableInput" style="float:left"/><span id="email"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系QQ</td>
            <td><input name="manufacturer.QQ" type="text" class="register-form-tableInput" style="float:left"/><span id="QQ"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">手机号码</td>
            <td><input name="manufacturer.phone" type="text" class="register-form-tableInput" style="float:left"/><span id="phone"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">传真号码</td>
            <td><input name="manufacturer.fax" type="text" class="register-form-tableInput" style="float:left"/></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">网店连接 </td>
            <td><input name="manufacturer.webSite" type="text" class="register-form-tableInput" style="float:left"/><span id="webSite"></span></td>
        </tr>
        <tr >
        	<td class="register-form-tableTxt"><div style="margin-top:-30px;">营业执照</div> </td>
            <td><input name="upfile" type="file" class="register-form-tableInput" style="border:0;"><br><p style=" color:#ff0000;">(支持jpg、png格式，图片像素大小：800x600，请勿超过200k)</p></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt"><div style="margin-top:-30px;">3c认证证书</div> </td>
            <td><input name="upfile2" type="file" class="register-form-tableInput" style="border:0;"><br><p style="color:#ff0000;">(支持jpg、png格式，图片像素大小：640x640，请勿超过200k)</p></td>
        </tr>

         <tr>
        	<td class="register-form-tableTxt">公司地址 </td>
            <td><input name="manufacturer.address" type="text" class="register-form-tableInput" style="float:left"/><span id="address"></span></td>
        </tr>
    </table>
    
    <div class="apply-form-title" >
		 <span style="float:left;">主营产品</span>
   		 <div class="apply-form-textarea" style="float:left; margin-left:10px;">
			<textarea name="manufacturer.mainProduct" cols="57" rows="5" style="border:1px solid #dfdfdf;"></textarea>
			<!--<span id="ttTalentReqStarId11" class="talentReqStar" style="float:left">*</span>-->
			<span id="mainProduct"></span>
		</div> 
	</div>
	
    <div class="apply-form-title" > 
		<span style="float:left;">公司简介</span>
		<div class="apply-form-textarea" style="float:left; margin-left:10px;">
			<textarea name="manufacturer.content" cols="" rows="" class="apply-textarea"></textarea>
			<!--<span id="ttTalentReqStarId12" class="talentReqStar">*</span>-->
			<span id="content"></span>
	   </div>
	</div>
    
    </form>
    <div class="apply-agree"><input  id="checkRadio" name="" type="radio" value="" /> <font color="#4c4c4c">我已同意</font><a href="manufacturerAgreement.do" target="blank"> &nbsp; &nbsp; 供货服务条款</a></div>
    <div class="apply-button" id="sbutton" >申请供货</div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
var company =new tt.Field(" 公司名称 ","manufacturer.company").setMsgId("company");
var brand =new tt.Field(" 自营品牌 ","manufacturer.brand").setMsgId("brand");
var salesArea =new tt.Field(" 销售市场","manufacturer.salesArea").setMsgId("salesArea");
var contact =new tt.Field(" 联系人 ","manufacturer.contact").setMsgId("contact");
var duty =new tt.Field(" 联系人职务","manufacturer.duty").setMsgId("duty");
var email =new tt.Field(" 联系人Email","manufacturer.email").setMsgId("email");
var QQ =new tt.Field(" 联系人QQ","manufacturer.QQ").setMsgId("QQ");
var phone =new tt.Field(" 手机号码","manufacturer.phone").setMsgId("phone");
var webSite =new tt.Field(" 网店连接","manufacturer.webSite").setMsgId("webSite");
var address =new tt.Field(" 公司地址","manufacturer.address").setMsgId("address");
var mainProduct =new tt.Field(" 主营产品","manufacturer.mainProduct").setMsgId("mainProduct");
var content =new tt.Field(" 公司简介","manufacturer.content").setMsgId("content");

tt.vf.req.add(company,brand,salesArea,contact,duty,email,QQ,phone,address,content,mainProduct);
tt.vf.email.add(email);
tt.vf.num.add(phone);
tt.vf.num.add(QQ);
new tt.LV().set(0, 100).add(company);
new tt.LV().set(0, 100).add(brand);
new tt.LV().set(0, 50).add(salesArea);
new tt.LV().set(0, 10).add(contact);
new tt.LV().set(0, 10).add(duty);
new tt.LV().set(0, 25).add(email);
new tt.LV().set(0, 11).add(QQ);
new tt.LV().set(0, 11).add(phone);
new tt.LV().set(0, 100).add(webSite);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
			if (document.getElementById("checkRadio").checked) {
				document.getElementById("from1").submit();
				}else{
					alert("请选择同意条款");
				}
			}
		});
	});
	

</script>