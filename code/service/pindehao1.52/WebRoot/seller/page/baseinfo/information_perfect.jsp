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
	<%-- <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/default.css" media="all" />
	<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/spage.css"/> --%>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
	<div id="content" class="wrapper">
		<div class="pure-g admin-wrapper">
			<div class="pure-u-1 main">
				<style type="text/css">
.ui-form fieldset {
	border: 0 none;
	margin: auto;
	padding: 60px 0 100px;
	width: 500px;
}

input.ui-button {
	padding: 0 1.5em;
}

.uploadify {
	position: relative;
	margin-bottom: 1em;
}

.uploadify-button {
	background-color: #ccc;
	background-image: linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -o-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -moz-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -webkit-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -ms-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #ccc),
		color-stop(1, #eee));
	background-position: center top;
	background-repeat: no-repeat;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 2px;
	border: 1px solid #888;
	color: #666;
	font: bold 12px Arial, Helvetica, sans-serif;
	text-align: center;
	width: 100%;
}

.uploadify:hover .uploadify-button {
	background-color: #ccc;
	background-image: linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -o-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -moz-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -webkit-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -ms-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #ccc),
		color-stop(1, #eee));
	background-position: center bottom;
}

.upload-tax-registration-certificate {
	background-image:
		url(http://b5.hucdn.com/upload/seller/1510/19/45551263126827_1000x727.jpg);
	background-size: 120px 100px;
}

.upload-general-taxpayer-qualification {
	
}

.modify-stauts {
	display: block;
}

.u-note-tip {
	position: relative;
	top: 4px;
	left: 6px;
	cursor: pointer;
	*z-index: 20;
}

.u-note-tip .ui-poptip-blue {
	display: none;
	position: absolute;
	width: 325px;
	height: 196px;
	top: 22px;
	left: -155px;
	*z-index: 100;
}

.u-note-tip li {
	list-style: circle;
	margin-left: 16px;
}

.uploadify {
	position: relative;
	height: 180px;
	width: 180px;
	text-align: center;
	border: 1px solid #ddd;
}

.uploadPreview_note {
	width: 180px;
	height: 180px;
	line-height: 180px;
}

.uploadPreview_imgfile {
	position: absolute;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	opacity: 0;
	z-index: 3;
	filter: alpha(opacity = 0);
	cursor: pointer;
}

em {
	font-size: larger;
}
</style>
			<%-- <c:if test="${manufacturerPojo.returnContent != null && manufacturerPojo.status == 0}">
				<div class="ui-tipbox ui-tipbox-success" style="display:none;">
					<div class="ui-tipbox-icon">
						<i class="iconfont">&#xf0156;</i>
					</div>
					<div class="ui-tipbox-content">
						<h3 class="ui-tipbox-title">退回原因</h3>
						<font color="red" style='word-wrap: break-word;' class="returnCont">${manufacturerPojo.returnContent}</font>
					</div>
				</div>
				</c:if> --%>
				<s:if test="manufacturerPojo.status == 1">
				<div class="ui-tipbox ui-tipbox-success">
					<div class="ui-tipbox-icon">
						<i class="iconfont">&#xf0156;</i>
					</div>
				</s:if>
				<s:else>
				<div class="ui-tipbox ui-tipbox-error">
					<div class="ui-tipbox-icon">
						<i class="iconfont">&#xf0155;</i>
					</div>
				</s:else>
					<div class="ui-tipbox-content">
						<h3 class="ui-tipbox-title">操作提示</h3>
						<c:if test="${manufacturerPojo.status == 1}">
							<p class="ui-tipbox-explain">审核已通过</p>
						</c:if>
						<c:if test="${manufacturerPojo.status == 0 and (manufacturerPojo.returnContent == '' or manufacturerPojo.returnContent == null)}">
							<p class="ui-tipbox-explain">等待管理员审核</p>
						</c:if>
						<c:if test="${manufacturerPojo.status == 0 && (manufacturerPojo.returnContent != '' && manufacturerPojo.returnContent != null)}">
							<p class="ui-tipbox-explain">退回修改</p>
						</c:if>
					</div>
				</div> 
				<form action="doInformationPerfectWeb.do" method="post" id="from1" enctype="multipart/form-data" class="ui-form">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
					<h2> 负责人基本信息</h2>
					<hr></hr>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 主营品类：</label>
							&nbsp;
							<select name="manufacturer.mainCategory" id="ticketType"  class="ui-input">
							<c:forEach items="${mainCategory}" var="mainCategory">
								<option value="${mainCategory.id}"<c:if test="${manufacturerPojo.mainCategory==mainCategory.id}">selected="selected" </c:if>>${mainCategory.name}</option>
							</c:forEach>
		    				</select>
							<div id="mainCtg_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 商务联系人：</label>
							<input type="text" name="manufacturer.contact"  value="${manufacturerPojo.contact}" class="ui-input" id="ticketName">
							<div id="contact_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 联系号码：</label>
							<input type="text" name="manufacturer.phone"  value="${manufacturerPojo.phone}" class="ui-input" id="ticketName">
							<div id="c_phone_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 邮箱地址：</label>
							<input type="text" name="manufacturer.email" value="${manufacturerPojo.email}" class="ui-input" id="ticketName">
							<div id="email_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 紧急联系人：</label>
							<input type="text" name="userCertificatesPhotoPojo.emergencyContactName" value="${userCertificatesPhotoPojo.emergencyContactName}" class="ui-input" id="ticketName">
							<div id="emergencyContactName_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 紧急联系人手机：</label>
							<input type="text" name="userCertificatesPhotoPojo.emergencyContactPhone" value="${userCertificatesPhotoPojo.emergencyContactPhone}" class="ui-input" id="ticketName">
							<div id="emergencyContactPhone_mgId"></div>
						</div>		
					<h2> 企业信息</h2>
					<hr></hr>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 公司法人：</label>
							<input type="text" name="manufacturer.legalPerson"  value="${manufacturer.legalPerson}" class="ui-input" id="ticketName">
							<div id="legalPerson_mgId"></div>
					  </div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 法人身份证号：</label>
							<input type="text" name="userCertificatesPhotoPojo.image4No"  value="${userCertificatesPhotoPojo.image4No}" class="ui-input" id="ticketName">
							<div id="image4No_mgId"></div>
					  </div> 
					<div class="ui-form-item">
							<label for="" class="ui-label"> 身份证有效期：</label>
							从<input id="s" name="userCertificatesPhotoPojo.image4BeginDate" value="" class="Wdate" type="text" 
							onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd',
								maxDate:'#F{$dp.$D(\'e\')}'})"/><br />
								至<input id="e" name="userCertificatesPhotoPojo.image4EndDate" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',
								minDate:'#F{$dp.$D(\'s\')}'})"/>
							<div id="image4Date_mgId"></div>
					  </div>   
					<div class="ui-form-item">
							<label for="" class="ui-label"> 公司名称：</label>
							<input type="text" name="manufacturer.company"  value="${manufacturerPojo.company}" class="ui-input" id="ticketName">
					 		<div id="company_mgId"></div><font color="red">公司名称，请严格按照您公司营业执照上的名称填写，务必保证填写的公司名称和您商标注册证上的公司名称完全一致。</font>
					  </div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 营业执照注册号：</label>
							<input type="text" name="userCertificatesPhotoPojo.image1No"  value="${userCertificatesPhotoPojo.image1No}" class="ui-input" id="ticketName">
					 		<div id="image1No_mgId"></div>
					  </div>
				    <div class="ui-form-item">
							<label for="" class="ui-label"> 组织机构代码号：</label>
							<input type="text" name="userCertificatesPhotoPojo.image2No"  value="${userCertificatesPhotoPojo.image2No}" class="ui-input" id="ticketName">
					 		<div id="image2No_mgId"></div>
					  </div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 纳税人识别号：</label>
							<input type="text" name="userCertificatesPhotoPojo.image3No"  value="${userCertificatesPhotoPojo.image3No}" class="ui-input" id="ticketName">
					 		<div id="image3No_mgId"></div>
					  </div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 统一社会信用代码：</label>
							<input type="text" name="userCertificatesPhotoPojo.image5No"  value="${userCertificatesPhotoPojo.image5No}" class="ui-input" id="ticketName">
					 		<div id="image5No_mgId"></div>
					 </div>		
					<div class="ui-form-item">
							<label for="" class="ui-label"> 营业执照有效期：</label>
							从<input id="s" name="userCertificatesPhotoPojo.image1BeginDate" value="" class="Wdate" type="text" 
							onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd',
								maxDate:'#F{$dp.$D(\'e\')}'})"/><br />
								至<input id="e" name="userCertificatesPhotoPojo.image1EndDate" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',
								minDate:'#F{$dp.$D(\'s\')}'})"/>
							<div id="image1Date_mgId"></div>
					  </div>
				   <div class="ui-form-item">
							<label for="" class="ui-label"> 组织机构证有效期：</label>
							从<input id="s" name="userCertificatesPhotoPojo.image2BeginDate" value="" class="Wdate" type="text" 
							onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd',
								maxDate:'#F{$dp.$D(\'e\')}'})"/><br />
								至<input id="e" name="userCertificatesPhotoPojo.image2EndDate" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',
								minDate:'#F{$dp.$D(\'s\')}'})"/>
							<div id="image2Date_mgId"></div>
					  </div>
					<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 营业执照：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image1">
							</div>
							<em class="red" id="color1"><div id="image1_mgId">*</div></em>
						</div>
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 组织机构代码证照：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image2">
							</div>
							<em class="red" id="color2"><div id="image2_mgId">*</div></em>
						</div>
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 税务登记证照：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image3">
							</div>
							<em class="red" id="color3"><div id="image3_mgId">*</div></em>
						</div>
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 法人身份证正面照：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image4">
							</div>
							<em class="red" id="color4"><div id="image4_mgId">*</div></em>
						</div>
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 法人身份证反面照：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image5">
							</div>
							<em class="red" id="color5"><div id="image5_mgId">*</div></em>
						</div>
					<!--	<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 合同照：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image6" id="imageBox6">
							</div>
							<em class="red" id="color6"><div id="image6_mgId">*</div></em>
						</div>-->
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 开户许可证：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image8" id="imageBox8">
							</div>
							<em class="red" id="color8"><div id="image8_mgId">*</div></em>
						</div>
					<h2> 质检报告（可填、可多选）</h2>
					<hr></hr>
					<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片1：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="qc_image1" id="qcImageBox1">
							</div>
						</div>
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片2：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="qc_image2" id="qcImageBox2">
							</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片3：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="qc_image3" id="qcImageBox3">
							</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片4：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="qc_image4" id="qcImageBox4">
							</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片5：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="qc_image5" id="qcImageBox5">
							</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片6：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="qc_image6" id="qcImageBox6">
							</div>
						</div>
					<h2> 品牌授权证明（可填、可多选）</h2>
					<hr></hr>
					</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片1：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image1" id="blImageBox1">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片2：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image2" id="blImageBox2">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片3：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image3" id="blImageBox3">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片4：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image4" id="blImageBox4">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片5：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image5" id="blImageBox5">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片6：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image6" id="blImageBox6">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片7：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image7" id="blImageBox7">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片8：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image8" id="blImageBox8">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片9：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image9" id="blImageBox9">
							</div>
						</div>
						</div><div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 图片10：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="bl_image10" id="blImageBox10">
							</div>
						</div>
			<!--	<div class="ui-form-item ui-form-item-error">
							<label for="" class="ui-label"> 帐号： </label>
							&nbsp;
							<p class="ui-form-text">${session.wuser.loginname}</p>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 昵称：</label>
							<input type="hidden" name="sysLogin.name"  value="" class="ui-input" id="ticketName">
							<div id="namead"></div>
						</div> 
						
						<div class="ui-form-item">
							<label for="" class="ui-label"> 公司规模：</label>
							&nbsp;
							<select name="manufacturer.scale" id="ticketType"  class="ui-input">
							<c:forEach items="${scale}" var="scale">
								<option value="${scale.value}"<c:if test="${manufacturerPojo.scale==scale.value}">selected="selected" </c:if>>${scale.name}</option>
							</c:forEach>
		    				</select>
							<div id="scale_mgId"></div>
						</div>

						<div class="ui-form-item">
							<label for="" class="ui-label"> 公司电话：</label>
							<input type="text" name="manufacturer.companyPhone"  value="${manufacturer.companyPhone}" class="ui-input" id="ticketName">
							<div id="companyPhone_mgId"></div><font color="red">请填写您入驻公司的注册电话或办公电话。</font>
						</div>
						<div class="ui-form-item">
							<label for="" class="ui-label"> 公司传真：</label>
							<input type="text" name="manufacturer.fax"  value="${manufacturer.fax}" class="ui-input" id="ticketName">
							<div id="fax_mgId"></div><font color="red">请填写您入驻公司的注册传真或办公传真。</font>
						</div>
						<div class="ui-form-item">
							<label for="" class="ui-label"> 旗下品牌：</label>
							<%-- <input type="text" name="manufacturer.brand"  value="${manufacturer.brand}" class="ui-input" id="ticketName">
							 --%><textarea name="manufacturer.brand" class="ui-input" rows="5">${manufacturer.brand}</textarea>
							 <div id="brand_mgId"></div>
						</div>
						<div class="ui-form-item">
							<label for="" class="ui-label"> 经营范围：</label>
							<%-- <input type="text" name="manufacturer.scopeBusiness"  value="${manufacturer.scopeBusiness}" class="ui-input" id="ticketName">
							 --%><textarea name="manufacturer.scopeBusiness" class="ui-input" rows="5">${manufacturer.scopeBusiness}</textarea>
							<div id="scopeBusiness_mgId"></div><font color="red">经营范围，请严格按照您营业执照上的经营范围进行填写。</font>
						</div>
						<div class="ui-form-item" style="display: none;">
							<label for="" class="ui-label"> 是否查看：</label>
							&nbsp;
							<select name="manufacturer.isread" id="ticketType"  class="ui-input">
							<c:forEach items="${isread}" var="isread">
								<option value="${isread.value}"<c:if test="${manufacturerPojo.isread==isread.value}">selected="selected" </c:if>>${isread.name}</option>
							</c:forEach>
		    				</select>
							<div id="read_mgId"></div>
						</div>
						
						<div class="ui-form-item">
							<label for="" class="ui-label"> 职务：</label>
							<input type="text" name="manufacturer.duty" value="${manufacturerPojo.duty}" class="ui-input" id="ticketName">
							<div id="duty_mgId"></div>
						</div>
						
						<div class="ui-form-item">
							<label for="" class="ui-label"> QQ：</label>
							<input type="text" name="manufacturer.QQ"  value="${manufacturerPojo.QQ}" class="ui-input" id="ticketName">
							<div id="QQ_mgId"></div>
						</div>
						
						<div class="ui-form-item">
							<label for="" class="ui-label"> 地址：</label>
							<p>&nbsp;
							<select id="province" name="shop.province" class="ui-input" ></select><br /><br /></p>
        					<p>&nbsp;
        					<select id="city" name="shop.city" class="ui-input"></select><br /><br /></p>
        					<p><select id="area" name="shop.area" class="ui-input"></select><br /><br /></p>
        					<label for="" class="ui-label"> 详细地址：</label>
        					<input type="text" name="shop.address" class="ui-input" value="${shop.address}" id="ticketName" />
							<div id="s_address_mgId"></div><font color="red">请填写您入驻公司的真实地址或所在地址。</font>
						</div>
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 品牌LOGO：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile">
							</div>
						</div>

						
						<div class="ui-form-item TaxRegistrationCertificate ">
							<label for="" class="ui-label"> 其他资质照（可不填）：</label>
							<div class="uploadify">
								<p class="uploadPreview_note">
									<i class="iconfont">&#xf00f7;</i> 添加图片
								</p>
								<div class="uploadPreview_img" style="display: none;">
									<img style="width: 180px; height: 180px;">
								</div>
								<input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image7" id="imageBox7">
							</div>
							<em class="red" id="color7"><div id="image7_mgId"></div></em>
						</div>-->
						<div class="ui-form-item">
							<input type="button" class="ui-button ui-button-lred" value="提交" id="sbutton">
						</div>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/base.js" type="text/javascript"></script>
		<script>
			$(function(){
				var s = "${manufacturerPojo.status}";
				var str = "${manufacturerPojo.returnContent}";
				if(s == 0){
					if(str != null && str != ""){
						alert("退回原因：" + str + "\n请填写完整信息，等待管理员审核！");
					}else{
						alert("请填写完整信息，等待管理员审核！");
					}
				}
				//发票类型提示
				$(".u-note-tip").hover(function(){
					$(".u-note-tip .ui-poptip-blue").show();	
				},function(){
					$(".u-note-tip .ui-poptip-blue").hide();					
				});
				
				//上传图片
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
					_this.siblings(".uploadPreview_note").hide();
				});
				
			})
		</script>
