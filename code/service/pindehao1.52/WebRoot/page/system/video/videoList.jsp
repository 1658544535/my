<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
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
function deleteAll(){
		$("#idform").attr("action","delVideoAll.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkVideoAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkVideo.do?timeId=${timeId}&videoPojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	}
	
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("findVideoCount.do?timeId=${timeId}", "findVideoList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要审核不通过吗？")){
			var url = "uncheckVideo.do?videoPojo.id="+val;	
			undoOpreator(url,null);
		}else{
			return ;
		}
	}

	
	function undoOpreator(url,params){
		MAOWU.ajax.get(url, params, ungoRefreshPage);
	}
	 
	function ungoRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核不通过成功");
			queryData("findVideoCount.do?os=1", "findVideoList.do?os=1&randdelete="+rand);
		}else{
			alert("审核不通过失败");
		}
	}
function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "delVideo.do?videoPojo.id="+val;	
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
			alert("删除成功");
			queryData("findVideoCount.do?os=1", "findVideoList.do?os=1&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	function p_setEdit() { 
	var msg = "确认要将视频设置为待编辑吗？"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">视频管理</a> &gt; <a href="#">待审核视频库</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">标题：</td>
						<td><label><input type="text" name="videoPojo.label" id="videoPojo.label" value=""></label></td>
						 <td align="right">审核状态：</td>
						<td><select name="videoPojo.status" id="videoPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="2">审核不通过</option>
				    		</select><div id="status_mgId"></div></td>
				    		</tr>
	                </tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>

				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
  <div class="h15"></div>
  <div>
<!--<a class="delAll_btn" onclick="deleteAll()">批量删除</a>-->
<!--<a class="Add_btn" onclick="checkAll()">批量审核</a>-->
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    <!--<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>-->
		<th>标题</th>
		<th>链接</th>
		<th>审核状态</th>
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
  
  
  
  <!---->


</div>
</body>
</html>


	


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("findVideoCount.do?os=1", "findVideoList.do?os=1&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				//"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<tr><td>"+ this.label + "</td>"+
			    "<td><a target='_blank' href="+this.url+">"+ this.url + "</a></td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td><a  class='edit_btn' href='updateVideoList.do?type=1&panduan=1&videoPojo.id="+this.id +"'>审核</a>"+
				"<a  class='edit_btn' onclick=uncheck("+this.id+")>审核不通过</a>"+
				"<a  class='edit_btn' href='updateVideoList.do?type=2&panduan=1&videoPojo.id="+this.id +"'>待编辑</a>"+
				<!--"<a  class='edit_btn' href='setVideoWaitEdit.do?videoPojo.status=3&videoPojo.id="+this.id +"' onclick='javascript:return p_setEdit()'>待编辑</a>"+-->
				"<a  class='del_btn'  onclick=del("+this.id+")>删除</a></td></tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"findVideoList.do?os=1&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>