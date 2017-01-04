<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
$(function(){
		$("#excel").click(function() {			
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				var formParam1 = $("#idform").serialize();
				var allformParam = formParam+"&"+formParam1;
				$(location).attr('href', 'getOperationsExcel.do?'+allformParam);
			}
		});
})
</script>

<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">信息统计</a> &gt; <a href="#">运营数据统计</a>
		</div>
		<div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>

		<!-- 查询开始 -->
        <form action="operationsManage.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr><td align="right">日期：</td>
				<td width="80px"  style="padding: 0px 0px;"><input style="min-width:60px;" name="orderPojo.createDateStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
			    <td> 到<input style="min-width:60px;" name="orderPojo.createDateEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
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
		
		<div class="h15"></div>
		<div>
		<input type="button" value="导出所有结果"  id="excel" class="submit_btn" style="float: right;"  />
		<form action="" id="idform" method="post">
			   <table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
				        <th>订单日期</th>
				        <th>订单号</th>
				        <th>商品ID</th>
				        <th>品牌名称</th>
				        <th>商家名称</th>
				        <th>商品名称</th>
				        <th>商品货号</th>
				        <th>单价</th>
				        <th>数量</th>
				        <th>省份</th>
				       
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
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getOperationsCount.do", "operationsAllList.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
	
		$("#body").append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
					    "<td>"+ this.createDateStr + "</td>"+
				        "<td>"+this.orderNo+"</td>"+
				        "<td>"+this.productId+"</td>"+
				        "<td>"+ this.brand + "</td>"+
				        "<td>"+ this.name + "</td>"+
				        "<td>"+ this.productName + "</td>"+
				        "<td>"+ this.productNum + "</td>"+
				        "<td>"+ this.stockPrice + "</td>"+
				        "<td>"+ this.num + "</td>"+
				        "<td>"+ this.provinces + "</td></tr>"
				        );
	}
	


	$(function() {
		// 首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "operationsAllList.do?randIni=" + rand);
		
		$("#query_btn").click(query);
		
	
	});
      
</script>