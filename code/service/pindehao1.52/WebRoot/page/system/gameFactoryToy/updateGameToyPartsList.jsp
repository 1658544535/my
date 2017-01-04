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
<div class="s_nav"><a href="#">玩具工厂管理</a> &gt;<a href="#">玩具设置</a> &gt;<a href="#">玩具配件设置</a>&gt;<a href="#">配件编辑</a></div>
  <div>
  <form action="updateGameToyParts.do?gameToyPartsPojo.id=${gameToyPartsPojo.id}&toyId=${toyId}" method="post" id="from1" enctype="multipart/form-data">
  <input type="hidden" name="type" id="type" value="${type}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr><td align="right">配件名称：</td>
	<td>
	<input type="text" name="gameToyPartsPojo.name" value="${gameToyPartsPojo.name}" class="floatLeft" />
	<div id="name_msgId"></div>
	</td>
	</tr>
    <tr><td align="right">配件图片：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/gameToyParts/${gameToyPartsPojo.images}" height="100px"  /></td></tr>
	<tr>
	<td> <input type="file" name="upfile" class="floatLeft" id="ticketName" /> <span id="images_msgId"></span></td>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </table>
	</tr>
	<tr><td align="right">生成所需时间（分钟）：</td>
	<td>
	<input type="text" name="gameToyPartsPojo.createTime" value="${gameToyPartsPojo.createTime}" class="floatLeft" />
	<div id="createTime_msgId"></div>
	</td>
	</tr>
	<tr><td align="right">概率（0-100）：</td>
	<td>
	<input type="text" name="gameToyPartsPojo.probability" value="${gameToyPartsPojo.probability}" class="floatLeft" />
	<div id="probability_msgId"></div>
	</td>
	</tr>
	<td align="right">状态：</td>
     <td><select name="gameToyPartsPojo.status" id="gameToyPartsPojo.status"  class="floatLeft">
							<option value="0"<s:if test="gameToyPartsPojo.status==0">selected="selected"</s:if>>未通过</option>
							<option value="1"<s:if test="gameToyPartsPojo.status==1">selected="selected"</s:if>>已通过</option>
				    		</select><div id="status_mgId"></div></td>
   	
	</tr>
	<tr><td align="right">排序：</td>
	<td>
	<input type="text" name="gameToyPartsPojo.sorting" value="${gameToyPartsPojo.sorting}" class="floatLeft" />
	<div id="sorting_msgId"></div>
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
    var v_name = new tt.Field("配件名称", "gameToyPartsPojo.name").setMsgId("name_msgId");
    var v_images = new tt.Field("配件图片 ", "gameToyPartsPojo.images").setMsgId("images_msgId");
    var v_sorting = new tt.Field("排序 ", "gameToyPartsPojo.sorting").setMsgId("sorting_msgId");
    var v_createTime = new tt.Field("生成所需时间 ", "gameToyPartsPojo.createTime").setMsgId("createTime_msgId");
    var v_probability = new tt.Field("概率 ", "gameToyPartsPojo.probability").setMsgId("probability_msgId");
    
	tt.vf.req.add(v_name,v_images,v_sorting,v_createTime,v_probability);
	tt.vf.num.add(v_sorting,v_createTime,v_probability);
	new tt.NRV().set(0, '++').add(v_sorting,v_createTime);
	new tt.NRV().set(0, 100).add(v_probability);
	tt.vf.int.add(v_sorting,v_createTime,v_probability);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>