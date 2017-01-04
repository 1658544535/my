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
		<div class="s_nav"><a href="goSpecial.do">专题模块</a>  &gt; <a href="goEditSpecial.do?id=${specialGoodsPojo.specialId}">修改专题</a> &gt; <a href="#goSpecialGoods.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}">专题商品列表</a></div>
		<!--<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>-->
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
		<!--	<div id="search_show" style="">
				
					<tr>
						<td align="right"></td>
						<td></td>		
					</tr>
				</table>
				
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>	<table width="100%" border="0" class="Search_table">
			-->
			<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="goGrouponActivityProduct.do?special=1&specialGoodsPojo.specialId=${specialGoodsPojo.specialId}" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkSpecialGoodsAll.do')" >批量审核</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckSpecialGoodsAll.do')" >批量取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delSpecialGoodsAll.do')" >批量删除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>商品编号</th>
						<th>商品货号</th>
						<th>商品名称</th>
						<th>商品图片</th>
						<th>商品销量</th>
						<th>团购价</th>
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
			queryData("specialGoodsCnt.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}", "specialGoodsList.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}&randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='50px' /></td>"+
				"<td>"+ this.sellNumber + "</td>"+
				"<td>"+ this.groupPrice + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td><input type='text' name='sorting' value='"+ this.sorting + "' onblur='update(this.value, "+ this.id +");''></td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' onclick='check(\"checkSpecialGoods.do?id="+this.id +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckSpecialGoods.do?id="+this.id +"\")'>取消审核</a>"+
				"<a class='del_btn' onclick='del(\"delSpecialGoods.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "specialGoodsList.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	   /**
	 **  修改排序
	 **/
	function update(sorting,specialGoodsId)
	{
		if(sorting != null && sorting != "")
		{
			var url = "updateSpecialGoodsSorting.do?specialGoodsPojo.id="+specialGoodsId+"&specialGoodsPojo.sorting="+sorting;
			doOpreator(url,null);
		}else{
			alert("请输入排序");
		}

	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			queryData("specialGoodsCnt.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}", "specialGoodsList.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}&randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
</script>
