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
$(function(){
	if("${platformSpecialPojo.banner}" == ''){
		style="display: none;"  
		document.getElementById("imgs").style.display="none";//隐藏  
			  
		document.getElementById("uploadPreview_note").style.display="";//显示 
	}
	select21("${platformSpecialPojo.ageType}");
	selectThree1("${platformSpecialPojo.skillType}");
	selectFour1("${platformSpecialPojo.secSkillType}");
	selectYourLike1("${platformSpecialPojo.ageType}");
});

function select21(val) {
	var ability = "${taskPojo.ability}";
	$("#second").html("<option value=''>----请选择----</option>");
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val ,
        dataType: 'json',
        success: function (msg) {
        	var o_msg = eval(msg);
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            	if(o_msg[i].skillValue == "${platformSpecialPojo.skillType}"){
            		selStr = " selected='selected' ";
            	}
                $("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
            }
        }
    })
};

function selectThree1(val) {
	  $("#three").html("<option value=''>----请选择----</option>");
	  $.ajax(
	  {
	      type: "get",
	      url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
	      dataType: 'json',
	      success: function (msg) {
	      	var o_msg = eval(msg);
	          for (var i = 0; i < o_msg.length; i++) {
	          	var selStr = "";

	          	if(o_msg[i].secondSkillValue == "${platformSpecialPojo.secSkillType}"){
	        		selStr = " selected='selected' ";
	        	}
	              $("#three").append("<option value=" + o_msg[i].secondSkillValue + selStr +">" + o_msg[i].secondSkillName + "</option>");
	          }
	      }
	  })
	};
	
	function selectFour1(val) {
	  $("#four").html("<option value=''>----请选择----</option>");
	  $.ajax(
	  {
	      type: "get",
	      url: "getProductTypes.do?secondSkillValue="+val+"&ageValue="+ageValuejs+"&skillValue="+skillValuejs,
	      dataType: 'json',
	      success: function (msg) {
	      	var o_msg = eval(msg);
	          for (var i = 0; i < o_msg.length; i++) {
	          	var selStr = "";
	          	if(o_msg[i].productId == "${platformSpecialPojo.productType}"){
	        		selStr = " selected='selected' ";
	        	}
	              $("#four").append("<option value=" + o_msg[i].productId + selStr +">" + o_msg[i].productName + "</option>");
	          }
	      }
	  })
	};
	//初始化有你喜欢
	function selectYourLike1(val) {
	  $("#yourFavouritesId").html("<option value=''>----请选择----</option>");
	  $.ajax(
	  {
	      type: "get",
	      url: "getYourFavouritesLable.do?id="+val,
	      dataType: 'json',
	      success: function (msg) {
	      	var o_msg = eval(msg.result);
	          for (var i = 0; i < o_msg.length; i++) {
	          	var selStr = "";
	          	if(o_msg[i].id == "${platformSpecialPojo.yourFavouritesId}"){
	        		selStr = " selected='selected' ";
	        	}
	              $("#yourFavouritesId").append("<option value=" + o_msg[i].id + selStr +">" + o_msg[i].name + "</option>");
	          }
	      }
	  })
	};
//-------------四级联动开始----------------	
//根据年龄查询有你喜欢标签
function selectYourLike(val) {
  $("#yourFavouritesId").html("");
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
//全局变量
var ageValuejs = "${platformSpecialPojo.ageType}";  
var skillValuejs = "${platformSpecialPojo.skillType}";

//第二个下拉框事件
function setSecond(obj){  
    var val = obj.value;
    ageValuejs = obj.value;
    
    $("#three").html("<option value=''>----请选择----</option>");
    $("#four").html("<option value=''>----请选择----</option>");
    select2(val);
    selectYourLike(val);
}  

function select2(val) {
	var ability = "${taskPojo.ability}";
	$("#second").html("<option value=''>----请选择----</option>");
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val ,
        dataType: 'json',
        success: function (msg) {
        	//console.log(msg);
        	var o_msg = eval(msg);
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            	//alert(o_msg[i].skillValue);
            	//alert(ability);
            	//if(o_msg[i].skillValue == ${sysDic.value}){
            	//	selStr = " selected='selected' ";
            	//}
                $("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
            }
        }
    })
};
//第三个下拉框事件
function setThree(obj){  
  var val = obj.value;
  skillValuejs = val;
  $("#four").html("<option value=''>----请选择----</option>");
  selectThree(val);
   
}  

