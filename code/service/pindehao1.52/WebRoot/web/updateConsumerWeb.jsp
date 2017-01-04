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
<title>淘竹马分销商</title>
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
	<div class="apply-form-title"></div>
	<form action="updateConsumerWeb.do" method="post" id="from1">
	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
	<input name="goUpdateConsumer.userId" id="sysDictId" value="${session.wuser.id}" class="inputText" type="hidden" >
	<input name="goUpdateConsumer.channel" id="sysDictId" value="0" class="inputText" type="hidden" >
	<input name="goUpdateConsumer.status" id="sysDictId" value="<s:property value='goUpdateConsumer.status'/>" class="inputText" type="hidden" >
	<input name="goUpdateConsumer.isread" id="sysDictId" value="0" class="inputText" type="hidden" >
    	<tr>
        	<td class="register-form-tableTxt">公司名称</td>
            <td><input name="goUpdateConsumer.company" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.company'/>" style="float: left;" /><span id="company"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">主营类目 </td>
            <td>
	            <select name="goUpdateConsumer.mainCategory" class="register-form-tableInput">
		            <option value="主营遥控玩具" <s:if test=" '主营遥控玩具' == goUpdateConsumer.mainCategory ">selected='selected'</s:if> >
		            	主营遥控玩具
		            </option>
		            <option value="主营早教玩具" <s:if test=" '主营早教玩具' == goUpdateConsumer.mainCategory ">selected='selected'</s:if> >
		            	主营早教玩具
		            </option>
		            <option value="主营过家家玩具" <s:if test=" '主营过家家玩具' == goUpdateConsumer.mainCategory ">selected='selected'</s:if> >
		            	主营过家家玩具
		            </option>
		            <option value="主营童车玩具" <s:if test=" '主营童车玩具' == goUpdateConsumer.mainCategory ">selected='selected'</s:if> >
		            	主营童车玩具
		            </option>
		            <option value="主营益智玩具" <s:if test=" '主营益智玩具' == goUpdateConsumer.mainCategory ">selected='selected'</s:if> >
		            	主营益智玩具
		            </option>
		            <option value="主营电动玩具" <s:if test=" '主营电动玩具' == goUpdateConsumer.mainCategory ">selected='selected'</s:if> >
		            	主营电动玩具
		            </option>
		            <!-- 
		            <option value="主营遥控玩具" >主营遥控玩具</option>
		            <option value="主营早教玩具" >主营早教玩具</option>
		            <option value="主营过家家玩具" >主营过家家玩具</option>
		            <option value="主营童车玩具" >主营童车玩具</option>
		            <option value="主营益智玩具" >主营益智玩具</option>
		            <option value="主营电动玩具" >主营电动玩具</option>
		             -->
	            </select>
            </td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">公司总人数 </td>
            <td>
	            <select name="goUpdateConsumer.groups" class="register-form-tableInput">
		            <option value="1" <s:if test=" 1 == goUpdateConsumer.groups ">selected="selected"</s:if> >
		            	1~5人
		            </option>
		            <option value="2" <s:if test=" 2 == goUpdateConsumer.groups ">selected="selected"</s:if> >
		            	6~20人
		            </option>
		            <option value="3" <s:if test=" 3 == goUpdateConsumer.groups ">selected="selected"</s:if> >
		            	21~50人
		            </option>
		            <option value="4" <s:if test=" 4 == goUpdateConsumer.groups ">selected="selected"</s:if> >
		            	51~100人
		            </option>
		            <option value="5" <s:if test=" 5 == goUpdateConsumer.groups ">selected="selected"</s:if> >
		            	100人以上
		            </option>
	            </select>
            </td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">销售平台</td>
            <td><input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput"  placeholder="例如:淘宝,京东,线下实体" value="<s:property value='goUpdateConsumer.platform'/>" style="float: left;" /><span id="platform"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">销售地区</td>
            <td><input name="goUpdateConsumer.salesArea" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.salesArea'/>" style="float: left;" /><span id="salesArea"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人</td>
            <td><input name="goUpdateConsumer.contact" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.contact'/>" style="float: left;" /><span id="contact"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人职务</td>
            <td><input name="goUpdateConsumer.duty" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.duty'/>" style="float: left;" /><span id="duty"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人Email</td>
            <td><input name="goUpdateConsumer.email" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.email'/>" style="float: left;" /><span id="email"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系QQ</td>
            <td><input name="goUpdateConsumer.QQ" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.QQ'/>" style="float: left;" /><span id="QQ"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系电话 </td>
            <td><input name="goUpdateConsumer.tel" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.tel'/>" style="float: left;" /><span id="tel"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">手机号码</td>
            <td><input name="goUpdateConsumer.phone" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.phone'/>" style="float: left;" /><span id="phone"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">传真号码</td>
            <td><input name="goUpdateConsumer.fax" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.fax'/>" /><span id="fax"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">网店链接 </td>
            <td><input name="goUpdateConsumer.webSite" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.webSite'/>" style="float: left;" /><span id="webSite"></span></td>
        </tr>
        <tr>
        	<td><div class="register-form-tableTxt">公司地址<!--  <font color="#df434e">*</font>  --></div></td>
        	<td>
        	<div class="consumer-apply-textarea"><textarea name="goUpdateConsumer.address" cols="" rows="" class="consumer-apply-textarea" style="float: left;" ><s:property value='goUpdateConsumer.address'/></textarea><span id="address" ></span></div>
        	</td>
        </tr>
    </table>
    
    </form>
    <!--  
    <div class="apply-agree"><input id="checkRadio" name="" type="radio" value="" /> <font color="#4c4c4c">我已同意</font> 分销服务条款</div>
    -->
    <div class="apply-button" id="sbutton" >修改</div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
