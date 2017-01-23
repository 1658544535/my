<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baidu.ueditor.ActionEnter"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家中心</title>
<jsp:include page="../common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/tzmeditor/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/tzmeditor/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-form.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/tzmeditor/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
</head>

<script>
$(function(){
	if("${platformSpecialPojo.banner}" == ''){
		style="display: none;"  
		//document.getElementById("imgs").style.display="none";//隐藏  
			  
		//document.getElementById("uploadPreview_note").style.display="";//显示 
	}
	//select21("${platformSpecialPojo.ageType}");
	//selectThree1("${platformSpecialPojo.skillType}");
	//selectFour1("${platformSpecialPojo.secSkillType}");
});
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
	//document.getElementById("uploadPreview_note").style.display="none";//隐藏 
	//document.getElementById("imgs").style.display="";//显示
	_this.siblings(".uploadPreview_img").show();
	_this.siblings(".uploadPreview_note").hide()
});
</script>
<body>
<jsp:include page="../sellerHeader.jsp"></jsp:include>
	<div class="wrap">
		<div class="container" style="width:100%">
			<ul class="nav nav-tabs row" role="tablist">
			    <li role="presentation" class="col-sm-6 text-center active" id="infoLi"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">1.新增商品</a></li>
			    <li role="presentation" class="col-sm-6 text-center " id="albumLi"><a href="#album" aria-controls="album" role="tab" data-toggle="tab">2.编辑产品详情</a></li>
			</ul>
			<div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="info">
					<div>
		<form  class="ui-form product-add-form" action="productAddSellerWeb.do" method="post" id="from1" enctype="multipart/form-data" onkeydown="if(event.keyCode==13)return false;">		
            <div id="content" class="wrapper" style="">
                <div class="pure-g admin-wrapper" style="">
                    <div class="pure-u-1 main" style="">
                        <h1 class="seller-title">
                            新增商品
                        </h1>
                        <div class="sp-body view-ProductDetailView" style="">
                            <h1 class="product-add-title">
                                填写商品详情
                            </h1>
                            <p class="product-add-note">&nbsp;

                            </p>
                            <div class="product-add-block view-BasisInfoDetail">
                                <h4>
                                    商品信息
                                </h4>
                                <div class="product-add-main">
                                        <fieldset>
                                        	<div class="ui-form-item">
                                                <div class="product-age-notice">
													商品基本信息
												</div>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品分类:
                                                </label>
                                                <p class="ui-form-text">
                                                     <select name="productPojo.productType1" id="" class="ui-input" style="width:140px;"  onChange="changeProType()">
                                                      <option value="">----- 一级类目 -----</option>
									                  <s:iterator value="productType1List">
										              <option value="<s:property value="id"/>" <s:if test="id == productPojo.productType1">selected="selected"</s:if>><s:property value="name"/></option>
									                  </s:iterator>
                                                    </select>
                                                    <select name="productPojo.productTypeIds" id="productTypeIds" class="ui-input" style="width:140px;"  onChange="changePidType()">
                                                    <option value="">----- 二级类目 -----</option>${typeIdsStr}
                                                    </select>
                                                    <select name="productPojo.productTypeId" id="productTypeId" class="ui-input" style="width:140px;" >
                                                    <option value="">----- 三级类目 -----</option>${typeIdStr}
                                                    </select>
                                                </p>
                                                <p><span id="productType1_mgId"></span><span id="productTypeIds_mgId"></span><span id="productTypeId_mgId"></span></p>
                                            </div>
                                            <%-- <div class="ui-form-item hide">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    参考标题:
                                                </label>
                                                <p class="ui-form-text" name="referenceTitle">
                                                </p>
                                            </div> --%>
                                            <div class="ui-form-item">
                                                <div class="product-age-notice">
													商品规格
												</div>
                                            </div>
                                            <div class="ui-form-item">
                                                <div class="product-age-notice">
													商品详情信息
												</div>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品标题:
                                                </label>
                                                <input class="ui-input big" type="text" name="productPojo.productName" id="productName" value=''/>
                                                <!-- <div class="product-age-notice">
													品牌+特点+名称+规格(为保证用户体验，请保持标题简洁明了，堆砌关键字将被降权)
                                                </div> -->
                                                <span id="productName_mgId"></span>
                                            </div>
                                            <div class="ui-form-item view-InputItem" prop-id="2" prop-name="货号">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品货号:
                                                </label>
                                                <input class="ui-input" type="text" name="productPojo.productNum" id="productNum" value=''/></br>
                                                <span id="productNum_mgId"></span>
                                            </div>
                                            <%-- <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品卖点:
                                                </label>
                                                <input class="ui-input big"  type="text"  name="productPojo.productSketch"  id="productSketch"  value=''/>
                                                 <span class="ui-form-other"> --%>
