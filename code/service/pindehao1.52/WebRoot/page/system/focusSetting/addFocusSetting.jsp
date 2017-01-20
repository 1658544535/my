<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">图片综合管理</a> &gt; <a href="#">首页顶部图片</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addFocusSetting.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">标题：</td>
					<td><input type="text" name="focusSettingPojo.title" id="focusSettingPojo.title" value="" /></td>
					<td><span id="title_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">开始显示时间：</td>
					<td><input name="focusSettingPojo.startTime" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
					<td><span id="startTime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">排序：</td>
					<td><input type="text" name="focusSettingPojo.sorting" id="focusSettingPojo.sorting" value="" /></td>
					<td><span id="sorting_mgId"></span></td>
				</tr>
				<tr>
				    <td align="right" class="grey" width="15%">上传图片:</td>
					<td width="35%"><input type="file" name="upfile" class="floatLeft" id="ticketName"><span style="color:red">(图片格式640*350)</span></td>
					<td><span id="banner_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">链接：</td>
					<td> <select name="focusSettingPojo.paramType" id="paramType" onchange="setParamId(this)">
						 		    <option value="">--请选择--</option>
									<option value="0">无</option>
									<!-- <option value="1">商品</option> -->
									<option value="2">普通拼团</option>
									<option value="3">猜价格</option>
									<option value="4">专题</option>
									<option value="5">专题分类</option>
									<option value="6">77专区</option>
									<option value="7">链接</option>
						 </select>
						 <div id="paramId" style="display:inline">
						<input type="text" name="focusSettingPojo.paramId" id="focusSettingPojo.paramId" value=""/>
						</div>
						<div id="paramUrl" style="display:inline">
						<input type="text" name="focusSettingPojo.url" id="focusSettingPojo.url" value=""/>
						</div>
					</td>
					<td><span id="paramType_mgId"></span><span id="paramId_mgId"></span></td>
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
	var title = new tt.Field(" 标题 ", "focusSettingPojo.title").setMsgId("title_mgId");
	var startTimeStr = new tt.Field(" 开始显示时间 ", "focusSettingPojo.startTime").setMsgId("startTime_mgId");
	var sorting = new tt.Field(" 排序", "focusSettingPojo.sorting").setMsgId("sorting_mgId");
	var paramType = new tt.Field(" 参数类型 ", "focusSettingPojo.paramType").setMsgId("paramType_mgId");
    //var paramId = new tt.Field("参数id ", "focusSettingPojo.paramId").setMsgId("paramId_mgId");		
    	tt.vf.req.add(title,startTimeStr,sorting,paramType);
	tt.vf.num.add(sorting);
    new tt.LV().set(0, 50).add(title);
	tt.vf.int.add(sorting);
	new tt.NRV().set(0, '++').add(sorting);


	
	$(document).ready(function() {
	$("#paramId").hide();
	$("#paramUrl").hide();
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
	
	function setParamId(obj){
	    var val = obj.value;
	    if(val == '' || val == 0){
	    	$("#paramId").hide();
	    	$("#paramUrl").hide();
	    }else if(val==7){
	    	$("#paramId").hide();
	    	$("#paramUrl").show();
	    }else{
	    	$("#paramUrl").hide();
	    	$("#paramId").show();
	    }
    }  

</script>