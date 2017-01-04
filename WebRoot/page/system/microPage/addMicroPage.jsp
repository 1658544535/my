<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baidu.ueditor.ActionEnter"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-form.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
</head>

<script>

</script>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>微页面</a> &gt; <a href="">新增微页面</a>
		</div>
	</div>
	<div class="wrap">
		<div class="container" style="width:100%">
			<ul class="nav nav-tabs row" role="tablist">
			    <li role="presentation" class="col-sm-6 text-center active" id="infoLi"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">1.编辑基本信息</a></li>
			    <li role="presentation" class="col-sm-6 text-center " id="albumLi"><a href="#album" aria-controls="album" role="tab" data-toggle="tab">2.编辑专题详情</a></li>
			</ul>
			<div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="info">
					<div>
						<form action="addMicroPage.do" method="post" id="form1" enctype="multipart/form-data">
						<input type="hidden" id="microPageId" name="microPagePojo.id"/>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
								<tr>
									<td align="right" class="grey" width="15%">微页面标题:</td>
									<td width="15%">
									 	<label>
									 		<input type="text" name="microPagePojo.title" id="microPagePojo.title" value="" class="floatLeft">
									 	</label>
									 	<span id="message_title"></span>
									</td>
								</tr>
							</table>
						</form>
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane container-fluid" id="album">
			    	<iframe src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/index.html" id="iframepage" name="iframepage" frameBorder=0 width="100%" onLoad="reinitIframeEND();"id="iframepage" name="iframepage" frameBorder=0 width="100%" onLoad="reinitIframeEND();"></iframe>
			    </div>
			</div>
		</div>
    </div>
	<div class="Btn_div" id="btn-div">
		<button class="back_btn" id="past">上一步</button>
		<input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" />
		<!-- <button type="input" class="btn btn-primary" onclick="window.history.back()" id="back">返回</button> -->
	</div>
</body>
</html>
<script>
$(function(){
	//隐藏上一步按钮
	$("#past").hide();
})
var title =new tt.Field("页面标题 ","microPagePojo.title").setMsgId("message_title");
tt.vf.req.add(title);
</script>
<script> 
var phoneData="";
var url="addMicroPageTemplatePageData.do";
var imgUpfileUrl="microPageImgUpfile.do";
var flag=false;
window.onload = function(){
	 //隐藏不要的控件
	 //$('#iframepage').contents().find(".text").remove();  
	 //$('#iframepage').contents().find(".videos").remove();  
	 //$('#iframepage').contents().find(".images").remove();
	 //监听标签页
	 $("#albumLi").click(function(){
		 //alert(1);
		 if(tt.validate()){
			 //alert(2);
			//判断是否添加了专题基本信息
			 if($("#microPageId").val() == null || $("#microPageId").val() == 0 || $("#microPageId").val() == ""){
				//alert(3);
				//提交表单
	    		$("#form1").ajaxSubmit({
	    			type: 'post', 
	                url: 'addMicroPage.do', 
	                success: function(data) {
	                    if(data != null){
	                    	url="addMicroPageTemplatePageData.do?microPageId="+data;
	                    	$("#microPageId").val(data);
	                    	$("#next").hide();
	                        $("#past").hide();
	                        $("#back").hide();
	                    }else if(data == 0){
	                    	alert("请先登录!");
	                    	return false;
	                    }else{
	                    	alert("添加微页面基本信息失败");
	                    	$("#infoLi").find("a").click();
	                    	return false;
	                    }
	                }
	    		});
			 }else{
				 $("#next").hide();
                 $("#past").hide();
                 //$("#back").hide();
			 }
		 }else{
			 return false;
		 }
	 });
	 //监听上一步
	 $("#infoLi").click(function(){
		$("#past").hide();
		$("#next").show();
		//$("#back").show();
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
</script>