<!--                                                     <a href="javascript:;" class="view-WhereToShow">
                                                        显示在哪？
                                                    </a> -->
                                                    <%-- <span class="hint">
                                                    </span>
                                                </span> 
                                                <span id="productSketch_mgId"></span>
                                            </div>
                                        </fieldset>
                                        <fieldset class="view-KeyPropDetail"> --%>
                                            
                                            <%-- <div class="ui-form-item view-InputItem" prop-id="1" prop-name="品牌">
                                                <label for="" class="ui-label">
                                                 <span class="ui-form-required">
                                                   *
                                                </span>
                                                    品牌:
                                                </label>
                                                    <select name="productPojo.userBrandId" id="" class="ui-input">
                                                      <option value="">----全部----</option>
									                  <s:iterator value="brandList">
										              <option value="<s:property value="id"/>" <s:if test="id == productPojo.userbrandId">selected="selected"</s:if>><s:property value="brandName"/></option>
									                  </s:iterator>
                                                    </select>
                                                <div class="product-age-notice">
                                                    <span id="userBrandId_mgId"></span>
													商品发布后品牌不可修改，请正确选择
                                                </div>
                                            </div> --%>
                                        </fieldset>
                                        <fieldset class="view-OtherPropDetail">
                                        	<div class="ui-form-item view-InputItem" prop-id="76" prop-name="原价">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    供货价(包邮):
                                                </label>
                                                <input class="ui-input" type="text" name="productPojo.proxyPrice" id="proxyPrice"/></br>
                                                <span id="proxyPrice_mgId"></span>
                                            </div>
                                            <div class="ui-form-item view-InputItem" prop-id="76" prop-name="原价">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    建议零售价:
                                                </label>
                                                <input class="ui-input" type="text" name="productPojo.sellingPrice" id="sellingPrice"/></br>
                                                <span id="sellingPrice_mgId"></span>
                                            </div>
                                            <div class="ui-form-item view-InputItem" prop-id="75" prop-name="实价">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    市场价:
                                                </label>
                                                <input class="ui-input" type="text" name="productPojo.distributionPrice" id="distributionPrice"/></br>
                                                <span	id="distributionPrice_mgId"></span>
                                            </div>
                                            <%-- <div class="ui-form-item view-InputItem" prop-id="75" prop-name="折扣">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        
                                                    </span>
                                                    折扣:
                                                </label>
                                                <span id="discount"></span>
                                            </div>
                                             <div class="ui-form-item view-InputItem" prop-id="76" prop-name="单位">
                                                <label for="" class="ui-label">
                                                    单位:
                                                </label>
                                                <select name="productPojo.unit" id="ticketType"  class="ui-input">
										            <c:forEach items="${unit}" var="unit">
													     <option value="${unit.value}" >${unit.name}</option>
											        </c:forEach>
									        </select>
                                            </div>
                                            <div class="ui-form-item view-InputItem" prop-id="76" prop-name="重量(kg)">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    重量(kg):
                                                </label>
                                            <input class="ui-input" type="text"  name="productPojo.weight" id="weight"/></br> 
                                            <span	id="weight_mgId"></span>
                                            </div>
                                            <div class="ui-form-item view-InputItem" prop-id="74" prop-name="适用年龄">
                                                <label for="" class="ui-label">
                                                    适用年龄:
                                                </label>
                                                <select name="productPojo.age" id="ticketType"  class="ui-input">
										          <c:forEach items="${age}" var="age">
													<option value="${age.value}" >${age.name}</option>
											     </c:forEach>
									           </select>
                                            </div>                                            
                                            <div class="ui-form-item view-InputItem" prop-id="73" prop-name="产地">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    产地:
                                                </label>
                                                <input class="ui-input"  type="text"  name="productPojo.location"  id="location"  value=''/></br>
                                                <span id="location_mgId"></span>
                                            </div> --%>
                                            <input class="ui-input big" type="hidden" name="productPojo.postageType" id="postageType"  value="1" />  
                                            <input class="ui-input big" type="hidden" name="productPojo.texture" id="texture"  value="0" />        
                                            <input class="ui-input big" type="hidden" name="productPojo.pack" id="pack"  value="0" />        
                                            <input class="ui-input big" type="hidden" name="productPojo.isPower" id="isPower"  value="0" />        
                                            <input class="ui-input big" type="hidden" name="productPojo.isIntroduce" id="isIntroduce"  value="0" />        
                                            <input class="ui-input big" type="hidden" name="productPojo.isHotsale" id="isHotsale"  value="0" /> 
                                            <%--
                                            <div class="ui-form-item view-InputItem" prop-id="74" prop-name="材质">
                                                <label for="" class="ui-label">
                                                    材质:
                                                </label>
                                                <select name="productPojo.texture" id="ticketType"  class="ui-input">
										        <c:forEach items="${texture}" var="texture">
													<option value="${texture.value}" >${texture.name}</option>
											    </c:forEach>
									            </select>
                                            </div>
											<div class="ui-form-item view-InputItem" prop-id="76" prop-name="是否包邮">
                                                <label for="" class="ui-label">
                                                    是否包邮:
                                                </label>
                                                <select name="productPojo.postageType" id="ticketType"  class="ui-input">
										         <c:forEach items="${isPower}" var="postageType">
													<option value="${postageType.value}">${postageType.name}</option>
											    </c:forEach>
									           </select>
                                            </div> 
                                            <div class="ui-form-item view-InputItem" prop-id="76" prop-name="包装方式">
                                                <label for="" class="ui-label">
                                                    包装方式:
                                                </label>
                                               <select name="productPojo.pack" id="ticketType"  class="ui-input">
										       <c:forEach items="${pack}" var="pack">
													<option value="${pack.value}" >${pack.name}</option>
											   </c:forEach>
									         </select>
                                            </div>                                           
                                           <div class="ui-form-item view-InputItem" prop-id="76" prop-name="是否电动">
                                                <label for="" class="ui-label">
                                                    是否电动:
                                                </label>
                                                <select name="productPojo.isPower" id="ticketType"  class="ui-input">
										          <c:forEach items="${isPower}" var="isPower">
													<option value="${isPower.value}" >${isPower.name}</option>
											    </c:forEach>
									          </select>
                                            </div>
                                            <div class="ui-form-item view-InputItem" prop-id="76" prop-name="是否推荐">
                                                <label for="" class="ui-label">
                                                    是否推荐:
                                                </label>
                                               <select name="productPojo.isIntroduce" id="ticketType"  class="ui-input">
										          <c:forEach items="${isIntroduce}" var="isIntroduce">
													<option value="${isIntroduce.value}" >${isIntroduce.name}</option>
											     </c:forEach>
									          </select>
                                            </div>   
                                            <div class="ui-form-item view-InputItem" prop-id="76" prop-name="掌柜热卖">
                                                <label for="" class="ui-label">
                                                    掌柜热卖:
                                                </label>
                                                <select name="productPojo.isHotsale" id="ticketType"  class="ui-input">
										          <c:forEach items="${isHotsale}" var="isHotsale">
													<option value="${isHotsale.value}" >${isHotsale.name}</option>
											     </c:forEach>
									           </select>
                                            </div>   --%>
                                            <div class="ui-form-item view-InputItem" prop-id="76" prop-name="商品主图">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品主图：<br>750*350px
                                                </label>
                                        <div class="uploadify main_img"style="position:relative;height: 240px; width: 480px;">
                                        <button class="uploadPreview_note"style="width:480px;height:240px;line-height:240px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:480px;height:240px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div> 
                                  </div>
                                  <div class="ui-form-item view-InputItem" prop-id="76" prop-name="商品主图">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    高清缩略图：<br>400*400px
                                                </label>
                                        <div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile2" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div> 
                                  </div>
                                  <div class="ui-form-item view-InputItem" prop-id="76" prop-name="商品主图">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品缩略图：<br>200*200px
                                                </label>
                                        <div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile3" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div> 
                                  </div>
                                           <div class="ui-form-item view-InputItem" prop-id="76" prop-name="商品主图">
                                                <label for="" class="ui-label">
                                                    <span class="ui-form-required">
                                                        *
                                                    </span>
                                                    商品轮播图：<br>640*400px

                                                </label>
                                        <div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfiles" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div> 
                                       <!-- <div class="product-age-notice">
											<font color="#df434e">展示在商品列表页（规格800 * 800）</font>
	                                    </div>  -->
                                       <div id="imageBox"></div>
                                       <br /><i class="iconfont" onclick="addImage()">󰅵</i>
                                  </div>                        
                            </div>
                            <div class="product-add-block" id="skuInfo">
                                <h4>
                                    <span class="ui-form-required">
                                        *
                                    </span>
                                    SKU信息
                                </h4>
                                <div class="product-add-main">
                                    <div class="product-sku-helper">          
                                        <a  class="ui-button ui-button-lgreen "  onclick="addPrice()" >添加SKU</a>&nbsp;&nbsp;
                                        <a href="javascript:;" class="view-ShowItemImgExampleBoxBtn" style="font-size:12px"title="请先勾选需要填充的sku"> 批量填充sku</a>
	                                    <a class="ui-button ui-button-morange fr"  onclick="delPrice()" >删除所选SKU</a> 
                    
                                    </div>
                                    <div class="view-SkuInfoDetail">
                                        <table class="ui-table product-add-sku" id="priceBody">
                                            <thead>
                                                <tr>
                                                    <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"/></th>
                                                    <th>颜色</th>
                                                    <th>规格</th>
                                                    <th>库存</th>
                                                    <th>价格</th> 
                                                    <th>商家编码</th>                                                  
                                                </tr>
                                            </thead>
                                            <tbody> </tbody>
                                        </table>
                                        <p align="right"><font color="#df434e" size="1">（图片展示在商品详情页）</font></p>
                                    </div>
                                </div>
                            </div>
                            <%--
                            <div class="product-add-block view-ProductDetail">
                                <h4>
                                    商品描述
                                    <p>
                                    </p>
                                </h4>
                                <div class="product-add-editor">
                                    <p class="product-add-editor-note">
                                        童装童鞋等须有尺码表；禁止出现淘宝等其他平台的网址、水印等信息；禁止在该商品详情中展示其他商品图片；
                                        <a href="javascript:;" class="view-ShowItemDetailExampleBoxBtn" style="font-size:12px">
                                            查看模板
                                        </a>
                                    </p>
                                    <!--编辑器-->
                                    <textarea id="editor" name="productPojo.content"   style="width:708px;height:400px;">  </textarea>
                                </div>
                            </div>
                            --%>
                            <!-- <div class="product-add-block">
                                <h4>
                                    服务保证
                                </h4>
                                <div class="product-add-main promise">
                                    <label for="">
                                        <input type="checkbox" class="ui-checkbox" checked="checked" disabled="disabled"/>
                                        <font>100%正品保证</font>
                                    </label>
                                    <label for="">
                                        <input type="checkbox" class="ui-checkbox" checked="checked" disabled="disabled"/>
                                        <font>专场包邮服务</font>
                                    </label>
                                    <label for="">
                                        <input type="checkbox" class="ui-checkbox" checked="checked" disabled="disabled"/>
                                        <font>48小时发货服务</font>
                                    </label>
                                    <label for="">
                                        <input type="checkbox" class="ui-checkbox" checked="checked" disabled="disabled"/>
                                        <font>7天无理由退货服务</font>
                                    </label>
                                </div>
                                <a class="promise-detail" href="#" target="_blank">
                                    详情&gt;
                                </a>
                            </div> -->
                            <div class="product-add-footbtn">
                                <a onclick="window.history.back()" class="ui-button ui-button-lwhite product-add-bigbtn">
                                    返回
                                </a>
                                <a  class="ui-button ui-button-lgreen product-add-bigbtn view-CreateBtn" id="next">
                                 下一步
                                </a>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
		</div>
