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
		<div class="s_nav"><a href="goZones.do?type=${zones}"><s:if test="zones==1">77专区管理</s:if><s:if test="zones==2">新品专区表管理 </s:if></a> &gt; <a href="goEditZones.do?type=${zones}&id=${zoneGoodsPojo.zoneId}">专区编辑</a>&gt;<a href="goZoneGoods.do?zones=${zones}&zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}">专区商品表管理</a></div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="goGrouponActivityProduct.do?zones=${zones}&zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkZoneGoodsAll.do')" >批量审核</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckZoneGoodsAll.do')" >批量取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delZoneGoodsAll.do')" >批量删除</a>
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
			queryData("zoneGoodsCnt.do?zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}", "zoneGoodsList.do?zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}&randquery="+rand);
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
				"<td><a class='edit_btn' onclick='check(\"checkZoneGoods.do?id="+this.id +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckZoneGoods.do?id="+this.id +"\")'>取消审核</a>"+
				"<a class='del_btn' onclick='del(\"delZoneGoods.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "zoneGoodsList.do?zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	
		   /**
	 **  修改排序
	 **/
	function update(sorting,zoneGoodsId)
	{
		if(sorting != null && sorting != "")
		{
			var url = "updateZoneGoodsSorting.do?zoneGoodsPojo.id="+zoneGoodsId+"&zoneGoodsPojo.sorting="+sorting;
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
			queryData("zoneGoodsCnt.do?zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}", "zoneGoodsList.do?zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}&randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
</script>
