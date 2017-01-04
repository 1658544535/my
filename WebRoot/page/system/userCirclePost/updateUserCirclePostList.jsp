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
<head>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>笔记管理</a> &gt; <a href="#">编辑笔记</a>
		</div>
	</div>
	<div class="wrap">
		<div class="container" style="width:100%">
			<ul class="nav nav-tabs row" role="tablist">
			    <li role="presentation" class="col-sm-6 text-center active" id="infoLi"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">1.编辑基本信息</a></li>
			    <li role="presentation" class="col-sm-6 text-center " id="albumLi"><a href="#album" aria-controls="album" role="tab" data-toggle="tab">2.编辑笔记详情</a></li>
			</ul>
			<div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="info">
					<div>
						<form action="updateUserCirclePost.do?userCirclePostPojo.id=${userCirclePostPojo.id}" method="post" id="form1" enctype="multipart/form-data">
  <input type="hidden" id="specialId" value=""/>
  <input type="hidden" name="userCirclePostPojo.id" value="${userCirclePostPojo.id}"/>
  <input type="hidden" name="type" id="type" value="${type}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
    	<td align="right" class="grey">标签：</td>
		<td>
		<select id="age" name="userCirclePostPojo.ageType" class="floatLeft" onchange="setSecond(this)">
		<option value="">年龄</option>
		<option value="1" <s:if test="userCirclePostPojo.ageType==1">selected="selected"</s:if>>0~6月</option>  
        <option value="2" <s:if test="userCirclePostPojo.ageType==2">selected="selected"</s:if>>6~12月</option> 
        <option value="3" <s:if test="userCirclePostPojo.ageType==3">selected="selected"</s:if>>1~3岁</option>
        <option value="4" <s:if test="userCirclePostPojo.ageType==4">selected="selected"</s:if>>3~6岁</option>
        <option value="5" <s:if test="userCirclePostPojo.ageType==5">selected="selected"</s:if>>6~12岁</option>
        <option value="6" <s:if test="userCirclePostPojo.ageType==6">selected="selected"</s:if>>12~16岁</option> 
		</select>					
		<select id="second" name="userCirclePostPojo.skillType" class="floatLeft"  onchange="setThree(this)"></select>
		<select id="three" name="userCirclePostPojo.secSkillType" class="floatLeft"  onchange="setFour(this)"></select>
		<select id="four" name="userCirclePostPojo.productType" class="floatLeft"></select>
		<span id="ageType_msgId"></span>
		<span id="skillType_msgId"></span>
		<span id="secSkillType_msgId"></span>
		<span id="productType_msgId"></span>
		</td>
	</tr>
	<%-- <tr>
        <td align="right" class="grey">内容标签：</td>
  		<td>
  		    <select name="userCirclePostPojo.optionType" id="optionType" class="floatLeft"></select>
  		    <span id="optionType_msgId"></span>
    	</td>	
    </tr> --%>
    <tr>
        <td align="right" class="grey">有你喜欢标签：</td>
  		<td>
  		    <select name="userCirclePostPojo.yourFavouritesId" id="yourFavouritesId" class="floatLeft" ></select>
   			<span id="yourFavouritesId_msgId"></span>
    	</td>	
    </tr>
	<tr>
    	<td align="right" class="grey">用户ID：</td>
		<td><input type="text" name="userCirclePostPojo.userId" value="${userCirclePostPojo.userId}" class="floatLeft" /><div id="userId_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey">笔记标题：</td>
		<td><input type="text" name="userCirclePostPojo.title" value="${userCirclePostPojo.title}" class="floatLeft" /><div id="title_msgId"></div></td>
	</tr>
	<tr>
    	<td align="right" class="grey">简述：</td>
		<td>
			<textarea rows="5" cols="50" name="userCirclePostPojo.sketch" class="floatLeft">${userCirclePostPojo.sketch}</textarea>
			<div id="sketch_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey">社圈：</td>
		<td>
		<select name="userCirclePostPojo.circleId" class="floatLeft">
			<option value="">请选择社圈</option>
			<c:forEach items="${socialCirclePojoList}" var="unit">
			<option value="${unit.id}" <c:if test="${unit.id == userCirclePostPojo.circleId }">selected="selected"</c:if>>${unit.title}</option>
			</c:forEach>
		</select>
		<div id="circleId_msgId"></div>
		</td>
	<tr>
	 	<td align="right" class="grey">评分：</td>
		<td>
			<input type="text" name="userCirclePostPojo.score" value="${userCirclePostPojo.score}" class="floatLeft" />
			<div id="score_msgId"></div>
		</td>
	</tr>
	<tr>
	 	<td align="right" class="grey">排序：</td>
		<td>
			<input type="text" name="userCirclePostPojo.sorting" value="${userCirclePostPojo.sorting}" class="floatLeft" />
			<div id="sorting_msgId"></div>
		</td>
	</tr>
	<tr>
	<td align="right" class="grey">状态：</td>
     <td><select name="userCirclePostPojo.status" id="userCirclePostPojo.status"  class="floatLeft">
							<option value="0" <s:if test="userCirclePostPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="userCirclePostPojo.status==1">selected="selected"</s:if>>审核通过</option>
							<option value="2" <s:if test="userCirclePostPojo.status==2">selected="selected"</s:if>>审核不通过</option>
				    		</select></td>
   	
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">主图：</td>
		<td>
			<input type="file" class="floatLeft" name="upfile1" class="floatLeft" id="userCirclePostPojo.banner">
			<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCirclePost/${userCirclePostPojo.banner}' height='100px' name="imageFile" />
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
	<div class="Btn_div" id="btn-div">
		<!-- <button class="back_btn" id="past">上一步</button> -->
		<input type="button" class="btn btn-warning save sbutton" value="下一步" id="next" />
		<!-- <button type="input" class="btn btn-primary" onclick="window.history.back()" id="back">返回</button> -->
	</div>
