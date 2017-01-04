<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "delSocialCircleType.do?socialCircleTypePojo.id="+val;	
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
			queryData("socialCircleTypeListCount.do", "socialCircleTypeListAll.do?randquery="+rand);
		}else{
			alert("删除失败");
		}
	}
</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">社圈管理</a> &gt; <a href="socialCircleType.do">社圈分类</a></div>
		<!-- 查询开始 -->
		<form action="socialCircleType.do" method="post" id="sysform">
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		</form>
		<!-- 查询结束 -->
<div class="h15"></div>
<div>
<a class="Add_btn" href="goAddSocialCircleType.do">新增分类</a>
<form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>分类名</th>	
		<th>图标</th>	
		<th>排序</th>	
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
		queryData("socialCircleTypeListCount.do", "socialCircleTypeListAll.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.name + "</td>"+	
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/socialCircleType/"+ this.images + "' height='100px' /></td>"+
				"<td>"+ this.sorting + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a class='edit_btn' href='goUpdateSocialCircleType.do?socialCircleTypePojo.id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick=del('"+this.id +"')>删除</a></td></s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"socialCircleTypeListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>