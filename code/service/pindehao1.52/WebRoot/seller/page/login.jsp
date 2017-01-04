<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/homecsslib.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/member_login.css" type="text/css" media="all" />
    </head>
	<body>
        <!-- 头部 -->
        <div class="m-wrapper-1080 m-mb-header">
            <div class="f-fl">
                <a class="mb-main-logo" href="#" title="淘竹马-妈妈的特卖会">
                </a>
            </div>
            <div class="f-fr">
                <a class="mb-guarantee-logo" href="#" title="淘竹马正品保证-100%正品,全场包邮,7天无理由退货-淘竹马">
                </a>
            </div>
        </div>
        <!-- 内容区 -->
        <div class="m-wrapper-1080 m-mb-content">
            <!-- 左侧广告位 -->
            <div class="fl m-mb-amscont">
                
            </div>
            <!-- 右侧输入区域 -->
            <div class="fr m-ipt-cont">
                <form id="form1" action="doSellerLogin.do" class="m-ipt-area J_loginForm" method="post" accept-charset="utf-8">
                    <div class="area-title">
                        登录淘竹马
                    </div>
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf012d;</i>
                            </span>
                            <input type="text" class="ipt-input J_iptUsername" placeholder="您的帐号" name="username" value="" >
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                            <span id="usernameMsg"></span>
                        </label>
                    </div><!-- </br> -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf00c9;</i>
                            </span>
                            <input type="password" class="ipt-input J_iptPasswd" placeholder="密码" name="passwd" value="" >
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                            <span id="passwdMsg"></span>
                        </label>
                    </div><!-- </br> -->
                    <%-- <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf00cb;</i>
                            </span>
                            <img class="f-fr checkcode-img J_checkcodeImg" title="看不清？换一张" src="http://d.beibei.com/checkcode/show.html">
                            <input type="text" class="ipt-input ipt-input-checkcode J_iptCheckcode"
                            placeholder="验证码" name="checkcode" maxlength="4" autocomplete="off" value=""
                            tabindex="3">
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                        </label>
                    </div> --%>
                    <!-- <div class="ipt-tip">
                        <a class="f-fr" href="#">
                            忘记密码
                        </a>
                        <input type="checkbox" checked="checked" id="remember-me" name="rememberme">
                        &nbsp;&nbsp;记住我（下次自动登录）　
                    </div> -->
                    <div class="ipt-quick">
                        <span class="f-fr">
                            <span>
                            </span>
                            <a class="u-hp-target" href="goForgetPasswordWeb.do">
                                忘记密码
                            </a>
                        </span>
                    </div>
                    <div class="ipt-btn-area">
                        <!--<input id="sbutton" type="button" value="登录" class="ipt-btn J_iptBtn" style="border:none;width:100%;" />-->
                        <button id="sbutton" class="ipt-btn J_iptBtn" style="border:none;width:100%;">登录</button>
                    </div>
                    <div class="ipt-quick">
                        <%-- <span class="f-fl">
                            快捷登录方式：
                        </span> --%>
                        <span class="f-fr">
                            <span>
                                还没账号？
                            </span>
                            <a class="u-hp-target" href="goRegWeb.do">
                                立即注册
                            </a>
                        </span>
                        <!-- <a class="f-fl ipt-quick-login ipt-quick-qq" href="#"
                        title="QQ账号登录">
                        </a>
                        <a class="f-fl ipt-quick-login ipt-quick-tb" href="#"
                        title="淘宝账号登录">
                        </a>
                        <a class="f-fl ipt-quick-login ipt-quick-wx" href="#"
                        title="微信账号登录">
                        </a> -->
                    </div>
                    <div class="ipt-quick">
	                <span class="u-hp-target">
	                    ${msg }
	                </span>
	                </div>
                </form>
            </div>
        </div>
        <!-- 页脚 -->
        <div id="footer">
			<div class="footer-nav">
			&nbsp;
				<!-- <a rel="nofollow" href="goSellerIndex.do">
					首页
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					关于淘竹马
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					商家入驻
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					正品保证
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					人才招聘
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					联系我们
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					帮助中心
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					友情链接
				</a>
				|
				<a rel="nofollow" target="_blank" href="#">
					手机客户端
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					客服在线
				</a> -->
			</div>
			<div class="footer-content">
				<div class="wrapper">
					<p>
                    	广东群宇互动科技有限公司 Copyright © 2014 All Rights Reserved 版权所有 粤ICP备13081564号
					</p>
				</div>
			</div>
		</div>
    </body>
</html>
<script>
//var username =new tt.Field("手机号 / 邮箱","username").setMsgId("usernameMsg");
//var passwd =new tt.Field("密码","passwd").setMsgId("passwdMsg");
//tt.vf.req.add(username,passwd);

$(document).ready(function() {
	$("#sbutton").click(function(){		
		//if(tt.validate()){
			document.getElementById("form1").submit();
		//}
	});
});
</script>