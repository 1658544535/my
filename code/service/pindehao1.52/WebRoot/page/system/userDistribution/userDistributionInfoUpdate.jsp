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

</script>


</head>
<body>

<div class="sub_wrap">
  <div class="s_nav"><a>分销商账户管理</a> &gt; <a href="userDistributionInfo.do" >账户信息</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="userDistributionInfoUpdateSub.do?userDistributionInfoPojo.id=${userDistributionInfoPojo.id}" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      <tr>
        <td align="right" class="grey" width="15%">账号：</td>	
        <td width="35%">${userDistributionInfoPojo.loginname}</td>
        <td align="right" class="grey" width="15%">昵称：</td>
        <td width="35%">${userDistributionInfoPojo.userName}</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">姓名：</td>
        <td width="35%">
        <input type="text" name="userDistributionInfoPojo.name" value="${userDistributionInfoPojo.name}" class="floatLeft">
        <span id="nameMsg"></span></td>
        <td align="right" class="grey" width="15%">性别：</td>
        <td width="35%">
        	<select name="userDistributionInfoPojo.sex">
				<option value="0">未填写</option>
				<option value="1">男</option>
				<option value="2">女</option>
			</select>
        <span id="sexMsg"></span></td>
      </tr>
      <tr>
        <%-- <td align="right" class="grey" width="15%">创建时间：</td>
        <td width="35%">${userDistributionInfoPojo.createDateStr}</td> --%>
        <!--
        <td align="right" class="grey" width="15%">用户编码：</td>
        <td width="35%">
        <input type="text" name="userDistributionInfoPojo.userId" value="${userDistributionInfoPojo.userId}" class="floatLeft">
        <span id="userIdMsg"></span></td>
        -->
        <td align="right" class="grey" width="15%">状态：</td>
        <td width="35%">
        	<select name="userDistributionInfoPojo.status">
				<option value="0">审核中</option>
				<option value="1">通过审核</option>
				<option value="-1">未通过</option>
			</select>
        <span id="statusMsg"></span></td>
      <tr>
        <td align="right" class="grey" width="15%">身份证号：</td>
        <td width="35%">
        <input type="text" name="userDistributionInfoPojo.personCode" value="${userDistributionInfoPojo.personCode}" class="floatLeft">
		<span id="pcMsg"></span></td>
		<c:if test="${userDistributionInfoPojo.personCodeImg != null && userDistributionInfoPojo.personCodeImg != ''}">
        <td align="right" class="grey" width="15%">身份证图片：</td>
        <td width="35%">
        <img src="${userDistributionInfoPojo.personCodeImg}" height="100px">
        </td></c:if>
      </tr>
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="button"  class="back_btn" onclick="window.history.back()">返回</button>
  		<input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  </div>
</div>

</body>
</html>


<script>
var name1 =new tt.Field(" 姓名 ","userDistributionInfoPojo.name").setMsgId("nameMsg");
var pc =new tt.Field(" 身份证号 ","userDistributionInfoPojo.personCode").setMsgId("pcMsg");
var userId =new tt.Field(" 用户编码 ","userDistributionInfoPojo.userId").setMsgId("userIdMsg");
tt.vf.req.add(name1,pc);
// tt.vf.num.add(userId);
new tt.LV().set(18,18).add(pc);
$(document).ready(function() {
	$("#sbutton").click(function(){	
		if(tt.validate()){
			document.getElementById("from1").submit();
		}
	});
});

$(document).ready(function(){
	$("select[name='userDistributionInfoPojo.sex']").val("${userDistributionInfoPojo.sex}");
	$("select[name='userDistributionInfoPojo.status']").val("${userDistributionInfoPojo.status}");
});
</script>