</form>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
        
        <div class="miniDialog_mask" style="display: none;"></div>
        <!--商品详情示例-->
        <div class="miniDialog_wrapper showItemDetailExampleBox" style="width: 600px; height: 360px; margin-left:-300px; margin-top:-180px;">
            <div class="miniDialog_title">
                商品详情模板（图片宽度不超800像素）
            </div>
            <div class="miniDialog_content_outer">
                <div class="miniDialog_content">
                    <div style="padding:30px;">
                        <p>
                            1、商品信息（除基本信息之外需包含如下，不同类目可依据实际内容填写）
                        </p>
                        <p>
                            材质：
                        </p>
                        <p>
                            洗涤说明：
                        </p>
                        <p>
                            产地：
                        </p>
                        <p>
                            版型指数：修身 正常 宽松
                        </p>
                        <p>
                            弹力指数：无弹 微弹 弹力 超弹
                        </p>
                        <p>
                            柔软指数：软 偏软 适中 偏硬 硬
                        </p>
                        <p>
                            ……
                        </p>
                        <p>
                            2、尺码对照表
                            <span style="color:red;">
                                (童装、童鞋等具规格产品必须具备)
                            </span>
                        </p>
                        <p>
                            3、图片（不加水印）
                        </p>
                        <p>
                            大图（上身图）、建议搭配、细节图、水洗唛图、质检报告证明图
                        </p>
                    </div>
                </div>
            </div>
            <div class="miniDialog_buttons_area">
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--  批量填充SKU示例-->
        <div class="miniDialog_wrapper showItemImgExampleBoxBtn" style="width: 600px; height: 400px; margin-left:-300px; margin-top:-225px;">
            <div class="miniDialog_title">
                批量填充SKU要求说明
            </div>
            <div class="miniDialog_content_outer">
                <div class="miniDialog_content">
                 <!--<font>请先勾选需填充的sku！！！</font>-->
                    <div style="padding:15px 35px;">
	                    <div class="ui-form-item view-InputItem">
		                    <label for="" class="ui-label">规格:</label>
		                    <input class="ui-input" type="text" name="format" id="format" value="">
		                </div>
	                    <div class="ui-form-item view-InputItem">
		                    <label for="" class="ui-label">库存:</label>
		                    <input class="ui-input" type="text" name="stock" id="stock" value="">
		                </div>
	                    <div class="ui-form-item view-InputItem">
		                    <label for="" class="ui-label">价格:</label>
		                    <input class="ui-input" type="text" name="price" id="price" value="">
		                </div>
	                    <div class="ui-form-item view-InputItem">
		                    <label for="" class="ui-label">商家编码:</label>
		                    <input class="ui-input" type="text" name="businessCode" id="businessCode" value="">
		                </div>
                    </div>
                    <div style="text-align:center;"><a class="ui-button ui-button-lgreen "  onclick="batchFillCoding()" >批量填充</a></div>
                </div>
            </div>
            <div class="miniDialog_buttons_area">
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--  批量填充SKU示例-->
        <!--商品卖点显示位置示意图-->        
        <div class="miniDialog_wrapper whereToShow" style="width: 658px; height: 321px; margin-left:-329px; margin-top:-165px;">
            <div class="miniDialog_title">
                商品卖点显示位置示意图
            </div>
            <div class="miniDialog_content_outer">
                <div class="miniDialog_content">
                    <img src="http://b0.hucdn.com/img/help/where_to_show3.png" width="547"
                    height="261" alt="商品卖点显示位置"/>
                </div>
            </div>
            <div class="miniDialog_buttons_area">
            </div>
            <div class="miniDialog_close_x">
                <i class="iconfont">&#xf00b3;</i>
            </div>
        </div>
        <!--商品卖点显示位置示意图-->  
        <!--保存修改并提交审核-->          
        <div class="miniDialog_wrapper submitAudit" style="width: 450px; height: 200px; margin-left:-225px;margin-top:-100px;">
            <div class="miniDialog_title">
                保存修改并提交审核
            </div>
            <div class="miniDialog_content_outer">
                <div class="miniDialog_content">
                      想要某个sku属性有效，请在选中该sku打钩提交！！！工作人员将在1-2个工作日内完成审核，确认要立即保存并提交审核吗？
                </div>
            </div>
            <div class="miniDialog_buttons_area">
                <a class="miniDialog_button" id="queding">
                    确定
                </a>
                <a class="miniDialog_button miniDialog_last_button miniDialog_button_secondary" href="javascript:;">
                    取消
                </a>
            </div>
        </div>
        
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane container-fluid" id="album">
			    <form  class="ui-form product-add-form" action="productAddContent.do" method="post" enctype="multipart/form-data" onkeydown="if(event.keyCode==13)return false;" id="ProductContent">	
			    	  <input type="hidden" name="productPojo.id" value="" class="floatLeft" id="productId"/>
            <div id="content" class="wrapper" style="">
                <div class="pure-g admin-wrapper" style="">
                    <div class="pure-u-1 main" style="">
                        <h1 class="seller-title">
                            编辑商品详情
                        </h1>
                        <div class="sp-body view-ProductDetailView" style="">
                            <h1 class="product-add-title">
                                填写商品详情
                            </h1>
                            <p class="product-add-note">&nbsp;

                            </p>
                            <div class="product-add-block view-BasisInfoDetail">
                                <h4>
                                    商品详情
                                </h4>
                                <div class="product-add-main">
                                        <fieldset>

                                            
                                        <fieldset class="view-OtherPropDetail">
                                            
                                                <textarea rows="10" cols="70" name="productPojo.content" class="floatLeft" id="introduction"></textarea>
			                                    <script type="text/javascript">UE.getEditor("introduction");</script></br>
                                            </div>
                                            </fieldset>
                                            
                            </div>
                           
                            <div class="product-add-footbtn">
                                <a onclick="window.history.back()" class="ui-button ui-button-lwhite product-add-bigbtn">
                                    退出
                                </a>
                                <a  class="ui-button ui-button-lgreen product-add-bigbtn view-CreateBtn" id="next" onclick="$('#ProductContent').submit()">
                                 提交            </a>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
		</div>
