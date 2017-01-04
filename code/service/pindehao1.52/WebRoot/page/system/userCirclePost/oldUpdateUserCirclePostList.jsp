<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">笔记管理</a> &gt;<a href="#">笔记管理</a> &gt;<a href="#">笔记编辑</a></div>
  <div>
  <form action="updateUserCirclePost.do?userCirclePostPojo.id=${userCirclePostPojo.id}" method="post" id="from1" enctype="multipart/form-data">
  <input type="hidden" name="type" id="type" value="${type}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
    	<td align="right" class="grey">笔记标题：</td>
		<td><input type="text" name="userCirclePostPojo.title" value="${userCirclePostPojo.title}" class="floatLeft" />
		<div id="title_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey">用户ID：</td>
		<td><input type="text" name="userCirclePostPojo.userId" value="${userCirclePostPojo.userId}" class="floatLeft" /><div id="userId_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey">社圈ID：</td>
		<td><input type="text" name="userCirclePostPojo.circleId" value="${userCirclePostPojo.circleId}" class="floatLeft" /><div id="circleId_msgId"></div>
		</td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">年龄：</td>
		<td><select id="age" name="userCirclePostPojo.ageType" class="floatLeft" onchange="setSecond(this)">
		<option value="">---- 选择年龄段 ----</option>
		<option value="1" <s:if test="userCirclePostPojo.ageType==1">selected="selected"</s:if>>0~6月</option>  
        <option value="2" <s:if test="userCirclePostPojo.ageType==2">selected="selected"</s:if>>6~12月</option> 
        <option value="3" <s:if test="userCirclePostPojo.ageType==3">selected="selected"</s:if>>1~3岁</option>
        <option value="4" <s:if test="userCirclePostPojo.ageType==4">selected="selected"</s:if>>3~6岁</option>
        <option value="5" <s:if test="userCirclePostPojo.ageType==5">selected="selected"</s:if>>6~12岁</option>
        <option value="6" <s:if test="userCirclePostPojo.ageType==6">selected="selected"</s:if>>12~16岁</option> 
		</select>					
		<span id="ageType_msgId"></span></td>
    </tr> 
    <tr>
		<td align="right" class="grey" width="15%">主能力：</td>
        <td><select id="second" name="userCirclePostPojo.skillType" class="floatLeft"  onchange="setThree(this)"></select><span id="skillType_msgId"></span></td></tr>
    <tr>
    <td align="right" class="grey" width="15%">次能力：</td>
						<td><select id="three" name="userCirclePostPojo.secSkillType" class="floatLeft"  onchange="setFour(this)"></select><span id="secSkillType_msgId"></span></td></tr>
						  <tr><td align="right" class="grey">商品：</td>
						<td><select id="four" name="userCirclePostPojo.productType" class="floatLeft"></select><span id="productType_msgId"></span></td>
	</tr>
    <tr>
        <td align="right" class="grey">备用标签：</td>
  		<td>
  		    <select name="userCirclePostPojo.optionType" id="optionType" class="floatLeft"></select>
  		    <span id="optionType_msgId"></span>
    	</td>	
    </tr>
	<tr>
	 	<td align="right" class="grey">评分：</td>
		<td>
			<input type="text" name="userCirclePostPojo.score" value="${userCirclePostPojo.score}" class="floatLeft" />
			<div id="score_msgId"></div>
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
		<td align="right" class="grey" width="15%">图片1：</td>
		<td>
			<input type="file" class="floatLeft" name="upfile1" class="floatLeft"">
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" />
			</s:i18n>/upfiles/userCirclePost/${userCirclePostPojo.banner}" height="100px" />
		</td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">图片2：</td>
		<td>
			<input type="file" class="floatLeft" name="upfile2" class="floatLeft"">
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" />
			</s:i18n>/upfiles/userCirclePost/${userCirclePostPojo.image}" height="100px" />
		</td>
	</tr>
	<tr>
	 	<td align="right" class="grey">视频URL：</td>
		<td>
			<input type="text" name="userCirclePostPojo.videoUrl" value="${userCirclePostPojo.videoUrl}" class="floatLeft" />
			<div id="videoUrl_msgId"></div>
		</td>
	</tr>
	<tr>
    	<td align="right" class="grey" width="15%">正文：</td>
		<td width="55%">
			<textarea rows="10" cols="70" name="userCirclePostPojo.content" class="floatLeft" id="introduction">${userCirclePostPojo.content}</textarea>
			<div id="content_mgId"></div>
		</td>
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
    var v_name         = new tt.Field("标题", "userCirclePostPojo.title").setMsgId("title_msgId");
    var v_userId       = new tt.Field("用户ID", "userCirclePostPojo.userId").setMsgId("userId_msgId");
    var v_circleId     = new tt.Field("社圈ID", "userCirclePostPojo.circleId").setMsgId("circleId_msgId");
    var v_score        = new tt.Field("评分 ", "userCirclePostPojo.score").setMsgId("score_msgId");    
    var v_ageType      = new tt.Field("年龄", "userCirclePostPojo.ageType").setMsgId("ageType_msgId");
    var v_skillType    = new tt.Field("主能力 ", "userCirclePostPojo.skillType").setMsgId("skillType_msgId");
    var v_secSkillType = new tt.Field("次能力", "userCirclePostPojo.secSkillType").setMsgId("secSkillType_msgId");
    var v_productType  = new tt.Field("商品品类 ", "userCirclePostPojo.productType").setMsgId("productType_msgId");
    var v_optionType   = new tt.Field("必选标签 ", "userCirclePostPojo.optionType").setMsgId("optionType_msgId");
    var v_content      = new tt.Field("正文 ", "userCirclePostPojo.content").setMsgId("content_mgId");
    var v_videoUrl     = new tt.Field("视频链接 ", "userCirclePostPojo.videoUrl").setMsgId("videoUrl_msgId");
	tt.vf.req.add(v_name,v_userId,v_circleId,v_score,v_ageType,v_skillType,v_secSkillType,v_productType,v_optionType,v_content/*,v_videoUrl*/);
	tt.vf.num.add(v_score);
	new tt.NRV().set(0, '100').add(v_score);
	tt.vf.int.add(v_score);
		
	//全局变量
	var ageValuejs;  
    var skillValuejs;
	
	$(document).ready(function() {
		$("#sbutton").click(function(){
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
		select2("${userCirclePostPojo.ageType}");
		select3();
		ageValuejs = "${userCirclePostPojo.ageType}";
		selectThree("${userCirclePostPojo.skillType}");
		skillValuejs = "${userCirclePostPojo.skillType}";
		selectFour("${userCirclePostPojo.secSkillType}");		
	});
//-------------四级联动开始----------------	

//第二个下拉框事件
function setSecond(obj){  
    var val = obj.value;
    ageValuejs = obj.value;

    $("#second").empty();
    select2(val);
     
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
        	$("#second").append("<option value=''>----请选择----</option>");
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
        	$("#four").append("<option value=''>----请选择----</option>");
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
