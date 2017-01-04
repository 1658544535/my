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
			<a href="#"><s:if test="type==1">77专区管理</s:if><s:if test="type==2">新品专区表管理 </s:if></a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addZones.do?type=${type}" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">标题：</td>
					<td><input type="text" name="zonesPojo.title" id="zonesPojo.title" value="" /></td>
					<td><span id="title_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">上传图片：</td>
					<td><input type="file" name="upfile" class="floatLeft" id="ticketName"><span style="color:red">(图片格式640*300)</span></td>
					<td><span id="image_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">排序：</td>
					<td><input type="text" name="zonesPojo.sorting" id="zonesPojo.sorting" value="" /></td>
					<td><span id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">分享图标：</td>
					<td><input type="file" name="upfile2" class="floatLeft" id="ticketName"></td>
					<td><span id="image2_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">分享文案：</td>
					<td><input type="text" name="zonesPojo.shareDesc" id="zonesPojo.shareDesc" value="" /></td>
					<td><span id="shareDesc_mgId"></span></td>
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
	var title = new tt.Field(" 标题", "zonesPojo.title").setMsgId("title_mgId");
	var sorting = new tt.Field(" 排序", "zonesPojo.sorting").setMsgId("sorting_mgId");
	var shareDesc = new tt.Field(" 分享文案", "zonesPojo.shareDesc").setMsgId("shareDesc_mgId");
	
    tt.vf.req.add(title,sorting,shareDesc);
	tt.vf.num.add(sorting);
	tt.vf.int.add(sorting);
	new tt.NRV().set(0, '++').add(sorting);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
</script>