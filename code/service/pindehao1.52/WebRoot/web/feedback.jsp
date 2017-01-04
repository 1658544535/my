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
<link href="/css/faq.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="/js/base/operation/shop/shop.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script src="/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script type="text/javascript" src="js/_head.js"></script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马意见反馈</title>
<meta name="keywords" content="品牌玩具分销 玩具分销平台  专业分销玩具平台 电商方案 下载玩具数据包 玩具新品快订 玩具采购囤货 玩具销售渠道  玩具售后质量 玩具电商渠道" />
<meta name="description" content="网站提供一系列服务，相关业务咨询可以咨询客服。提供意见反馈栏目" />

</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<!--<div class="logo02"></div>-->
	<a href="goIndexWeb.do" class="logo"></a>
	<form action="goSearch.do" id="idform" method="post">
	<div class="search"><input id="searchkey" name="searchkey" type="text" value="请简单描述你的问题" onfocus="this.value=''" onblur="if(this.value==''){this.value='请简单描述你的问题'}" class="search-input"/><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form> 
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav">
    	<ul>
        	<li><a href="goHelpWeb.do">首 页</a></li>
            <li><a href="goSelfServiceWeb.do">自主服务</a></li>
            <li><a href="goFaqWeb.do">常见问题</a></li>
            <li><a href="goContact.do">联系客服 </a></li>
            <li><a href="goFeedBackWeb.do"><span>意见反馈</span></a></li>
        </ul>
    </div>
</div>

<div class="register-form">
	<form action="feedBackAdd.do" method="post" id="from1">
	<input type="hidden" name="feedBackPojo.userId" value="${session.wuser.id}">
	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
		<tr>
			<td colspan="2"><span>请填写邮箱及联系方式信息有助于帮忙我们解决您提出的问题,并及时与你沟通处理结果。</span></td>
		</tr>
		<tr>
	        	<td class="register-form-tableTxt">类型 :</td>
	            <td>
	             	&nbsp;&nbsp;<select name="feedBackPojo.type" class="register-form-tableInput"  style="float:left" >
	             	<option name="feedBackPojo.type" id="complaints" value="1">投诉</option>
	             	<option name="feedBackPojo.type" id="advice" value="2">建议</option>
	             	</select>
	            </td>
	     </tr>
		<!--
         <tr>
        	<td class="register-form-tableTxt">用户名：</td>
            <td><input name="feedBackPojo.name" type="text" class="register-form-tableInput" style="float:left"/><span id="name1"></span></td>
        </tr>
        -->
        <tr>
        	<td class="register-form-tableTxt">邮箱：</td>
            <td><input name="feedBackPojo.email" type="text" class="register-form-tableInput" style="float:left"/><span id="email"></span></td>
        </tr>
        <!--
        <tr>
	        	<td class="register-form-tableTxt">省 :</td>
	            <td>
	             	&nbsp;&nbsp;<select id="province" name="feedBackPojo.province" class="register-form-tableInput"  style="float:left"></select>
	            </td>
	    </tr>
	    <tr>
	        	<td class="register-form-tableTxt">城市 :</td>
	            <td>
	             	&nbsp;&nbsp;<select id="city" name="feedBackPojo.city" class="register-form-tableInput"  style="float:left" ></select>
	            </td>
	     </tr>
	     <tr>
	        	<td class="register-form-tableTxt">区域 :</td>
	            <td>
	             	&nbsp;&nbsp;<select id="area" name="feedBackPojo.area2" class="register-form-tableInput"   style="float:left"></select>
	            </td>
        </tr>
        -->
       <!-- <tr>
        	<td class="register-form-tableTxt">所在地区：</td>
            <td><input name="feedBackPojo.area" type="text" class="register-form-tableInput" style="float:left"/><span id="area"></span></td>
        </tr>-->
        
        <tr>
        	<td class="register-form-tableTxt">联系电话：</td>
            <td><input name="feedBackPojo.telephone" type="text" class="register-form-tableInput" style="float:left"/><span id="telephone"></span><br/><br/><br/></td>
        </tr>
        <tr>
       	<td class="register-form-tableTxt">意见及建议：</td>
        <td><textarea name="feedBackPojo.content" cols="" rows="" style="float:left" class="apply-textarea" ></textarea><span id="content"></span></td>
         </tr>
    </table>
    </form>
    
    <div class="apply-button" id="sbutton" >提交建议</div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
var name1 =new tt.Field("用户名 ","feedBackPojo.name").setMsgId("name1");
var email =new tt.Field("用户邮箱","feedBackPojo.email").setMsgId("email");
var telephone =new tt.Field("联系电话 ","feedBackPojo.telephone").setMsgId("telephone");
var content =new tt.Field("意见及建议","feedBackPojo.content").setMsgId("content");

tt.vf.req.add(name1,email,telephone,content);
tt.vf.email.add(email);
tt.vf.num.add(telephone);
new tt.LV().set(0, 20).add(name1);
new tt.LV().set(0, 30).add(email);
new tt.LV().set(0, 15).add(telephone);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	
	$(document).ready(function() {
		
		select1();
		$("#city").append("<option value=''>- 请选择 -</option>");
		$("#area").append("<option value=''>- 请选择 -</option>");
		$('#province').bind("change", select2);
        $('#city').bind("change", select3);
        
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${deliveryAddressPojo.province}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#province").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
				if("${deliveryAddressPojo.province}"!=null && "${deliveryAddressPojo.province}"!=""){
					select2();
				}
			}
		})
	};
	
	function select2() {
        $("#city").html("");
        $("#city").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
            dataType: 'json',
            success: function (msg) {
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	var selectedStr = "";
                	if("${deliveryAddressPojo.city}" == o_msg[i].id){
                		selectedStr = "selected='selected'";
                	}
                    $("#city").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                }
                if("${deliveryAddressPojo.city}"!=null && "${deliveryAddressPojo.city}"!=""){
					select3();
				}
            }
        })
    };
    
    function select3() {
        $("#area").html("");
        $("#area").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
            dataType: 'json',
            success: function (msg) {
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	var selectedStr = "";
                	if("${deliveryAddressPojo.area}" == o_msg[i].id){
                		selectedStr = "selected='selected'";
                	}
                    $("#area").append("<option value=" + o_msg[i].id + " "+selectedStr+"> " + o_msg[i].name + "</option>");
                }
            }
        })
    };

</script>