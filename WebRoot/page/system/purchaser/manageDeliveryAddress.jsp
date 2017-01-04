<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	function allcb() {
		var checkbox = document.getElementById("selectcb");
		if (checkbox.checked == true) {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = true;
				}
			}

		} else {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = false
				}
			}
		}
	}

	//批量审核
	function checkAll() {
		document.getElementById("idform").submit();
	}
	
	//删除全部
	function deleteAll() {
		document.getElementById("idform").submit();
	}

	//删除
	function del(val) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delDeliveryAddress.do?deliveryAddressPojo.id=" + val;
			doOpreator(url, null);
		} else {
			return;
		}
	}
	
	function doOpreator(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	//刷新
	function goRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("删除成功");
			queryData("getDeliveryAddressCount.do", "deliveryAddressAllList.do?randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>采购商账户管理</a> &gt; <a href="">我的收货地址</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div id="search_show" style="">
			<form action=deliveryAddressManage.do method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户昵称</td>
						<td align="left"><input type="text" name="deliveryAddressPojo.userName"
							id="deliveryAddressPojo.userName"
							value="<s:property
								value="deliveryAddressPojo.userName" />" /></td>
						<td align="right">收货人</td>
						<td align="left"><input type="text" name="deliveryAddressPojo.consignee"
							id="deliveryAddressPojo.consignee"
							value="<s:property
								value="deliveryAddressPojo.consignee" />" /></td>
						<td align="right"></td>
						<td align="left"></td>		
					</tr>
					<tr align="center">
						<td align="right">收货地址</td>
						<td align="left"><input type="text" name="deliveryAddressPojo.address"
							id="deliveryAddressPojo.address"
							value="<s:property
								value="deliveryAddressPojo.address" />" /></td>
					</tr>
					<tr align="right">
						<td align="right" colspan="4">
							<div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input id="query_btn" type="button" class="submit_btn"
									value="查询" />
							</div>
						</td>
					</tr>
				</table>
				<div class="Clear"></div>
			</form>
		</div>
		<div class="h15"></div>
		<div>
			<input type="hidden" name=""
				value="" class="floatLeft" id="merNamffe">
			<a class="Add_btn" onclick="checkAll()">批量审核</a>
			<form action="checkAllDeliveryAddressById.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户昵称</th>
						<th>收货人</th>
						<th>收货人电话</th>
						<th>邮编</th>
						<th>地址</th>
						<th>省份</th>
						<th>城市</th>
						<th>区域</th>
						<th>操作</th>
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
			queryData("getDeliveryAddressCount.do", "deliveryAddressAllList.do?randquery=" + rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
								+ this.userName
								+ "</td><td>"
								+ this.consignee
								+ "</td><td>"
								+ this.consigneePhone
								+ "</td><td>"
								+ this.postcode
								+ "</td><td>"
								+ this.address
								+ "</td><td>"
								+ this.provinceName
								+ "</td><td>"
								+ this.cityName
								+ "</td><td>"
								+ this.areaName
								+ "</td><td><a class='del_btn' onclick=del('"
								+ this.id
								+ "')>删除</a>"
								+ "<!--<a class='edit_btn' href='goCheckDeliveryAddress.do?deliveryAddressPojo.id="
								+ this.id + "'>--><!--审核</a>--></td></tr>");
	}

	$(function() {
		/**
		  *首次要初始化分页
		 **/
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"deliveryAddressAllList.do?randIni=" + rand);
		$("#query_btn").click(query);
		
	});

</script>