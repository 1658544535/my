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
                            售后详情
                        </h1>
                        <div class="p20 seller-refund">
                            <div class="refund-detail">
                                <div class="detail-info">
                                    <ul>
                                        <li class="first">
                                            <span class="title">
                                                 申请时间：
                                            </span>
                                            <span>
                                                ${userOrderRefundPojo.creatDateString }
                                            </span>
                                        </li>                               
                                        <li>
                                            <span class="title">
                                                售后类型：
                                            </span>
                                            <span>
                                                <em>
                                                 ${userOrderRefundPojo.typeName }
                                                </em>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="title">
                                                退款金额：
                                            </span>
                                            <span>
                                                <em >
                                                    ￥${userOrderRefundPojo.refundSumPrice }
                                                </em>
                                            </span>
                                        </li>
                                          <li>
                                            <span class="title">
                                                退货物流：
                                            </span>
                                            <span>
                                                   ${userOrderRefundPojo.logisticsNameCN } &nbsp;&nbsp;&nbsp; ${userOrderRefundPojo.logistics }
                                            </span>
                                        </li>
                                        <li class="last">
                                            <span class="title">
                                                当前状态：
                                            </span>
                                            <span>
                                             <c:choose>
                                              <c:when test="${(userOrderRefundPojo.status==1 || userOrderRefundPojo.status==2 || userOrderRefundPojo.status==3) && ((userOrderRefundPojo.rejectImages !=null && userOrderRefundPojo.rejectImages !='') || (userOrderRefundPojo.rejectReason !=null && userOrderRefundPojo.rejectReason !=''))}">
                                             退单仲裁中
                                             </c:when>
                                             <c:otherwise>
                                              ${userOrderRefundPojo.statusName }
                                             </c:otherwise>
                                             </c:choose>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="detail-list">
                                    <h5>
                                        商品清单
                                    </h5>
                                    <br>
                                    <div class="trade-entry">
                                        <div class="order-entry">
                                            <div class="order-head">
                                                <span>
                                                    订单编号：${orderDetailPojo.orderNo }
                                                </span>
                                                <span class="spec-span">
                                                    订单状态
                                                </span>
                                                <span class="spec-span">
                                                    售后金额
                                                </span>
                                                <span class="spec-span">
                                                    总金额
                                                </span>
                                                <span class="spec-span">
                                                    数量
                                                </span>
                                                <span class="spec-span">
                                                    单价
                                                </span>
                                            </div>
                                            <table class="pure-table">
                                                <tbody>
                                                    <tr class="order-item">
                                                        <td class="ol-b-item-info" style="width: 350px">
                                                            <a class="image" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/${orderDetailPojo.productImages}"
                                                            target="_blank">
                                                                <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/${orderDetailPojo.productImages}">
                                                            </a>
                                                            <a class="title c-666" target="_blank" href="getMyOrderDetailWeb.do?orderPojo.id=${orderDetailPojo.orderId}">
                                                                ${orderDetailPojo.productName}
                                                            </a>
                                                            <span class="sku">
                                                                商品编码：${orderDetailPojo.businessCode}
                                                                <b class="c-666">
                                                                </b>
                                                                （SKU:${orderDetailPojo.productSku}）
                                                            </span>
                                                        </td>
                                                        <td class="ol-b-item-price" style="border-left: 1px solid #efefef;">
                                                            <p>
                                                                ￥${userOrderRefundPojo.stockPrice}
                                                            </p>
                                                            <p class="strike">
                                                                ￥${userOrderRefundPojo.stockPriceOld}
                                                            </p>
                                                        </td>
                                                        <td class="ol-b-number" style="border-left: 1px solid #efefef;" title="数量">
                                                            x ${userOrderRefundPojo.refundNum}
                                                        </td>
                                                        <td class="ol-b-number">
                                                            <span class="price" title="总金额">
                                                                ￥${userOrderRefundPojo.sumPrice}
                                                                <p class='ol-b-number c-999'>(优惠金额:<br/>￥-${userOrderRefundPojo.couponPrice })</p>
                                                            </span>
                                                        </td>
                                                         <td class="ol-b-subtotal">
                                                            <span class="price" title="退款金额">
                                                                ￥${userOrderRefundPojo.refundSumPrice}
                                                            </span>
                                                        </td>
                                                        <td class="ol-b-ops c-999">
                                                            <p>
                                                                ${orderDetailPojo.orderStatusName}
                                                            </p>
                                                            <p>
                                                               ${orderDetailPojo. serialNumber}
                                                            </p>
