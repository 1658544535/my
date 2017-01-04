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
  <a>商家中心</a> &gt; 
  <a href="specialPushList.do">专场推送</a> &gt; 
  <a href="goAddSpecialPush.do">增加专场推送</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addSpecialPushOk.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">专场ID：</td>
		<td>
			<input type="text" name="specialPushPojo.specialId" value="${specialPushPojo.specialId}" class="floatLeft" id="ticketName" />
		</td>
		<td><span id="specialId_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td>
		<input type="text" name="specialPushPojo.sorting" value="${specialPushPojo.sorting}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sorting_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="specialPushPojo.status" id="specialPushPojo.status"  class="floatLeft">
							<option value="0">未审核</option>
							<option value="1">已审核</option>
		</select><span id="status_mgId"></span></td>
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
				var id = $("input[name='specialPushPojo.specialId']").val();
				var r = /^[1-9][0-9]*$/;
				if(id != "" && !r.test(id)){
					alert("专场ID必须为正整数！");
				}else{
					document.getElementById("from1").submit();	
				}				
			}
		});
	});	
	var specialId     =new tt.Field("专场标题 ","specialPushPojo.specialId").setMsgId("specialId_mgId");
	var sorting       =new tt.Field("排序 ","specialPushPojo.sorting").setMsgId("sorting_mgId");
	var v_status      =new tt.Field("状态","specialPushPojo.status").setMsgId("status_mgId");
	tt.vf.req.add(specialId,sorting,v_status);
</script>