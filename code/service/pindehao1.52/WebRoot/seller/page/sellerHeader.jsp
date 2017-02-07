<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/default.css" media="all" />
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/base.js" type="text/javascript"></script>
<!-- 代码 开始 -->
<s:if test="#session.wuser != null">
<div id="head">
	<div class="wrapper">
		<div class="main-logo ">
			<a class="main-logo-a" href="goSellerIndex.do" title="返回首页">
				&nbsp;
			</a>
			<span class="sec-title">
				<i class="b-inco">
				</i>
				<a href="goSellerIndex.do">
					商家中心
				</a>
			</span>
			<div id="st-nav">
				<span id="login-status" class="op" data-status="1">
					<a class="st-icons account" href="goSellerIndex.do">
						${session.wuser.name}
					</a>
					<a href="doSellerLogout.do" rel="nofollow">
						退出
					</a>
					<%-- |
					<a href="goMessagesCenterWeb.do">
					<s:if test="#session.sellerMessageCount > 0">
						<font id="messageCss" class="" style="color: #ff647c;">
						消息<span id="messageCount">(${sellerMessageCount})</span></font>
					</s:if>
					<s:else>
						<font id="messageCss">
						消息<span id="messageCount"></span></font>
					</s:else>
					</a> --%>
				</span>
				<!-- |
				<a href="#" target="_blank">
					商家咨询
				</a>
				| -->
				<%-- <s:if test="#session.informationIsCheck == 1"> --%>
				<!-- <a class="bold" href="goHelpWeb.do">
					帮助中心
				</a> -->
				<%-- </s:if> --%>
			</div>
		</div>
	</div>
</div>
<div id="container">
	<div class="ui-nav seller-nav">
		<div class="ui-nav-main  seller-nav-bg">
<s:if test="#session.informationIsCheck == 1">
			<ul class="wrapper seller-nav-main" id="J_sellerNav">
				<li class="ui-nav-item">
					<a href="goSellerIndex.do">
						首页
					</a>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="getProfileWeb.do">
						基本信息
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<!-- <li class="ui-nav-subitem">
							<a href="getProfileWeb.do">
								基本信息
							</a>
						</li> -->
						<!--
						<li class="ui-nav-subitem">
							<a href="baseinfo/pay_account.html">
								收款银行账户
							</a>
						</li>
						<li class="ui-nav-subitem">
							<a href="baseinfo/invoice.html">
								开票信息
							</a>
						</li>
						-->
						<!-- <li class="ui-nav-subitem">
							<a href="goAddressWeb.do">
								退货地址管理
							</a>
						</li>					
						<li class="ui-nav-subitem">
							<a href="goPasswdWeb.do">
								修改密码
							</a>
						</li> -->
						<li class="ui-nav-subitem">
							<a href="goInformationPerfectWeb.do">
								商家信息
							</a>
						</li>
					</ul>
				</li>
				<li class="ui-nav-item has-sub">
					<a href="productManageSellerWeb.do">
						商品管理
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<li class="ui-nav-subitem">
							<a href="productManageSellerWeb.do">
								商品列表
							</a>
						</li>
						<li class="ui-nav-subitem">
							<a href="goProductAddSellerWeb.do">
								创建商品
							</a>
						</li>
						<!-- <li class="ui-nav-subitem">
							<a href="getBrandListWeb.do">
								品牌列表
							</a>
						</li> -->
						<!-- <li class="ui-nav-subitem">
							<a href="getBrandAddWeb.do">
								添加品牌
							</a>
						</li> -->
					</ul>
				</li>
				<!-- <li class="ui-nav-item has-sub">
					<a href="getMartShowWeb.do">
						品牌特卖
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<li class="ui-nav-subitem">
							<a href="getMartShowWeb.do">
								专场列表
							</a>
						</li>
						<li class="ui-nav-subitem">
							<a href="applyMartShowWeb.do">
								报名专场
							</a>
						</li>
					</ul>
				</li> -->
				<li class="ui-nav-item has-sub">
					<a href="getMyOrderWeb.do">
						交易管理
						<i class="iconfont">
							&#xe601;
						</i>
					</a>
					<ul class="ui-nav-submain">
						<li class="ui-nav-subitem">
							<a href="getMyOrderWeb.do">
								我的订单
							</a>
						</li>
						<li class="ui-nav-subitem">
							<a href="goOrderDeliveryWeb.do">
							发货订单导入
							</a>
						</li>
						<!--
						<li class="ui-nav-subitem pageSellerOrderShipexportlist">
							<a href="trademng/ship.html">
								发货
							</a>
						</li>
						-->
						<li class="ui-nav-subitem">
							<a href="goRefundWeb.do">
								售后管理
							</a>
						</li>
					</ul>
				</li>
				<li class="ui-nav-item has-sub">
                            <a href="goSettleWeb.do">
                                财务管理
                                <i class="iconfont">
                                    &#xe601;
                                </i>
                            </a>
                            <ul class="ui-nav-submain">
                                <!-- <li class="ui-nav-subitem">
                                    <a href="goSettleWeb.do">
                                        结算
                                    </a>
                                </li> -->
                                <li class="ui-nav-subitem">
                                    <a href="goWithdrawWeb.do">
                                        提现
                                    </a>
                                </li> 
                                <li class="ui-nav-subitem">
                                    <a href="goEditSellerBank.do">
                                        账户设置
                                    </a>
                                </li> 
                            </ul>
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