var company =new tt.Field(" 公司名称","goUpdateConsumer.company").setMsgId("company");
var platform =new tt.Field(" 销售平台","goUpdateConsumer.platform").setMsgId("platform");
var salesArea =new tt.Field(" 销售地区","goUpdateConsumer.salesArea").setMsgId("salesArea");
var contact =new tt.Field(" 联系人","goUpdateConsumer.contact").setMsgId("contact");
var duty =new tt.Field(" 联系人职务","goUpdateConsumer.duty").setMsgId("duty");
var email =new tt.Field(" 联系人Email","goUpdateConsumer.email").setMsgId("email");
var QQ =new tt.Field(" 联系人QQ","goUpdateConsumer.QQ").setMsgId("QQ");
var tel =new tt.Field(" 联系电话","goUpdateConsumer.tel").setMsgId("tel");
var phone =new tt.Field(" 手机号码","goUpdateConsumer.phone").setMsgId("phone");
//var fax =new tt.Field(" 传真号码","goUpdateConsumer.fax").setMsgId("fax");
var webSite =new tt.Field(" 网店连接","goUpdateConsumer.webSite").setMsgId("webSite");
var address =new tt.Field(" 公司地址","goUpdateConsumer.address").setMsgId("address");

tt.vf.req.add(company,platform,salesArea,contact,duty,email,QQ,tel,phone,webSite,address);
tt.vf.email.add(email);
//tt.vf.email.add(email);
tt.vf.num.add(phone);
//tt.vf.num.add(fax);
tt.vf.num.add(QQ);
tt.vf.num.add(tel);
new tt.LV().set(0, 100).add(company);
new tt.LV().set(0, 50).add(platform);
new tt.LV().set(0, 50).add(salesArea);
new tt.LV().set(0, 10).add(contact);
new tt.LV().set(0, 10).add(duty);
new tt.LV().set(0, 40).add(email);
new tt.LV().set(0, 15).add(QQ);
new tt.LV().set(0, 20).add(tel);
new tt.LV().set(0, 11).add(phone);
//new tt.LV().set(0, 20).add(fax);
new tt.LV().set(0, 50).add(webSite);
new tt.LV().set(0, 800).add(address);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>