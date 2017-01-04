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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/system/help/helpCommon.js"></script>
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

	/*删除全部*/
	function deleteAll() {
		document.getElementById("idform").submit();
	}
	/*删除全部*/
	function delAll() {
		if(tt.validate()){
			var formParam1 = $("#idform").serialize();
			var allformParam = formParam1;
			$(location).attr('href', 'productCommentDeleteIds.do?'+allformParam);
		}
	}
	$(function(){
		$("#delAll").click(function() {
				if(tt.validate()){
					var formParam1 = $("#idform").serialize();
					var allformParam = formParam1;
					//alert(formParam+"&"+formParam1);
					$(location).attr('href', 'productCommentDeleteId.do?'+allformParam);
				}
			});
	});
	
	function checkAll(){
		$("#idform").attr("action","productCommentCheckIds.do");
		$("#idform").submit();
	}

	/*删除*/
	function del(val) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delProductComment.do?productCommentPojo.id=" + val;
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
		if (result == "1") {
			alert("删除成功");
			queryData("getProductCommentCount.do",
					"productCommentAllList.do?randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
	
	function check(val,val2){
		if(confirm("确认要通过审核吗？")){
			var url = "checkProductComment.do?productCommentPojo.id="+val+"&productCommentPojo.productId="+val2;	
			doOpreator1(url,null);
		}else{
			return ;
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
			queryData("getProductCommentCount.do", "productCommentAllList.do?randquery=" + rand);
		} else {
			alert("审核失败");
		}
	}
	
	function uncheck(val,val2){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckProductComment.do?productCommentPojo.id="+val+"&productCommentPojo.productId="+val2;	
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
			queryData("getProductCommentCount.do", "productCommentAllList.do?randquery=" + rand);
		} else {
			alert("取消审核失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">商品管理</a> &gt; <a href="#">商品评价管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<div id="search_show" style="">
			<form action=productCommentManage.do method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户昵称:</td>
						<td align="left"><input type="text"
							name="productCommentPojo.userName" id="productCommentPojo.userName
							value="<s:property
								value="productCommentPojo.userName" />" /></td>
						<td align="right">商品名称</td>
						<td align="left">
						<input type="text" name="productCommentPojo.productName" id="productCommentPojo.productName
						value="<s:property value="productCommentPojo.productName" />" />
						<!--  
						<select name="productCommentPojo.productId" id="">
						 		<option value="">全部</option>
								<s:iterator value="productNameList">
									<option value="<s:property value="id"/>"
									<s:if test="id == productCommentPojo.productId">selected="selected"</s:if>>
									<s:property value="productName" />
									</option>
								</s:iterator>
						</select>
						-->
						</td>
						<td align="right">审核状态</td>
						<td align="left">
							<select name="productCommentPojo.status" id="">
						 		<option value=""></option>
								<s:iterator value="statusDic">
									<option value="<s:property value="value"/>"> <s:property value="name" /> </option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
					<td align="right">订单号:</td>
						<td align="left"><input type="text"
							name="productCommentPojo.orderNo" id="productCommentPojo.orderNo
							value="<s:property
								value="productCommentPojo.orderNo" />" /></td>
					<td align="right">评价时间的区域-开始日期：</td>
						   <td><input name="productCommentPojo.beginTimeStr" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true })"/></td>
					<td align="right">结束日期：</td>
						   <td><input name="productCommentPojo.endTimeStr" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true })"/></td>
					</tr>
					<tr align="right">
						<td align="right" colspan="6">
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
			<s:if test="#session.role.roleId!=7">
			<a class="Add_btn"  onclick="checkAll()" >批量审核</a>
			<a class="Add_btn"  onclick="delAll()" >批量删除</a>
			</s:if>
			<form action="productCommentDeleteId.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户昵称</th>
						<th width=30%>评价内容</th>
						<th>商品图片</th>
						<!--<th>评价状态</th>-->
						<th>商品名称</th>
						<th>订单号</th>
						<th>评价时间</th>
						<th>审核状态</th>
						<s:if test="#session.role.roleId!=7"><th >操作</th></s:if>
					</tr>
					<tbody id="body"></tbody>
					<!-- 这里显示具体的遍历信息 -->
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
			queryData("getProductCommentCount.do",
					"productCommentAllList.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		$("#body") 
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
								+ this.userName 
								+ "</td><td width=30%>"
								+ this.comment
								+ "</td><td>"
								+"<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.productImage + "' height='100px' /></td><td>"
								//+ this.scoreName
								//+ "</td><td>"
								+ this.productName  
								+ "</td><td>"
								+ this.orderNo  
								+ "</td><td>"
								+ this.createDateStr 
								+ "</td><td>"
								+ this.statusName 
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ "<a class='edit_btn' onclick=check('"+this.id+"','"+this.productId+"')>审核</a>"
								+ "<a class='edit_btn' onclick=uncheck('"+this.id+"','"+this.productId+"')>取消审核</a>"
								+ "<a class='del_btn' onclick=del('" + this.id + "')>删除</a>"
								+ "</td></s:if></tr>");
	}
	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"productCommentAllList.do?randIni=" + rand);
		$("#query_btn").click(query);

	});

	
</script>