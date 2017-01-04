<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/system/help/helpCommon.js"></script>
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
		document.getElementById("idform").submit();
	}


	function del(val,roleid)
	{
		if(confirm("你真的想删除该记录么？"))
		{
		
			
			var url = "delUserRole.do?userId="+val+"&roleId="+roleid;	
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
			queryData("getUserRuleCount.do", "userRuleAllList.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="#">系统管理</a> &gt; <a href="#">用户角色管理</a></div>
    <div class="Search_control">
    	<p>按条件查找</p><a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
    </div>
    <!-- 查询开始 -->
    
    <form action="gomanageuserrole.do" method="post" id="sysform">
	
    <div id="search_show" style="" >
        <table width="100%" border="0" class="Search_table">
          <tr>
          
            <td align="right" >角色类型：</td>
            <td><select name="userRolePojo.roleId" id="ticketType">
					<option value="">全部</option>
				   	<s:iterator value="#request.roleList" id="us">
   	  
                
					<option value="<s:property value="#us.roleId"/>"<c:if test="${us.roleId==userRolePojo.roleId}">selected="selected"</c:if>> <s:property value="#us.roleName"/></option>
			              	
            	

   		
   	</s:iterator>
				  </select></td>     
            </tr>
            
        </table>
        <input type="hidden" name="page.pageNo" value=0 	id="pageNo">
    	<div class="floatRight"> <input id="query_btn"
				type="button"  class="submit_btn" value="查询" /></div>
				
        <div class="Clear"></div>
    </div>
    </form>
     <!-- 查询结束 -->
    
  <div class="h15"></div>
   <div>
  <a class="delAll_btn"  onclick="deleteAll()" >删除全部</a><a class="Add_btn" href="addtouserrole.do" >新增用户角色</a>
  <form action="userRuleDeleteId.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<th>角色名称</th>
         <th>用户名</th>
		
	

        <th>操作</th>
    </tr>
    <tbody id="body"></tbody>
      
    </table>
    </form>
   <div class="page">
        <div class="floatleft">总共 <i id="rowcount">${page.rowCount }</i> 条/<i id="pagecount"></i>页 </div>
        <div style="float:right" id="Pagination" class="pagination"></div>
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
		queryData("getUserRuleCount.do", "userRuleAllList.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.roleId +","+this.userId +"   /></td><td>" 
		        + this.roleName + "</td><td>" + this.userName
				+ "</td><td><a class='del_btn' onclick=del('"+this.userId+"','"+this.roleId +"')>删除</a>"
				+ "<a class='edit_btn' href='goadduserrole.do?roleId="+this.roleId +"&userId="+this.userId +"'>编辑</a></td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userRuleAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>