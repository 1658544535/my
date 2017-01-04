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
	/*改变checkBox的状态*/
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


	/*删除*/
	function del(val) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delInfo.do?infoPojo.id=" + val;
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
			queryData("getInfoCount.do?infoPojo.type=${infoPojo.type}", "infoAllList.do?infoPojo.type=${infoPojo.type}&randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
	
	function update(val)
	{
		//alert(val);
		if(confirm("确认审核吗？"))
		{
			//alert(val);
			
			var url = "updateinfo.do?infoPojo.id="+val;	
			doupdateOpreator(url,null);
		}else{
			return ;
		}
		
	}
	function doupdateOpreator(url,params){
		MAOWU.ajax.get(url, params, goupdateRefreshPage);
	}
	 
	function goupdateRefreshPage(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getInfoCount.do?infoPojo.type=${infoPojo.type}", "infoAllList.do?infoPojo.type=${infoPojo.type}&randquery=" + rand);
		}else{
			alert("审核失败");
		}
	}
	
	function manySend(href){
		var form = document.getElementById("idform");
		form.action = href;//传想要跳转的路径
		form.submit();
		} 
	/*删除全部*/
	function deleteAll() {
	if (confirm("你真的想删除所选记录么？")) {
		manySend("infoDeleteId.do?infoPojo.type=${infoPojo.type}");
		//document.getElementById("idform").submit();
		} else {
			return;
		}
	}
	/*审核全部*/
	function updateAll(){
	if(confirm("确认审核所选记录吗？"))
		{
			manySend("verifyAllPushNotice.do?infoPojo.type=${infoPojo.type}");            
            return true;
		}else{
			return ;
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">资讯信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div id="search_show" style="">
			<form action="infoManage.do?infoPojo.type=${infoPojo.type}" method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">标题</td>
						<td align="left"><input type="text" name="infoPojo.title"
							id="infoPojo.title"
							value="<s:property
								value="infoPojo.title" />" /></td>
						<%-- <td align="right">所属类型</td>
						<td align="left">
						<select name="infoPojo.type" id="">
						 		<option value="">全部</option>
								<s:iterator value="infoTypeList">
									<option value="<s:property value="value"/>"
									<s:if test="value == infoPojo.type">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
						</td> --%>
					</tr>
					<tr align="right">
						<td align="right" colspan="4">
							<div class="floatRight">
								<input type="hidden" name="page.pageNo"  id="pageNo">
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
			<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="updateAll()">批量审核</a>	
			<a class="delAll_btn" onclick="deleteAll()">批量删除</a>
			<a class="Add_btn" href="goAddInfo.do?infoPojo.type=${infoPojo.type}">新增资讯信息</a></s:if>
			<form action="infoDeleteId.do?infoPojo.type=${infoPojo.type}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<!--<th>资讯类型</th>-->
						<th>标题</th>
						<th>来源</th>
						<th>状态</th>
						<th>日期</th>
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
	</div>
</body>
</html>

<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getInfoCount.do?infoPojo.type=${infoPojo.type}", "infoAllList.do?infoPojo.type=${infoPojo.type}&randquery=" + rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><!--<td>"
								+ this.typeName
								+ "</td>--><td>"
								+ "<a href='goUpdateInfo.do?infoPojo.id="
								+ this.id + "'>"+this.title+"</a>"
								+ "</td><td>"
								+ this.author
								+ "</td><td>"
								+ this.statusName
								+ "</td><td>"
								+ this.create_date
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ "<a class='edit_btn' onclick=update('"+this.id+"')>审核</a>"
								+ "<a class='del_btn' onclick=del('"
								+ this.id
								+ "')>删除</a>"
								+ "<a class='edit_btn' href='goUpdateInfo.do?infoPojo.id="
								+ this.id + "'>编辑</a></td></s:if></tr>");
	}

	$(function() {
		/**
		  *首次要初始化分页
		 **/
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"infoAllList.do?infoPojo.type=${infoPojo.type}&randIni=" + rand);
		$("#query_btn").click(query);
		
	});

</script>