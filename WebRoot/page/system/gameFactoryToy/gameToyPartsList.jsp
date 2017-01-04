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
		$("#idform").attr("action","delGameToyPartsAll.do?toyId=${gameFactoryToyPojo.id}").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkGameToyPartsAll.do?toyId=${gameFactoryToyPojo.id}").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkGameToyParts.do?timeId=${timeId}&gameToyPartsPojo.id="+val;	
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
			queryData("findGameToyPartsCount.do?gameFactoryToyPojo.id=${gameFactoryToyPojo.id}", "findGameToyPartsList.do?gameToyPartsPojo.toyId=${gameFactoryToyPojo.id}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckGameToyParts.do?timeId=${timeId}&gameToyPartsPojo.id="+val;	
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
			queryData("findGameToyPartsCount.do?gameFactoryToyPojo.id=${gameFactoryToyPojo.id}", "findGameToyPartsList.do?gameToyPartsPojo.toyId=${gameFactoryToyPojo.id}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "delGameToyParts.do?gameToyPartsPojo.id="+val;	
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
			queryData("findGameToyPartsCount.do?gameFactoryToyPojo.id=${gameFactoryToyPojo.id}", "findGameToyPartsList.do?gameToyPartsPojo.toyId=${gameFactoryToyPojo.id}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
            <form action="" method="post" id="sysform">
               <input type="hidden" name="page.pageNo" value=0 id="pageNo">
			</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
  <div>
<div class="s_nav"><a href="#">玩具工厂管理</a> &gt; <a href="#">玩具设置</a>&gt; <a href="#">玩具配件设置</a></div>
<a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" onclick="checkAll()">批量审核</a>
<a class="Add_btn" href="addGameToyParts.do?toyId=${gameFactoryToyPojo.id}">新增玩具配件</a>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>配件名称</th>
		<th>配件图片</th>
		<th>生成所需时间（分钟）</th>
		<th>概率（0-100）</th>
		<th>状态</th>
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
		queryData("findGameToyPartsCount.do?gameToyPartsPojo.toyId=${gameFactoryToyPojo.id}", "findGameToyPartsList.do?gameToyPartsPojo.toyId=${gameFactoryToyPojo.id}&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.name + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/gameToyParts/"+ this.images + "' height='100px' /></td>"+
				"<td>"+ this.createTime + "</td>"+
				"<td>"+ this.probability + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td><a  class='edit_btn' onclick=check("+this.id+")>审核通过</a>"+
				"<a  class='edit_btn' onclick=uncheck("+this.id+")>取消审核</a>"+
				"<a  class='edit_btn' href='updateGameToyPartsList.do?toyId=${gameFactoryToyPojo.id}&gameToyPartsPojo.id="+this.id +"'>编辑</a>"+
				"<a  class='del_btn'  onclick=del("+this.id+")>删除</a></td>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"findGameToyPartsList.do?gameToyPartsPojo.toyId=${gameFactoryToyPojo.id}&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	  
	  
</script>