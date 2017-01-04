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
	if(confirm("确认要通过审核吗？"))
	{
		var url = "checkAgencyApply.do?agencyApplyPojo.id="+val;
		//doOpreator(url,null);
		window.location.href=url;
	}else{
		return ;
	}
	
}
function uncheck(val)
{
	if(confirm("确认要取消审核吗？"))
	{
		var url = "uncheckAgencyApply.do?agencyApplyPojo.id="+val;
		//doOpreator2(url,null);
		window.location.href=url;
	}else{
		return ;
	}
	
}
/* function doOpreator(url,params){
	MAOWU.ajax.get(url, params);
}
function goRefreshPage(result){

	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("审核成功");
		queryData("agencyApplyCount.do", "agencyApplyAllList.do?randdelete="+rand);
	}else{
		alert("审核失败");
	}
}
function doOpreator2(url,params){
	MAOWU.ajax.get(url, params);
}
function goRefreshPage2(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("取消成功");
		queryData("agencyApplyCount.do", "agencyApplyAllList.do?randdelete="+rand);
	}else{
		alert("取消失败");
	}
} */
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">批发商账户管理</a> &gt; <a href="#">批发商申请管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="agencyApplication.do" method="post" id="sysform">
		<div id="search_show" style="">
		<table width="100%" border="0" class="Search_table">
		<tr>
			<td align="right">联系人：</td>
			<td><label><input type="text" name="agencyApplyPojo.contact"
					value="${agencyApplyPojo.contact }"></label></td>
			<td align="right">主营商品：</td>
			<td><label><input type="text" name="agencyApplyPojo.mainProduct"
					value="${agencyApplyPojo.mainProduct }"></label></td>
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
		<a class="delAll_btn"  onclick="deleteAll()" >删除全部</a><a class="Add_btn"  onclick="checkAll()" >审核全部</a>
		<a class="Add_btn" href="AgencyApplyAdd.do?type=0">新增信息</a>
			<form action="AgencyApplyCheckAll.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
							<th>联系人</th>
						<!-- <th>联系电话</th> -->
						<th>手机号码</th>
						<!-- <th>传真号码</th> -->
						<th>QQ</th>
						<th>省</th>
						<th>市</th>
						<th>地区</th>
						<!-- <th>代理地区</th> -->
						<th>主营商品</th>
						
						<th>状态</th>
						<!-- <th>创建者</th> -->
						<th>创建时间</th>
						<!-- <th>更新者</th> -->
						<th>更新时间</th>
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
var ctx  ='<s:property value="ctx" />';
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		//var rand=Math.random() * ( 100000 + 1);
		//queryData("agencyApplyCount.do", "agencyApplyAllList.do?randquery="+rand);
		var contact=$("input[name='agencyApplyPojo.contact']").val();
		var mainProduct=$("input[name='agencyApplyPojo.mainProduct']").val();
		var url = "agencyApplication.do?agencyApplyPojo.contact="+contact+"&agencyApplyPojo.mainProduct="+mainProduct;
		window.location.href=url;
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" />"+
				"</td><td>" + this.contact + 
		        //"</td><td>" + this.tel+ 
		        "</td><td>" + this.phone+ 
		        //"</td><td>" + this.fax+
		        "</td><td>" + this.QQ
				+ "</td><td>" + this.province
				+ "</td><td>" + this.city
				+ "</td><td>" + this.area
				//+ "</td><td>" + this.address
				+ "</td><td>" + this.mainProduct
				
				+ "</td><td>" + this.statusName
				//+ "</td><td>" + this.createBy
				+ "</td><td>" + this.createDateStr
				//+ "</td><td>" + this.updateBy
				+ "</td><td>" + this.updateDateStr
						+ "</td><td>"+
						"<a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
						"<a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
						"<a class='edit_btn' href='updateAgencyApply.do?type=1&agencyApplyPojo.id="+this.id +"'>编辑</a>"+
						 "<a class='del_btn' href='delAgencyApply.do?agencyApplyPojo.id="+this.id +"'onclick='javascript:return window.confirm(\"确定删除？\");'>删除</a>"+
						"</td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"agencyApplyAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});

	function deleteAll(){
		$("#idform").attr("action","AgencyApplyDeleteAll.do").submit();
	}
</script>