</form>
			    	<!--<iframe src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/tzmeditor/index.html" id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();"id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();"></iframe>-->
			    </div>
			</div>
		</div>
    </div>
<!--	<div class="Btn_div">
		<button class="back_btn" id="past">上一步</button>
		<input type="button"  value="下一步" id="next" />
	</div>-->
</body>

</html>

<script type="text/javascript" language="javascript"> 
var productType1 =new tt.Field(" 商品一级类目","productPojo.productType1").setMsgId("productType1_mgId");	
var productTypeIds =new tt.Field(" 商品二级类目","productPojo.productTypeIds").setMsgId("productTypeIds_mgId");
var productTypeId =new tt.Field(" 商品三级类目","productPojo.productTypeId").setMsgId("productTypeId_mgId");
var productName =new tt.Field(" 商品标题","productPojo.productName").setMsgId("productName_mgId");
var productNum =new tt.Field(" 商品货号","productPojo.productNum").setMsgId("productNum_mgId");
//var location_mgId =new tt.Field(" 产地","productPojo.location").setMsgId("location_mgId");
//var productSketch =new tt.Field("商品卖点","productPojo.productSketch").setMsgId("productSketch_mgId");
var distributionPrice =new tt.Field(" 市场价","productPojo.distributionPrice").setMsgId("distributionPrice_mgId");
var sellingPrice =new tt.Field(" 建议零售价","productPojo.sellingPrice").setMsgId("sellingPrice_mgId");
var proxyPrice =new tt.Field(" 供货价(包邮)","productPojo.proxyPrice").setMsgId("proxyPrice_mgId");
//var weight =new tt.Field("重量","productPojo.weight").setMsgId("weight_mgId");
//var userBrandId =new tt.Field("品牌","productPojo.userBrandId").setMsgId("userBrandId_mgId");
tt.Conf.reqStarCls = ""; 
tt.vf.req.add(productType1,productTypeIds,productTypeId,productName,productNum/* ,location_mgId,productSketch */,distributionPrice,sellingPrice,proxyPrice/* ,weight,userBrandId */);
//new tt.LV().set(0, 150).add(productSketch);
//tt.vf.num.add(weight);
//new tt.NRV().set(0, '++').add(weight);
window.onload = function(){
	$("#past").hide();
	
	 //监听标签页
	 $("#albumLi,#next").click(function(){
		if(tt.validate()){
			//提交表单
			$(".submitAudit,.miniDialog_mask").show();
			   $("#queding").click(function(){
			   		 $("#from1").ajaxSubmit({
		    			type: 'post', 
		                url: 'productAddSellerWeb.do', 
		                success: function(msg) {
		                //获取字符串第一个字符
		                var msg1=msg.charAt(0);
		                var msg2=msg.substring(1);
		                    if(msg1 == "1"){
		                    	alert("新增商品成功");
		                    	$('#albumLi a').tab('show');
		                    	$(".submitAudit,.miniDialog_mask").hide();
		                    	$('#productId').val(msg2);
		                    }else if(msg1 == "2"){
		                        alert("新增商品成功，但sku商家编码不能为空！"); 
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                        $('#productId').val(msg2);
		                    }else if(msg1 == "3"){
		                        alert("新增商品成功，但sku库存不能为空！");
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                        $('#productId').val(msg2);
		                    }else if(msg1 == "4"){
		                        alert("新增商品成功，但sku库存不能小于0！");
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                        $('#productId').val(msg2);
		                    }else if(msg1 == "5"){
		                        alert("新增商品成功，但sku价格不能为空！");
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                        $('#productId').val(msg2);
		                    }else if(msg1 == "6"){
		                        alert("修改失败，sku审核状态不能为空！");
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                    }else if(msg1 == "7"){
		                        alert("新增商品成功，但sku规格不能为空！");
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                        $('#productId').val(msg2);
		                    }else if(msg1 == "8"){
		                        alert("新增商品成功，但sku颜色不能为空！");
		                        $('#albumLi a').tab('show');
		                        $(".submitAudit,.miniDialog_mask").hide();
		                        $("#next").hide();
		                        $("#past").show();
		                        $('#productId').val(msg2);
		                    }else{
		                        alert("新增商品失败！");
		                      $(".submitAudit,.miniDialog_mask").hide();
		                    }
		                 
		                }
		    		  });
				
	             });
		    		  return false;
		 }
		 else{
		 return false;
		 }
		
	 });
	 $("#past").click(function(){
		$("#past").hide();
		$("#next").show();
		$("#infoLi").find("a").click();
	 });
	 
	 //点击提交按钮
	 $('.sbutton').click(function() {
		//alert(11);
    	if (tt.validate()) {
            $("#albumLi").find("a").click();
    	}
	 });
}


		var ue = UE.getEditor('editor');
			$(function(){
				
				//显示在哪
				$(".view-WhereToShow").on("click",function(){
					$(".whereToShow,.miniDialog_mask").show();
				});
				
				//批量填充SKU
				$(".view-ShowItemImgExampleBoxBtn").on("click",function(){
					var ddd=1;
					var code_Values = document.getElementsByName("tids") ; 
					for(i = 0;i < code_Values.length;i++){ 
						if(code_Values[i].type == "checkbox" && code_Values[i].checked == false) { 
                                ddd=2;
							}else{
								ddd=1;
								$(".showItemImgExampleBoxBtn,.miniDialog_mask").show();								
								break;
							}
					}
					if(ddd==2){
						alert("请先勾选需要填充的sku！");
					}
				});
				
				//查看模板
				$(".view-ShowItemDetailExampleBoxBtn").on("click",function(){
					$(".showItemDetailExampleBox,.miniDialog_mask").show();
				});							
				
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
				
				//提交确认
				//$(".view-CreateBtn").on("click",function(){
				//	$(".submitAudit,.miniDialog_mask").show();
				//});
				
				
			})
	
			
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
			if(code_Values[i].type == "checkbox") 
				{ 
					code_Values[i].checked = true; 
				} 
		} 
	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
			if(code_Values[i].type == "checkbox") 
				{ 
					code_Values[i].checked = false
				} 
		} 
		
	}

}			
function addPrice(){
	//var imgHtml = '<td><div class="uploadify" style="position:relative;height: 80px; width: 80px;"><button class="uploadPreview_note"style="width:80px;height:80px;line-height:80px;"><i class="iconfont">&#xf00f7;</i>图片(必选)</button><div class="uploadPreview_img"style="display:none;"> <img style="width:80px;height:80px;"/></div><input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfileSku" style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/> </td>';
	$("#priceBody").append(
		"<tr><td><input type='checkbox' name='tids' class='cb_price' /><input type='hidden' name='status'></td>"+
		"<td><input type='text' name='colors' value='' placeholder='必填'/></td>"+
		"<td><input type='text' class='formats' name='formats' value='' placeholder='必填'/></td>"+
		//"<td><input type='file' name='upfileSku' class='floatLeft' /></td>"+
		//imgHtml+
		"<td><input type='text' class='stocks' name='stocks' value='' placeholder='必填'/></td>"+				
		"<td><input type='text' class='prices' name='prices' value='' placeholder='必填'/></td>"+
		"<td><input type='text' class='businessCodes' name='businessCodes' value='' placeholder='必填'/></td></tr>"
	);
}

