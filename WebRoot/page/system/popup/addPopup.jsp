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
  <div class="s_nav">
  <a>推送综合管理</a> &gt; 
  <a href="#">App弹窗管理</a> &gt; 
  <a href="#">App弹窗新增</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addPopupOK.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="popupPojo.popupSize" id="popupSize" value="1" class="inputText" />
    <tr>
	    <td align="right" class="grey" width="15%">开始时间：</td>
		<td>
			<input id="s" name="popupPojo.startTimeStr" value="${popupPojo.startTimeStr}" class="Wdate" type="text" onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'e\')}'})"/>
		</td>
		<td><span id="startTime_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">结束时间：</td>
		<td>
			<input id="e" name="popupPojo.endTimeStr" value="${popupPojo.endTimeStr}" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'s\')}'})"/>
		</td>
		<td><span id="endTime_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">弹窗图片：</td>
        <td width="35%">
	    <input type="file" name="upfile" class="floatLeft" id="popupPic" />		
      	</td>
		<td><span id="popupPic_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">标题：</td>
		<td><input type="text" name="popupPojo.title" id="popupPojo.title" value="<s:property value="popupPojo.title"/>" /></td>
		<td><span id="title_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">参数类型：</td>
       <td><select name="popupPojo.parameterSize" id="popupPojo.parameterSize"  class="floatLeft">
							<option value="1">产品ID</option>
							<option value="2">店铺ID</option>
							<option value="3">产品类型ID</option>
							<option value="4">URL</option>
				    		</select></td>
		<td><span id="parameterSize_mgId"></span></td>
    </tr>   
    <tr>
		<td align="right" class="grey" width="15%">参数值：</td>
        <td><input type="text" name="popupPojo.parameter" id="popupPojo.parameter" value="<s:property value="popupPojo.parameter"/>" /></td>		
		<td><span id="parameter_mgId"></span></td>
    </tr>
    <!--
    <tr>
		<td align="right" class="grey" width="15%">弹窗类型：</td>
        <td><input type="hidden" name="popupPojo.popupSize" id="popupPojo.popupSize"  class="floatLeft" value="1">首页弹窗</td>
		<td><span id="popupSize_mgId"></span></td>
    </tr>
    -->
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="popupPojo.status" id="popupPojo.status"  class="floatLeft">
							<option value="0">未通过</option>
							<option value="1">已通过</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
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
	var startTime     =new tt.Field("开始时间 ","popupPojo.startTimeStr").setMsgId("startTime_mgId");
	var endTime       =new tt.Field("结束时间","popupPojo.endTimeStr").setMsgId("endTime_mgId");
	var popupPic      =new tt.Field("弹窗图片","upfile").setMsgId("popupPic_mgId");
	var parameterSize =new tt.Field("参数状态","popupPojo.parameterSize").setMsgId("parameterSize_mgId");
    var parameter     =new tt.Field("参数值",  "popupPojo.parameter").setMsgId("parameter_mgId");
    // var title      =new tt.Field("标题",   "popupPojo.title").setMsgId("title_mgId");
	// var popupSize  =new tt.Field("弹窗状态","popupPojo.popupSize").setMsgId("popupSize_mgId");
	var v_status      =new tt.Field("审核状态","popupPojo.status").setMsgId("status_mgId");	
	tt.vf.req.add(startTime,endTime,parameterSize,parameter,v_status);
</script>