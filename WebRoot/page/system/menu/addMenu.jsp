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
		<form action="addMenu.do" id="MyForm" method="post">

			<div class="s_nav">
				<a>系统管理</a> &gt; <a href="findMenulesect.do">菜单管理</a> &gt; <a
					href="#">添加菜单信息</a>
			</div>


			<div class="top-main-content-div">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right" class="grey">菜单名称:</td>
						<td><input name="name" value="" class="inputText"> <input
							name="level" type="hidden"
							value="<s:property value='menuObj.level'/>"> <input
							name="path" value="url" type="hidden"> <input
							name="icon" value="/images/menu_icon_10.png" type="hidden"><span
							id="name_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey">菜单英文名称:</td>
						<td><input name="nameEn" value="" class="inputText">
							<span id="nameEn_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey">排序:</td>
						<td><input name="sorting" value="0" class="inputText">
							<span id="sorting_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey">审核状态:</td>
						<td><input type="radio" value="1" name="status" id="status"
							checked="checked">已审核 <input type="radio" value="0"
							name="status" id="status">未审核
						<td>
					</tr>
				</table>
				<div class="Btn_div">
					<input type="button" class="ok_btn" value="保存" id="MyFormSubmit">
					<input type="button" class="back_btn" value="返回" id="returnPage">
				</div>
			</div>
		</form>
	</div>

</body>
<script type="text/javascript">
	var nameCn = new tt.Field("菜单名称 ", "name").setMsgId("name_msgId");
	tt.vf.req.add(nameCn); //为必输项
	new tt.LV().set(0, 50).add(nameCn);
</script>
</html>