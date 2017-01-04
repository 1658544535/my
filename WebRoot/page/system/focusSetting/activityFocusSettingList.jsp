<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">图片综合管理</a> &gt; <a href="#">活动顶部图片管理</a></div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div>
			<a class="Add_btn" href="goAddActivityFocusSetting.do" >新增</a>
			<%--
			<a class="Add_btn" onclick="checkAll('checkFocusSettingAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckFocusSettingAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delFocusSettingAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>图片预览图</th>
						<th>类型</th>
						<th>开始显示时间</th>
						<th>审核状态</th>
						<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
					</tr>
					<tbody id="body"></tbody>    
				</table>
			</form>
			<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	/**
	 ** 条件查询函数 
	 **/
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("activityFocusSettingCnt.do", "activityFocusSettingList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
	if(this.type == 2){
	var typeName = "猜价格";
	}else if(this.type == 3){
	var typeName = "0.1抽奖";
	}else if(this.type == 4){
	var typeName = "免费抽奖";
	}else{
	var typeName = "首页";
	}
	if(this.status == 0){
	var setStatus = "<a  class='edit_btn' onclick='check(\"checkFocusSetting.do?id="+this.id +"\")'>设置已审核</a>";
	}else{
	var setStatus = "<a  class='edit_btn' onclick='uncheck(\"uncheckFocusSetting.do?id="+this.id +"\")'>设置未审核</a>";
	}
		$("#body").append(
				"<tr height='100px'><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/focusbanner/"+ this.banner + "' height='50px' /></td>"+  
				"<td>"+ typeName + "</td>"+   
				"<td>"+ this.startTimeStr + "</td>"+  
				"<td>"+ this.statusName + "<p>"+setStatus+"</p></td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' href='goEditActivityFocusSetting.do?id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delFocusSetting.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "activityFocusSettingList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
