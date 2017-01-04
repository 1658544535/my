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
  <div class="s_nav"><a>系统管理</a> &gt; 
  <c:if test="${os==0}"><a href="sysLogin.do?os=${os}">管理员管理</a></c:if> 
  <c:if test="${os==1}"><a href="sysLogin.do?os=${os}">普通用户管理</a></c:if> 
  <c:if test="${os==3}"><a href="sysLogin.do?os=${os}">供应商管理</a></c:if> 
  <c:if test="${os==2}"><a href="sysLogin.do?os=${os}">采购商管理</a></c:if>
  <c:if test="${os==6}"><a href="sysLogin.do?os=${os}">代理商管理</a></c:if> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateSysLogin.do?os=${os}" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="sysLogin.id" id="sysLoginId" value="${sysLoginPojo.id}" class="inputText" type="hidden" >
    <input name="sysLogin.password"  value="${sysLoginPojo.password}" class="inputText" id="ticketName" type="hidden">
      <tr>
        <td align="right" class="grey" width="15%">昵称:</td>	
        <td width="35%">
        <input type="text" name="sysLogin.name"  value="${sysLoginPojo.name}" class="floatLeft" id="ticketName"><span id="namead"></span></td>
        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="sysLogin.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}" <c:if test="${sysLoginPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">帐号:</td>	
        <td width="35%">
        <input type="text" name="sysLogin.loginname"  value="${sysLoginPojo.loginname}" class="floatLeft" id="ticketName"><span id="loginname"></span></td>
        
        <td align="right" class="grey" width="15%">排序:</td>	
        <td width="35%">
        <input type="text" name="sysLogin.sorting"  value="${sysLoginPojo.sorting}" class="floatLeft" id="ticketName"><span id="sorting"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">嫌疑对象:</td>	
        <td>
        <select name="sysLogin.blackFlag" id="ticketName"  class="floatLeft">
							<option value="0" <c:if test="${sysLoginPojo.blackFlag == 0}">selected="selected"</c:if>>否</option>
							<option value="1" <c:if test="${sysLoginPojo.blackFlag == 1}">selected="selected"</c:if>>是</option>
							</select><span id="blackFlag"></span>
	    </td>
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
var namead    =new tt.Field(" 昵称 ","sysLogin.name").setMsgId("namead");
var loginname =new tt.Field(" 帐号 ","sysLogin.loginname").setMsgId("loginname");
var sorting   =new tt.Field(" 排序 ","sysLogin.sorting").setMsgId("sorting");
var blackFlag =new tt.Field(" 嫌疑对象 ","sysLogin.blackFlag").setMsgId("blackFlag");
tt.vf.req.add(namead,loginname,sorting,blackFlag);
new tt.LV().set(0, 50).add(namead);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>