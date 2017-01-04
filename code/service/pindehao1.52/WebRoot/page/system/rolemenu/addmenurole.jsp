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
		<form action="addmenurole.do" id="menuRoleForm" method="post">

			<div class="s_nav">
				<a href="#">系统管理</a> &gt; <a href="#">菜单角色管理</a>
			</div>

			<div class="top-main-content-div">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td>角色名称:</td>
						<td><select name="menuRolePojo.roleId" id="menuRoleId"
							style="width: 200px;">
								<option value="0">请选择</option>

								<c:forEach items="${roleAllList }" var="role">

									<option value="${role.id }"
										<c:if test="${menuRole.roleId==role.id}">selected="selected"</c:if>>${role.name }</option>
								</c:forEach>
						</select></td>
						<td></td>
						<td></td>

					</tr>
					<tr>
						<td></td>
						<td valign="top">
							<h3 class="tit">已选择</h3> <select
							style="width: 200px; height: 525px;" size="35" id="selectedValue"
							ondblclick="dblclickRemoveOne();">
								<option value=""></option>

						</select> <input type="hidden" id="menuRolePojo.menuIds"
							name="menuRolePojo.menuIds" value="">
						</td>
						<s:if test="#session.role.roleId!=7"><td>
							<p>&nbsp;</P> <input value="左移" class="Btn blue" type="button"
							id="leftShift">
							<p>&nbsp;</P> <input value="右移" class="Btn blue" type="button"
							id="removeOne">
							<p>&nbsp;</P> <input type="button" class="Btn orange" value="保存"
							id="menuRoleFormSubmit">
							<p>&nbsp;</P> <input type="button" class="Btn blue" value="删除"
							id="delemenuRole">
							<p>&nbsp;</P>
						</td>

						<td valign="top">
							<p>&nbsp;</p>
							<h3 class="tit">主菜单</h3> <select
							style="width: 200px; height: 250px;" size="15" id="level"
							ondblclick="dblclickLeftShift();">
								<s:iterator value="menuFatherList">
									<option value="<s:property value='id'/>"><s:property
											value='name' /></option>
								</s:iterator>
						</select>
							<p>&nbsp;</p>
							<h3 class="tit">子菜单</h3> <select
							style="width: 200px; height: 250px;" size="15" id="sonSelect"
							name="sonSelect" ondblclick="dblclickLeftShift();">
								<option value="1"></option>
						</select>
						</td></s:if>
					</tr>

				</table>
			</div>
		</form>
	</DIV>
</body>
<script type="text/javascript">
	selectValue = $("#menuRoleId").val();

	var url = "getMenuRoleByRoleid.do";
	var datas = {
		"menuRolePojo.roleId" : selectValue
	};
	$
			.ajax({
				type : "post",
				url : url,
				data : datas,
				datatype : "json",
				success : function(result) {
					var obj = document.getElementById('selectedValue');

					obj.options.length = 0; //删除所有选项option   

					var myobj = eval(result.jsonStr);

					for (var i = 0; i < myobj.length; i++) {
						obj.options.add(new Option(myobj[i].menuName,
								myobj[i].menuId)); //这个兼容IE与firefox  
					}
				}
			});

	
</script>

</html>