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
<script type="text/javascript">
$(function(){
		//select4();
		$('#age').bind("change", select1);
		$('#skillType').bind("change", select2);
		$('#secSkillType').bind("change", select3);
		$('#age').bind("change", select5);
	});
function select1() {
        $("#skillType").append("<option value=''>一级能力</option>");
		$.ajax(
		{
			type: "post",
			url: "getSkillTypes.do?ageId="+$('#age').val(),
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
					$("#skillType").append("<option value=" + o_msg[i].skillValue + " "+selectedStr+">" + o_msg[i].skillName + "</option>");
				}
				if("${userCirclePostPojo.skillType}"!=null && "${userCirclePostPojo.skillType}"!=""){
					select2();
				}
			}
		})
	};
function select2() {
			$("#secSkillType").html("");
            $("#secSkillType").append("<option value=''>二级能力</option>");
            $.ajax(
            {
                type: "post",
                url: "getSecondSkillTypes.do?skillValue="+$('#skillType').val()+"&ageValue="+$('#age').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                        $("#secSkillType").append("<option value=" + o_msg[i].secondSkillValue + " "+selectedStr+">" + o_msg[i].secondSkillName + "</option>");
                    }
                    if("${userCirclePostPojo.secSkillType}"!=null && "${userCirclePostPojo.secSkillType}"!=""){
						select3();
					}
                }
            })
        };
function select3() {
			$("#productType").html("");
            $("#productType").append("<option value=''>商品类别</option>");
            $.ajax(
            {
                type: "post",
                url: "getProductTypes.do?secondSkillValue="+$('#secSkillType').val()+"&ageValue="+$('#age').val()+"&skillValue="+$('#skillType').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                        $("#productType").append("<option value=" + o_msg[i].productId + " "+selectedStr+">" + o_msg[i].productName + "</option>");
                    }
                }
            })
        };
