<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">专区商品表管理</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addZoneGoods.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">专区id：</td>
					<td><input type="text" name="zoneGoodsPojo.zoneId" id="zoneGoodsPojo.zoneId" value="" /></td>
					<td><span id="zoneId_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">团购活动表id：</td>
					<td><input type="text" name="zoneGoodsPojo.activityId" id="zoneGoodsPojo.activityId" value="" /></td>
					<td><span id="activityId_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">商品id：</td>
					<td><input type="text" name="zoneGoodsPojo.productId" id="zoneGoodsPojo.productId" value="" /></td>
					<td><span id="productId_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">状态：</td>
					<td><input type="text" name="zoneGoodsPojo.status" id="zoneGoodsPojo.status" value="" /></td>
					<td><span id="status_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">排序：</td>
					<td><input type="text" name="zoneGoodsPojo.sorting" id="zoneGoodsPojo.sorting" value="" /></td>
					<td><span id="sorting_mgId"></span></td>
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
</script>