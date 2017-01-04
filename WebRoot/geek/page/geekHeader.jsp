<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/default.css" media="all" />
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />

<!-- 代码 开始 -->
<s:if test="#session.geekuser != null">
<div id="head">
	<div class="wrapper">
		<div class="main-logo ">
			<a class="main-logo-a" href="#" title="返回首页">
				&nbsp;
			</a>
			<span class="sec-title">
				<i class="b-inco">
				</i>
				<a href="#">
					创客中心
				</a>
			</span>
			<div id="st-nav">
				<span id="login-status" class="op" data-status="1">
					<a class="st-icons account" href="#">
						${session.geekuser.name}
					</a>
					<a href="doGeekLogout.do" rel="nofollow">
						退出
					</a>
				</span>
			</div>
		</div>
	</div>
</div>
<div id="container">
	<div class="ui-nav seller-nav">
		<div class="ui-nav-main  seller-nav-bg">
		<s:if test="#session.geekInfoCheck != 0">
			<ul class="wrapper seller-nav-main" id="J_sellerNav">
				<li class="ui-nav-item">
					<a href="geekWeb.do">
						首页
					</a>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="addGeekInfo.do">
						基本信息
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<li class="ui-nav-subitem">
							<a href="addGeekInfo.do">
								基本信息
							</a>
						</li>					
						<li class="ui-nav-subitem">
							<a href="baseInfoPasswd.do">
								修改密码
							</a>
						</li>
					</ul>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="getGeekThemeListWeb.do">
											笔记管理
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<li class="ui-nav-subitem">
							<a href="getUserCirclePostListWebGeek.do">
								笔记列表
							</a>
						</li>
						<li class="ui-nav-subitem">
							<a href="goAddUCPostGeek.do">
								笔记发布
							</a>
						</li>
					</ul>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="getGeekThemeListWeb.do">
						专题管理
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<li class="ui-nav-subitem">
							<a href="getGeekThemeListWeb.do">
								专题列表
							</a>
						</li>
						<li class="ui-nav-subitem">
							<a href="goAddGeekTheme.do">
								专题发布
							</a>
						</li>
					</ul>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="geekShopWeb.do">
						店铺管理
						<!--
						<i class="iconfont">
							&#xe601;
						</i>
						-->
					</a>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="geekBrandManage.do">
						品牌管理
						<!--
						<i class="iconfont">
							&#xe601;
						</i>
						-->
					</a>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="productManageGeekWeb.do">
						商品管理
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
				</li>
			</ul>
		</s:if>
		</div>
	</div>
</div>
</s:if>
<s:else>
<div id="head">
</div>
</s:else>
