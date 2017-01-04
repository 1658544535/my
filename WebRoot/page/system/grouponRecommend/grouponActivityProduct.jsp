<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script>
function addAll(url){
	if(confirm("确认要批量添加吗？")){
		var formParam = $("#idform").serialize();
		MAOWU.ajax.get(url, formParam, addRefreshPage);
	}else{
		return ;
	}
}
function addRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("添加成功");
		if("2" == "${zones}"){
			window.history.go(-1);
		} else if("1" == "${special}"){
			window.location.href="goSpecialGoods.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}";
		}
	} else{
		alert("添加失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">首页团购推荐管理</a> &gt; <a href="#">首页团购推荐管理</a> &gt; <a href="#">添加商品</a></div>  
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					<td align="right">商品分类:</td>
					    <td>
					    	<!--<select name="productTypeIds" id="productTypeIds" class="floatLeft" onChange="">
                            <option value="">全部</option>
							<s:iterator value="productTypeIdsList">
							<option value="<s:property value="id"/>" <s:if test="id == productTypeIds">selected="selected"</s:if>><s:property value="name"/></option>
							</s:iterator>
                            </select>-->
					    
					    <select name="productPojo.productType1" id="productType1" class="floatLeft" onChange="changeProType()">
                        <option value="">----- 一级类目 -----</option>
						<s:iterator value="productType1List">
						<option value="<s:property value="id"/>" <s:if test="id == productPojo.productType1">selected="selected"</s:if>><s:property value="name"/></option>
						</s:iterator>
                        </select>
                        <select class="floatLeft" name="productPojo.productTypeIds" id="productTypeIds" onChange="changePidType()"></select>
                        <select class="floatLeft" name="productPojo.productTypeId" id="productTypeId" ></select>
                        </td>
						<td align="right">商品ID、货号或名称</td>
						<td align="left"><input type="text" name="query"/></td>	
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
			<form action="#" id="idform" method="post" >
				<s:if test="special==1"><a class='Add_btn' onclick="addAll('addAllSpecialGoods.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}')">批量添加</a></s:if>
				<s:if test="zones==2"><a class='Add_btn' onclick="addAll('addAllZoneGoods.do?zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}')">批量添加</a></s:if>
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>商品编号${zoneGoodsPojo.zoneId}</th>
						<th>商品货号</th>
						<th>商品名称</th>
						<th>商品图</th>
						<th>销量</th>
						<th>团购价</th>
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
	 * 条件查询函数 
	 */
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("grouponActivityProductCnt.do", "grouponActivityProductList.do?randquery="+rand);
		}
	}
	
	/**
	 * 分页展现页面函数 
	 */
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td><input name='tids' type='checkbox' value='"+this.id +":"+this.productId +"'/></td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.imageMain + "' height='50px' /></td>"+
				"<td>"+ this.sellNumber + "</td>"+
				"<td>"+ this.price + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><s:if test="zones==1 || zones==2"><a class='edit_btn'  href='addZoneGoods.do?zones=${zones}&zoneGoodsPojo.zoneId=${zoneGoodsPojo.zoneId}&zoneGoodsPojo.activityId="+this.id+"&zoneGoodsPojo.productId="+this.productId+"'>添加</a></s:if>"+
				"<s:if test="special==1"><a class='edit_btn' href='addSpecialGoods.do?specialGoodsPojo.specialId=${specialGoodsPojo.specialId}&specialGoodsPojo.activityId="+this.id+"&specialGoodsPojo.productId="+this.productId+"'>添加</a></s:if>"+
				"<s:if test="seckill==1"><a class='edit_btn' href='addSeckillGoods.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&seckillGoodsPojo.activityId="+this.id+"&seckillGoodsPojo.productId="+this.productId+"'>添加</a></s:if>"+
				"<s:if test="special!=1 && zones!=1 && zones!=2 && seckill!=1"><a class='edit_btn' href='addGrouponRecommend.do?special=${special}&grouponRecommendPojo.activityId="+this.id +"&grouponRecommendPojo.productId="+this.productId+"'>显示</a></s:if></td>"
				</s:if>
				);
	}
	
		//替换商品
	function replaceProduct(id){
		parent.replacePro(id);
	}
	/**
	 *  首次要初始化分页
	 */
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "grouponActivityProductList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	
	function changeProType(){
		var aVal = $("select[name='productPojo.productType1']").val();
		$.ajax({
			url:"getProductType2.do?productTypePojo.level=1&productTypePojo.topLevel=" + aVal,
			success:function(data){
				var c = eval("(" + data + ")");
				$("#productTypeIds").html('<option value="">----- 二级类目 -----</option>');
				$("#productTypeId").html('<option value="">----- 三级类目 -----</option>');
				$("#productTypeIds").append(c[0].data);
			},
			error:function(){
				alert("error");
			}
		});
		
	}

	function changePidType(){
		var bVal = $("select[name='productPojo.productTypeIds']").val();
		$.ajax({
			url:"getProductType3.do?productTypePojo.pid=" + bVal,
			success:function(data){
				var c = eval("(" + data + ")");
				$("#productTypeId").html('<option value="">----- 三级类目 -----</option>');
				$("#productTypeId").append(c[0].data);
			},
			error:function(){
				alert("error");
			}
		});
		
	}
	
</script>