</body>
</html>
<script>
    var v_name         = new tt.Field("标题", "userCirclePostPojo.title").setMsgId("title_msgId");
    var v_userId       = new tt.Field("用户ID", "userCirclePostPojo.userId").setMsgId("userId_msgId");
    var v_circleId     = new tt.Field("社圈", "userCirclePostPojo.circleId").setMsgId("circleId_msgId");
    var v_score        = new tt.Field("评分 ", "userCirclePostPojo.score").setMsgId("score_msgId");    
    var v_sorting      = new tt.Field("排序 ", "userCirclePostPojo.sorting").setMsgId("sorting_msgId");    
    var v_ageType      = new tt.Field("年龄", "userCirclePostPojo.ageType").setMsgId("ageType_msgId");
    var v_skillType    = new tt.Field("主能力 ", "userCirclePostPojo.skillType").setMsgId("skillType_msgId");
    var v_secSkillType = new tt.Field("次能力", "userCirclePostPojo.secSkillType").setMsgId("secSkillType_msgId");
    var v_productType  = new tt.Field("商品品类 ", "userCirclePostPojo.productType").setMsgId("productType_msgId");
    var v_optionType   = new tt.Field("必选标签 ", "userCirclePostPojo.optionType").setMsgId("optionType_msgId");
    var v_yourFavouritesId   = new tt.Field("有你喜欢标签 ", "userCirclePostPojo.yourFavouritesId").setMsgId("yourFavouritesId_msgId");
    var v_sketch   = new tt.Field("简述 ", "userCirclePostPojo.sketch").setMsgId("sketch_msgId");
    tt.vf.req.add(v_name,v_userId,v_circleId,v_score,v_sorting,v_ageType,v_skillType,v_secSkillType,v_productType,/* v_optionType, */v_yourFavouritesId,v_sketch);
	tt.vf.num.add(v_userId,v_circleId,v_score,v_sorting);
	new tt.NRV().set(0, '100').add(v_score);
	tt.vf.int.add(v_score);
	new tt.LV().set(0, 100).add(v_name);
	tt.vf.int.add(v_sorting);
	//new tt.NRV().set(0, '100').add(v_sorting);
	new tt.LV().set(0, 10).add(v_sorting);
	new tt.LV().set(0, 20).add(v_userId);
		
	//全局变量
	var ageValuejs;  
    var skillValuejs;
	
	$(document).ready(function() {
		//$("#sbutton").click(function(){
			//if(tt.validate()){
				//document.getElementById("from1").submit();
			//}
		//});
		select2("${userCirclePostPojo.ageType}");
		select3();
		ageValuejs = "${userCirclePostPojo.ageType}";
		selectThree("${userCirclePostPojo.skillType}");
		skillValuejs = "${userCirclePostPojo.skillType}";
		selectFour("${userCirclePostPojo.secSkillType}");		
		selectFive(ageValuejs);
	});
//-------------四级联动开始----------------	

//第二个下拉框事件
function setSecond(obj){  
    var val = obj.value;
    ageValuejs = obj.value;

    $("#second").empty();
    select2(val);
    
    selectFive(val);
}  

