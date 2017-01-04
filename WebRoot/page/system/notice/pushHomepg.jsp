<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
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
	function del(val1,val2) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delPushHomePage.do?pushHomePagePojo.id=" + val1 + "&pushHomePagePojo.type=" + val2;
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
			alert("删除成功");//浏览器阻止创建更多的的多画框对话框
			queryData("pushHomePageListCount.do?type=${type}", "pushHomePageList.do?type=${type}&randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
	function doOpreator1(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage1);
	}

	/*刷新*/
	function goRefreshPage1(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("审核成功");//浏览器阻止创建更多的的多画框对话框
			queryData("pushHomePageListCount.do?type=${type}", "pushHomePageList.do?type=${type}&randquery=" + rand);
		} else {
			alert("审核失败");
		}
	}
	function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkPushHomePage.do?pushHomePagePojo.id="+val;	
			doOpreator1(url,null);
		}else{
			return ;
		}
		
	}
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckPushHomePage.do?pushHomePagePojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	}
	function doOpreator2(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	/*刷新*/
	function goRefreshPage2(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("取消审核成功");//浏览器阻止创建更多的的多画框对话框
			queryData("pushHomePageListCount.do?type=${type}", "pushHomePageList.do?type=${type}&randquery=" + rand);
		} else {
			alert("取消审核失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">推送综合管理</a> &gt; 
			<c:if test="${type==0}"><a href="#">首页顶部推送管理</a></c:if>
			<c:if test="${type==1}"><a href="#">电动遥控玩具推送管理</a></c:if>
			<c:if test="${type==2}"><a href="#">早教玩具推送管理</a></c:if>
			<c:if test="${type==3}"><a href="#">过家家玩具推送管理</a></c:if>
			<c:if test="${type==4}"><a href="#">儿童童车推送管理</a></c:if>
			<c:if test="${type==5}"><a href="#">益智玩具推送管理</a></c:if>
			<c:if test="${type==6}"><a href="#">其他玩具推送管理</a></c:if>
			<c:if test="${type>6}"><a href="#">玩具推送管理</a></c:if>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div id="search_show" style="">
			<form action= "pushHomePageList.do" method="post" id="sysform">
				<input type="hidden" name="pushHomePagePojo.type" value="${type}" id="pushHomePagePojo.type">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
					<td align="right">标题</td>
					<td align="left"><input type="text" name="pushHomePagePojo.title"
						id="pushHomePagePojo.title" value="<s:property value="pushHomePagePojo.title" />" /></td>
					<td align="right">状态</td>
					<td align="left">
					<select name="pushHomePagePojo.status" id="">
				 		<option value="">全部</option>
						<s:iterator value="statusSysDictList">
							<option value="<s:property value="value"/>"
							<s:if test="value == pushHomePagePojo.status">selected="selected"</s:if>><s:property
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
								<input id="query_btn" type="button" class="submit_btn" value="查询" />
							</div>
						</td>
					</tr>
				</table>
				<div class="Clear"></div>
			</form>
		</div>
       
		<div class="h15"></div>
		<div>
		<s:if test="#session.role.roleId!=7"><a class="delAll_btn"  onclick="deleteAll()" >批量删除</a>
		<a class="Add_btn"  onclick="checkAll()" >批量审核</a>
			<a class="Add_btn" href="pushHomePageAdd.do?type=${type}">新增信息</a></s:if>
			<form action="pushHomePageList.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
						<th>标题</th>
						<th width="150px">图片</th>
						<th>状态</th>
						<th>发布时间</th>
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
			queryData("pushHomePageListCount.do?type=${type}", "pushHomePageList.do?type=${type}&randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		$("#body")
				.append(
						"<tr><td><input name='tids' type='checkbox' value="+this.id +"   />"
								+"</td><td>"
								+ this.title
								+"</td><td>"
								+"<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/pushhomepg/"+this.image+"' width='100px'/>" 
								+"</td><td>"
								+ this.statusName
								+"</td><td>"
								+ this.createDateStr
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ "<a class='edit_btn' onclick=check('"+this.id+"')>审核</a>"
								+ "<a class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"
								+ "<a class='edit_btn' href='goUpdatePushHomePage.do?pushHomePagePojo.id="
								+ this.id + "&type=${type}'>编辑</a>"
								/* +"<a class='del_btn' onclick=del('"
								+ this.id
								+ "','"
								+ this.type
								+ "')>删除</a>" */
								+"</td></s:if></tr>");
	}

	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "pushHomePageList.do?type=${type}");
		$("#query_btn").click(query);
	});

	function deleteAll(){
		$("#idform").attr("action","pushHomePageDeleteId.do?pushHomePagePojo.id=<s:property value='pushHomePagePojo.id'/>&type=${type}");
		$("#idform").submit();
	}
	
	function checkAll(){
		$("#idform").attr("action","pushHomePagecheckAll.do?pushHomePagePojo.id=<s:property value='pushHomePagePojo.id'/>&type=${type}");
		$("#idform").submit();
	}
</script>