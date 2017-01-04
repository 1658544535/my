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
		if(confirm("确认审核所选记录吗？"))
		{
			document.getElementById("idform").submit();       
            return true;
		}else{
			return ;
		}
	}
	
	/*
	function deleteAll() {
		document.getElementById("idform").submit();
	}
	*/
	/*删除*/
	function del(val) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delUserAttestation.do?userAttestationPojo.userId=" + val;
			doOpreator(url, null);
		} else {
			return;
		}
	}
	
	function doOpreator(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	/*刷新*/
	function goRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("删除成功");
			queryData("getUserAttestationCount.do", "userAttestationAllList.do?randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">供应商账户管理 </a> &gt; <a href="#">店铺认证</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div id="search_show" style="">
			<form action=userAttestationManage.do method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户</td>
						<td align="left"><input type="text"
							name="userAttestationPojo.userName"
							id="userAttestationPojo.userName"
							value="<s:property
								value="userAttestationPojo.userName" />" /></td>
						<td align="right">显示状态</td>
						<td align="left"><select name="userAttestationPojo.status"
							id="">
								<option value="">全部</option>
								<s:iterator value="attestationStatusList">
									<option value="<s:property value="value"/>"
										<s:if test="value == userAttestationPojo.status">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select></td>
					</tr>
					<tr align="right">
						<td align="right" colspan="4">
							<div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input type="hidden" name="type"
									value="<s:property value="type"/>" id="type"> <input
									id="query_btn" type="button" class="submit_btn" value="查询" />
							</div>
						</td>
					</tr>
				</table>
				<div class="Clear"></div>
			</form>
		</div>

		<div class="h15"></div>
		<div>
			<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="checkAll()">批量审核</a></s:if>
			<form action="verifyAllUserAttestation.do?type=${type}" id="idform"
				method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户</th>
						<th>营业执照</th>
						<th>3C认证</th>
						<th>状态</th>
						<th>提交时间</th>
						<s:if test="#session.role.roleId!=7">
							<th>操作</th>
						</s:if>
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
			queryData("getUserAttestationCount.do", "userAttestationAllList.do?randquery=" + rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.userId +"   /></td><td>"
								+ this.userName
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/attestation/"+ this.attestationImage + "' height='50px' />"
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/attestation/"+ this.user3cImage + "' height='50px' />"
								+ "</td><td>"
								+ this.statusName
								+ "</td><td>"
								+ this.createDateStr
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ "<a class='edit_btn' href='goCheckUserAttestation.do?type=${type}&userAttestationPojo.userId="
								+ this.userId + "'>审核</a>"
								+ "<a class='edit_btn' href='goUpdateUserAttestation.do?type=${type}&userAttestationPojo.userId="
								+ this.userId + "'>编辑</a>"
								+"<a class='del_btn' onclick=del('"
								+ this.userId
								+ "')>删除</a>"
								+"</td></s:if></tr>");
	}

	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userAttestationAllList.do?randIni=" + rand);
		$("#query_btn").click(query);
		
	});

</script>