<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>
	<body>
			<jsp:include page="sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
				<div class="pure-g admin-wrapper">
					<div class="pure-u-1 main">
                    	<style>
						body{background:#f9f9f9;}#content{box-shadow:none;background:transparent;}.summary{font-size:14px;}.summary span{font-size:14px;margin-right:30px;}.summary span em{font-weight:normal;color:#f10303;}.top-img{display:block;margin-bottom:10px;}.mb50{margin-bottom:50px;}.ac-ul{height:35px;line-height:35px;}.ac-ul span{height:35px;display:block;float:left;text-align:center;overflow:hidden;}.ac-ul .icon{width:80px;}.ac-ul .icon em{padding:0 5px;height:20px;line-height:35px;background:#FF4965;color:#fff;}.ac-ul .tit{width:200px;}.ac-ul .time{width:300px;}.ac-ul .btn{width:80px;}.more a{margin-left:530px;text-decoration:none;font-size:10px;color:#555;}.common-info .info-con span{width:20%;}
						</style>
						<div class="clearfix">
							<!-- 主体内容 start -->
							<div class="bo-left">								
								<!-- 商家信息 start-->
								<div class="seller-info l-box">
									<h1 class="title-1">
										欢迎您！
									</h1>
									<div class="info-con clearfix">
										<span class="score-con">
											商家综合评分
											<br/>
											<b class="num">
												0
											</b>
											<p class="score-tips">
												<em>
													品牌综合评分
													<strong>
														0
													</strong>
												</em>
												<em>
													发货速度评分
													<strong>
														0
													</strong>
												</em>
												<em class="dele-right-boder">
													物流速度评分
													<strong>
														0
													</strong>
												</em>
											</p>
										</span>
										<span>
											退款率
											<br/>
											<b class="num">
												0%
											</b>
										</span>
										<span class="dele-right-boder">
											扣分
											<br/>
											<!--<a href="#" target="_blank">-->
												<b class="num">
													${deductScoreAll }
												</b>
											<!-- </a> -->
											<p class="score-detail">
												<a href="goDeductionScoreWeb.do" target="_blank">
													查看扣分明细
												</a>
											</p>
											
										</span>
									</div>
								</div>
								<!-- 商家信息 end -->
								<!-- 常用提醒 start -->
								<!--
								<div class="common-info l-box">
									<h3 class="title-2">
										<b>
											常用提醒
										</b>
									</h3>
									<div class="info-con clearfix">
										<span>
											待发货
											<br/>
											<a href="trademng/ship.html" target="_blank">
												<b class="num">
													0
												</b>
											</a>
										</span>
										<span>
											补寄待处理
											<br/>
											<a href="trademng/refund.html"
											target="_blank">
												<b class="num">
													0
												</b>
											</a>
										</span>
										<span>
											退货待签收
											<br/>
											<a href="trademng/refund.html" target="_blank">
												<b class="num">
													0
												</b>
											</a>
										</span>
										<span>
											退款待处理
											<br/>
											<a href="trademng/refund.html" target="_blank">
												<b class="num">
													0
												</b>
											</a>
										</span>
										<span class="dele-right-boder">
											待阅读
											<br/>
											<a href="#" target="_blank">
												<b class="num">
													0
												</b>
											</a>
										</span>
									</div>
								</div>
								-->
								<!-- 常用提醒 end -->
								<!--
								<div class="l-box activity-info">
									<h3 class="title-2 more">
										<b>
											活动报名
										</b>
										<a href="#" target="_blank">
											查看更多
										</a>
									</h3>
									<div class="info-con">
										<div class="ac-ul clearfix">
											<span class="icon">
												<em>
													HOT
												</em>
											</span>
											<span class="tit">
												品牌特卖纸尿裤黑五活动报名
											</span>
											<span class="time">
												活动时间: 2015-11-26 至 2015-11-29
											</span>
											<span class="btn">
												<a href="#" target="_blank">
													立即报名
												</a>
											</span>
										</div>
										<div class="ac-ul clearfix">
											<span class="icon">
												<em>
													HOT
												</em>
											</span>
											<span class="tit">
												【成人服饰鞋包】品牌特卖黑五大促报名
											</span>
											<span class="time">
												活动时间: 2015-11-26 至 2015-11-29
											</span>
											<span class="btn">
												<a href="#" target="_blank">
													立即报名
												</a>
											</span>
										</div>
										<div class="ac-ul clearfix">
											<span class="icon">
												<em>
													HOT
												</em>
											</span>
											<span class="tit">
												【美妆个护】品牌特卖黑五大促报名
											</span>
											<span class="time">
												活动时间: 2015-11-26 至 2015-11-29
											</span>
											<span class="btn">
												<a href="#" target="_blank">
													立即报名
												</a>
											</span>
										</div>
									</div>
								</div>
								-->
								<!-- 特卖排期 start -->
								<div class="l-box activity-info">
									<h3 class="title-2">
										<b>
											专场排期
										</b>
										<span>
											请确保备货充足
										</span>
									</h3>
									<!--<div class="info-con">
										<div class="tac c-999 mt-15 mb-15">
											暂无排期
										</div>
									</div>-->
									
                            <div class="ui-table-container p20 martshow-index">
                            <table class="ui-table">
                                <thead>
                                    <tr>
                                        <th>
                                            专场ID
                                            
                                        </th>
                                       								
                                        <th>
                                            专场标题
                                        </th>
                                        <th width="120px">
                                            专场满减
                                        </th>

                                        <th>
                                            开始时间
                                        </th>
                                        <th>
                                            结束时间
                                        </th>
                                        <th>
                                            创建时间
                                        </th>
                                    </tr>
                                </thead>
                                <!--<tbody id="body"></tbody>-->
                                                           
                          
                               <c:forEach items="${specialShowList}" var="specialShowPojo">
                                <tr>
                                <td><label class="floatLeft" name="specialShowPojo.id">${specialShowPojo.id}<label></td>
                                <td><label class="floatLeft" name="specialShowPojo.title">${specialShowPojo.title}<label></td>
                                <td><label class="floatLeft" name="specialShowPojo.discountContext">${specialShowPojo.discountContext}<label></td>
                                <td><label class="floatLeft" name="specialShowPojo.beginTimeStr">${specialShowPojo.beginTimeStr}<label></td>
                                <td><label class="floatLeft" name="specialShowPojo.endTimeStr">${specialShowPojo.endTimeStr}<label></td>
                                <td><label class="floatLeft" name="specialShowPojo.createDateStr">${specialShowPojo.createDateStr}<label></td>
                                </tr>
							   </c:forEach>
							
                            
                               
                               </table>
                              </div>	
								</div>
								<!-- 特卖排期 end -->
								<!-- 单品特卖排期 start -->
								<%--
								<div class="l-box activity-info">
									<h3 class="title-2">
										<b>
											单品特卖排期
										</b>
										<span>
											请确保备货充足
										</span>
									</h3>
									<div class="info-con">
										<div class="tac c-999 mt-15 mb-15">
											暂无排期
										</div>
									</div>
								</div>
								<!-- 单品特卖排期 end -->
								<div class="mb50">
								</div>
								%>
							</div>
							<div class="right-con">
								<!-- 通知 start -->
								<div class="notice-con r-con">
									<h2 class="title-3">
										<i class="iconfont" title="公告" style="color: #FD5555;">
											&#xf00b5;
										</i>
										官方公告
									</h2>
									<%--
									<ul class="notice-list news-list">
										<li class="first">
											<span class="time">
												11.18
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="关于新增“二次延迟发货”处罚规则与“补寄”售后流程的通知">
													·
													<!--注意这里有个点-->
													关于新增“二次延迟发货”处罚...
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												10.23
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="2015年双十一活动规则及发货规则">
													·
													<!--注意这里有个点-->
													2015年双十一活动规则及发...
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												11.16
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="“包装”违规行为解析">
													·
													<!--注意这里有个点-->
													“包装”违规行为解析
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												10.23
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="海外购商家延迟发货/虚假发货处罚内容变更生效通知">
													·
													<!--注意这里有个点-->
													海外购商家延迟发货/虚假发货...
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												10.22
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="中老年女装的品牌淘汰通知">
													·
													<!--注意这里有个点-->
													中老年女装的品牌淘汰通知
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												10.20
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="【重要通知】买家取消订单流程变更">
													·
													<!--注意这里有个点-->
													【重要通知】买家取消订单流程...
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												10.21
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="平台C2C圈儿商家招募">
													·
													<!--注意这里有个点-->
													平台C2C圈儿商家招募
												</a>
											</p>
										</li>
										<li>
											<span class="time">
												10.21
											</span>
											<p>
												<a class="grey" target="_blank" href="#"
												title="常态销售和服务簿上线通知">
													·
													<!--注意这里有个点-->
													常态销售和服务簿上线通知
												</a>
											</p>
										</li>
									</ul>
									<div class="notice-bot">
										<a href="#" target="_blank">
											查看更多
										</a>
									</div>
									--%>
								</div>
								<!-- 通知 end -->
								<!-- 快捷帮助 start -->
								<div class="right-con">
								<div class="rule-con r-con">
									<h2 class="title-3">
										<b>
											商家须知
										</b>
									</h2>
									
									<ul class="rule-list news-list">
									<c:forEach items="${helpTypePojo.helpTypeChildPojoList }" var="h">
										<li>
											·
											<a href="goHelpWeb.do?helpPojo.id=${h.id }">
												${h.name }
											</a>
										</li>
									</c:forEach>
									</ul>
									
								</div>
								<!-- 快捷帮助 end -->
								<div>
									<!-- TODO 设置广告位 -->
									<div class="side-banner">
										<!--
										<a href="#" target="_blank">
											<img src="http://b2.hucdn.com//upload/hmp/1507/01/45594295332737_250x200.png" />
										</a>
										-->
									</div>
								</div>
							</div>
						</div>
					 </div>	
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="sellerFooter.jsp"></jsp:include>
	</body>
</html>
