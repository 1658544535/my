<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../common/common_head.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>jfreechart-demo</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="UTF-8">
<link
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/jquery-ui-1.10.3/themes/smoothness/jquery-ui.css"
	media="screen" rel="stylesheet">
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/tipswindown.css" media="screen"
	rel="stylesheet">
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/tipswindown.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/tipswindown/browser.js"></script>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util.js"></script>
<body>
	<form action="#" id="show">
		<input type="text" name="city"> <input type="text" name="sex">
		<%-- <%@ include file="./selects/depart.txt"%> --%>

		<s:action name="mydepartmentCombox" executeResult="true">
			<%--构建筛选条件or缺省值--%>
			<s:param name="departmentId">
				<s:property value="departmentId" />
			</s:param>

		</s:action>

		<input type="button" id="img1" value="折线图"> <input
			type="button" id="img2" value="饼状图"> <input type="button"
			id="img3" value="柱状图"> <input type="button" id="img4"
			value="ajax"> <input type="button" id="img5" value="excel">
	</form>

<table border="0" cellpadding="0" cellspacing="0" class="pages" align="center">
  <tr>
	<td>
 <div>共<i>${count }</i>条记录</div> 
        <div id="Pagination" class="pagination"></div> 
        &nbsp;
        <div class="clear"></div>
     </td>
  </tr>
</table>  



</body>
<script type="text/javascript">

var ctx = '<s:property value="ctx" />';

//分页回调
//分页
function handlePaginationClick(page_index, jq) {
	var name = $("#name").val();
	var mobile = $("#mobile").val();
	var company = $("#company").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var allpass = $("input[type='checkbox'][name='allpass']:checked");
	var str = "";
	for ( var i = 0; i < allpass.length; i++) {
		str += allpass[i].value + ",";
	}
	if (str != "") {
		str = str.substring(0, str.length - 1);
		$("#isPass").val(str);
	}
	url = "${ctx}/user/searchByPage?name=" + encodeURI(encodeURI(name))
			+ "&moblie=" + mobile + "&company=" + encodeURI(encodeURI(company))
			+ "&startTime=" + startTime + "&endTime=" + endTime + "&start="
			+ (page_index + 1) + "&limit=" + 20 + "&isPass="
			+ $("#isPass").val() + "";
	$(location).attr('href', url);
	return false;
}

	$(function() {
	//	MAOWU.page.init(handlePaginationClick);
		$("#img1").click(
				function() {
					var parm = $("#show").serialize();
					/**解码参数**/
					//parm = decodeURIComponent(parm, true);
					tipsWindown("折线", "img:lineFreeChartAction.do?" + parm,
							"750", "450", "true", "", "true", "img");
				});
		$("#img2").click(
				function() {
					var parm = $("#show").serialize();
					/**解码参数**/
					//parm = decodeURIComponent(parm, true);
					tipsWindown("饼图", "img:pieChartAction.do?" + parm, "750",
							"450", "true", "", "true", "img");
				});
		$("#img3").click(
				function() {
					var parm = $("#show").serialize();
					/**解码参数**/
					//parm = decodeURIComponent(parm, true);
					tipsWindown("柱图", "img:barChartAction.do?" + parm, "750",
							"450", "true", "", "true", "img");
				});
		$("#img4").click(function() {
			alert(ctx);
			alert($("#depart").val());
			MAOWU.ajax.postForm("url", "show", a);

		});
		$("#img5").click(function() {
			$(location).attr('href', 'doExport.do');
		});
	});
	function a() {
		alert(1);
	}
</script>


</html>
