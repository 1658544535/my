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
		document.getElementById("idform").submit();
	}


	function check(val)
	{
		//alert(val);
		if(confirm("确认要通过审核吗？(成功：普通用户将升级为分销商)"))
		{
			//alert(val);
			
			var url = "checkConsumer.do?consumer.id="+val;	
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
			alert("审核成功");
			queryData("getConsumerCount.do", "consumerAllList.do?randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">采购商账户管理</a> &gt; <a href="#">账户信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="consumer.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">公司名称：</td>
						<td><label><input type="text" name="consumer.company"
								value=""></label></td>
						<td align="right">地址：</td>
						<td><label><input type="text" name="consumer.address"
								value=""></label></td>
						<td align="right">销售平台：</td>
						<td><label><input type="text" name="consumer.platform"
								value=""></label></td>
					</tr>
					<tr>
						<td align="right">主营品类：</td>
						<td><select name="consumer.mainCategory" id="ticketType"  class="floatLeft">
							<option value="">----请选择----</option>
							<c:forEach items="${mainCategory}" var="mainCategory">
										<option value="${mainCategory.value}">${mainCategory.name}</option>
								</c:forEach>
				    		</select><div id="scale_mgId"></div></td>
						<td align="right">销售地区：</td>
						<td><label><input type="text" name="consumer.salesArea"
								value=""></label></td>
						<td align="right">手机号码：</td>
						<td><label><input type="text" name="consumer.phone"
								value=""></label></td>
					</tr>
					<tr>
					<td align="right">QQ：</td>
						<td><label><input type="text" name="consumer.QQ"
								value=""></label></td>
						<td align="right">帐号：</td>
						<td><label><input type="text" name="consumer.loginname"
								value=""></label></td>
					</tr>
					<!--<tr>
					<td align="right">用户ID：</td>
						<td><label><input type="text" name="consumer.userId"
								value=""></label></td>
					</tr>-->

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
			<s:if test="#session.role.roleId!=2"><a class="Add_btn" onclick="checkAll()">批量审核</a></s:if>
			<form action="consumerChecks.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
				<!--	<th>用户ID</th>	-->	
						<th>帐号</th>				
						<th>公司名称</th>
						<th>账户昵称</th>
						<th>地址</th>
						<th>企业规模</th>
						<th>主营品类</th>
						<th>销售平台</th>
						<th>销售地区</th>
						<th>联系人</th>
						<th>职务</th>
						<th>联系号码</th>
						<th>QQ</th>
						<th>创建时间</th>
						<th>状态</th>
						<th width="10%">操作</th>
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
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getConsumerCount.do", "consumerAllList.do?randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" />"+
		<!--	"</td><td>" + this.userId +  -->
				"</td><td>" + this.loginname +
		        "</td><td>" + this.company + 
		        "</td><td>" + this.userName+
		        "</td><td>" + this.address+ 
		        "</td><td>" + this.groupsName+ 
		        "</td><td>" + this.mainCategoryName+
		        "</td><td>" + this.platform+
		        "</td><td>" + this.salesArea+
		        "</td><td>" + this.contact+
		        "</td><td>" + this.duty+
		        "</td><td>" + this.phone+
		        "</td><td>" + this.QQ+
		        "</td><td>" + this.createDateStr+
		        "</td><td>" + this.statusName
				+ "</td><td>"+
				<s:if test="#session.role.roleId!=2">
				"<a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				</s:if>
				"<a class='edit_btn' href='goFindConsumer.do?consumer.id="+this.id +"'>编辑</a>"+
				<s:if test="#session.role.roleId!=2">
				 "<a class='del_btn' href='delConsumer.do?consumer.id="+this.id +"'onclick='javascript:return window.confirm(\"确定删除？\");'>删除</a>"+
				</s:if>
				"</td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"consumerAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>