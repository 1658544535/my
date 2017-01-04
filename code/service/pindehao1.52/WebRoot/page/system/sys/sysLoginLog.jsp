<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/pagination.css"
	media="screen" rel="stylesheet">
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/tipswindown.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/browser.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/tipswindown.css" media="screen"
	rel="stylesheet">
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">信息统计</a> &gt; <a href="#">登录信息记录</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="sysLoginLog.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">用户昵称：</td>
						<td><label><input type="text" name="sysLoginLog.loginIp"
								value=""></label></td>
						<td align="right" >开始日期: </td>
						 <td><label><input id="beganday" name="beganday" type="text"
							readonly="readonly"> <img id="begancalendarImg"
							src="<s:property value="ctx" />/js/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" align="middle" /></label></td>
							<td><span id="beganday_inf"></span></td>
							
							<td align="right" >结束日期: </td>
							<td><label><input id="endday"
							name="endday" type="text" readonly="readonly"> <img
							id="endcalendarImg"
							src="<s:property value="ctx" />/js/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" align="middle" /></label></td>
							<td><span id="endday_inf"></span></td> 
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
						<th>用户昵称</th>
						<th>最后登录ip</th>
						<th>最后登录时间</th>
					</tr>
					<tbody id="body"></tbody>

				</table>
				
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
		queryData("getSysLoginLogCount.do", "sysLoginLogAllList.do?randquery="+rand);
	}
}

function setBeganDay() {
	WdatePicker({
		el : 'beganday'
	});
}
function setEndDay() {
	WdatePicker({
		el : 'endday'
	});
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td>" 
				+ this.userName + 
		        "</td><td>" + this.loginIp+
		        "</td><td>" + this.loginDateName
		        + "</td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"sysLoginLogAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		$("#begancalendarImg").click(setBeganDay);
		$("#beganday").click(setBeganDay);
		$("#endcalendarImg").click(setEndDay);
		$("#endday").click(setEndDay);
		
	});
	
</script>