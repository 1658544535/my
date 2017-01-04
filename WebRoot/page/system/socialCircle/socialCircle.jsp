<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		select();
	});
	function select() {
		$("#circleTypeId").append("<option value=''>----请选择----</option>");
		$.ajax(
		{
			type: "post",
			url: "socialCircleTypeListAll.do?op=1",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					$("#circleTypeId").append("<option value=" + o_msg[i].id + ">" + o_msg[i].name + "</option>");
				}
			}
		})
	};
</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">社圈管理</a> &gt; <a href="socialCircle.do">社圈管理</a></div>
<div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="socialCircle.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">社圈名称：</td>
						<td><input type="text" name="socialCirclePojo.title" value=""></td>
						<td align="right">社圈分类：</td>
						<td><select id="circleTypeId" name="socialCirclePojo.circleTypeId" class="floatLeft" ></select></td>
						<td align="right">审核状态：</td>
						<td><select name="socialCirclePojo.status" id="socialCirclePojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核成功</option>
							<option value="2">审核失败</option>
				    		</select></td>
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
<a class="Add_btn" href="goAddSocialCircle.do">新增社圈</a>
<form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th>社圈ID</th>
		<th>社圈名称</th>
		<th>LOGO</th>
		<th>社圈分类</th>
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
		queryData("socialCircleListCount.do", "socialCircleListAll.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ this.title + "</td>"+	
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/socialCircle/"+this.banner+"' height='100px'></td>"+
				"<td>"+ this.typeName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a class='edit_btn' href='checkSocialCircle.do?socialCirclePojo.id="+this.id +"'>审核成功</a>"+
				"<a class='edit_btn' href='uncheckSocialCircle.do?socialCirclePojo.id="+this.id +"'>审核失败</a>"+
				<!-- "<a class='del_btn' href='delSocialCircle.do?socialCirclePojo.id="+this.id +"'>删除</a>+ -->
				"<a class='edit_btn' href='goUpdateSocialCircle.do?socialCirclePojo.id="+this.id +"'>编辑</a></td></s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"socialCircleListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>