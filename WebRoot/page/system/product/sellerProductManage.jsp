<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/seller/css/default.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/seller/css/seller_common.css" type="text/css" media="all" /> --%>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/css/pageSellerMartshowItems-9b61bc2a35m.css" type="text/css" media="all" />
<style>.ui-popup-stock{visibility:hidden;width:660px;margin-left:-330px;margin-top:-300px;text-align:center;z-index:2412;}.ui-popup-stock .title{font-size:14px;color:#666;line-height:20px;height:30px;}.ui-popup-stock p{text-align:left;line-height:23px;}.ui-popup-stock .batch-operate{text-align:right;border-top:1px solid #f0f0f0;padding:10px 0;margin-top:10px;}.ui-popup-stock .batch-operate input,.ui-popup-stock input{width:68px;height:20px;line-height:20px;border:1px solid #ececec;padding:0 5px;margin:0 5px 0 2px;border-radius:2px;}.ui-popup-stock .batch-operate input:hover,.ui-popup-stock .batch-operate input:active,.ui-popup-stock input:hover,.ui-popup-stock input:active{border:1px solid #ff4965;}.ui-popup-stock .batch-operate button{height:23px;background:#f5f5f5;padding:0 5px;border:1px solid #e6e6e6;color:#666;line-height:23px;width:75px;outline:0;}.ui-popup-stock .batch-operate button:hover{background:#fff;border:1px solid #ff4965;color:#ff4965;}.ui-popup-stock .btn-info{margin-top:10px;}.ui-popup-stock table{width:660px;border:1px solid #ececec;}.ui-table th{background:#f6f6f6;font-weight:normal;color:#999;}.ui-table td{text-align:left;color:#666;}.ui-table tr:nth-child(even),.ui-table-split,.ui-table-hover{background:#fff;}.ui-popup-stock .des{text-align:left;height:30px;line-height:30px;}.ui-popup-stock .des span a{color:#ff4965;display:inline-block;}.ui-popup-stock button{outline:0;}.ui-popup-stock .btn-save{margin-right:10px;}.ui-popup-stock .btn-cancel{border:1px solid #ececec;border-radius:2px;color:#666;background:#fff;}.ui-popup-stock .btn-save:hover{background:#f56c81;}.ui-popup-stock .btn-cancel:hover{color:#ff4965;border:1px solid #ff4965;}.ui-popup-stock th i{margin-left:2px;cursor:pointer;}.oflow-hide{overflow-y:auto;max-height:406px;width:677px;}</style>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<style>
.inTable{border-collapse:collapse}
.inTable td,.inTable tr{border:none!important;background:none!important;padding:5px 5px!important;}
.skuTable{border-collapse:collapse}
.skuTable th{background:#BAB8B7!important;padding:5px 5px!important;}
.skuTable td,.skuTable tr{padding:5px 5px!important;}
.skuTable tbody tr:hover{background-color:#2CA6E5; }
.skuTable tr:nth-child(even) {background-color:#FCEFD7;}
</style>
<script type="text/javascript">
$(function(){
	$("#excel").click(function() {
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				$(location).attr('href', 'getProductExcel.do?'+formParam);
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

function checkAll(){
	$("#idform").attr("action","sellerCheckProductAll.do").submit();
}

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "deleProduct.do?productPojo.id="+val;	
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
			queryData("getCountProduct.do?productPojo.userId=${productPojo.userId}", "getProductAll.do?productPojo.userId=${productPojo.userId}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val)
	{
		//alert(val);
		if(confirm("确认要通过审核吗？"))
		{
			//alert(val);
			
			var url = "checkProduct.do?productPojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage1);
	}
	 
	function goRefreshPage1(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getCountProduct.do?productPojo.userId=${productPojo.userId}", "getProductAll.do?productPojo.userId=${productPojo.userId}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val)
	{
		//alert(val);
		if(confirm("确认要取消审核吗？"))
		{
			//alert(val);
			
			var url = "uncheckProduct.do?productPojo.id="+val;	
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
			queryData("getCountProduct.do?productPojo.userId=${productPojo.userId}", "getProductAll.do?productPojo.userId=${productPojo.userId}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
	function setActivity(val1, val2)
	{	
		var url = "setActivityProduct.do?activityProductPojo.productId="+val1+"&activityProductPojo.type="+val2;	
		doActivityOpreator(url,null);
	}
	function doActivityOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage3);
	}
	 
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("设置成功");
			queryData("getCountProduct.do?productPojo.userId=${productPojo.userId}", "getProductAll.do?productPojo.userId=${productPojo.userId}&randdelete="+rand);
		}else{
			alert("设置失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><c:if test="${!empty productPojo}"><a href="#">品牌管理</a> &gt; <a href="#">账户信息</a> &gt;</c:if><a href="#">商品管理</a> &gt; <a href="#">商家商品信息管理</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="productManage.do?productPojo.userId=${productPojo.userId}" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">公司名：</td>
						<td><label><input type="text" name="productPojo.remarks"
								id="productPojo.remarks"
								value="<s:property value="productPojo.remarks"/>"></label></td>
						<td align="right">商品序号：</td>
						<td><label><input type="text" name="productPojo.productNo"
								id="productPojo.productNo"  
								value="<s:property value="productPojo.productNo"/>"></label></td>
						<td align="right">商品名称：</td>
						<td><label><input type="text" name="productPojo.productName"
								id="productPojo.productName"
								value="<s:property value="productPojo.productName"/>"></label></td>
					</tr>
					<tr>
					<td align="right">商品货号：</td>
						<td><label><input type="text" name="productPojo.productNum"
								id="productPojo.productNum"
								value="<s:property value="productPojo.productNum"/>"></label></td>
					<td align="right">商品状态：</td>
						<td><select name="productPojo.productStatus" id="productPojo.productStatus"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">已审核</option>
							<option value="0">未审核</option>
				    		</select><div id="scale_mgId"></div></td>				    
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
  <s:if test="#session.role.roleId!=7"><c:if test="${!empty productPojo and isProType != 1}">  <a href="goproductAdd.do?productPojo.userId=${productPojo.userId}" class="Add_btn">新增商品</a> </c:if>
  <a class="Add_btn"  onclick="checkAll()" >批量审核</a>
  <!--<a class="delAll_btn"  onclick="deleteAll()" >删除全部</a>-->
  <input type="button" value="导出EXCEL"  id="excel" class="submit_btn" style="float: right;"  /></s:if>
  <form action="productDeleteId.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <!-- <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th> -->
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<th >商品信息</th>
    	<th>商品SKU</th>
    	<s:if test='#session.role.roleId!=7'><th>操作</th></s:if>
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
<!--批量修改-->
<style>
	.miniDialog_mask {
    position: fixed;
    _position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 2399;
    background-color: rgba(0,0,0,.6);
    background: -moz-radial-gradient(center,ellipse cover,rgba(0,0,0,.35) 0,rgba(0,0,0,.35) 15%,rgba(0,0,0,.47) 38%,rgba(0,0,0,.8) 100%);
    background: -webkit-gradient(radial,center center,0,center center,100%,color-stop(0,rgba(0,0,0,.35)),color-stop(15%,rgba(0,0,0,.35)),color-stop(38%,rgba(0,0,0,.47)),color-stop(100%,rgba(0,0,0,.8)));
    background: -webkit-radial-gradient(center,ellipse cover,rgba(0,0,0,.35) 0,rgba(0,0,0,.35) 15%,rgba(0,0,0,.47) 38%,rgba(0,0,0,.8) 100%);
    background: -o-radial-gradient(center,ellipse cover,rgba(0,0,0,.35) 0,rgba(0,0,0,.35) 15%,rgba(0,0,0,.47) 38%,rgba(0,0,0,.8) 100%);
    background: -ms-radial-gradient(center,ellipse cover,rgba(0,0,0,.35) 0,rgba(0,0,0,.35) 15%,rgba(0,0,0,.47) 38%,rgba(0,0,0,.8) 100%);
    background: radial-gradient(ellipse at center,rgba(0,0,0,.35) 0,rgba(0,0,0,.35) 15%,rgba(0,0,0,.47) 38%,rgba(0,0,0,.8) 100%);
}
</style>
<div class="miniDialog_mask" style="display: none;"></div>
        <div class="J_skuInfoDetail ui-popup ui-popup-stock ui-table">
            <div class="close">
                <span class="J_hideThisTwo">
                    x
                </span>
            </div>
            <div class="title">
                批量修改
            </div>
            <p>
                1. 修改商品库存为淘竹马的统一库存，修改后，所有特卖活动将按新库存进行售卖。
            </p>
            <p>
                2. 若有特卖正在售卖中，更新库存可能导致超卖，请谨慎操作。
            </p>
            <!-- <p>
                3. 特卖价格必须大于等于15元。
            </p> -->
            <div class="batch-operate">
                <span>
                    特卖价格
                </span>
                <input type="text" class="J_batchPrice" value="" onkeyup="value=value.replace(/[^\d.]/g,'')"
                onafterpaste="value=value.replace(/[^\d.]/g,'')" maxlength="10">
                &nbsp;
                <span>
                    库存
                </span>
                <input type="text" class="J_batchNums" value="" onkeyup="this.value=this.value.replace(/\D/g,'')"
                onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="10">
                &nbsp;
                <button type="button" class="J_batchFillCoding">
                    批量填充
                </button>
            </div>
            <form action="#" id="batchModifyForm"  method="post" >
            <div class="oflow-hide">
                <table>
                    <thead>
                        <tr align="left">
                            <th>
                                颜色
                            </th>
                            <th>
                                尺寸
                            </th>
                            <th>
                                原价
                            </th>
                            <th>
                                本专场特卖价格
                            </th>
                            <th>
                                库存
                            </th>
                            <th>
                                商家编码
                            </th>
                            <th>
                                审核状态
                            </th>
                        </tr>
                    </thead>
                    <tbody id="batchModify">
                    </tbody>
                </table>
            </div>
            </form>
            <div class="btn-info">
                <button class="J_btnSaveStock btn-save">
                    保存
                </button>
                <button class="J_hideThisTwo btn-cancel">
                    取消
                </button>
            </div>
        </div>
        <!--批量修改-->
</body>
</html>


	


<script type="text/javascript">
var page = 1;
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getCountProduct.do?productPojo.userId=${productPojo.userId}", "getProductAll.do?productPojo.productTypeId=${productPojo.productTypeId}&productPojo.userId=${productPojo.userId}&randquery="+rand);
	}
}



	//库存批量修改显示
	function listBatchModify(pid){
		$(".J_skuInfoDetail").css("visibility","visible");
		$(".miniDialog_mask").show();
		var parameter = "";
		MAOWU.ajax.get("getProductSkuLinkAll.do?page.pageNo="+page+"&productSkuLinkPojo.type=0&productSkuLinkPojo.activityId=0&productSkuLinkPojo.productId="+pid, parameter, listEachModify);
	}
	
	function listEachModify(skuResult){
		var rand=Math.random() * ( 100000 + 1);
		var c = eval("(" + skuResult + ")");
		$("#batchModify").html("");
		$.each(c, initModify);
	}
	
	function initModify(){
		var statusColorB = "",statusColorE = "";
		if(this.status == 1){
			statusColorB = "<span style='color: #e72645;' class='price'>";
			statusColorE = "</span>";
		}
		
		$("#batchModify").append( 
				"<tr sku-id='#' event-id='#' product-id='#'>"+
				"<input name='tids' type='hidden' value="+this.id +" />"+
				"<td>"+this.colorValue+"</td>"+
				"<td>"+this.formatValue+"</td>"+
				"<td>"+this.sellingPrice+"</td>"+
				"<td><input class='J_priceKeyDown' type='text' name='batchPrice' value='"+this.price+
				"' onkeyup='value=value.replace(/[^\d.]/g,'')' onafterpaste='value=value.replace(/[^\d.]/g,'')' maxlength='10' ></td>"+
				"<td><input class='J_numKeyDown' type='text' name='batchStock' value='"+this.stock+
				"' onkeyup='this.value=this.value.replace(/\D/g,'')' onafterpaste='this.value=this.value.replace(/\D/g,'')' maxlength='10'></td>"+
				"<td><input type='text' name='batchBusCode' value='"+this.businessCode+"' ></td>"+
				"<td>"+statusColorB+""+this.statusName+""+statusColorE+"</td></tr>");
	}
	
	//批量修改提交
	function submitBatchMod(){
		var parameter = $("#batchModifyForm").serializeArray();
		MAOWU.ajax.get("batchModifySku.do", parameter, goRefreshPage3);
	}
	
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			$(".J_skuInfoDetail").css("visibility","hidden");
			$(".miniDialog_mask").hide();
			MAOWU.page.init(<s:property value="page.rowCount"/>,
					"getProductAll.do?productPojo.productTypeId=${productPojo.productTypeId}&productPojo.userId=${productPojo.userId}&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand);
			
		}else{
			alert("修改失败");
		}
	}
	
	//批量修改
	/* $(".J_setBatchModify").on("click",function(){
				$(".J_skuInfoDetail").css("visibility","visible");
				$(".miniDialog_mask").show();
			}); */
			$(".J_hideThisTwo").on("click",function(){
				//$(this).parents(".ui-popup").hide();
				$(".J_skuInfoDetail").css("visibility","hidden");
				$(".miniDialog_mask").hide();
			});
			$(".J_batchFillCoding").on("click",function(){
				$(".J_priceKeyDown").val($(".J_batchPrice").val());
				$(".J_numKeyDown").val($(".J_batchNums").val());
			});
			$(".J_btnSaveStock").off("click");
			$(".J_btnSaveStock").on("click",function(){
				submitBatchMod();
			});
	
	//SKU展示
	var skuBody = "";
	function listSkus(pid){
		var parameter = "";
		MAOWU.ajax.get("getProductSkuLinkAll.do?page.pageNo="+page+"&productSkuLinkPojo.type=0&productSkuLinkPojo.activityId=0&productSkuLinkPojo.productId="+pid, parameter, listEachSku);
	}
	
	function listEachSku(skuResult){
		var rand=Math.random() * ( 100000 + 1);
		var c = eval("(" + skuResult + ")");
		skuBody = "";
		$.each(c, initSku);
	}
	
	function initSku(){
		var statusColorB = "",statusColorE = "";
		if(this.status == 1){
			statusColorB = "<span style='color: #e72645;' class='price'>";
			statusColorE = "</span>";
		}
		
		skuBody += 
				"<tr data-sku-id='"+this.id+"' class='m-sku-tr'>"+
				"<td class='m-sku-elem'>"+this.colorValue+"</td>"+
				"<td class='m-sku-elem'>"+this.formatValue+"</td>"+
				"<td><span style='color: #e72645;' class='price'>¥"+this.price+"</span></td>"+
				"<td><span class='stock'>"+this.stock+"</span></td>"+
				"<td><span class='outerId'>"+this.businessCode+"</span></td>"+
				"<td>"+statusColorB+""+this.statusName+""+statusColorE+"</td>"+
				"</tr>";
	}
	
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		listSkus(this.id);
		var pageButton = "";
		getPageNo(this.id);
		if(skuBody != ""){
			var skuPageNo = parseInt(skuPageRowCount/10) + 1;
			pageButton = "<div class='pageInfo'>总共 <i>"+skuPageRowCount+"</i> 条/<i>"+skuPageNo+"</i>页</div>";
			if(skuPageRowCount > 10){
				pageButton += "<span class='pageButton' onclick='sku_back("+this.id +")'> < </span><span class='pageButton' onclick='sku_forward("+this.id +")'> > </span>";
			}
		}
         
		/*$("#body").append(
				"<tr>"+
				//"<td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td style='width: 10%;'><!-- <ul class='item-list view-MartshowItemList'>"+
				"<li class='item view-MartshowItem' apply-id='#' product-id='#' style='height: 230px;'> -->"+
				//"<div class='item-info'>"+
				"<div class='info-title'><strong>"+ this.productName + "</strong></div>"+
				//"<div class='info-detail'>"+
				"<div class='J_detailImg detail-img'>"+
				"<img src='upfiles/product/small/"+this.image+"' width='100px' height='100px'></div>"+
				"</td><td style='width: 10%;'><ul class='detail-cont' style='word-wrap: break-word;'>"+
				"<li><span class='cont-name'>序号：</span><span>"+ this.productNo + "</span></li>"+
				"<li><span class='cont-name'>货号：</span><span>"+ this.productNum + "</span></li>"+
				"<li><span class='cont-name'>品牌：</span><span>"+ this.brandName+"</span></li>"+
				"<li><span class='cont-name'>公司名：</span><span>"+ this.name+"</span></li>"+
				"<li><span class='cont-name'>商品ID：</span><span>"+ this.id+"</span></li>"+
                "<li product-id='#' class='J_status status-4'>"+
				"<font color='red'><span class='cont-name' style='line-height: 20px;'>状态：</span><span>"+this.statusName+"</span>"+
				"</span></font></li></ul><!-- </div></div></li></ul> --></td>"+
				"<td style='width: 60%;'><table class='item-sku' style='width: 100%;'>"+
				"<thead><tr>"+
				"<th style='width: 10%;'>颜色</th>"+
				"<th style='width: 10%;'>尺寸</th>"+
				"<th style='width: 10%;'>本专场特卖价格</th>"+
				"<th style='width: 10%;'>剩余库存</th>"+
				"<th style='width: 10%;'>商家编码</th>"+
				"</tr></thead>"+
				"<tbody></tbody>"+skuBody+"</table></td>"+
				"<s:if test='#session.role.roleId!=7'><td style='width: 10%;'>"+
				"<a href='javascript:;' class='edit_btn' onclick='listBatchModify("+this.id+")'>批量修改</a>"+
				"<a class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				"<a class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"</td></s:if>"+
				"</tr>"
				);*/
		$("#body").append(
				"<tr>"+
				"<td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td style='width: 20%;'><table width='100%' border='0' class='inTable'>"+
	  			"<tr><td colspan='3' style='text-align:center;'><strong>"+ this.productName +"</strong></td></tr>"+
	  			"<tr><td rowspan='6'><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/"+ this.image +"' width='100px' height='100px'/></td>"+
	  			"<td style='text-align:left;'>序号：</td>"+
	  			"<td style='text-align:left;'>"+ this.productNo +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>货号：</td>"+
	  			"<td style='text-align:left;'>"+ this.productNum +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>品牌：</td>"+
	  			"<td style='text-align:left;'>"+ this.brandName +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>公司名：</td>"+
	  			"<td style='text-align:left;'>"+ this.name +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>商品ID：</td>"+
	  			"<td style='text-align:left;'>"+ this.id +"</td></tr>"+
                "<tr><td style='text-align:left;'><font color='red'>状态：</font></td>"+
	  			"<td style='text-align:left;'><font color='red'>"+ this.statusName +"</font></td></tr>"+
	  			"<tr><td style='text-align:left;'>焦点图数量："+ this.focusImageCount +"</td>"+
	  			"<td style='text-align:left;'>商品图片集数量：</td>"+
	  			"<td style='text-align:left;'>"+ this.imageCount +"</td></tr></table></td>"+
                "<td style='width: 60%;vertical-align:top;'><table class='skuTable' style='width: 100%;'>"+
                "<thead>"+
				"<th style='width: 10%;'>颜色</th>"+
				"<th style='width: 10%;'>尺寸</th>"+
				"<th style='width: 10%;'>本专场特卖价格</th>"+
				"<th style='width: 10%;'>剩余库存</th>"+
				"<th style='width: 10%;'>商家编码</th>"+
				"<th style='width: 10%;'>审核状态</th>"+
				"</thead>"+
				"<tbody name='sku_"+this.id +"'>"+skuBody+"</tbody></table>"+pageButton+"</td>"+
				"<s:if test='#session.role.roleId!=7'><td style='width: 20%;'>"+
				"<a href='javascript:;' class='edit_btn' onclick='listBatchModify("+this.id+")'>批量修改</a>"+
				"<a class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				"<a class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a><br>"+
				"<a class='edit_btn' target='_blank' href='/goPreviewWeb.do?productSkuLinkPojo.activityId="+this.activityId+"&productPojo.id="+this.id +"'>商品预览</a>"+
				"</td></s:if>"+
				"</tr>"
				);
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getProductAll.do?productPojo.productTypeId=${productPojo.productTypeId}&productPojo.userId=${productPojo.userId}&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
	var skuPageRowCount = 0;
	function sku_back(pId){
		if(page > 1){
			page--;
			listSkus(pId);
			var id = "tbody[name='sku_"+pId+"']";
			$(id).html("");
			$(id).append(skuBody);
		}else{
			alert("已经是最前一页了！");
		}
	}
	
	function sku_forward(pId){
		getPageNo(pId);
		var skuPageNo = skuPageRowCount/10;
		if(page <= skuPageNo){
			page++;
			listSkus(pId);
			var id = "tbody[name='sku_"+pId+"']";
			$(id).html("");
			$(id).append(skuBody);
		}else{
			alert("总共"+page+"页，已经是最后一页了！");
		}
	}
	
	function getPageNo(pid){
		$.ajax({
			type: "post",
			url: "getProductSkuLinkCount.do?productSkuLinkPojo.type=0&productSkuLinkPojo.activityId=0&productSkuLinkPojo.productId="+pid,
			dataType: 'json',
			async: false,
			success: function (msg) {
				skuPageRowCount = msg;
			}
		});
	}
</script>
<style>
.pageButton {
    background: #fff;
    border: 1px solid #e3e3e3;
    border-radius: 14px;
    color: #666;
    display: inline-block;
    font-weight: 700;
    height: 28px;
    line-height: 28px;
    margin: 0 3px;
    padding: 0 10px;
    text-decoration: none;
}
.pageButton:hover {
    background-color: #ff647c;
    border-radius: 15px;
    color: #fff;
    display: inline-block;
    font-weight: 700;
    height: 30px;
    line-height: 30px;
    margin: 0 3px;
    padding: 0 11px;
}
.pageInfo {
    font-size: 14px;
    color: #666;
    float: left;
}
</style>