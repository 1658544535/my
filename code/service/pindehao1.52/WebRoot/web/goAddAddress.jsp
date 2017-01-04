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
	<div class="register-txt">淘竹马分销平台|<span class="register-txt02">添加我的收货地址</span></div>
</div>

<div class="register-form">
	<!--  <div class="apply-form-title">收货地址信息</div>	-->
	<form action="addDeliveryAddress.do" method="post" id="from1">
	
		<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
		<input name="deliveryAddressPojo.userId" id="sysDictId" value="${session.wuser.id}" class="inputText" type="hidden" >
		<input name="cidsStr" id="cidsStr" value="${cidsStr}" class="inputText" type="hidden" >
	        <tr>
	        	<td class="register-form-tableTxt">省 </td>
	            <td>
	             	&nbsp;&nbsp;<select id="province" name="deliveryAddressPojo.province" class="register-form-tableInput" ></select>
	            </td>
	        </tr>
	        <tr>
	        	<td class="register-form-tableTxt">城市 </td>
	            <td>
	             	&nbsp;&nbsp;<select id="city" name="deliveryAddressPojo.city" class="register-form-tableInput" ></select>
	            </td>
	        </tr>
	        <tr>
	        	<td class="register-form-tableTxt">区域 </td>
	            <td>
	             	&nbsp;&nbsp;<select id="area" name="deliveryAddressPojo.area" class="register-form-tableInput" ></select>
	            </td>
        	</tr>
        	<tr>
	        	<td class="register-form-tableTxt">收货地址</td>
	            <td><input name="deliveryAddressPojo.address" type="text" class="register-form-tableInput" style="float: left;" /><span id="address"></span></td>
        	</tr>
        	
        	<tr>
        	    <td class="register-form-tableTxt">收货人</td>
                <td><input name="deliveryAddressPojo.consignee" type="text" class="register-form-tableInput" style="float: left;" /><span id="consignee"></span></td>
    	    </tr>
        	<tr>
	        	<td class="register-form-tableTxt">收货人电话</td>
	            <td><input name="deliveryAddressPojo.consigneePhone" type="text" class="register-form-tableInput" style="float: left;" /><span id="consigneePhone"></span></td>
        	</tr>
        	<tr>
	        	<td class="register-form-tableTxt">邮编</td>
	            <td>&nbsp;&nbsp;<input name="deliveryAddressPojo.postcode" type="text" class="register-form-tableInput" style="float: left;" /><span id="zipCode"></span></td>
        	</tr>
        	<!--  
        	<tr>
	        	<td class="register-form-tableTxt">排序</td>
	            <td>&nbsp;&nbsp;<input name="deliveryAddressPojo.sorting" type="text" class="register-form-tableInput"/><span id=""></span></td>
        	</tr>
        	-->
	    </table>
    
    </form>
    
    <div class="apply-button" id="sbutton" >添加</div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
var province=new tt.Field("省 ","deliveryAddressPojo.province").setMsgId("province");
var city=new tt.Field("城市 ","deliveryAddressPojo.city").setMsgId("city");
var area=new tt.Field("区域","deliveryAddressPojo.area").setMsgId("area");
var consignee =new tt.Field("收货人 ","deliveryAddressPojo.consignee").setMsgId("consignee");
var consigneePhone =new tt.Field("收货人电话","deliveryAddressPojo.consigneePhone").setMsgId("consigneePhone");
var address =new tt.Field("收货地址","deliveryAddressPojo.address").setMsgId("address");
var zipCode =new tt.Field("邮政编码","deliveryAddressPojo.postcode").setMsgId("zipCode");
tt.vf.req.add(consignee,consigneePhone,address,province,city,area);
tt.vf.num.add(consigneePhone);
tt.vf.num.add(zipCode);
new tt.LV().set(0, 20).add(consignee);
new tt.LV().set(0, 11).add(consigneePhone);
new tt.LV().set(0, 100).add(address);
new tt.LV().set(0, 10).add(zipCode);
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