function selectThree(val) {
  $("#three").html("<option value=''>----请选择----</option>");
  $.ajax(
  {
      type: "get",
      url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
      dataType: 'json',
      success: function (msg) {
      	//console.log(msg);
      	var o_msg = eval(msg);
      	
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";

          	//if(o_msg[i].secondSkillValue == ${sysDictory.value}){
        	//	selStr = " selected='selected' ";
        	//}
              $("#three").append("<option value=" + o_msg[i].secondSkillValue +" " + selStr +">" + o_msg[i].secondSkillName + "</option>");
          }
      }
  })
};

//第四个下拉框事件
function setFour(obj){  
  var val = obj.value;
  
  selectFour(val);
   
}  

function selectFour(val) {
  $("#four").html("<option value=''>----请选择----</option>");
  $.ajax(
  {
      type: "get",
      url: "getProductTypes.do?secondSkillValue="+val+"&ageValue="+ageValuejs+"&skillValue="+skillValuejs,
      dataType: 'json',
      success: function (msg) {
      	//console.log(msg);
      	var o_msg = eval(msg);
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
          //	if(o_msg[i].productId == ${platformSpecialPojo.productType}){
        	//	selStr = " selected='selected' ";
        	//}
              $("#four").append("<option value=" + o_msg[i].productId + selStr +">" + o_msg[i].productName + "</option>");
          }
      }
  })
};
//----------四级联动结束-------------------
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
	document.getElementById("uploadPreview_note").style.display="none";//隐藏 
	document.getElementById("imgs").style.display="";//显示
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
						<form action="updatePlatformSpecial.do" method="post" id="form1" enctype="multipart/form-data">
			<input type="hidden" name="platformSpecialPojo.id" value="${platformSpecialPojo.id}" class="floatLeft" />
			<input type="hidden" name="platformSpecialPojo.createBy" value="${sessionScope.loginPojoId}">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					
					<tr>
						<td align="right" class="grey" width="10%">专题标题:</td>
						<td width="20%">
						 	<label>
						 		<input type="text" name="platformSpecialPojo.title" id="platformSpecialPojo.title" value="${platformSpecialPojo.title}" class="floatLeft">
						 	</label>
						 	<span id="message_title"></span>
						</td>
						<td align="right" class="grey" width="10%">评分:</td>
						<td width="20%">
						 	<label>
						 		<input type="text" name="platformSpecialPojo.score" id="platformSpecialPojo.score" value="${platformSpecialPojo.score}" class="floatLeft">
						 	</label>
						 	<span id="message_score"></span>
						</td>
						<td align="right" class="grey" width="10%" rowspan="3">专题主图:</td>
						<td width="30%" rowspan="3">
						 	<div class="uploadify main_img"style="position:relative;height: 120px; width: 360px;">
                                        <!-- <button class="uploadPreview_note"style="width:360px;height:120px;line-height:120px;display:none;">
                                        	添加图片</button> -->
                                        <p class="uploadPreview_note"  id="uploadPreview_note" style="width:360px;height:120px;line-height:120px;display:none;"><i class="iconfont">&#xf00f7;</i>暂无图片</p>
                                        <div class="uploadPreview_img"> 
											<img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/platformSpecial/${platformSpecialPojo.banner}" id="imgs" style="width:360px;height:120px;" />
										</div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                                       </br><span id="image_mgId"></span>
                                       </div>
						 	<span id="message_img"></span>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="10%">专题类别:</td>
						<td><select name="platformSpecialPojo.specialCategories" id="platformSpecialPojo.specialCategories"  class="floatLeft" >
							<option value="">----请选择----</option>
							<option value="1" <s:if test="platformSpecialPojo.specialCategories == 1"> selected="selected"</s:if>>软文专题</option>
							<option value="2" <s:if test="platformSpecialPojo.specialCategories == 2"> selected="selected"</s:if>>商品专题</option>
							
				    		</select>
				    		<span id="message_specialCategories"></span>
				    	</td>
					 	<td align="right" class="grey" width="10%">专题类型:</td>
						<td><select name="platformSpecialPojo.type" id="platformSpecialPojo.type"  class="floatLeft" >
							<option value="">----请选择----</option>
							<option value="1" <s:if test="platformSpecialPojo.type == 1"> selected="selected"</s:if>>普通专题</option>
							<option value="2" <s:if test="platformSpecialPojo.type == 2"> selected="selected"</s:if>>首页专题</option>
							<option value="3" <s:if test="platformSpecialPojo.type == 3"> selected="selected"</s:if>>热门专题</option>
							<option value="4" <s:if test="platformSpecialPojo.type == 4"> selected="selected"</s:if>>经典专题</option>
				    		</select>
				    		<span id="message_type"></span>
				    	</td>
					 </tr>	
					<tr>
						<td align="right" class="grey" width="10%">年龄:</td>
						<td width="20%">
							<select id="ageType" name="platformSpecialPojo.ageType" class="floatLeft" onchange="setSecond(this)">
								<option value="">----请选择----</option>
								<s:iterator value="ageTypeList" id="c"> 
								<!--  <option value="" ><s:property value='name'/> </option>  -->
            					<option value="<s:property value='value'/>" <s:if test="platformSpecialPojo.ageType==value"> selected="selected"</s:if>><s:property value='name'/></option>
        						</s:iterator>
        					</select>
							<span id="message_ageType"></span>
						</td>
						<td align="right" class="grey" width="10%">能力:</td>
						<td width="20%">
							<select id="second" name="platformSpecialPojo.skillType" class="floatLeft" onchange="setThree(this)"></select>
							<span id="message_skillType"></span>
						</td>

					 </tr>
					 <tr>
						<td align="right" class="grey" width="10%">次能力:</td>
						<td>
							<select id="three" name="platformSpecialPojo.secSkillType" class="floatLeft"  onchange="setFour(this)"></select>
							<span id="message_secSkillType"></span>
						</td>
						<td align="right" class="grey" width="10%">商品品类:</td>
						<td>
						<select id="four" name="platformSpecialPojo.productType" class="floatLeft"><option value="">----请选择----</option></select>
						<span id="message_productType"></span>
						</td>
						<td align="right" class="grey" width="10%">有你喜欢:</td>
						<td>
						<select id="yourFavouritesId" name="platformSpecialPojo.yourFavouritesId" class="floatLeft"><option value="">----请选择----</option></select>
						<span id="message_yourFavouritesId"></span>
						</td>
						<!-- <td align="right" class="grey" width="10%">备用标签:</td>
						<td width="20%">
							<select name="platformSpecialPojo.optionType" id="optionType"  class="floatLeft">
								<option value="">----请选择----</option>
								<s:iterator value="labelList"> 
								<option value="<s:property value='id'/>" <s:if test="platformSpecialPojo.optionType == id"> selected="selected"</s:if>><s:property value='name'/> </option>
								</s:iterator>
        					</select>
							<span id="message_optionType"></span>
						</td>
					 </tr> -->
					 
					 
					 <!--  <tr>
					  <td align="right" class="grey" width="10%">内容:</td>
						<td colspan="5">
						<textarea rows="10" cols="40" name="platformSpecialPojo.content" class="floatLeft" id="content">${platformSpecialPojo.content} </textarea>
						<script type="text/javascript">
							var ue = UE.getEditor("content");
						</script>
						</td>
					</tr> -->
					<tr>
    	<td align="right" class="grey">简述：</td>
		<td>
			<textarea rows="5" cols="50" name="platformSpecialPojo.sketch" class="floatLeft">${platformSpecialPojo.sketch }</textarea>
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
	<div class="Btn_div">
		<input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" />
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
	var yourFavouritesId	=new tt.Field("类别 ","platformSpecialPojo.specialCategories").setMsgId("message_specialCategories");
	var score	=new tt.Field("评分","platformSpecialPojo.yourFavouritesId").setMsgId("message_yourFavouritesId");
	var v_sketch   = new tt.Field("简述 ", "platformSpecialPojo.sketch").setMsgId("sketch_msgId");
	tt.vf.req.add(title,img,ageType,skillType,type,secSkillType,specialCategories,score,yourFavouritesId,v_sketch);
	tt.vf.num.add(score);
	new tt.NRV().set(0, 100).add(score);
	tt.vf.int.add(score);
	//new tt.LV().set(0, 50).add(message_title);
</script>
<script type="text/javascript" language="javascript"> 
var phoneData=${templatePageDataPojo.data};
var url="updateSpecialTemplatePageData.do";
var imgUpfileUrl="specialImgUpfile.do";
window.onload = function(){
	//console.log(phoneData);
	 //$('#iframepage').contents().find(".text").remove();  
	 //$('#iframepage').contents().find(".videos").remove();	
	 //监听标签页
	 $("#albumLi").click(function(){
		 //alert(1);
		 if(tt.validate()){
			//alert(3);
			//提交表单
    		$("#form1").ajaxSubmit({
    			type: 'post', 
                url: 'updatePlatformSpecial.do', 
                success: function(specialId) {
                    if(specialId != null){
                    	url="updateSpecialTemplatePageData.do?specialId="+specialId+"&dataId="+"${templatePageDataPojo.id}&pagetype=1";
                    }
                    $("#next").hide();
                }
    		});
		 }else{
			 //alert(5);
			 return false;
		 }
		 $("#next").hide();
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