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
<title>淘竹马首页</title>
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
	<div class="register-txt">淘竹马分销 |<span class="register-txt02">修改个人信息</span></div>
</div>

<div class="register-form">
	<!--  <div class="apply-form-title">公司基本信息</div>	-->
	<form action="updateManufacturerWeb.do" method="post" id="from1">
	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
	<input name="goUpdateManufacturer.userId" id="sysDictId" value="${session.wuser.id}" class="inputText" type="hidden" >
	<input name="goUpdateManufacturer.channel" id="sysDictId" value="0" class="inputText" type="hidden" >
	<input name="goUpdateManufacturer.status" id="sysDictId" value="<s:property value='goUpdateManufacturer.status'/>" class="inputText" type="hidden" >
	<input name="goUpdateManufacturer.isread" id="sysDictId" value="0" class="inputText" type="hidden" >
    	<tr>
        	<td class="register-form-tableTxt">公司名称</td>
            <td><input name="goUpdateManufacturer.company" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.company'/>" style="float: left;"/><span id="company"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">主营类目 </td>
            <td>
	            <select name="goUpdateManufacturer.mainCategory" class="register-form-tableInput">
	             <option value="主营遥控玩具" <s:if test=" '主营遥控玩具' == goUpdateManufacturer.mainCategory ">selected='selected'</s:if> >
		            	主营遥控玩具
		            </option>
		            <option value="主营早教玩具" <s:if test=" '主营早教玩具' == goUpdateManufacturer.mainCategory ">selected='selected'</s:if> >
		            	主营早教玩具
		            </option>
		            <option value="主营过家家玩具" <s:if test=" '主营过家家玩具' == goUpdateManufacturer.mainCategory ">selected='selected'</s:if> >
		            	主营过家家玩具
		            </option>
		            <option value="主营童车玩具" <s:if test=" '主营童车玩具' == goUpdateManufacturer.mainCategory ">selected='selected'</s:if> >
		            	主营童车玩具
		            </option>
		            <option value="主营益智玩具" <s:if test=" '主营益智玩具' == goUpdateManufacturer.mainCategory ">selected='selected'</s:if> >
		            	主营益智玩具
		            </option>
		            <option value="主营电动玩具" <s:if test=" '主营电动玩具' == goUpdateManufacturer.mainCategory ">selected='selected'</s:if> >
		            	主营电动玩具
		            </option>
		            <!--  
			            <option value="1" >主营遥控玩具</option>
			            <option value="2" >主营早教玩具</option>
			            <option value="3" >主营过家家玩具</option>
			            <option value="4" >主营童车玩具</option>
			            <option value="5" >主营益智玩具</option>
			            <option value="6" >主营电动玩具</option>
			       -->
	            </select>
            </td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">公司总人数 </td>
            <td>
	            <select name="goUpdateManufacturer.scale" class="register-form-tableInput">
	            	<option value="1" <s:if test=" 1 == goUpdateManufacturer.scale ">selected="selected"</s:if> >
		            	小于50人
		            </option>
		            <option value="2" <s:if test=" 2 == goUpdateManufacturer.scale ">selected="selected"</s:if> >
		            	50~100人
		            </option>
		            <option value="3" <s:if test=" 3 == goUpdateManufacturer.scale ">selected="selected"</s:if> >
		            	100~150人
		            </option>
		            <option value="4" <s:if test=" 4 == goUpdateManufacturer.scale ">selected="selected"</s:if> >
		            	150~200人
		            </option>
		            <option value="5" <s:if test=" 5 == goUpdateManufacturer.scale ">selected="selected"</s:if> >
		            	200~300人
		            </option>
		             <option value="6" <s:if test=" 6 == goUpdateManufacturer.scale ">selected="selected"</s:if> >
		            	大于300人
		            </option>
		            <!--  
			            <option value="1" >小于50人</option>
			            <option value="2" >50~100人</option>
			            <option value="3" >100~150人</option>
			            <option value="4" >150~200人</option>
			            <option value="5" >200~300人</option>
			            <option value="6" >大于300人</option>
			        -->
	            </select>
            </td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">自营品牌</td>
            <td><input name="goUpdateManufacturer.brand" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.brand'/>" style="float: left;"/><span id="brand"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">销售市场</td>
            <td><input name="goUpdateManufacturer.salesArea" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.salesArea'/>" style="float: left;"/><span id="salesArea"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人</td>
            <td><input name="goUpdateManufacturer.contact" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.contact'/>" style="float: left;"/><span id="contact"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人职务</td>
            <td><input name="goUpdateManufacturer.duty" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.duty'/>" style="float: left;"/><span id="duty"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人Email</td>
            <td><input name="goUpdateManufacturer.email" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.email'/>" style="float: left;"/><span id="email"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系QQ</td>
            <td><input name="goUpdateManufacturer.QQ" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.QQ'/>" style="float: left;"/><span id="QQ"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">手机号码</td>
            <td><input name="goUpdateManufacturer.phone" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.phone'/>" style="float: left;"/><span id="phone"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">传真号码</td>
            <td><input name="goUpdateManufacturer.fax" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.fax'/>" style="float: left;"/><span id="fax"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">网店链接 </td>
            <td><input name="goUpdateManufacturer.webSite" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.webSite'/>" style="float: left;"/><span id="webSite"></span></td>
        </tr>
    	<tr>
    		<td><div class="register-form-tableTxt">公司地址</div></td>
    		<td> <div class="apply-form-textarea"><textarea name="goUpdateManufacturer.address" cols="29" rows="3" style="float: left;"><s:property value='goUpdateManufacturer.address'/></textarea><span id="address"></span></div></td>
    	</tr>
    	<tr>
    		<td><div class="register-form-tableTxt">主营产品</div></td>
    		<td><div class="apply-form-textarea"><textarea name="goUpdateManufacturer.mainProduct" cols="29" rows="3" style="float: left;"><s:property value='goUpdateManufacturer.mainProduct'/></textarea><span id="mainProduct"></span></div></td>
    	</tr>
   		<tr>
    		<td><div class="register-form-tableTxt">公司简介</div></td>
    		<td><div class="apply-form-textarea"><textarea name="goUpdateManufacturer.content" cols="29" rows="3" style="float: left;"><s:property value='goUpdateManufacturer.content'/></textarea><span id="content"></span></div></td>
    	</tr>
    
    	</table>
    </form>
    
    <!--  <div class="apply-agree"><input  id="checkRadio" name="" type="radio" value="" /> <font color="#4c4c4c">我已同意</font> 供货服务条款</div>	-->
    <div class="apply-button" id="sbutton" >修改</div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
