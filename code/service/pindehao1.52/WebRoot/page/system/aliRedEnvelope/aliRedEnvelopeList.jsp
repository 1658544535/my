<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">支付宝红包口令表管理</a> &gt; <a href="#">支付宝红包口令表管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">邀请码：</td>
						<td><label><input type="text" name="aliRedEnvelopePojo.inviteCode"></label>
						</td>
						<td align="right">是否使用：</td>
						<td><select name="aliRedEnvelopePojo.isUsed" class="floatLeft">
									<option value="">----请选择----</option>
									<option value="0">未使用</option>
									<option value="1">已使用</option>
							</select>
						</td>
						<td align="right">团id：</td>
						<td><label><input type="text" name="aliRedEnvelopePojo.attendId"></label>
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
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<%--<a class="Add_btn" href="goAddAliRedEnvelope.do" >新增</a>
			
			<a class="Add_btn" onclick="checkAll('checkAliRedEnvelopeAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckAliRedEnvelopeAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delAliRedEnvelopeAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>邀请码</th>
						<th>红包口令图片1</th>
						<th>红包口令图片2</th>
						<th>使用状态</th>
						<th>团id</th>
						<!--<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>-->
					</tr>
					<tbody id="body"></tbody>    
				</table>
			</form>
			<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
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
	/**
	 ** 条件查询函数 
	 **/
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("aliRedEnvelopeCnt.do", "aliRedEnvelopeList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
	if(this.isUsed == 0){
	 var isUsed = "未使用";
	}else{
	 var isUsed = "已使用";
	}
		$("#body").append(
				"<tr><td>"+ this.inviteCode + "</td>"+
				"<td><a href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/aliRedEnvelope/"+this.passwdImg1+"' target='_blank'><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/aliRedEnvelope/"+this.passwdImg1+"'  height='100px'></a></td>"+
				"<td><a href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/aliRedEnvelope/"+this.passwdImg2+"' target='_blank'><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/aliRedEnvelope/"+this.passwdImg2+"'  height='100px'></a></td>"+
				"<td>"+ isUsed + "</td>"+
				"<td><a href='goDrawGroupGroUserRec.do?drawGrouponRecordPojo.attendId="+this.attendId +"'>"+ this.attendId + "</a></td>"
			//	<s:if test="#session.role.roleId!=7">
			//	"<td><a class='edit_btn' href='goEditAliRedEnvelope.do?id="+this.id +"'>编辑</a>"+
			//	"<a class='del_btn' onclick='del(\"delAliRedEnvelope.do?id="+this.id+"\")'>删除</a></td>"
			//	</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "aliRedEnvelopeList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