function delPrice(){
	if(confirm("你真的想删除该记录么？")){
		$(".cb_price:checked").each(function(){
				$(this).parent().parent().remove();
		});
	}
}

	function submit(){
		var ttt = true;
		var code_Values = document.getElementsByName("tids") ; 		
			var statuses = document.getElementsByName("status") ; 
//			var checkbox=document.getElementById("selectcb"); 
//			if(checkbox.checked==false){
				 for(i = 0;i < code_Values.length;i++){ 
					if(code_Values[i].type == "checkbox") {
						if(code_Values[i].checked == true){
							statuses[i].value=1;
							var ttt = true;
						}else{
							statuses[i].value=0;
							var ttt = false;
							code_Values[i].checked = true
						}
					} 
				}
				
//			}
		 if(tt.validate() && !$(".main_img").is(":hidden") && code_Values.length>0 && ttt){
			
			if(!validate_sku()){
				alert("sku信息没有填写完整，请填写完整");
				$(".submitAudit,.miniDialog_mask").hide();
				$(".view-SkuInfoDetail-tips").length==1 ? $(".view-SkuInfoDetail-tips").show() : $(".view-SkuInfoDetail").append('<p class="view-SkuInfoDetail-tips" style="color:red;">请填写完整的sku信息</p>');
				return false
			}else{
				$(".view-SkuInfoDetail-tips").hide();
			}
			
			document.getElementById("from1").submit();
			
		}else{
			if(ttt==false){
				alert("sku至少有一条打钩保证有效");
			}else{
				alert("还有信息没有填写哦，请填写完整");
			}		
			$(".submitAudit,.miniDialog_mask").hide();
		}
	}
	//sku验证
	function validate_sku(){
		var state = true;
		$("#priceBody input[type='text']").each(function(index,el){
			if($(el).val()==""){
				state = false;
			}
		});
		if($("#priceBody").length!=0 && $("#priceBody").is(":hidden")){
			state = false;
		}
		return state;
	}