</body>
</html>
<script type="text/javascript">
	$(function(){
		select1();
		$('#province').bind("change", select2);
        $('#city').bind("change", select3);
	});
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var s = "";
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${shop.province}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#province").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
					if("${shop.province}" == o_msg[i].id){
						select2();
					}
				}
			}
		})
	};
	function select2() {
		$("#city").html("");
        $("#city").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
            dataType: 'json',
            success: function (msg) {
            	var s = "";
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	if("${shop.city}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
                    $("#city").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
                    if("${shop.city}" == o_msg[i].id){
						select3();
					}
                }
            }
        })
    };
	function select3() {
		$("#area").html("");
        $("#area").append("<option value=''>- 请选择 -</option>");
       $.ajax(
       {
           type: "post",
           url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
           dataType: 'json',
           success: function (msg) {
        	   var s = "";
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${shop.area}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#area").append("<option value=" + o_msg[i].id + " "+s+"> " + o_msg[i].name + "</option>");
				}
           }
       })
   };
</script>
<script>
//var namead =new tt.Field(" 昵称 ","sysLogin.name").setMsgId("namead");
var company =new tt.Field(" 公司名称 ","manufacturer.company").setMsgId("company_mgId");
var legalPerson =new tt.Field(" 公司法人 ","manufacturer.legalPerson").setMsgId("legalPerson_mgId");
var companyPhone =new tt.Field(" 公司电话 ","manufacturer.companyPhone").setMsgId("companyPhone_mgId");
var fax =new tt.Field(" 公司传真 ","manufacturer.fax").setMsgId("fax_mgId");
var brand =new tt.Field(" 旗下品牌 ","manufacturer.brand").setMsgId("brand_mgId");
var scopeBusiness =new tt.Field(" 经营范围 ","manufacturer.scopeBusiness").setMsgId("scopeBusiness_mgId");
var contact =new tt.Field(" 商务联系人 ","manufacturer.contact").setMsgId("contact_mgId");
var duty =new tt.Field(" 职务 ","manufacturer.duty").setMsgId("duty_mgId");
var QQ =new tt.Field(" QQ ","manufacturer.QQ").setMsgId("QQ_mgId");
var email =new tt.Field(" 邮箱地址 ","manufacturer.email").setMsgId("email_mgId");
var cPhone =new tt.Field(" 联系号码 ","manufacturer.phone").setMsgId("c_phone_mgId");
var sArea =new tt.Field(" 地址 ","shop.area").setMsgId("s_address_mgId");
var sAddress =new tt.Field(" 地址 ","shop.address").setMsgId("s_address_mgId");
//var image1BeginDate =new tt.Field(" 营业执照有效开始日期 ","userCertificatesPhotoPojo.image1BeginDate").setMsgId("image1Date_mgId");
//var image1EndDate =new tt.Field(" 营业执照有效结束日期 ","userCertificatesPhotoPojo.image1EndDate").setMsgId("image1Date_mgId");

