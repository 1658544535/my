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
<div class="s_nav"><a href="#">任务管理</a> &gt; <a href="taskSettingList1.do?age=${age}">
<s:if test="age == 1">
0~6个月
</s:if>
<s:elseif test="age == 2">
6~12个月
</s:elseif>
<s:elseif test="age == 3">
1~3岁
</s:elseif>
<s:elseif test="age == 4">
3~6岁
</s:elseif>
<s:elseif test="age == 5">
6~12岁
</s:elseif>
<s:elseif test="age == 6">
12~16岁
</s:elseif>
</a>
&gt; 
<a href="goAddTaskSettingDate.do?age=${age}">设置日程</a>
</div>
  <div class="h15"></div>
  <div>
  <form action="addTaskSettingDate.do" method="post" id="from1" enctype="multipart/form-data">
  	<input type="hidden" name="taskSettingPojo.taskAgeType" value="${age}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">日程：</td>
		<td><input name="taskSettingPojo.taskDateStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true ,minDate:'%y-%M-%d'})"></td>
		<td><span id="taskDate_mgId"></span></td>
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
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
	var taskDate =new tt.Field("日程","taskSettingPojo.taskDateStr").setMsgId("taskDate_mgId");	
	tt.vf.req.add(taskDate);
</script>