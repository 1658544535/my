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
			<a href="#">团购活动表管理</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addGrouponActivity.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">商品ID：</td>
					<td><input type="text" name="grouponActivityPojo.productId" id="grouponActivityPojo.productId" value="" /></td>
					<td><span id="productId_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">开始时间：</td>
					<td><input type="text" name="grouponActivityPojo.beginTime" id="grouponActivityPojo.beginTime" value="" /></td>
					<td><span id="beginTime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">结束时间：</td>
					<td><input type="text" name="grouponActivityPojo.endTime" id="grouponActivityPojo.endTime" value="" /></td>
					<td><span id="endTime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">拼团人数：</td>
					<td><input type="text" name="grouponActivityPojo.num" id="grouponActivityPojo.num" value="" /></td>
					<td><span id="num_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">拼团价格：</td>
					<td><input type="text" name="grouponActivityPojo.price" id="grouponActivityPojo.price" value="" /></td>
					<td><span id="price_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">是否默认，0-否1-是：</td>
					<td><input type="text" name="grouponActivityPojo.isDefault" id="grouponActivityPojo.isDefault" value="" /></td>
					<td><span id="isDefault_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">猜价价格区间起：</td>
					<td><input type="text" name="grouponActivityPojo.priceMin" id="grouponActivityPojo.priceMin" value="" /></td>
					<td><span id="priceMin_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">猜价价格区间止：</td>
					<td><input type="text" name="grouponActivityPojo.priceMax" id="grouponActivityPojo.priceMax" value="" /></td>
					<td><span id="priceMax_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">状态：</td>
					<td><input type="text" name="grouponActivityPojo.status" id="grouponActivityPojo.status" value="" /></td>
					<td><span id="status_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">活动状态，0-未开始1-活动中2-活动结束：</td>
					<td><input type="text" name="grouponActivityPojo.activityStatus" id="grouponActivityPojo.activityStatus" value="" /></td>
					<td><span id="activityStatus_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">活动类型，1-普通拼团2-团免3-猜价：</td>
					<td><input type="text" name="grouponActivityPojo.type" id="grouponActivityPojo.type" value="" /></td>
					<td><span id="type_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">排序：</td>
					<td><input type="text" name="grouponActivityPojo.sorting" id="grouponActivityPojo.sorting" value="" /></td>
					<td><span id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">标题：</td>
					<td><input type="text" name="grouponActivityPojo.title" id="grouponActivityPojo.title" value="" /></td>
					<td><span id="title_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">banner：</td>
					<td><input type="text" name="grouponActivityPojo.banner" id="grouponActivityPojo.banner" value="" /></td>
					<td><span id="banner_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">删除状态，0-否1-是：</td>
					<td><input type="text" name="grouponActivityPojo.isDelete" id="grouponActivityPojo.isDelete" value="" /></td>
					<td><span id="isDelete_mgId"></span></td>
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