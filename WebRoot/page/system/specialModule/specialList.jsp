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
		<div class="s_nav"><a href="#">平台运营</a> &gt;<a href="#">专题模块</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					 <tr>
					<td>专题分类：
					<select name="specialPojo.specialTypeId" id="ticketType">
                    <option value="">--请选择--</option>
                    <option value="0">无分类</option>
				    <c:forEach items="${specialTypePojos}" var="specialType">
					<option value="${specialType.id}">${specialType.name}</option>
				    </c:forEach>
	                </select>
	                </td>
	                <td>审核状态：
	                <select name="specialPojo.status" id="specialPojo.status">
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
			<a class="Add_btn" href="goAddSpecial.do" >新增专场</a>
			<a class="Add_btn" href="goSpecialType.do" >新增专场分类</a>
			<a class="Add_btn" onclick="checkAll('checkSpecialAll.do')" >批量审核</a>
			<a class="delAll_btn" onclick="deleteAll('delSpecialAll.do')" >批量删除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>专题id</th>
						<th>专题分类</th>
						<th>标题</th>
						<th>专题顶部图</th>
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
			queryData("specialCnt.do", "specialList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var specialTypeName = "";
		if(this.specialTypeId == 0){
			specialTypeName = "无分类";
		}else{
		     specialTypeName = this.specialTypeName;
		}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+specialTypeName+"</td>"+
				"<td>"+ this.title + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/special/"+ this.image + "' height='50px' /></td>"+  
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' onclick='check(\"checkSpecial.do?id="+this.id +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckSpecial.do?id="+this.id +"\")'>取消审核</a>"+
				"<a class='edit_btn' href='goSpecialGoods.do?specialGoodsPojo.specialId="+this.id +"'>新增商品</a>"+
				"<a class='edit_btn' href='goEditSpecial.do?id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delSpecial.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "specialList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