//var mainCategory =new tt.Field(" 营业分类","manufacturer.mainCategory").setMsgId("mainCtg_mgId");
var emergencyContactName =new tt.Field(" 紧急联系人 ","userCertificatesPhotoPojo.emergencyContactName").setMsgId("emergencyContactName_mgId");
var emergencyContactPhone =new tt.Field(" 紧急联系人手机 ","userCertificatesPhotoPojo.emergencyContactPhone").setMsgId("emergencyContactPhone_mgId");
var image4No =new tt.Field(" 法人身份证号 ","userCertificatesPhotoPojo.image4No").setMsgId("image4No_mgId");
var image4BeginDate =new tt.Field(" 身份证有效开始日期 ","userCertificatesPhotoPojo.image4BeginDate").setMsgId("image4Date_mgId");
var image4EndDate =new tt.Field(" 身份证有效有效结束日期 ","userCertificatesPhotoPojo.image4EndDate").setMsgId("image4Date_mgId");
var image1No =new tt.Field(" 营业执照注册号","userCertificatesPhotoPojo.image1No").setMsgId("image1No_mgId");
var image2No =new tt.Field(" 组织机构代码号 ","userCertificatesPhotoPojo.image2No").setMsgId("image2No_mgId");
var image3No =new tt.Field(" 纳税人识别号 ","userCertificatesPhotoPojo.image3No").setMsgId("image3No_mgId");
var image5No =new tt.Field(" 统一社会信用代码 ","userCertificatesPhotoPojo.image5No").setMsgId("image5No_mgId");
var image2BeginDate =new tt.Field(" 组织机构证有效开始日期 ","userCertificatesPhotoPojo.image2BeginDate").setMsgId("image2Date_mgId");
var image2EndDate =new tt.Field(" 组织机构证有效结束日期 ","userCertificatesPhotoPojo.image2EndDate").setMsgId("image2Date_mgId");

