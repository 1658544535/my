<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <link rel="stylesheet" href="../../geek/css/default.css" media="all" />
    <link rel="stylesheet" href="../../geek/css/seller_common.css" type="text/css" media="all" />
    <style>
    	.ui-tipbox-content{margin:14px 0 20px 87px!important;}
    	.ui-tipbox-content h3.ui-tipbox-title{margin-top:22px;}
    </style>
</head>
<body>
<jsp:include page="../geekHeader.jsp"/>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                	<style>
                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}.apply-box select{width:190px;}.store-tab{border-top:1px solid #f0f0f0;}
                    </style>
                    <h1 class="seller-title">
                        品牌管理
                        <a class="ui-button ui-button-morange fr" href="goEditGeekBrand.do?userMakerBrandPojo.id=${userMakerBrandPojo.id}">修改</a>
                    </h1>
                    <form action="#" accept-charset="utf-8" class="ui-form p50" method="">
         		<s:if test = "userMakerBrandPojo.status == 0" >
           		 <div class="ui-tipbox ui-tipbox-question">
                    <div class="ui-tipbox-icon"><i class="iconfont">&#xf0142;</i></div>
                    <div class="ui-tipbox-content">
                        <h3 class="ui-tipbox-title">您的品牌信息正在审核中~</h3>
                    </div>
                </div>
           		 </s:if>  
            	<s:elseif test="userMakerBrandPojo.status == 1">    
                    <div class="ui-tipbox ui-tipbox-success">
	                    <div class="ui-tipbox-icon"><i class="iconfont">&#xf0142;</i></div>
	                    <div class="ui-tipbox-content">
	                        <h3 class="ui-tipbox-title">恭喜您！审核已经通过！</h3>
	                    </div>
	                </div>
               </s:elseif>  
               <s:elseif test="userMakerBrandPojo.status == 2">
               	<div class="ui-tipbox ui-tipbox-error">
	                <div class="ui-tipbox-icon"><i class="iconfont">&#xf0142;</i></div>
	                <div class="ui-tipbox-content">
	                    <h3 class="ui-tipbox-title">很遗憾，您的品牌审核没有通过，请您修改为正确的信息后再次提交审核。</h3>
	                </div>
	            </div>    
	            </s:elseif>         
                    <div class="p50">
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    品牌名称
                                </label>
                                <p class="ui-form-text">${userMakerBrandPojo.brandName}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    品牌LOGO
                                </label>
                                <div>
                                <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userMakerBrand/${userMakerBrandPojo.logo}" id="imgs" style="width:400px;height:210px;" />
                                   
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    商标注册证明
                                </label>
                                <div>
                                    <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userMakerBrand/${userMakerBrandPojo.registrationCertificate}" id="imgs" style="width:400px;height:210px;" />
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    内容
                                </label>
                                
                                    <script id="editor" type="text/plain" style="width:708px;height:400px;">${userMakerBrandPojo.content}</script>
                                
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
<jsp:include page="../geekFooter.jsp"/>

    <script src="../../seller/js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../geek/js/base.js" type="text/javascript" charset="utf-8"></script>


    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">UE.getEditor("editor",{toolbars: [[]],readonly:true,wordCount:false,});</script>
</body>

</html>
