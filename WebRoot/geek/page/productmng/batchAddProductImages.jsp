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
		<!-- image dialog -->
		<link rel="stylesheet" href="/ueditor1_4_3-utf8-jsp/dialogs/image/imageupload.css" type="text/css" />
		<link href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/ueditor1_4_3-utf8-jsp/third-party/webuploader/webuploader.css">
		<!-- jquery -->
		<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/third-party/jquery-1.10.2.min.js"></script>
		<!-- webuploader -->
		<script src="/ueditor1_4_3-utf8-jsp/third-party/webuploader/webuploader.min.js"></script>	
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>js/_head.js"></script>
		<title>商家中心</title>
	</head>
	<body>
		<jsp:include page="../geekHeader.jsp"></jsp:include>
		<form class="ui-form product-add-form"  action=""  method="post" id="from1" enctype="multipart/form-data">
			<input type="hidden" name="productImagesPojo.productId" id="productImagesPojo.productId"
				value="${productImagesPojo.productId}"/>
			<input type="hidden" name="productImagesPojo.userId" id="productImagesPojo.userId"
				value="${productImagesPojo.userId}"/>

			<div id="content" class="wrapper" style="">
				<div class="pure-g admin-wrapper" style="">
					<div class="pure-u-1 main" style="">
						<h1 class="seller-title">
							添加商品图片
						</h1>
						<div class="sp-body view-ProductDetailView" style="">
							<h1 class="product-add-title">
								商品图片详情
							</h1>
							<p class="product-add-note">&nbsp;

							</p>
							<div class="product-add-block view-BasisInfoDetail">
								<h4>
									商品图片上传</br>
								</h4>
								<div class="product-add-main">
									<div id="uploader" class="wu-example">
										<div class="queueList">
											<div id="dndArea" class="placeholder">
												<div id="filePicker"></div>
												<p>或将照片拖到这里，单次最多可选50张</p>
											</div>
										</div>
										<div class="statusBar" style="display:none;">
											<div class="progress">
												<span class="text">0%</span>
												<span class="percentage"></span>
											</div>
											<div class="info"></div>
											<div class="btns">
												<div id="filePicker2"></div>
												<div class="uploadBtn">开始上传</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="product-add-footbtn">
					<a href="productImagesManageGeekWeb.do?productImagesPojo.productId=${productImagesPojo.productId}&productImagesPojo.userId=${productImagesPojo.userId}" class="ui-button ui-button-lwhite product-add-bigbtn product-add-shortbtn">
						返回
					</a>
				</div>
				<div>
					<br />
				</div>
			</div>
		</form>
		<jsp:include page="../geekFooter.jsp"></jsp:include>
	</body>
</html>
<script  type="text/javascript">
	var actionUrl = "/productImgBatchAddWeb.do?productImagesPojo.productId=${productImagesPojo.productId}";
</script>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/dialogs/image/imageupload.js"></script>
