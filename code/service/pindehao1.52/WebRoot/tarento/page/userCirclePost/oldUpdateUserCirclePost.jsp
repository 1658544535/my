<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马达人中心</title>
    <link rel="stylesheet" href="../../seller/css/default.css" media="all" />
    <link rel="stylesheet" href="../../seller/css/seller_common.css" type="text/css" media="all" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
	<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
	
	</head>

<body>
    <jsp:include page="../tarentoHeader.jsp"/>

        <div id="content" class="wrapper">
        <style>
                            .red-packet-tooltip i{ color: #C2C220; margin-right: 10px; padding-left:2px; } .red-packet-tooltip a:hover { text-decoration: none; } .more-packet-info a{ padding: 5px 0px 0 4px; }.upload-img{position:relative;height: 30px; line-height: 30px; width: 120px;background:#f64f61;border-radius:30px;text-align:center;color:#fff;}.upload-img input{position:absolute;top:0;left:0;width:100%;height:100%;opacity:0;filter:alpha(opacity=0);cursor:pointer;}.upload-img-box{margin-top:10px;}.upload-img-box img{display:block;height:100%;width:auto;}
                        </style>
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
                    </style>
                    <h1 class="seller-title">编辑笔记</h1>
                    <form action="updateUCPost.do" id="form1" accept-charset="utf-8" class="ui-form p50 view-MartShowForm clone-MartShowForm" method="post"  enctype="multipart/form-data">
                        <input type="hidden" name="userCirclePostPojo.id" value="${userCirclePostPojo.id }">
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 标题
                                </label>
                                <input type="text" class="ui-input" name="userCirclePostPojo.title" value="${userCirclePostPojo.title}" style="float:left" />
                            	<p><span id="msg_title"></span></p>
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 图片
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify"style="position:relative;height: 120px; width: 120px;">
                                         <p class="uploadPreview_note" style="display:none;"><i class="iconfont">&#xf00f7;</i>更改图片</p>
                                         <div class="uploadPreview_img">
                                            <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userCirclePost/${userCirclePostPojo.banner}" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="banner" />
                                    
                                    </div>
                                    <div id="message1" name="message1" class="talentErrMsg" style="display:none;">
                                      	图片不允许为空
                                    </div>
                                    
                                    <p><span id="msg_banner1"></span></p>
                                </div>
                               
                                <div class="fl picInfo" style="margin-left:50px;display:none;" name="pictureTwoShow" id="pictureTwoShow">
                                    <div class="uploadify"style="position:relative;height: 120px; width: 120px;">
                                         <p class="uploadPreview_note" style="display:none;"><i class="iconfont">&#xf00f7;</i>更改图片</p>
                                         <div class="uploadPreview_img">
                                            <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userCirclePost/${userCirclePostPojo.image}" style="width:120px;height:120px;">
                                        
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image" />
                                    
                                    </div>
                                    <div id="message2" name="message2" class="talentErrMsg" style="display:none;">
                                      	图片不允许为空
                                    </div>
                                    
                                    <p><span id="msg_banner2"></span></p>
                                </div>
                                
                                <div class="fl picInfo" name="pictureTwoAdd" id="pictureTwoAdd" style="margin-left:50px;display:none;">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img src=""  id="bannerIdImage2" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="image" id="bannerId2"  value=""/>
                                    
                                    </div>
                                    <div id="message1" name="message1" class="talentErrMsg" style="display:none;">
                                      	图片不允许为空
                                    </div>
                                    
                                    <p><span id="msg_banner1"></span></p>
                                </div>
                                
                            </div>
                        </div>

                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 内容
                                </label>
                               
                                <textarea rows="10" cols="50" name="userCirclePostPojo.content" style="float:left" >${userCirclePostPojo.content}</textarea>
                            	<p><span id="msg_content"></span></p>
                            </div>
                        </div>
                        
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required"></span> 视频URL
                                </label>
                                <input type="text" class="ui-input" name="userCirclePostPojo.videoUrl" value="${userCirclePostPojo.videoUrl }" style="width:300px"/>
                            	<p><span id="msg_videoUrl"></span></p>
                            </div>
                        </div>
                        
                        <div class="apply-box">
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 所属社圈
                                </label>
                                <select name="userCirclePostPojo.circleId" id="ticketType"  class="ui-input" style="float:left" >
									<c:forEach items="${socialCirclePojoList}" var="unit">
										<option value="${unit.id}" <c:if test="${unit.id==userCirclePostPojo.circleId}">selected="selected" </c:if>>${unit.title}</option>
									</c:forEach>
								</select>
                            	<p><span id="msg_circleId"></span></p>
                            </div>
                        </div>

                        <div class="apply-box boxs-botom">
                            <div class="ui-form-item">
                                <input type="button"  id="sbutton"  style="padding-top: 0px;" class="ui-button ui-button-mred view-SubmitBtn" value="修改保存并提交审核">
                                <input type="button" style="padding-top: 0px;" class="ui-button ui-button-mred view-SubmitBtn" onclick="window.history.back()" value="返回">
                            	<input type="button" id="resetImage" value="重置图片" class="ui-button ui-button-mred view-SubmitBtn">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="../tarentoFooter.jsp"></jsp:include>

    <script src="../../seller/js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../seller/js/base.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script>
    console.log("${userCirclePostPojo.image}" == 0);
    if("${userCirclePostPojo.image}" == 0){
    	document.getElementById('pictureTwoAdd').style.display = "";
    }else{
    	document.getElementById('pictureTwoShow').style.display = "";
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
      
        $(document).ready(function() {
    		$("#sbutton").click(function() {
    			//获取主图信息
    			var oFile1 = document.getElementById('bannerId1');
    			//当标题、主图、内容都不为空时页面提交
    				if (tt.validate()) {
    					//if(oFile1.value.length == 0){
    	    				//document.getElementById('message1').style.display = "";
    	    				//return null;
    	    			//}
    					
        				document.getElementById("form1").submit();
        			}
    				
    			
    		});
    		
    		//重置图片方法
    		$("#resetImage").click(function() {
    			//var obj1 = document.getElementById('bannerId1') ; 
    			//obj1.outerHTML=obj1.outerHTML; 
    			
    			//alert("重置操作只能重置第二张图片,如重置后第一张图片无上传新图片时将使用原有图片。");
    			if (confirm("重置操作只能重置第二张图片,如重置后第一张图片无上传新图片时将使用原有图片,你确定重置吗？")) {  
    				var obj2 = document.getElementById('bannerId2') ; 
        			obj2.outerHTML=obj2.outerHTML; 
        			$(".uploadPreview_note").show();
        			$(".uploadPreview_img").hide(); 
    	        }  
    	        else {  
    	        	
    	        }  
    			console.log("123");
    		});
    	});
        var title =new tt.Field(" 标题","userCirclePostPojo.title").setMsgId("msg_title");
        var content =new tt.Field(" 内容","userCirclePostPojo.content").setMsgId("msg_content");
        //var videoUrl =new tt.Field(" 视频URL","userCirclePostPojo.videoUrl").setMsgId("msg_videoUrl");
        var circleId =new tt.Field(" 所属社圈","userCirclePostPojo.circleId").setMsgId("msg_circleId");
        
        new tt.LV().set(0, 20).add(title);
        new tt.LV().set(0, 140).add(content);
        tt.vf.req.add(title,content,circleId);
        </script>
</body>

</html>
