<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<!--IOS中Safari允许全屏浏览-->
<meta content="yes" name="apple-mobile-web-app-capable">
<!--IOS中Safari顶端状态条样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<title>操作步骤</title>
<style>
html,body{width:100%;height:100%;margin:0;padding:0;background:#fff798;font-family:arial,Microsoft YaHei;}
html{font-size:40px;}
.top,.top img,.step,.step img{display:block;width:100%;height:auto;}
a{-webkit-tap-highlight-color:rgba(0,0,0,0);}
</style>
</head>

<body>
<div class="wrapper">
    <!--顶部图片-->
    <div class="top"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/wallet/images/top_step.jpg" alt="邀请好友，立刻赚钱" /></div>

    <!--操作步骤-->
    <div class="step">
        <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/wallet/images/step.jpg" alt="操作步骤" />
    </div>

</div>
</body>
</html>
