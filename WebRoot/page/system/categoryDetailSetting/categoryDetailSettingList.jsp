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
		$("#idform").attr("action","delCategoryDetailSettingAllById.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkCategoryDetailSettingAllById.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkCategoryDetailSettingById.do?categoryDetailSettingPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckCategoryDetailSettingById.do?categoryDetailSettingPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function del(val){
		if(confirm("确认要删除此条吗？")){
			var url = "delCategoryDetailSettingById.do?categoryDetailSettingPojo.categoryId=${categoryDetailSettingPojo.categoryId}&categoryDetailSettingPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}		
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商城管理</a> &gt;<a href="#">更多分类</a></div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">

				<input type="hidden" name="page.pageNo" value=0 id="pageNo">

		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
<a class="Add_btn" href="goProductTypeThree.do?panduan=1&categoryId=${categoryDetailSettingPojo.categoryId}">新增分类</a>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>品类名</th>
		<th>品类图</th>
		<th>级别</th>
		<th>所属父类</th>
		<th>排序</th>
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
		queryData("findCategoryDetailSettingCount.do", "findCategoryDetailSettingList.do?${categoryDetailSettingPojo.categoryId}&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td>"+ this.name + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/productType/"+ this.image + "' height='100px' /></td>"+
				"<td>三级</td>"+
				"<td>"+ this.parentName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td><a  class='edit_btn' href='goUpdateCategoryDetailSettingById.do?id="+this.id +"'>编辑</a>"+
				"<a  class='del_btn'  onclick=del("+this.id+")>删除</a></td>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"findCategoryDetailSettingList.do?categoryDetailSettingPojo.categoryId=${categoryDetailSettingPojo.categoryId}&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>