<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script language='javascript'>
	function funclick(){
		document.getElementById("ddd").style.display="none";
		}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">信息统计</a> &gt; <a href="#">总订单记录</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="getEleAllorderlist.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
										<!-- 这里做了限制只能选择今天以前的日期(包括今天) -->
					<tr>
					<td align="right">开始日期：</td>
					<td><input name="beganday" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/></td>
					<td align="right">结束日期：</td>
					<td><input name="endday" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/></td>
					
				</table>
				<input type="hidden" name="page.pageNo" id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
		<s:if test="#session.role.roleId!=7">
		<form action="ImportEleExcel.do" method="post" id="formImport" enctype="multipart/form-data">
			<div class="floatLeft">
			<input type="file" name="importupfile" class="floatLeft">
			<input type="submit" value="导入EXCEL" class="submit_btn" style="float:left;"  />
			</div>
			<div class="Clear"></div>
		</form>
		</s:if>
		<div class="h15"></div>
		<div>
			
			<form id="idform" method="post">
				<div id="ddd">
				<table width="100%" border="0" class="Info_list_table">
				<th>总订单数量<input readonly="readonly" type="text" value="${count}"/></th><th>淘竹马订单<input readonly="readonly" type="text" value="${taozhumacount}"/><th><th>电商订单<input readonly="readonly" type="text" value="${dianshangcount}"/><th>
				</table>
				</div>
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<!--<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>-->
						<th>类型</th>
						<th>订单号</th>
						<th>订单总金额</th>
						<th>商品总数量</th>
						<th>收货人</th>
						<th width="30%">收货地址</th>
						<th>收货人电话</th>
						<th>时间</th>
						<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
					</tr>
					<tbody id="body"></tbody>
				</table>
			<div class="page">
				<div class="floatleft">
					总共 <i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
			</form>
		
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	var count="${count}";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getEleAllorderlistCount.do", "getEleAllorderlist.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		var sqlString="";
		var s=this.type;
		if(s=="电商订单"){
			sqlString="<a class='edit_btn' href='dianshangDetail.do?id="+this.id+"'>订单详情</a>";
		}
		else{
			sqlString="<a class='edit_btn' href='taozhumaDetail.do?id="+this.id+"'>订单详情</a>";
		}
		$("#body")
				.append(
						"<tr><!--<td><input  name='tids' type='checkbox' value="+this.id +"   /></td>--><td>"
								+ this.type
								+ "</td><td>"
								+ this.orderNo
								+ "</td><td>"
								+ this.pay
								+ "</td><td>"
								+ this.count
								+ "</td><td>"
								+ this.consignee
								+ "</td><td>"
								+ this.consigneeAddress
								+ "</td><td>"
								+ this.consigneePhone
								+ "</td><td>"
								+ this.time
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+sqlString
								+ "</td></s:if></tr>"
                         );
	}

	$(function() {
		// 首次要初始化分页
		//alert(this.createDateString);
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getEleAllorderlist.do?randIni=" + rand);
		$("#query_btn").click(query);
	});

</script>