<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMakerBrand.do">创客品牌管理</a></div>
    <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userMakerBrand.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					    <td align="right">创客昵称：</td>
					    <td><input type="text" name="userMakerBrandPojo.userName" value=""></td>	
					    <td align="right">创客品牌：</td>
					    <td><input type="text" name="userMakerBrandPojo.brandName" value=""></td>
					</tr>
					<tr>
					    <td align="right">审核状态：</td>
						<td><select name="userMakerBrandPojo.status" id="userMakerBrandPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核成功</option>
							<option value="2">审核失败</option>
				    		</select></td>
				    	<td align="right">品牌类型：</td>
						<td><select name="userMakerBrandPojo.brandType" id="userMakerBrandPojo.brandType"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">首页品牌</option>
							<option value="1">精选品牌</option>
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
<form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>创客昵称</th>	
		<th>创客品牌</th>
		<th>品牌LOGO</th>
		<th>商标注册证明</th>
		<th>提交时间</th>
		<th>品牌类型</th>
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
		queryData("userMakerBrandListCount.do", "userMakerBrandListAll.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.userName + "</td>"+	
				"<td>"+ this.brandName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerBrand/"+ this.logo + "' height='100px' /></td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerBrand/"+ this.registrationCertificate + "' height='100px' /></td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.brandTypeName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a class='edit_btn' href='goCheckUserMakerBrand.do?userMakerBrandPojo.id="+this.id +"'>设置审核</a>"+
				"<a class='edit_btn'  href='goUpdateUserMakerContent.do?userMakerBrandPojo.id="+this.id +"'>编辑内容</a>"+
				"<a class='edit_btn' href='changeTypeUserMakerBrand1.do?userMakerBrandPojo.id="+this.id +"'>设置首页</a>"+
				"<a class='edit_btn' href='changeTypeUserMakerBrand2.do?userMakerBrandPojo.id="+this.id +"'>设置精选</a>"+
				<!-- "<a class='edit_btn'>预览</a>"+  -->
				"</td></s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"userMakerBrandListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>