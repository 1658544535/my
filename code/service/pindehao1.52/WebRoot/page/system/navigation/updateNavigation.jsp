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
<script type="text/javascript">
$(function(){
		select1();
		$('#categoryId1').bind("change", select2);
	});
function select1() {
		$("#categoryId1").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getProductTypeByPid.do?pid=-1",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${navigationPojo.categoryId1}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#categoryId1").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
			}
		})
	};
function select2() {
			$("#categoryId2").html("");
            $("#categoryId2").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getProductTypeByPid.do?level=1&pid="+$('#categoryId1').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                    	if("${navigationPojo.categoryId2}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#categoryId2").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
</script>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>商家中心</a> &gt; 
  <a href="navigationList.do">App导航</a> &gt; 
  <a href="#">App导航编辑</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateNavigation.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="navigationPojo.id" id="shopLatitude" value="${navigationPojo.id}" class="inputText" />
    <tr>
		<td align="right" class="grey" width="15%">导航分类名：</td>
		<td><input type="text" name="navigationPojo.name" id="navigationPojo.name" value="<s:property value="navigationPojo.name"/>" /></td>
		<td><span id="name_mgId"></span></td>
    </tr>
    <tr>
        <td align="right" class="grey" width="15%">当前的分类级别和名称：</td> 
        <td><input type="hidden" name="navigationPojo.levelAll" id="navigationPojo.levelAll" value="${navigationPojo.levelAll}" />${navigationPojo.levelAll}
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <font color="#FF0000">亲，如需修改分类级别和其名称，请在下方“修改的分类级别”和“**分类名称”处修改哦~~</font></td>
        <td><span id="levelAll_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">修改的分类级别：</td>
        <td><select name="navigationPojo.levels" id="navigationPojo.levels"  class="floatLeft">
                            <option value="-1">→不修改时请选择本项</option>
							<%--<option value="1">一级类目</option>--%>
							<option value="2">二级类目</option>
				    		</select></td>
		<td><span id="levels_mgId"></span></td>
    </tr>   
    <tr style="display: none;" id="data1">
		<td align="right" class="grey" width="15%">一级分类名称：</td>
        <td><select  name="navigationPojo.categoryId1" id="categoryId1" class="floatLeft" ></select>	
		<span id="categoryId1_mgId"></span></td>
    </tr>
    <tr style="display: none;" id="data2">
		<td align="right" class="grey" width="15%">二级分类名称：</td>
        <td><select name="navigationPojo.categoryId2" id="categoryId2"  class="floatLeft"></select>
		<span id="categoryId2_mgId"></span></td>
    </tr>    
    <tr>
		<td align="right" class="grey" width="15%">排序：</td>
        <td><input type="text" name="navigationPojo.sorting" id="navigationPojo.sorting" value="<s:property value="navigationPojo.sorting"/>" /></td>		
		<td><span id="sorting_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="navigationPojo.status" id="navigationPojo.status"  class="floatLeft">
							<option value="1" <s:if test="navigationPojo.status==1">selected="selected"</s:if>>未审核</option>
							<option value="2" <s:if test="navigationPojo.status==2">selected="selected"</s:if>>已审核</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
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
$(function(){
	$("select[name='navigationPojo.levels']").bind("change", select);
});
function select(){
    var op=$("select[name='navigationPojo.levels']").val();
    if(op==1){
		var names      =new tt.Field("导航分类名 ","navigationPojo.name").setMsgId("name_mgId");
		var levels     =new tt.Field("分类级别","navigationPojo.levels").setMsgId("levels_mgId");
		var categoryId1=new tt.Field("一级分类名称","navigationPojo.categoryId1").setMsgId("categoryId1_mgId");
	    var sorting    =new tt.Field("排序",  "navigationPojo.sorting").setMsgId("sorting_mgId");
		var v_status     =new tt.Field("审核状态","navigationPojo.status").setMsgId("status_mgId");	
		tt.vf.req.add(names,levels,categoryId1,sorting,v_status);		
		$("#data1").show();
	    $("#data2").hide();
	}
	else if(op==2){
		var names      =new tt.Field("导航分类名 ","navigationPojo.name").setMsgId("name_mgId");
		var levels     =new tt.Field("分类级别","navigationPojo.levels").setMsgId("levels_mgId");
		var categoryId1=new tt.Field("一级分类名称","navigationPojo.categoryId1").setMsgId("categoryId1_mgId");
		var categoryId2=new tt.Field("二级分类名称","navigationPojo.categoryId2").setMsgId("categoryId2_mgId");
	    var sorting    =new tt.Field("排序",  "navigationPojo.sorting").setMsgId("sorting_mgId");
		var v_status     =new tt.Field("审核状态","navigationPojo.status").setMsgId("status_mgId");	
		tt.vf.req.add(names,levels,categoryId1,categoryId2,sorting,v_status);		
		$("#data1").show();
	    $("#data2").show();   
	}
	else{
	    var names      =new tt.Field("导航分类名 ","navigationPojo.name").setMsgId("name_mgId");
	    var levels     =new tt.Field("分类级别","navigationPojo.levels").setMsgId("levels_mgId");
	    var sorting    =new tt.Field("排序",  "navigationPojo.sorting").setMsgId("sorting_mgId");
		var v_status     =new tt.Field("审核状态","navigationPojo.status").setMsgId("status_mgId");	
		tt.vf.req.add(names,levels,sorting,v_status);		
		$("select[name='navigationPojo.levels']").val("-1");
	    $("#data1").hide();
	    $("#data2").hide();
	}
}
$(document).ready(function(){
	$("select[name='navigationPojo.levels']").val("${navigationPojo.levels}");
	select();
});
</script>