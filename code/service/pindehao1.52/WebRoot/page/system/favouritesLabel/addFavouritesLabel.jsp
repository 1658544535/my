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
  <div class="s_nav">
  <a>标签管理</a> &gt; 
  <a href="favouritesLabelList.do">有你喜欢</a> &gt; 
  <a href="#">标签新增</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addFavouritesLabel.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">标签名称：</td>
		<td>
			<input type="text" name="yourFavouritesPojo.name" class="floatLeft" value="${session.yourFavouritesPojo.name}" />
			<span id="name_mgId"></span>
		</td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">标签图：</td>
		<td>
			<input type="file" name="upfile" class="floatLeft" />
			<img src='' height='100px' name="imageFile" />
		</td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">所属年龄：</td>
		<td>
			<div name="ageTypeOption"></div>
			<select name="ageType" class="floatLeft">
				<option value="">----请选择----</option>
				<option value="1">0-6月</option>
				<option value="2">6-12月</option>
				<option value="3">1-3岁</option>
				<option value="4">3-6岁</option>
				<option value="5">6-12岁</option>
				<option value="6">12-16岁</option>
			</select>
			<span id="ageType_mgId"></span>
		</td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">标签类别：</td>
		<td>
			<select name="yourFavouritesPojo.contentType" class="floatLeft">
				<option value="1" <c:if test="${session.yourFavouritesPojo.contentType == 1 }">selected</c:if>>商品单品</option>
				<option value="2" <c:if test="${session.yourFavouritesPojo.contentType == 2 }">selected</c:if>>内容标签</option>
				<option value="3" <c:if test="${session.yourFavouritesPojo.contentType == 3 }">selected</c:if>>三级分类标签</option>
			</select>
		</td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">标签序号：</td>
		<td>
			<input type="text" name="yourFavouritesPojo.sorting" class="floatLeft" value="${session.yourFavouritesPojo.sorting}" />
			<span id="sorting_mgId"></span>
		</td>
	</tr>
    </table> 
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onClick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
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
	var v_name = new tt.Field("标签名称 ","yourFavouritesPojo.name").setMsgId("name_mgId");
	//var v_ageType = new tt.Field("标签名称 ","ageType").setMsgId("ageType_mgId");
	var v_sorting = new tt.Field("标签序号 ","yourFavouritesPojo.sorting").setMsgId("sorting_mgId");
	tt.vf.req.add(v_name,v_sorting);
	tt.vf.num.add(v_sorting);
	new tt.LV().set(0, 100).add(v_name);
	tt.vf.int.add(v_sorting);
	//new tt.NRV().set(0, '100').add(v_sorting);
	new tt.LV().set(0, 10).add(v_sorting);
	
	$("select[name='ageType']").change(function(){
		var val = $(this).val();
		var ageTypeName = "";
		if(val == 1){
			ageTypeName = " 0-6月 ";
		}else if(val == 2){
			ageTypeName = " 6-12月 ";
		}else if(val == 3){
			ageTypeName = " 1-3岁 ";
		}else if(val == 4){
			ageTypeName = " 3-6岁 ";
		}else if(val == 5){
			ageTypeName = " 6-12岁 ";
		}else if(val == 6){
			ageTypeName = " 12-16岁 ";
		}
		$("div[name='ageTypeOption']").append('<span name="delOption"><input type="button" class="" value="'+ageTypeName+'" title="删除" /><input type="hidden" name="ageTypes" class="" value="'+val+'" /><span> X </span></span>');
		//this.options.remove(val);
		//var name = "select[name='ageType'] option[value='"+val+"']";
		//$(name).remove();
	});
	
	$("body").on("click", "span[name='delOption']", function () {
		$(this).remove();
		$("select[name='ageType']").val('');
	});
	
	$(function(){
		<c:forEach items="${session.ageTypes }" var="a">
			var val = "${a}";
			var ageTypeName = "";
			if(val == 1){
				ageTypeName = " 0-6月 ";
			}else if(val == 2){
				ageTypeName = " 6-12月 ";
			}else if(val == 3){
				ageTypeName = " 1-3岁 ";
			}else if(val == 4){
				ageTypeName = " 3-6岁 ";
			}else if(val == 5){
				ageTypeName = " 6-12岁 ";
			}else if(val == 6){
				ageTypeName = " 12-16岁 ";
			}else{
				
			}
			$("div[name='ageTypeOption']").append('<span name="delOption"><input type="button" class="" value="'+ageTypeName+'" title="删除" /><input type="hidden" name="ageTypes" class="" value="${a}" /><span> X </span></span>');
		</c:forEach>
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