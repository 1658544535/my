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
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/pagination.css"
	media="screen" rel="stylesheet">
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/tipswindown.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/browser.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/tipswindown.css" media="screen"
	rel="stylesheet">
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>用户信息</a> &gt;
  <div class="h15"></div>
  <div>
  <form action="userInfoUpdate.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="userInfoPojo.id" id="userInfoPojo.id" value="${userInfoPojo.id}" class="inputText" type="hidden" >
     <input name="userInfoPojo.userId" id="userInfoPojo.userId" value="${userInfoPojo.userId}" class="inputText" type="hidden" >
     <input name="userInfoPojo.qrCode" id="userInfoPojo.qrCode" value="${userInfoPojo.qrCode}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">性别:</td>	
        <td width="35%"><select name="userInfoPojo.sex" id="ticketType"  class="floatLeft">
							<c:forEach items="${sex}" var="sex">
									<option value="${sex.value}" <c:if test="${userInfoPojo.sex==sex.value}">selected="selected" </c:if>>${sex.name}</option>
							</c:forEach>
				    </select><div id="status_mgId"></div></td>
        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="userInfoPojo.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
									<option value="${status.value}" <c:if test="${userInfoPojo.status == status.value}">selected="selected"</c:if>>${status.name}</option>
							</c:forEach>
				    </select><div id="status_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">地址:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.address"  value="${userInfoPojo.address}" class="floatLeft" id="ticketName"><span id="address"></span></td>
        
        <td align="right" class="grey"  width="15%">来源渠道:</td>
        <td width="35%"><select name="userInfoPojo.channel" id="ticketType"  class="floatLeft">
        					<c:forEach items="${channel}" var="channel">
										<option value="${channel.value}" <c:if test="${userInfoPojo.channel==channel.value}">selected="selected" </c:if>>${channel.name}</option>
								</c:forEach>
				    </select><div id="channel_mgId"></div></td>
      </tr>
      <tr>
      <td align="right" class="grey"  width="15%">是否查看:</td>
        <td width="35%"><select name="userInfoPojo.isread" id="ticketType"  class="floatLeft">
							<c:forEach items="${isread}" var="isread">
										<option value="${isread.value}" <c:if test="${userInfoPojo.isread==isread.value}">selected="selected" </c:if>>${isread.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
        <td align="right" class="grey" width="15%" >生日: </td>
						 <td><label><input id="beganday" name="beganday" type="text"
							readonly="readonly" value="${userInfoPojo.birthday}"> <img id="begancalendarImg"
							src="<s:property value="ctx" />/js/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" align="middle" /></label></td>
							<!--<td><span id="beganday_inf"></span></td>-->
        
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">网址:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.webSite"  value="${userInfoPojo.webSite}" class="floatLeft" id="ticketName"><span id="webSite"></span></td>
        <td align="right" class="grey" width="15%">联系电话:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.tel"  value="${userInfoPojo.tel}" class="floatLeft" id="ticketName"><span id="tel"></span></td>
      </tr>
      
     <tr>
        <td align="right" class="grey" width="15%">手机号码:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.phone"  value="${userInfoPojo.phone}" class="floatLeft" id="ticketName"><span id="phone"></span></td>
        <td align="right" class="grey" width="15%">传真号码:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.fax" value="${userInfoPojo.fax}" class="floatLeft" id="ticketName"><span id="fax"></span></td>
      </tr>
       <tr>
        <td align="right" class="grey" width="15%">QQ:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.QQ"  value="${userInfoPojo.QQ}" class="floatLeft" id="ticketName"><span id="QQ"></span></td>
        <td align="right" class="grey" width="15%">邮箱地址:</td>	
        <td width="35%">
        <input type="text" name="userInfoPojo.email" value="${userInfoPojo.email}" class="floatLeft" id="ticketName"><span id="email"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">用户Logo:</td>	
        <td width="35%">
        <p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userlogo/${userInfoPojo.image}' height='80px' />&nbsp;&nbsp; <font color="#FF0000">图片建议尺寸：300*300</font></p>
        <p></p>
        <input type="file" name="upfile" class="floatLeft" id="ticketName"> </td>
        <td align="right" class="grey" width="15%">二维码:</td>	
        <td width="35%">
        <p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userInfo/${userInfoPojo.qrCode}' height='80px' />&nbsp;&nbsp; <font color="#FF0000">图片建议尺寸：300*300</font></p>
        <p></p>
        <input type="file" name="upfile2" class="floatLeft" id="ticketName"> </td>
        
      </tr>
   	  <tr>
      	<td align="right" class="grey" width="15%">介绍:</td>	
    	<td width="35%">
    	<textarea class="floatLeft" rows="6" cols="50" name="userInfoPojo.content" id="content"   >${userInfoPojo.content}</textarea><span id="content"></span></td>
      </tr>
           
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><s:if test="#session.role.roleId!=2"><input type="button"  class="ok_btn" value="提交" id="sbutton"/></s:if>
  </div>
</div>

</body>
</html>


<script>
var address =new tt.Field(" 地址 ","userInfoPojo.address").setMsgId("address");
var webSite =new tt.Field(" 网址 ","userInfoPojo.webSite").setMsgId("webSite");
var tel =new tt.Field(" 联系号码 ","userInfoPojo.tel").setMsgId("tel");
var phone =new tt.Field(" 联系号码 ","userInfoPojo.phone").setMsgId("phone");
var fax =new tt.Field(" 传真号码 ","userInfoPojo.fax").setMsgId("fax");
var QQ =new tt.Field(" QQ ","userInfoPojo.QQ").setMsgId("QQ");
var email =new tt.Field(" 邮箱地址 ","userInfoPojo.email").setMsgId("email");
var content =new tt.Field(" 介绍 ","userInfoPojo.content").setMsgId("content");


tt.vf.req.add(address,tel,phone,QQ);
new tt.LV().set(0, 50).add(address);
new tt.LV().set(0, 120).add(content);
$(document).ready(function() {
	$("#sbutton").click(function(){		
		if(tt.validate()){
			document.getElementById("from1").submit();
		}
	});
});
function setBeganDay() {
	WdatePicker({
		el : 'beganday'
	});
}

$("#begancalendarImg").click(setBeganDay);
$("#beganday").click(setBeganDay);
$("#endcalendarImg").click(setEndDay);
$("#endday").click(setEndDay)
	
</script>