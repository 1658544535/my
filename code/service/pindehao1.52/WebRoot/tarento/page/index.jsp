<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马达人中心</title>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/css/default.css" media="all" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
</head>

<body>
<jsp:include page="tarentoHeader.jsp"/>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
						body{background:#f9f9f9}#content{box-shadow:none;background:transparent}
						.bo-left{float:none;width:1000px;margin:0 auto;}
                    </style>
                    <div class="clearfix">
                        <!-- 主体内容 start -->
                        <div class="bo-left">
                            <!-- 商家信息 start-->
                            <div class="seller-info l-box">
                                <h1 class="title-1">欢迎您！</h1>
                                <div class="info-con clearfix">
                                    <span class="score-con">
										笔记<br/>
										<b class="num">${userPostCount}</b>
									</span>
                                    <span>
                                    	粉丝<br/>
										<b class="num">${userCircleFollowCount}</b>
									</span>
                                 
                                </div>
                            </div>
                            <!-- 商家信息 end -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="tarentoFooter.jsp"/>

    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/base.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</body>

</html>
