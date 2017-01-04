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
  <a>场景商品设置</a> &gt; 
  <a href="#">场景商品列表</a> &gt; 
  <a href="#">新增场景商品</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addSceneProductListOk.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">ID：</td>
		<td>
			<input type="text" name="sceneProductPojo.id" value="${sceneProductPojo.id}" class="floatLeft" id="ticketName" />
		    <label>${sceneProductPojo.id}</label>
		</td>
		<td><span id="Id_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">场景名称：</td>
		<td>
			<input type="text" name="sceneProductPojo.sceneName" value="${sceneProductPojo.sceneName}" class="floatLeft" id="ticketName" />
		</td>
		<td><span id="sceneName_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">场景ID：</td>
		<td>
			<input type="text" name="sceneProductPojo.sceneId" value="${sceneProductPojo.sceneId}" class="floatLeft" id="ticketName" />
		</td>
		<td><span id="sceneId_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">商品标题：</td>
		<td>
		<input type="text" name="sceneProductPojo.title" value="${sceneProductPojo.title}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="title_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">商品名称：</td>
		<td>
		<input type="text" name="sceneProductPojo.productName" value="${sceneProductPojo.productName}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="productName_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">商品总量：</td>
		<td>
		<input type="text" name="sceneProductPojo.sceneNum" value="${sceneProductPojo.sceneNum}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sceneNum_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">商品库存：</td>
		<td>
		<input type="text" name="sceneProductPojo.sceneStock" value="${sceneProductPojo.sceneStock}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sceneStock_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td>
		<input type="text" name="sceneProductPojo.sorting" value="${sceneProductPojo.sorting}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sorting_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">状态：</td>
		<td><select name="sceneProductPojo.status" id="sceneProductPojo.status"  class="floatLeft">
							<option value="0">未通过</option>
							<option value="1">已通过</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">备注：</td>
		<td>
		<input type="text" name="sceneProductPojo.remarks" value="${sceneProductPojo.remarks}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="remarks_mgId"></span></td>
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
	var sceneName =new tt.Field(" 场景名称 ","sceneProductPojo.sceneName").setMsgId("sceneName_mgId");
	var sceneId =new tt.Field(" 场景ID ","sceneProductPojo.sceneId").setMsgId("sceneId_mgId");
	var title =new tt.Field(" 商品标题","sceneProductPojo.title").setMsgId("title_mgId");
	var productName =new tt.Field(" 商品名称","sceneProductPojo.productName").setMsgId("productName_mgId");
	var sceneNum =new tt.Field(" 商品总量","sceneProductPojo.sceneNum").setMsgId("sceneNum_mgId");
	var sceneStock =new tt.Field(" 商品库存 ","sceneProductPojo.sceneStock").setMsgId("sceneStock_mgId");
	var sorting =new tt.Field(" 排序 ","sceneProductPojo.sorting").setMsgId("sorting_mgId");	
	var v_status =new tt.Field(" 状态 ","sceneProductPojo.status").setMsgId("status_mgId");
	var remarks =new tt.Field(" 备注 ","sceneProductPojo.remarks").setMsgId("remarks_mgId");
	tt.vf.req.add(sceneName,sceneId,title,productName,sceneNum,sceneStock,sorting,v_status,remarks);
</script>