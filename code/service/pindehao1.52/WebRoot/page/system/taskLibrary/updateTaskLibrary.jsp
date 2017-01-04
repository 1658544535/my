<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n><s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">

$(function(){
	select2(${taskPojo.taskAge});
	select3(${taskPojo.taskType});
});
function setSecond(obj){  
    var val = obj.value;
    $("#second").empty();
    select2(val);
     
}  

function select2(val) {
	var ability = "${taskPojo.ability}";
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
function setThree(obj){  
    var val = obj.value;
    $("#three").empty();
    select3(val);

}  

function select3(val) {
var taskTypeLink = "${taskPojo.taskTypeLink}";
    $.ajax(
    {
        type: "get",
        url: "getTaskTypeLink.do?taskType="+val ,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
        	if(val == 1){
        		for (var i = 0; i < o_msg.length; i++) {
                	var selStr = "";
                	if(o_msg[i].taskTypeLinkValue == taskTypeLink){
                		selStr = " selected='selected' ";
                	}
                    $("#three").append("<option value=" + o_msg[i].taskTypeLinkValue + selStr +">" + o_msg[i].taskTypeLinkName + "</option>");
                }
        	}else{
        		$("#three").append("<option value='0'>------</option>");
        	}
            
        }
    })
};   

</script>	
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>任务管理</a> &gt; 
  <a href="taskLibraryList.do">任务库</a> &gt; 
  <a href="#">编辑任务</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateTaskLibrary.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="taskPojo.id" value="${taskPojo.id}" class="floatLeft" id="taskId" />
    <input type="hidden" name="taskPojo.updateName" value="${sessionScope.loginPojoId}">
    <tr>
	    <td align="right" class="grey" width="15%">任务标题：</td>
		<td>
			<input type="text" name="taskPojo.taskTitle" value="${taskPojo.taskTitle}" class="floatLeft" id="taskTitle" />
		<span id="title_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">年龄：</td>
		<td><select id="taskAge" name="taskPojo.taskAge" class="floatLeft" onchange="setSecond(this)">
		<option value="">---- 选择年龄段 ----</option>
		<option value="1" <s:if test="taskPojo.taskAge==1">selected="selected"</s:if>>0~6月</option>  
        <option value="2" <s:if test="taskPojo.taskAge==2">selected="selected"</s:if>>6~12月</option> 
        <option value="3" <s:if test="taskPojo.taskAge==3">selected="selected"</s:if>>1~3岁</option>
        <option value="4" <s:if test="taskPojo.taskAge==4">selected="selected"</s:if>>3~6岁</option>
        <option value="5" <s:if test="taskPojo.taskAge==5">selected="selected"</s:if>>6~12岁</option>
        <option value="6" <s:if test="taskPojo.taskAge==6">selected="selected"</s:if>>12~16岁</option> 
		</select>					
		<span id="title_taskAge"></span></td>
    </tr> 
    <tr>
		<td align="right" class="grey" width="15%">能力：</td>
		<td><select id="second" name="taskPojo.ability" class="floatLeft">
		</select>					
		<span id="title_mgId"></span></td>
    </tr>
	
	<tr>
		<td align="right" class="grey" width="15%">任务类型：</td>
		<td><select id="taskType" name="taskPojo.taskType" class="floatLeft" onchange="setThree(this)">
		<option value="1" <s:if test="taskPojo.taskType==1">selected="selected"</s:if>>线上</option>  
        <option value="2" <s:if test="taskPojo.taskType==2">selected="selected"</s:if>>线下</option> 
		</select>					
		<select id="three" name="taskPojo.taskTypeLink" class="floatLeft"> 
		</select>					
    </tr> 
	
	<tr>
	    <td align="right" class="grey" width="15%">分值：</td>
		<td>
		<input type="text" name="taskPojo.taskScore" value="${taskPojo.taskScore}" class="floatLeft" id="taskScore" />		
		<span id="title_taskScore"></span></td>
	</tr>

	<tr>
	    <td align="right" class="grey" width="15%">任务简介：</td>
		<td>
		<textarea rows=6  cols=30 name="taskPojo.taskContent" id="taskContent"  class="floatLeft"/>${taskPojo.taskContent}</textarea>	
		<span id="title_taskContent"></span></td>
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
	$(document).ready(function(){
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});

});	
	var title           =new tt.Field("任务标题 ","taskPojo.taskTitle").setMsgId("title_mgId");
	var taskAge         =new tt.Field("年龄 ","taskPojo.taskAge").setMsgId("title_taskAge");  
	var taskScore       =new tt.Field("分值 ","taskPojo.taskScore").setMsgId("title_taskScore");
	var taskContent     =new tt.Field("任务简介 ","taskPojo.taskContent").setMsgId("title_taskContent");
	tt.vf.req.add(title,taskAge,taskScore,taskContent);
	tt.vf.num.add(taskScore);
	
	tt.vf.int.add(taskScore);
	new tt.NRV().set(0, 100).add(taskScore);
</script>