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
<div class="s_nav"><a href="#">活动商品管理</a> &gt; <a href="homePackageManage.do">首页套餐设置</a> &gt;
<c:if test="${t == 1}"><a href="#">新增首页套餐</a></c:if>
<c:if test="${t == 2}"><a href="#">编辑首页套餐</a></c:if>
<div class="h15"></div>
  <div>
  <form action="homePackageAddOk.do?t=1" method="post" id="form1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
    <td align="right" class="grey" width="15%">套餐名称：</td>
	<td><input type="text" name="scenePojo.name" value="${scenePojo.name}" class="floatLeft"></td>
	<td><span id="name_mgId"></span></td>
	</tr>
    <tr>
    <td align="right" class="grey" width="15%">套餐时间：</td>
	<td>
	从
	<input id="d5221" name="scenePojo.beginTimeStr" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d5222\')}'})" 
	value="${scenePojo.beginTimeStr }"/>
	至
	<input id="d5222" name="scenePojo.endTimeStr" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d5221\')}'})"
	value="${scenePojo.endTimeStr }"/>
	</td>
	<td><span id="beginTime_mgId"></span><span id="endTime_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">套餐原价：</td>
	<td><input type="text" name="scenePojo.psellPrice" value="${scenePojo.psellPrice}" class="floatLeft">
	<c:if test="${tempSellPrice != 0 }">套餐原价参考：${tempSellPrice }</c:if>
	<%-- <c:if test="${tempSellPrice == 0 }">套餐原价参考：</c:if> --%>
	</td>
	<td><span id="psellPrice_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">套餐活动价：</td>
	<td><input type="text" name="scenePojo.packagePrice" value="${scenePojo.packagePrice}" class="floatLeft">
	<c:if test="${tempPrice != 0 }">套餐活动价参考：${tempPrice }</c:if>
	<%-- <c:if test="${tempPrice == 0 }">套餐活动价参考：</c:if> --%>
	</td>
	<td><span id="packagePrice_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">套餐数量：</td>
	<td><input type="text" name="scenePojo.stockNum" value="${scenePojo.stockNum}" class="floatLeft"></td>
	<td><span id="stockNum_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">套餐库存：</td>
	<td><input type="text" name="scenePojo.stock" value="${scenePojo.stock}" class="floatLeft"></td>
	<td><span id="stock_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">排序：</td>
	<td><input type="text" name="scenePojo.sorting" value="${scenePojo.sorting}" class="floatLeft"></td>
	<td><span id="sorting_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">状态：</td>
		<td width="35%">
			<select name="scenePojo.status">
		 		<option value="1">已审核</option>
		 		<option value="0">未审核</option>
			</select>
		</td>
		<td></td>
	</tr>
	<%-- <tr>
		<td align="right" class="grey" width="15%">预览：</td>
		<td width="35%">
			<select name="scenePojo.preview">
		 		<option value="1">有效预览</option>
		 		<option value="0">无效预览</option>
			</select>
		</td>
		<td></td>
	</tr> --%>
	<tr>
		<td align="right" class="grey" width="15%">活动图片：</td>
		<td width="35%" height="100px">
	        <input type="file" name="upfile" class="floatLeft">
	        <font color="#FF0000">图片建议尺寸：800*380</font>
		</td>
		<td>
			<c:if test="${t == 2 }"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/homePackage/${scenePojo.image }' height='100px' width="200px"/></c:if>
	        <span id="file_mgId"></span>
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
  		<c:if test="${t == 1 }">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  		</c:if>
  		<c:if test="${t == 2 }">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton2"/>
  		</c:if>
  </div>
</div>

</body>
</html>


<script>
var pname =new tt.Field("套餐名称","scenePojo.name").setMsgId("name_mgId");
var packagePrice =new tt.Field("套餐活动价","scenePojo.packagePrice").setMsgId("packagePrice_mgId");
var psellPrice =new tt.Field("套餐原价","scenePojo.psellPrice").setMsgId("psellPrice_mgId");
var beginTime =new tt.Field("套餐活动开始时间","scenePojo.beginTimeStr").setMsgId("beginTime_mgId");
var endTime =new tt.Field("套餐活动结束时间 ","scenePojo.endTimeStr").setMsgId("endTime_mgId");
//var file =new tt.Field("套餐图片","upfile").setMsgId("file_mgId");
var introduction =new tt.Field("套餐介绍","scenePojo.introduction").setMsgId("introduction_mgId");
var sorting =new tt.Field("排序","scenePojo.sorting").setMsgId("sorting_mgId");
var stockNum =new tt.Field("套餐数量","scenePojo.stockNum").setMsgId("stockNum_mgId");
var stock =new tt.Field("套餐库存","scenePojo.stock").setMsgId("stock_mgId");
	
tt.vf.req.add(pname,packagePrice,psellPrice,beginTime,endTime/* ,file */,introduction,sorting,stockNum,stock);
tt.vf.num.add(packagePrice,psellPrice,sorting,stockNum,stock);

$(document).ready(function() {
	$("#sbutton").click(function(){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
	
	$("#sbutton2").click(function(){
		if(tt.validate()){
			$("#form1").attr("action","homePackageAddOk.do?t=2&scenePojo.id=${scenePojo.id}").submit();
		}
	});
	if("${scenePojo.status }" != ''){
		$("select[name='scenePojo.status']").val("${scenePojo.status }");
	}
	/* if("${scenePojo.preview }" != ''){
		$("select[name='scenePojo.preview']").val("${scenePojo.preview }");
	} */
});
</script>
<c:if test="${t == 1 || scenePojo.image == '' || scenePojo.image == null}">
<script>
//var file =new tt.Field("套餐图片","upfile").setMsgId("file_mgId");
//tt.vf.req.add(file);
</script>
</c:if>