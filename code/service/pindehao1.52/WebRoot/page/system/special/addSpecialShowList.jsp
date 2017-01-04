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
		select5();
	    select4(); 
		select3();
		select1();
		$('#mainCategory1').bind("change", select2);
	});
	function select1() {
	$("#mainCategory1").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getProductTypeByPid.do?pid=-1",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${specialShowPojo.mainCategory1}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#mainCategory1").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
				if("${specialShowPojo.mainCategory1}"!=null && "${specialShowPojo.mainCategory1}"!=""){
					select2();
				}
			}
		})
	};
	function select2() {
            $("#mainCategory2").html("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getProductTypeByPid.do?level=1&pid="+$('#mainCategory1').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                    	if("${specialShowPojo.mainCategory2}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#mainCategory2").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
    function select3() {
    $("#userBrandId").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "findUserBrandNameByUserId.do",
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        var selectedStr = "";
                    	if("${specialShowPojo.userBrandId}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                    	$("#userBrandId").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].userName +'-'+ o_msg[i].brandName + "</option>");
                    }
                }
            })
        };
    function select4() {
    $("#ageRange").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getSyntheticalDictListByType.do?syntheticalDict.type=age_range",
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        var selectedStr = "";
                    	if("${specialShowPojo.ageRange}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#ageRange").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
    function select5() {
    	$("#channelId").append("");
            $.ajax(
            {
                type: "post",
                url: "findChildrenChannelList.do?childrenChannelPojo.status=1",
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        var selectedStr = "";
                        if("${specialShowPojo.channelId}".indexOf(':'+ o_msg[i].id+':') != -1){
                    		selectedStr = "checked='true'";
                    	}
                        $("#channelId").append("<label><input type='checkbox' name='channelIds' value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</label>&nbsp;&nbsp;");
                    }
                }
            })
        };
</script>	
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>专场特卖设置</a> &gt; 
  <a href="specialShowList.do">专场详情列表</a> &gt; 
  <a href="goAddSpecialShowList.do">增加专场详情</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addSpecialShowListOk.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="specialShowPojo.id" value="${specialShowPojo.id}" class="floatLeft" id="ticketName" />
    <tr>
	    <td align="right" class="grey" width="15%">专场标题：</td>
		<td>
			<input type="text" name="specialShowPojo.title" value="${specialShowPojo.title}" class="floatLeft" id="ticketName" />
		<span id="title_mgId"></span></td>
	</tr>
	<!-- <tr>
    <td align="right" class="grey" width="15%">专场时间：</td>
	<td>
	从
	<input id="s" name="specialShowPojo.beginTimeStr" value="${specialShowPojo.beginTimeStr }" class="Wdate" type="text" onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'e\')}'})"/>       
	至
	<input id="e" name="specialShowPojo.endTimeStr" value="${specialShowPojo.endTimeStr }" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'s\')}'})"/>      
	</td>
	<td><span id="beginTime_mgId"></span><span id="endTime_mgId"></span></td>
	</tr> -->
	<tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td>
		<input type="text" name="specialShowPojo.sorting" value="${specialShowPojo.sorting}" class="floatLeft" id="ticketName" />		
		<span id="sorting_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">状态：</td>
        <td><input type="hidden" name="specialShowPojo.status" id="specialShowPojo.status"  class="floatLeft" value="1">待审核</td>					
		<span id="status_mgId"></span>
    </tr>
    <tr>
	    <td align="right" class="grey" width="15%">专场图片：</td>
		<td>
			<%-- <img  class="floatLeft" src="/upfiles/specialShow/${specialShowPojo.banner}" height="100px" /> --%>
						<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="specialPic">
						<font color="#FF0000">图片建议尺寸：800*500</font>
		</td>
		<span id="banner_mgId"></span>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">一级类目：</td>
		<td><select id="mainCategory1" name="specialShowPojo.mainCategory1" class="floatLeft" ></select>					
		<span id="mainCategory1_mgId"></span></td>
    </tr>    
    <tr>
        <td align="right" class="grey" width="15%">二级类目：</td>
		<td><select id="mainCategory2" name="specialShowPojo.mainCategory2" class="floatLeft" ><option value="">- 请选择 -</option></select>					
		<span id="mainCategory2_mgId"></span></td>
    </tr>
    <tr>
        <td align="right" class="grey" width="15%">专场品牌：</td>
		<td><select id="userBrandId" name="specialShowPojo.userBrandId" class="floatLeft" ></select>					
		<span id="userBrandId_mgId"></span></td>
    </tr>
    <tr>
        <td align="right" class="grey" width="15%">适用年龄段：</td>
		<td><select id="ageRange" name="specialShowPojo.ageRange" class="floatLeft" ></select>					
		<span id="ageRange_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey"  width="15%">优惠类型:</td>
        <td><select name="specialShowPojo.discountType" id="discountType"  class="floatLeft">
            <option value="0">无优惠</option>
			<option value="1">满减</option>
			<option value="2">满折</option>
	    </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="addbtn"><a class='edit_btn'  onclick=add() >增加一条优惠记录</a></span><span id="delbtn"  style="display: none;"><a class='edit_btn'  onclick=del() >删除一条优惠记录</a></span>
		<span id="discountType_mgId"></span></td>
	</tr>
	<tr id="data1">
	    <td align="right" class="grey"  width="15%">优惠内容:</td>
	    <td>满<input type="text" id="om1" name="specialShowPojo.om1" value="${specialShowPojo.om1}">元，减<input type="text" id="m1" name="specialShowPojo.m1" value="${specialShowPojo.m1}">元</td> 
	</tr>
	<tr style="display: none;" id="data2">
	    <td align="right" class="grey"  width="15%">优惠内容:</td>
	    <td>满<input type="text" id="om2" name="specialShowPojo.om2" value="${specialShowPojo.om2}">件享受<input type="text" id="m2" name="specialShowPojo.m2" value="${specialShowPojo.m2}">折</td>    
	</tr>
	<tr style="display: none;" id="data3">
	    <td align="right" class="grey"  width="15%">优惠内容:</td>
	    <td>满<input type="text" id="om3" name="specialShowPojo.om3" value="${specialShowPojo.om3}">元，减<input type="text" id="m3" name="specialShowPojo.m3" value="${specialShowPojo.m3}">元</td>	    
	</tr>
	<tr style="display: none;" id="data4">
	    <td align="right" class="grey"  width="15%">优惠内容:</td>
	    <td>满<input type="text" id="om4" name="specialShowPojo.om4" value="${specialShowPojo.om4}">件享受<input type="text" id="m4" name="specialShowPojo.m4" value="${specialShowPojo.m4}">折</td>	    
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">所属频道：</td>
		<td><div id="channelId" name="specialShowPojo.channelId" class="floatLeft" ></div></td>
	</tr>
    <tr>
		<td align="right" class="grey" width="15%">活动介绍：</td>
		<td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="specialShowPojo.introduction" id="content"></textarea>
		<span id="introduction_mgId"></span></td>
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
	var title           =new tt.Field("专场标题 ","specialShowPojo.title").setMsgId("title_mgId");
	var sorting         =new tt.Field("排序 ","specialShowPojo.sorting").setMsgId("sorting_mgId");
	var v_status          =new tt.Field("状态","specialShowPojo.status").setMsgId("status_mgId");
	var banner          =new tt.Field("专场图片","specialShowPojo.banner").setMsgId("banner_mgId");
	var introduction    =new tt.Field("活动介绍","specialShowPojo.introduction").setMsgId("introduction_mgId");	
  //var beginTime       =new tt.Field("开始时间 ","specialShowPojo.beginTimeStr").setMsgId("beginTime_mgId");
  //var endTime         =new tt.Field("结束时间 ","specialShowPojo.endTimeStr").setMsgId("endTime_mgId");		
	var mainCategory1   =new tt.Field("一级类目","specialShowPojo.mainCategory1").setMsgId("mainCategory1_mgId");
    var mainCategory2   =new tt.Field("二级类目","specialShowPojo.mainCategory2").setMsgId("mainCategory2_mgId");    
    var userBrandId     =new tt.Field("专场品牌","specialShowPojo.userBrandId").setMsgId("userBrandId_mgId");    
    var ageRange        =new tt.Field("适用年龄段","specialShowPojo.ageRange").setMsgId("ageRange_mgId");
    var discountType    =new tt.Field("优惠类型","specialShowPojo.discountType").setMsgId("discountType_mgId");  
    var introduction    =new tt.Field("活动介绍","specialShowPojo.introduction").setMsgId("introduction_mgId");    
	tt.vf.req.add(title,sorting,v_status,title,mainCategory1,mainCategory2,userBrandId,ageRange,discountType);
