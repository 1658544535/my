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
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/area.js" type="text/javascript"></script>
    <script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
	<%-- <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/default.css" media="all" />
	<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/spage.css"/> --%>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript" charset="utf-8" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
  <jsp:include page="../geekHeader.jsp"/>

        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
                    </style>
                    <h1 class="seller-title">专题发布</h1>
                    <form action="martshowApply.do" accept-charset="utf-8" class="ui-form p50 view-MartShowForm clone-MartShowForm" method="">
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 标题
                                </label>
                                <input type="text" class="ui-input" name="userMakerThemePojo.specialName"/>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 专题主图
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img src="" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="" />
                      
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 内容
                                </label>
                                <!--编辑器-->
                                <script id="editor" type="text/plain" style="width:708px;height:400px;" name="userMakerThemePojo.content"></script>
                            </div>
                        </div>

                        <div class="apply-box boxs-botom">
                            <div class="ui-form-item">
                                <input type="submit" style="padding-top: 0px;" class="ui-button ui-button-mred view-SubmitBtn" value="修改保存并提交审核">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
<jsp:include page="../geekFooter.jsp"/>
    <script src="../js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="../js/base.js" type="text/javascript"></script>

    <script type="text/javascript" charset="utf-8" src="../js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
        $(function(){
            //编辑器
            var ue = UE.getEditor('editor');
        });
    </script>
</body>

</html>
