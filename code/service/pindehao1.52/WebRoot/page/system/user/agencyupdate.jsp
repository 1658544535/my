<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	select1();
	$('#province').bind("change", select2);
    $('#city').bind("change", select3);
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
                if("${agencyPojo.province}" == o_msg[i].id){
                	selectedStr = "selected='selected'";
                }
				$("#province").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
			}
			if("${agencyPojo.province}"!=null && "${agencyPojo.province}"!=""){
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
                	if("${agencyPojo.city}" == o_msg[i].id){
                		selectedStr = "selected='selected'";
                	}
                    $("#city").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                }
                if("${agencyPojo.city}"!=null && "${agencyPojo.city}"!=""){
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
                	if("${agencyPojo.area}" == o_msg[i].id){
                		selectedStr = "selected='selected'";
                	}
                    $("#area").append("<option value=" + o_msg[i].id + " "+selectedStr+"> " + o_msg[i].name + "</option>");
                }
            }
        })
    };
</script>


</head>
<body>

<div class="sub_wrap">
  <div class="s_nav"><a>代理商账户管理</a> &gt; <a href="agency.do" >账户信息</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateAgency.do?os=${os}" method="post" id="from1"   enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="agency.agencyId" id="agencyId" value="${agencyPojo.agencyId}" class="inputText" type="hidden" >
    <input name="agency.userId" id="userId" value="${agencyPojo.userId}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">公司名称:</td>	
        <td width="35%">
        <input type="text" name="agency.company"  value="${agencyPojo.company}" class="floatLeft" id="ticketName"><span id="company"></span></td>
       
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%">
        <select name="agency.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${agencyPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select>
	    <div id="status_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">家庭地址:</td>
        <td width="35%"><input type="text" name="agency.addressHome"  value="${agencyPojo.addressHome}" class="floatLeft" id="ticketName"><span id="addressHome"></span></td>
        
        <td align="right" class="grey" width="15%">公司地址:
        </td><td width="35%"><input type="text" name="agency.addressCompany"  value="${agencyPojo.addressCompany}" class="floatLeft" id="ticketName"><span id="addressCompany"></span></td>
       
      </tr>
      <tr>
        <td align="right" class="grey"  width="15%">来源渠道:</td>
        <td width="35%"><select name="agency.channel" id="ticketType"  class="floatLeft">
        					<c:forEach items="${channel}" var="channel">
										<option value="${channel.value}"<c:if test="${agencyPojo.channel==channel.value}">selected="selected" </c:if>>${channel.name}</option>
								</c:forEach>
				    </select><div id="channel_mgId"></div></td>
        
        <td align="right" class="grey"  width="15%">是否查看:</td>
        <td width="35%"><select name="agency.isread" id="ticketType"  class="floatLeft">
							<c:forEach items="${isread}" var="isread">
										<option value="${isread.value}"<c:if test="${agencyPojo.isread==isread.value}">selected="selected" </c:if>>${isread.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">法人代表:</td>	
        <td width="35%">
        <input type="text" name="agency.corporation"  value="${agencyPojo.corporation}" class="floatLeft" id="ticketName"><span id="corporation"></span></td>
        <td align="right" class="grey" width="15%">联系电话:</td>	
        <td width="35%">
         <input type="text" name="agency.tel"  value="${agencyPojo.tel}" class="floatLeft" id="ticketName"><span id="tel"></span></td>	
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">联系人:</td>	
        <td width="35%">
        <input type="text" name="agency.contact"  value="${agencyPojo.contact}" class="floatLeft" id="ticketName"><span id="contact"></span></td>
        <td align="right" class="grey" width="15%">信用等级：</td>	
        <td width="35%">
        <input type="text" name="agency.creditLevel"  value="${agencyPojo.creditLevel}" class="floatLeft" id="ticketName"><span id="creditLevel"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">手机号码:</td>	
        <td width="35%">
        <input type="text" name="agency.phone"  value="${agencyPojo.phone}" class="floatLeft" id="ticketName"><span id="phone"></span></td>
        <td align="right" class="grey" width="15%">传真号码:</td>	
        <td width="35%">
        <input type="text" name="agency.fax" value="${agencyPojo.fax}" class="floatLeft" id="ticketName"><span id="fax"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">联系QQ:</td>	
        <td width="35%">
        <input type="text" name="agency.QQ"  value="${agencyPojo.QQ}" class="floatLeft" id="ticketName"><span id="QQ"></span></td>
        <td align="right" class="grey" width="15%">联系Email:</td>	
        <td width="35%">
        <input type="text" name="agency.email" value="${agencyPojo.email}" class="floatLeft" id="ticketName"><span id="email"></span></td>
      </tr>
       <tr>
       <td align="right" class="grey" width="15%">代理品牌:</td>	
       <td width="35%"><select name="agency.manufacturerId" id="ticketType"  class="floatLeft">
   							<c:forEach items="${shop}" var="shop">
   										<option value="${shop.shopId}"<c:if test="${agencyPojo.manufacturerId==shop.shopId}">selected="selected" </c:if>>${shop.shopName}</option>
   								</c:forEach>
   				    <select><div id="scale_mgId"></div><span id="manufacturerId"></span></td>
        <td align="right" class="grey" width="15%">主营品类:</td>	
        <td width="35%"><select name="agency.mainCategory" id="ticketType"  class="floatLeft">
							<c:forEach items="${mainCategory}" var="mainCategory">
										<option value="${mainCategory.value}"<c:if test="${agencyPojo.mainCategory==mainCategory.value}">selected="selected" </c:if>>${mainCategory.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
      </tr>
      <tr>
    	<td align="right" class="grey" width="15%">代理地区:</td>	
      <td colspan="3">
      <select id="province" name="agency.province" class="floatLeft" ><select>
      <select id="city" name="agency.city" class="floatLeft"><select>
      <select id="area" name="agency.area" class="floatLeft"><select>
      <input type="text" name="agency.address"  value="${agencyPojo.address}" style="height:30px;padding: 3px;line-height: 28px;min-width: 180px;border: 1px #cdcdcd solid;margin-top: 2px;float: left;" id="ticketName" /><span id="address"></span></p></td>
    </tr>
    <tr>
    
    <td align="right" class="grey" width="15%">营业执照:</td>	
	<td width="35%">
		<p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/agency/${agencyPojo.busLicence}' /></p>
							       
		<input type="file" name="upfile" class="floatLeft" id="ticketName">
	    </br>
		<p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（800 * 600）</font></p> <span id="busLicence"></span></td>
				    
    </tr>
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="button"  class="back_btn" onclick="window.history.back()">返回</button>
  		<s:if test="#session.role.roleId!=2"><input type="button"  class="ok_btn" value="提交" id="sbutton"/></s:if>
  </div>
</div>

</body>
</html>


<script>
var company =new tt.Field(" 公司名称 ","agency.company").setMsgId("company");
var contact =new tt.Field(" 联系人","agency.contact").setMsgId("contact");
var phone =new tt.Field(" 手机号码","agency.phone").setMsgId("phone");
var corporation =new tt.Field(" 法人代表","agency.corporation").setMsgId("corporation");
var creditLevel =new tt.Field(" 信用等級","agency.creditLevel").setMsgId("creditLevel");
var QQ =new tt.Field(" 联系QQ","agency.QQ").setMsgId("QQ");
var email =new tt.Field(" 联系Email","agency.email").setMsgId("email");
var manufacturerId =new tt.Field("代理品牌 ","agency.manufacturerId").setMsgId("manufacturerId");
var busLicence =new tt.Field("营业执照 ","agency.busLicence").setMsgId("busLicence");
var address =new tt.Field("代理地址区","agency.address").setMsgId("address");
tt.vf.req.add(company,contact,phone,corporation,creditLevel,QQ,email,manufacturerId,busLicence,address);
tt.vf.email.add(email);
$(document).ready(function() {
	$("#sbutton").click(function(){	
		if(tt.validate()){
			document.getElementById("from1").submit();
		}
	});
});

</script>