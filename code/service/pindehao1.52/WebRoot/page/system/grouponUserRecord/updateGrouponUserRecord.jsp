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
			<a href="#">团购用户记录管理</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateGrouponUserRecord.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="grouponUserRecordPojo.id" id="grouponUserRecordPojo.id" value="${grouponUserRecordPojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">活动类型,1-普通团2-团免3-猜价：</td>
						<td><input type="text" name="grouponUserRecordPojo.activityType" id="grouponUserRecordPojo.activityType" value="${grouponUserRecordPojo.activityType}" /></td>
						<td><span id="activityType_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">活动id：</td>
						<td><input type="text" name="grouponUserRecordPojo.activityId" id="grouponUserRecordPojo.activityId" value="${grouponUserRecordPojo.activityId}" /></td>
						<td><span id="activityId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">参与团购记录id：</td>
						<td><input type="text" name="grouponUserRecordPojo.attendId" id="grouponUserRecordPojo.attendId" value="${grouponUserRecordPojo.attendId}" /></td>
						<td><span id="attendId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">参与用户id：</td>
						<td><input type="text" name="grouponUserRecordPojo.userId" id="grouponUserRecordPojo.userId" value="${grouponUserRecordPojo.userId}" /></td>
						<td><span id="userId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态：</td>
						<td><input type="text" name="grouponUserRecordPojo.status" id="grouponUserRecordPojo.status" value="${grouponUserRecordPojo.status}" /></td>
						<td><span id="status_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">是否团长，0-否1-是：</td>
						<td><input type="text" name="grouponUserRecordPojo.isHead" id="grouponUserRecordPojo.isHead" value="${grouponUserRecordPojo.isHead}" /></td>
						<td><span id="isHead_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">参与时间：</td>
						<td><input type="text" name="grouponUserRecordPojo.attendTime" id="grouponUserRecordPojo.attendTime" value="${grouponUserRecordPojo.attendTime}" /></td>
						<td><span id="attendTime_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">团免券ID：</td>
						<td><input type="text" name="grouponUserRecordPojo.couponId" id="grouponUserRecordPojo.couponId" value="${grouponUserRecordPojo.couponId}" /></td>
						<td><span id="couponId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">猜价价格：</td>
						<td><input type="text" name="grouponUserRecordPojo.price" id="grouponUserRecordPojo.price" value="${grouponUserRecordPojo.price}" /></td>
						<td><span id="price_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">中奖，1-一等奖2-二等奖3-三等奖：</td>
						<td><input type="text" name="grouponUserRecordPojo.prize" id="grouponUserRecordPojo.prize" value="${grouponUserRecordPojo.prize}" /></td>
						<td><span id="prize_mgId"></span></td>
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