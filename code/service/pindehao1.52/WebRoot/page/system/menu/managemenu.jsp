<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/menu.js"></script>

</head>



<body>
<DIV class="sub_wrap"> 
<form action="addmenu.do" id="menuForm" method="post">

		
		<div class="s_nav"><a href="#">系统管理</a> &gt; <a href="#">菜单管理</a></div>
    
    


			<div class="top-main-content-div">
			 <table width="100%" border="0" class="Search_table">
					
					
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
							
							<p>&nbsp;</P>
							<h3 class="tit">主菜单</h3>
							<select style="width:200px;hight:100px;" size="20"name="level" id="level" >
								<s:iterator value="menuFatherList">
									<option  value="<s:property value='id'/>"><s:property value='name'/></option>
								</s:iterator>
							</select>
							<p>&nbsp;</P><s:if test="#session.role.roleId!=7">
							<input type="button" id="goAddMenu" class="addBtn"  value="添加">
							
							
							<input type="button" id="goUpdateMenu" class="editBtn" value="修改" ></s:if>
						</td>
						<td>
							
							<p>&nbsp;</P>
							<h3 class="tit">子菜单</h3>
							<select style="width:200px;hight:100px;" size="20" id="sonSelect" name="sonSelect">
								<option value="1">  </option>
							</select>
							<p>&nbsp;</P><s:if test="#session.role.roleId!=7">
							<input type="button" id="goSonAddMenu" class="addBtn" value="添加">
							
							<input type="button" id="goSonUpdateMenu" class="editBtn" value="修改" ></s:if>
						</td>
					</tr>
					
					
					
				
				</table>
			</div>
</form>
</DIV>
</body>
</html>