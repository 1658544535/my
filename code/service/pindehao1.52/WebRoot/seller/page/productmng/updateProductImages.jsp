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
<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/_head.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    </head>
	<body>
<jsp:include page="../sellerHeader.jsp"></jsp:include>
<form class="ui-form product-add-form"  action="productImagesUpdateSellerWeb.do"  method="post" id="from1" enctype="multipart/form-data">	
		<input type="hidden" name="productImagesPojo.id" id="productImagesPojo.id" value="${productImagesPojo.id}"/> 
		<input type="hidden" name="productImagesPojo.productId" id="productImagesPojo.productId" value="${productImagesPojo.productId}"/>
		<input type="hidden" name="productImagesPojo.userId" id="productImagesPojo.userId" value="${productImagesPojo.userId}"/>
		<input type="hidden" name="productImagesPojo.images" id="productImagesPojo.images" value="${productImagesPojo.images}"/>
            <div id="content" class="wrapper" style="">
                <div class="pure-g admin-wrapper" style="">
                    <div class="pure-u-1 main" style="">
                        <h1 class="seller-title">
                            修改商品图片
                        </h1>
                        <div class="sp-body view-ProductDetailView" style="">
                            <h1 class="product-add-title">
                                商品图片详情
                            </h1>
                            <p class="product-add-note">&nbsp;
                                
                            </p>
                            <div class="product-add-block view-BasisInfoDetail">
                                <h4>
                                    商品图片信息
                                </h4>
                                <div class="product-add-main">
                                        <fieldset>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品图片:
                                                </label>
                                                <p class="ui-form-text">
                                                 <!--<img  class="floatLeft" src="/upfiles/product/${productImagesPojo.images}" height="100px" />
						                         <input type="file" class="floatLeft" name="upfile" class="floatLeft" id="userPic">-->
						                         
						                         <div class="uploadify" style="position:relative;height: 120px; width: 120px;border:1px solid #ddd;">
													<p class="uploadPreview_note" style="display:none;width:120px;height:120px;text-align:center;line-height:120px;">
														<i class="iconfont">&#xf00f7;</i>添加图片
													</p>
													<div class="uploadPreview_img" style="">
														<img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/${productImagesPojo.images}" style="width:120px;height:120px;" />
													</div>
													<input type="file" name="upfile" id="userPic" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;" />
												</div>
						                         
						                         <font color="#FF0000">图片建议尺寸：300*300</font>
                                                </p>
                                                <span class="ui-form-other" id="productTypeId_mgId"></span>
                                            </div>
                                            <!--<div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    alt属性:
                                                </label>
                                                <input class="ui-input" type="text" name="productImagesPojo.alts" value="${productImagesPojo.alts}" id="alts"> 
                                                <span id="alts_mgId"></span>
                                            </div>-->
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    排序:
                                                </label>
                                                <input class="ui-input" type="text" name="productImagesPojo.sorting" value="${productImagesPojo.sorting}" id="sorting"> 
                                                <span id="sorting_mgId"></span>
                                            </div>
                                        </fieldset>
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
                                    更新图片
                                </a>
                            </div> 
                            	<div class="m-feedback J_feedback">

		</div>
</form>
	
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
</body>
</html>

<script  type="text/javascript">	

function submit(){
	document.getElementById("from1").submit();
}
//图片更换
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
</script>