var company =new tt.Field(" 公司名称 ","goUpdateManufacturer.company").setMsgId("company");
var brand =new tt.Field(" 自营品牌","goUpdateManufacturer.brand").setMsgId("brand");
var salesArea =new tt.Field(" 销售市场","goUpdateManufacturer.salesArea").setMsgId("salesArea");
var contact =new tt.Field(" 联系人","goUpdateManufacturer.contact").setMsgId("contact");
var duty =new tt.Field(" 联系人职务","goUpdateManufacturer.duty").setMsgId("duty");
var email =new tt.Field(" 联系人Email","goUpdateManufacturer.email").setMsgId("email");
var QQ =new tt.Field(" 联系人QQ","goUpdateManufacturer.QQ").setMsgId("QQ");
var phone =new tt.Field(" 手机号码","goUpdateManufacturer.phone").setMsgId("phone");
//var fax =new tt.Field(" 传真号码","goUpdateManufacturer.fax").setMsgId("fax");
var webSite =new tt.Field(" 网店连接","goUpdateManufacturer.webSite").setMsgId("webSite");
var address =new tt.Field(" 公司地址","goUpdateManufacturer.address").setMsgId("address");
var mainProduct =new tt.Field(" 主营产品","goUpdateManufacturer.mainProduct").setMsgId("mainProduct");
var content =new tt.Field(" 公司简介","goUpdateManufacturer.content").setMsgId("content");

tt.vf.req.add(company,brand,salesArea,contact,duty,email,QQ,phone,address,content,mainProduct);
tt.vf.email.add(email);
tt.vf.num.add(phone);
//tt.vf.num.add(fax);
tt.vf.num.add(QQ);
new tt.LV().set(0, 100).add(company);
new tt.LV().set(0, 100).add(brand);
new tt.LV().set(0, 50).add(salesArea);
new tt.LV().set(0, 10).add(contact);
new tt.LV().set(0, 10).add(duty);
new tt.LV().set(0, 25).add(email);
new tt.LV().set(0, 11).add(QQ);
new tt.LV().set(0, 11).add(phone);
//new tt.LV().set(0, 20).add(fax);
new tt.LV().set(0, 100).add(webSite);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>