//var image1 =new tt.Field(" 营业执照 ","image1").setMsgId("image1_mgId");
//var image2 =new tt.Field(" 组织机构代码证照 ","image2").setMsgId("image2_mgId");
//var image3 =new tt.Field(" 税务登记证照 ","image3").setMsgId("image3_mgId");
//var image4 =new tt.Field(" 法人身份证正面照 ","image4").setMsgId("image4_mgId");
//var image5 =new tt.Field(" 法人身份证反面照 ","image5").setMsgId("image5_mgId");

tt.vf.req.add(/* namead, */company,legalPerson,companyPhone,fax,brand,scopeBusiness,contact,duty,QQ,email,cPhone,sArea,sAddress/* ,image1BeginDate,image1EndDate */,emergencyContactName,emergencyContactPhone,image4No,image4BeginDate,image4EndDate,image1No,image2No,image3No,image5No,image2BeginDate,image2EndDate);

//tt.vf.req.add(image1,image2,image3,image4,image5);

//new tt.LV().set(0, 30).add(namead);
new tt.LV().set(0, 50).add(company,legalPerson,companyPhone,fax,contact,duty,QQ,email,cPhone);
new tt.LV().set(0,225).add(brand,sAddress);
new tt.LV().set(0,1000).add(scopeBusiness);
tt.vf.num.add(QQ);