$(function(){
	$("select[name='specialShowPojo.discountType']").bind("change", select);
});	
function select(){
	var op=$("select[name='specialShowPojo.discountType']").val();
	if(op == 1){
		$("#data1").show();
		$("#data2").hide();
		$("#data3").hide();
		$("#data4").hide();
		$("#addbtn").show();
		$("#delbtn").hide();
		document.getElementById('om2').value="";
		document.getElementById('m2').value="";
		document.getElementById('om3').value="";
		document.getElementById('om4').value="";
		document.getElementById('m3').value="";
		document.getElementById('m4').value="";	
	}else if(op == 2){
		$("#data1").hide();
		$("#data2").show();
		$("#data3").hide();
		$("#data4").hide();
		$("#addbtn").show();		
		$("#delbtn").hide();
		document.getElementById('om1').value="";
		document.getElementById('m1').value="";
		document.getElementById('om3').value="";
		document.getElementById('om4').value="";
		document.getElementById('m3').value="";
		document.getElementById('m4').value="";
	}else{
		$("#addbtn").hide();
		$("#delbtn").hide();
		$("#data1").hide();
		$("#data2").hide();
		$("#data3").hide();
		$("#data4").hide();
		document.getElementById('om1').value="";
		document.getElementById('om2').value="";
		document.getElementById('m1').value="";
		document.getElementById('m2').value="";
		document.getElementById('om3').value="";
		document.getElementById('om4').value="";
		document.getElementById('m3').value="";
		document.getElementById('m4').value="";
	}
}
function add(){
            var op=$("select[name='specialShowPojo.discountType']").val();
            if(op==1){   
            $("#data1").show();
		    $("#data2").hide();      
			$("#data3").show();	
			$("#data4").hide();
			}
			if(op==2){
			$("#data1").hide();
	     	$("#data2").show();
	     	$("#data3").hide();	
			$("#data4").show();
			}
			$("#addbtn").hide();
			$("#delbtn").show();			
}	
function del(){      
			$("#data3").hide();	
			$("#data4").hide();
			$("#delbtn").hide();
			$("#addbtn").show();
			document.getElementById('om3').value="";
		    document.getElementById('om4').value="";
		    document.getElementById('m3').value="";
		    document.getElementById('m4').value="";		
}
$(document).ready(function(){
	select();
});
</script>