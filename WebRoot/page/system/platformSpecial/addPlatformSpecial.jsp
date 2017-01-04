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
//-------------四级联动开始----------------	

//全局变量
var ageValuejs;  
var skillValuejs;

//第二个下拉框事件
function setSecond(obj){  
  var val = obj.value;
  ageValuejs = obj.value;

  $("#second").empty();
  select2(val);
  selectYourLike(val);
}  

function select2(val) {
  $.ajax(
  {
      type: "get",
      url: "getSkillTypes.do?ageId="+val ,
      dataType: 'json',
      success: function (msg) {
      	var o_msg = eval(msg);
      	$("#second").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
          }
      }
  })
};

//第三个下拉框事件
function setThree(obj){  
  var val = obj.value;
  skillValuejs = val;
  $("#three").empty();
  selectThree(val);
}  

function selectThree(val) {
  //alert("quan ju :"+ageValuejs);
  $.ajax(
  {
      type: "get",
      url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
      dataType: 'json',
      success: function (msg) {
      	var o_msg = eval(msg);
      	$("#three").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#three").append("<option value=" + o_msg[i].secondSkillValue + selStr +">" + o_msg[i].secondSkillName + "</option>");
          }
      }
  })
};

//第四个下拉框事件
function setFour(obj){  
  var val = obj.value;
  $("#four").empty();
  selectFour(val);
   
}  

function selectFour(val) {
  //alert("quan ju :"+skillValuejs);
  $.ajax(
  {
      type: "get",
      url: "getProductTypes.do?secondSkillValue="+val+"&ageValue="+ageValuejs+"&skillValue="+skillValuejs,
      dataType: 'json',
      success: function (msg) {
      	var o_msg = eval(msg);
      	$("#four").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#four").append("<option value=" + o_msg[i].productId + selStr +">" + o_msg[i].productName + "</option>");
          }
      }
  })
};
//----------四级联动结束-------------------
//根据年龄查询有你喜欢标签
function selectYourLike(val) {
  $.ajax(
  {
      type: "get",
      url: "getYourFavouritesLable.do?id="+val,
      dataType: 'json',
      success: function (msg) {
      	var o_msg = eval(msg.result);
      	$("#yourFavouritesId").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#yourFavouritesId").append("<option value=" + o_msg[i].id +">" + o_msg[i].name + "</option>");
          }
      }
  })
};

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
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>专题管理</a> &gt; <a href="">专题发布</a>
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
						<form action="addPlatformSpecialList.do" method="post" id="form1" enctype="multipart/form-data">
						<input type="hidden" id="version" name="platformSpecialPojo.version" value="1"/>
						<input type="hidden" id="specialId" name="platformSpecialPojo.id"/>
						<input type="hidden" name="platformSpecialPojo.createBy" value="${sessionScope.loginPojoId}">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
								<tr>
									<td align="right" class="grey" width="15%">专题标题:</td>
									<td width="15%">
									 	<label>
									 		<input type="text" name="platformSpecialPojo.title" id="platformSpecialPojo.title" value="" class="floatLeft">
									 	</label>
									 	<span id="message_title"></span>
									</td>
									<td align="right" class="grey" width="15%">评分:</td>
									<td width="15%">
									 	<label>
									 		<input type="text" name="platformSpecialPojo.score" id="platformSpecialPojo.score" value="" class="floatLeft">
									 	</label>
									 	<span id="message_score"></span>
									</td>
									<td align="right" class="grey" width="15%" rowspan="3">专题主图:</td>
									<td width="35%" rowspan="3">
									 	<!--<img src="http://www.taozhuma.com/upfiles/product/small/20151222020606507233.jpg" width="100%" height="100px">-->
									 	<div class="uploadify main_img"style="position:relative;height: 120px; width: 360px;">
			                                        <button class="uploadPreview_note"style="width:360px;height:120px;line-height:120px;">
			                                        	添加图片</button>
			                                        <div class="uploadPreview_img"style="display:none;"> <img style="width:360px;height:120px;"/></div>
			                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
			                                       </br><span id="message_img"></span></span>
			                                       </div>
									 	
									</td>
									
								<tr>
									<td align="right" class="grey" width="15%">年龄:</td>
									<td width="15%">
										<select id="ageType" name="platformSpecialPojo.ageType" class="floatLeft" onchange="setSecond(this)">
											<option value="">----请选择----</option>
											<s:iterator value="ageTypeList"> 
											<option value="<s:property value='value'/>"><s:property value='name'/> </option>
			            
			        						</s:iterator>
			        					</select>
										<span id="message_ageType"></span>
									</td>
									<td align="right" class="grey" width="15%">能力:</td>
									<td width="15%">
										<select id="second" name="platformSpecialPojo.skillType" class="floatLeft" onchange="setThree(this)"></select>
										<span id="message_skillType"></span>
									</td>
			
								 </tr>
								 
								 <tr>
									<td align="right" class="grey" width="15%">次能力:</td>
									<td>
										<select id="three" name="platformSpecialPojo.secSkillType" class="floatLeft"  onchange="setFour(this)"></select>
										<span id="message_secSkillType"></span>
									</td>
									<td align="right" class="grey" width="15%">商品:</td>
									<td>
									<select id="four" name="platformSpecialPojo.productType" class="floatLeft"></select>
									<span id="message_productType"></span>
									</td>
			
								 </tr>
								 
								 <tr>
									<td align="right" class="grey" width="15%">专题类型:</td>
									<td><select name="platformSpecialPojo.type" id="platformSpecialPojo.type"  class="floatLeft" >
										<option value="">----请选择----</option>
										<option value="1">普通专题</option>
										<option value="2">首页专题</option>
										<option value="3">热门专题</option>
										<option value="4">经典专题</option>
							    		</select>
							    		<span id="message_type"></span>
							    	</td>
									<!-- 
									<td align="right" class="grey" width="15%">备用标签:</td>
									<td width="15%">
										<select name="platformSpecialPojo.optionType" id="optionType"  class="floatLeft">
											<option value="">----请选择----</option>
											<s:iterator value="labelList"> 
											<option value="<s:property value='id'/>"><s:property value='name'/> </option>
											</s:iterator>
			        					</select>
										<span id="message_optionType"></span>
									</td>
									 -->
									<td align="right" class="grey" width="15%">有你喜欢:</td>
									<td width="15%">
										<select id="yourFavouritesId" name="platformSpecialPojo.yourFavouritesId" class="floatLeft"></select>
										<span id="message_yourFavouritesId"></span>
									</td>
									<td align="right" class="grey">专题类别：</td>
									<td ><select name="platformSpecialPojo.specialCategories" id="platformSpecialPojo.specialCategories"  class="floatLeft" >
										<option value="">----请选择----</option>
										<option value="1">软文专题</option>
										<option value="2">商品专题</option>
							    		</select>
							    		<span id="message_specialCategories"></span>
							    	</td>
								 </tr>
								  <!-- <tr>
								  <td align="right" class="grey" width="15%">内容:</td>
									<td width="15%" colspan="54">
									<textarea rows="5" cols="40" name="platformSpecialPojo.content" class="floatLeft" id="content"></textarea>
									<script type="text/javascript">
										var ue = UE.getEditor("content");
									</script>
									</td>
								</tr> -->
								<tr>
    	<td align="right" class="grey">简述：</td>
		<td>
			<textarea rows="5" cols="50" name="platformSpecialPojo.sketch" class="floatLeft"></textarea>
			<div id="sketch_msgId"></div>
		</td>
	</tr>
							</table>
						</form>
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane container-fluid" id="album">
			    	<iframe src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/tzmeditor/index.html" id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();"id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();"></iframe>
			    </div>
			</div>
		</div>
    </div>
	<div class="Btn_div" id="btn-div">
		<input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" />
		<!-- <button type="input" class="btn btn-primary" onclick="window.history.back()" id="back">返回</button> -->
	</div>
