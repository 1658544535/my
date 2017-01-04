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
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>
	<body>
	<jsp:include page="../sellerHeader.jsp"></jsp:include>

			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .trade-entry,.trade-entry .pure-table{width:870px;}.spec-span{float:right;width:80px;text-align:center;}.seller_reissue_comment textarea{background:#fafafa none repeat scroll 0 0;border-color:#ccc;border-style:solid;border-width:2px 1px 1px 2px;color:#808080;margin-left:25px;outline:medium none;resize:none;width:300px;}.uploadify{position:relative;height: 120px; width: 120px;text-align:center;border:1px solid #ddd;}.uploadPreview_note{width:120px;height:120px;line-height:120px;}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;}
                        </style>
                        <h1 class="seller-title">
                            物流详情
                        </h1>
                        <c:choose>
						   <c:when test="${result.result==1}">
							   	<h1 class="seller-title">${result.reason}</h1>
						   </c:when>   
						   <c:otherwise>
						   		<div class="p20 seller-refund">
		                            <div class="refund-detail">
		                                <div class="detail-info">
		                                    <ul>
		                                        <li class="first">
		                                            <span class="title">
		                                                 快递单号：${result.expressNumber}
		                                            </span>
		                                            <span>
		                                               
		                                            </span>
		                                        </li>                               
		                                        <li>
		                                            <span class="title">
		                                            公司名称：		
		                                            <c:if test="${result.expressType=='lianhaowuliu'}">
		                                            	联昊通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='zhongtong'}">
		                                            	中通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='shentong'}">
		                                            	申通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='shunfeng'}">
		                                            	顺丰
		                                            </c:if>
		                                            <c:if test="${result.expressType=='yuantong'}">
		                                            	圆通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='huitong'}">
		                                            	汇通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='huitongkuaidi'}">
		                                            	百世汇通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='tiantian'}">
		                                            	天天
		                                            </c:if>
		                                            <c:if test="${result.expressType=='yunda'}">
		                                            	韵达
		                                            </c:if>
		                                            <c:if test="${result.expressType=='dhl'}">
		                                            	DHL
		                                            </c:if>
		                                            <c:if test="${result.expressType=='zhaijisong'}">
		                                            	宅急送
		                                            </c:if>
		                                            <c:if test="${result.expressType=='debangwuliu'}">
		                                            	德邦
		                                            </c:if>
		                                            <c:if test="${result.expressType=='ems'}">
		                                            	EMS国内
		                                            </c:if>
		                                            <c:if test="${result.expressType=='eyoubao'}">
		                                            	E邮宝
		                                            </c:if>
		                                            <c:if test="${result.expressType=='guotongkuaidi'}">
		                                            	国通
		                                            </c:if>
		                                            <c:if test="${result.expressType=='longbangwuliu'}">
		                                            	龙邦
		                                            </c:if>
		                                            <c:if test="${result.expressType=='lianbangkuaidi'}">
		                                            	联邦
		                                            </c:if>
		                                            <c:if test="${result.expressType=='tnt'}">
		                                            	TNT
		                                            </c:if>
		                                            <c:if test="${result.expressType=='xinbangwuliu'}">
		                                            	新邦
		                                            </c:if>
		                                            <c:if test="${result.expressType=='zhongtiewuliu'}">
		                                            	中铁
		                                            </c:if>
		                                            <c:if test="${result.expressType=='zhongyouwuliu'}">
		                                            	中邮
		                                            </c:if>
		                                            <c:if test="${result.expressType=='youshuwuliu'}">
		                                            	优速
		                                            </c:if>
		                                            <c:if test="${result.expressType=='kuaijiesudi'}">
		                                            	快捷
		                                            </c:if>
		                                            <c:if test="${result.expressType=='youzhengguonei'}">
		                                            	国内小包
		                                            </c:if>
		                                            <c:if test="${result.expressType=='shenghuiwuliu'}">
		                                            	盛辉
		                                            </c:if>
		                                                
		                                            </span>
		                                        </li>
		                                        <li class="last">
		                                            <span class="title">
		                                                当前状态：
		                                            <c:if test="${result.status=='0'}">
		                                            	物流单号暂无结果
		                                            </c:if>
		                                            <c:if test="${result.status=='3'}">
		                                            	在途
		                                            </c:if>
		                                            <c:if test="${result.status=='4'}">
		                                            	揽件
		                                            </c:if>
		                                            <c:if test="${result.status=='5'}">
		                                            	疑难
		                                            </c:if>
		                                            <c:if test="${result.status=='6'}">
		                                            	已签收
		                                            </c:if>
		                                            <c:if test="${result.status=='7'}">
		                                            	退签
		                                            </c:if>
		                                            <c:if test="${result.status=='8'}">
		                                            	派件
		                                            </c:if>
		                                            <c:if test="${result.status=='9'}">
		                                            	退回
		                                            </c:if>
		                                            <c:if test="${result.status==''}">
		                                            	${result.reason}
		                                            </c:if>
		                                            </span>
		                                            <span>
		                                            </span>
		                                        </li>
		                                    </ul>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="detail-item">
                                    <h1 class="seller-title">
			                            物流记录：
			                        </h1>
                                    <div class="item-info refund_log">
                                        <ul id="expressBody">
                                        	<li id="exinfo"></li>
                                        </ul>
                                    </div>
                                </div>
						   </c:otherwise>  
						</c:choose>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
        <script>
        //console.log('${result}');
        var resudata = eval("(" + '${result}' + ")");
        //console.log(resudata.data.length);
        var data = resudata.data;
    	for(var i=0; i<data.length; i++){
    				$("#exinfo").before(
    						"<li>"+
    	    	            "<i class='iconfont'>&#xf018e;</i>"+
    	    	            "<span class='effect-bar'>"+
    	    	            "</span>"+
    	    	            data[i].time+"&nbsp;&nbsp;&nbsp;"+data[i].context+
    	    	        "</li>"
    				);
    	}
		</script>
	</body>
</html>