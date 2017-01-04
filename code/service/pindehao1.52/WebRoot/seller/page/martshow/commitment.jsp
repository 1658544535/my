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
                            商家专场活动承诺书
                        </h1>
                        <div class="profile">
                            <div class="box">
                                <h2>
                                    承诺书信息
                                </h2>
                                <dl class="ui-dlist">
                                    <dt class="ui-dlist-tit">
                                        100%正品保证：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                       保证所有在淘竹马销售的商品均为品牌正品，吊牌齐全。不销售假冒伪劣、侵权、翻新或有其他问题的商品。
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        如实描述：
                                    </dt>
                                    <dd class="ui-dlist-det" style='word-wrap: break-word;'>
                                       保证所展示的商品图片和商品描述真实性，并清楚的说明了所售商品的所有属性。
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        库存真实：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                       保证活动专场商品库存真实可售。
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        价格控制：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                       <p> 在淘竹马上线销售期间，要求商品价格全网最低，否则淘竹马有权拒绝上专场活动。 所谓全网最低，是指：</p>
                                       <p> a、比价不限如下综合类电商网站：天猫、京东、苏宁易购、卓越亚马逊、当当、一号店等；</p>
                                       <p> b、同是正品、同SKU，同销售时间，商品标价最低（促销活动和运费等不含在比价范围）；</p>
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        全场包邮服务：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                       使用淘竹马指定的物流公司发货并承担发货运费。指定物流公司有：申通快递，圆通快递，中通快递，韵达快递，顺丰快递，邮政EMS，天天快递，汇通快递，优速物流，邮政快递包裹，德邦快递，宅急送。
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        开具发票：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        承诺提供发票服务，当买家在下单时选择需要发票服务时，品牌入驻商必须提供发票。
                                    </dd>
                                    <dt class="ui-dlist-tit">
                                        48小时发货服务：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        在买家拍下商品并成功付款后，品牌入驻商需在买家付款成功后四十八（48）小时内完成发货（包含周六日）。
                                    </dd> 
                                    <dt class="ui-dlist-tit">
                                        7天无理由退货服务：
                                    </dt>
                                    <dd class="ui-dlist-det">
                                        买家在商品签收后的7天内，在保证商品及商品本身包装保持出售时原状且配件齐全，不影响二次销售，买家可以选择7天无理由退货服务进行退货。如有违反按照“淘竹马合作商家违规处罚管理规定”执行。
                                    </dd>
                                </dl>
                            </div>
                            <!-- 单个contact -->
                            <div class="box">
                                <h2>
                                    新建专场商品最低款数要求
                                </h2>
                                <table class="contact-table ui-table ui-table-noborder">
                                    <tbody>
                                    <tr class="ui-table-split">
                                    <th>前台类目</th>
                                    <th>专场款数最低门槛</th>
                                    </tr>
                                     <tr class="ui-table-split">
                                            <td>童装</td>
                                            <td>30款</td>
                                      </tr>
                                       <tr class="ui-table-split">
                                            <td>婴儿装</td>
                                            <td>25款</td>
                                      </tr>
                                      <tr class="ui-table-split">
                                            <td>亲子装</td>
                                            <td>20款</td>
                                      </tr>
                                       <tr class="ui-table-split">
                                            <td>童鞋</td>
                                            <td>30件</td>
                                      </tr>
                                      </tr>
                                     <tr class="ui-table-split">
                                            <td>配饰</td>
                                            <td>20款</td>
                                      </tr>
                                       <tr class="ui-table-split">
                                            <td>玩具</td>
                                            <td>10-20款</td>
                                      </tr>
                                      <tr class="ui-table-split">
                                            <td>婴儿用品</td>
                                            <td>10-20款</td>
                                      </tr>
                                       <tr class="ui-table-split">
                                            <td>孕妈服装</td>
                                            <td>30款</td>
                                      </tr>
                                      <tr class="ui-table-split">
                                            <td>孕妈用品</td>
                                            <td>20款</td>
                                      </tr>
                                       <tr class="ui-table-split">
                                            <td>孕妈护肤</td>
                                            <td>15款</td>
                                      </tr>
                                    </tbody>
                                </table>
                                &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; <span class="red">注：如果没有达到要求，将不能通过审核！</span>
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