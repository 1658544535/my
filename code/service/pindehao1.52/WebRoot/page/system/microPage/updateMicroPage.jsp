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
			<a>微页面管理</a> &gt; <a href="">编辑微页面</a>
		</div>
	</div>
	<div class="wrap">
		<div class="container" style="width:100%">
			<ul class="nav nav-tabs row" role="tablist">
			    <li role="presentation" class="col-sm-6 text-center active" id="infoLi"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">1.编辑基本信息</a></li>
			    <li role="presentation" class="col-sm-6 text-center " id="albumLi"><a href="#album" aria-controls="album" role="tab" data-toggle="tab">2.编辑详情</a></li>
			</ul>
			<div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="info">
					<div>
						<form action="updateMicroPage.do" method="post" id="form1" enctype="multipart/form-data">
							<input type="hidden" name="microPagePojo.id" value="${microPagePojo.id}" class="floatLeft" />
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
								<tr>
									<td align="right" class="grey" width="10%">专题标题:</td>
									<td width="20%">
									 	<label>
									 		<input type="text" name="microPagePojo.title" id="microPagePojo.title" value="${microPagePojo.title}" class="floatLeft">
									 	</label>
									 	<span id="message_title"></span>
									</td>
								</tr>
							</table>
						</form>
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane container-fluid" id="album">
			    	<iframe src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/index.html" id="iframepage" name="iframepage" frameBorder=0 width="100%" onLoad="reinitIframeEND();"></iframe>
			    </div>
			</div>
		</div>
    </div>
	<div class="Btn_div">
		<input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" />
	</div>
</body>
</html>
<script>
var title =new tt.Field("专题标题 ","microPagePojo.title").setMsgId("message_title");
</script>
<script type="text/javascript" language="javascript"> 
var phoneData=${templatePageDataPojo.data};
var url="updateMicroPageTemplatePageData.do";
var imgUpfileUrl="microPageImgUpfile.do";
window.onload = function(){
	//console.log(phoneData);
	 //$('#iframepage').contents().find(".text").hide();  
	 //$('#iframepage').contents().find(".videos").hide();  
	 //$('#iframepage').contents().find(".images").hide();
	 //监听标签页
	 $("#albumLi").click(function(){
		 //alert(1);
		 if(tt.validate()){
			//alert(3);
			//提交表单
    		$("#form1").ajaxSubmit({
    			type: 'post', 
                url: 'updateMicroPage.do', 
                success: function(microPageId) {
                    if(microPageId != null){
                    	url="updateMicroPageTemplatePageData.do?microPageId="+microPageId+"&dataId="+"${templatePageDataPojo.id}";
	                    $("#next").hide();
                    } else {
                    	alert("修改基本信息时发生错误!");
                    	$("#infoLi").find("a").click();
                    	return false;
                    }
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