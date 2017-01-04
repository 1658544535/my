<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>推送信息管理</a> &gt; <a href="#">修改首页推送图</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updatePushHomePage.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
					<input type="hidden" name="pushHomePagePojo.id" id="pushHomePagePojo.id" value="${pushHomePagePojo.id}" class="inputText">
					<input type="hidden" name="pushHomePagePojo.type" id="pushHomePagePojo.type" value="${pushHomePagePojo.type}" class="inputText">
					<tr>
						<td align="right" class="grey" width="15%">标题:</td>
						<td width="35%"><input type="text" name="pushHomePagePojo.title" value="${pushHomePagePojo.title}" class="floatLeft">
						<div id="title_msgId"></div></td>
						<td align="right" class="grey" width="15%">审核状态:</td>
						<td width="35%">
						<select name="pushHomePagePojo.status" id="">
					 		<option value="">全部</option>
							<s:iterator value="statusSysDictList">
								<option value="<s:property value="value"/>"
								<s:if test="value == pushHomePagePojo.status">selected="selected"</s:if>><s:property value="name" />
								</option>
							</s:iterator>
						</select>
						<div id="status_msgId"></div>
						</td>
					</tr>
					<tr>
					<!--
					<c:choose>
    					<c:when test="${type == 0 && (fn:contains(pushHomePagePojo.title, '大牌') || fn:contains(pushHomePagePojo.title, '秒杀'))}">
    						<input type="hidden" name="pushHomePagePojo.param" value="${pushHomePagePojo.param}" class="floatLeft" >
    					</c:when>
      					<c:otherwise>
		      				<c:if test="${type == 0}"><td align="right" class="grey" width="15%">商品类型ID:</td></c:if>
		      				<c:if test="${type != 0}"><td align="right" class="grey" width="15%">商品ID:</td></c:if>
							<td width="35%"><input type="text" name="pushHomePagePojo.param" value="${pushHomePagePojo.param}" class="floatLeft" ></td>
    					</c:otherwise>
					</c:choose>
					-->
						<c:if test="${type == 0}"><td align="right" class="grey" width="15%">商品类型ID:</td></c:if>
		      			<c:if test="${type != 0}"><td align="right" class="grey" width="15%">商品ID:</td></c:if>
						<td width="35%"><input type="text" name="pushHomePagePojo.param" value="${pushHomePagePojo.param}" class="floatLeft" ></td>
						<td align="right" class="grey" width="15%">排序:</td>
						<td width="35%"><input type="text" name="pushHomePagePojo.sorting" value="${pushHomePagePojo.sorting}" class="floatLeft" >
						<div id="sorting_msgId"></div></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">图片:</td>
						<td width="35%"><img class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/pushhomepg/${pushHomePagePojo.image}" height="100px"  />
						<input type="file" name="upfile2" class="floatLeft" id="ticketName"></td>
						<td align="right" class="grey" width="15%">备注:</td>	
						<td width="35%">
				        <textarea class="floatLeft" rows="6" cols="50" name="pushHomePagePojo.remarks" id="pushHomePagePojo.remarks" >${pushHomePagePojo.remarks}</textarea>
						<span id="remarks_msgId"></span>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">活动ID:</td>
						<td width="35%"><input type="text" name="pushHomePagePojo.activityId" value="${pushHomePagePojo.activityId}" class="floatLeft" >
						<div id="activityId_msgId"></div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
			<input type="button" class="ok_btn" value="提交" id="sbutton" />
		</div>
	</div>
</body>
</html>
<script>
	var title      = new tt.Field("标题 ","pushHomePagePojo.title").setMsgId("title_msgId");
	var sorting    = new tt.Field("排序","pushHomePagePojo.sorting").setMsgId("sorting_msgId");
	var activityId = new tt.Field("活动ID","pushHomePagePojo.activityId").setMsgId("activityId_msgId");
	tt.vf.req.add(title,sorting,activityId);
	new tt.LV().set(0, 50).add(title);
	new tt.NRV().set(0, '++').add("sorting,activityId");
	
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});	
</script>