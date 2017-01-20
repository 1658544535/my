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
			<a href="#">拼得客当月销售额记录表管理</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updatePindekeMonthSale.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="pindekeMonthSalePojo.id" id="pindekeMonthSalePojo.id" value="${pindekeMonthSalePojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">用户id：</td>
						<td><input type="text" name="pindekeMonthSalePojo.userId" id="pindekeMonthSalePojo.userId" value="${pindekeMonthSalePojo.userId}" /></td>
						<td><span id="userId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">类型 :1为排行榜2为30天销售额：</td>
						<td><input type="text" name="pindekeMonthSalePojo.type" id="pindekeMonthSalePojo.type" value="${pindekeMonthSalePojo.type}" /></td>
						<td><span id="type_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">区间时间：</td>
						<td><input type="text" name="pindekeMonthSalePojo.sectionTime" id="pindekeMonthSalePojo.sectionTime" value="${pindekeMonthSalePojo.sectionTime}" /></td>
						<td><span id="sectionTime_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">累计返佣金额：</td>
						<td><input type="text" name="pindekeMonthSalePojo.total" id="pindekeMonthSalePojo.total" value="${pindekeMonthSalePojo.total}" /></td>
						<td><span id="total_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">是否已返佣奖励(1是0否)：</td>
						<td><input type="text" name="pindekeMonthSalePojo.isSettle" id="pindekeMonthSalePojo.isSettle" value="${pindekeMonthSalePojo.isSettle}" /></td>
						<td><span id="isSettle_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">奖励金额：</td>
						<td><input type="text" name="pindekeMonthSalePojo.settleAmt" id="pindekeMonthSalePojo.settleAmt" value="${pindekeMonthSalePojo.settleAmt}" /></td>
						<td><span id="settleAmt_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">排名：</td>
						<td><input type="text" name="pindekeMonthSalePojo.ranking" id="pindekeMonthSalePojo.ranking" value="${pindekeMonthSalePojo.ranking}" /></td>
						<td><span id="ranking_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">邀请者id：</td>
						<td><input type="text" name="pindekeMonthSalePojo.inviterId" id="pindekeMonthSalePojo.inviterId" value="${pindekeMonthSalePojo.inviterId}" /></td>
						<td><span id="inviterId_mgId"></span></td>
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