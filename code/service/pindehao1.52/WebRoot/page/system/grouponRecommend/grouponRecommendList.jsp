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
		<div class="s_nav"><a href="#">首页团购推荐管理</a> &gt; <a href="#">首页团购推荐管理</a></div>
		<div class="h15"></div>
		<div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		 </div>
		<!-- 查询开始 -->
		<form action="" method="post" id="sysform">
				<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">商品ID/名称：</td>
						<td><label><input type="text" name="keywords" value=""></label></td>
					</tr>					    
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					   <input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				       <div class="Clear"></div>
			    </div>
		</form>
		<!-- 查询结束 -->
		<div>
			<a class="Add_btn" href="goGrouponActivityProduct.do" >点击添加</a>
			<a class="delAll_btn" onclick="deleteAll('delGrouponRecommendAll.do')" >批量移除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
					    <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>商品ID</th>
						<th>商品图片</th>
						<th>商品名称</th>
						<th>销量</th>
						<th>团购价</th>
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
			queryData("grouponRecommendCnt.do", "grouponRecommendList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
	           	"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.productId + "</td><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.imageMain + "' height='50px' /></td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.sellNumber + "</td>"+
				"<td>"+ this.price + "</td>"+   
				"<td><input type='text' name='sorting' value='"+ this.sorting + "' onblur='update(this.value, "+ this.id +");''></td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='del_btn' onclick='del(\"delGrouponRecommend.do?id="+this.id+"\")'>移除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "grouponRecommendList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
   /**
	 **  修改排序
	 **/
	function update(sorting,grouponRecommendId)
	{
		if(sorting != null && sorting != "")
		{
			var url = "updateGrouponRecommend.do?grouponRecommendPojo.id="+grouponRecommendId+"&grouponRecommendPojo.sorting="+sorting;
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
			queryData("grouponRecommendCnt.do", "grouponRecommendList.do?randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
</script>
