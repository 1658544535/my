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
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>首页场景设置</a> &gt; 
  <a href="#">场景详情列表</a> &gt; 
  <a href="#">编辑场景详情</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateSceneById.do" method="post" id="from1" enctype="multipart/form-data">
  	<input type="hidden" name="scenePojo.id" value="${scenePojo.id}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">场景名称：</td>
		<td>
			<input type="text" name="scenePojo.name" value="${scenePojo.name}" class="floatLeft" id="ticketName" />
		</td>
		<td><span id="name_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">场景时间：</td>
	<td>
	从
	<input id="s" name="scenePojo.beginTimeStr" value="${scenePojo.beginTimeStr }" class="Wdate" type="text" onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'e\')}'})"/>
        <!-- <font color="#FF0000">修改前先清空"结束时间"O(∩_∩)O哈哈~</font> -->
	至
	<input id="e" name="scenePojo.endTimeStr" value="${scenePojo.endTimeStr }" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'s\')}'})"/>
        
	</td>
	<td><span id="beginTime_mgId"></span><span id="endTime_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td>
		<input type="text" name="scenePojo.sorting" value="${scenePojo.sorting}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sorting_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">状态：</td>
        <td><select name="scenePojo.status" id="scenePojo.status"  class="floatLeft">
							<option value="0" <s:if test="scenePojo.status==0">selected="selected"</s:if>>未通过</option>
							<option value="1" <s:if test="scenePojo.status==1">selected="selected"</s:if>>已通过</option>
				    		</select></td>					
		<td><span id="status_mgId"></span></td>
    </tr>
    <tr>
	    <td align="right" class="grey" width="15%">场景图片：</td>
		<td>
						<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="scenePic">
						<font color="#FF0000">图片建议尺寸：800*380</font>
		</td>
		<td>
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/scene/${scenePojo.image}" height="100px" />
			<span id="image_mgId"></span>
		</td>
	</tr>
    <tr>
		<td align="right" class="grey" width="15%">活动介绍：</td>
		<td width="55%">
			<textarea rows="10" cols="70" name="scenePojo.introduction" class="floatLeft" id="introduction">${scenePojo.introduction }</textarea>
			<script type="text/javascript">UE.getEditor("introduction");</script>
		</td>
		<td><span id="introduction_mgId"></span></td>
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
	$(document).ready(function(){
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
	var sname =new tt.Field(" 场景名称 ","scenePojo.name").setMsgId("name_mgId");
	var sorting =new tt.Field(" 排序 ","scenePojo.sorting").setMsgId("sorting_mgId");
	var v_status =new tt.Field(" 状态","scenePojo.status").setMsgId("status_mgId");
	var image =new tt.Field(" 场景图片","scenePojo.image").setMsgId("image_mgId");
	var beginTime =new tt.Field(" 开始时间 ","scenePojo.beginTimeStr").setMsgId("beginTime_mgId");
	var endTime =new tt.Field(" 结束时间 ","scenePojo.endTimeStr").setMsgId("endTime_mgId");	
	tt.vf.req.add(sname,sorting,v_status,image,beginTime,endTime);
</script>