<!--                                                             <p>
                                                                <a href="javascript:;">
                                                                    762895724714
                                                                </a>
                                                            </p> -->
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="detail-item">
                                    <h5>
                                        售后原因：
                                    </h5>
                                    <div class="item-info">
                                       ${userOrderRefundPojo.refundTypeName } 
                                    </div>
                                </div>
                                <div class="detail-item">
                                    <h5>
                                        售后详情：
                                    </h5>
                                    <div class="item-info">
                                        ${userOrderRefundPojo.refundReason } 
                                    </div>
                                </div>
                                <div class="detail-item">
                                    <h5>
                                        买家凭证：
                                    </h5>
                                    <c:if test="${userOrderRefundPojo.images !=null && userOrderRefundPojo.images !=''}">
                                    <div class="item-info item-img">
                                        <div>
                                            <a href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images }" target="_blank">
                                                <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images }" >
                                            </a>
                                        </div>                                    
                                    </div>
                                    </c:if>
                                </div>
                            </div>
                          <!-- 上传图片凭证 -->
                          <a name="reject"></a>
                          <form action="submitRejectWeb.do" method="post" id="sysform" enctype="multipart/form-data" onkeydown="if(event.keyCode==13)return false;">
                            <div class="upload-certificate" id="upload-certificate" style="display: block">
                          <input type="hidden" name="userOrderRefundPojo.id" value="${userOrderRefundPojo.id }" />
                          <input type="hidden" name="userOrderRefundPojo.detailId" value=" ${userOrderRefundPojo.detailId }" />
                                <h5>
                                    如对买家申请有异议，请提供此商品的发货凭证！
                                </h5>
                                <div>
                                    <div class="view-UploadImgInput upload-img-input">
                                        <div class="uploadify">
                                            <p class="uploadPreview_note">
                                                <i class="iconfont">&#xf00f7;</i>
                                                上传凭证
                                            </p>
                                            <div class="uploadPreview_img"  style="position:absolute;left:0;top:0;">
                                                <img  src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.rejectImages }"  style="width:120px;height:120px;">
                                            </div>
                                            <input type="file" name="upfile" data-imgSrc="${userOrderRefundPojo.rejectImages }" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" >
                                        </div>
                                        <span class="upload-info">
                                            请提供商品的发货凭证，最多可上传1张，支持jpg、jpeg、png格式。
                                        </span>
                                    </div>
                                </div>
                                <div class="uploaded-certificate view-UploadedCertificate">
                                </div>
                                <div class="seller-comment">
                                    详细说明：
                                    <textarea class="view-SellerComment" name="userOrderRefundPojo.rejectReason" cols="100" rows="5" placeholder="您可以用文字描述凭证">${userOrderRefundPojo.rejectReason}</textarea>
                                </div>
                            </div>
                            <c:if test="${userOrderRefundPojo.status==1 || userOrderRefundPojo.status==2 || userOrderRefundPojo.status==3}">
                            <div class="btn-area" style="padding-top: 0">
                                <c:if test="${(userOrderRefundPojo.rejectImages ==null || userOrderRefundPojo.rejectImages =='') || (userOrderRefundPojo.rejectReason ==null || userOrderRefundPojo.rejectReason =='')}">
                                  <a onclick="reject()" class="view-BtnRefundConfirm ui-button submit-cert">
                                    申请仲裁
                                 </a>
                                </c:if>
                                <c:if test="${userOrderRefundPojo.type ==1}">                              
                                <a href='checkOrderRefundWeb.do?userOrderRefundPojo.id=${userOrderRefundPojo.id}&userOrderRefundPojo.orderId=${userOrderRefundPojo.orderId}&userOrderRefundPojo.detailId=${userOrderRefundPojo.detailId}&is=7' class="view-BtnApplyArbitration ui-button agree-to-return">
                                    同意退款(无需上传凭证)
                                </a>
                                </c:if>
                                <c:if test="${userOrderRefundPojo.type ==2 || userOrderRefundPojo.type ==3}">
                                <a href='checkOrderRefundWeb.do?userOrderRefundPojo.id=${userOrderRefundPojo.id}&userOrderRefundPojo.orderId=${userOrderRefundPojo.orderId}&userOrderRefundPojo.detailId=${userOrderRefundPojo.detailId}&is=4' class="view-BtnApplyArbitration ui-button agree-to-return">
                                    确认收货(无需上传凭证)
                                </a>
                                </c:if>
                            </div>
                            </c:if>
                          </form>
                        </div>                       
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
        <script>
		$(function(){
			$(document).delegate(".uploadPreview_imgfile","change",function(){
				var _this = $(this);
				var url = _this.val();
				if (window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(_this.get(0).files[0]);
				} else if (window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(_this.get(0).files[0]);
				} else if (window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
				}
				_this.siblings(".uploadPreview_img").find("img").attr("src", url);
				_this.siblings(".uploadPreview_img").show();
				_this.siblings(".uploadPreview_note").hide()
			});
		});
		
		function reject(){
			var rejectReason = $("textarea[name='userOrderRefundPojo.rejectReason']").val();
			var upfile = $("input[name='upfile']").val(),
				imgSrc =  $("input[name='upfile']").attr("data-imgSrc");
			if(rejectReason==""){
				alert("仲裁原因不能为空");
				return false;
			}
			if(upfile=="" && imgSrc==""){
				alert("凭证图片不能为空");
				return false;
			}
			document.getElementById("sysform").submit();
		}
		</script>
	</body>
</html>