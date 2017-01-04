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
<script type="text/javascript">
function del(val) { 
	var msg = "您真的确定要删除吗？"; 
	if (confirm(msg)==true){ 
		var url = "delMicroPage.do?microPagePojo.id="+val;	
		window.location.href=url;
	} else { 
		return false; 
	} 
} 
</script>	
</head>
<body>
<div class="sub_wrap">
	<div class="s_nav">
		<a>活动管理</a> &gt; <a href="">微页面</a>
	</div>
	<div class="h15"></div>
	<div>
		<a class="Add_btn" href="goAddMicroPage.do">新增微页面</a>
		<table width="100%" border="0" class="Info_list_table">
		<tr>    
			<th>标题</th>
			<th>页面ID</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<tbody id="body"></tbody>      
		</table>
		<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
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
		queryData("microPageListCount.do", "microPageListAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.id + "</td>"+				
				"<td>"+ this.createDateStr + "</td>"+
				"<td><a class='edit_btn' href='goUpdateMicroPage.do?microPagePojo.id="+this.id +"'>编辑</a>"+
				"<a class='edit_btn' onclick='linkMicro("+this.id +")'>链接</a>"+
				"<a class='del_btn'  onclick=del("+this.id+")>删除</a></td>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"microPageListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	function linkMicro(id){
	 	var url="getMicroPageVisApi.do?id="+id+"&type=4"; //转向网页的地址; 
        var name='预览'; //网页名称，可为空; 
        var iWidth=420; //弹出窗口的宽度; 
        var iHeight=700; //弹出窗口的高度; 
        //获得窗口的垂直位置 
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
        //获得窗口的水平位置 
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
        window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
</script>