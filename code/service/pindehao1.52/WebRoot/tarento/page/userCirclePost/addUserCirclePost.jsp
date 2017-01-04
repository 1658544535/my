<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马达人中心</title>
    <script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/js/jquery-1.11.2.min.js"></script>
    <link rel="stylesheet" href="../../seller/css/default.css" media="all" />
    <link rel="stylesheet" href="../../seller/css/seller_common.css" type="text/css" media="all" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
	<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-form.js"></script>
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
</head>

<body>
    <jsp:include page="../tarentoHeader.jsp"/>
    
    <div class="wrap">
    	<div class="container" style="width:65%">
                <div class="pure-u-1 main">
                    <h1 class="seller-title">笔记管理/发布笔记</h1>
                </div>
        </div>
        
		<div class="container" style="width:65%">
			<ul class="nav nav-tabs row" role="tablist">
			    <li role="presentation" class="col-sm-6 text-center active" id="infoLi"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">1.编辑基本信息</a></li>
			    <li role="presentation" class="col-sm-6 text-center " id="albumLi"><a href="#album" aria-controls="album" role="tab" data-toggle="tab">2.编辑笔记详情</a></li>
			</ul>
			<div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="info">
					<div>
					<form action="addUCPost.do" id="form1" accept-charset="utf-8" class="ui-form p50 view-MartShowForm clone-MartShowForm" method="post"  enctype="multipart/form-data">
					<input type="hidden" id="version" name="userCirclePostPojo.version" value="1"/>
					<input type="hidden" id="specialId" name="userCirclePostPojo.id" value=""/>
                    <div class="apply-box">
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                <span class="ui-form-required">*</span> 社圈
                            </label>
                            <select name="userCirclePostPojo.circleId" id="ticketType"  class="ui-input" style="float:left">
                            	<option value="">-----请选择社圈-----</option>
								<c:forEach items="${socialCirclePojoList}" var="unit">
								<option value="${unit.id}" >${unit.title}</option>
								</c:forEach>
							</select>
                        	<p><span id="msg_circleId"></span></p>
                        </div>
                    </div>
                    
                    <div class="apply-box">
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                <span class="ui-form-required">*</span> 笔记标题
                            </label>
                            <input type="text" class="ui-input" style="float:left" name="userCirclePostPojo.title"/>
                        	<p><span id="msg_title"></span></p>
                        </div>
                    </div>
                    
                    <div class="apply-box">
	                    <style>
	                    .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
	                    </style>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                <span class="ui-form-required">*</span> 图片
                            </label>
                            <div class="fl picInfo">
                                <div class="uploadify">
                                    <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                    <div class="uploadPreview_img" style="display:none;">
                                        <img src="" id="bannerIdImage1" style="width:120px;height:120px;">
                                    </div>
                                    <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="banner" id="bannerId1" value=""/>
                                
                                </div>
                                <div id="message1" name="message1" class="talentErrMsg" style="display:none;">
                                  	图片不允许为空
                                </div>
                                
                                <p><span id="msg_banner1"></span></p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="apply-box boxs-botom">
                        <div class="ui-form-item">
                        	<input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" />
                        </div>
                    </div>
                	</form>
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane container-fluid" id="album">
			    	<iframe src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/index.html" id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();"id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();"></iframe>
			    </div>
			</div>
		</div>
	</div>
	
    <jsp:include page="../tarentoFooter.jsp"></jsp:include>
    
    <script src="../../seller/js/base.js" type="text/javascript" charset="utf-8"></script>
    
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script>
        $(document).ready(function() {
        	$("#albumLi").click();
    	});
        var circleId =new tt.Field(" 社圈","userCirclePostPojo.circleId").setMsgId("msg_circleId");
        var title =new tt.Field(" 笔记标题","userCirclePostPojo.title").setMsgId("msg_title");
        
        tt.vf.req.add(circleId,title);
        new tt.LV().set(0, 20).add(title);
	</script>
</body>
</html>
<script type="text/javascript" language="javascript">     
//调整js窗口
function reinitIframe(){  
var iframe = document.getElementById("iframepage");  
try{  
    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    var height = Math.max(bHeight, dHeight);  
    iframe.height = height;  
}catch (ex){}  
}  
  
var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
  
function reinitIframeEND(){  
var iframe = document.getElementById("iframepage");  
try{  
    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    var height = Math.max(bHeight, dHeight);  
    iframe.height = height;  
}catch (ex){}  
// 停止定时  
window.clearInterval(timer1);  
}  
</script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-form.js"></script>
<script> 
var phoneData="";
var url="addSpecialTemplatePageData.do";
var imgUpfileUrl="postImgUpfile.do";
var flag=false;
window.onload = function(){
	 //监听标签页
	 $("#albumLi").click(function(){
		 if(tt.validate() && oFile1.value.length != 0){
			 document.getElementById('message1').style.display = "none";
			//判断是否添加了专题基本信息
			 if($("#specialId").val() == null || $("#specialId").val() == 0 || $("#specialId").val() == ""){
				//提交表单
				 $("#form1").ajaxSubmit({
		    			type: 'post', 
		                url: 'addUCPost.do', 
		                success: function(data) {
		                    if(data != null){
		                    	alert("基本信息保存成功！~请填写详情信息！~");
		                    	url="addSpecialTemplatePageDataTarento.do?specialId="+data+"&pagetype=3";
		                    	$("#specialId").val(data);
		                    	$("#next").hide();
		                    }else{
		                    	alert("添加基本信息失败!");
		                    	$("#infoLi").find("a").click();
		                    	return false;
		                    }
		                }
		    		});
			 }else{
				 $("#next").hide();
			 }
		 }else{
			 document.getElementById('message1').style.display = "";
			 return false;
		 }
	 });
	 //监听上一步
	 $("#infoLi").click(function(){
		$("#next").show();
	 });
	 
	//获取主图信息
	var oFile1 = document.getElementById('bannerId1');
	//当标题、主图、内容都不为空时页面提交
	
	 //点击提交按钮
	 $('.sbutton').click(function() {
		if (tt.validate() && oFile1.value.length != 0) {
			document.getElementById('message1').style.display = "none";
			$("#albumLi").find("a").click();
    	}else{
    		document.getElementById('message1').style.display = "";
    	}
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
}
</script>