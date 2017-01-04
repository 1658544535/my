<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/default.css" media="all" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/js/base.js" type="text/javascript"></script>
</head>

<body>
   <jsp:include page="../geekHeader.jsp"/>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                    	.profile .ui-dlist .ui-dlist-tit {clear: both;}
                    </style>
                    <h1 class="seller-title">
                    	基本信息
                    	<a class="ui-button ui-button-morange fr" href="baseInfoEdit.do" id="update">修改</a>
                    </h1>
         
                    <div class="profile">
                        <div class="box">
                            <dl class="ui-dlist">
                                
                                <dt class="ui-dlist-tit">头像：</dt>
                                <dd class="ui-dlist-det"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userlogo/${sysLoginPojo.image}" width="120" height="120"></dd>
 
                                <dt class="ui-dlist-tit">昵称：</dt>
                                <dd class="ui-dlist-det"> ${sysLoginPojo.name}</dd>
                                
                                <dt class="ui-dlist-tit">宝宝性别：</dt>
                                 <s:if test="userInfoPojo.babySex==1">王子</s:if>
                                   <s:if test="userInfoPojo.babySex==2">公主</s:if>
                                
                                <dt class="ui-dlist-tit">宝宝生日：</dt>                                
                                <dd class="ui-dlist-det"> ${userInfoPojo.babyBirthday}</dd>
                                

                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../geekFooter.jsp"/>

</body>
<script>
</script>
</html>
