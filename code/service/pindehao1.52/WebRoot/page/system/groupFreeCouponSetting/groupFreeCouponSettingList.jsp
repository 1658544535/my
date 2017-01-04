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
		<div class="s_nav"><a href="#">团免券管理</a> &gt; <a href="#">团免券链接列表</a></div>
		<!-- <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div> -->
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="goAddGroupFreeCouponSetting.do" >新增</a>
			<%--
			<a class="Add_btn" onclick="checkAll('checkGroupFreeCouponSettingAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckGroupFreeCouponSettingAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delGroupFreeCouponSettingAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>链接</th>
						<th>券人数</th>
						<th>失效时间</th>
						<th>领取人数</th>
						<th>状态</th>
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
			queryData("groupFreeCouponSettingCnt.do", "groupFreeCouponSettingList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var statusStr = "未审核";
		if(this.status == 1){
			statusStr = "已审核";
		}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td><a href='"+ this.url + "' target='_blank'>"+ this.url + "</a></td>"+
				"<td>"+ this.num + "</td>"+
				"<td>"+ this.invalidTimeStr + "</td>"+
				"<td>"+ (this.num - this.surplusNum) + "</td>"+
				"<td>"+ statusStr + "</td>"+
				"<s:if test='#session.role.roleId!=7'>"+
				"<td><a class='edit_btn' onclick='check(\"checkGroupFreeCouponSetting.do?id="+this.id +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckGroupFreeCouponSetting.do?id="+this.id +"\")'>取消审核</a>"+
				"<a class='edit_btn' href='goEditGroupFreeCouponSetting.do?id="+this.id +"'>编辑</a>"+
				//"<a class='del_btn' onclick='del(\"delGroupFreeCouponSetting.do?id="+this.id+"\")'>删除</a>"+
				"</td>"+
				"</s:if>"
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init("${page.rowCount}", "groupFreeCouponSettingList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