function select4() {
            $("#optionType").append("<option value=''>请选择标签</option>");
            $.ajax(
            {
                type: "post",
                url: "labelListAll.do?all=1",
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                        $("#optionType").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
function select5() {
			$("#yourFavouritesId").html("");
            $("#yourFavouritesId").append("<option value=''>请选择标签</option>");
            $.ajax(
            {
                type: "post",
                url: "favouritesLabelListAllSelect.do?ageType="+$('#age').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                        $("#yourFavouritesId").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
</script>
<head>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>笔记管理</a> &gt; <a href="#">添加笔记</a>
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
						<form action="insertUserCirclePost.do" method="post" id="form1" enctype="multipart/form-data">
    <input type="hidden" id="version" name="userCirclePostPojo.version" value="1"/>
						<input type="hidden" id="specialId" name="userCirclePostPojo.id" value=""/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
    	<td align="right" class="grey">标签：</td>
		<td>
		<select id="age" name="userCirclePostPojo.ageType" class="floatLeft" onchange="setSecond(this)">
		<option value="">年龄</option>
		<option value="1">0~6月</option>  
        <option value="2">6~12月</option> 
        <option value="3">1~3岁</option>
        <option value="4">3~6岁</option>
        <option value="5">6~12岁</option>
        <option value="6">12~16岁</option> 
		</select>					
		<select  name="userCirclePostPojo.skillType" id="skillType" class="floatLeft" ></select>	
		<select  name="userCirclePostPojo.secSkillType" id="secSkillType" class="floatLeft" ></select>	
		<select  name="userCirclePostPojo.productType" id="productType" class="floatLeft" ></select>	
		<span id="ageType_msgId"></span>
		<span id="skillType_msgId"></span>
		<span id="secSkillType_msgId"></span>
		<span id="productType_msgId"></span>
		</td>
	</tr>
	<%-- <tr>
        <td align="right" class="grey">内容标签：</td>
  		<td>
  		    <select name="userCirclePostPojo.optionType" id="optionType" class="floatLeft" ></select>
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
		<td><input type="text" name="userCirclePostPojo.userId" value="" class="floatLeft" /><div id="userId_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey">笔记标题：</td>
		<td><input type="text" name="userCirclePostPojo.title" value="" class="floatLeft" /><div id="title_msgId"></div></td>
	</tr>
	<tr>
    	<td align="right" class="grey">简述：</td>
		<td>
			<textarea rows="5" cols="50" name="userCirclePostPojo.sketch" class="floatLeft"></textarea>
			<div id="sketch_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey">社圈：</td>
		<td>
		<select name="userCirclePostPojo.circleId" class="floatLeft">
			<option value="">请选择社圈</option>
			<c:forEach items="${socialCirclePojoList}" var="unit">
			<option value="${unit.id}">${unit.title}</option>
			</c:forEach>
		</select>
		<div id="circleId_msgId"></div>
		</td>
	</tr>
	<tr>
	 	<td align="right" class="grey">评分：</td>
		<td>
			<input type="text" name="userCirclePostPojo.score" value="" class="floatLeft" />
			<div id="score_msgId"></div>
		</td>
	</tr>
	<tr>
	 	<td align="right" class="grey">排序：</td>
		<td>
			<input type="text" name="userCirclePostPojo.sorting" value="0" class="floatLeft" />
			<div id="sorting_msgId"></div>
		</td>
	</tr>
	<tr>
		<td align="right" class="grey">状态：</td>
     	<td><select name="userCirclePostPojo.status" id="userCirclePostPojo.status"  class="floatLeft">
							<option value="0">未审核</option>
							<option value="1">审核通过</option>
							<option value="2">审核不通过</option>
		 	</select><div id="status_msgId"></div></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">主图：</td>
		<td>
			<input type="file" class="floatLeft" name="upfile1" class="floatLeft" id="userCirclePostPojo.banner">
			<img src='' height='100px' name="imageFile" />
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
    //var v_optionType   = new tt.Field("内容标签 ", "userCirclePostPojo.optionType").setMsgId("optionType_msgId");
    var v_status       = new tt.Field("状态 ", "userCirclePostPojo.status").setMsgId("status_msgId");
    var v_yourFavouritesId   = new tt.Field("有你喜欢标签 ", "userCirclePostPojo.yourFavouritesId").setMsgId("yourFavouritesId_msgId");
    var v_sketch   = new tt.Field("简述 ", "userCirclePostPojo.sketch").setMsgId("sketch_msgId");
	tt.vf.req.add(v_name,v_userId,v_circleId,v_score,v_sorting,v_ageType,v_skillType,v_secSkillType,v_productType,/* v_optionType, */v_status,v_yourFavouritesId,v_sketch);
	tt.vf.num.add(v_userId,v_circleId,v_score,v_sorting);
	new tt.NRV().set(0, '100').add(v_score);
	tt.vf.int.add(v_score);
	new tt.LV().set(0, 100).add(v_name);
	tt.vf.int.add(v_sorting);
	//new tt.NRV().set(0, '100').add(v_sorting);
	new tt.LV().set(0, 10).add(v_sorting);
	new tt.LV().set(0, 20).add(v_userId);
	
	$(document).ready(function() {
		//$("#sbutton").click(function(){		
			//if(tt.validate()){
				//document.getElementById("from1").submit();
			//}
		//});
		
		//隐藏上一步按钮
		//$("#past").hide();
	});
	
	$(function(){
		$(":file").change(function () {
			var _this = $(this);
			var url = _this.val();
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(_this.get(0).files[0]);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(_this.get(0).files[0]);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
			}
			$("img[name='imageFile']").attr("src", url);
		});
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
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-form.js"></script>
<script> 
var phoneData="";
var url="addSpecialTemplatePageData.do";
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
	                url: 'insertUserCirclePost.do', 
	                success: function(data) {
	                    if(data != null){
	                    	alert("基本信息保存成功！~请填写详情信息！~");
	                    	url="addSpecialTemplatePageData.do?specialId="+data+"&pagetype=3";
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