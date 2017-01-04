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

//push
function pushOrder(val)
{
	if(confirm("将此订单推送给该批发商么？"))
		{
			//alert(typeof(val));
			var url = "startPushForSingleAgency.do?agencyPojo.agencyId="+val+"&order.id="+${order.id};	
			doOpreator(url,null);
		}else{
			return ;
		}
}
function doOpreator(url,params){
	MAOWU.ajax.get(url, params, goRefreshPage);
}
 
function goRefreshPage(result){
	if(result=="1"){
		alert("推送成功");
	}else{
		alert("推送失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">批发商推送管理</a>
		</div>

		<div class="h15"></div>
		<div>
			<a class="Add_btn" onclick="checkAll()">批量推送</a>
			<form action="startPushForTag.do?order.id=${order.id}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>帐号</th>
						<th>昵称</th>
						<th>代理品牌</th>
						<!-- <th>QQ</th> -->
						<!-- <th>类型</th> -->
						<th>推送状态</th>
						<!--<th>注册时间</th> -->
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
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("agencyCountForPush.do", "agencyAllListForPush.do?randquery="+rand);
	}
}

function installPage() {
	$("#body").append(
			"<tr><td><input  name='tids' type='checkbox' value="+this.agencyId +">"+
	        "</td><td><a href='goFindAgency.do?agency.agencyId="+this.agencyId +"')>" + this.loginname + 
	        "</a></td><td>" + this.name+ 
	        "</td><td><a href='goFindAgencyCollect.do?agencyCollectPojo.agency_id="+this.agencyId +"')>" + this.shopName+ 
	        //"</td><td>" + this.QQ+ 
	        //"</td><td>" + this.typeName+
	        "</a></td><td>" + this.pushStatusName
			//+ "</td><td>" + this.agencydate
			+ "</td>"+
			"<td>"
			+ "<a class='edit_btn' onclick=pushOrder("+this.agencyId+")>推送</a>"
			+ "</td>"+
			"</tr>");
}

$(function() {
	 var rand=Math.random() * ( 100000 + 1);
	MAOWU.page.init(<s:property value="page.rowCount"/>,
			"agencyAllListForPush.do?order.id=${order.id}&randIni="+rand);
	$("#query_btn").click(query);	
});
</script>