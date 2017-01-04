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
  <a href="homePageIconList.do">首页图标管理</a> &gt; 
  <a href="addHomePageIcon.do">新增首页图标</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addHomePageIcon.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
		<td align="right" class="grey" width="15%">图标标题：</td>
		<td><input type="text" name="homePageIconPojo.title" id="homePageIconPojo.title" value="<s:property value="homePageIconPojo.title"/>" class="floatLeft" /></td>
		<td><span id="title_mgId"></span></td>
    </tr>
   <!-- <tr>
		<td align="right" class="grey" width="15%">链接内容：</td>
		<td><input type="text" name="homePageIconPojo.url" id="homePageIconPojo.url" value="<s:property value="homePageIconPojo.url"/>" /></td>
		<td><span id="url_mgId"></span></td>
    </tr> -->
    <tr>
		<td align="right" class="grey" width="15%">类型：</td>
        <td><select name="homePageIconPojo.type" id="homePageIconPojo.type"  class="floatLeft">
							<option value="1">免费试用列表</option>
							<option value="2">猜价格列表</option>
							<option value="3">9.9特卖</option>
							<option value="4">掌上秒杀</option>
							<option value="5">抽奖团列表</option>
							<option value="6">专题分类</option>
							<option value="7">专题</option>
				    		</select></td>
		<td><span id="type_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">参数：</td>
		<td><input type="text" name="homePageIconPojo.url" id="homePageIconPojo.url" value="<s:property value="homePageIconPojo.url"/>" class="floatLeft" /></td>
		<td><span id="url_mgId"></span></td>
    </tr>
	<tr>
		<td align="right" class="grey" width="15%">图标图片：</td>
        <td width="35%">
	    <input type="file" name="upfile" class="floatLeft" id="image" />		
      	</td>
		<td><span id="image_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">排序：</td>
		<td><input type="text" name="homePageIconPojo.sorting" id="homePageIconPojo.sorting" value="<s:property value="homePageIconPojo.sorting"/>" class="floatLeft" /></td>
		<td><span id="sorting_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="homePageIconPojo.status" id="homePageIconPojo.status"  class="floatLeft">
							<option value="0">未审核</option>
							<option value="1">已审核</option>
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
	var title   =new tt.Field("图标标题 ","homePageIconPojo.title").setMsgId("title_mgId");
	//var url   =new tt.Field("链接内容","homePageIconPojo.url").setMsgId("url_mgId");
	var v_type   =new tt.Field("类型","homePageIconPojo.type").setMsgId("type_mgId");
	var image   =new tt.Field("图标图片","upfile").setMsgId("image_mgId");
	var sorting =new tt.Field("排序","homePageIconPojo.sorting").setMsgId("sorting_mgId");
    var v_status  =new tt.Field("审核状态","homePageIconPojo.status").setMsgId("status_mgId");	
	tt.vf.req.add(title,sorting,v_status,v_type);
</script>