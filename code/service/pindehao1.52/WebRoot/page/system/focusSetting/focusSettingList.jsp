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
		<div class="s_nav"><a href="#">图片综合管理</a> &gt; <a href="#">首页顶部图片管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">标题</td>
						<td align="left"><input type="text" name="focusSettingPojo.title" id="focusSettingPojo.title" /></td>
						<td align="right">审核状态</td>
						<td align="left">
						 <select name="focusSettingPojo.status" id="">
						 		<option value="">--全部--</option>
									<option value="0">未审核</option>
									<option value="1">已审核</option>
						</select>
						</td>
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="goAddFocusSetting.do" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkFocusSettingAll.do')" >批量审核</a>
			<a class="delAll_btn" onclick="deleteAll('delFocusSettingAll.do')" >批量删除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>图片预览图</th>
						<th>图片标题</th>
						<th>排序</th>
						<th>发布时间</th>
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
			queryData("focusSettingCnt.do", "focusSettingList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
	if(this.status == 0){
	var setStatus = "<a  class='edit_btn' onclick='check(\"checkFocusSetting.do?id="+this.id +"\")'>设置已审核</a>";
	}else{
	var setStatus = "<a  class='edit_btn' onclick='uncheck(\"uncheckFocusSetting.do?id="+this.id +"\")'>设置未审核</a>";
	}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/focusbanner/"+ this.banner + "' height='50px' /></td>"+     
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.startTimeStr + "</td>"+
				"<td>"+ this.statusName + "<p>"+setStatus+"</p></td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' href='goEditFocusSetting.do?id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delFocusSetting.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "focusSettingList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
