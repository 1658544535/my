<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/css/default.css" type="text/css" media="all" />
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">拼得客-用户信息表管理</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addUserPindekeInfo.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">用户账号：</td>
					<td><input type="text" name="userPindekeInfoPojo.loginname" id="userPindekeInfoPojo.loginname" value="" /></td>
					<td><span id="userId_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">真实姓名：</td>
					<td><input type="text" name="userPindekeInfoPojo.name" id="userPindekeInfoPojo.name" value="" /></td>
					<td><span id="name_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">手机号码：</td>
					<td><input type="text" name="userPindekeInfoPojo.phone" id="userPindekeInfoPojo.phone" value="" /></td>
					<td><span id="phone_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">身份证号码：</td>
					<td><input type="text" name="userPindekeInfoPojo.cardNo" id="userPindekeInfoPojo.cardNo" value="" /></td>
					<td><span id="cardNo_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">推广渠道：</td>
					<td>
					<input type="text" name="userPindekeInfoPojo.extendChannel" id="userPindekeInfoPojo.extendChannel" value="" />
					</td>
					<td><span id="extendChannel_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">推广证明（图片1）：</td>
					<td>
					<!--<input type="text" name="userPindekeInfoPojo.extendImg1" id="userPindekeInfoPojo.extendImg1" value="" />-->
					<div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile1" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（400 * 400）</font>

推广证明图片

a. 尺寸400 x 400px

b. 大小100k
						</p>
					</td>
					<td><span id="extendImg1_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">推广证明（图片2）：</td>
					<td>
					<div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile2" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（400 * 400）</font>

推广证明图片

a. 尺寸400 x 400px

b. 大小100k
						</p>
					</td>
					<td><span id="extendImg2_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">推广证明（图片3）：</td>
					<td><div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile3" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（400 * 400）</font>

推广证明图片

a. 尺寸400 x 400px

b. 大小100k
						</p></td>
					<td><span id="extendImg3_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">推广证明（图片4）：</td>
					<td><div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile4" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（400 * 400）</font>

推广证明图片

a. 尺寸400 x 400px

b. 大小100k
						</p></td>
					<td><span id="extendImg4_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">推广证明（图片5）：</td>
					<td><div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                                        <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;">
                                        <i class="iconfont">&#xf00f7;</i>添加图片</button>
                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile5" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（400 * 400）</font>

推广证明图片

a. 尺寸400 x 400px

b. 大小100k
						</p></td>
					<td><span id="extendImg5_mgId"></span></td>
				</tr>
			</table> 
		</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
		</div>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
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