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
<div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMakerShop.do">创客店铺管理</a></div>
    <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userMakerShop.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					    <td align="right">创客昵称：</td>
					    <td><input type="text" name="userMakerShopPojo.userName" value=""></td>	
					    <td align="right">创客店铺：</td>
					    <td><input type="text" name="userMakerShopPojo.shopName" value=""></td>
					</tr>
					<s:if test="panduan!=1">
					<tr>
					    <td align="right">审核状态：</td>
						<td><select name="userMakerShopPojo.status" id="userMakerShopPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核成功</option>
							<option value="2">审核失败</option>
				    		</select></td>
					</tr>
					</s:if>
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
		<th>ID</th>	
		<th>创客昵称</th>	
		<th>创客店铺</th>
		<th>开发能力</th>
		<th>适用年龄</th>
		<th>单平台内容产出量</th>
		<th>单平台原创内容产出量</th>
		<th>提交时间</th>
		<th>审核状态</th>
		<th>店铺类型</th>
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
		queryData("userMakerShopListCount.do", "userMakerShopListAll.do?panduan=${panduan}&randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.id + "</td>"+	
				"<td>"+ this.userName + "</td>"+	
				"<td>"+ this.shopName + "</td>"+
				"<td>"+ this.abilityName + "</td>"+
				"<td>"+ this.ageTypeName + "</td>"+
				"<td>"+ this.contentOutputName + "</td>"+
				"<td>"+ this.contentOutputOriginalName + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.isGenCabinetStr + "</td>"+
				"<s:if test="#session.role.roleId!=7 && panduan!=1"><td>"+
				"<a class='edit_btn' href='checkUserMakerShop.do?userMakerShopPojo.id="+this.id +"'>审核成功</a>"+
				"<a class='edit_btn' href='uncheckUserMakerShop.do?userMakerShopPojo.id="+this.id +"'>审核失败</a>"+
				"<a class='edit_btn' href='setGenCabinetType.do?userMakerShopPojo.id="+this.id+"&userMakerShopPojo.isGenCabinet=1' onclick='javascript:return p_set()'>设置创阁</a>"+
				"<a class='edit_btn' href='setGenCabinetType.do?userMakerShopPojo.id="+this.id+"&userMakerShopPojo.isGenCabinet=0' onclick='javascript:return p_delSet()'>取消创阁</a>"+
				"<a class='edit_btn' href='goDetailedUserMakerShop.do?userMakerShopPojo.id="+this.id +"'>编辑</a>"+
				"</td></s:if>"+
				"<s:if test="#session.role.roleId!=7 && panduan==1"><td>"+
				"<a class='edit_btn' href='selectUserMakerShop.do?userMakerShopPojo.shopId="+this.shopId +"'>选取</a>"+  
				"</td></s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userMakerShopListAll.do?panduan=${panduan}&randIni="+rand);	
		$("#query_btn").click(query);		
	});
	
	function p_set() { 
	  var msg = "是否设置为创阁"; 
	  if (confirm(msg)==true){ 
	   return true; 
	  }else{ 
	   return false; 
	  } 
	} 
	
	function p_delSet() { 
	  var msg = "是否取消创阁"; 
	  if (confirm(msg)==true){ 
	   return true; 
	  }else{ 
	   return false; 
	  } 
	} 	
</script>