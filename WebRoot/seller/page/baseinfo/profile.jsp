<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>
	<body>
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .profile .ui-dlist .ui-dlist-tit { clear: both; }
                        </style>
                        <h1 class="seller-title">
                            基本信息
                        </h1>
                        <div class="profile">
                            <div class="box">
                                <h2>
                                    商家信息
                                </h2>
                                <dl class="ui-dlist">
                                    <dt class="ui-dlist-tit">
                                        公司名称：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                       ${manufacturerPojo.company }
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        公司简介：
                                    </dt>
                                    <dd class="ui-dlist-det" style='word-wrap: break-word;'>
                                        ${manufacturerPojo.content }
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        旗下品牌：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        ${manufacturerPojo.brand }
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        公司地址：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        ${manufacturerPojo.address }
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        公司电话：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        ${manufacturerPojo.phone }
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        公司传真：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        ${manufacturerPojo.fax }
                                    </dd>
                                    <!-- <dt class="ui-dlist-tit">
                                        淘宝店铺：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        <a href="" target="_blank">
                                            
                                        </a>
                                    </dd> -->
                                    <dt class="ui-dlist-tit">
                                        入驻时间：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        ${manufacturerPojo.createDateStr }
                                    </dd>
                                    <div align="right">
                                        <p>
                                            商家咨询：
                                            <span style="position:relative;top:7px;">
                                            <a target='_blank' href='http://crm2.qq.com/page/portalpage/wpa.php?uin=4001503677&aty=0&a=0&curl=&ty=1'><img alt="商家咨询" src="seller/images/help_contact_d1.png"></a>
                                            </span>
                                        </p>
                                        <p>
                                            咨询电话：
                                            <span>
                                                400-150-3677
                                            </span>
                                        </p>
                                        <p>
                                            工作时间：周一到周六<br /> 9:00-12:00/13:30-17:30
                                        </p>
                                    </div>
                                </dl>
                            </div>
                            <!-- 单个contact -->
                            <div class="box">
                                <h2>
                                    业务负责人信息
                                </h2>
                                <table class="contact-table ui-table ui-table-noborder">
                                    <tbody>
                                        <tr class="ui-table-split">
                                            <td>
                                                ${manufacturerPojo.contact }
                                            </td>
                                            <td>
                                                <p>
                                                    电话：${manufacturerPojo.phone }
                                                </p>
                                                <p>
                                                    QQ：${manufacturerPojo.QQ }
                                                </p>
                                                <p>
                                                    邮箱：${manufacturerPojo.email }
                                                </p>
                                            </td>
                                            <td>
                                                <p>
                                                    添加时间：${manufacturerPojo.createDateStr }
                                                </p>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- 多个contact，暂无 -->
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>