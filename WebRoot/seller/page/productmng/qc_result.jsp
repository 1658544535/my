<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
                            .qc-body{margin:36px;padding-bottom:50px;}.qc-body table{width:100%;border:1px solid #ddd;}.qc-body thead{background-color:#fff;}.qc-body table th{padding:12px 0;}.qc-body table td{padding:10px 0;}.qc-body table th,.qc-body table td{text-align:center;border:1px solid #ddd;}.qc-body td{word-break:break-all;word-wrap:break-word;}
                        </style>
                        <h1 class="seller-title">
                            质检结果
                        </h1>
                        <div class="qc-body">
                            <div style="font-size: 16px; margin-bottom: 25px;">
                                质检结果：
                                <span style="color: green">
                                    质检通过
                                </span>
                            </div>
                            <div style="font-size: 16px; margin-bottom: 15px;">
                                商品质检结果：
                            </div>
                            <table class="pure-table">
                                <thead>
                                    <tr>
                                        <th class="time">
                                            序号
                                        </th>
                                        <th class="type">
                                            商品名称
                                        </th>
                                        <th class="detail">
                                            货号
                                        </th>
                                        <th class="amt">
                                            规格/尺寸
                                        </th>
                                        <th class="num">
                                            数量
                                        </th>
                                        <th class="reason">
                                            单价
                                        </th>
                                        <th class="remind">
                                            质检结果
                                        </th>
                                        <th class="remind">
                                            质检报告
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            0
                                        </td>
                                        <td>
                                            荣泰603-4 卡通婴儿摇铃3件套
                                        </td>
                                        <td>
                                            603-4
                                        </td>
                                        <td>
                                            单色
                                        </td>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            16
                                        </td>
                                        <td>
                                            质检合格
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            荣泰605C-1 益智婴儿摇铃5件套
                                        </td>
                                        <td>
                                            605C-1
                                        </td>
                                        <td>
                                            单色
                                        </td>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            23
                                        </td>
                                        <td>
                                            质检合格
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            2
                                        </td>
                                        <td>
                                            荣泰612 益智婴儿摇铃12件套
                                        </td>
                                        <td>
                                            612
                                        </td>
                                        <td>
                                            单色
                                        </td>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            34
                                        </td>
                                        <td>
                                            质检合格
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            3
                                        </td>
                                        <td>
                                            荣泰毛绒床铃669-2
                                        </td>
                                        <td>
                                            669-2
                                        </td>
                                        <td>
                                            单色
                                        </td>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            75
                                        </td>
                                        <td>
                                            质检合格
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            4
                                        </td>
                                        <td>
                                            荣泰奇趣启智健身毯670-6
                                        </td>
                                        <td>
                                            670-6
                                        </td>
                                        <td>
                                            单色
                                        </td>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            87
                                        </td>
                                        <td>
                                            质检合格
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div style="font-size: 16px; margin-bottom: 15px;margin-top: 15px;">
                                质检提醒：无
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		</div>
	</body>
</html>