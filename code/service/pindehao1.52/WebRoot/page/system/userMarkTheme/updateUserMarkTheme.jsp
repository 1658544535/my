<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baidu.ueditor.ActionEnter"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>

<script>
$(function(){
	if("${userMakerThemePojo.banner}" == ''){
		style="display: none;"  
		document.getElementById("imgs").style.display="none";//隐藏  
			  
		document.getElementById("uploadPreview_note").style.display="";//显示 
	}
	if("${userMakerThemePojo.ageType}" != null && "${userMakerThemePojo.ageType}" != 0){
		select21("${userMakerThemePojo.ageType}");
		selectThree1("${userMakerThemePojo.skillType}");
		selectFour1("${userMakerThemePojo.secSkillType}");
		selectYourLike1("${userMakerThemePojo.ageType}");
	}
	
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
            	if(o_msg[i].skillValue == "${userMakerThemePojo.skillType}"){
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

	          	if(o_msg[i].secondSkillValue == "${userMakerThemePojo.secSkillType}"){
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
	          	if(o_msg[i].productId == "${userMakerThemePojo.productType}"){
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
	  $.ajax({
	      type: "get",
	      url: "getYourFavouritesLable.do?id="+val,
	      dataType: 'json',
	      success: function (msg) {
	      	var o_msg = eval(msg.result);
	          for (var i = 0; i < o_msg.length; i++) {
	          	var selStr = "";
	          	if(o_msg[i].id == "${userMakerThemePojo.yourFavouritesId}"){
	        		selStr = "selected='selected'";
	        	}
	              $("#yourFavouritesId").append("<option value=" + o_msg[i].id +" "+selStr+">" + o_msg[i].name + "</option>");
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
if("${userMakerThemePojo.ageType}" != null && "${userMakerThemePojo.ageType}" != 0){
	var ageValuejs = "${userMakerThemePojo.ageType}";  
	var skillValuejs = "${userMakerThemePojo.skillType}";
}else{
	var ageValuejs;  
	var skillValuejs;
}


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
              $("#three").append("<option value=" + o_msg[i].secondSkillValue + selStr +">" + o_msg[i].secondSkillName + "</option>");
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
          //	if(o_msg[i].productId == ${userMakerThemePojo.productType}){
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
			<a>专题管理</a> &gt; <a href="">编辑专题</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateUserMakerTheme.do" method="post" id="from1" enctype="multipart/form-data">
			<input type="hidden" name="userMakerThemePojo.id" value="${userMakerThemePojo.id}" class="floatLeft" />
			<input type="hidden" name="userMakerThemePojo.createBy" value="${sessionScope.loginPojoId}">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					
					<tr>
						<td align="right" class="grey" width="10%">专题标题:</td>
						<td width="20%">
						 	<label>
						 		<input type="text"  name="userMakerThemePojo.specialName" id="userMakerThemePojo.specialName" value="${userMakerThemePojo.specialName}" class="floatLeft" readOnly="true" >
						 	</label>
						 	<span id="message_title"></span>
						</td>
						
						<td align="right" class="grey" width="10%"  rowspan="3">专题主图:</td>
						<td width="30%"  rowspan="3">
						<table border="0" cellpadding="0" cellspacing="0">
						<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userMakerTheme/${userMakerThemePojo.banner}" id="imgs" style="width:360px;height:120px;"  /></td></tr>
						<tr>
						<td> <input type="file" name="upfile" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
						</tr>
					    </table>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="10%">审核状态:</td>
						<td><select name="userMakerThemePojo.status" id="userMakerThemePojo.status"  class="floatLeft" >
							<option value="1" <s:if test="userMakerThemePojo.status == 1"> selected="selected"</s:if>>审核成功</option>
							<option value="2" <s:if test="userMakerThemePojo.status == 2"> selected="selected"</s:if>>审核失败</option>
							</select>
				    	</td>
						</tr>
						<tr>
					 	<td align="right" class="grey" width="10%">专题类型:</td>
						<td><select name="userMakerThemePojo.type" id="userMakerThemePojo.type"  class="floatLeft" >
							<option value="">----请选择----</option>
							<option value="1" <s:if test="userMakerThemePojo.type == 1"> selected="selected"</s:if>>创好玩</option>
							<option value="2" <s:if test="userMakerThemePojo.type == 2"> selected="selected"</s:if>>创选活动</option>
							<option value="3" <s:if test="userMakerThemePojo.type == 3"> selected="selected"</s:if>>普通创客专题</option>
				    		</select>
				    		<span id="message_type"></span>
				    	</td>
					 </tr>	
					<tr>
						<td align="right" class="grey" width="10%">年龄:</td>
						<td width="20%">
							<select id="ageType" name="userMakerThemePojo.ageType" class="floatLeft" onchange="setSecond(this)">
								<option value="">----请选择----</option>
								<s:iterator value="ageTypeList" id="c"> 
								<!--  <option value="" ><s:property value='name'/> </option>  -->
            					<option value="<s:property value='value'/>" <s:if test="userMakerThemePojo.ageType==value"> selected="selected"</s:if>><s:property value='name'/></option>
        						</s:iterator>
        					</select>
							<span id="message_ageType"></span>
						</td>
						<td align="right" class="grey" width="10%">能力:</td>
						<td width="20%">
							<select id="second" name="userMakerThemePojo.skillType" class="floatLeft" onchange="setThree(this)"></select>
							<span id="message_skillType"></span>
						</td>

					 </tr>
					 <tr>
						<td align="right" class="grey" width="10%">次能力:</td>
						<td>
							<select id="three" name="userMakerThemePojo.secSkillType" class="floatLeft"  onchange="setFour(this)"></select>
							<span id="message_secSkillType"></span>
						</td>
						<td align="right" class="grey" width="10%">商品品类:</td>
						<td>
						<select id="four" name="userMakerThemePojo.productType" class="floatLeft"><option value="">----请选择----</option></select>
						<span id="message_productType"></span>
						</td>
						
					 </tr>
					 <tr>
					 	<td align="right" class="grey" width="10%">有你喜欢:</td>
						<td>
						<select id="yourFavouritesId" name="userMakerThemePojo.yourFavouritesId" class="floatLeft"><option value="">----请选择----</option></select>
						<span id="message_yourFavouritesId"></span>
						</td>
						<td align="right" class="grey" width="10%">简述:</td>
						<td>
						<input type="text" name="userMakerThemePojo.sketch" id="userMakerThemePojo.sketch" value="${userMakerThemePojo.sketch}"/>
						<span id="message_sketch"></span>
						</td>
						<!-- <td align="right" class="grey" width="10%">备用标签:</td>
						<td width="20%">
							<select name="userMakerThemePojo.optionType" id="optionType"  class="floatLeft">
								<option value="">----请选择----</option>
								<s:iterator value="labelList"> 
								<option value="<s:property value='id'/>" <s:if test="userMakerThemePojo.optionType == id"> selected="selected"</s:if>><s:property value='name'/> </option>
								</s:iterator>
        					</select>
							<span id="message_optionType"></span>
						</td>
						<td align="right" class="grey" width="10%">评分:</td>
						<td width="20%">
						 	<label>
						 		<input type="text"  name="userMakerThemePojo.score" id="userMakerThemePojo.score" value="${userMakerThemePojo.score}" class="floatLeft" >
						 	</label>
						 	<span id="message_score"></span>
						</td> -->
					 </tr>
					 <tr>
					  <td align="right" class="grey" width="10%">内容:</td>
						<td colspan="5">
						<textarea rows="15" cols="40" name="" class="floatLeft" id="content">${userMakerThemePojo.content} </textarea>
						<script type="text/javascript">
							var ue = UE.getEditor("content",{readonly:true});
						</script>
						</td>
					</tr>
					  
				</table>
			</form>
		</div>
		<div class="Btn_div">
		    <input type="button" class="ok_btn" value="提交" id="sbutton" />
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
		</div>
	</div>
</body>
</html>

<script>


	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
	
	var ageType         =new tt.Field("年龄 ","userMakerThemePojo.ageType").setMsgId("message_ageType");  
	var skillType       =new tt.Field("能力 ","userMakerThemePojo.skillType").setMsgId("message_skillType");
	//var optionType      =new tt.Field("品类 ","userMakerThemePojo.optionType").setMsgId("message_optionType");
	var productType     =new tt.Field("商品 ","userMakerThemePojo.productType").setMsgId("message_productType");
	var type     		=new tt.Field("专题类型 ","userMakerThemePojo.type").setMsgId("message_type");
	var secSkillType	=new tt.Field("次能力 ","userMakerThemePojo.secSkillType").setMsgId("message_secSkillType");
	var score			=new tt.Field("评分","userMakerThemePojo.score").setMsgId("message_score");
	var yourFavouritesId=new tt.Field("评分","userMakerThemePojo.yourFavouritesId").setMsgId("message_yourFavouritesId");
	var sketch=new tt.Field("评分","userMakerThemePojo.sketch").setMsgId("message_sketch");
	

	tt.vf.req.add(ageType,skillType,productType,type,secSkillType,score);
	tt.vf.num.add(score);
	new tt.NRV().set(0, 100).add(score);
	tt.vf.int.add(score);
	//new tt.LV().set(0, 50).add(message_title);
	
</script>