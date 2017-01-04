<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">玩具工厂管理</a> &gt; <a href="userToyLogList.do">玩具记录</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 </div>
		<!-- 查询开始 -->
		<form action="userToyLogList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr> 
					    <td align="right">用户名称：</td>
						<td><label><input type="text" name="userToyLogPojo.loginName" value=""></label></td>
						<td align="right">联系方式：</td>
						<td><label><input type="text" name="userToyLogPojo.telphone" value=""></label></td>
						<td align="right">玩具名称：</td>
						<td><label><input type="text" name="userToyLogPojo.toyName" value=""></label></td>
					</tr>
					<tr> 
						<td align="right">开始日期：</td><td><input name="userToyLogPojo.beginday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })"/>	</td>				
						<td align="right">结束日期：</td><td><input name="userToyLogPojo.endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })"/></td>
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
<table width="100%" border="0" class="Info_list_table">
<tr>    
		<th>用户名称</th>
		<th>收件人名称</th>
		<th>玩具名称</th>
		<th>送货地址</th>
		<th>用户联系方式</th>
		<th>快递单号</th>
		<th>快递名称</th>
		<th>玩具合成时间</th>
		<th>操作</th>
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
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("UserToyLogListCount.do", "UserToyLogfindAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.loginName + "</td>"+
				"<td>"+ this.username + "</td>"+
				"<td>"+ this.toyName + "</td>"+
				"<td>"+ this.address + "</td>"+
				"<td>"+ this.telphone + "</td>"+
				"<td>"+ this.expressNo + "</td>"+
				"<td>"+ this.expressName + "</td>"+
				"<td>"+ this.createDateStr + "</td><td>"+
				"<a class='edit_btn' href='ToySendOut.do?userToyLogPojo.id="+this.id +"'>发货</a></td></tr>"
				);
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"UserToyLogfindAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	
</script>