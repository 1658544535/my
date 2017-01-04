<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/_head.js"></script>
<script>
$(function() {
	var time = null;
	$('#zhaqsz').hover(function(){
		$('.drop').show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
	$(".drop").hover(function(){
		clearTimeout(time);
		$(this).show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
});
</script>

<div class="help-nav-width">
	<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">淘竹马分销平台</div>
        <div class="buy-nav">
            <ul>
                <!--  <li><a href="goIndexWeb.do">首页</a></li>	-->	
                <li><a href="gomySupplier.do"><span>供应商主页</span></a></li>
                <li><a href="systemInfoWeb.do"  id="myMessage" >消息</object></a></li>
                <li><a href="#" id="zhaqsz">帐户设置 <img src="images/buy_03.png" alt="" width="10" height="10" /></a></li> 
            </ul>
        </div>
        
        <div class="drop">
        	<div class="drop-arrow"></div>
            <div class="drop-Bj">
            	<div class="drop-title">安全设置</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="goRetrieve.do">修改登录密码</a></dt>
                        <!--<dt><a href="goToSetManufacturerAccountSecurityQuestion.do">密保问题设置</a></dt>-->
                        <!--  <dt><a href="#">其他</a></dt>  -->
                    </dl>
                </div>
            </div>
            
            <div class="drop-Bj02">
            	<div class="drop-title02">个人资料</div>
                <div class="drop-list">
                	<dl>
                        <!--  <dt><a href="goUpdateManufacturerWeb.do">修改个人信息</a></dt>	-->
                        <dt><a href="goManufacturerWeb.do">查看个人信息</a></dt>
                    </dl>
                </div>
            </div>
            
            <div class="drop-Bj03">
            	<div class="drop-title03">账号信息</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="userAttestationWeb.do">店铺认证</a></dt>
                    </dl>
                </div>
            </div>
            
        </div>
    </div>
</div>
