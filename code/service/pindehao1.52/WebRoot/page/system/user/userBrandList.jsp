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
		$("#idform").attr("action","delUserBrand.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkUserBrandAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkUserBrand.do?userBrandPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckUserBrand.do?userBrandPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function del(val){
		if(confirm("确认要删除此条吗？")){
			var url = "delUserBrand.do?userBrandPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}		
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商家中心管理</a> &gt; <a href="userBrandList.do">用户品牌管理</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userBrandList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">品牌名称：</td>
						<td><label><input type="text" name="userBrandPojo.brandName"
								id="userBrandPojo.brandName"
								value=""></label></td>
					    <td align="right">审核状态：</td>
						<td><select name="userBrandPojo.status" id="userBrandPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
				    		</select><div id="status_mgId"></div></td>
	                </tr>
					<tr>
						<td align="right">开始日期：</td>
						<td><input name="userBrandPojo.startDateStr" id="startdate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true })"/></td>
						<td align="right">结束日期：</td>
						<td><input name="userBrandPojo.endDateStr" id="enddate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true })"/></td>
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
<s:if test="#session.role.roleId!=7">
<a class="Add_btn"    onclick="checkAll()">批量审核</a>
<a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn"    href="goAddUserBrandList.do">新增商品品牌</a></s:if>
	<form action="" method="post" id="idform">
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<!--  <th>用户名称 </th> -->
		<th>品牌名称 </th>
		<th>品牌LOGO </th>
		<th>品牌描述</th>
		<th>授权类型</th>
		<th>开始时间</th>
		<th>结束时间</th>
		<!--  <th>主营品类</th>	-->	
		<th>经营等级</th>
		<th>排序</th>
		<th>审核状态</th>
		<!--<th>证书1</th>
		<th>证书2</th>
		<th>证书3</th>-->
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
		queryData("userBrandListCount.do", "userBrandListAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td>"+
				//"<td>"+ this.userName + "</td>"+
				"<td>"+ this.brandName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/businessCenter/brandDic/"+this.logo+"'  height='100px'></td>"+
				"<td width='300px'>"+ this.brandDisc + "</td>"+
				"<td>"+ this.grantTypeName + "</td>"+
				"<td>"+ this.startDateStr + "</td>"+
				"<td>"+ this.endDateStr + "</td>"+			
				//"<td>"+ this.mainCategoryName + "</td>"+		
				"<td>"+ this.manageLevelName + "</td>"+									
				"<td>"+ this.sorting + "</td>"+					
				"<td>"+ this.statusName + "</td>"+	
				//"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userBrand/"+ this.image1 + "' height='100px' /></td>"+	
				//"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userBrand/"+ this.image2 + "' height='100px' /></td>"+	
				//"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userBrand/"+ this.image3 + "' height='100px' /></td>"+	
				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='goUpdateUserBrand.do?userBrandPojo.id="+this.id+"'>编辑</a>"+			
				"<a class='del_btn'  onclick=del("+this.id+")>删除</a><br/>"+
				"<a class='edit_btn' onclick=check("+this.id+")>通过审核</a>"+
				"<a class='edit_btn' onclick=uncheck("+this.id+")>取消审核</a></td></s:if>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userBrandListAll.do?randIni="+rand);		
		$("#query_btn").click(query);
		
		/**
		  点击图片显示原图
		 **/
		$(document).delegate(".Info_list_table img","click",function(){
			var imgSrc = $(this).attr("src");
			$("body").append("<div id='popup' onClick='$(\"#popup\").remove();' style='position:fixed;top:0;left:0;bottom:0;right:0;z-index:999;overflow:auto;background:#333;background:rgba(0,0,0,0.75);'>"+
							"<img src='"+ imgSrc +"' style='display:block;max-width:50%;margin:10% auto;' /></div>");
		});				
	});	
</script>