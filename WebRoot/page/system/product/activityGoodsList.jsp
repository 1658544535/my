<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

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
			var url = "delActivityGoods.do?timeId=${timeId}&activityGoodsPojo.id="+val;	
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
			queryData("findActivityGoodsCount.do?timeId=${timeId}", "findActivityGoodsList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkActivityGoods.do?timeId=${timeId}&activityGoodsPojo.id="+val;	
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
			queryData("findActivityGoodsCount.do?timeId=${timeId}", "findActivityGoodsList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckActivityGoods.do?timeId=${timeId}&activityGoodsPojo.id="+val;	
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
			queryData("findActivityGoodsCount.do?timeId=${timeId}", "findActivityGoodsList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
	
	function deleteAll(){
		$("#idform").attr("action","delActivityGoodsAll.do?t=${t}&timeId=${timeId}");
		$("#idform").submit();
	}
	
	function checkAll(){
		$("#idform").attr("action","checkActivityGoodsAll.do?t=${t}&timeId=${timeId}");
		$("#idform").submit();
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav">
    <a href="#">活动商品管理</a> &gt;<c:if test="${t == 2}"><a href="thematicActivityManage.do?op=2">钱包专区</a>
		 &gt;<a href="#">钱包专区商品列表</a></c:if>
		 <c:if test="${t != 2}"><a href="activityTimeManage.do?op=1">限时秒杀管理</a>
		 &gt;<a href="#">限时秒杀商品列表</a></c:if></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="findActivityGoodsList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">商品货号：</td>
						<td><input type="text" name="activityGoodsPojo.productNum"
								id="activityGoodsPojo.productNum"
								value="<s:property value="activityGoodsPojo.productNum"/>"></td>
						<td align="right">商品名称：</td>
						<td><input type="text" name="activityGoodsPojo.productName"
								id="activityGoodsPojo.productName"
								value="<s:property value="activityGoodsPojo.productName"/>"></td>
				    	<td align="right">审核状态：</td>
								<td><select name="activityGoodsPojo.status" id="activityGoodsPojo.status"  class="floatLeft">
									<option value="">全部</option>
									<s:iterator value="statusSysDictList">
										<option value="<s:property value="value"/>"><s:property value="name" />
										</option>
									</s:iterator>
						    		</select>
						 </td>				    
					</tr>
						<tr>
						<td align="right">商品序号：</td>
						<td><input type="text" name="activityGoodsPojo.productNo"
								id="activityGoodsPojo.productNo"
								value="<s:property value="activityGoodsPojo.productNo"/>"></td>
						<td align="right">品牌：</td>
						<td><input type="text" name="activityGoodsPojo.brandName"
								id="activityGoodsPojo.brandName"
								value="<s:property value="activityGoodsPojo.brandName"/>"></td>
				    <!--	<td align="right">商品状态：</td>
								<td><select name="activityGoodsPojo.status" id="activityGoodsPojo.status"  class="floatLeft">
									<option value="">全部</option>
									<s:iterator value="statusSysDictList">
										<option value="<s:property value="value"/>"><s:property value="name" />
										</option>
									</s:iterator>
						    		</select>
						 </td>-->		</tr>			    
				
					
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
   <a class="delAll_btn" onclick="deleteAll()" >删除全部</a><a class="Add_btn" onclick="checkAll()" >审核全部</a>
   <form action="findActivityGoodsList.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<c:if test="${t != 2}"><th>活动日期</th></c:if>
    	<th>活动时间</th>
		<th>商品序号</th>
		<th>品牌</th>
		<th>商品名称</th>
		<th>商品货号</th>
		<th>商品图片</th>
		<th>排序</th>
		<th>商品原价</th>
		<th>商品活动价</th>
		<th>活动标示</th>
		<th>活动商品总量</th>
		<th>活动商品库存</th>
		<!--<th>活动商品SKU</th>-->
		<th>审核状态</th>
		<th width="10%">操作</th>
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
			queryData("findActivityGoodsCount.do?timeId=${timeId}", "findActivityGoodsList.do?timeId=${timeId}&randquery="+rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<c:if test="${t != 2}"><td>"+ this.activityDateStr + "</td></c:if>"+
				"<td>"+ this.activityTime + "</td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.brandName + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.productImage + "' height='100px' /></td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.sellPrice + "</td>"+
				"<td>"+ this.activePrice + "</td>"+
				"<td>"+ this.tips + "</td>"+
				"<td>"+ this.activityNum + "</td>"+
				"<td>"+ this.activityStock + "</td>"+
				//"<td>"+ this.skuLinkStr + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td><a class='edit_btn' href='productSkuLinkManage.do?productSkuLinkPojo.type=1&productSkuLinkPojo.activityId=${timeId}&productSkuLinkPojo.productId="+this.productId +"'>设置SKU</a>"+
				<c:if test="${type==2}">"<a class='edit_btn' href='goFindProduct.do?activityProductPojo.productId="+this.productId +"'>商品编辑</a>"+</c:if>
				"<a class='edit_btn' href='findActivityGoodsById.do?t=${t}&productSkuLinkPojo.productId="+this.productId +"&activityGoodsPojo.id="+this.id +"'>活动设置</a>"+
				"<a class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核"+
				"<a class='del_btn' onclick=del('"+ this.id + "')>删除</a></td></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "findActivityGoodsList.do?timeId=${timeId}");
		
		$("#query_btn").click(query);
	});
	
</script>