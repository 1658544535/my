<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>left</title>

<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/public.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/leftmenu.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-1.7.js"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("menu");
		myMenu.init();
	};
	// ]]>
</script>

</head>

<body class="menu_bg">
		<div class="leftmenu">
		<div id="menu" class="sdmenu">
			<s:iterator value="#session.menuList" var="menu">
			
				<div class="collapsed">   
	            	<span class="Fmenu">
						<img alt="" align="absmiddle"  src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n><s:property value="#menu.icon" />">
						<s:property value="name" />
					</span>
					<s:iterator value="#menu.menuPojoList">
							<a class="menu-url" href=<s:property value="path" /> target="workFrame">
								<s:property value="name" />
							</a>
					</s:iterator>
				</div>
			
			</s:iterator>
			
		</div>
	</div>
</body>
</html>
