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
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">视频管理</a> &gt;<a href="#">视频编辑</a></div>
  <div>
  <form action="updateVideo.do?type=${type}&panduan=${panduan}&videoPojo.id=${videoPojo.id}" method="post" id="from1" enctype="multipart/form-data">
  <input type="hidden" name="type" id="type" value="${type}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr><td align="right" class="grey">标题：</td>
	<td>
	<input type="text" name="videoPojo.label" value="${videoPojo.label}" class="floatLeft" />
	<span id="label_msgId"></span>
	</td>
	</tr>
	 <tr><td align="right" class="grey">视频链接：</td>
	<td>
	<label class="floatLeft" name="videoPojo.url"><a target='_blank' href="${videoPojo.url}">${videoPojo.url}</a><label>
	</td>
	</tr>
	<tr>
	<td align="right" class="grey">评分：</td>
     <td><select name="videoPojo.score" id="videoPojo.score"  class="floatLeft">                
							<option value="1"<s:if test="videoPojo.score==1">selected="selected"</s:if>>1</option>
							<option value="2"<s:if test="videoPojo.score==2">selected="selected"</s:if>>2</option>
							<option value="3"<s:if test="videoPojo.score==3 || videoPojo.score==0">selected="selected"</s:if>>3</option>
							<option value="4"<s:if test="videoPojo.score==4">selected="selected"</s:if>>4</option>
							<option value="5"<s:if test="videoPojo.score==5">selected="selected"</s:if>>5</option>
				    		</select><div id="status_mgId"></div></td>
   	
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">年龄：</td>
		<td><select id="age" name="videoPojo.age" class="floatLeft" onchange="setSecond(this)">
		<option value="">---- 选择年龄段 ----</option>
		<option value="1" <s:if test="videoPojo.age==1">selected="selected"</s:if>>0~6月</option>  
        <option value="2" <s:if test="videoPojo.age==2">selected="selected"</s:if>>6~12月</option> 
        <option value="3" <s:if test="videoPojo.age==3">selected="selected"</s:if>>1~3岁</option>
        <option value="4" <s:if test="videoPojo.age==4">selected="selected"</s:if>>3~6岁</option>
        <option value="5" <s:if test="videoPojo.age==5">selected="selected"</s:if>>6~12岁</option>
        <option value="6" <s:if test="videoPojo.age==6">selected="selected"</s:if>>12~16岁</option> 
		</select>					
		<span id="age_msgId"></span></td>
    </tr> 
    <tr>
		<td align="right" class="grey" width="15%">能力：</td>
		<td><select id="second" name="videoPojo.skill" class="floatLeft">
		</select>					
		<span id="skill_msgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">类型：</td>
		<td><div id="channelId" name="videoPojo.type" class="floatLeft" ></div></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">备注：</td>
		<td><textarea rows="10" cols="40" name="videoPojo.remark" class="floatLeft" id="content">${videoPojo.remark}</textarea></td>
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
    var v_name = new tt.Field("标题", "videoPojo.label").setMsgId("label_msgId");
    var v_age = new tt.Field("年龄 ", "videoPojo.age").setMsgId("age_msgId");
    var v_skill = new tt.Field("能力 ", "videoPojo.skill").setMsgId("skill_msgId");
    
	tt.vf.req.add(v_name,v_age,v_skill);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
				
			}
		});
		select3();
		select2(${videoPojo.age});
	});
	
	function setSecond(obj){  
    var val = obj.value;
    $("#second").empty();
    select2(val);
     
}  

function select2(val) {
	var ability = "${videoPojo.skill}";
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val ,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            	//alert(o_msg[i].skillValue);
            	//alert(ability);
            	if(o_msg[i].skillValue == ability){
            		selStr = " selected='selected' ";
            	}
                $("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
            }
        }
    })
};
 function select3() {
    	$("#channelId").append("");
            $.ajax(
            {
                type: "post",
                url: "sysDictAllList.do?sysDict.shipin=1",
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        var selectedStr = "${videoPojo.type}";
                        var selectedStrs = selectedStr.split("，")
                        for(var j = 0; j < selectedStrs.length; j++){
	                        if(selectedStrs[j] == o_msg[i].name){
	                    		selectedStr = "checked='true'";
	                    	}
                        }
                        $("#channelId").append("<label><input type='checkbox' name='channelIds' value=" + o_msg[i].name + " "+selectedStr+">" + o_msg[i].name + "</label>&nbsp;&nbsp;");
                    }
                }
            })
        };	
        
        

</script>