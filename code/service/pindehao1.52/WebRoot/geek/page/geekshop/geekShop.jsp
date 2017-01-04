<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/default.css" media="all" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
    <style>
    	.ui-tipbox-content{margin:14px 0 20px 87px!important;}
    	.ui-tipbox-content h3.ui-tipbox-title{margin-top:22px;}
    </style>
</head>

<body>
    <jsp:include page="../geekHeader.jsp"></jsp:include>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}.apply-box select{width:190px;}.store-tab{border-top:1px solid #f0f0f0;}
                    </style>
                    <h1 class="seller-title">
                        店铺管理
                        <a class="ui-button ui-button-morange fr" href="goUpdateGeekShopWeb.do">修改</a>
                    </h1>
                    <form action="#" accept-charset="utf-8" class="ui-form p50" method="">
                    
                   
                    
                    
           <s:if test = "userMakerShopPojo.status == 0" >
           		 <div class="ui-tipbox ui-tipbox-question">
                    <div class="ui-tipbox-icon"><i class="iconfont">&#xf0142;</i></div>
                    <div class="ui-tipbox-content">
                        <h3 class="ui-tipbox-title">您的店铺信息正在审核中~</h3>
                    </div>
                </div>
            </s:if>  
            	<s:elseif test="userMakerShopPojo.status == 1">    
                    <div class="ui-tipbox ui-tipbox-success">
	                    <div class="ui-tipbox-icon"><i class="iconfont">&#xf0142;</i></div>
	                    <div class="ui-tipbox-content">
	                        <h3 class="ui-tipbox-title">恭喜您！审核已经通过！</h3>
	                    </div>
	                </div>
               </s:elseif>  
               <s:elseif test="userMakerShopPojo.status == 2">
               	<div class="ui-tipbox ui-tipbox-error">
	                <div class="ui-tipbox-icon"><i class="iconfont">&#xf0142;</i></div>
	                <div class="ui-tipbox-content">
	                    <h3 class="ui-tipbox-title">很遗憾，您的店铺审核没有通过，请您修改为正确的信息后再次提交审核。</h3>
	                </div>
	            </div>        
               </s:elseif>           
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    店铺名称
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.shopName}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    店铺LOGO
                                </label>
                                        <div>
                                            <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${userMakerShopPojo.shopLOGO}" style="width:120px;height:120px;">
                                        </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    店铺主图
                                </label>
                                <div>
                                    <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${userMakerShopPojo.shopMainImage}" style="max-width:400px;">
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    适用年龄
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.ageTypeName}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    开发能力
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.abilityName}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    联系人
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.contact}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    地址
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.address}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    联系电话
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.phone}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    客服电话
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.servicePhone}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    支付宝帐号
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.alipayAccount}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    支付宝实名
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.alipayName}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    单平台内容产出量
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.contentOutputName}</p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    单平台原创内容产出量
                                </label>
                                <p class="ui-form-text">${userMakerShopPojo.contentOutputOriginalName}</p>
                            </div>
                        </div>
                        <div class="store-tab">
                        <s:if test = "userMakerShopPojo.shopType == 0">
                            <!-- 企业认证 -->
                            <div id="store_company" class="store-tab-item">
                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            企业全称
                                        </label>
                                        <p class="ui-form-text">${userMakerShopPojo.shopTypeName}</p>
                                    </div>
                                </div>

                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            营业执照正/副本
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <div>
                                            <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageOriginal}" style="max-width:400px;">
                                        </div>
                                    </div>
                                </div>

                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            法人身份证正反面图
                                            <p class="ui-form-explain"></p>
                                        </label>
                                        <div>
                                            <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageCopy}" style="max-width:400px;">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 企业认证 end -->
                        </s:if> 
                        <s:elseif test = "userMakerShopPojo.shopType == 1">   
                        <div id="store_company" class="store-tab-item">
                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            店铺平台名称
                                        </label>
                                        <p class="ui-form-text">${userMakerShopPojo.shopTypeName}</p>
                                    </div>
                                </div>

                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                           店铺地址URL
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <p class="ui-form-text">${userMakerShopPojo.shopTypeUrl}</p>
                                    </div>
                                </div>

                                <div class="apply-box">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            店主手持身份证照片
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <div>
                                            <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageOriginal}" style="max-width:400px;">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
						</s:elseif>
                        <div class="apply-box boxs-botom">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
    
<jsp:include page="../geekFooter.jsp"></jsp:include>

    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/js/base.js" type="text/javascript"></script>
</body>

</html>
