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
$(function(){
	$("#excel").click(function() {
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				$(location).attr('href', 'getProductExcel2.do?'+formParam);
			}
		});
});

function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = true; 
		} 
		} 
	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = false
		
		} 
		} 
	}

}

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val,grouponId)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			var url = "deleProduct.do?productPojo.id="+val+"&grouponActivityPojo.id="+grouponId;	
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
			queryData("getCountProduct.do", "getProductAll.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
	<s:if test="productPojo.tongji=1">
	<div class="s_nav"><a href="productListManage.do?productPojo.tongji=1">信息统计</a> &gt; <a href="#">商品点击量</a></div>
	</s:if>
	<s:else>
    <div class="s_nav"><a href="productListManage.do">商品管理</a> &gt; <a href="#">商品列表</a></div>
    </s:else>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="productListManage.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="80%" border="0" class="Search_table">
				<tr>
					<td align="right">商品分类：</td>
					<td align="left">
			        <select name="productPojo.productType1" id="productType1" class="floatLeft"   onChange="changeProType()">
                        <option value="">----- 一级类目 -----</option>
					<s:iterator value="productType1List">   
					<option value="<s:property value="id"/>" <s:if test="id == productPojo.productType1">selected="selected"</s:if>><s:property value="name"/></option>
					</s:iterator>
                    </select>
                    <select class="floatLeft" name="productPojo.productTypeIds" id="productTypeIds" onChange="changePidType()"></select>
                    <select class="floatLeft" name="productPojo.productTypeId" id="productTypeId" ></select></td>
                    
                    <td style="display: none;"><select class="floatLeft" name="productPojo.orderBy" id="productPojo.orderBy">
										<option value="">全部</option>
										<option value="1">按销量</option>
										<option value="2">按价格</option>
									</select></td>
					</tr>
					<tr>
						<td align="right">商品ID、货号或名称：</td>
						<td><label><input type="text" name="productPojo.productNameOrId"
								id="productPojo.productNameOrId"
								value="<s:property value="productPojo.productNameOrId"/>"></label></td>
						<td align="right">状态筛选：</td>
						<td><select class="floatLeft" name="productPojo.activityStatus" id="productPojo.activityStatus">
										<option value="">全部</option>
										<option value="1">销售中</option>
										<option value="0">未上架</option>
									</select></td>
						<td><input id="query_btn" type="button" class="submit_btn" value="查询" /></td>

					</tr>
                    
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">

				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
  <div class="h15"></div>
   <div>
	<a class="submit_btn" href="#" onclick="query_btn2(1)">按销量</a>
    <a class="submit_btn" href="#" onclick="query_btn2(2)">按价格</a>
   <s:if test="#session.role.roleId!=7"><input type="button" value="导出EXCEL"  id="excel" class="submit_btn" style="float: right;"  /></s:if>
  <form action="productDeleteId.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
		<%--<th>公司名</th>--%>
		<th>商品ID</th>
		<th>活动ID</th>
		<th>货号</th>
		<th>商品名称</th>
		<!--<th>商品图片</th>-->
		<th>单购价</th>
		<th>团购价</th>
		<th>库存</th>

		<th>状态</th>
		<th>组团人数</th>

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
  
  
  
  <!---->


</div>
</body>
</html>


	


<script type="text/javascript">
var pageNB=0;

var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		<s:if test="productPojo.tongji=1">
		queryData("getCountProduct.do?productPojo.activityType=1&productPojo.tongji=1", "getProductAll.do?productPojo.activityType=1&productPojo.tongji=1&randquery="+rand);
		</s:if>
		<s:else>
		queryData("getCountProduct.do?productPojo.activityType=1", "getProductAll.do?productPojo.activityType=1&randquery="+rand);
	    </s:else>
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var statusStr="";
		var status2Str="";
		var deleteStr="";
		var tongji="${productPojo.tongji}";
		//console.log(this.activityStatus);
				if(this.status2==1){
				statusStr  = "销售中";
				this.status2=0;
				status2Str  = "下架";
				}else if(this.status2==0){
				statusStr  = "未上架";
				this.status2=1;
				status2Str = "上架";
				if(tongji==""){
					deleteStr="<a class='edit_btn' onclick=del('"+this.id+"','"+this.grouponId+"')>删除</a>";
				 }
				}
		//var typeStr="";
		//		if(this.type==1){
		//		typeStr  = "普通拼团";
		//		}else if(this.type==2){
		//		typeStr  = "团免";
		//		}else if(this.type==3){
		//		typeStr  = "猜价";
		//		}
		pageNB = $("#pageNo").val();
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				//"<td>"+ this.name + "</td>"+
				"<td>"+ this.id + "<br /><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.imageMain + "' height='50px' /></td>"+
				"<td>"+ this.grouponId + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td>"+ this.productName + "<a class='edit_btn' style='float:right' href='goFindProductName.do?id="+this.id +"&page.pageNo="+pageNB+"'></a></td>"+
				//"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px'/></td>"+
				"<td>"+ this.distributionPrice + "</td>"+
				"<td>"+ this.price + "</td>"+
				"<td>"+ this.stock + "<a class='edit_btn' style='float:right' href='goProductStock.do?id="+this.id +"&grouponId="+this.grouponId+"&page.pageNo="+pageNB+"'></a></td>"+
				//"<td>"+ this.productName + "</td>"+

				"<td>"+statusStr+"</td>"+
				//"<td>"+ this.statusName + "</td>"+
				"<td><a href='goGroRecList.do?activityType=1&gaActivityId="+this.grouponId+"'>"+ this.numNow1 + "</a></td>"+

				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='goFindProduct.do?productPojo.id="+this.id +"'>编辑</a>"+
				"<a class='edit_btn' href='productStatusUpdate.do?grouponActivityPojo.id="+this.grouponId +"&grouponActivityPojo.status="+this.status2+"'>"+status2Str+"</a>"+
				deleteStr+
				//"<a class='edit_btn' href='qrcodeDownload.do?productPojo.id="+this.id +"'>二维码</a>"+
				"</td></s:if></tr>"
				);
	}
	
	$(function() {
		pageNumber = "${page.pageNo}";
		/**
		  首次要初始化分页
		 **/
		var getProductAllStr = "";
		var rand=Math.random() * ( 100000 + 1);
		<s:if test="productPojo.tongji=1">
			getProductAllStr = "getProductAll.do?productPojo.activityType=1&productPojo.tongji=1&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand;
		</s:if>
		<s:else>
			getProductAllStr = "getProductAll.do?productPojo.activityType=1&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand;
		</s:else>
		MAOWU.page.init(<s:property value="page.rowCount"/>,getProductAllStr);
		$("#query_btn").click(query);
	});
		function changeProType(){
		var aVal =$("#productType1").val();		
		if(aVal==""){
		$("#productTypeIds").html("");	
		$("#productTypeId").html("");
		}else{
		$("#productTypeId").html("");
		$.ajax({
			url:"getProductType2.do?productTypePojo.level=1&productTypePojo.topLevel=" + aVal,
			success:function(data){
				var c = eval("(" + data + ")");
				$("#productTypeIds").html('<option value="">----- 二级类目 -----</option>');
				$("#productTypeIds").append(c[0].data);
			},
			error:function(){
				alert("error");
			}
		});
		
		}
		
	}
	
	function changePidType(){
		var bVal = $("select[name='productPojo.productTypeIds']").val();
		if(bVal==""){
		$("#productTypeId").html("");
		}else{
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
	}
	
	function query_btn2(o){
		$("select[name='productPojo.orderBy']").val(o);
		$("#query_btn").trigger('click');
	}
</script>