</body>
</html>
<script>
	/* $(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	}); */
	var title           =new tt.Field("专题标题 ","platformSpecialPojo.title").setMsgId("message_title");
	var img         	=new tt.Field("专题主图 ","platformSpecialPojo.banner").setMsgId("message_img");
	var ageType         =new tt.Field("年龄 ","platformSpecialPojo.ageType").setMsgId("message_ageType");  
	var skillType       =new tt.Field("能力 ","platformSpecialPojo.skillType").setMsgId("message_skillType");
	//var optionType      =new tt.Field("品类 ","platformSpecialPojo.optionType").setMsgId("message_optionType");
	var productType     =new tt.Field("商品 ","platformSpecialPojo.productType").setMsgId("message_productType");
	var type     		=new tt.Field("专题类型 ","platformSpecialPojo.type").setMsgId("message_type");
	var secSkillType	=new tt.Field("次能力 ","platformSpecialPojo.secSkillType").setMsgId("message_secSkillType");
	var specialCategories	=new tt.Field("类别 ","platformSpecialPojo.specialCategories").setMsgId("message_specialCategories");
	var yourFavouritesId	=new tt.Field("类别 ","platformSpecialPojo.yourFavouritesId").setMsgId("message_yourFavouritesId");
	var score	=new tt.Field("评分","platformSpecialPojo.score").setMsgId("message_score");
	var v_sketch   = new tt.Field("简述 ", "platformSpecialPojo.sketch").setMsgId("sketch_msgId");
	tt.vf.req.add(title,img,ageType,skillType,productType,type,secSkillType,specialCategories,score,yourFavouritesId,v_sketch);
	tt.vf.num.add(score);
	new tt.NRV().set(0, 100).add(score);
	tt.vf.int.add(score);
	//new tt.LV().set(0, 50).add(message_title);
</script>
<script> 
var phoneData="";
var url="addSpecialTemplatePageData.do";
var imgUpfileUrl="specialImgUpfile.do";
var flag=false;
window.onload = function(){
	 //$('#iframepage').contents().find(".text").remove();  
	 //$('#iframepage').contents().find(".videos").remove();
	 //监听标签页
	 $("#albumLi").click(function(){
		 //alert(1);
		 if(tt.validate()){
			 //alert(2);
			//判断是否添加了专题基本信息
			 if($("#specialId").val() == null || $("#specialId").val() == 0 || $("#specialId").val() == ""){
				//alert(3);
				//提交表单
	    		$("#form1").ajaxSubmit({
	    			type: 'post', 
	                url: 'addPlatformSpecialList.do', 
	                success: function(data) {
	                    if(data != null){
	                    	url="addSpecialTemplatePageData.do?specialId="+data+"&pagetype=1";
	                    	$("#specialId").val(data);
	                    	$("#next").hide();
	                        $("#past").hide();
	                       // $("#back").hide();
	                    }else{
	                    	alert("添加基本信息失败!");
	                    	$("#infoLi").find("a").click();
	                    	return false;
	                    }
	                }
	    		});
			 }else{
				 //alert(4);
				 $("#next").hide();
			 }
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