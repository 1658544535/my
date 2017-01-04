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
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商家中心管理</a> &gt; <a href="adSpread.do">有米广告管理</a></div>
    <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="adSpread.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
				    	<td align="right">imei：</td>	
				    	<td><input type="text" name="adSpreadPojo.imei" id="adSpreadPojo.imei" value=""></td>	    			
				    	<td align="right">渠道：</td>
						<td><select name="adSpreadPojo.channel" id="adSpreadPojo.channel"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">ios</option>
							<option value="2">android</option>
				    		</select></td>
				    	<td align="right">激活状态：</td>
						<td><select name="adSpreadPojo.status" id="adSpreadPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未激活</option>
							<option value="1">已激活</option>
				    		</select></td>	
					</tr>
					<tr>
						<td align="right">开始日期：</td>
						<td><input name="adSpreadPojo.createStartDateStr" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
						<td align="right">结束日期：</td>
						<td><input name="adSpreadPojo.createEndDateStr" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
				<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- <a href="javascript:self.print();">打印该页</a> -->
		<!-- 查询结束 -->
<div class="h15"></div>
<div>
<!-- <a class="Add_btn" href="addPopup.do" >新增</a> -->
<form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>渠道</th>
		<th>imei</th>
		<th>回调URL</th>
		<th>激活状态</th>
		<th>回调结果</th>
		<th>用户ID</th>
		<th>创建时间</th>
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
		queryData("findAdSpreadCount.do", "findAdSpreadList.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.channelName + "</td>"+
				"<td>"+ this.imei + "</td>"+
				"<td>"+ this.callbackUrl + "</td>"+			
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.result + "</td>"+
				"<td>"+ this.userId + "</td>"+
				"<td>"+ this.createDateStr + "</td>"
				);
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"findAdSpreadList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
<script type="text/javascript">
</script>