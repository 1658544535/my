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
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="productListManage.do">商品管理</a> &gt; <a href="#">商品库存</a></div>
  <div class="h15"></div>
   <div>
  <form action="productstockUpdate.do" id="from1"  method="post" >
  <input type="hidden" name="page.pageNo" value="${page.pageNo == null ? 0 : page.pageNo}"/>
  	<table width="60%" border="0" class="Info_list_table">
    <tr>
		<th>套餐类型</th>
		<th>颜色分类</th>
		<th>库存</th>
    </tr>
    <tbody id="body"></tbody>
    </table>
    <div style="width:60%" class="Btn_div">
    <input type="button" value="返回" class="back_btn"
				onclick="window.history.back()" />   <input id="submitId"
				name="submitId" type="button" class="ok_btn" value="修改" />
	</div>
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
		queryData("findProductStockCount.do?id=${id}", "findProductStockList.do?id=${id}&randquery="+rand);
	}
}
$(document).ready(function() {
	$("#submitId").click(function(){
		document.getElementById("from1").submit();
	});
});


	/**
	 *分页展现页面函数 
	 **/
	 
	function installPage() {
		pageNB = $("#pageNo").val();
		$("#body").append(
				"<tr>"+
				"<td>"+ this.skuFormat + "</td>"+
				"<td>"+ this.skuColor + "</td>"+
				"<td><input type='hidden' name='stockArray' id='stockArray"+this.productSkuId+"' value='"+ this.stock + ":"+this.productSkuId+"'><input onkeyup='test("+this.productSkuId+");' type='text' id='stock"+this.productSkuId+"' value='"+ this.stock + "'></td></tr>"
				);
	}
	var stockId;
    var skuId;
	function test(SkuId){
		stockId="stock"+SkuId;
		skuId="stockArray"+SkuId;
		var stock= $("#"+stockId).val();
		$("#"+skuId).val(stock+":"+SkuId);
	}

	$(function() {
	/**
	  首次要初始化分页
	 **/
	 var rand=Math.random() * ( 100000 + 1);
	MAOWU.page.init(<s:property value="page.rowCount"/>,
			"findProductStockList.do?id=${id}&randIni="+rand);
	$("#query_btn").click(query);
	
	
	});
	
</script>