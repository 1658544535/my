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
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>采购商账户管理</a> &gt; <a href="consumer.do" >账户信息</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateConsumer.do?os=${os}" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="consumer.id" id="consumerId" value="${consumerPojo.id}" class="inputText" type="hidden" >
    <input name="consumer.userId" id="consumerId" value="${consumerPojo.userId}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">公司名称:</td>	
        <td width="35%">
        <input type="text" name="consumer.company"  value="${consumerPojo.company}" class="floatLeft" id="ticketName"><span id="company"></span></td>
        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%">
        <!--${consumerPojo.statusName}
         <input name="consumer.status" id="status" value="${consumerPojo.status}" class="inputText" type="hidden" >
        -->
        <select name="consumer.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${consumerPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select>
	    <div id="status_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">地址:</td>	
        <td width="35%">
        <input type="text" name="consumer.address"  value="${consumerPojo.address}" class="floatLeft" id="ticketName"><span id="address"></span></td>
        
        <td align="right" class="grey"  width="15%">来源渠道:</td>
        <td width="35%"><select name="consumer.channel" id="ticketType"  class="floatLeft">
        					<c:forEach items="${channel}" var="channel">
										<option value="${channel.value}"<c:if test="${consumerPojo.channel==channel.value}">selected="selected" </c:if>>${channel.name}</option>
								</c:forEach>
				    </select><div id="channel_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">公司规模:</td>	
        <td width="35%"><select name="consumer.groups" id="ticketType"  class="floatLeft">
        					<c:forEach items="${groups}" var="groups">
										<option value="${groups.value}"<c:if test="${consumerPojo.groups==groups.value}">selected="selected" </c:if>>${groups.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
        <td align="right" class="grey"  width="15%">是否查看:</td>
        <td width="35%"><select name="consumer.isread" id="ticketType"  class="floatLeft">
							<c:forEach items="${isread}" var="isread">
										<option value="${isread.value}"<c:if test="${consumerPojo.isread==isread.value}">selected="selected" </c:if>>${isread.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">网店URL:</td>	
        <td width="35%">
        <input type="text" name="consumer.webSite"  value="${consumerPojo.webSite}" class="floatLeft" id="ticketName"><span id="webSite"></span></td>
        <td align="right" class="grey" width="15%">联系电话:</td>	
        <td width="35%">
         <input type="text" name="consumer.tel"  value="${consumerPojo.tel}" class="floatLeft" id="ticketName"><span id="tel"></span></td>	
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">联系人:</td>	
        <td width="35%">
        <input type="text" name="consumer.contact"  value="${consumerPojo.contact}" class="floatLeft" id="ticketName"><span id="contact"></span></td>
        <td align="right" class="grey" width="15%">联系人职务:</td>	
        <td width="35%">
        <input type="text" name="consumer.duty" value="${consumerPojo.duty}" class="floatLeft" id="ticketName"><span id="duty"></span></td>
      </tr>
     <tr>
        <td align="right" class="grey" width="15%">手机号码:</td>	
        <td width="35%">
        <input type="text" name="consumer.phone"  value="${consumerPojo.phone}" class="floatLeft" id="ticketName"><span id="phone"></span></td>
        <td align="right" class="grey" width="15%">传真号码:</td>	
        <td width="35%">
        <input type="text" name="consumer.fax" value="${consumerPojo.fax}" class="floatLeft" id="ticketName"><span id="fax"></span></td>
      </tr>
       <tr>
        <td align="right" class="grey" width="15%">联系QQ:</td>	
        <td width="35%">
        <input type="text" name="consumer.QQ"  value="${consumerPojo.QQ}" class="floatLeft" id="ticketName"><span id="QQ"></span></td>
        <td align="right" class="grey" width="15%">联系Email:</td>	
        <td width="35%">
        <input type="text" name="consumer.email" value="${consumerPojo.email}" class="floatLeft" id="ticketName"><span id="email"></span></td>
      </tr>
       <tr>
        <td align="right" class="grey" width="15%">销售平台:</td>	
        <td width="35%">
        <input type="text" name="consumer.platform"  value="${consumerPojo.platform}" class="floatLeft" id="ticketName"><span id="platform"></span></td>
        <td align="right" class="grey" width="15%">销售地区:</td>	
        <td width="35%">
        <input type="text" name="consumer.salesArea"  value="${consumerPojo.salesArea}" class="floatLeft" id="ticketName"><span id="salesArea"></span></td>
      </tr>
      <tr>
       <td align="right" class="grey" width="15%">主营品类:</td>	
        <td width="35%"><select name="consumer.mainCategory" id="ticketType"  class="floatLeft">
							<c:forEach items="${mainCategory}" var="mainCategory">
										<option value="${mainCategory.value}"<c:if test="${consumerPojo.mainCategory==mainCategory.value}">selected="selected" </c:if>>${mainCategory.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
	<!--	<td align="right" class="grey" width="15%">公司形象:</td>
        <td width="35%">
        <input type="text" name="consumer.corporateImage"  value="${consumerPojo.corporateImage}" class="floatLeft" id="ticketName"></td>-->
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
var company =new tt.Field(" 公司名称 ","consumer.company").setMsgId("company");
//var address =new tt.Field(" 地址 ","consumer.address").setMsgId("address");
var contact =new tt.Field(" 联系人","consumer.contact").setMsgId("contact");
var phone =new tt.Field(" 手机号码","consumer.phone").setMsgId("phone");
var platform =new tt.Field(" 销售平台","consumer.platform").setMsgId("platform");
var salesArea =new tt.Field(" 销售地区","consumer.salesArea").setMsgId("salesArea");
var QQ =new tt.Field(" 联系QQ","consumer.QQ").setMsgId("QQ");
var email =new tt.Field(" 联系Email","consumer.email").setMsgId("email");
tt.vf.email.add(email);
tt.vf.req.add(company,contact,phone,platform,salesArea,QQ,email);
<!--new tt.LV().set(0, 30).add(webSite);-->
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>