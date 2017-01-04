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
  <a href="goAddNavigation.do">App导航新增</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addNavigationOK.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
		<td align="right" class="grey" width="15%">导航分类名：</td>
		<td><input type="text" name="navigationPojo.name" id="navigationPojo.name" value="<s:property value="navigationPojo.name"/>"  class="floatLeft"/></td>
		<td><span id="name_mgId"></span></td>
    </tr>
    <tr>
	   <td align="right" class="grey" width="15%">分类级别：</td>
       <td><select name="navigationPojo.level" id="navigationPojo.level"  class="floatLeft">
							<%--<option value="1">一级类目</option>--%>
							<option value="2" selected="selected">二级类目</option>
				    		</select></td>
		<td><span id="level_mgId"></span></td>
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
        <td><input type="text" name="navigationPojo.sorting" id="navigationPojo.sorting" value="<s:property value="navigationPojo.sorting"/>"  class="floatLeft"/></td>		
		<td><span id="sorting_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="navigationPojo.status" id="navigationPojo.status"  class="floatLeft">
							<option value="1">未审核</option>
							<option value="2">已审核</option>
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
	$("select[name='navigationPojo.level']").bind("change", select);
});
function select(){
    var op=$("select[name='navigationPojo.level']").val();
    if(op==1){
		var names      =new tt.Field("导航分类名 ","navigationPojo.name").setMsgId("name_mgId");
		var level      =new tt.Field("分类级别","navigationPojo.level").setMsgId("level_mgId");
		var categoryId1=new tt.Field("一级分类名称","navigationPojo.categoryId1").setMsgId("categoryId1_mgId");
	    var sorting    =new tt.Field("排序",  "navigationPojo.sorting").setMsgId("sorting_mgId");
		var v_status     =new tt.Field("审核状态","navigationPojo.status").setMsgId("status_mgId");	
		tt.vf.req.add(names,level,categoryId1,sorting,v_status);}
    else{
	    var names      =new tt.Field("导航分类名 ","navigationPojo.name").setMsgId("name_mgId");
		var level      =new tt.Field("分类级别","navigationPojo.level").setMsgId("level_mgId");
		var categoryId1=new tt.Field("一级分类名称","navigationPojo.categoryId1").setMsgId("categoryId1_mgId");
		var categoryId2=new tt.Field("二级分类名称","navigationPojo.categoryId2").setMsgId("categoryId2_mgId");
	    var sorting    =new tt.Field("排序",  "navigationPojo.sorting").setMsgId("sorting_mgId");
		var v_status     =new tt.Field("审核状态","navigationPojo.status").setMsgId("status_mgId");	
		tt.vf.req.add(names,level,categoryId1,categoryId2,sorting,v_status);}
    if(op==1){   
	    $("#data1").show();
	    $("#data2").hide();
    }
    else{ 
	    $("#data1").show();
	    $("#data2").show();   
    }
}
$(document).ready(function(){
	select();
});
</script>