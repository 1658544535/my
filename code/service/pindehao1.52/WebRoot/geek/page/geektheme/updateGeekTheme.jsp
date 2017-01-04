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
    <title>淘竹马创客中心</title>
	<script src="../../seller/js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../geek/js/base.js" type="text/javascript" charset="utf-8"></script>
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
	<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/tzmeditor/js/bootstrap.min.js"></script>
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/css/bootstrap.css" />
    <link rel="stylesheet" href="../../geek/css/default.css" media="all" />
    <link rel="stylesheet" href="../../geek/css/seller_common.css" type="text/css" media="all" />
	<style>
        .red-packet-tooltip i{ color: #C2C220; margin-right: 10px; padding-left:2px; } .red-packet-tooltip a:hover { text-decoration: none; } .more-packet-info a{ padding: 5px 0px 0 4px; }.upload-img{position:relative;height: 30px; line-height: 30px; width: 120px;background:#f64f61;border-radius:30px;text-align:center;color:#fff;}.upload-img input{position:absolute;top:0;left:0;width:100%;height:100%;opacity:0;filter:alpha(opacity=0);cursor:pointer;}.upload-img-box{margin-top:10px;}.upload-img-box img{display:block;height:100%;width:auto;}
        .uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
	    .submit-group{clear:both;padding:30px 0 60px;text-align:center;border-top:1px solid #ddd;}
    </style>
    <script>
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
	</head>

<body>
    <jsp:include page="../geekHeader.jsp"/>
    <div class="wrap" style="background: #fff;">
	    <h1 class="seller-title">专题发布</h1>
		<div class="container" style="width:100%">
			<ul class="nav nav-tabs row" role="tablist">
			    <li role="presentation" class="col-sm-6 text-center active" id="infoLi"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">1.编辑基本信息</a></li>
			    <li role="presentation" class="col-sm-6 text-center " id="albumLi"><a href="#album" aria-controls="album" role="tab" data-toggle="tab">2.编辑专题详情</a></li>
			</ul>
			<div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="info">
					<div>
						<form action="updateGeekTheme.do" id="form1" accept-charset="utf-8" class="ui-form p50 view-MartShowForm clone-MartShowForm" method="post"  enctype="multipart/form-data">
	                        <input type="hidden" name="userMakerThemePojo.id" value="${userMakerThemePojo.id }">
	                        <div class="apply-box">
	                            <div class="ui-form-item">
	                                <label for="" class="ui-label">
	                                    <span class="ui-form-required">*</span> 标题
	                                </label>
	                                <input type="text" class="ui-input" name="userMakerThemePojo.specialName" value="${userMakerThemePojo.specialName }"/>
	                            	<p><span id="msg_specialName"></span></p>
	                            </div>
	                        </div>
	
	                        <div class="apply-box">
	                            <div class="ui-form-item">
	                                <label for="" class="ui-label">
	                                    <span class="ui-form-required">*</span> 专题主图
	                                </label>
	                                <div class="fl picInfo">
	                                    <div class="uploadify"style="position:relative;height: 140px; width: 140px;">
	                                         <p class="uploadPreview_note" style="display:none;"><i class="iconfont">&#xf00f7;</i>更改图片</p>
	                                         <div class="uploadPreview_img">
	                                            <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userMakerTheme/${userMakerThemePojo.banner}" style="width:120px;height:120px;">
	                                        </div>
	                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="banner" />
	                                    
	                                    </div>
	                                    <div id="message" name="message" class="talentErrMsg" style="display:none;">
	                                      	主图不允许为空
	                                    </div>
	                                </div>
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
		<div class="submit-group clearfix" id="submit">
			<div style="text-align:center;"><input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" /></div>
			<!-- <button type="input" class="btn btn-primary" onclick="window.history.back()" id="back">返回</button> -->
   		</div>
    </div>
    
    <jsp:include page="../geekFooter.jsp"></jsp:include>


<script>
	var specialName =new tt.Field(" 标题","userMakerThemePojo.specialName").setMsgId("msg_specialName");	
	new tt.LV().set(0, 40).add(specialName);
	tt.vf.req.add(specialName);
</script>
<script type="text/javascript" language="javascript"> 
var phoneData=${templatePageDataPojo.data};
var url="updateGeekThemeTemplatePageData.do";
var imgUpfileUrl="specialImgUpfile.do";
var userFlag="1";
window.onload = function(){
	//console.log(phoneData);
// 	$('#iframepage').contents().find(".text").remove();  
// 	$('#iframepage').contents().find(".videos").remove();
// 	$('#iframepage').contents().find(".images").remove();
// 	$('#iframepage').contents().find(".coupon").remove();
	 //监听标签页
	 $("#albumLi").click(function(){
		 //alert(1);
		 if(tt.validate()){
			//alert(3);
			//提交表单
    		$("#form1").ajaxSubmit({
    			type: 'post', 
                url: 'updateGeekTheme.do', 
                success: function(geekThemeId) {
                    if(geekThemeId != null){
                    	url="updateGeekThemeTemplatePageData.do?geekThemeId="+geekThemeId+"&dataId="+"${templatePageDataPojo.id}";
	                    $("#next").hide();
                    } else {
                    	alert("修改基本信息时发生错误!");
                    	$("#infoLi").find("a").click();
                    	return false;
                    }
                },
                error:function(){
                	alert("修改错误!");
                }
    		});
		 }else{
			 //alert(5);
			 return false;
		 }
	 });
	 //监听上一步
	 $("#infoLi").click(function(){
		$("#next").show();
	 });
	 
	 //点击提交按钮
	 $('.sbutton').click(function() {
		//alert(11);
    	if (tt.validate()) {
            $("#albumLi").find("a").click();
    	}
	 });
}
</script>
</body>

</html>