function select2(val) {
var ability = "${userCirclePostPojo.skillType}";
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val,
        dataType: 'json',
        success: function (msg) {
        	//console.log(msg);
        	var o_msg = eval(msg);
        	$("#second").append("<option value=''>一级能力</option>");
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            if(o_msg[i].skillValue == ability){
            		selStr = " selected='selected' ";
            	}
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
var secondAbility = "${userCirclePostPojo.secSkillType}";
    //alert("quan ju :"+ageValuejs);
    $.ajax(
    {
        type: "get",
        url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
        	$("#three").append("<option value=''>二级能力</option>");
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            	if(o_msg[i].secondSkillValue == secondAbility){
            		selStr = " selected='selected' ";
            	}
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
var productType = "${userCirclePostPojo.productType}";
    //alert("quan ju :"+skillValuejs);
    $.ajax(
    {
        type: "get",
        url: "getProductTypes.do?secondSkillValue="+val+"&ageValue="+ageValuejs+"&skillValue="+skillValuejs,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
        	$("#four").append("<option value=''>商品类别</option>");
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            	if(o_msg[i].productId == productType){
            		selStr = " selected='selected' ";
            	}
                $("#four").append("<option value=" + o_msg[i].productId + selStr +">" + o_msg[i].productName + "</option>");
            }
        }
    })
};

function selectFive(val) {
	$("#yourFavouritesId").html("");
    $("#yourFavouritesId").append("<option value=''>请选择标签</option>");
	var yourFavouritesId = "${userCirclePostPojo.yourFavouritesId}";
    $.ajax(
    {
        type: "post",
        url: "favouritesLabelListAllSelect.do?ageType="+val,
        dataType: 'json',
        success: function (msg) {
        	var o_msg = eval(msg);
            for (var i = 0; i < o_msg.length; i++) {
            	var selectedStr = "";
            	if(o_msg[i].id == yourFavouritesId){
            		selectedStr = " selected='selected' ";
            	}
                $("#yourFavouritesId").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
            }
        }
    })
	    //alert("quan ju :"+ageValuejs);
	    $.ajax(
	    {
	        type: "get",
	        url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
	        dataType: 'json',
	        success: function (msg) {
	        	console.log(msg);
	        	var o_msg = eval(msg);
	        	$("#three").append("<option value=''>----请选择----</option>");
	            for (var i = 0; i < o_msg.length; i++) {
	            	var selStr = "";
	            	if(o_msg[i].secondSkillValue == secondAbility){
	            		selStr = " selected='selected' ";
	            	}
	                $("#three").append("<option value=" + o_msg[i].secondSkillValue + selStr +">" + o_msg[i].secondSkillName + "</option>");
	            }
	        }
	    })
	};
//----------四级联动结束-------------------


function select3() {
		$("#optionType").append("<option value=''>----请选择----</option>");
		$.ajax(
		{
			type: "post",
			url: "labelListAll.do?all=1",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
				    var selectedStr = "";
                    	if("${userCirclePostPojo.optionType}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
					$("#optionType").append("<option value=" + o_msg[i].id + " "+selectedStr+ ">" + o_msg[i].name + "</option>");
				}
			}
		})
	};

function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckUserCirclePost.do?timeId=${timeId}&userCirclePostPojo.id="+val;	
			undoOpreator(url,null);
		}else{
			return ;
		}
	}
	
	function undoOpreator(url,params){
		MAOWU.ajax.get(url, params, ungoRefreshPage);
	}
	 
	function ungoRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("取消审核成功");
			queryData("findUserCirclePostCount.do?timeId=${timeId}", "findUserCirclePostList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}


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
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-form.js"></script>
<script> 
var phoneData=${templatePageDataPojo.data};
var url="";
var imgUpfileUrl="postImgUpfile.do";
var flag=false;
window.onload = function(){
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
	                url: 'updateUserCirclePost.do', 
	                success: function(data) {
	                    if(data != null){
	                    	alert("基本信息保存成功！~请填写详情信息！~");
	                    	url="updateSpecialTemplatePageData.do?specialId="+data+"&dataId="+"${templatePageDataPojo.id}&pagetype=3";
	                    	$("#specialId").val(data);
	                    	$("#next").hide();
	                        //$("#past").hide();
	                        //$("#back").hide();
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
                 //$("#past").hide();
                 //$("#back").hide();
			 }
		 }else{
			 //alert(5);
			 return false;
		 }
		 //$("#next").hide();
         //$("#past").hide();
         //$("#back").hide();
	 });
	 //监听上一步
	 $("#infoLi").click(function(){
		//$("#past").hide();
		$("#next").show();
		//$("#back").show();
	 });
	 //$("#past").click(function(){
		//$("#past").hide();
		//$("#next").show();
		//$("#infoLi").find("a").click();
	 //});
	 //点击提交按钮
	 $('.sbutton').click(function() {
		//alert(11);
		if (tt.validate()) {
            $("#albumLi").find("a").click();
    	}
	});
	 
}
</script>