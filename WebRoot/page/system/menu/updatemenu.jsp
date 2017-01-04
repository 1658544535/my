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

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/menu.js"></script>

</head>

<body>
	<DIV class="sub_wrap">
		<form action="updateMenuByHost.do" id="MyForm" method="post">


			<div class="s_nav">
				<a>系统管理</a> &gt; <a href="findMenulesect.do">菜单管理</a> &gt; <a
					href="#">修改菜单信息</a>
			</div>

			<div class="top-main-content-div">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">

					<tr>
						<td align="right" class="grey">菜单名称:</td>
						<td><input name="name"
							value="<s:property value='menuObj.name'/>" class="inputText">
							<input name="id" type="hidden"
							value="<s:property value='menuObj.id'/>"> <input
							name="path" value="url" type="hidden"> <input
							name="icon" value="/images/menu_icon_10.png" type="hidden"> <span
							id="menuName_msgId"></span></td>
					</tr>

					<tr>
						<td align="right" class="grey">菜单英文名称:</td>
						<td><input name="nameEn" value="<s:property value='menuObj.nameEn'/>"
							class="inputText"> <span id="nameEn_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey">排序:</td>
						<td><input name="sorting" value="<s:property value='menuObj.sorting'/>"
							class="inputText"> <span id="sorting_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey">审核状态:</td>
						<td><input type="radio" value="1" name="status" id="status"
							<c:if test="${menuObj.status==1}"> checked="checked"</c:if>>已审核
							<input type="radio" value="0" name="status" id="status"
							<c:if test="${menuObj.status==0}"> checked="checked"</c:if>>未审核
						
						</td>
					</tr>
				</table>

				<div class="Btn_div">
					<input class="ok_btn" type="button" value="保存" id="MyFormSubmit">
					<input class="back_btn" type="button" value="返回" id="returnPage">

				</div>
			</div>
		</form>
	</DIV>
</body>

<script type="text/javascript">
	var menuName = new tt.Field(" 菜单名称 ", "name")
			.setMsgId("menuName_msgId");
	tt.vf.req.add(menuName); //为必输项
	new tt.LV().set(0, 50).add(menuName);
</script>
</html>