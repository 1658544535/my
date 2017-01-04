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
  <div class="s_nav"><a>品牌管理</a> &gt; <a href="manufacturer.do" >账户信息</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateManufacturer.do?os=${os}" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="manufacturer.id" id="manufacturerId" value="${manufacturerPojo.id}" class="inputText" type="hidden" >
     <input name="manufacturer.userId" id="manufacturerId" value="${manufacturerPojo.userId}" class="inputText" type="hidden" >
     <input name="manufacturer.logo" id="manufacturerId" value="${manufacturerPojo.logo}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">公司名称:</td>	
        <td>${manufacturerPojo.company}</td>        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><!--${manufacturerPojo.statusName}-->
        <select name="manufacturer.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${manufacturerPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select><div id="status_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">地址:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.address"  value="${manufacturerPojo.address}" class="floatLeft" id="ticketName"><span id="address"></span></td>
        
        <td align="right" class="grey"  width="15%">来源渠道:</td>
        <td width="35%"><select name="manufacturer.channel" id="ticketType"  class="floatLeft">
        					<c:forEach items="${channel}" var="channel">
										<option value="${channel.value}"<c:if test="${manufacturerPojo.channel==channel.value}">selected="selected" </c:if>>${channel.name}</option>
								</c:forEach>
				    </select><div id="channel_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">公司规模:</td>	
        <td width="35%"><select name="manufacturer.scale" id="ticketType"  class="floatLeft">
							<c:forEach items="${scale}" var="scale">
										<option value="${scale.value}"<c:if test="${manufacturerPojo.scale==scale.value}">selected="selected" </c:if>>${scale.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
        <td align="right" class="grey"  width="15%">是否查看:</td>
        <td width="35%"><select name="manufacturer.isread" id="ticketType"  class="floatLeft">
							<c:forEach items="${isread}" var="isread">
										<option value="${isread.value}"<c:if test="${manufacturerPojo.isread==isread.value}">selected="selected" </c:if>>${isread.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">公司网址:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.webSite"  value="${manufacturerPojo.webSite}" class="floatLeft" id="ticketName"><span id="webSite"></span></td>
        <td align="right" class="grey" width="15%">销售市场:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.salesArea"  value="${manufacturerPojo.salesArea}" class="floatLeft" id="ticketName"><span id="salesArea"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">主营品类:</td>	
        <td width="35%"><select name="manufacturer.mainCategory" id="ticketType"  class="floatLeft">
							<c:forEach items="${mainCategory}" var="mainCategory">
										<option value="${mainCategory.id}"<c:if test="${manufacturerPojo.mainCategory==mainCategory.id}">selected="selected" </c:if>>${mainCategory.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
      	<td align="right" class="grey" width="15%">自营品牌:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.brand" value="${manufacturerPojo.brand}" class="floatLeft" id="ticketName"><span id="brand"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">商务联系人:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.contact"  value="${manufacturerPojo.contact}" class="floatLeft" id="ticketName"><span id="contact"></span></td>
        <td align="right" class="grey" width="15%">职务:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.duty" value="${manufacturerPojo.duty}" class="floatLeft" id="ticketName"><span id="duty"></span></td>
      </tr>
     <tr>
        <td align="right" class="grey" width="15%">联系号码:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.phone"  value="${manufacturerPojo.phone}" class="floatLeft" id="ticketName"><span id="phone"></span></td>
        <td align="right" class="grey" width="15%">传真号码:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.fax" value="${manufacturerPojo.fax}" class="floatLeft" id="ticketName"><span id="fax"></span></td>
      </tr>
       <tr>
        <td align="right" class="grey" width="15%">QQ:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.QQ"  value="${manufacturerPojo.QQ}" class="floatLeft" id="ticketName"><span id="QQ"></span></td>
        <td align="right" class="grey" width="15%">邮箱地址:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.email" value="${manufacturerPojo.email}" class="floatLeft" id="ticketName"><span id="email"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">企业LOGO:</td>	
        <td width="35%">
        <p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/manufacturer/${manufacturerPojo.logo}' height='80px' /></p>
       
        <input type="file" name="upfile" class="floatLeft" id="ticketName"></br><font color="#df434e">建议上传图片规格（200 * 200）类型为（*.jpg）</font></td>
        
       
        </tr>
       <tr>
       <td align="right" class="grey" width="15%">公司简介:<br/><span id="content"></span></td>	
        <td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="manufacturer.content" id="content">${manufacturerPojo.content}</textarea></td>
        <td align="right" class="grey" width="15%">主营商品:</td>	
        <td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="manufacturer.mainProduct" id="mainProduct"   >${manufacturerPojo.mainProduct}</textarea><span id="mainProduct"></span></td>
        </tr>
           
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  </div>
</div>

</body>
</html>


<script>
var company =new tt.Field(" 公司名称 ","manufacturer.company").setMsgId("company");
var address =new tt.Field(" 地址 ","manufacturer.address").setMsgId("address");
var webSite =new tt.Field(" 公司网址 ","manufacturer.webSite").setMsgId("webSite");
var salesArea =new tt.Field(" 销售市场 ","manufacturer.salesArea").setMsgId("salesArea");
var brand =new tt.Field(" 自营品牌 ","manufacturer.brand").setMsgId("brand");
var contact =new tt.Field(" 商务联系人 ","manufacturer.contact").setMsgId("contact");
var duty =new tt.Field(" 职务 ","manufacturer.duty").setMsgId("duty");
var QQ =new tt.Field(" QQ ","manufacturer.QQ").setMsgId("QQ");
var email =new tt.Field(" 邮箱地址 ","manufacturer.email").setMsgId("email");
var content =new tt.Field(" 公司简介 ","manufacturer.content").setMsgId("content");
var mainProduct =new tt.Field(" 主营商品 ","manufacturer.mainProduct").setMsgId("mainProduct");


tt.vf.req.add(company,address,salesArea,brand,contact,duty,QQ,email,content,mainProduct);
new tt.LV().set(0, 50).add(company);
new tt.LV().set(0, 300).add(content);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>