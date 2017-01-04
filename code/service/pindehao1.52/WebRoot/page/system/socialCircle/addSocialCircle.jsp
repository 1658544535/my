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
<script type="text/javascript">
	$(function(){
		select();
	});
	function select() {
		$("#circleTypeId").append("<option value=''>----请选择----</option>");
		$.ajax(
		{
			type: "post",
			url: "socialCircleTypeListAll.do?op=1",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					$("#circleTypeId").append("<option value=" + o_msg[i].id + ">" + o_msg[i].name + "</option>");
				}
			}
		})
	};
</script>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a href="#">社圈管理</a> &gt; <a href="socialCircle.do">社圈管理</a> &gt; <a href="goAddSocialCircle.do">新增社圈</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addSocialCircle.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
				<td align="right" class="grey" width="15%">社圈名称：</td>
				<td><input type="text" name="socialCirclePojo.title" id="socialCirclePojo.title" style="float:left" value="<s:property value="socialCirclePojo.title"/>" />
				</td><td><span id="title_mgId"></span></td>
				</tr>
				<tr>
				<td align="right" class="grey" width="15%">社圈分类：</td>
				<td><select id="circleTypeId" name="socialCirclePojo.circleTypeId" class="floatLeft" ></select><span id="circleTypeId_mgId"></span></td>
				</tr>
				<tr>
				<td align="right" class="grey" width="15%">审核状态：</td>
						<td><select name="socialCirclePojo.status" id="socialCirclePojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核成功</option>、
							<option value="2">审核失败</option>
				    		</select>
				<span id="status_mgId"></span></td>
				</tr>
				<tr>
	   			<td align="right" class="grey" width="15%">LOGO：</td>
				<td>
					<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="banner">
					<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/socialCircle/${socialCirclePojo.banner}" height="100px" />
					<span id="banner_mgId"></span>
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
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
	var v_title        = new tt.Field("社圈名称 ","socialCirclePojo.title").setMsgId("title_mgId");
	var v_circleTypeId = new tt.Field("社圈分类 ","socialCirclePojo.circleTypeId").setMsgId("circleTypeId_mgId");
	var v_status       = new tt.Field("审核状态 ","socialCirclePojo.status").setMsgId("status_mgId");
	var v_banner       = new tt.Field("LOGO","socialCirclePojo.banner").setMsgId("banner_mgId");
	tt.vf.req.add(v_title,v_circleTypeId,v_status,v_banner);
</script>