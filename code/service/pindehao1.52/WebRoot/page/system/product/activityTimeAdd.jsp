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
<div class="s_nav"><a href="#">活动商品管理</a> &gt;<c:if test="${t == 2}"><a href="thematicActivityManage.do?op=2">专题活动设置</a>
&gt;<a href="#">新增专题活动</a></c:if>
<c:if test="${t != 2}"><a href="activityTimeManage.do?op=1">限时秒杀活动管理</a>
&gt;<a href="#">新增秒杀活动</a></c:if></div>
  <div>
  <form action="insertActivityTime.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr><td align="right">标题：</td>
	<td>
	<input type="text" name="activityTimePojo.title" value="${activityTimePojo.title}" class="floatLeft">
	</td>
	<td><span id="title_mgId"></span></td>
	</tr>
	<c:choose>
    <c:when test="${t == 2}">
    <!--
    <tr>
		<td align="right" width="15%">活动类型：</td>	
		<td width="35%">
			<select name="activityTimePojo.type" id=""  class="floatLeft">
				<c:forEach items="${syntheticalDictPojos}" var="s">
				<option value="${s.value}">${s.name}</option>
				</c:forEach>
			</select>
		</td>
		<td><input type="text" hidden="true" name="op" value="2"></td>
	</tr>
	-->
	<input type="text" hidden="true" name="op" value="2">
	<input name="activityTimePojo.type" id="activityTimePojo.type" value="1" class="inputText" type="hidden" >
    </c:when>
    <c:otherwise>
    <input type="hidden" name="activityTimePojo.type" value="0" id="activityTimePojo.type">
    <tr><td align="right">活动日期：</td>
	<td>
	<input id="d5220" name="activityTimePojo.activityDate" class="Wdate" type="text" onFocus="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})"/>
	</td>
	<td><input type="text" hidden="true" name="op" value="1"><span id="activityDate_mgId"></span></td>
	</tr>
    </c:otherwise>
    </c:choose>
    <tr>
    <c:choose>
    <c:when test="${t == 2}">
    <td align="right">活动时间从：</td>
	<td>
	<input id="d5223" name="activityTimePojo.beginTime" class="Wdate" type="text" onFocus="var d5224=$dp.$('d5224');WdatePicker({onpicked:function(){d5224.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d5224\')}'})"/>
	至
	<input id="d5224" name="activityTimePojo.endTime" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d5223\')}'})"/>
	</td>
    </c:when>
    <c:otherwise>
    <td align="right">活动时间从：</td>
	<td>
	<input id="d5221" name="activityTimePojo.beginTime" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},isShowToday:false,dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
	至
	<input id="d5222" name="activityTimePojo.endTime" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'HH:mm',minDate:'#F{$dp.$D(\'d5221\')}'})"/>
	</td>
    </c:otherwise>
    </c:choose>
	<td><span id="beginTime_mgId"></span>
	<span id="endTime_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" width="15%">活动渠道：</td>	
		<td width="35%">
			<select name="activityTimePojo.channel" id="">
		 		<option value="1">APP</option>
		 		<option value="2">微商城</option>
			</select>
		</td>
		<td></td>
	</tr>
	<tr>
		<td align="right" width="15%">Banner：</td>	
	        <td width="35%">
	        <input type="file" name="upfile" class="floatLeft" id="">
		</td>
		<td></td>
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
	
	var title =new tt.Field(" 标题 ","activityTimePojo.title").setMsgId("title_mgId");
	var activityDate =new tt.Field(" 活动日期 ","activityTimePojo.activityDate").setMsgId("activityDate_mgId");
	var beginTime =new tt.Field(" 活动开始时间 ","activityTimePojo.beginTime").setMsgId("beginTime_mgId");
	var endTime =new tt.Field(" 活动结束时间 ","activityTimePojo.endTime").setMsgId("endTime_mgId");
	
	tt.vf.req.add(title,activityDate,beginTime,endTime);
</script>