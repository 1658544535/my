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
<div class="s_nav"><a href="#">达人管理</a> &gt; <a href="userTalentAuth.do">达人管理</a></div>
    <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="labelList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					    <td align="right">达人昵称：</td>
					    <td><input type="text" name="userTalentAuthPojo.userName" value=""></td>	
					    <td align="right">达人账号：</td>
					    <td><input type="text" name="userTalentAuthPojo.loginname" value=""></td>	
						<td align="right">审核状态：</td>
						<td><select name="userTalentAuthPojo.status" id="userTalentAuthPojo.status"  class="floatLeft">
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
<form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>达人昵称</th>	
		<th>达人账号</th>
		<th>职业身份</th>	
		<th>单平台真实粉丝积累</th>	
		<th>平台用户名</th>	
		<th>跨平台能力</th>	
		<th>单平台内容产出量/日</th>	
		<th>单平台原创内容产出量/日</th>	
		<th>现有合作同类平台</th>	
		<th>现有合作商品品牌</th>	
		<th>现商业化合作模式</th>	
		<th>申请时间</th>	
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
		queryData("userTalentAuthListCount.do", "userTalentAuthListAll.do?userTalentAuthPojo.all=1&randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
	    var op ="";
		if(this.status == 2){
			op = "<a class='del_btn' href='goCheckUserTalentAuth.do?status=1&userTalentAuthPojo.id="+this.id +"'>审核成功</a></td>";
		}else if(this.status == 1){
			op = "<a class='del_btn' href='goCheckUserTalentAuth.do?status=2&userTalentAuthPojo.id="+this.id +"'>审核失败</a></td>";
		}else if(this.status == 0){
			op = "<a class='del_btn' href='goCheckUserTalentAuth.do?status=1&userTalentAuthPojo.id="+this.id +"'>审核成功</a>"+
				 "<a class='del_btn' href='goCheckUserTalentAuth.do?status=2&userTalentAuthPojo.id="+this.id +"'>审核失败</a></td>";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.userName + "</td>"+	
				"<td>"+ this.loginname + "</td>"+
				"<td>"+ this.identity + "</td>"+
				"<td>"+ this.platformFansNum + "</td>"+
				"<td>"+ this.platformUserName +"</td>"+
				"<td>"+ this.crossPlatform + "</td>"+
				"<td>"+ this.contentOutPerName + "</td>"+
				"<td>"+ this.origContentOutPerName + "</td>"+
				"<td>"+ this.coopSimilarPlat + "</td>"+
				"<td>"+ this.coopBrand + "</td>"+
				"<td>"+ this.commercialType + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				op+
				"</s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userTalentAuthListAll.do?userTalentAuthPojo.all=1&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>