MyValidator1 = tt.BV.ext({
	/**
	 * 验证的主方法
	 */
	v : function(trimedValue, indexOfElements, elements, field){
		var vparam = field.name;
		var url = "validLoginRegiste.do?"+ vparam + "=" + trimedValue;
		var flag = false;
		$.ajax(
			{
				type: "post",
				url: url,
				dataType: 'json',
				async: false,
				success: function (data) {
					if(data==1){
						flag = true;
					}else{
					    flag = false;
					};
				}
			})
		return flag;
	},
	getI18 : function(label){
		return "已被注册，请换一个!";
	},
	/**
	 * 验证通过时，提示信息
	 */
	getTip : function(e,f,v,val) {
		return "恭喜！可以使用!";
	}
});

//new MyValidator1().add(namead);

$(document).ready(function() {
	var path = "<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/manufacturer/";
	if("${manufacturerPojo.logo}" != null){
		var url = path+"${manufacturerPojo.logo}";
		var _this = $("#upfileBox");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	path = "<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userCertificatesPhoto/";
	if("${userCertificatesPhotoPojo.image1}" != null){
		var url = path+"${userCertificatesPhotoPojo.image1}";
		var _this = $("#imageBox1");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
		//_this.val(url);
	}
	if("${userCertificatesPhotoPojo.image2}" != null){
		var url = path+"${userCertificatesPhotoPojo.image2}";
		var _this = $("#imageBox2");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	if("${userCertificatesPhotoPojo.image3}" != null){
		var url = path+"${userCertificatesPhotoPojo.image3}";
		var _this = $("#imageBox3");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	if("${userCertificatesPhotoPojo.image4}" != null){
		var url = path+"${userCertificatesPhotoPojo.image4}";
		var _this = $("#imageBox4");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	if("${userCertificatesPhotoPojo.image5}" != null){
		var url = path+"${userCertificatesPhotoPojo.image5}";
		var _this = $("#imageBox5");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	if("${userCertificatesPhotoPojo.image8}" != null){
		var url = path+"${userCertificatesPhotoPojo.image8}";
		var _this = $("#imageBox8");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	if("${userCertificatesPhotoPojo.image7}" != null){
		var url = path+"${userCertificatesPhotoPojo.image7}";
		var _this = $("#imageBox7");
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	}
	
	$("#sbutton").click(function(){
		<%-- var v,v1,v2,v3,v4,v5,v6;
		
		if($("input[name='image1']").val() == ""){
			$("#color1").attr("class","red");
			$("#image1_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/error.png' /> 营业执照 为必须项。");
			v1 = false;
		}else{
			$("#color1").attr("class","green");
			$("#image1_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/success.png' /> OK!");
			v1 = true;
		}
		
		if($("input[name='image2']").val() == ""){
			$("#color2").attr("class","red");
			$("#image2_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/error.png' /> 组织机构代码证照 为必须项。");
			v2 = false;
		}else{
			$("#color2").attr("class","green");
			$("#image2_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/success.png' /> OK!");
			v2 = true;
		}
		
		if($("input[name='image3']").val() == ""){
			$("#color3").attr("class","red");
			$("#image3_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/error.png' /> 税务登记证照 为必须项。");
			v3 = false;
		}else{
			$("#color3").attr("class","green");
			$("#image3_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/success.png' /> OK!");
			v3 = true;
		}
		
		if($("input[name='image4']").val() == ""){
			$("#color4").attr("class","red");
			$("#image4_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/error.png' /> 法人身份证正面照 为必须项。");
			v4 = false;
		}else{
			$("#color4").attr("class","green");
			$("#image4_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/success.png' /> OK!");
			v4 = true;
		}
		
		if($("input[name='image5']").val() == ""){
			$("#color5").attr("class","red");
			$("#image5_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/error.png' /> 法人身份证反面照 为必须项。");
			v5 = false;
		}else{
			$("#color5").attr("class","green");
			$("#image5_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/success.png' /> OK!");
			v5 = true;
		}
		if($("input[name='image8']").val() == ""){
			$("#color8").attr("class","red");
			$("#image8_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/error.png' /> 开户许可证 为必须项。");
			v6 = false;
		}else{
			$("#color8").attr("class","green");
			$("#image8_mgId").html("<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/img/success.png' /> OK!");
			v6 = true;
		}
		if(v1 == false || v2 == false || v3 == false || v4 == false || v5 == false || v6 == false){
			v = false;
		}else{
			v = true;
		} --%>
		
		if(tt.validate()/*  && v == true */){
			if(window.confirm("确定要提交修改吗？提交修改需等待管理员审核！")){
				document.getElementById("from1").submit();
			}else{
				
			}
		}
	});
});
</script>