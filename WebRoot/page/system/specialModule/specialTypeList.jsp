<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script>
function check2(url){
	if(confirm("确认通过审核吗？")){
		MAOWU.ajax.get(url, null, checkRefreshPage2);
	}else{
		return ;
	}
}
function checkRefreshPage2(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("审核成功");
		query();
	} else{
		alert("审核失败");
	}
}
function check3(url){
	if(confirm("确认取消审核吗？")){
		MAOWU.ajax.get(url, null, checkRefreshPage3);
	}else{
		return ;
	}
}
function checkRefreshPage3(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("取消审核成功");
		query();
	} else{
		alert("取消审核失败");
	}
}

function check4(url){
	if(confirm("确认设置为显示吗？")){
		MAOWU.ajax.get(url, null, checkRefreshPage4);
	}else{
		return ;
	}
}
function checkRefreshPage4(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("设置成功");
		query();
	} else{
		alert("设置失败");
	}
}

function check5(url){
	if(confirm("确认设置为隐藏吗？")){
		MAOWU.ajax.get(url, null, checkRefreshPage5);
	}else{
		return ;
	}
}
function checkRefreshPage5(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("设置成功");
		query();
	} else{
		alert("设置失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">专题模块</a> &gt; <a href="#">专题分类</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="50%" border="0" class="Search_table">
					<tr>
						<td align="right">显示状态：</td>
						<td>
							<select name="specialTypePojo.statusDisplay" id=""  class="floatLeft">
								<option value="">全部</option>
								<option value="0">隐藏</option>
								<option value="1">显示</option>
				    		</select>
						</td>
						<td align="right">审核状态：</td>
						<td>
							<select name="specialTypePojo.status" id=""  class="floatLeft">
								<option value="">全部</option>
								<option value="0">未审核</option>
								<option value="1">已审核</option>
				    		</select>
						</td>
						<td>
							<input id="query_btn" type="button" class="submit_btn" value="查询" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
					
				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="goAddSpecialType.do" >新增</a>
			<a class="Add_btn" href="goSpecial.do" hight="100px">专题活动列表</a>
			<%--
			<a class="Add_btn" onclick="checkAll('checkSpecialTypeAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckSpecialTypeAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delSpecialTypeAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
					<!--	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th> -->
						<th>名称</th>
						<th>排序</th>
						<th>添加时间</th>
						<th>显示状态</th>
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
			queryData("specialTypeCnt.do", "specialTypeList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var statusStr = "";
		var statusDisplayStr = "";
		var statusStr2 = "";
		var statusDisplayStr2 = ""; 
		if(this.status==0){
			statusStr   = "未审核";
			statusStr2  = "<a class='edit_btn' onclick='check2(\"checkSpecialType.do?id="+this.id +"\")'>设置为已审核</a>";
			this.status = 1;
		}else if(this.status==1){
			statusStr   = "已审核";
			statusStr2  = "<a class='edit_btn' onclick='check3(\"uncheckSpecialType.do?id="+this.id +"\")'>设置为未审核</a>";
			this.status = 0;
		}
		if(this.statusDisplay==0){
			statusDisplayStr   = "隐藏";
		    statusDisplayStr2  = "<a class='edit_btn' onclick='check4(\"goUpdateStatusDisplay.do?id="+this.id +"&statusDisplay=1\")'>设置为显示</a>";
		    this.statusDisplay = 1;
		}else if(this.statusDisplay==1){
			statusDisplayStr   = "显示";
		    statusDisplayStr2  = "<a class='edit_btn' onclick='check5(\"goUpdateStatusDisplay.do?id="+this.id +"&statusDisplay=0\")'>设置为隐藏</a>";
		    this.statusDisplay = 0;
		}
	
	
		$("#body").append(
				//"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<tr><td>"+ this.name + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.creatDateString + "</td>"+
				"<td>"+ statusDisplayStr + "<br /><a>"+ statusDisplayStr2 + "</a></td>"+
				"<td>"+ statusStr + "<br />"+ statusStr2 + "</td>"+
				<s:if test="#session.role.roleId!=7">
				//"<td><a class='edit_btn' onclick='check(\"checkSpecialType.do?id="+this.id +"\")'>审核通过</a>"+
				//"<a class='edit_btn' onclick='uncheck(\"uncheckSpecialType.do?id="+this.id +"\")'>取消审核</a>"+
				"<td><a class='edit_btn' href='goEditSpecialType.do?id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delSpecialType.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "specialTypeList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
