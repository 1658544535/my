<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>


<body>
    <jsp:include page="../geekHeader.jsp"/>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
                    </style>
                    <h1 class="seller-title">品牌管理</h1>
                    <form action="addGeekBrand.do" accept-charset="utf-8" class="ui-form p50"  method="post" enctype="multipart/form-data" id="from1">
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 品牌名称
                                </label>
                                <input class="ui-input" name="userMakerBrandPojo.brandName" type="text" value="" />
                                </br><span id="brandName_msgId"></span></span>
                            </div>
                            
                        </div>
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 主营类目
                                </label>	
                               <select name="userMakerBrandPojo.mainCategory" id="ticketType"  class="floatLeft">
                               <option value="">--请选择--</option>
							    <c:forEach items="${productTypePojos}" var="mainCategory">
								<option value="${mainCategory.id}">${mainCategory.name}</option>
							    </c:forEach>
				               </select>
				                </br><span id="mainCategory_msgId"></span></span>
				               </div>
				        </div>
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 品牌LOGO
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        	添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="logo" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="message_img"></span></span>
                                       
                                       
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 商标注册证明
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        	添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> 
                                        	<img style="width:120px;height:120px;"/>
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="registrationCertificate" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="message_img"></span></span>
                                       
                                        
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 内容
                                </label>
                                <!--编辑器-->
                                <script id="editor" type="text/plain" style="width:708px;height:400px;" name="userMakerBrandPojo.content"></script>
                            </div>
                        </div>

                        <div class="apply-box boxs-botom">
                            <div class="ui-form-item">
                                <input type="button" style="padding-top: 0px;" class="ui-button ui-button-mred view-SubmitBtn" value="修改保存并提交审核" id="sbutton">
                                 <button type="input" class="ui-button ui-button-mred view-SubmitBtn" onclick="window.history.back()">返回</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="../geekFooter.jsp"/>

    <script src="../../seller/js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../geek/js/base.js" type="text/javascript" charset="utf-8"></script>


    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script>
        $(function(){
            //编辑器
            var ue = UE.getEditor('editor');
        });
    </script>
    <script type="text/javascript">
	var brandName = new tt.Field(" 品牌名称 ", "userMakerBrandPojo.brandName").setMsgId("brandName_msgId");
	var mainCategory = new tt.Field(" 主营类目 ", "userMakerBrandPojo.mainCategory").setMsgId("mainCategory_msgId");
	tt.vf.req.add(brandName,mainCategory);			
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
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
</script>
</body>

</html>
