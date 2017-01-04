<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript"
src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	function allcb(){
		var checkbox=document.getElementById("selectcb"); 
		if(checkbox.checked==true){
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox") { 
					code_Values[i].checked = true; 
				} 
			} 
		}else{	
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox"){ 
					code_Values[i].checked = false
				} 
			} 
		}
	}

	function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "deletePackageProduct.do?sceneId=${sceneId}&sceneProductPojo.id="+val;	
			doOpreator(url,null);
		}else{
			return ;
		}
		
	}
	
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	 
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			queryData("packageProductCount.do?sceneId=${sceneId}", "packageProductAllList.do?sceneId=${sceneId}&randquery="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkPackageProduct.do?sceneProductPojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	}
	
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("packageProductCount.do?sceneId=${sceneId}", "packageProductAllList.do?sceneId=${sceneId}&randquery="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckPackageProduct.do?sceneProductPojo.id="+val;	
			undoOpreator(url,null);
		}else{
			return ;
		}
	}
	
	function undoOpreator(url,params){
		MAOWU.ajax.get(url, params, ungoRefreshPage);
	}
	 
	function ungoRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("取消审核成功");
			queryData("packageProductCount.do?sceneId=${sceneId}", "packageProductAllList.do?sceneId=${sceneId}&randquery="+rand);
		}else{
			alert("取消审核失败");
		}
	}
	
	function deleteAll(){
		$("#idform").attr("action","deletePackageProductAll.do?sceneId=${sceneId}");
		$("#idform").submit();
	}
	
	function checkAll(){
		$("#idform").attr("action","checkPackageProductAll.do?sceneId=${sceneId}");
		$("#idform").submit();
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <!-- <div class="s_nav"><a href="#">活动商品管理</a> &gt;<a href="#">首页套餐设置</a></div> -->
    <div class="s_nav"><a href="homePackageManage.do">套餐商品设置</a> &gt;<a href="#">套餐商品详情列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr>
				<td align="right">商品标题：</td>
				<td><input type="text" name="sceneProductPojo.title"></td>
				<td align="right">商品名称：</td>
				<td><input type="text" name="sceneProductPojo.productName"></td>
				</tr>
				<%-- <tr>
				<td align="right">状态：</td>
				<td><select name="sceneProductPojo.status">
				 		<option value="">全部</option>
				 		<option value="0">未审核</option>
				 		<option value="1">已审核</option>
					</select></td>
				</tr> --%>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
   <div class="h15"></div>
   <div>
   <s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()" >批量删除</a>
   <!--<a class="Add_btn" onclick="checkAll()" >批量审核</a>-->
   <a class="Add_btn" href="packageProductAddInfo.do?type=2&sceneId=${sceneId}&productPojo.productStatus=1" >添加套餐商品</a></s:if>
   <form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th width='20px'><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>套餐名称</th>
		<th>商品名称</th>
		<th>商品图片</th>
		<th>商品标题</th>
		<th>商品原价</th>
		<th>商品价格</th>
		<!-- <th>推荐介绍</th> -->
		<!--<th>是否主推</th>-->
		<!-- <th>预览</th> -->
		<!-- <th>原价</th>
		<th>套餐活动价</th>
		<th>套餐活动商品总量</th>
		<th>套餐活动商品库存</th>
		<th>状态</th>
		<th>排序</th> -->
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
    </tr>
    <tbody id="body"></tbody>
      
    </table>
    </form>
    <div class="page">
		<div class="floatleft">
			总共 <i id="rowcount">${page.rowCount }</i> 条/<i id="pagecount"></i>页
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
	function query() {
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("packageProductCount.do?sceneId=${sceneId}", "packageProductAllList.do?sceneId=${sceneId}&randquery="+rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td width='20px'><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.sceneName + "</td>"+
				"<td>"+ this.productName + "</td>"+
				//"<td><img src='/upfiles/homePackage/packageProduct/"+ this.image + "' height='100px'/></td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/"+ this.image + "' height='100px'/></td>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.sellingPrice + "</td>"+
				"<td>"+ this.distributionPrice + "</td>"+
				//"<td>"+ this.introduction + "</td>"+
				//"<td>"+ this.isIntroduceName + "</td>"+
				//"<td>"+ this.previewProName + "</td>"+
				/* "<td>"+ this.sellPrice + "</td>"+
				"<td>"+ this.scenePrice + "</td>"+
				"<td>"+ this.sceneNum + "</td>"+
				"<td>"+ this.sceneStock + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.sorting + "</td>"+ */
				"<s:if test="#session.role.roleId!=7"><td>"+
				//"<a class='edit_btn' href='.do?t=2'>添加套餐商品</a>" + */
				//"<a class='edit_btn' href='.do?t=2')>设置套餐商品</a>"+
				"<a class='edit_btn' href='packageProductAdd.do?t=2&sceneId=${sceneId}&sceneProductPojo.id="+this.id +"'>编辑</a>"+
				//"<a class='del_btn' onclick=check('"+this.id+"')>审核</a>"+
				//"<a class='del_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"<a class='del_btn' onclick=del('"+this.id+"')>删除</a></td></s:if></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "packageProductAllList.do?sceneId=${sceneId}");
		
		$("#query_btn").click(query);
	});
	
</script>