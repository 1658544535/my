<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript"
src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	function allcb(){
		var checkbox=document.getElementById("selectcb"); 
		if(checkbox.checked==true){
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox") { 
					code_Values[i].checked = true; 
				} 
			} 
		}else{	
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox"){ 
					code_Values[i].checked = false
				} 
			} 
		}
	}

	function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "delActivityTitle.do?type=${type}&activityTitlePojo.id="+val;	
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
			queryData("findActivityTitleCount.do?type=${type}", "findActivityTitleList.do?type=${type}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkActivityTitle.do?type=${type}&activityTitlePojo.id="+val;	
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
			queryData("findActivityTitleCount.do?type=${type}", "findActivityTitleList.do?type=${type}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckActivityTitle.do?type=${type}&activityTitlePojo.id="+val;	
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
			alert("取消审核成功");
			queryData("findActivityTitleCount.do?type=${type}", "findActivityTitleList.do?type=${type}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
	function deleteAll(){
		$("#idform").attr("action","delActivityTitleAll.do?type=${type}");
		$("#idform").submit();
	}
	
	function checkAll(){
		$("#idform").attr("action","checkActivityTitleAll.do?type=${type}");
		$("#idform").submit();
	}
	
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="#">活动商品管理</a> &gt;<c:if test="${type==5}"><a href="#">web活动页管理</a></c:if><c:if test="${type==4}"><a href="#">首页列表活动管理</a></c:if></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="findActivityTitleList.do?type=${type}" method="post" id="sysform">
		<input type="hidden" name="activityProductPojo.type" value="${type}" id="activityProductPojo.type">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr>
				<td align="right">标题：</td>
				<td><input type="text" name="activityTitlePojo.title"
				id="activityTitlePojo.title"
				value="<s:property value="activityTitlePojo.title"/>"></td>
    	        <td align="right">审核状态：</td>
				<td><select name="activityTitlePojo.status" id="activityTitlePojo.status"  class="floatLeft">
					<option value="">全部</option>
					<s:iterator value="statusSysDictList">
						<option value="<s:property value="value"/>"><s:property value="name" />
						</option>
					</s:iterator>
		    		</select>
		        </td>				  
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
   <s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()" >批量删除</a>
   <a class="Add_btn" onclick="checkAll()" >批量审核</a>&nbsp;&nbsp;&nbsp;<a class="Add_btn" href="activityTitleAdd.do?type=${type}" >新增活动标题</a></s:if>
   <form action="getActivityProductList.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th width='20px'><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>活动标题</th>
		<th>banner</th>
		<c:if test="${type==5}">
		<th>标题图片	1</th>
		<th>标题图片2</th>
		<th>标题图片3</th>
		</c:if>
		<th>审核状态</th>
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
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	function query() {
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("findActivityTitleCount.do?type=${type}", "findActivityTitleList.do?type=${type}&randquery="+rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td width='20px'><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<c:if test="${type==4}"><td><a href='activityProductManage.do?type=${type}&titleId="+this.id+"&title="+this.title+"'>"+ this.title + "</a></td></c:if>"+
				"<c:if test="${type==5}"><td>"+ this.title + "</td></c:if>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/"+ this.banner + "' height='50px' /></td>"+
				"<c:if test="${type==5}"><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/"+this.titlePic+"' height='50px' /></td><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/"+this.titlePicture+"' height='50px' /></td><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/"+this.titlePhoto+"' height='50px' /></td></c:if>"+
				"<td>"+ this.statusName + "</td>"+
				"</td><s:if test="#session.role.roleId!=7"><td>"+
				"<c:if test="${type==5}"><a class='edit_btn' href='activityPlaceList.do?type=${type}&titleId="+this.id+"'>添加活动商品</a></c:if>" +
				"<c:if test="${type==4}"><a class='edit_btn' href='addAgencyCollect.do?type=${type}&titleId="+this.id+"&title="+this.title+"'>添加活动商品</a></c:if>" +
				"<c:if test="${type==5}"><a class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a></c:if>" +
				"<c:if test="${type==4}"><a class='edit_btn' href='findActivityTitleById.do?type=${type}&activityTitlePojo.id="+this.id +"'>编辑</a></c:if>"+
				"<c:if test="${type==5}"><a class='edit_btn' href='findActivityTitleById.do?type=${type}&activityTitlePojo.id="+this.id +"'>编辑</a></c:if>"+
				"<a class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"<c:if test="${type==5}"><a class='edit_btn' target='_blank' href='/goWebView.do?id="+this.id +"'>web活动页预览</a></c:if></td></s:if></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "findActivityTitleList.do?type=${type}");
		
		$("#query_btn").click(query);
	});
	
</script>