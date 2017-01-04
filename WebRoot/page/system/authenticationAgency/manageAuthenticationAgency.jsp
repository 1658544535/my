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
			var url = "delAuthenticationAgency.do?agencyAuthenticationPojo.user_id=" + val;
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
			queryData("getAuthenticationAgencyAllCount.do", "authenticationAgencyAllList.do?randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">代理商账户管理 </a> &gt; <a href="#">实名认证</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div id="search_show" style="">
			<form action="authenticationAgencyManage.do" method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户</td>
						<td align="left"><input type="text" name="agencyAuthenticationPojo.user_name"
							id="agencyAuthenticationPojo.user_name"
							value="<s:property
								value="agencyAuthenticationPojo.user_name" />" /></td>
						<td align="right">显示状态</td>
						<td align="left">
						<select name="agencyAuthenticationPojo.auth_status" id="">
						 		<option value="">全部</option>
								<s:iterator value="authenticationAuthStatusList">
									<option value="<s:property value="value"/>"
									<s:if test="value == agencyAuthenticationPojo.auth_status">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
						</td>
					</tr>
					<tr align="right">
						<td align="right" colspan="4">
							<div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input type="hidden" name="type" value="<s:property value="type"/>"
								id="type">
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
			<a class="Add_btn" onclick="checkAll()">批量认证</a>
			<form action="verifyAllAuthenticationAgency.do?type=${type}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户</th>
						<th>实名</th>
						<th>身份证号</th>
						<th>认证资料</th>
						<th>状态</th>
						<th>提交时间</th>
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
			queryData("getAuthenticationAgencyAllCount.do", "authenticationAgencyAllList.do?randquery=" + rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.user_id +"   /></td><td>"
								+ this.user_name
								+ "</td><td>"
								+ this.realName
								+ "</td><td>"
								+ this.id_num
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/authentication/"+ this.auth_licence + "' height='50px' />"
								+ "</td><td>"
								+ this.statusName
								+ "</td><td>"
								+ this.create_dateStr
								+ "</td><td>"
								+ "<a class='edit_btn' href='goCheckAuthenticationAgency.do?type=${type}&agencyAuthenticationPojo.user_id="
								+ this.user_id + "'>认证</a>"
								+ "<a class='edit_btn' href='goUpdateAuthenticationAgency.do?type=${type}&agencyAuthenticationPojo.user_id="
								+ this.user_id + "'>编辑</a>"
								//+ "<a class='del_btn' href='delAuthenticationAgency.do?agencyAuthenticationPojo.user_id="+this.user_id +"'onclick='javascript:return window.confirm(\"确定删除？(该代理商账户信息将被删除)\");'>删除</a></td></tr>");
				
								+"<a class='del_btn' onclick=del('"
								+ this.user_id
								+ "')>删除</a>"
								+"</td></tr>");
	}
	

	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"authenticationAgencyAllList.do?randIni=" + rand);
		$("#query_btn").click(query);
		
	});

</script>