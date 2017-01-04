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
		<div class="s_nav"><a href="#"><s:if test="type==1">77专区管理</s:if><s:if test="type==2">新品专区表管理 </s:if></a> &gt;  <a href="#">专区表管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table" >
					<tr><td>审核状态：
	                <select name="zonesPojo.status" id="zonesPojo.status" align="center">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
				    		</select></td>
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
			<a class="Add_btn" href="goAddZones.do?type=${type}" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkZonesAll.do')" >批量审核</a>
			<a class="delAll_btn" onclick="deleteAll('delZonesAll.do')" >批量删除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>专区id</th>
						<th>标题</th>
						<th>专区顶部图</th>
						<!--<th>专区类型：1-7.7专区</th>-->
						<th>状态</th>
						<th>排序</th>
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
			queryData("zonesCnt.do?type=${type}", "zonesList.do?type=${type}&randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ this.title + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/zones/"+ this.image + "' height='50px' /></td>"+
				//"<td>"+ this.type + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' onclick='check(\"checkZones.do?id="+this.id +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckZones.do?id="+this.id +"\")'>取消审核</a>"+
				"<a class='edit_btn' href='goZoneGoods.do?zones=${type}&zoneGoodsPojo.zoneId="+this.id +"'>新增商品</a>"+
				"<a class='edit_btn' href='goEditZones.do?type=${type}&id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delZones.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "zonesList.do?type=${type}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