function showDiscount(){
	var d = $("input[name='productPojo.distributionPrice']").val().trim();
	var s = $("input[name='productPojo.sellingPrice']").val().trim();
	if(d != "" && d != null && s != "" && s != null){
		if(isNaN(d) || isNaN(s)){
			$("#discount").text("原价或特卖价格输入有误！");
		}else{
			var p = d/s*10;
			$("#discount").text(p.toFixed(1)+"折");
		}
	}else{
		$("#discount").text("请输入原价和特卖价格！");
	}
}

function batchFillCoding(){
	var f = $("input[name='format']").val()
	var s = $("input[name='stock']").val();
	var p = $("input[name='price']").val();
	var b = $("input[name='businessCode']").val();	
	
	$("input[name='tids']").each(function(index,el){
		if($(el).is(":checked")){
			$(el).parents("tr").find(".formats").val(f);
			$(el).parents("tr").find(".stocks").val(s);
			$(el).parents("tr").find(".prices").val(p);
			$(el).parents("tr").find(".businessCodes").val(b);
		}
	});
		
	$(".showItemImgExampleBoxBtn,.miniDialog_mask").hide();
}

function changeProType(){
	var aVal = $("select[name='productPojo.productType1']").val();
	$.ajax({
		url:"getProductType2.do?productTypePojo.level=1&productTypePojo.topLevel=" + aVal,
		success:function(data){
			var c = eval("(" + data + ")");
			$("#productTypeIds").html('<option value="">----- 二级类目 -----</option>');
			$("#productTypeId").html('<option value="">----- 三级类目 -----</option>');
			$("#productTypeIds").append(c[0].data);
		},
		error:function(){
			alert("error");
		}
	});
	
}

function changePidType(){
	var bVal = $("select[name='productPojo.productTypeIds']").val();
	$.ajax({
		url:"getProductType3.do?productTypePojo.pid=" + bVal,
		success:function(data){
			var c = eval("(" + data + ")");
			$("#productTypeId").html('<option value="">----- 三级类目 -----</option>');
			$("#productTypeId").append(c[0].data);
		},
		error:function(){
			alert("error");
		}
	});
	
}

var i = 1;
function addImage(){
	if(i < 10){
		i++;
		$("#imageBox").append('<div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;margin-top: 10px;"><a style="z-index : 1000;position : absolute;right : 10px;top : 10px;font-style : italic;" class="" name="removeBox"><b>X</b></a><button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;"><i class="iconfont">&#xf00f7;</i>添加图片'+ i +'</button><div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div><input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfiles" data-id="\'\+dataId\+\'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/><br /><span id="image'+ i +'_mgId"></span></div> ');
	}else{
		alert("最多只能添加10张图片！~");
	}
}

$("body").on("click", "a[name='removeBox']", function () {
	i--;
	$(this).parent().remove();
});
</script>