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
<title>竹马分销平台</title>
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
	<div class="register-txt">竹马分销 |<span class="register-txt02">申请分销</span></div>
</div>

<div class="register-form">
	<div class="apply-form-title">分销商基本信息</div>
	<form action="addConsumer.do" method="post" id="from1">
	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
	<input name="consumer.userId" id="sysDictId" value="${session.wuser.id}" class="inputText" type="hidden" >
	<input name="consumer.channel" id="sysDictId" value="0" class="inputText" type="hidden" >
	<input name="consumer.status" id="sysDictId" value="0" class="inputText" type="hidden" >
	<input name="consumer.isread" id="sysDictId" value="0" class="inputText" type="hidden" >
    	<tr>
        	<td class="register-form-tableTxt">店铺名称</td>
            <td><input name="consumer.company" type="text" class="register-form-tableInput" style="float:left"/><span id="company"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">主营类目 </td>
            <td>&nbsp;&nbsp;<select name="consumer.mainCategory" class="register-form-tableInput" style="float:left">
            <option value="1" >遥控/电动玩具</option>
            <option value="2" >早教/音乐玩具</option>
            <option value="3" >过家家玩具</option>
            <option value="4" >童车玩具</option>
            <option value="5" >益智玩具</option>
            <option value="6" >其他玩具</option>
            </select></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">运营总人数 </td>
            <td>&nbsp;&nbsp;<select name="consumer.groups" class="register-form-tableInput" style="float:left">
            <option value="1" >1~5人</option>
            <option value="2" >6~20人</option>
            <option value="3" >21~50人</option>
            <option value="4" >51~100人</option>
            <option value="5" >100人以上</option>
            </select></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">销售平台</td>
            <td><input name="consumer.platform" type="text" value="淘宝网" onfocus="this.value=''" onblur="if(this.value==''){this.value='淘宝网'}"  class="register-form-tableInput" style="float:left"/><span id="platform"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">销售地区</td>
            <td><input name="consumer.salesArea" type="text" class="register-form-tableInput" style="float:left"/><span id="salesArea"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人</td>
            <td><input name="consumer.contact" type="text" class="register-form-tableInput" style="float:left"/><span id="contact"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人职务</td>
            <td><input name="consumer.duty" type="text" class="register-form-tableInput" style="float:left"/><span id="duty"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系人Email</td>
            <td><input name="consumer.email" type="text" class="register-form-tableInput" style="float:left"/><span id="email"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系QQ</td>
            <td><input name="consumer.QQ" type="text" class="register-form-tableInput" style="float:left"/><span id="QQ"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">联系电话 </td>
            <td><input name="consumer.tel" type="text" class="register-form-tableInput" style="float:left"/><span id="tel"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">手机号码</td>
            <td><input name="consumer.phone" type="text" class="register-form-tableInput" style="float:left"/><span id="phone"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">传真号码</td>
            <td><input name="consumer.fax" type="text" class="register-form-tableInput" style="float:left"/><span id="fax"></span></td>
        </tr>
        <tr>
        <td class="register-form-tableTxt">店铺类型</td>
        <td><input type="radio" id="checkR" name="applytype" value="1" onClick="show();">实体铺&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="checkR" name="applytype" value="2" onClick="show();">网店</td>
        </tr>
        <tr id="test1" style="display: none">
        	<td class="register-form-tableTxt">网店URL </td>
            <td><input name="consumer.webSite" type="text" class="register-form-tableInput" id="webUrl"  style="float:left"/><span id="webSite"></span></td>
        </tr>
        <tr id="test2" style="display: none" >
        	<td class="register-form-tableTxt">公司地址 </td>
            <td><input name="consumer.address" type="text" class="register-form-tableInput" id="address" style="float:left"/><span id="address"></span></td>
        </tr>
    </table>
    
    </form>
    </br>
    <div class="apply-agree" style="width: 240px;"><input id="checkRadio" name="" type="radio" value="" /> <font color="#4c4c4c">我已同意</font><a href="consumerAgreement.do" target="blank">&nbsp;&nbsp;分销服务条款</a>
    <a href="consumerAgreements.do" target="blank">&nbsp;&nbsp;分销商协议</a></div>
    <div class="apply-button" id="sbutton" >申请分销</div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
var company =new tt.Field(" 公司名称 ","consumer.company").setMsgId("company");
var platform =new tt.Field(" 销售平台 ","consumer.platform").setMsgId("platform");
var salesArea =new tt.Field(" 销售地区","consumer.salesArea").setMsgId("salesArea");
var contact =new tt.Field(" 联系人 ","consumer.contact").setMsgId("contact");
var duty =new tt.Field(" 联系人职务","consumer.duty").setMsgId("duty");
var email =new tt.Field(" 联系人Email","consumer.email").setMsgId("email");
var QQ =new tt.Field(" 联系人QQ","consumer.QQ").setMsgId("QQ");
var tel =new tt.Field(" 联系电话","consumer.tel").setMsgId("tel");
var phone =new tt.Field(" 手机号码","consumer.phone").setMsgId("phone");
var fax =new tt.Field(" 传真号码","consumer.fax").setMsgId("fax");
var webSite =new tt.Field(" 网店连接","consumer.webSite").setMsgId("webSite");
var address =new tt.Field(" 公司地址","consumer.address").setMsgId("address");

tt.vf.req.add(company,platform,salesArea,contact,email,QQ,phone);
tt.vf.email.add(email);
tt.vf.email.add(email);
tt.vf.num.add(phone);
tt.vf.num.add(fax);
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
new tt.LV().set(0, 20).add(fax);

	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
			var ss="";
			var zt = document.getElementsByName("applytype");
			for(var i=0;i<zt.length;i++){
				if(zt[i].checked) {
				
				 ss = zt[i].value;
				
				}
				//----------------------------------------------
				if(ss == "1"){
				document.getElementById('webUrl').value='';
				if(document.getElementById('address').value==''){
				alert("公司地址不允许为空");
			    return false;
			}
			}
			//----------------------------------------------
			if(ss == "2"){
			document.getElementById('address').value='';
				if(document.getElementById('webUrl').value==''){
				alert("网店URL不允许为空");
			    return false;
			}
			
			}
			//----------------------------------------------
			
			}
			//alert(ss);
			if(ss==null||ss==""){
			var a = "zero";
			}
			
			if(a == "zero"){
			alert("请选择商铺类型");
			return false;
	
		
			}
			if (document.getElementById("checkRadio").checked) {
				document.getElementById("from1").submit();
					}
				else{
					alert("请选择同意条款");
				}
			}
			
			
		});
	});
	
function show(){
	//兼容Ie火狐浏览器
	//var evt=evt || window.event;   
	var evt = window.event || arguments.callee.caller.arguments[0];
    var e =evt.srcElement || evt.target;	
     if(e.value=="2")
     {
     document.getElementById('test2').style.display='none';
     document.getElementById('test1').style.display='';
     }
	if(e.value=="1")
     {
     document.getElementById('test2').style.display='';
     document.getElementById('test1').style.display='none';
     
	 }
}
</script>