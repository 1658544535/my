<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/_head.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
        <link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
        <script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    </head>
	<body>
<jsp:include page="../sellerHeader.jsp"></jsp:include>
<form class="ui-form product-add-form"  action="addOrderShipSeller.do"  method="post" id="from1" enctype="multipart/form-data">	
    <input name="orderShipPojo.orderId" value="${orderPojo.id}" class="inputText" type="hidden" />
    <input name="orderShipPojo.userId" value="${orderPojo.userId}" class="inputText" type="hidden" />
    <input name="orderShipPojo.orderStatus" value="2" class="inputText" type="hidden" />
    <input name="orderShipPojo.status" value="1" class="inputText" type="hidden" />						
            <div id="content" class="wrapper" style="">
                <div class="pure-g admin-wrapper" style="">
                    <div class="pure-u-1 main" style="">
                        <h1 class="seller-title">
                            添加发货信息
                        </h1>
                        <div class="sp-body view-ProductDetailView" style="">
                            <h1 class="product-add-title">
                                发货详情
                            </h1>
                            <p class="product-add-note">&nbsp;
                                
                            </p>
                            <div class="product-add-block view-BasisInfoDetail">
                                <h4>
                                    发货信息</br>
                                </h4>
                                <div class="product-add-main">
                                        <fieldset>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    物流名称:
                                                </label>
                                                <p class="ui-form-text">
                                                <select name="orderShipPojo.logisticsName" class="floatLeft">
        	                                         <c:forEach items="${sysDicPojoList}" var="sysDicPojoList">
				                                     <option value="${sysDicPojoList.nameEn}">${sysDicPojoList.name}</option>
			                                         </c:forEach>   
	                                           </select>
                                                </p>
                                                </br><span class="ui-form-other" id="logistics_name"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    物流单号:
                                                </label>
                                                <input class="floatLeft" type="text" name="orderShipPojo.logisticsNo" value="${productImagesPojo.alts}" id="ticketName"/> <br>
                                                 <span id="logistics_no"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    订单号:
                                                </label>
                                                <input type="text" name="orderShipPojo.orderNo"  value="${orderPojo.orderNo}" class="floatLeft" id="ticketName"/></br><span id="order_no"></span>
                                            </div> 
                                        </fieldset>
                                        <fieldset>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    发货人:
                                                </label>
                                                <input type="text" name="orderShipPojo.consignor"  value="" class="floatLeft" id="ticketName"/></br><span id="consignor"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    发货人电话:
                                                </label>
                                                 <input type="text" name="orderShipPojo.shipPhone"  value="" class="floatLeft" id="ticketName"/></br><span id="ship_phone"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    发货地址:
                                                </label>
                                                <input type="text" name="orderShipPojo.consignorAddress"  value="" class="floatLeft" id="ticketName" style="width:450px;"/></br><span id="consignor_address"></span>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                   收货人:
                                                </label>
                                               <input type="text" name="orderShipPojo.consignee"  value="${orderPojo.consignee}" class="floatLeft" id="ticketName"/></br><span id="consignee"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    收货人电话:
                                                </label>
                                               <input type="text" name="orderShipPojo.consigneePhone" value="${orderPojo.consigneePhone}" class="floatLeft" id="ticketName"/></br><span id="consignee_phone"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                  收货地址:
                                                </label>
                                                 <input type="text" name="orderShipPojo.consigneeAddress" value="${orderPojo.consigneeAddress}" class="floatLeft" id="ticketName" style="width:450px;"/></br><span id="consignee_address"></span>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                        *
                                                    </span>
                                                  收货方式:
                                                </label>
                                                <p class="ui-form-text">
                                                <select name="orderShipPojo.consigneeType" id="ticketType"  class="floatLeft">
        				                  	    <c:forEach items="${consigneeType}" var="consigneeType">
										        <option value="${consigneeType.value}">${consigneeType.name}</option>
								                </c:forEach>
				                                </select>
                                                </p>
                                                </br><span  id="consignee"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    买家留言:
                                                </label>
                                                <textarea class="" rows="5" cols="10" name="orderShipPojo.buyerMessage" id="content"style="width:450px;border: 1px solid #ddd;box-shadow:none;" >${orderPojo.buyerMessage}</textarea>
                                                <span id="buyer_message"></span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                   客服留言:
                                                </label>
                                                <textarea class="" rows="5" cols="10" name="orderShipPojo.remarks" id="content" style="width:450px;border: 1px solid #ddd;box-shadow:none;">${orderPojo.remarks}</textarea>
			                                    <span id="remarks"></span>
                                            </div>
                                        </fieldset>
                                        </div>
                                      </div>
                                   </div>
                                </div>  
                            </div>
                        </div> 
                      </div>        
		                    <div class="product-add-footbtn">
                                <a onclick="window.history.back()" class="ui-button ui-button-lwhite product-add-bigbtn product-add-shortbtn">
                                    返回
                                </a>
                                <a onclick="submit()"  class="ui-button ui-button-lgreen product-add-bigbtn view-CreateBtn">
                                    发货
                                </a>
                            </div> 
                            	<div class="m-feedback J_feedback">

		</div>
</form>
	
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
</body>
</html>

<script>
var logistics_name =new tt.Field(" 物流名称 ","orderShipPojo.logisticsName").setMsgId("logistics_name");
var logistics_no =new tt.Field(" 物流单号 ","orderShipPojo.logisticsNo").setMsgId("logistics_no");
var consignor =new tt.Field(" 发货人","orderShipPojo.consignor").setMsgId("consignor");
var consignor_address =new tt.Field(" 发货地址 ","orderShipPojo.consignorAddress").setMsgId("consignor_address");
var ship_phone =new tt.Field(" 发货电话","orderShipPojo.shipPhone").setMsgId("ship_phone");
var consignee =new tt.Field(" 收货人","orderShipPojo.consignee").setMsgId("consignee");
var consignee_address =new tt.Field(" 收货地址","orderShipPojo.consigneeAddress").setMsgId("consignee_address");
var consignee_phone =new tt.Field(" 收货人电话","orderShipPojo.consigneePhone").setMsgId("consignee_phone");
var order_no =new tt.Field(" 订单号","orderShipPojo.orderNo").setMsgId("order_no");
tt.Conf.reqStarCls = ""; 
tt.vf.req.add(logistics_name,logistics_no,consignor,consignor_address,ship_phone,consignee,consignee_address,consignee_phone,order_no);
tt.vf.num.add(ship_phone);
new tt.LV().set(0, 30).add(logistics_no);
//new tt.LV().set(0, 25).add(logistics_name);
function submit(){
	if(tt.validate()){
		document.getElementById("from1").submit();
	}
				}
</script>